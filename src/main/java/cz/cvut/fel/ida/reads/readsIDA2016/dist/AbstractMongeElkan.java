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

/**
 * Abstract implementation of the classes that modify the Monge-Elkan distance.
 * Provides some shared code.
 *
 * @author Petr Ryšavý
 * @param <T> The type of compared objects.
 */
public abstract class AbstractMongeElkan<T> implements Distance<Multiset<T>> {

    /** The distance measure that is used inside the Monge-Elkan distance. See
     * dist in equation (4). */
    protected final Distance<T> innerDistance;

    /**
     * Constructs abstract Monge-Elkan distance.
     *
     * @param innerDistance The distance measure that is used inside the
     *                      Monge-Elkan distance. See dist in equation (4).
     */
    public AbstractMongeElkan(Distance<T> innerDistance) {
        this.innerDistance = innerDistance;
    }

    /**
     * Compares two multisets. Should be non-negative.
     *
     * @param a First multiset to compare.
     * @param b Second multiset to compare.
     * @return Distance of {@code a} and {@code b}.
     */
    @Override
    public abstract double getDistance(Multiset<T> a, Multiset<T> b);

    /**
     * Monge-Elkan distance is not symmetric in general.
     *
     * @return {@code false}.
     */
    @Override
    public boolean isSymmetric() {
        return false;
    }

    /**
     * Monge-Elkan distance is normalized to [0,1] interval iff the distance
     * used inside is normalized to the same interval.
     *
     * @return {@code true} iff the used distance is normalized.
     */
    @Override
    public boolean isZeroOneNormalized() {
        return innerDistance.isZeroOneNormalized();
    }
}
