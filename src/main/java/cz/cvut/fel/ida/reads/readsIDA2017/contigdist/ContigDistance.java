package cz.cvut.fel.ida.reads.readsIDA2017.contigdist;

import cz.cvut.fel.ida.reads.readsIDA2016.dist.Distance;
import cz.cvut.fel.ida.reads.readsIDA2016.model.ContigSet;
import cz.cvut.fel.ida.reads.readsIDA2016.util.MathUtils;

/**
 * This is implementation of the distance suggested in paper Estimating Sequence
 * Similarity from Contig Sets.
 *
 * @author Petr Ryšavý
 */
public class ContigDistance implements Distance<ContigSet> {

    /** This is to calculate distance of two contig sets using overlaps in one
     * direction. */
    private final OverlapDistance overlap;
    private final int readLength;
    private final double coverage;

    public ContigDistance(OverlapDistance overlap, int readLength, double coverage) {
        this.overlap = overlap;
        this.readLength = readLength;
        this.coverage = coverage;
    }

    @Override
    public double getDistance(ContigSet a, ContigSet b) {
        // there is a typo in the paper, sum in the normalizing factor Z should
        // not be in Z but in the first equation above (6) ...
        return MathUtils.average(overlap.getDistance(a, b), overlap.getDistance(b, a))
                * Math.max(a.getImage().size(), b.getImage().size())
                * readLength
                / coverage;
    }

}
