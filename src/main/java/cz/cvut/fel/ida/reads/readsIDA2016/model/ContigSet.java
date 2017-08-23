package cz.cvut.fel.ida.reads.readsIDA2016.model;

/**
 *
 * @author petr
 */
public class ContigSet extends ReadsBag {
    
    private final ReadsBag image;

    /**
     * Instantiates new contig set.
     * @param initialCapacity
     * @param description
     * @param image The original set of reads that was assembled into this
     * contig set.
     */
    public ContigSet(int initialCapacity, String description, ReadsBag image) {
        super(initialCapacity, description);
        this.image = image;
    }

    public ReadsBag getImage() {
        return image;
    }

}
