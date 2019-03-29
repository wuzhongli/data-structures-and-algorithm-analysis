package heap;

import array.Array;

/**
 * 最大堆
 *
 * @author wzl
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(data.getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("非法参数");
        }
        return (index - 1) >> 1;
    }

    private int leftChild(int index) {
        return (index << 1) + 1;
    }

    private int rightChild(int index) {
        return (index << 1) + 2;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    public E findMax() {
        return data.get(0);
    }

    public E extractMax() {
        E result = findMax();

        // 交换堆首尾元素 以实现删除堆顶元素
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return result;
    }

    public E replace(E e) {
        E result = findMax();
        data.set(0, e);
        siftDown(0);
        return result;
    }

    /**
     * 上浮
     */
    private void siftUp(int i) {
        while (i > 0 && data.get(i).compareTo(data.get(parent(i))) >= 0) {
            data.swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * 下沉
     */
    private void siftDown(int top) {

        while (leftChild(top) < data.getSize()) {
            int maxChildIndex = leftChild(top);

            // 索引为i的元素存在右孩子 并且右孩子大于左孩子
            if (rightChild(top) < data.getSize()
                    && data.get(rightChild(top)).compareTo(data.get(maxChildIndex)) > 0) {
                maxChildIndex = rightChild(top);
            }

            if (data.get(top).compareTo(data.get(maxChildIndex)) >= 0) {
                break;
            }

            data.swap(top, maxChildIndex);
            top = maxChildIndex;
        }
    }

}
