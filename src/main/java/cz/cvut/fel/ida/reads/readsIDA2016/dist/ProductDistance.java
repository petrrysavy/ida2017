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
 * This distance is calculated as product of two simpler distances. It can be
 * understood as a fuzzy conjunction, namely the product conjunction.
 *
 * @author Petr Ryšavý
 * @param <T> The type of compared objects.
 */
public class ProductDistance<T> implements Distance<T> {

    /** The first distance. */
    private final Distance<T> distance1;
    /** The second distance. */
    private final Distance<T> distance2;

    /**
     * Constructs new product distance.
     *
     * @param distance1 The first distance.
     * @param distance2 The second distance.
     */
    public ProductDistance(Distance<T> distance1, Distance<T> distance2) {
        this.distance1 = distance1;
        this.distance2 = distance2;
    }

    /**
     * {@inheritDoc }
     *
     * @return Product of the two distances.
     */
    @Override
    public double getDistance(T a, T b) {
        return distance1.getDistance(a, b) * distance2.getDistance(a, b);
    }

    /**
     * {@inheritDoc }
     *
     * @return Product is normalized if both of the distances are normalized.
     */
    @Override
    public boolean isZeroOneNormalized() {
        return distance1.isZeroOneNormalized() && distance2.isZeroOneNormalized();
    }

    /**
     * {@inheritDoc }
     *
     * @return Product is symmetric if both of the distances are symmetric.
     */
    @Override
    public boolean isSymmetric() {
        return distance1.isSymmetric() && distance2.isSymmetric();
    }
}
