/*
 * Copyright (c) 2016 Petr Rysavy <rysavpe1@fel.cvut.cz>
 *
 * This file is part of the project readsIDA2016, which is available on 
 * <https://github.com/petrrysavy/readsIDA2016/>.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with This program.  If not, see <http ://www.gnu.org/licenses/>.
 */
package cz.cvut.fel.ida.reads.readsIDA2016.model;

import cz.cvut.fel.ida.reads.readsIDA2016.util.ArrayUtils;
import cz.cvut.fel.ida.reads.readsIDA2016.util.Nucleotides;
import java.util.Arrays;

/**
 * A simple representation of the sequence.
 *
 * @author Petr Ryšavý
 */
public class Sequence implements CharSequence {

    /** The sequence to be stored. */
    private final char[] sequence;
    /** Description. */
    private final String description;

    /**
     * Creates new sequence.
     *
     * @param sequence The sequence to be stored.
     * @param description Description of the sequence. Can be {@code null}.
     */
    public Sequence(char[] sequence, String description) {
        this.sequence = sequence;
        this.description = description;
    }

    /**
     * Returns the character array representation of this sequence.
     *
     * @return Array that contains this sequence. Note that possible changes
     * will propagate to this class.
     */
    public char[] getSequence() {
        return sequence;
    }

    /**
     * Returns description of this sequence.
     *
     * @return Textual description.
     */
    public String getDescription() {
        return description;
    }

    /** {@inheritDoc} This method returns number of symbols in this sequence. */
    @Override
    public int length() {
        return sequence.length;
    }

    /** {@inheritDoc} */
    @Override
    public char charAt(int index) {
        if (index < 0 || index > sequence.length)
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        return sequence[index];
    }

    /** {@inheritDoc} */
    @Override
    public Sequence subSequence(int start, int end) {
        if (start < 0 || start > end || end > sequence.length)
            throw new IndexOutOfBoundsException("Illegal subsequence indices : start=" + start + ", end=" + end);

        char[] arr = new char[end - start];
        System.arraycopy(sequence, start, arr, 0, arr.length);
        return new Sequence(arr, description + " [subsequence " + start + '-' + end + ']');
    }

    /**
     * Constructs a sequence from string representation.
     *
     * @param string String that will form the newly created sequence. It will
     * be used as description too.
     * @return Sequence from the given string.
     */
    public static Sequence fromString(String string) {
        return new Sequence(string.toCharArray(), string);
    }

    /**
     * Gets the reverse sequence.
     * @return Sequence with nucleotides from the last symbol to the first one.
     */
    public Sequence reverse() {
        return new Sequence(ArrayUtils.reversedCopy(sequence), description + ", reversed");
    }

    /**
     * Gets the complementary sequence.
     * @return Sequence with nucleotides replaced by their complementary
     * nucleotides.
     */
    public Sequence complement() {
        return new Sequence(Nucleotides.complementaryCopy(sequence), description + ", complement");
    }

    /**
     * Gets the complementary reversed sequence. This is how would looks
     * sequence look like if it was read from the complementary strand.
     * @return Sequence with nucleotides replaced by their complementary
     * nucleotides and in reversed order.
     */
    public Sequence reverseComplement() {
        return this.reverse().complement();
    }

    /** {@inheritDoc}
     *
     * @return Description or sequence itself, whatever is shorter. If the
     * description is {@code null}, the method returns the sequence.
     */
    @Override
    public String toString() {
        if (description != null && sequence.length >= description.length())
            return description;
        else
            return new String(sequence);
    }

    /** {@inheritDoc} Hash code depends only on the sequence. Description does
     * not matter. */
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.sequence);
    }

    /** {@inheritDoc} Two sequences are equal if the contain the same symbols.
     * Descriptions do not matter. */
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
