package array;

/**
 * 实现自己的动态数组
 *
 * @author wzl
 */
public class Array<E> {

    private Object[] elementData;
    private static int DEFAULT_CAPACITY = 10;
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     */
    public Array(int initCapacity) {
        elementData = new Object[initCapacity];
    }

    /**
     * 无参数的构造函数，默认数组的容量为DEFAULT_CAPACITY
     */
    public Array() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 获取数组的容量
     */
    public int getCapacity() {
        return elementData.length;
    }

    /**
     * 获取数组中的元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在index索引的位置插入一个新元素e
     */
    public void add(int index, E e) {

        rangeCheckForAdd(index);

        if (size == elementData.length) {
            resize(2 * elementData.length);
        }

        for (int i = size - 1; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }

        elementData[index] = e;

        size++;
    }

    /**
     * 向所有元素后添加一个新元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在所有元素前添加一个新元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 获取index索引位置的元素
     */
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    /**
     * 修改index索引位置的元素为e
     */
    public void set(int index, E e) {
        rangeCheck(index);
        elementData[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除index位置的元素, 返回删除的元素
     */
    public E remove(int index) {
        rangeCheck(index);

        E ret = elementData(index);
        for (int i = index + 1; i < size; i++) {
            elementData[i - 1] = elementData[i];
        }
        size--;
        // loitering objects != memory leak
        elementData[size] = null;

        if (size == elementData.length / 4 && elementData.length / 2 != 0) {
            resize(elementData.length / 2);
        }
        return ret;
    }

    /**
     * 从数组中删除第一个元素, 返回删除的元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素, 返回删除的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, elementData.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(elementData[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    /**
     * 将数组空间的容量变成newCapacity大小
     */
    private void resize(int newCapacity) {

        Object[] newElementData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
    }

    private E elementData(int index) {
        return (E) elementData[index];
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}
