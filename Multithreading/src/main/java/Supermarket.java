import java.util.*;
import java.util.concurrent.*;

public class Supermarket {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Queue<String>> listOfQueues = new ArrayList();

        for (int i = 0; i < 10; i++) {
            listOfQueues.add(generateQueue());
        }


        long before = System.currentTimeMillis();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<List<String>> future = executorService.submit(new Serve(listOfQueues));
        List<String> users = future.get();

        long after = System.currentTimeMillis();

        System.out.println("Time of executing is: " + (after - before) + " milliseconds");
        System.out.println("Count of users is: " + users.size());

        executorService.shutdown();
    }





    public static class Serve implements Callable<List<String>> {

        private List<Queue<String>> queueList;


        public Serve(List<Queue<String>> queueList) {

            this.queueList = queueList;

        }

        public List<String> call() throws Exception {

            List<String> list = new ArrayList();


            for (int i = 0; i < queueList.size(); i++) {

                while (!queueList.get(i).isEmpty()) {

                    System.out.println(queueList.get(i).element() + " thread: " + Thread.currentThread().getId());

                    list.add(queueList.get(i).poll());

                    TimeUnit.MILLISECONDS.sleep(10);

                }

            }

            return list;

        }

    }
    private static Queue<String> generateQueue() {
        final Random RANDOM = new Random();

        Queue<String> queue = new LinkedList();


        Integer numberOfQueue = RANDOM.nextInt(100);

        for (int i = 0; i < 100; i++) {

            queue.add("user number: " + (i + 1) + "queue:  " + numberOfQueue);

        }

        return queue;

    }

}

