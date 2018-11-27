public class WaterProblemContainer {

    private Bottle bottle1;
    private Bottle bottle2;

    private int result;

    public WaterProblemContainer(Bottle bottle1, Bottle bottle2, int result) {
        this.bottle1 = bottle1;
        this.bottle2 = bottle2;
        if (result > bottle1.getSize() + bottle2.getSize()) {
            throw new RuntimeException("无效计算result：输入的计算result大小超过了两个水壶的最大容量之和！");
        }
        this.result = result;
    }

    /**
     * 问题分析：
     *
     * 如果两个瓶子中，假设任何一个瓶子都不向对方倒水，那么，任何一个瓶子，都只能有灌满和倒光两种行为，能够得到的result只能是各个瓶子的size
     * 大小或者0。如果我们想要得到的result大小不是0，也不是任何一个瓶子的size大小，我们在过程中，必然会从一个瓶子向另外一个瓶子倒水。
     *
     * 现在，我们只谈论result为不满不空的值的情况。
     *
     * 两个瓶子同时都是灌满状态，是没有意义的，这样，只有一条路可选，必然会倒光一个瓶子，然后从另一个满瓶子向空瓶子倒水。 两个瓶子都是空的状态，也是没有意义的，这样，只有一条路可选，必然会灌满一个瓶子，然后将水倒向另外一个空瓶子。
     *
     * 1：当我们从一个小的满瓶子倒向一个大的空瓶子，第一次得到大瓶子不满不空状态，小瓶子为空； 2：当我们从一个大的满瓶倒向一个小的空瓶子，第一次得到大瓶子的不满不空状态，小瓶子为满；
     *
     *
     * 而通过灌满和倒光两个行为，得到的要么是满，要么是空，如果我们希望瓶子中出现不满不空的情况，一定是从一个瓶子向对方的瓶子 倒水导致的（因为前面已经讨论了，假如不向对方倒水，只能是满或者空，绝不可能出现不满不空），第一次有一个瓶子出现不满不空出现的原因，
     * 是因为一方向另一方倒水所致，我们知道，在倒水的过程中，停止的条件是，对方满，或者我方空，如果对方不满，我方不空，倒水不会停止，因此，当我们向对方倒 完水之后，一定是对方满了，
     * 或者我方空了。
     *
     * 因此，又得到了一个非常重要的结论，任何时候，绝不存在两个瓶子同时不满不空出现。这个结论，是我们算法实现的关键。
     *
     * 结合上面的讨论：我们进一步得出任何时候两个瓶子的状态： 1满2满 ：下一步，必须倒空其中一个瓶子 1满2空：下一步：将1中的水倒向2 1满2不满不空：有待讨论 1不满不空2满：有待讨论
     * 1不满不空2空：有待讨论 1空2空：下一步， 必须灌满其中的一个瓶子 1空2满：下一步，必须将2中的水倒向1 1空2不满不空：有待讨论
     *
     *
     * 我们来讨论1满2不满不空的情况： 下一步该怎么做呢？在这种情况下，将2灌满或者排空，都是没有意义的，这样，就导致1满2满或者1满2空的情况，前面已经给出了这样的情况下的解决办法了，现在1已经是满的，,2中不满不空的水，
     * 也不能倒向1，因此，2瓶子只能啥也不做。1瓶子是满的，显然没法继续灌满，因此，这个时候，下一步有两种做法，第一，将1瓶子排空，变成1空2不满不空的情况，但是要注意，如果1满的原因，就是
     * 因为灌满的，显然不能继续排空了只能走第二条路；第二种情况，将1倒入2，但是我们也要注意，如果1满的原因，是此前2向1倒水所致，现在再倒回去，也是没有意义的！只能走第一种情况了！
     *
     * 我们来讨论1空2不满不空的情况： 和上面的讨论类似，2灌满或者排空都是没有意义的，1已经是空的，也无法继续排空了，也无法向2倒水，这个时候，只有两条路可走，第一，1灌满，变成1满2不满不空的情况,但是这里我们要判断一下
     * 1空的原因，如果1本身就是排空的，就不能再灌满只能走第二条路；第二，将2中的水倒向1，但是如果1空的原因，就是因为此前1向2倒水导致1空，现在再倒回去也是毫无意义的，只能走第一条路！！
     *
     * 剩下的1不满不空2满，和1不满不空2空，和上面的讨论类似。
     *
     *
     * 这就是整个算法的精华所在。
     */

    public void processStartWithBottle1() {
        if (result == 0) {
            return;
        }

        if (result == bottle1.getSize() + bottle2.getSize()) {
            bottle1.fillUp();
            bottle2.fillUp();
            return;
        }

        while (true) {
            if (bottle1.isEmpty() && bottle2.isEmpty()) {
                bottle1.fillUp();
            } else if (bottle1.isFull() && bottle2.isFull()) {
                bottle2.pourUp();
            } else if (bottle1.isFull() && bottle2.isEmpty()) {
                bottle1.pourToTheOtherBottle(bottle2);
            } else if (bottle1.isEmpty() && bottle2.isFull()) {
                bottle2.pourToTheOtherBottle(bottle1);
            } else if (bottle1.isFull() && bottle2.isNotFullAndNotEmpty()) {
                if (bottle1.getFullReason() == 1) {
                    bottle1.pourToTheOtherBottle(bottle2);
                } else {
                    bottle1.pourUp();
                }
            } else if (bottle1.isNotFullAndNotEmpty() && bottle2.isFull()) {
                if (bottle2.getFullReason() == 1) {
                    bottle2.pourToTheOtherBottle(bottle1);
                } else {
                    bottle2.pourUp();
                }
            } else if (bottle1.isNotFullAndNotEmpty() && bottle2.isEmpty()) {
                if (bottle2.getEmptyReason() == 1) {
                    bottle1.pourToTheOtherBottle(bottle2);
                } else {
                    bottle2.fillUp();
                }
            } else if (bottle1.isEmpty() && bottle2.isNotFullAndNotEmpty()) {
                if (bottle1.getEmptyReason() == 1) {
                    bottle2.pourToTheOtherBottle(bottle1);
                } else {
                    bottle1.fillUp();
                }
            }

            if (reachToResult()) {
                break;
            }
        }
    }

    private boolean reachToResult() {
        return bottle1.getUsage() == result || bottle2.getUsage() == result
                || bottle1.getUsage() + bottle2.getUsage() == result;
    }
}
