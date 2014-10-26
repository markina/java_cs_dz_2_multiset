package ru.compscicenter.java2014.collections.test;

import org.testng.annotations.Test;
import ru.compscicenter.java2014.collections.MultiSet;
import ru.compscicenter.java2014.collections.MultiSetImpl;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Consumer;

import static org.fest.assertions.api.Assertions.*;

@Test(timeOut = 1000L, sequential = true)
public class MultiSetTest {

  private Class<?> multiSetClass;

  public MultiSetTest(Class<?> multiSetClass) throws Exception {
    if (multiSetClass == null) {
      throw new IllegalArgumentException("multiSetClass");
    }
    this.multiSetClass = multiSetClass;
    newMultiSetMustBeEmpty();
    checkSizeAddAndRemove();
    checkSizeAddCollectionAndRemoveOccurrences();
    checkToArray();
    checkContains();
    checkAddOcc();
    checkRemove();
    checkEquals();
    checkIterator();


  }

  private void checkIterator() {
    MultiSet<Integer> ms1 = new MultiSetImpl<>();
    ms1.add(1, 2);
    ms1.add(2, 2);
    ms1.add(3);
    ms1.add(10);
    ms1.add(12, 2);
    ms1.add(11, 1);
    ms1.add(1, 1);

    Iterator<Integer> it = ms1.iterator();
    for(int i = 0; i < ms1.size(); i++) {
      assert (it.hasNext());
      it.next();
    }

    Consumer<Integer> consumer = (Integer a) -> a += 5;
    ms1.forEach(consumer);

    Integer[] ar = new Integer[10];
    ms1.toArray(ar);
    assert (ar.length == 10);
    assert (ar[0] == 6);
    assert (ar[1] == 3);
    assert (ar[2] == 3);
    assert (ar[3] == 4);
    assert (ar[4] == 4);

    assert (ar[5] == 5);
    assert (ar[6] == 12);
    assert (ar[7] == 13);
    assert (ar[8] == 14);
    assert (ar[9] == 14);

  }

  private void checkEquals() {
    MultiSet<Integer> ms1 = new MultiSetImpl<>();
    MultiSet<Integer> ms2 = new MultiSetImpl<>();

    ms1.add(1, 2);
    ms1.add(2, 2);
    ms1.add(3);
    ms1.add(10);
    ms1.add(12, 2);
    ms1.add(11, 1);
    ms1.add(1, 1);

    ms2.add(1);
    ms2.add(2);
    ms2.add(1);
    ms2.add(3);
    ms2.add(2);
    ms2.add(10);
    ms2.add(12);
    ms2.add(11);
    ms2.add(12);
    ms2.add(1);

    assert (ms1.equals(ms2));
  }

  private void checkRemove() {
    MultiSet<Integer> ms1 = new MultiSetImpl<>();

    ms1.add(1, 2);
    ms1.add(2, 2);
    ms1.add(3);
    ms1.add(10);
    ms1.add(12, 2);
    ms1.add(11, 1);
    ms1.add(1, 1);


    assert (ms1.remove(1));
    assert (ms1.remove(2));
    assert (ms1.remove(3));
    assert (ms1.remove(1));
    assert (ms1.remove(10));
    assert (ms1.remove(12, 2) == 2);
    assert (ms1.remove(11));
    assert (ms1.remove(2));
    assert (ms1.remove(1, 1) == 1);

    assert (ms1.size() == 0);
  }

  private void checkAddOcc() {
    MultiSet<Integer> ms1 = new MultiSetImpl<>();

    ms1.add(1, 2);
    ms1.add(2, 2);
    ms1.add(3);
    ms1.add(10);
    ms1.add(12, 2);
    ms1.add(11, 1);
    ms1.add(1, 1);

    assert (ms1.contains(1));
    assert (ms1.contains(2));
    assert (ms1.contains(3));
    assert (ms1.contains(10));
    assert (ms1.contains(11));
    assert (ms1.contains(12));

    assert (!ms1.contains(111));
    assert (!ms1.contains(21));
    assert (!ms1.contains(31));
    assert (!ms1.contains(101));
    assert (!ms1.contains(111));
    assert (!ms1.contains(121));

    Integer[] ar = new Integer[10];
    ms1.toArray(ar);
    assert (ar.length == 10);
    assert (ar[0] == 1);
    assert (ar[1] == 1);
    assert (ar[2] == 1);
    assert (ar[3] == 2);
    assert (ar[4] == 2);

    assert (ar[5] == 3);
    assert (ar[6] == 10);
    assert (ar[7] == 11);
    assert (ar[8] == 12);
    assert (ar[9] == 12);
  }

