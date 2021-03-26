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
            if (elementData[i] != null && elementData[i].equals(o)
                    || elementData[i] == null && elementData[i] == o) {
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
            if (elementData[i] != null && elementData[i].equals(o)
                    || elementData[i] == null && elementData[i] == o) {
                isElement = true;
                size--;
                for (int j = i; j < elementData.length - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }
                elementData[elementData.length - 1] = null;
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
                if (elementData[i] != null && elementData[i].equals(obj)
                        || elementData[i] == null && elementData[i] == obj) {
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
                if (elementData[i] != null && elementData[i].equals(obj)
                        || elementData[i] == null && elementData[i] == obj) {
                    isElement = true;
                    size--;
                    for (int j = i; j < elementData.length - 1; j++) {
                        elementData[j] = elementData[j + 1];
                    }
                    i--;
                    elementData[elementData.length - 1] = null;
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
                if (elementData[i] != null && elementData[i].equals(obj)
                        || elementData[i] == null && elementData[i] == obj) {
                    newCollection.add(obj);
                    break;
                }
            }

        }
        if (newCollection.toArray().length == size) {
            return false;
        } else {
            size = newCollection.toArray().length;
            elementData = Arrays.copyOf(newCollection.toArray(), elementData.length);
            return true;
        }
    }

    @Override
    public void clear() {
        size = 0;
        for (Object obj : elementData
        ) {
            obj = null;
        }
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
                size--;
                for (int i = cursor; i < elementData.length - 1; i++) {
                    elementData[i] = elementData[i + 1];
                }
                isNext = false;
            }

        }
    }
}
