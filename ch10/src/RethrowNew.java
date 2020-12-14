//: RethrowNew.java
// Rethrow a different object from the one that
// was caught

public class RethrowNew {
    public static void f() throws Exception {
        System.out.println(
                "originating the exception in f()");
        throw new Exception("thrown from f()");
    }
    public static void main(String[] args) {
        try {
            f();
        } catch(Exception e) {
            System.out.println(
                    "Caught in main, e.printStackTrace()");
            e.printStackTrace();
            throw new NullPointerException("from main");
        }
    }
} ///:~