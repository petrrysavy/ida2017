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
package cz.cvut.fel.ida.reads.readsIDA2016;

import cz.cvut.fel.ida.reads.readsIDA2016.dist.Distance;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Test for comparing bigger reads bags. If you do some changes in the code, you can
 * use this test to see that the changes (probably) did not influence result of the
 * distance calculations.
 *
 * @author Petr Ryšavý
 */
@RunWith(Parameterized.class)
public class DistanceProviderTest {
    // inputs
    private static final char[] NUCLEOTIDES = new char[]{'A', 'T', 'C', 'G'};
    public static ReadsBag BAG_1 = generateReadBag(135, 30, 42);
    public static ReadsBag BAG_2 = generateReadBag(67, 30, 82);
    // parameters of the test - expected distance and distance
    private final Distance<ReadsBag> distanceCalculator;
    private final double expectedResult;

    public DistanceProviderTest(Distance<ReadsBag> distanceCalculator, double expectedResult) {
        this.distanceCalculator = distanceCalculator;
        this.expectedResult = expectedResult;
    }

    private static ReadsBag generateReadBag(int bagSize, int readLength, long seed) {
        final ReadsBag bag = new ReadsBag(bagSize, "A test reads bag");
        final Random rand = new Random(seed);
        for (int i = 0; i < bagSize; i++) {
            // generate new string
            final char[] read = new char[readLength];
            for (int j = 0; j < readLength; j++)
                read[j] = NUCLEOTIDES[rand.nextInt(NUCLEOTIDES.length)];
            bag.add(new Sequence(read, "Random read " + i));
        }
        return bag;
    }

    @Parameters
    public static Collection<Object[]> data() {
        final DistanceProvider dp = new DistanceProvider(30, 3, 0.33);
        return Arrays.asList(new Object[][]{
            {dp.getMaxSizeDistance(), 135.0},
            {dp.getDistME(), 13.637037037037038},
            {dp.getDistMES(), 13.378220011055832},
            {dp.getDistMESS(), 1806.0597014925374},
            {dp.getDistMESSG(), 1231.4691316146543},
            {dp.getDistMESSGM(), 1579.5753052917232}
        });
    }

    @Test
    public void test() {
        assertThat(distanceCalculator.getDistance(BAG_1, BAG_2), is(closeTo(expectedResult, 1e-10)));
    }
}
