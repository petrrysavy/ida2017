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
import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;
import cz.cvut.fel.ida.reads.readsIDA2017.wis.WeightedIntervalScheduling;
import java.util.ArrayList;

/**
 * Class that calculates d(C_A, C_B). (See equation above 6).
 *
 * @author Petr Ryšavý
 */
public class OverlapDistance implements Distance<ContigSet> {

    private final OverlapFinder overlap;
    private final boolean orientationUnknown;
    private final boolean strandUnknown;

    /**
     * Creates new instance of the class that calculates d(C_A, C_B).
     * @param overlap Implementation of Algorithm 1 to find overlap of two sequences.
     * @param orientationUnknown Consider that we do not know whether the
     * contigs go from 5 to 3 end.
     * @param strandUnknown We do not know whether all contigs come from the
     * same strand.
     */
    public OverlapDistance(OverlapFinder overlap, boolean orientationUnknown, boolean strandUnknown) {
        this.overlap = overlap;
        this.orientationUnknown = orientationUnknown;
        this.strandUnknown = strandUnknown;
    }

    @Override
    public double getDistance(ContigSet a, ContigSet b) {
        double sumA = 0.0;
        int matchedLensA = 0;
        OverlapAdapterList regions = new OverlapAdapterList(b.size() * (strandUnknown ? 2 : 1) * (orientationUnknown ? 2 : 1));
        for (Sequence aSeq : a) {
            regions.clear();
            for (Sequence bSeq : b) {
                regions.add(overlap.getOverlap(aSeq, bSeq));

                if (strandUnknown) {
                    regions.add(overlap.getOverlap(aSeq, bSeq.reverseComplement()));
                    if (orientationUnknown)
                        regions.add(overlap.getOverlap(aSeq, bSeq.complement()));
                }
                if (orientationUnknown)
                    regions.add(overlap.getOverlap(aSeq, bSeq.reverse()));
            }
            for (OverlapAdapter region : WeightedIntervalScheduling.solve(regions.toArray(new OverlapAdapter[regions.size()]))) {
                sumA += region.getRegion().getDistance();
                matchedLensA += region.getRegion().getLengthMax();
            }
        }
        return sumA / matchedLensA;
    }

    private class OverlapAdapterList extends ArrayList<OverlapAdapter> {

        public OverlapAdapterList(int initialCapacity) {
            super(initialCapacity);
        }

        public OverlapAdapterList() {
        }

        public void add(OverlapRegion e) {
            if (e != null)
                super.add(new OverlapAdapter(e, 1.0 / e.distanceToLengthRatio()));
        }
    }
}
