import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Part1Test {

    @Test
    void exampleTest() {
        assertThat(Part1.calculateSurfaceArea(Part1.parseParticles("test.txt"))).isEqualTo(64);
    }

    @Test
    void part1Test() {
        assertThat(Part1.calculateSurfaceArea(Part1.parseParticles("input.txt"))).isEqualTo(4370);
    }
}
