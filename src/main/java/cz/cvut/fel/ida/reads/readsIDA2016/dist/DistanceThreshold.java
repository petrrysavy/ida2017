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
