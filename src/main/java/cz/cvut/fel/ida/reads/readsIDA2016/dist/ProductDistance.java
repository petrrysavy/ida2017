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
