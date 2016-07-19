package cz.cvut.fel.ida.reads.readsIDA2016.util;

/**
 * Utility class similar to {@code java.lang.Math}.
 *
 * @author Petr Ryšavý
 */
public final class MathUtils {

    /** Do not let anybody to instantiate the class. */
    private MathUtils() {
    }

    /**
     * Finds minimum of the three arguments.
     *
     * @param a First argument.
     * @param b Second argument.
     * @param c Third argument.
     * @return {@code min(a, b, c)}.
     */
    public static int min(int a, int b, int c) {
        if (a <= b && a <= c)
            return a;
        return b <= c ? b : c;
    }

    /**
     * Finds minimum of the three arguments.
     *
     * @param a First argument.
     * @param b Second argument.
     * @param c Third argument.
     * @return {@code min(a, b, c)}.
     */
    public static double min(double a, double b, double c) {
        if (a <= b && a <= c)
            return a;
        return b <= c ? b : c;
    }

    /**
     * Calculates average of the two arguments.
     *
     * @param a First argument.
     * @param b Second argument.
     * @return {@code (a+b)/2}.
     */
    public static double average(double a, double b) {
        return (a + b) / 2.0;
    }
}
