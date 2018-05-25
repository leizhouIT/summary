package thread;

/**
 * Created by zhoulei8 on 2017/3/21.
 */
public class Example {

    static class E1{
        int i=5;
        public int printA(){
            return i;
        }
    }
    static class E2 extends E1{
        int i=10;
        public int printB(){
            return i;
        }

        public void printC(){
            System.out.println(super.i);
        }
    }

    public static void main(String[] args) {
        E1 e1 = new E1();
        E2 e2 = new E2();
        e2.printC();
        System.out.println(e2.printB());
        System.out.println(e1.printA());
        System.out.println(e2.printA());

        E1 e3 = (E1)e2;
        System.out.println(e3.printA());

    }
}
