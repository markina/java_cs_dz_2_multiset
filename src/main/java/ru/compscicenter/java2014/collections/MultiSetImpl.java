package ru.compscicenter.java2014.collections;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
   * Creates a {@link java.util.Spliterator} over the elements in this collection.
   * <p>
   * Implementations should document characteristic values reported by the
   * spliterator.  Such characteristic values are not required to be reported
   * if the spliterator reports {@link java.util.Spliterator#SIZED} and this collection
   * contains no elements.
   * <p>
   * <p>The default implementation should be overridden by subclasses that
   * can return a more efficient spliterator.  In order to
   * preserve expected laziness behavior for the {@link #stream()} and
   * {@link #parallelStream()}} methods, spliterators should either have the
   * characteristic of {@code IMMUTABLE} or {@code CONCURRENT}, or be
   * <em><a href="Spliterator.html#binding">late-binding</a></em>.
   * If none of these is practical, the overriding class should describe the
   * spliterator's documented policy of binding and structural interference,
   * and should override the {@link #stream()} and {@link #parallelStream()}
   * methods to create streams using a {@code Supplier} of the spliterator,
   * as in:
   * <pre>{@code
   *     Stream<E> s = StreamSupport.stream(() -> spliterator(), spliteratorCharacteristics)
   * }</pre>
   * <p>These requirements ensure that streams produced by the
   * {@link #stream()} and {@link #parallelStream()} methods will reflect the
   * contents of the collection as of initiation of the terminal stream
   * operation.
   *
   * @return a {@code Spliterator} over the elements in this collection
   * @implSpec The default implementation creates a
   * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
   * from the collections's {@code Iterator}.  The spliterator inherits the
   * <em>fail-fast</em> properties of the collection's iterator.
   * <p>
   * The created {@code Spliterator} reports {@link java.util.Spliterator#SIZED}.
   * @implNote The created {@code Spliterator} additionally reports
   * {@link java.util.Spliterator#SUBSIZED}.
   * <p>
   * <p>If a spliterator covers no elements then the reporting of additional
   * characteristic values, beyond that of {@code SIZED} and {@code SUBSIZED},
   * does not aid clients to control, specialize or simplify computation.
   * However, this does enable shared use of an immutable and empty
   * spliterator instance (see {@link Spliterators#emptySpliterator()}) for
   * empty collections, and enables clients to determine if such a spliterator
   * covers no elements.
   * @since 1.8
   */
  @Override
  public Spliterator<E> spliterator() {
    return null;
  }

  /**
   * Returns a sequential {@code Stream} with this collection as its source.
   * <p>
   * <p>This method should be overridden when the {@link #spliterator()}
   * method cannot return a spliterator that is {@code IMMUTABLE},
   * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
   * for details.)
   *
   * @return a sequential {@code Stream} over the elements in this collection
   * @implSpec The default implementation creates a sequential {@code Stream} from the
   * collection's {@code Spliterator}.
   * @since 1.8
   */
  @Override
  public Stream<E> stream() {
    return null;
  }

  /**
   * Returns a possibly parallel {@code Stream} with this collection as its
   * source.  It is allowable for this method to return a sequential stream.
   * <p>
   * <p>This method should be overridden when the {@link #spliterator()}
   * method cannot return a spliterator that is {@code IMMUTABLE},
   * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
   * for details.)
   *
   * @return a possibly parallel {@code Stream} over the elements in this
   * collection
   * @implSpec The default implementation creates a parallel {@code Stream} from the
   * collection's {@code Spliterator}.
   * @since 1.8
   */
  @Override
  public Stream<E> parallelStream() {
    return null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
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
  public void forEach(Consumer<? super E> action) {

  }

  @Override
  public boolean add(E e) {
    add(e, 1);
    return true;
  }

  @Override
  public int add(E e, int occurrences) throws IllegalArgumentException {
    if (occurrences < 0) {
      throw new IllegalArgumentException();
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

  @Override
  public int count(Object e) {
    if (map.containsKey(e)) {
      return map.get(e);
    } else {
      return 0;
    }
  }

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
    return false;
  }

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
    return false;
  }

  /**
   * Removes all of the elements of this collection that satisfy the given
   * predicate.  Errors or runtime exceptions thrown during iteration or by
   * the predicate are relayed to the caller.
   *
   * @param filter a predicate which returns {@code true} for elements to be
   *               removed
   * @return {@code true} if any elements were removed
   * @throws NullPointerException          if the specified filter is null
   * @throws UnsupportedOperationException if elements cannot be removed
   *                                       from this collection.  Implementations may throw this exception if a
   *                                       matching element cannot be removed or if, in general, removal is not
   *                                       supported.
   * @implSpec The default implementation traverses all elements of the collection using
   * its {@link #iterator}.  Each matching element is removed using
   * {@link java.util.Iterator#remove()}.  If the collection's iterator does not
   * support removal then an {@code UnsupportedOperationException} will be
   * thrown on the first matching element.
   * @since 1.8
   */
  @Override
  public boolean removeIf(Predicate<? super E> filter) {
    return false;
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
    return false;
  }

  @Override
  public void clear() {
    map.clear();
  }
}
