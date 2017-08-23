/* 
 * Copyright (C) 2017 Petr Ryšavý <petr.rysavy@fel.cvut.cz>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
