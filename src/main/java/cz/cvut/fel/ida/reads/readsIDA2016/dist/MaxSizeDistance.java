package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;

/**
 * This distance implements the baseline method. Distance of two read bags has
 * an upper bound (up to the scale factor), which is maximum of the sizes of the
 * read bags.
 *
 * @author Petr Ryšavý
 */
public class MaxSizeDistance implements Distance<ReadsBag> {

    /**
     * {@inheritDoc }
     * @return {@code max(|R_A|, |R_B|)}.
     */
    @Override
    public double getDistance(ReadsBag a, ReadsBag b) {
        return new Double(Math.max(a.size(), b.size()));
    }

}
