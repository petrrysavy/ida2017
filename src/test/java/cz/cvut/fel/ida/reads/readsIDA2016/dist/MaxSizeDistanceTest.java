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
package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;
import org.junit.Before;
import org.junit.Test;

import static cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag.fromString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Petr Ryšavý
 */
public class MaxSizeDistanceTest {

    private MaxSizeDistance distance;

    @Before
    public void init() {
        distance = new MaxSizeDistance();
    }

    @Test
    public void testGetDistance() {
        ReadsBag bag1 = fromString("A", "T", "C", "G", "T");
        ReadsBag bag2 = fromString("A", "T", "C", "G", "T", "G", "C");
        assertThat(distance.getDistance(bag1, bag2), is(equalTo(7.0)));
        assertThat(distance.getDistance(bag2, bag1), is(equalTo(7.0)));
    }

    @Test
    public void testSymmetric() {
        assertThat(distance.isSymmetric(), is(true));
    }

    @Test
    public void testNormalizedFalse() {
        assertThat(distance.isZeroOneNormalized(), is(false));
    }

}
