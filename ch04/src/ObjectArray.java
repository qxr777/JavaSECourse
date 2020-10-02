class Value {
    int i;
}

public class ObjectArray {

    public static void main(String[] args) {
        Value[] av = new Value[(int)(Math.random() * 10) + 1];
        for (int i = 0; i < av.length; ++i) {
            av[i] = new Value();
            av[i].i = (int)(Math.random() * 100);
        }
        for (Value v : av) {
            System.out.println(v.i);
//            Math.abs(v.i);
//            Integer.parseInt("123");
//            int m = Integer.MAX_VALUE;
        }
    }

}
