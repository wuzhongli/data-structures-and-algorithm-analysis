public class Test {

    public static void main(String[] args) {
        Bottle bottle1 = new Bottle("5升水瓶", 5);
        Bottle bottle2 = new Bottle("6升水瓶", 6);
        WaterProblemContainer container = new WaterProblemContainer(bottle1, bottle2, 3);
        container.processStartWithBottle1();
    }

}
