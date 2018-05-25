package thread;


import java.io.*;

/**
 * Created by zhoulei8 on 2017/12/27.
 */
public class Demo1 implements Serializable{


    private static class inn{
        private static final Demo1 DEMO_1 = new Demo1();
    }
    public static Demo1 getInstance(){
        return inn.DEMO_1;
    }


    private Demo1(){}

    protected Object readResolve() throws ObjectStreamException{
        return inn.DEMO_1;
    }


    public static void main(String[] args) throws Exception {
        Demo1 demo1 = Demo1.getInstance();
        FileOutputStream fos = new FileOutputStream(new File("d:/aa.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(demo1);
        oos.close();
        fos.close();
        System.out.println(demo1.hashCode());

        FileInputStream fis = new FileInputStream(new File("d:/aa.txt"));
        ObjectInputStream ois = new ObjectInputStream(fis);
        Demo1 o = (Demo1) ois.readObject();
        ois.close();
        fis.close();
        System.out.println(o.hashCode());
    }






    /*private boolean isContinueRun = true;

    public void runMethod() {
        String anyString = new String();
        while (isContinueRun == true) {
//            synchronized (anyString) {
//            }
        }
        System.out.println("stop ！");
    }

    public void stopMethod() {
        isContinueRun = false;
    }*/
}
