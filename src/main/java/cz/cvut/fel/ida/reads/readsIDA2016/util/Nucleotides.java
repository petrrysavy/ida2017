package cz.cvut.fel.ida.reads.readsIDA2016.util;

import java.util.Arrays;

/**
 * Generic utility class.
 *
 * @author Petr Ryšavý
 */
public final class Nucleotides {

    /** Do not let anybody to instantiate the class. */
    private Nucleotides() {
    }

    /**
     * For a DNA sequence returns its complement.
     * @param sequence The sequence, for example {@code ATCCG}.
     * @return The complement, for example {@code TAGGC}.
     */
    public static char[] complementaryCopy(char[] sequence) {
        final char[] copy = Arrays.copyOf(sequence, sequence.length);
        makeComplementary(copy);
        return copy;
    }

    /**
     * Changes a DNA sequence into its complement.
     * @param sequence The sequence, for example.
     */
    public static void makeComplementary(char[] sequence) {
        for (int i = 0; i < sequence.length; i++)
            sequence[i] = complement(sequence[i]);
    }

    /**
     * Returns a complement of the nucleotide. The pairs are A/T and C/G.
     * @param ch A nucleotide.
     * @return Its complementary nucleotide.
     */
    public static char complement(char ch) {
        switch (ch) {
            case 'A':
                return 'T';
            case 'T':
                return 'A';
            case 'C':
                return 'G';
            case 'G':
                return 'C';
            default:
                throw new IllegalArgumentException("Unknown nucleotide : " + ch);
        }
    }
}
