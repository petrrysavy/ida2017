package cz.cvut.fel.ida.reads.readsIDA2016.model;

import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Petr Ryšavý
 * @param <T>
 */
public abstract class AbstractMultiset<T> extends AbstractCollection<T> implements Multiset<T>
{
    @Override
    public Set<T> toSet()
    {
        return new HashSet<>(this);
    }

}
