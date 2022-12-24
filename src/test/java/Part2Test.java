import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Part2Test {
    @Test
    void exampleTest() {
        assertThat(Part2.calculateExteriorSurfaceArea(Part1.parseParticles("test.txt"))).isEqualTo(58);
    }

    @Test
    void exampleTest2() {
        assertThat(Part2.calculateExteriorSurfaceArea(Part1.parseParticles("test2.txt"))).isEqualTo(32);
    }

    @Test
    void part2Test() {
        assertThat(Part2.calculateExteriorSurfaceArea(Part1.parseParticles("input.txt"))).isEqualTo(2458);
    }
}
