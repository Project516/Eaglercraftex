package com.carrotsearch.hppc;

import com.carrotsearch.hppc.comparators.*;
import com.carrotsearch.hppc.cursors.*;
import com.carrotsearch.hppc.predicates.*;
import com.carrotsearch.hppc.procedures.*;
import com.carrotsearch.hppc.sorting.QuickSort;
import java.util.Iterator;
import java.util.function.IntBinaryOperator;

/**
 * Read-only view with sorted iteration order on a delegate {@link ShortCharHashMap}.
 *
 * <p>In its constructor, this view creates its own iteration order array and sorts it, which is in
 * O(n.log(n)) of the size of the delegate map. Afterward, calls to any method have the same
 * performance as the delegate map.
 *
 * <p>This view is read-only. In addition, the delegate map must not be modified while the view is
 * used, otherwise the iteration is undefined.
 *
 * <p>Since this view provides a fixed iteration order, it must not be used to add entries to
 * another {@link ShortCharHashMap} as this may result in a runtime deadlock. See <a
 * href="https://github.com/carrotsearch/hppc/issues/146">HPPC-103</a> and <a
 * href="https://github.com/carrotsearch/hppc/issues/228">HPPC-186</a> for more information.
 */
@com.carrotsearch.hppc.Generated(
    date = "2024-06-04T15:20:17+0200",
    value = "SortedIterationKTypeVTypeHashMap.java")
public class SortedIterationShortCharHashMap implements ShortCharMap {
  public final ShortCharHashMap delegate;
  public final int[] iterationOrder;

  /**
   * Creates a read-only view with sorted iteration order on the given delegate map. The ordering is
   * based on the provided comparator on the keys.
   */
  public SortedIterationShortCharHashMap(ShortCharHashMap delegate, ShortComparator comparator) {
    this.delegate = delegate;
    this.iterationOrder = sortIterationOrder(createEntryIndexes(), comparator);
  }

  /**
   * Creates a read-only view with sorted iteration order on the given delegate map. The ordering is
   * based on the provided comparator on keys and values.
   */
  public SortedIterationShortCharHashMap(
      ShortCharHashMap delegate, ShortCharComparator comparator) {
    this.delegate = delegate;
    this.iterationOrder = sortIterationOrder(createEntryIndexes(), comparator);
  }

  private int[] createEntryIndexes() {
    final short[] keys = delegate.keys;
    final int size = delegate.size();
    int[] entryIndexes = new int[size];
    int entry = 0;
    if (delegate.hasEmptyKey) {
      entryIndexes[entry++] = delegate.mask + 1;
    }
    for (int keyIndex = 0; entry < size; keyIndex++) {
      if (!((keys[keyIndex]) == 0)) {
        entryIndexes[entry++] = keyIndex;
      }
    }
    return entryIndexes;
  }

  /** Sort the iteration order array based on the provided comparator on the keys. */
  protected int[] sortIterationOrder(int[] entryIndexes, ShortComparator comparator) {
    QuickSort.sort(
        entryIndexes,
        (i, j) -> {
          short[] keys = delegate.keys;
          return comparator.compare(keys[entryIndexes[i]], keys[entryIndexes[j]]);
        });
    return entryIndexes;
  }

  /** Sort the iteration order array based on the provided comparator on keys and values. */
  protected int[] sortIterationOrder(int[] entryIndexes, ShortCharComparator comparator) {
    QuickSort.sort(
        entryIndexes,
        new IntBinaryOperator() {
          final short[] keys = delegate.keys;
          final char[] values = delegate.values;

          @Override
          public int applyAsInt(int i, int j) {
            int index1 = entryIndexes[i];
            int index2 = entryIndexes[j];
            return comparator.compare(keys[index1], values[index1], keys[index2], values[index2]);
          }
        });
    return entryIndexes;
  }

  @Override
  public Iterator<ShortCharCursor> iterator() {
    assert checkUnmodified();
    return new EntryIterator();
  }

  @Override
  public boolean containsKey(short key) {
    return delegate.containsKey(key);
  }

  @Override
  public int size() {
    assert checkUnmodified();
    return delegate.size();
  }

  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public int removeAll(ShortContainer container) {
    throw readOnlyException();
  }

  @Override
  public int removeAll(ShortPredicate predicate) {
    throw readOnlyException();
  }

  @Override
  public int removeAll(ShortCharPredicate predicate) {
    throw readOnlyException();
  }

  @Override
  public <T extends ShortCharProcedure> T forEach(T procedure) {
    assert checkUnmodified();
    final int[] iterationOrder = this.iterationOrder;
    final short[] keys = delegate.keys;
    final char[] values = delegate.values;
    for (int i = 0, size = size(); i < size; i++) {
      int slot = iterationOrder[i];
      procedure.apply(keys[slot], values[slot]);
    }
    return procedure;
  }

  @Override
  public <T extends ShortCharPredicate> T forEach(T predicate) {
    assert checkUnmodified();
    final int[] iterationOrder = this.iterationOrder;
    final short[] keys = delegate.keys;
    final char[] values = delegate.values;
    for (int i = 0, size = size(); i < size; i++) {
      int slot = iterationOrder[i];
      if (!predicate.apply(keys[slot], values[slot])) {
        break;
      }
    }
    return predicate;
  }

  @Override
  public ShortCollection keys() {
    assert checkUnmodified();
    return new KeysContainer();
  }

  @Override
  public CharContainer values() {
    assert checkUnmodified();
    return new ValuesContainer();
  }

  @Override
  public char get(short key) {
    return delegate.get(key);
  }

  @Override
  public char getOrDefault(short key, char defaultValue) {
    return delegate.getOrDefault(key, defaultValue);
  }

