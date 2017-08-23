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

import cz.cvut.fel.ida.reads.readsIDA2016.model.Multiset;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;

/**
 * This is only adapter that adapts distance of multisets of sequences to
 * distance of read bags.
 *
 * @author Petr Ryšavý
 */
public class MultisetAsReadsBagDistanceAdapter implements Distance<ReadsBag> {

    /** The adapted distance. */
    private final Distance<Multiset<Sequence>> me;

    /**
     * Constructs new adapter instance.
     * @param me The adapted distance.
     */
    public MultisetAsReadsBagDistanceAdapter(Distance<Multiset<Sequence>> me) {
        this.me = me;
    }

    /** {@inheritDoc} */
    @Override
    public double getDistance(ReadsBag a, ReadsBag b) {
        return me.getDistance(a, b);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isZeroOneNormalized() {
        return me.isZeroOneNormalized();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSymmetric() {
        return me.isSymmetric();
    }
}
