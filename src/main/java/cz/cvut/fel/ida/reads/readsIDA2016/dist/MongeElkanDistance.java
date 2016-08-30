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

import cz.cvut.fel.ida.reads.readsIDA2016.model.Multiset;
import java.util.Set;

/**
 * This class implements simple Monge-Elkan distance. For each object from the
 * first multiset it finds the least distant in the second multiset and then
 * finds an average over those pairs. For more details see (4) in the paper or
 * publication "An efficient domain-independent algorithm for detecting
 * approximately duplicate database records" by Alvaro E. Monge and Charles P.
 * Elkan.
 *
 * @author Petr Ryšavý
 * @param <T> Type of objects in compared multisets.
 */
public class MongeElkanDistance<T> extends AbstractMongeElkan<T> {

    /** Creates new instance of this class.
     * 
     * @param innerDistance The distance measure that is used inside the
     *                      Monge-Elkan distance. See dist in equation (4).
     */
    public MongeElkanDistance(Distance<T> innerDistance) {
        super(innerDistance);
    }

    /**
     * {@inheritDoc }
     * @return Monge-Elkan distance as defined by (4).
     */
    @Override
    public double getDistance(Multiset<T> a, Multiset<T> b) {
        final Set<T> bSet = b.toSet();
        double distance = 0.0;
        for (T aElem : a.toSet()) {
            double bestMatch = Double.POSITIVE_INFINITY;
            for (T bElem : bSet)
                bestMatch = Math.min(innerDistance.getDistance(aElem, bElem), bestMatch);
            distance += bestMatch * a.count(aElem);
        }
        return distance / a.size();
    }
}
