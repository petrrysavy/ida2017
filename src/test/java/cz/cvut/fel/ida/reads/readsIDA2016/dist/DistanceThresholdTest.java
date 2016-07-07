package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Petr Ryšavý
 */
public class DistanceThresholdTest {

    private DistanceThreshold<Integer> threshold;

    @Before
    public void bootstrap() {
        threshold = new DistanceThreshold<>((Integer a, Integer b) -> 1.0 - Math.exp(-Math.abs((a - b) / 10.0)), 0.3, 1.0);
    }

    @Test
    public void testGetBorderGapPenaulty() {
        assertThat(threshold.getDistance(0, 0), is(closeTo(0.0, 1e-10)));
        assertThat(threshold.getDistance(0, 1), is(closeTo(0.09516258196404042684, 1e-10)));
        assertThat(threshold.getDistance(0, 2), is(closeTo(0.18126924692201814133, 1e-10)));
        assertThat(threshold.getDistance(0, 3), is(closeTo(0.25918177931828213393, 1e-10)));
        assertThat(threshold.getDistance(0, 4), is(closeTo(1.0, 1e-10)));
        assertThat(threshold.getDistance(0, 100), is(closeTo(1.0, 1e-10)));
    }

    @Test
    public void testSymmetric() {
        assertThat(threshold.isSymmetric(), is(true));
    }

    @Test
    public void testNormalizedFalse() {
        assertThat(threshold.isZeroOneNormalized(), is(false));
    }
}
