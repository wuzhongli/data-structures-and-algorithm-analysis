package chapter5;

/**
 * 可以放在一个哈希表中的Employee类的例子
 * 提供equals方法和基于Employee名字的hashCode方法
 *
 * @author wzl
 */
public class Employee {

    @Override
    public boolean equals(Object o) {
        return o instanceof Employee && name.equals(((Employee) o).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    private String name;
    /**
     * 薪水
     */
    private double salary;
    /**
     * 排行
     */
    private int seniority;
}
