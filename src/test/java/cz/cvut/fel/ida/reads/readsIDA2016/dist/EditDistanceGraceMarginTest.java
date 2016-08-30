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
package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * @author Petr Ryšavý
 */
public class EditDistanceGraceMarginTest {

    private EditDistanceGraceMargin editDistance;

    @Before
    public void init() {
        editDistance = new EditDistanceGraceMargin(0, 1, 1, (int distanceFromEnd, int wordLength) -> {
            switch (distanceFromEnd) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                default:
                    throw new RuntimeException();
            }
        });
    }

    @Test
    public void testGetDistance1() {
        assertThat(editDistance.getDistance(Sequence.fromString("ABC"), Sequence.fromString("CDA")), is(equalTo(2.0)));
    }

    @Test
    public void testGetDistance5() {
        editDistance = new EditDistanceGraceMargin(0, 1, 1, new LinearMarginPenalty(2, 2));
        assertThat(editDistance.getDistance(Sequence.fromString("ABCDEF"), Sequence.fromString("DEFGHI")), is(closeTo(4.0 / 3.0, 1e-10)));
    }

    @Test
    public void testGetDistance6() {
        editDistance = new EditDistanceGraceMargin(0, 1, 1, new LinearMarginPenalty(2, 2));
        assertThat(editDistance.getDistance(Sequence.fromString("ABCDEF"), Sequence.fromString("EFGHIJ")), is(closeTo(4.0, 1e-10)));
    }

    @Test
    public void testGetDistance2() {
        assertThat(editDistance.getDistance(Sequence.fromString("ABC"), Sequence.fromString("BCD")), is(equalTo(0.0)));
    }

    @Test
    public void testGetDistance3() {
        assertThat(editDistance.getDistance(Sequence.fromString("BCD"), Sequence.fromString("CDA")), is(equalTo(0.0)));
    }

    @Test
    public void testGetDistance4() {
        assertThat(editDistance.getDistance(Sequence.fromString("ABC"), Sequence.fromString("ABC")), is(equalTo(0.0)));
    }

    @Test
    public void testFigure2() {
        editDistance = new EditDistanceGraceMargin(0, 1, 1, (int distanceFromEnd, int wordLength) -> {
            switch (distanceFromEnd) {
                case 0:
                case 1:
                    return 0;
                case 2:
                    return 1;
                case 3:
                    return 2;
                case 4:
                    return 3;
                default:
                    throw new RuntimeException();
            }
        });
        assertThat(editDistance.getDistance(Sequence.fromString("TCGC"), Sequence.fromString("CGCT")), is(equalTo(0.0)));
    }

    @Test
    public void testSymmetric() {
        assertThat(editDistance.isSymmetric(), is(true));
    }

    @Test
    public void testNormalizedFalse() {
        assertThat(editDistance.isZeroOneNormalized(), is(false));
    }
}
