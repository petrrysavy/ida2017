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

/**
 * This method implements linear border gap penaulty. The gap penaulty is zero
 * for any x from interval [0, t), then linearly increases on interval [t, l -
 * t) and is maximal on interval [l-t, l). Index of this function represents
 * cost for MARGIN gap at the particular position. See Sect. 2.3 for more
 * details.
 *
 * @author Petr Ryšavý
 */
public class LinearMarginPenalty implements GraceMargin {

    /** t, number of gaps that are not penalized. */
    private final double freeGaps;
    /** The maximal penalty that is returned by this function. It should be
     * twice the gap penalty used in the original distance function. */
    private final double maximalGapPenaulty;

    public LinearMarginPenalty(double freeGaps, double maximalGapPenalty) {
        this.freeGaps = freeGaps;
        this.maximalGapPenaulty = maximalGapPenalty;
    }

    /** {@inheritDoc }
     *
     * @returns Function g(x) defined in (8) in the paper.
     */
    @Override
    public double getMarginGapPenalty(int gapIndex, int readLength) {
        if (gapIndex < 0 || gapIndex >= readLength)
            throw new IndexOutOfBoundsException("It is impossible to index gap : " + gapIndex + " on read of length " + readLength);
        if (gapIndex <= freeGaps - 1)
            return 0;
        else if (gapIndex >= readLength - freeGaps)
            return maximalGapPenaulty;
        else
            return maximalGapPenaulty * (gapIndex - freeGaps + 1) / (readLength + 1 - 2 * freeGaps);
    }
}
