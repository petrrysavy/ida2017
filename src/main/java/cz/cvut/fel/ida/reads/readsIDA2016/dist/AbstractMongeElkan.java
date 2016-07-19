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
