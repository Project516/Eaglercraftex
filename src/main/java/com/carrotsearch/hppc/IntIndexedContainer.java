package com.carrotsearch.hppc;

import java.util.RandomAccess;
import java.util.stream.IntStream;

/**
 * An indexed container provides random access to elements based on an <code>index</code>. Indexes
 * are zero-based.
 */
@com.carrotsearch.hppc.Generated(
    date = "2024-06-04T15:20:17+0200",
    value = "KTypeIndexedContainer.java")
public interface IntIndexedContainer extends IntCollection, RandomAccess {
  /**
   * Removes the first element that equals <code>e1</code>, returning whether an element has been
   * removed.
   */
  public boolean removeElement(int e1);

  /**
   * Removes the first element that equals <code>e1</code>, returning its deleted position or <code>
   * -1</code> if the element was not found.
   */
  public int removeFirst(int e1);

  /**
   * Removes the last element that equals <code>e1</code>, returning its deleted position or <code>
   * -1</code> if the element was not found.
   */
  public int removeLast(int e1);

  /**
   * Returns the index of the first occurrence of the specified element in this list, or -1 if this
   * list does not contain the element.
   */
  public int indexOf(int e1);

  /**
   * Returns the index of the last occurrence of the specified element in this list, or -1 if this
   * list does not contain the element.
   */
  public int lastIndexOf(int e1);

  /** Adds an element to the end of this container (the last index is incremented by one). */
  public void add(int e1);

  /**
   * Inserts the specified element at the specified position in this list.
   *
   * @param index The index at which the element should be inserted, shifting any existing and
   *     subsequent elements to the right.
   */
  public void insert(int index, int e1);

  /**
   * Replaces the element at the specified position in this list with the specified element.
   *
   * @return Returns the previous value in the list.
   */
  public int set(int index, int e1);

  /**
   * @return Returns the element at index <code>index</code> from the list.
   */
  public int get(int index);

  /**
   * Removes the element at the specified position in this container and returns it.
   *
   * @see #removeFirst
   * @see #removeLast
   * @see #removeAll
   */
  public int removeAt(int index);

  /** Removes and returns the last element of this container. This container must not be empty. */
  public int removeLast();

  /**
   * Removes from this container all of the elements with indexes between <code>fromIndex</code>,
   * inclusive, and <code>toIndex</code>, exclusive.
   */
  public void removeRange(int fromIndex, int toIndex);

  /** Returns this container elements as a stream. */
  public IntStream stream();

  /** Sorts the elements in this container and returns this container. */
  public IntIndexedContainer sort();

  /** Reverses the elements in this container and returns this container. */
  public IntIndexedContainer reverse();
}