  private void checkContains() {
    MultiSet<Integer> ms1 = new MultiSetImpl<>();

    ms1.add(1);
    ms1.add(2);
    ms1.add(1);
    ms1.add(3);
    ms1.add(2);
    ms1.add(10);
    ms1.add(12);
    ms1.add(11);
    ms1.add(12);
    ms1.add(1);

    assert (ms1.contains(1));
    assert (ms1.contains(2));
    assert (ms1.contains(3));
    assert (ms1.contains(10));
    assert (ms1.contains(11));
    assert (ms1.contains(12));

    assert (!ms1.contains(111));
    assert (!ms1.contains(21));
    assert (!ms1.contains(31));
    assert (!ms1.contains(101));
    assert (!ms1.contains(111));
    assert (!ms1.contains(121));


    Integer[] ar = new Integer[10];
    ms1.toArray(ar);
    assert (ar.length == 10);
    assert (ar[0] == 1);
    assert (ar[1] == 1);
    assert (ar[2] == 1);
    assert (ar[3] == 2);
    assert (ar[4] == 2);

    assert (ar[5] == 3);
    assert (ar[6] == 10);
    assert (ar[7] == 11);
    assert (ar[8] == 12);
    assert (ar[9] == 12);
  }

  private void checkToArray() {
    MultiSet<Integer> ms1 = new MultiSetImpl<>();
    Integer[] ar = new Integer[10];
    ar[0] = 1;
    ar[0] = 2;
    ar[0] = 1;
    ar[0] = 3;
    ar[0] = 2;

    ar[0] = 10;
    ar[0] = 12;
    ar[0] = 11;
    ar[0] = 12;
    ar[0] = 1;

    ms1.add(1);
    ms1.add(2);
    ms1.add(1);
    ms1.add(3);
    ms1.add(2);
    ms1.add(10);
    ms1.add(12);
    ms1.add(11);
    ms1.add(12);
    ms1.add(1);


    ms1.toArray(ar);
    assert (ar.length == 10);
    assert (ar[0] == 1);
    assert (ar[1] == 1);
    assert (ar[2] == 1);
    assert (ar[3] == 2);
    assert (ar[4] == 2);

    assert (ar[5] == 3);
    assert (ar[6] == 10);
    assert (ar[7] == 11);
    assert (ar[8] == 12);
    assert (ar[9] == 12);
  }

  private void checkSizeAddAndRemove() {
    MultiSet<Integer> multiSet = new MultiSetImpl<>();
    multiSet.add(1);                         // 1
    assert (multiSet.size() == 1);
    multiSet.add(1);                         // 1 1
    assert (multiSet.size() == 2);
    multiSet.add(2);                         // 1 1 2
    assert (multiSet.size() == 3);
    multiSet.add(1);                         // 1 1 1 2
    assert (multiSet.size() == 4);
    multiSet.add(2);                         // 1 1 1 2 2
    assert (multiSet.size() == 5);
    multiSet.add(2);                         // 1 1 1 2 2 2
    assert (multiSet.size() == 6);
    multiSet.add(3);                         // 1 1 1 2 2 2 3
    assert (multiSet.size() == 7);
    assert (multiSet.remove(3));              // 1 1 1 2 2 2
    assert (multiSet.size() == 6);
    assert (multiSet.remove(2));              // 1 1 1 2 2
    assert (multiSet.size() == 5);
    assert (multiSet.remove(2));              // 1 1 1 2
    assert (multiSet.size() == 4);
    assert (multiSet.remove(2));              // 1 1 1
    assert (multiSet.size() == 3);
    assert (!multiSet.remove(2));             // 1 1 1
    assert (multiSet.size() == 3);
    multiSet.clear();
    assert (multiSet.size() == 0);


  }

  private void checkSizeAddCollectionAndRemoveOccurrences() {
    MultiSet<Integer> multiSet = new MultiSetImpl<>();

    multiSet.add(1);
    assert (multiSet.size() == 1);
    assert (multiSet.remove(1, 1) == 1);
    assert (multiSet.size() == 0);

    Collection<Integer> collection = new ArrayList<>();
    collection.add(1);
    collection.add(1);
    collection.add(1);

    collection.add(1);
    collection.add(1);
    collection.add(1);

    assert (collection.size() == 6);

    assert (multiSet.addAll(collection));
    assert (multiSet.size() == 6);
    assert (multiSet.remove(1, 4) == 6);
    assert (multiSet.size() == 2);
    assert (multiSet.remove(1, 2) == 2);
    assert (multiSet.size() == 0);
  }

  private void newMultiSetMustBeEmpty() throws Exception {
    assertThat(newMultiSet()).isEmpty();
    assertThat(newMultiSet()).hasSize(0);
  }

  /*
   * This is constructor without parameters for your MultiSet implementation.
   */
  private <E> MultiSet<E> newMultiSet() throws Exception {
    Constructor<?> constructor = getNoArgConstructor();
    constructor.setAccessible(true);
    return (MultiSet<E>) constructor.newInstance();
  }

  /*
   * This is constructor with Collection parameter for your MultiSet implementation.
   */
  private <E> MultiSet<E> newMultiSet(Collection c) throws Exception {
    Constructor<?> constructor = getCollectionConstructor();
    constructor.setAccessible(true);
    return (MultiSet<E>) constructor.newInstance(c);
  }

  private Constructor<?> getNoArgConstructor() throws Exception {
    return multiSetClass.getDeclaredConstructor();
  }

  private Constructor<?> getCollectionConstructor() throws Exception {
    return multiSetClass.getDeclaredConstructor(Collection.class);
  }

  private static Integer randomInteger() {
    return (int) (Math.random() * Integer.MAX_VALUE);
  }
}
