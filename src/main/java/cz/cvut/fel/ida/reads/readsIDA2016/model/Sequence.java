package cz.cvut.fel.ida.reads.readsIDA2016.model;

import java.util.Arrays;

/**
 *
 * @author Petr Ryšavý
 */
public class Sequence implements CharSequence {

    private final char[] sequence;
    private final String description;

    public Sequence(char[] sequence, String description) {
        this.sequence = sequence;
        this.description = description;
    }

    public char[] getSequence() {
        return sequence;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int length() {
        return sequence.length;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index > sequence.length)
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        return sequence[index];
    }

    @Override
    public Sequence subSequence(int start, int end) {
        if (start < 0 || start > end || end > sequence.length)
            throw new IndexOutOfBoundsException("Illegal subsequence indices : start=" + start + ", end=" + end);

        char[] arr = new char[end - start];
        System.arraycopy(sequence, start, arr, 0, arr.length);
        return new Sequence(arr, description + " [subsequence " + start + '-' + end + ']');
    }

    public static Sequence fromString(String string) {
        return new Sequence(string.toCharArray(), string);
    }

    @Override
    public String toString() {
        if (sequence.length >= description.length())
            return description;
        else
            return new String(sequence);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.sequence);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Sequence other = (Sequence) obj;
        return Arrays.equals(this.sequence, other.sequence);
    }
}
