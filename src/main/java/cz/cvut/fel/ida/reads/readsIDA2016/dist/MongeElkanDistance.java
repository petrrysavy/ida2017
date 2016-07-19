package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Multiset;
import java.util.Set;

/**
 * This class implements simple Monge-Elkan distance. For each object from the
 * first multiset it finds the least distant in the second multiset and then
 * finds an average over those pairs. For more details see (4) in the paper or
 * publication "An efficient domain-independent algorithm for detecting
 * approximately duplicate database records" by Alvaro E. Monge and Charles P.
 * Elkan.
 *
 * @author Petr Ryšavý
 * @param <T> Type of objects in compared multisets.
 */
public class MongeElkanDistance<T> extends AbstractMongeElkan<T> {

    /** Creates new instance of this class.
     * 
     * @param innerDistance The distance measure that is used inside the
     *                      Monge-Elkan distance. See dist in equation (4).
     */
    public MongeElkanDistance(Distance<T> innerDistance) {
        super(innerDistance);
    }

    /**
     * {@inheritDoc }
     * @return Monge-Elkan distance as defined by (4).
     */
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
}
