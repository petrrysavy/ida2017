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
import static org.junit.Assert.assertThat;

/**
 * @author Petr Ryšavý
 */
public class EditDistanceTest {
    private EditDistance ed;

    @Before
    public void bootstrap() {
        ed = new EditDistance();
    }

    @Test
    public void testDist1() {
        assertThat(ed.getDistance(new Sequence("kitten".toCharArray(), ""), new Sequence("sitting".toCharArray(), "")), is(equalTo(3.0)));
    }

    @Test
    public void testDist2() {
        assertThat(ed.getDistance(new Sequence("Hello".toCharArray(), ""), new Sequence("Jello".toCharArray(), "")), is(equalTo(1.0)));
    }

    @Test
    public void testDist3() {
        assertThat(ed.getDistance(new Sequence("good".toCharArray(), ""), new Sequence("goodbye".toCharArray(), "")), is(equalTo(3.0)));
    }

    @Test
    public void testDist4() {
        assertThat(ed.getDistance(new Sequence("goodbye".toCharArray(), ""), new Sequence("goodbye".toCharArray(), "")), is(equalTo(0.0)));
    }

    @Test
    public void testDist5() {
        assertThat(ed.getDistance(Sequence.fromString("ATCGCTGCAA"), Sequence.fromString("CTCCTCCA")), is(equalTo(4.0)));
    }

    @Test
    public void testDist6() {
        assertThat(ed.getDistance(Sequence.fromString("TCG"), Sequence.fromString("TCC")), is(equalTo(1.0)));
    }

    @Test
    public void testDist7() {
        assertThat(ed.getDistance(Sequence.fromString("GCA"), Sequence.fromString("CCA")), is(equalTo(1.0)));
    }

    @Test
    public void testDist8() {
        assertThat(ed.getDistance(Sequence.fromString("TCG"), Sequence.fromString("CCA")), is(equalTo(2.0)));
    }

    @Test
    public void testDist9() {
        assertThat(ed.getDistance(Sequence.fromString("GCA"), Sequence.fromString("TCC")), is(equalTo(2.0)));
    }

    @Test
    public void testSymmetric() {
        assertThat(ed.isSymmetric(), is(true));
    }

    @Test
    public void testNormalizedFalse() {
        assertThat(ed.isZeroOneNormalized(), is(false));
    }
}
