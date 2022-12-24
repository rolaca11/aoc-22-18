import java.util.Set;

import static java.util.function.Predicate.not;

public class Part2 {
    public static void main(String[] args) {
        System.out.println(calculateExteriorSurfaceArea(Part1.parseParticles("input.txt")));
    }

    public static int calculateExteriorSurfaceArea(Cube cube) {
        InvertCube invertCube = createInvertCubeOf(cube);
        invertCube.fillCube(not(cube::contains));

        Particle cubeSize = invertCube.size();

        return invertCube.findExposedSides() -
                cubeSize.x() * cubeSize.y() * 2 -
                cubeSize.x() * cubeSize.z() * 2 -
                cubeSize.y() * cubeSize.z() * 2;
    }

    public static InvertCube createInvertCubeOf(Cube cube) {
        return new InvertCube(Set.of(
                new Particle(cube.getMaximumBoundary(), 1, 1, 1),
                new Particle(cube.getMinimumBoundary(), -1, -1, -1)
        ));
    }
}
