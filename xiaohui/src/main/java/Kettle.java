/**
 * 使用6L和5L的水壶得到3L的水
 *
 * @author wzl
 */
public class Kettle {

    /**
     * 水壶容量
     */
    private int capacity;

    public Kettle(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 水壶当前水量
     */
    private int currentCapacity;

    /**
     * 用湖中水装满水壶
     */
    public void filledWithWater() {
        this.currentCapacity = capacity;
    }

    /**
     * 把壶中水清空
     */
    public void pourWater() {
        this.currentCapacity = 0;
    }

    /**
     * 向另一个水壶倒水
     */
    public void pourWaterToOtherKettle(Kettle target) {
        if (isEmpty()) {
            return;
        }

        // 目标水壶剩余容量
        int targetRemainingCapacity = target.capacity - target.currentCapacity;

        // 当前水量小于目标水壶剩余容量，则水壶中所有的水倒进目标水壶
        if (this.currentCapacity < targetRemainingCapacity) {
            target.currentCapacity += this.currentCapacity;
            this.currentCapacity = 0;
        }
        // 当前水量大于等于目标水壶剩余容量，则当前水壶水量减去目标水壶的剩余容量，目标水壶加满
        else {
            this.currentCapacity -= targetRemainingCapacity;
            target.currentCapacity = target.capacity;
        }
    }

    public boolean isEmpty() {
        return this.currentCapacity == 0;
    }

    public static void main(String[] args) {

        Kettle a = new Kettle(6);
        Kettle b = new Kettle(5);

    }
}
