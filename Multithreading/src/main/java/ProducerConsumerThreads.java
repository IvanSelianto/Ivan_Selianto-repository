import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerThreads {
    public static Queue<Integer> products = new LinkedList();

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        sleep(1000);
        System.out.println("Queue size is: " + products.size());
    }





    public static class Producer implements Runnable {

        public void run() {

            for (int i = 0; i < 100; i++) {

                products.add(i + 5);

            }

        }

    }



    public static class Consumer implements Runnable {



        public void run() {

            while (!products.isEmpty()) {

                System.out.println(products.poll());

            }

        }

    }



    public static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

