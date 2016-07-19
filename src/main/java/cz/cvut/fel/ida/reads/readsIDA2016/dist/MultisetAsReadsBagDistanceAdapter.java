package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.Multiset;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;
import cz.cvut.fel.ida.reads.readsIDA2016.model.Sequence;

/**
 * This is only adapter that adapts distance of multisets of sequences to
 * distance of read bags.
 *
 * @author Petr Ryšavý
 */
public class MultisetAsReadsBagDistanceAdapter implements Distance<ReadsBag> {

    /** The adapted distance. */
    private final Distance<Multiset<Sequence>> me;

    /**
     * Constructs new adapter instance.
     * @param me The adapted distance.
     */
    public MultisetAsReadsBagDistanceAdapter(Distance<Multiset<Sequence>> me) {
        this.me = me;
    }

    /** {@inheritDoc} */
    @Override
    public double getDistance(ReadsBag a, ReadsBag b) {
        return me.getDistance(a, b);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isZeroOneNormalized() {
        return me.isZeroOneNormalized();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSymmetric() {
        return me.isSymmetric();
    }
}
