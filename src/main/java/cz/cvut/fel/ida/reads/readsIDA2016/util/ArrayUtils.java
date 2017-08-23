/*
 * Copyright (c) 2016 Petr Rysavy <rysavpe1@fel.cvut.cz>
 *
 * This file is part of the project readsIDA2016, which is available on 
 * <https://github.com/petrrysavy/readsIDA2016/>.
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
 * along with This program.  If not, see <http ://www.gnu.org/licenses/>.
 */
package cz.cvut.fel.ida.reads.readsIDA2016.util;

import java.util.Arrays;

/**
 * Utility class for manipulating arrays.
 *
 * @author Petr Ryšavý
 */
public final class ArrayUtils {

    /** Do not let anybody to instantiate the class. */
    private ArrayUtils() {
    }

    /**
     * Creates new double array that contains {@code n}-times {@code value}.
     *
     * @param value The value that should be in the array.
     * @param n Length of the desired array.
     * @return Array that contains {@code n}-times {@code value}.
     */
    public static double[] nTimes(double value, int n) {
        final double[] arr = new double[n];
        Arrays.fill(arr, value);
        return arr;
    }

    /**
     * Creates a copy of the array that contains values from the last character
     * to the first character.
     * @param arr Array to be reversed.
     * @return Copy of {@code arr} with elements from the end to the beginning.
     */
    public static char[] reversedCopy(char[] arr) {
        final char[] reversed = new char[arr.length];
        for (int i = 0; i < arr.length; i++)
            reversed[i] = arr[arr.length - i - 1];
        return reversed;
    }
}
