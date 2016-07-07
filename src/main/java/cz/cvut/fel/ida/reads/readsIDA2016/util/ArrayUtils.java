package cz.cvut.fel.ida.reads.readsIDA2016.util;

import java.util.Arrays;

/**
 *
 * @author Petr Ryšavý
 */
public final class ArrayUtils {

    private ArrayUtils() {
    }

    public static double[] nTimes(double value, int n) {
        final double[] arr = new double[n];
        Arrays.fill(arr, value);
        return arr;
    }
}
