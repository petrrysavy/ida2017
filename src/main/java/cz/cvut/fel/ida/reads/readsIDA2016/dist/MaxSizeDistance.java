package cz.cvut.fel.ida.reads.readsIDA2016.dist;

import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;

/**
 *
 * @author Petr Ryšavý
 */
public class MaxSizeDistance implements Distance<ReadsBag> {

    @Override
    public double getDistance(ReadsBag a, ReadsBag b) {
        return new Double(Math.max(a.size(), b.size()));
    }
    
}
