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
package cz.cvut.fel.ida.reads.readsIDA2016.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Petr Ryšavý
 */
public class ReadsBagTest {

    private ReadsBag readsBag;

    @Before
    public void bootstrap() {
        this.readsBag = new ReadsBag(10, "A test reads bag.");
        readsBag.add(new Sequence("ATCGCCTTAG".toCharArray(), "A test sequence 1"));
        readsBag.add(new Sequence("ATCGCCTTAG".toCharArray(), "A test sequence 2"));
        readsBag.add(new Sequence("AAA".toCharArray(), "A test sequence 3"));
        readsBag.add(new Sequence("ATCG".toCharArray(), "A test sequence 4"));
        readsBag.add(new Sequence("ATCG".toCharArray(), "A test sequence 5"));
        readsBag.add(new Sequence("GCTATTATATCGCGTA".toCharArray(), "A test sequence 5"));
    }

    @Test
    public void testGetDescription() {
        assertThat(readsBag.getDescription(), is(equalTo("A test reads bag.")));
    }

    @Test
    public void testLongestSequence() {
        assertThat(readsBag.longestSequence(), is(equalTo(Sequence.fromString("GCTATTATATCGCGTA"))));
    }

    @Test
    public void testFromString() {
        assertThat(ReadsBag.fromString("AAA", "ATCG", "ATCGCCTTAG", "GCTATTATATCGCGTA", "ATCGCCTTAG", "ATCG"), is(equalTo(readsBag)));
    }

    @Test
    public void testToString() {
        assertThat(readsBag.toString(), is(equalTo("A test reads bag.")));
    }

}
