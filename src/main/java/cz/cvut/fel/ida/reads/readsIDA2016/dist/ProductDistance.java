package cz.cvut.fel.ida.reads.readsIDA2016.dist;

/**
 *
 * @author Petr Ryšavý
 * @param <T>
 */
public class ProductDistance<T> implements Distance<T> {

    private final Distance<T> distance1;
    private final Distance<T> distance2;

    public ProductDistance(Distance<T> distance1, Distance<T> distance2) {
        this.distance1 = distance1;
        this.distance2 = distance2;
    }

    @Override
    public double getDistance(T a, T b) {
        return distance1.getDistance(a, b) * distance2.getDistance(a, b);
    }

    @Override
    public boolean isZeroOneNormalized() {
        return distance1.isZeroOneNormalized() && distance2.isZeroOneNormalized();
    }

    @Override
    public boolean isSymmetric() {
        return distance1.isSymmetric() && distance2.isSymmetric();
    }
}
