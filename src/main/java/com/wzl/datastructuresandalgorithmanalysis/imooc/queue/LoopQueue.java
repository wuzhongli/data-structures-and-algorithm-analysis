package com.wzl.datastructuresandalgorithmanalysis.imooc.queue;

/**
 * 循环队列
 *
 * @author wzl
 */
public class LoopQueue<E> implements Queue<E> {

    private Object[] elementData;
    private static int DEFAULT_CAPACITY = 10;
    private int size;
    private int front;
    private int tail;

    public LoopQueue(int initCapacity) {
        elementData = new Object[initCapacity + 1];
    }

    public LoopQueue() {
        this(DEFAULT_CAPACITY);
    }

    public int getCapacity() {
        return elementData.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public boolean isFull() {
        return (tail + 1) % elementData.length == front;
    }

    @Override
    public void enqueue(E e) {
        if (isFull()) {
            resize(getCapacity() * 2);
        }

        elementData[tail] = e;
        tail = (tail + 1) % elementData.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("队列为空");
        }

        E ret = elementData(front);
        elementData[front] = null;
        front = (front + 1) % elementData.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        return elementData(front);
    }

    private E elementData(int index) {
        return (E) elementData[index];
    }

    private void resize(int newCapacity) {
        Object[] newElementData = new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newElementData[i] = elementData[(i + front) % elementData.length];
        }
        elementData = newElementData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % elementData.length) {
            res.append(elementData[i]);
            if ((i + 1) % elementData.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {

        LoopQueue<Integer> queue = new LoopQueue<>(5);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
