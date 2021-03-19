package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class MyCollection<E> implements Collection<E> {

    private int size;

    private Object[] elementData = new Object[10];


    @Override
    public boolean add(final E e) {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, (int) (size * 1.5f));
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public boolean contains(final Object o) {
        boolean isElement = false;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                isElement = true;
                break;
            }
        }
        return isElement;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        if (a.length <= size) {
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        } else {
            for (int i = 0; i < size; i++) {
                a[i] = (T) elementData[i];
            }
            return a;
        }

    }

    @Override
    public boolean remove(final Object o) {
        boolean isElement = false;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                isElement = true;
                size--;
                for (int j = i; j < elementData.length - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }
                elementData = Arrays.copyOf(elementData, elementData.length - 1);
                break;
            }
        }
        return isElement;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        boolean isElement;

        for (Object obj : c
        ) {
            isElement = false;
            for (int i = 0; i < size; i++) {
                if (elementData[i].equals(obj)) {
                    isElement = true;
                    break;
                }
            }
            if (!isElement) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        for (Object obj : c
        ) {
            if (size == elementData.length) {
                elementData = Arrays.copyOf(elementData, (int) (size * 1.5f));
            }
            elementData[size++] = obj;
        }

        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {

        boolean isElement = false;
        for (Object obj : c
        ) {
            for (int i = 0; i < size; i++) {
                if (elementData[i].equals(obj)) {
                    isElement = true;
                    size--;
                    for (int j = i; j < elementData.length - 1; j++) {
                        elementData[j] = elementData[j + 1];
                    }
                    i--;
                    elementData = Arrays.copyOf(elementData, elementData.length - 1);
                }
            }
        }
        return isElement;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        MyCollection<Object> newCollection = new MyCollection<>();

        for (int i = 0; i < size; i++) {
            for (Object obj : c
            ) {
                if (elementData[i].equals(obj)) {
                    newCollection.add(obj);
                    break;
                }
            }

        }
        if (newCollection.toArray().length == size) {
            return false;
        } else {
            size = newCollection.toArray().length;
            elementData = Arrays.copyOf(newCollection.toArray(), size);
            return true;
        }
    }

    @Override
    public void clear() {
        size = 0;
        elementData = Arrays.copyOf(elementData, size);
    }

    private class MyIterator<T> implements Iterator<T> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        private boolean isNext = false;



        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            isNext = true;
            return (T) elementData[cursor++];

        }

        @Override
        public void remove() {
            if (!isNext) {
                throw new IllegalStateException();
            } else {
                cursor--;
                for (int i = cursor; i < elementData.length - 1; i++) {
                    elementData[i] = elementData[i + 1];
                }
                isNext = false;
            }

        }
    }
}
