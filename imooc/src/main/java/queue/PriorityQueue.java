package queue;

import heap.MaxHeap;

/**
 * 基于最大堆的优先队列
 *
 * @author wzl
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }

    /**
     * 获取队列元素个数
     */
    @Override
    public int getSize() {
        return maxHeap.size();
    }

    /**
     * 判断队列是否为空
     */
    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    /**
     * 入队
     */
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    /**
     * 出队
     */
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    /**
     * 获取队首元素
     */
    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
