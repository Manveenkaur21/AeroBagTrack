package service;

public class BaggageTask implements Runnable {
    private static int completedTasks;

    @Override
    public void run() {
        synchronized (BaggageTask.class) {
            completedTasks++;
            System.out.println(Thread.currentThread().getName()
                    + " processed a baggage task. Total: " + completedTasks);
        }
    }

    public static synchronized int getCompletedTasks() { return completedTasks; }
}
