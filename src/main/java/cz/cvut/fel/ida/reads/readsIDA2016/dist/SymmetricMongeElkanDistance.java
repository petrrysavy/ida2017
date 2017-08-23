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
import cz.cvut.fel.ida.reads.readsIDA2016.util.ArrayUtils;
import cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a symmetric version of Monge-Elkan distance. This is
 * obtained from Monge-Elkan distance by averaging the two directions, i.e. it
 * is equal to 1/2(dist(RA, RB) + dist(RB, RA)). See equation (5) in the paper.
 *
 * This class does not implement equation (5) directly, but avoids duplicate
 * distance calculations at cost of linear memory requirement in size of both
 * multisets.
 *
 * @author Petr Ryšavý
 * @param <T> Type of objects in compared multisets.
 * @see MongeElkanDistance
 */
public class SymmetricMongeElkanDistance<T> extends AbstractMongeElkan<T> {

    /** Creates new instance of this class.
     *
     * @param innerDistance The distance measure that is used inside the
     *                      Monge-Elkan distance. See dist in equation (4).
     */
    public SymmetricMongeElkanDistance(Distance<T> innerDistance) {
        super(innerDistance);
    }

    /**
     * {@inheritDoc }
     *
     * @return Symmetric version of Monge-Elkan distance as defined by (5).
     */
    @Override
    public double getDistance(Multiset<T> a, Multiset<T> b) {
        final List<T> aList = new ArrayList<>(a.toSet());
        final List<T> bList = new ArrayList<>(b.toSet());

        // instead of distance matrix, we will remember only minimum in each row & col
        final double[] rowMin = ArrayUtils.nTimes(Double.MAX_VALUE, aList.size());
        final double[] colMin = ArrayUtils.nTimes(Double.MAX_VALUE, bList.size());

        // iterate over all pairs of objects, compare them and remember if minimal
        for (int i = 0; i < rowMin.length; i++)
            for (int j = 0; j < colMin.length; j++) {
                final double dist = innerDistance.getDistance(aList.get(i), bList.get(j));
                rowMin[i] = Math.min(rowMin[i], dist);
                colMin[j] = Math.min(colMin[j], dist);
            }

        // now calculate the distance from multiset a to multiset b
        double distanceA = 0.0;
        for (int i = 0; i < rowMin.length; i++)
            distanceA += rowMin[i] * a.count(aList.get(i));
        // and the other way from b to a
        double distanceB = 0.0;
        for (int j = 0; j < colMin.length; j++)
            distanceB += colMin[j] * b.count(bList.get(j));

        // and use formula (5)
        return MathUtils.average(distanceA / a.size(), distanceB / b.size());
    }

    /**
     * This version of Monge-Elkan distance is symmetric.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isSymmetric() {
        return true;
    }
}
