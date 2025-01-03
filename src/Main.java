import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        memoryTrackForJvm();
        cpuTrackingForJvm();
    }
    public static void memoryTrackForJvm(){
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.printf("Initial memory request for start up: %.2f GB%n", (double) memoryMXBean.getHeapMemoryUsage().getInit() / 1073741824);
        System.out.printf("Maximum memory available for JVM: %.2f GB%n", (double) memoryMXBean.getHeapMemoryUsage().getMax() / 1073741824);
        System.out.printf("Current memory used by JVM: %.2f GB%n", (double) memoryMXBean.getHeapMemoryUsage().getUsed() / 1073741824);
        System.out.printf("Commited memory guaranteed to be available to the JVM: %.2f GB%n", (double) memoryMXBean.getHeapMemoryUsage().getCommitted() / 1073741824);
    }
    public static void cpuTrackingForJvm(){
        System.out.println("=========");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        for (Long threadId : threadMXBean.getAllThreadIds()) {
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
            System.out.println("Thread name: " + threadInfo.getThreadName());
            System.out.println("Thread state: " + threadInfo.getThreadState());
            System.out.println("CPU time: " + threadMXBean.getThreadCpuTime(threadId) +  " ns");
        }
    }
}