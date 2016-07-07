package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Multiset;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;

/**
 *
 * @author Petr Ryšavý
 */
public class MultisetAsReadsBagDistanceAdapter implements Distance<ReadsBag> {

    private final Distance<Multiset<Sequence>> me;

    public MultisetAsReadsBagDistanceAdapter(Distance<Multiset<Sequence>> me) {
        this.me = me;
    }

    @Override
    public double getDistance(ReadsBag a, ReadsBag b) {
        return me.getDistance(a, b);
    }

    @Override
    public boolean isZeroOneNormalized() {
        return me.isZeroOneNormalized();
    }

    @Override
    public boolean isSymmetric() {
        return me.isSymmetric();
    }
}
