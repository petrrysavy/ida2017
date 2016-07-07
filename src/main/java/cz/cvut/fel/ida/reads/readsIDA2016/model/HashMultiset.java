package cz.cvut.fel.ida.reads.readsIDA2016.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Petr Ryšavý
 * @param <T>
 */
public class HashMultiset<T> extends AbstractMultiset<T> implements Cloneable
{
    private HashMap<T, Integer> map;

    private int size = 0;

    public HashMultiset()
    {
        map = new HashMap<>();
    }

    public HashMultiset(Collection<T> initialElements)
    {
        this(initialElements.size());
        addAll(initialElements);
    }

    public HashMultiset(int initialCapacity)
    {
        map = new HashMap<>(initialCapacity);
    }

    public HashMultiset(int initialCapacity, float loadFactor)
    {
        map = new HashMap<>(initialCapacity, loadFactor);
    }

    public HashMultiset(T ... elements) {
        this(elements.length);
        addAll(Arrays.asList(elements));
    }

    @Override
    public Iterator<T> iterator()
    {
        return new MultisetIterator();
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {
        size = 0;
        map.clear();
    }

    @Override
    @SuppressWarnings({"unchecked", "element-type-mismatch"})
    public boolean remove(Object o)
    {
        Integer count = count(o);
        if (count == 0)
            return false;

        size--;
        if(count > 1)
            map.put((T) o, count - 1);
        else
            map.remove(o);
        return true;
    }

    @Override
    public boolean add(T e)
    {
        return add(e, 1);
    }

    @Override
    public boolean add(T e, int count)
    {
        if (count <= 0)
            throw new IllegalArgumentException("Illegal count: " + count);

        Integer currentCount = count(e);
        currentCount += count;
        size += count;
        map.put(e, currentCount);
        return true;
    }

    @Override
    public Set<T> toSet()
    {
        return new HashSet<>(map.keySet());
    }

    @Override
    public HashMultiset<T> union(Multiset<T> other)
    {
        HashMultiset<T> copy = this.clone();
        for (T elem : other.toSet())
            copy.add(elem, other.count(elem));
        return copy;
    }

    @Override
    public int count(Object o)
    {
        @SuppressWarnings("element-type-mismatch")
        Integer count = map.get(o);
        if (count == null)
            return 0;
        return count;
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(Object o)
    {
        return map.containsKey(o);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final HashMultiset<?> other = (HashMultiset<?>) obj;
        // the sizes of the maps may be equal, but this may be still violated
        if(this.size != other.size)
            return false;
        return Objects.equals(this.map, other.map);
    }

    @Override
    public int hashCode()
    {
        return map.hashCode();
    }

    @Override
    @SuppressWarnings("CloneDeclaresCloneNotSupported")
    protected HashMultiset<T> clone()
    {
        try
        {
            @SuppressWarnings("unchecked")
            HashMultiset<T> copy = (HashMultiset<T>) super.clone();
            copy.size = size;
            copy.map = (HashMap<T, Integer>) map.clone();
            return copy;
        }
        catch (CloneNotSupportedException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    private class MultisetIterator implements Iterator<T>
    {
        private final Iterator<Entry<T, Integer>> iterator = map.entrySet().iterator();
        private Entry<T, Integer> current = null;
        private int count = 0;

        @Override
        public boolean hasNext()
        {
            return iterator.hasNext() || (current != null && count < current.getValue());
        }

        @Override
        public T next()
        {
            if (current == null)
                current = iterator.next();

            if (current.getValue() <= count)
            {
                current = iterator.next();
                count = 0;
            }

            count++;
            return current.getKey();
        }
    }
}
