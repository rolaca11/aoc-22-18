import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Cube {
    private final Collection<Particle> particles = new HashSet<>();

    public Cube(Collection<Particle> particles) {
        this.particles.addAll(particles);
    }

    private Map<Integer, List<Particle>> getSlices(Collection<Particle> particles, Function<Particle, Integer> direction) {
        return particles.stream().collect(groupingBy(direction));
    }

    int findComponentsInSample(Collection<Particle> particles, Function<Particle, Integer> direction) {
        LinkedList<Particle> sortedParticles = particles.stream().sorted(Comparator.comparing(direction)).collect(Collectors.toCollection(LinkedList::new));

        int componentCount = 1;
        int lastParticlePosition = direction.apply(sortedParticles.getFirst());
        sortedParticles.removeFirst();
        for (var particle : sortedParticles) {
            int currentParticlePosition = direction.apply(particle);
            if (currentParticlePosition - lastParticlePosition > 1) {
                componentCount++;
            }
            lastParticlePosition = currentParticlePosition;
        }
        return componentCount;
    }

    public int findExposedSidesX() {
        return getSlices(particles, Particle::z).values().parallelStream()
                .map(slice -> getSlices(slice, Particle::y))
                .map(Map::values)
                .flatMap(Collection::stream)
                .mapToInt(sample -> findComponentsInSample(sample, Particle::x))
                .sum() * 2;
    }

    public int findExposedSidesY() {
        return getSlices(particles, Particle::z).values().parallelStream()
                .map(slice -> getSlices(slice, Particle::x))
                .map(Map::values)
                .flatMap(Collection::stream)
                .mapToInt(sample -> findComponentsInSample(sample, Particle::y))
                .sum() * 2;
    }

    public int findExposedSidesZ() {
        return getSlices(particles, Particle::y).values().parallelStream()
                .map(slice -> getSlices(slice, Particle::x))
                .map(Map::values)
                .flatMap(Collection::stream)
                .mapToInt(sample -> findComponentsInSample(sample, Particle::z))
                .sum() * 2;
    }

    public int findExposedSides() {
        return findExposedSidesX() + findExposedSidesY() + findExposedSidesZ();
    }

    public boolean contains(Particle particle) {
        return particles.contains(particle);
    }

    public void addParticle(Particle particle) {
        particles.add(particle);
    }

    public Particle getMinimumBoundary() {
        return new Particle(
                particles.stream().map(Particle::x).min(Comparator.naturalOrder()).get(),
                particles.stream().map(Particle::y).min(Comparator.naturalOrder()).get(),
                particles.stream().map(Particle::z).min(Comparator.naturalOrder()).get()
        );
    }

    public Particle getMaximumBoundary() {
        return new Particle(
                particles.stream().map(Particle::x).max(Comparator.naturalOrder()).get(),
                particles.stream().map(Particle::y).max(Comparator.naturalOrder()).get(),
                particles.stream().map(Particle::z).max(Comparator.naturalOrder()).get()
        );
    }

    public Particle size() {
        return new Particle(
                getMaximumBoundary().x() - getMinimumBoundary().x() + 1,
                getMaximumBoundary().y() - getMinimumBoundary().y() + 1,
                getMaximumBoundary().z() - getMinimumBoundary().z() + 1
        );
    }
}
