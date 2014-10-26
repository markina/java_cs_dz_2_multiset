package ru.compscicenter.java2014.collections;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by Markina Margarita on 20.10.14.
 */

public class MultiSetImpl<E> extends AbstractCollection<E> implements MultiSet<E> {
  private Map<E, Integer> map;
  private int size;

  public MultiSetImpl() {
    map = new HashMap<>();
    size = 0;
  }

  public MultiSetImpl(final Collection<? extends E> c) {
    this();
    addAll(c);
  }

  /**
   * Returns a hash code value for the object. This method is
   * supported for the benefit of hash tables such as those provided by
   * {@link java.util.HashMap}.
   * <p>
   * The general contract of {@code hashCode} is:
   * <ul>
   * <li>Whenever it is invoked on the same object more than once during
   * an execution of a Java application, the {@code hashCode} method
   * must consistently return the same integer, provided no information
   * used in {@code equals} comparisons on the object is modified.
   * This integer need not remain consistent from one execution of an
   * application to another execution of the same application.
   * <li>If two objects are equal according to the {@code equals(Object)}
   * method, then calling the {@code hashCode} method on each of
   * the two objects must produce the same integer result.
   * <li>It is <em>not</em> required that if two objects are unequal
   * according to the {@link Object#equals(Object)}
   * method, then calling the {@code hashCode} method on each of the
   * two objects must produce distinct integer results.  However, the
   * programmer should be aware that producing distinct integer results
   * for unequal objects may improve the performance of hash tables.
   * </ul>
   * <p>
   * As much as is reasonably practical, the hashCode method defined by
   * class {@code Object} does return distinct integers for distinct
   * objects. (This is typically implemented by converting the internal
   * address of the object into an integer, but this implementation
   * technique is not required by the
   * Java&trade; programming language.)
   *
   * @return a hash code value for this object.
   * @see Object#equals(Object)
   * @see System#identityHashCode
   */
  @Override
  public final int hashCode() {
    return super.hashCode();
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * <p>
   * The {@code equals} method implements an equivalence relation
   * on non-null object references:
   * <ul>
   * <li>It is <i>reflexive</i>: for any non-null reference value
   * {@code x}, {@code x.equals(x)} should return
   * {@code true}.
   * <li>It is <i>symmetric</i>: for any non-null reference values
   * {@code x} and {@code y}, {@code x.equals(y)}
   * should return {@code true} if and only if
   * {@code y.equals(x)} returns {@code true}.
   * <li>It is <i>transitive</i>: for any non-null reference values
   * {@code x}, {@code y}, and {@code z}, if
   * {@code x.equals(y)} returns {@code true} and
   * {@code y.equals(z)} returns {@code true}, then
   * {@code x.equals(z)} should return {@code true}.
   * <li>It is <i>consistent</i>: for any non-null reference values
   * {@code x} and {@code y}, multiple invocations of
   * {@code x.equals(y)} consistently return {@code true}
   * or consistently return {@code false}, provided no
   * information used in {@code equals} comparisons on the
   * objects is modified.
   * <li>For any non-null reference value {@code x},
   * {@code x.equals(null)} should return {@code false}.
   * </ul>
   * <p>
   * The {@code equals} method for class {@code Object} implements
   * the most discriminating possible equivalence relation on objects;
   * that is, for any non-null reference values {@code x} and
   * {@code y}, this method returns {@code true} if and only
   * if {@code x} and {@code y} refer to the same object
   * ({@code x == y} has the value {@code true}).
   * <p>
   * Note that it is generally necessary to override the {@code hashCode}
   * method whenever this method is overridden, so as to maintain the
   * general contract for the {@code hashCode} method, which states
   * that equal objects must have equal hash codes.
   *
   * @param obj the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj
   * argument; {@code false} otherwise.
   * @see #hashCode()
   * @see java.util.HashMap
   */
  @Override
  public final boolean equals(final Object obj) {

    if (!(obj instanceof MultiSetImpl)) {
      return false;
    }
    MultiSet<E> multiObj = (MultiSetImpl<E>) obj;

    if (multiObj.size() != size) {
      return false;
    }
    Iterator<E> it1 = this.iterator();
    Iterator<E> it2 = multiObj.iterator();

    while (it1.hasNext()) {
      E a = it1.next();
      E b = it2.next();

      if (!a.equals(b)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the number of elements in this multiset, including all duplicates
   *
   * @return the number of elements in this multiset, including all duplicates
   */
  @Override
  public final int size() {
    return size;
  }

  /**
   * Returns <tt>true</tt> if this collection contains no elements.
   *
   * @return <tt>true</tt> if this collection contains no elements
   */
  @Override
  public final boolean isEmpty() {
    return size == 0;
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
  public final boolean contains(final Object o) {
    return map.containsKey(o);
  }

  /**
   * Returns an iterator over the elements in this multiset.
   * Elements that occur multiple times in the multiset will be returned multiple times
   * by this iterator, but the order is not defined.
   *
   * @return an <tt>Iterator</tt> over the elements in this multiset
   */
  @Override
  public final Iterator<E> iterator() {
    return new MultiSetIterator();
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
  public final boolean add(final E e) {
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
  public final int add(final E e, final int occurrences) {
    if (occurrences < 0) {
      throw new IllegalArgumentException();
    }
    Objects.requireNonNull(e);
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
  public final boolean remove(final Object e) {

    int occBefore = remove(e, 1);
    if (occBefore == 0) {
      return false;
    } else if (occBefore == 1) {
      return !map.containsKey(e);
    } else {
      return map.get(e) == occBefore - 1;
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
  public final int remove(final Object e, final int occurrences) {
    int occurrencesBeforeTheOperation = 0;
    if (occurrences < 0) {
      throw new IllegalArgumentException();
    }
    if (map.containsKey(e)) {
      occurrencesBeforeTheOperation = map.get(e);
      if (occurrencesBeforeTheOperation <= occurrences) {
        size -= occurrences;
        map.remove(e);
      } else {
        size -= occurrences;
        map.put((E) e, occurrencesBeforeTheOperation - occurrences);
      }
    }
    return occurrencesBeforeTheOperation;
  }

  /**
   * Returns the number of occurrences of an element in this multiset,
   * or <code>0</code> if multiset does not contain this element.
   *
   * @param e the element to whose occurrences are to be returned
   * @return the number of occurrences of an element in this multiset
   */
  @Override
  public final int count(final Object e) {
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
  public final boolean containsAll(final Collection<?> c) {
    for (Object el : c) {
      if (!map.containsKey(el)) {
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
  public final boolean addAll(final Collection<? extends E> c) {
    if (c == this) {
      throw new ClassCastException();
    }
    Objects.requireNonNull(c);
    int addSize = c.size();
    for (E object : c) {
      add(object);
    }
    return addSize != 0;
  }

  /**
   * Performs the given action for each element of the {@code Iterable}
   * until all elements have been processed or the action throws an
   * exception.  Unless otherwise specified by the implementing class,
   * actions are performed in the order of iteration (if an iteration order
   * is specified).  Exceptions thrown by the action are relayed to the
   * caller.
   *
   * @param action The action to be performed for each element
   * @throws NullPointerException if the specified action is null
   * @implSpec <p>The default implementation behaves as if:
   * <pre>{@code
   *     for (T t : this)
   *         action.accept(t);
   * }</pre>
   * @since 1.8
   */
  @Override
  public final void forEach(final Consumer<? super E> action) {
    if (map.keySet().size() == 0) {
      return;
    }
    Set<E> setKey = new HashSet<>(map.keySet());
    for (E k : setKey) {
      int cntK = map.get(k);
      map.remove(k);
      action.accept(k);
      map.put(k, cntK);
    }
  }

  /**
   * For each element in given collection removes <em>all</em> occurrences
   * of the element from this multiset, if present.
   *
   * @param c collection with elements to remove from this multiset
   * @return <code>true</code> if at least one element was found and removed
   */
  @Override
  public final boolean removeAll(final Collection<?> c) {
    Set<E> set = new HashSet<>(map.keySet());
    for (E e : set) {
      if (c.contains(e)) {
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
  public final boolean retainAll(final Collection<?> c) {
    Set<E> set = new HashSet<>(map.keySet());
    for (E e : set) {
      if (!c.contains(e)) {
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
  public final void clear() {
    map.clear();
    size = 0;
  }

  private class MultiSetIterator implements Iterator<E> {
    private Set<E> setKey;
    private E currentKey;
    private int cntByCurrentKey;
    private Iterator<E> iterator;
    private int id = 0;


    public MultiSetIterator() {
      setKey = new HashSet<>(map.keySet());
      iterator = setKey.iterator();
      id = 0;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
      if(cntByCurrentKey == id) {
        return iterator.hasNext();
      }
      if (currentKey != null) {
        return true;
      }
      return iterator.hasNext();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws java.util.NoSuchElementException if the iteration has no more elements
     */
    @Override
    public E next() {
      if (hasNext()) {
        if (currentKey != null && cntByCurrentKey > id) {
          ++id;
        } else {
          currentKey = iterator.next();
          cntByCurrentKey = map.get(currentKey);
          id = 1;
        }
      }
      return currentKey;

    }


    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this iterator
     * @throws IllegalStateException         if the {@code next} method has not
     *                                       yet been called, or the {@code remove} method has already
     *                                       been called after the last call to the {@code next}
     *                                       method
     * @implSpec The default implementation throws an instance of
     * {@link UnsupportedOperationException} and performs no other action.
     */
    public void remove() {
      if (currentKey == null) {
        throw new IllegalStateException();
      }
      E oldCurrentKey = currentKey;
      size--;
      if (cntByCurrentKey == 1) {
        map.remove(oldCurrentKey);
      } else {
        map.put(oldCurrentKey, map.get(oldCurrentKey) - 1);
      }
    }

  }
}
