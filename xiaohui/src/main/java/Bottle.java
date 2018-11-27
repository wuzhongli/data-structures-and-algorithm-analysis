public class Bottle {

    public static final int EMPTY = 0;
    private String name;
    private int size;//瓶子的尺寸
    private int usage;//瓶子的使用量
    private int fullReason;//水满的原因，1代表灌满，-1代表对方倒水导致满
    private int emptyReason;//水空的原因，1代表排空，-1给对方倒水导致空

    public Bottle(String name, int size) {
        super();
        this.name = name;
        this.size = size;
        this.usage = EMPTY;
    }

    /**
     * 瓶子一共有三个行为，灌满，倒光，向另外一个瓶子倒水
     */

    //灌满水
    public void fillUp() {
        this.usage = size;
        this.fullReason = 1;
        System.out.println(name + "已经灌满！");
    }

    //倒光水
    public void pourUp() {
        this.usage = EMPTY;
        this.emptyReason = 1;
        System.out.println(name + "已经倒光！");
    }

    //向其他瓶子倒水
    public void pourToTheOtherBottle(Bottle other) {
        if (this.isEmpty() || other.isFull()) {
            return;
        }
        /**
         * 像另外一个瓶子倒水，停止条件为对方瓶子满了，或者我方瓶子空了，因此倒水之前，先对比一下我方的水量，和对方的剩余容量
         */
        if (this.usage >= other.currentCapacity()) {
            this.usage = this.usage - other.currentCapacity();
            if (this.isEmpty()) {
                this.emptyReason = -1;
            }
            other.usage = other.size;
            other.fullReason = -1;
        } else {
            other.usage = other.usage + this.usage;
            this.usage = EMPTY;
            this.emptyReason = -1;
        }
        System.out.println(this.name + "向" + other.name + "倒水！");
    }


    public int getUsage() {
        return usage;
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        return usage == size;
    }

    public boolean isEmpty() {
        return usage == EMPTY;
    }

    public boolean isNotFullAndNotEmpty() {
        return usage != EMPTY && usage != size;
    }

    public int getFullReason() {
        return fullReason;
    }

    public int getEmptyReason() {
        return emptyReason;
    }

    //获取瓶子的当前剩余容量
    public int currentCapacity() {
        return size - usage;
    }
}
