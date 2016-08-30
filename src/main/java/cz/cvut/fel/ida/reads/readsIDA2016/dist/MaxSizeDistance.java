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

import cz.cvut.fel.ida.reads.readsIDA2016.model.ReadsBag;

/**
 * This distance implements the baseline method. Distance of two read bags has
 * an upper bound (up to the scale factor), which is maximum of the sizes of the
 * read bags.
 *
 * @author Petr Ryšavý
 */
public class MaxSizeDistance implements Distance<ReadsBag> {

    /**
     * {@inheritDoc }
     * @return {@code max(|R_A|, |R_B|)}.
     */
    @Override
    public double getDistance(ReadsBag a, ReadsBag b) {
        return new Double(Math.max(a.size(), b.size()));
    }

}
