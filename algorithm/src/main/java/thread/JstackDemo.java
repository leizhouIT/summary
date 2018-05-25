package thread;

import java.util.Map;

/**
 * Created by zhoulei8 on 2018/1/2.
 * 模拟 jstack名字生产线程栈信息
 */
public class JstackDemo {

    public static void main(String[] args) {
        for (Map.Entry<Thread, StackTraceElement[]> threadEntry : Thread.getAllStackTraces().entrySet()) {
            Thread thread = threadEntry.getKey();
            StackTraceElement[] stack = threadEntry.getValue();
            if (thread.equals(Thread.currentThread())){
                continue;
            }
            System.out.println("\n线程： "+thread.getName()+"\n");
            for (StackTraceElement stackTraceElement : stack) {
                System.out.println("\t"+stackTraceElement+"\n");
            }
        }
    }
}
