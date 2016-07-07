package cz.cvut.fel.ida.reads.readsIDA2016.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Petr Ryšavý
 */
public class ArrayUtilsTest {

    public ArrayUtilsTest() {
    }

    @Test
    public void testNTimes() {
        assertThat(ArrayUtils.nTimes(0.0, 0), is(equalTo(new double[]{})));
        assertThat(ArrayUtils.nTimes(0.0, 1), is(equalTo(new double[]{0.0})));
        assertThat(ArrayUtils.nTimes(0.0, 3), is(equalTo(new double[]{0.0, 0.0, 0.0})));
        assertThat(ArrayUtils.nTimes(Double.MAX_VALUE, 3), is(equalTo(new double[]{Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE})));
        assertThat(ArrayUtils.nTimes(0.8519018499, 3), is(equalTo(new double[]{0.8519018499, 0.8519018499, 0.8519018499})));
    }

}
