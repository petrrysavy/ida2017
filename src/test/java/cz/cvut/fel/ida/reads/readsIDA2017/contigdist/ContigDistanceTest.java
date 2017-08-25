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
package cz.cvut.fel.ida.reads.readsIDA2017.contigdist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.ContigSet;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for distance published in paper "Estimating Sequence Similarity from
 * Contig Sets".
 *
 * @author Petr Ryšavý <petr.rysavy@fel.cvut.cz>
 */
public class ContigDistanceTest {

    private ContigDistance dist;
    private ContigSet setA;
    private ContigSet setB;

    @Before
    public void bootstrap() {
        dist = new ContigDistance(new OverlapDistance(new OverlapFinder(4),
                false, false), 5, 2);
        setA = new ContigSet(4, "Contig set A", new ReadsBag(0, "ReadsBag A") {
            @Override
            public int size() {
                return 12;
            }
        });
        setB = new ContigSet(4, "Contig set B", new ReadsBag(0, "ReadsBag B") {
            @Override
            public int size() {
                return 12;
            }
        });

        setA.add(Sequence.fromString("CTAGCTAG"));
        setA.add(Sequence.fromString("AAGACCTGACTGCTAAAGGGGGAT"));

        setB.add(Sequence.fromString("GCAAGTTAAGA"));
        setB.add(Sequence.fromString("GAATGGTAAA"));
        setB.add(Sequence.fromString("GGGATA"));
    }

    @Test
    public void testDistAB() {
        assertThat(dist.getDistance(setA, setB), is(3.75));
    }

    @Test
    public void testDistBA() {
        assertThat(dist.getDistance(setB, setA), is(3.75));
    }

    @Test
    public void testSymmetric() {
        assertThat(dist.isSymmetric(), is(true));
    }

    @Test
    public void testNormalized() {
        assertThat(dist.isZeroOneNormalized(), is(false));
    }

}
