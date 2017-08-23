package cz.cvut.fel.ida.reads.readsIDA2017.contigdist;

import cz.cvut.fel.ida.reads.readsIDA2017.wis.Interval;

/**
 * Adapter that converts region with overlap to task in the Weighted Interval
 * Schedulling problem.
 *
 * @author Petr Ryšavý
 */
public class OverlapAdapter implements Interval {

    /** The overlap of the two sequences. */
    protected final OverlapRegion region;
    /** Value of the overlap. See equation (5). */
    protected final double value;

    public OverlapAdapter(OverlapRegion region, double value) {
        this.region = region;
        this.value = value;
    }

    @Override
    public int getEnd() {
        return region.getEndA();
    }

    @Override
    public int getStart() {
        return region.getStartA();
    }

    @Override
    public final double getValue() {
        return value;
    }

    public final OverlapRegion getRegion() {
        return region;
    }

}
