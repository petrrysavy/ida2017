package cz.cvut.fel.ida.reads.readsIDA2016.model;

/**
 * A representation of bag of reads. This is efficiently a multiset of sequences
 * and a description.
 *
 * @author Petr Ryšavý
 */
public class ReadsBag extends HashMultiset<Sequence> {

    /** Description of the bag of reads. */
    private final String description;

    /**
     * Constructs a new bag of reads.
     *
     * @param initialCapacity Initial capacity of the multiset.
     * @param description     Description, a comment or {@code null}.
     */
    public ReadsBag(int initialCapacity, String description) {
        super(initialCapacity);
        this.description = description;
    }

    /**
     * Returns the description.
     *
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Finds the longest sequence.
     *
     * @return The longest sequence or {@code null} if the bag is empty.
     */
    public Sequence longestSequence() {
        int maxLenght = -1;
        Sequence maximal = null;
        for (Sequence s : toSet())
            if (s.length() > maxLenght) {
                maximal = s;
                maxLenght = s.length();
            }
        return maximal;
    }

    /**
     * Constructs a bag of reads from string representation of sequences.
     *
     * @param sequences The sequences to be stored in the new reads bag.
     * @return A read bags that contains all the sequences.
     */
    public static ReadsBag fromString(String... sequences) {
        final ReadsBag bag = new ReadsBag(sequences.length, null);
        for (String sequence : sequences)
            bag.add(Sequence.fromString(sequence));
        return bag;
    }

    /** {@inheritDoc}
     *
     * @return Description of the reads bag.
     */
    @Override
    public String toString() {
        return description;
    }

}
