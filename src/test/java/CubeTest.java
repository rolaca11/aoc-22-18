import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CubeTest {

    @Test
    void findComponentsInSampleOneComponent() {
        Cube subject = new Cube(Collections.emptyList());

        int result = subject.findComponentsInSample(List.of(
                new Particle(0, 0, 0),
                new Particle(0, 0, 1),
                new Particle(0, 0, 2),
                new Particle(0, 0, 3)
        ), Particle::z);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void findComponentsInSampleMultipleComponents() {
        Cube subject = new Cube(Collections.emptyList());

        int result = subject.findComponentsInSample(List.of(
                new Particle(0, 0, 0),
                new Particle(0, 0, 1),
                new Particle(0, 0, 2),
                new Particle(0, 0, 4),
                new Particle(0, 0, 5),
                new Particle(0, 0, 7),
                new Particle(0, 0, 9),
                new Particle(0, 0, 10)
        ), Particle::z);

        assertThat(result).isEqualTo(4);
    }

    @Test
    void findExposedSidesCoherentCube() {
        Cube subject = new Cube(List.of(
                new Particle(0, 0, 0),
                new Particle(0, 0, 1),
                new Particle(0, 1, 0),
                new Particle(0, 1, 1),
                new Particle(1, 0, 0),
                new Particle(1, 0, 1),
                new Particle(1, 1, 0),
                new Particle(1, 1, 1)
        ));

        assertThat(subject.findExposedSides()).isEqualTo(24);
    }

    @Test
    void findExposedSidesExampleCube() {
        Cube subject = new Cube(List.of(
                new Particle(2,2,2),
                new Particle(1,2,2),
                new Particle(3,2,2),
                new Particle(2,1,2),
                new Particle(2,3,2),
                new Particle(2,2,1),
                new Particle(2,2,3),
                new Particle(2,2,4),
                new Particle(2,2,6),
                new Particle(1,2,5),
                new Particle(3,2,5),
                new Particle(2,1,5),
                new Particle(2,3,5)
        ));

        assertThat(subject.findExposedSides()).isEqualTo(64);
    }
}