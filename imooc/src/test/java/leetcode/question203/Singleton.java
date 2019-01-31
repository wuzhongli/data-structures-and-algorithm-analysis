package leetcode.question203;

public class Singleton {

    public static int count3;
    public static int i;

    static {
        System.out.println(i);
    }

    static {
        i = 2;
    }

    public static int count4 = 4;
    private static Singleton singleton = new Singleton();
    public static int count1;
    public static int count2 = 0;

    static {
        System.out.println(singleton);
    }

    private Singleton() {
        count1++;
        count2++;
        count3++;
        count4++;
    }

    public static Singleton getInstance() {
        return singleton;
    }

    public static void main(String[] args) {
//        Singleton singleton = Singleton.getInstance();
//        System.out.println("count1=" + singleton.count1);
//        System.out.println("count2=" + singleton.count2);
//        System.out.println("count3=" + singleton.count3);
//        System.out.println("count4=" + singleton.count4);
    }
}
