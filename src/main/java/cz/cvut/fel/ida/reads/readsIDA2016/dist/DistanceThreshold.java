package cz.cvut.fel.ida.reads.readsIDA2016.dist;

/**
 * Decorator that thresholds the similarity. If dissimilarity is greater than a
 * threshold, we consider the values to be completely different.
 *
 * @author Petr Ryšavý
 */
public class DistanceThreshold<T> implements Distance<T> {
    private final Distance<T> innerDistance;
    private final double threshold;
    private final double maxCost;

    public DistanceThreshold(Distance<T> innerDistance, double threshold, double maxCost) {
        this.innerDistance = innerDistance;
        this.threshold = threshold;
        this.maxCost = maxCost;
    }

    @Override
    public boolean isZeroOneNormalized() {
        return innerDistance.isZeroOneNormalized();
    }

    @Override
    public double getDistance(T a, T b) {
        final double distance = innerDistance.getDistance(a, b);
        return distance >= threshold ? maxCost : distance;
    }
}
