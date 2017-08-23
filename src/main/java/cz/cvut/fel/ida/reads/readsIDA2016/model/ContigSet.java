/* 
 * Copyright (C) 2017 Petr Ryšavý <petr.rysavy@fel.cvut.cz>
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
