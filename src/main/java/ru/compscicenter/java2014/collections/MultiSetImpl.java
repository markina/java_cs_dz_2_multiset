package ru.compscicenter.java2014.collections;

import java.util.*;

/**
 * Created by Markina Margarita on 20.10.14.
 */

public class MultiSetImpl<E> implements MultiSet<E> {
  private Map<E, Integer> map;
  private int size;

  public MultiSetImpl() {
    map = new HashMap<>();
    size = 0;
  }

  public MultiSetImpl(Collection<? extends E> c) {
    this();
    addAll(c);
  }


  /**
   * Returns the number of elements in this multiset, including all duplicates
   *
   * @return the number of elements in this multiset, including all duplicates
   */
  @Override
  public int size() {
    return 0;
  }

  /**
   * Returns <tt>true</tt> if this collection contains no elements.
   *
   * @return <tt>true</tt> if this collection contains no elements
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * Returns <tt>true</tt> if this collection contains the specified element.
   * More formally, returns <tt>true</tt> if and only if this collection
   * contains at least one element <tt>e</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
   *
   * @param o element whose presence in this collection is to be tested
   * @return <tt>true</tt> if this collection contains the specified
   * element
   * @throws ClassCastException   if the type of the specified element
   *                              is incompatible with this collection
   *                              (<a href="#optional-restrictions">optional</a>)
   * @throws NullPointerException if the specified element is null and this
   *                              collection does not permit null elements
   *                              (<a href="#optional-restrictions">optional</a>)
   */
  @Override
  public boolean contains(Object o) {
    return false;
  }

  /**
   * Returns an iterator over the elements in this multiset.
   * Elements that occur multiple times in the multiset will be returned multiple times
   * by this iterator, but the order is not defined.
   *
   * @return an <tt>Iterator</tt> over the elements in this multiset
   */
  @Override
  public Iterator<E> iterator() {
    return null;
  }

  /**
   * Adds a single occurrence of the specified element to this multiset.
   * <p>
   * Always returns <code>true</code>, because multiset always allows adding
   * both new elements and new occurrences of known elements.
   *
   * @param e element to add
   * @return <code>true</code>
   */
  @Override
  public boolean add(E e) {
    add(e, 1);
    return true;
  }

  /**
   * Adds multiple occurrences of the specified element to this multiset.
   *
   * @param e           element to add
   * @param occurrences number of element occurrences to add; can't be negative
   * @return the count of element occurrences before the operation; possibly zero
   * @throws IllegalArgumentException if <code>occurrences</code> is negative
   */
  @Override
  public int add(E e, int occurrences) {
    if (occurrences < 0) {
      throw new IllegalArgumentException();
    }
    if(e == null) {
      throw new NullPointerException();
    }
    int occurrencesBeforeTheOperation = 0;
    size += occurrences;
    if (map.containsKey(e)) {
      occurrencesBeforeTheOperation = map.get(e);
      map.put(e, occurrencesBeforeTheOperation + occurrences);
    } else {
      map.put(e, occurrences);
    }
    return occurrencesBeforeTheOperation;
  }

