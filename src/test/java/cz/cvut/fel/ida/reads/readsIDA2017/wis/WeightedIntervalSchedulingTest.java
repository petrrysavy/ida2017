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
package cz.cvut.fel.ida.reads.readsIDA2017.wis;

import java.util.Arrays;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Test class for the weighted interval schedulling problem.
 *
 * @author Petr Ryšavý
 */
@RunWith(Parameterized.class)
public class WeightedIntervalSchedulingTest {

    private static final SimpleInterval A = new SimpleInterval(-1, 11, 5);
    private static final SimpleInterval B = new SimpleInterval(3, 5, 7);
    private static final SimpleInterval C = new SimpleInterval(0, 2, 2);
    private static final SimpleInterval D = new SimpleInterval(6, 10, 3);
    private static final SimpleInterval E = new SimpleInterval(1, 7, 9);
    private static final SimpleInterval F = new SimpleInterval(0, 1, 2);
    private static final SimpleInterval G = new SimpleInterval(8, 9, 1);
    private static final SimpleInterval H = new SimpleInterval(6, 7, 5);

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {asArray(A), 5.0, asArray(A)},
            {asArray(A, B), 7.0, asArray(B)},
            {asArray(A, F, G), 5.0, asArray(A)},
            {asArray(A, B, F, G, H), 15.0, asArray(F, B, G, H)},
            {asArray(A, B, G, H), 13.0, asArray(B, G, H)},
            {asArray(A, B, D, E, F, G, H), 15.0, asArray(B, F, G, H)},
            {asArray(A, B, C, D, E, G, H), 15.0, asArray(B, C, G, H)},
            {asArray(E, F, H), 11.0, asArray(E, F)},
            {asArray(E, F, G, H), 12.0, asArray(E, F, G)},
            {asArray(B, D, E), 10.0, asArray(B, D)},});
    }

    private final SimpleInterval[] arr;
    private final SimpleInterval[] result;
    private final double optimum;

    public WeightedIntervalSchedulingTest(SimpleInterval[] arr, double optimum, SimpleInterval[] result) {
        this.arr = arr;
        this.optimum = optimum;
        this.result = result;
    }

    @Test
    public void testGetOptimalValueSorted() {
        assertThat(WeightedIntervalScheduling.getOptimalValueSorted(arr), is(equalTo(optimum)));
    }

    @Test
    public void testSolveSorted() {
        assertThat(WeightedIntervalScheduling.solve(arr), containsInAnyOrder(result));
    }

    /**
     * Converts list of arguments to an array.
     * @param <T> Type of values.
     * @param vals This will be in the array.
     * @return Array with the values from {@code vals}.
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> T[] asArray(T... vals) {
        return vals;
    }
}
