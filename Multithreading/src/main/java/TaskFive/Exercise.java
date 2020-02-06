package TaskFive;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class Exercise  implements Callable<List<String>>{
    private List users;

    private PullQueue pullQueue;

    private Queue<String> queue;
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        PullQueue pullQueue = new PullQueue();

        List<String> userNameList = new ArrayList();



        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Exercise> exercises = new ArrayList();

        for (int i = 0; i < 10; i++) {

            exercises.add(new Exercise(pullQueue));

        }



        long before = System.currentTimeMillis();

        List<Future<List<String>>> futures = executorService.invokeAll(exercises);

        for (Future<List<String>> future : futures) {

            userNameList.addAll(future.get());

        }

        long after = System.currentTimeMillis();



        System.out.println("Count of usernames is: " + userNameList.size());

        System.out.println("Time of executing is: " + (after - before) + " millis");

        executorService.shutdown();

    }




    public Exercise(PullQueue pullQueue) {

        this.pullQueue = pullQueue;

        users = Collections.synchronizedList(new ArrayList());

        queue = pullQueue.getRandomQueue();

    }


    public List<String> call() throws InterruptedException {

        while (!pullQueue.isQueuesEmpty()) {

            if (queue.isEmpty()) {
                queue = pullQueue.getRandomQueue();
            }

            String poll = queue.poll();

            if (poll != null) {

                users.add(poll);

                System.out.println(poll + " сделано потоком: " + Thread.currentThread().getId());

            }

            TimeUnit.MILLISECONDS.sleep(5);

        }

        return users;

    }
}
