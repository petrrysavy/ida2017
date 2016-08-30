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
package cz.cvut.fel.ida.reads.readsIDA2016.dist;

/**
 * This interface defines schema how the margin gaps are penalized. See Sect.
 * 2.3 for more details.
 *
 * @author Petr Ryšavý
 */
public interface GraceMargin {

    /**
     * Calculates the cost of the margin gap.
     *
     * @param distanceFromEnd The index of the gap. Starts with 0, which means
     *                        that the maximal possible index is
     *                        {@code wordLength - 1}.
     * @param wordLength      Lenght of the read.
     * @return Cost for margin gap at index {@code distanceFromEnd}.
     */
    public double getMarginGapPenalty(int distanceFromEnd, int wordLength);
}
