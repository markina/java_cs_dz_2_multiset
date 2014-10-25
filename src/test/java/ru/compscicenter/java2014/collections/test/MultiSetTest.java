package ru.compscicenter.java2014.collections.test;

import org.testng.annotations.Test;
import ru.compscicenter.java2014.collections.MultiSet;
import ru.compscicenter.java2014.collections.MultiSetImpl;

import java.lang.reflect.Constructor;
import java.util.*;

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
