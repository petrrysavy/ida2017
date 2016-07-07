package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Multiset;

/**
 *
 * @author Petr Ryšavý
 */
public abstract class AbstractMongeElkan<T> implements Distance<Multiset<T>> {

    protected final Distance<T> innerDistance;

    public AbstractMongeElkan(Distance<T> innerDistance) {
        this.innerDistance = innerDistance;
    }

    @Override
    public abstract double getDistance(Multiset<T> a, Multiset<T> b);
    
    @Override
    public boolean isSymmetric() {
        return false;
    }

    @Override
    public boolean isZeroOneNormalized() {
        return innerDistance.isZeroOneNormalized();
    }
}