  @Override
  public char put(short key, char value) {
    throw readOnlyException();
  }

  @Override
  public int putAll(ShortCharAssociativeContainer container) {
    throw readOnlyException();
  }

  @Override
  public int putAll(Iterable<? extends ShortCharCursor> iterable) {
    throw readOnlyException();
  }

  @Override
  public char putOrAdd(short key, char putValue, char incrementValue) {
    throw readOnlyException();
  }

  @Override
  public char addTo(short key, char additionValue) {
    throw readOnlyException();
  }

  @Override
  public char remove(short key) {
    throw readOnlyException();
  }

  @Override
  public int indexOf(short key) {
    return delegate.indexOf(key);
  }

  @Override
  public boolean indexExists(int index) {
    return delegate.indexExists(index);
  }

  @Override
  public char indexGet(int index) {
    return delegate.indexGet(index);
  }

  @Override
  public char indexReplace(int index, char newValue) {
    throw readOnlyException();
  }

  @Override
  public void indexInsert(int index, short key, char value) {
    throw readOnlyException();
  }

  @Override
  public char indexRemove(int index) {
    throw readOnlyException();
  }

  @Override
  public void clear() {
    throw readOnlyException();
  }

  @Override
  public void release() {
    throw readOnlyException();
  }

  @Override
  public String visualizeKeyDistribution(int characters) {
    return delegate.visualizeKeyDistribution(characters);
  }

  private static RuntimeException readOnlyException() {
    throw new UnsupportedOperationException("Read-only view cannot be modified");
  }

  private boolean checkUnmodified() {
    // Cheap size comparison.
    // We could also check the hashcode, but this is heavy for a frequent check.
    assert delegate.size() == iterationOrder.length
        : "The delegate map changed; this is not supported by this read-only view";
    return true;
  }

  /** An iterator implementation for {@link #iterator}. */
  private final class EntryIterator extends AbstractIterator<ShortCharCursor> {
    private final ShortCharCursor cursor = new ShortCharCursor();
    private int index;

    @Override
    protected ShortCharCursor fetch() {
      if (index < iterationOrder.length) {
        int slot = iterationOrder[index++];
        cursor.index = slot;
        cursor.key = delegate.keys[slot];
        cursor.value = delegate.values[slot];
        return cursor;
      }
      return done();
    }
  }

  /** A view of the keys in sorted order. */
  private final class KeysContainer extends AbstractShortCollection
      implements ShortLookupContainer {
    private final SortedIterationShortCharHashMap owner = SortedIterationShortCharHashMap.this;

    @Override
    public boolean contains(short e) {
      return owner.containsKey(e);
    }

    @Override
    public <T extends ShortProcedure> T forEach(final T procedure) {
      owner.forEach((ShortCharProcedure) (k, v) -> procedure.apply(k));
      return procedure;
    }

    @Override
    public <T extends ShortPredicate> T forEach(final T predicate) {
      owner.forEach((ShortCharPredicate) (key, value) -> predicate.apply(key));
      return predicate;
    }

    @Override
    public boolean isEmpty() {
      return owner.isEmpty();
    }

    @Override
    public Iterator<ShortCursor> iterator() {
      return new KeysIterator();
    }

    @Override
    public int size() {
      return owner.size();
    }

    @Override
    public void clear() {
      throw readOnlyException();
    }

    @Override
    public void release() {
      throw readOnlyException();
    }

    @Override
    public int removeAll(ShortPredicate predicate) {
      throw readOnlyException();
    }

    @Override
    public int removeAll(final short e) {
      throw readOnlyException();
    }
  }

  /** A sorted iterator over the set of assigned keys. */
  private final class KeysIterator extends AbstractIterator<ShortCursor> {
    private final ShortCursor cursor = new ShortCursor();
    private int index;

    @Override
    protected ShortCursor fetch() {
      if (index < iterationOrder.length) {
        int slot = iterationOrder[index++];
        cursor.index = slot;
        cursor.value = delegate.keys[slot];
        return cursor;
      }
      return done();
    }
  }

  /** A view of the values in sorted order. */
  private final class ValuesContainer extends AbstractCharCollection {
    private final SortedIterationShortCharHashMap owner = SortedIterationShortCharHashMap.this;

    @Override
    public int size() {
      return owner.size();
    }

    @Override
    public boolean isEmpty() {
      return owner.isEmpty();
    }

    @Override
    public boolean contains(char value) {
      for (ShortCharCursor c : owner) {
        if (((value) == (c.value))) {
          return true;
        }
      }
      return false;
    }

    @Override
    public <T extends CharProcedure> T forEach(T procedure) {
      owner.forEach((ShortCharProcedure) (k, v) -> procedure.apply(v));
      return procedure;
    }

    @Override
    public <T extends CharPredicate> T forEach(T predicate) {
      owner.forEach((ShortCharPredicate) (k, v) -> predicate.apply(v));
      return predicate;
    }

    @Override
    public Iterator<CharCursor> iterator() {
      return new ValuesIterator();
    }

    @Override
    public int removeAll(final char e) {
      throw readOnlyException();
    }

    @Override
    public int removeAll(final CharPredicate predicate) {
      throw readOnlyException();
    }

    @Override
    public void clear() {
      throw readOnlyException();
    }

    @Override
    public void release() {
      throw readOnlyException();
    }
  }

  /** A sorted iterator over the set of assigned values. */
  private final class ValuesIterator extends AbstractIterator<CharCursor> {
    private final CharCursor cursor = new CharCursor();
    private int index;

    @Override
    protected CharCursor fetch() {
      if (index < iterationOrder.length) {
        int slot = iterationOrder[index++];
        cursor.index = slot;
        cursor.value = delegate.values[slot];
        return cursor;
      }
      return done();
    }
  }
}