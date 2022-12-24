import java.util.Collection;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

public class InvertCube extends Cube {

    public InvertCube(Collection<Particle> boundaries) {
        super(boundaries);
    }

    public void fillCube(Predicate<Particle> condition) {
        Predicate<Particle> fillCondition = condition.and(this::withinBoundaries).and(not(this::contains).or(getMaximumBoundary()::equals).or(getMinimumBoundary()::equals));
        fillInDirection(getMaximumBoundary(), fillCondition, -1);
        fillInDirection(getMinimumBoundary(), fillCondition, 1);
    }

    private boolean withinBoundaries(Particle particle) {
        Particle minimumBoundary = getMinimumBoundary();
        Particle maximumBoundary = getMaximumBoundary();
        return minimumBoundary.x() <= particle.x() &&
                minimumBoundary.y() <= particle.y() &&
                minimumBoundary.z() <= particle.z() &&
                maximumBoundary.x() >= particle.x() &&
                maximumBoundary.y() >= particle.y() &&
                maximumBoundary.z() >= particle.z();
    }

    public void fillInDirection(Particle particle, Predicate<Particle> condition, int direction) {
        if(condition.test(particle)) {
            addParticle(particle);

            fillInDirection(new Particle(particle, direction, 0, 0), condition, direction);
            fillInDirection(new Particle(particle, 0, direction, 0), condition, direction);
            fillInDirection(new Particle(particle, 0, 0, direction), condition, direction);
            fillInDirection(new Particle(particle, -direction, 0, 0), condition, direction);
            fillInDirection(new Particle(particle, 0, -direction, 0), condition, direction);
            fillInDirection(new Particle(particle, 0, 0, -direction), condition, direction);
        }
    }
}
