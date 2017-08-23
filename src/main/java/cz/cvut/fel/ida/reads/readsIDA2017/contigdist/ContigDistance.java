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

import cz.cvut.fel.ida.reads.readsIDA2016.dist.Distance;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ContigSet;
import cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils;

/**
 * This is implementation of the distance suggested in paper Estimating Sequence
 * Similarity from Contig Sets.
 *
 * @author Petr Ryšavý
 */
public class ContigDistance implements Distance<ContigSet> {

    /** This is to calculate distance of two contig sets using overlaps in one
     * direction. */
    private final OverlapDistance overlap;
    private final int readLength;
    private final double coverage;

    public ContigDistance(OverlapDistance overlap, int readLength, double coverage) {
        this.overlap = overlap;
        this.readLength = readLength;
        this.coverage = coverage;
    }

    @Override
    public double getDistance(ContigSet a, ContigSet b) {
        // there is a typo in the paper, sum in the normalizing factor Z should
        // not be in Z but in the first equation above (6) ...
        return MathUtils.average(overlap.getDistance(a, b), overlap.getDistance(b, a))
                * Math.max(a.getImage().size(), b.getImage().size())
                * readLength
                / coverage;
    }

}
