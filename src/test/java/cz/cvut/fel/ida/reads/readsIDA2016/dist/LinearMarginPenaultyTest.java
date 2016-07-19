package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

/**
 * @author Petr Ryšavý
 */
public class LinearMarginPenaultyTest {

    public LinearMarginPenaultyTest() {
    }

    @Test
    public void testGetMarginGapPenalty1() {
        LinearMarginPenalty penalty = new LinearMarginPenalty(2, 2);
        assertThat(penalty.getMarginGapPenalty(0, 6), is(equalTo(0.0)));
        assertThat(penalty.getMarginGapPenalty(1, 6), is(equalTo(0.0)));
        assertThat(penalty.getMarginGapPenalty(2, 6), is(closeTo(2.0/3.0, 1e-10)));
        assertThat(penalty.getMarginGapPenalty(3, 6), is(closeTo(4.0/3.0, 1e-10)));
        assertThat(penalty.getMarginGapPenalty(4, 6), is(equalTo(2.0)));
        assertThat(penalty.getMarginGapPenalty(5, 6), is(equalTo(2.0)));
    }

    @Test
    public void testGetMarginGapPenalty2() {
        LinearMarginPenalty penalty = new LinearMarginPenalty(4.5, 2);
        for(int g = 0; g < 4; g++)
            assertThat(penalty.getMarginGapPenalty(g, 30), is(equalTo(0.0)));
        assertThat(penalty.getMarginGapPenalty(4, 30), is(closeTo(1.0 / 22.0, 1e-10)));
        assertThat(penalty.getMarginGapPenalty(13, 30), is(closeTo(19.0 / 22.0, 1e-10)));
        for(int g = 26; g < 30; g++)
            assertThat(penalty.getMarginGapPenalty(g, 30), is(equalTo(2.0)));
    }

}
