import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;


public class Poker {

    private static CyclicBarrier barrier = new CyclicBarrier(6);


    public static void main(String[] args) {

        while (true) {

            new Thread(new joinPoker(barrier)).start();

            sleep(1000);

        }

    }


    public static class joinPoker implements Runnable {

        CyclicBarrier barrier;

        public joinPoker(CyclicBarrier cyclicBarrier) {
            this.barrier = cyclicBarrier;

        }

        public void run() {

            try {

                barrier.await();

            } catch (Exception e) {

                e.printStackTrace();

            }

            System.out.println("Игра Началась!");

        }

    }

    private static void sleep(int milliseconds) {

        try {

            TimeUnit.MILLISECONDS.sleep(milliseconds);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

}
