package cz.cvut.fel.ida.reads.readsIDA2016.util;

/**
 *
 * @author Petr Ryšavý
 */
public final class MathUtils {

    private MathUtils() {
    }

    public static int min(int a, int b, int c) {
        if (a <= b && a <= c)
            return a;
        return b <= c ? b : c;
    }

    public static double min(double a, double b, double c) {
        if (a <= b && a <= c)
            return a;
        return b <= c ? b : c;
    }

    public static double average(double a, double b) {
        return (a + b) / 2.0;
    }
}
