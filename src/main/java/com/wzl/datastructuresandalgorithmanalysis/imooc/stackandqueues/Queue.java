package com.wzl.datastructuresandalgorithmanalysis.imooc.stackandqueues;

/**
 * 队列接口
 *
 * @author wzl
 */
public interface Queue<E> {

    /**
     * 获取队列元素个数
     */
    int getSize();

    /**
     * 判断队列是否为空
     */
    boolean isEmpty();

    /**
     * 判断队列是否已满
     */
    boolean isFull();

    /**
     * 入队
     */
    void enqueue(E e);

    /**
     * 出队
     */
    E dequeue();

    /**
     * 获取队首元素
     */
    E getFront();
}
