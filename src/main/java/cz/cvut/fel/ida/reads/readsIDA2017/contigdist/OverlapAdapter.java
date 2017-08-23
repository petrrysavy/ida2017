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

import cz.cvut.fel.ida.reads.readsIDA2017.wis.Interval;

/**
 * Adapter that converts region with overlap to task in the Weighted Interval
 * Schedulling problem.
 *
 * @author Petr Ryšavý
 */
public class OverlapAdapter implements Interval {

    /** The overlap of the two sequences. */
    protected final OverlapRegion region;
    /** Value of the overlap. See equation (5). */
    protected final double value;

    public OverlapAdapter(OverlapRegion region, double value) {
        this.region = region;
        this.value = value;
    }

    @Override
    public int getEnd() {
        return region.getEndA();
    }

    @Override
    public int getStart() {
        return region.getStartA();
    }

    @Override
    public final double getValue() {
        return value;
    }

    public final OverlapRegion getRegion() {
        return region;
    }

}