  /**
   * Removes a single occurrence of the specified element from this multiset, if present.
   *
   * @param e element to remove
   * @return <code>true</code> if the element was found and removed
   */
  @Override
  public boolean remove(Object e) {
    if (map.containsKey(e)) {
      size--;
      if (map.get(e) == 0) {
        map.remove(e);
      } else {
        map.put((E) e, map.get(e) - 1);
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes multiple occurrences of the specified element from this multiset, if present.
   * If multiset contains fewer copies of the element than given by <code>occurrences</code>
   * parameter, all occurrences are removed.
   *
   * @param e           element to remove
   * @param occurrences number of element occurrences to remove; can't be negative
   * @return the count of element occurrences before the operation; possibly zero
   * @throws IllegalArgumentException if <code>occurrences</code> is negative
   */
  @Override
  public int remove(Object e, int occurrences) {
    int occurrencesBefireTheOperation = 0;
    if (occurrences < 0) {
      throw new IllegalArgumentException();
    }
    if (map.containsKey(e)) {
      occurrencesBefireTheOperation = map.get(e);
      if (occurrencesBefireTheOperation <= occurrences) {
        size -= occurrences;
        map.remove(e);
      } else {
        size -= occurrences;
        map.put((E) e, occurrencesBefireTheOperation - occurrences);
      }
    }
    return occurrencesBefireTheOperation;
  }

  /**
   * Returns the number of occurrences of an element in this multiset,
   * or <code>0</code> if multiset does not contain this element.
   *
   * @param e the element to whose occurrences are to be returned
   * @return the number of occurrences of an element in this multiset
   */
  @Override
  public int count(Object e) {
    if (map.containsKey(e)) {
      return map.get(e);
    } else {
      return 0;
    }

  }

  /**
   * Returns <code>true</code> if this multiset contains at least one occurrence of each element
   * in the specified collection.
   * <p>
   * This method ignores the occurrence count of an element in the two collections; it may still
   * return <code>true</code> even if other collections contains several occurrences of an element
   * and this multiset contains only one.
   *
   * @param c the collection of elements to be looked up in this multiset
   * @return <code>true</code> if this multiset contains at least one occurrence of each element in <code>c</code>
   */
  @Override
  public boolean containsAll(Collection<?> c) {
    for (Object el : c) {
      if(!map.containsKey(el)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Adds all of the elements in the specified collection to this collection
   * (optional operation).  The behavior of this operation is undefined if
   * the specified collection is modified while the operation is in progress.
   * (This implies that the behavior of this call is undefined if the
   * specified collection is this collection, and this collection is
   * nonempty.)
   *
   * @param c collection containing elements to be added to this collection
   * @return <tt>true</tt> if this collection changed as a result of the call
   * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
   *                                       is not supported by this collection
   * @throws ClassCastException            if the class of an element of the specified
   *                                       collection prevents it from being added to this collection
   * @throws NullPointerException          if the specified collection contains a
   *                                       null element and this collection does not permit null elements,
   *                                       or if the specified collection is null
   * @throws IllegalArgumentException      if some property of an element of the
   *                                       specified collection prevents it from being added to this
   *                                       collection
   * @throws IllegalStateException         if not all the elements can be added at
   *                                       this time due to insertion restrictions
   * @see #add(Object)
   */
  @Override
  public boolean addAll(Collection<? extends E> c) {
    if(c == this) {
      throw new ClassCastException();
    }
    if(c == null) {
      throw new NullPointerException();
    }
    int addSize = c.size();
    for(E object: c) {

      add(object);
    }
    size += addSize;
    return addSize != 0;
  }

  /**
   * Returns an array containing all of the elements in this multiset including all duplicates.
   *
   * @return an array containing all of the elements in this collection
   */
  @Override
  public Object[] toArray() {
    Object[] array = new Object[size];
    int y = 0;
    for(E el: map.keySet()) {
      int cnt = map.get(el);
      for(int i = 0; i < cnt; i++) {
        array[y] = el;
        y++;
      }
    }
    return array;
  }

  /**
   * Returns an array containing all of the elements in this multiset including all duplicates.
   * The runtime type of the returned array is that of the specified array.
   * If the collection fits in the specified array, it is returned therein.
   * Otherwise, a new array is allocated with the runtime type of the
   * specified array and the size of this collection.
   * <p>
   * <p>If this collection fits in the specified array with room to spare
   * (i.e., the array has more elements than this collection), the element
   * in the array immediately following the end of the collection is set to
   * <tt>null</tt>.  (This is useful in determining the length of this
   * collection <i>only</i> if the caller knows that this collection does
   * not contain any <tt>null</tt> elements.)
   *
   * @param a the array into which the elements of this collection are to be
   *          stored, if it is big enough; otherwise, a new array of the same
   *          runtime type is allocated for this purpose.
   * @return an array containing all of the elements in this collection
   * @throws ArrayStoreException  if the runtime type of the specified array
   *                              is not a supertype of the runtime type of every element in
   *                              this collection
   * @throws NullPointerException if the specified array is null
   */
  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  /**
   * For each element in given collection removes <em>all</em> occurrences
   * of the element from this multiset, if present.
   *
   * @param c collection with elements to remove from this multiset
   * @return <code>true</code> if at least one element was found and removed
   */
  @Override
  public boolean removeAll(Collection<?> c) {
    Set<E> set = new HashSet<>(map.keySet());
    for(E e: set) {
      if(c.contains(e)) {
        size -= map.get(e);
        map.remove(e);
      }
    }
    return true;
  }

  /**
   * For each element in given collection retains <em>all</em> occurrences
   * of the element from this multiset, if present.
   *
   * @param c collection with elements to retain in this multiset
   * @return <code>true</code> if at least one element was removed
   */
  @Override
  public boolean retainAll(Collection<?> c) {
    Set<E> set = new HashSet<>(map.keySet());
    for(E e: set) {
      if(!c.contains(e)) {
        size -= map.get(e);
        map.remove(e);
      }
    }
    return true;
  }

  /**
   * Removes all of the elements from this multiset.
   * The collection will be empty after this method returns.
   */
  @Override
  public void clear() {
    map.clear();
  }
}