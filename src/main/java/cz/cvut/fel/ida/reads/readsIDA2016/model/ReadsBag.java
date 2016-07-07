package cz.cvut.fel.ida.reads.readsIDA2016.model;

/**
 *
 * @author Petr Ryšavý
 */
public class ReadsBag extends HashMultiset<Sequence>
{
    private final String description;

    public ReadsBag(int initialCapacity, String description)
    {
        super(initialCapacity);
        this.description = description;
    }

    public ReadsBag(int initialCapacity)
    {
        this(initialCapacity, null);
    }

    public String getDescription()
    {
        return description;
    }

    public Sequence longestSequence()
    {
        int maxLenght = -1;
        Sequence maximal = null;
        for (Sequence s : toSet())
            if (s.length() > maxLenght)
            {
                maximal = s;
                maxLenght = s.length();
            }
        return maximal;
    }

    public static ReadsBag fromString(String... sequences)
    {
        final ReadsBag bag = new ReadsBag(sequences.length);
        for (String sequence : sequences)
            bag.add(Sequence.fromString(sequence));
        return bag;
    }

    @Override
    public String toString() {
        return description;
    }

}
