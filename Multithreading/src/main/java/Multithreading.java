
import java.util.concurrent.TimeUnit;

public class Multithreading {
    public static void main(String[] args) {
        Multithreading multithreading = new Multithreading();
        multithreading.writingParallelThread();


    }

    public static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void writingParallelThread() {
        WritingRunnable writingRunnable = new WritingRunnable();
        Thread thread = new Thread(writingRunnable);

        thread.start();

    }


    static class WritingRunnable implements Runnable {

        public void run() {
            while (true) {
                sleep(5000);

                System.out.println("Прошло 5 секунд");
            }
        }
    }


}

