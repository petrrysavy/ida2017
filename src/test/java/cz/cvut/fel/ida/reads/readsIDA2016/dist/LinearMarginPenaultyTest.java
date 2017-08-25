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
