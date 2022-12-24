import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        System.out.println(calculateSurfaceArea(parseParticles("input.txt")));
    }

    public static Cube parseParticles(String fileName) {
        InputStream resourceAsStream = Part1.class.getResourceAsStream(fileName);
        assert resourceAsStream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));

        return new Cube(reader.lines().map(Particle::new).collect(Collectors.toSet()));
    }

    public static int calculateSurfaceArea(Cube cube) {
        return cube.findExposedSides();
    }
}
