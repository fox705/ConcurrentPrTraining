package ConcurrentTraining;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        IntWrapper intWrapper = new IntWrapper(0);

        Runnable r = () -> {

            for (int i = 0; i < 1000; i++) {
                intWrapper.incrementI();

            }
        };

        Thread[] threads = new Thread[1000];

        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(r);
            threads[i].start();
        }

        for(int i = 0; i < threads.length; i++){
            threads[i].join();
        }

        //t1.join(); //Why without this line the result is still 1.

        System.out.println( "\n Finall result is: " + intWrapper.getI());

    }


}
