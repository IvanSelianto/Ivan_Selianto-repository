package TaskFive;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PullQueue {
    private List<Queue<String>> queues;

    private final Random RANDOM = new Random();



    public PullQueue(){

        queues = new ArrayList();

        for (int i = 0; i < 10; i++) {

            queues.add(generateQueue());

        }

    }







    public Queue<String> getRandomQueue(){

        return queues.get(RANDOM.nextInt(10));

    }



    public boolean isQueuesEmpty(){

        Iterator<Queue<String>> iterator = queues.iterator();
        while (iterator.hasNext()) {
            Queue<String> queue = iterator.next();

            if (!queue.isEmpty()) {

                return false;
            }

        }

        return true;

    }
    private Queue<String> generateQueue() {

        Queue<String> queue = new ConcurrentLinkedQueue();

        for (int i = 0; i < 100; i++) {

            queue.add("Пользователь номер: " + i + "Очередь: " + RANDOM.nextInt(100));

        }

        return queue;

    }


}
