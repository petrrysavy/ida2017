package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Multiset;
import java.util.Set;

/**
 *
 * @author Petr Ryšavý
 * @param <T>
 */
public class MongeElkanDistance<T> extends AbstractMongeElkan<T> {

    public MongeElkanDistance(Distance<T> innerDistance) {
        super(innerDistance);
    }

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

    @Override
    public boolean isZeroOneNormalized() {
        return innerDistance.isZeroOneNormalized();
    }

    @Override
    public boolean isSymmetric() {
        return false;
    }
}
