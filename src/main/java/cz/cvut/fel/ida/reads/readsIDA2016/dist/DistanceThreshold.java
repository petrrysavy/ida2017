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
 * Decorator that thresholds the distance. If distance is greater than a
 * threshold, we consider the values to be completely different. See Sect. 2.4.
 *
 * @author Petr Ryšavý
 * @param <T> The compared objects type.
 */
public class DistanceThreshold<T> implements Distance<T> {

    /** The decorated distance. */
    private final Distance<T> innerDistance;
    /** Threshold. */
    private final double threshold;
    /** This is returned if the distance is greater than the threshold. */
    private final double maxCost;

    /**
     * Constructs new thresholded distance object.
     *
     * @param innerDistance The decorated distance.
     * @param threshold     The threshold.
     * @param maxCost       This is returned if the distance is greater than the
     *                      threshold.
     */
    public DistanceThreshold(Distance<T> innerDistance, double threshold, double maxCost) {
        this.innerDistance = innerDistance;
        this.threshold = threshold;
        this.maxCost = maxCost;
    }

    /**
     * {@inheritDoc }
     *
     * @return If the decorated distance is normalized, than this is as well.
     */
    @Override
    public boolean isZeroOneNormalized() {
        return innerDistance.isZeroOneNormalized();
    }

    /**
     * {@inheritDoc }
     *
     * @return {@code maxCost} iff the distance is greater than the threshold,
     *         otherwise the calculated distance.
     */
    @Override
    public double getDistance(T a, T b) {
        final double distance = innerDistance.getDistance(a, b);
        return distance >= threshold ? maxCost : distance;
    }
}
