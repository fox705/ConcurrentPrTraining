package ProducerCustomer;



public class ProducerCustomer {

     private static int[] magazine;
     private static int count;

     private static Object lock = new Object();

    public static class Producer {


        public void produce()  {
            synchronized (lock){
                if(isFull(magazine)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                magazine[count++] = 1;
                lock.notify();
            }


            
        }

    }

    public static class Customer{

            public void consume()  {
                synchronized (lock){
                    if(isEmpty(magazine)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    magazine[--count] = 0;
                    lock.notify();
                }


        }

    }

     static boolean isFull(int[] magazine) {

        return count == magazine.length;
    }

     static boolean isEmpty(int[] magazine){
        return count == 0;
    }



public static void main(String[] args) throws InterruptedException {

        magazine = new int[10];
        count = 0;

     Producer producer = new Producer();
     Customer customer = new Customer();

    Runnable produce = () -> {
        for(int i = 0; i < 50; i++){
            producer.produce();

        }
        System.out.println("Produce is done");
    };

    Runnable consume = () -> {
        for(int i =0; i< 40; i++){
            customer.consume();
        }
        System.out.println("Produce is done");
    };

    Thread t1 = new Thread(produce);
    Thread t2 = new Thread(consume);

    t1.start();
    t2.start();

    t1.join();
    t2.join();


    System.out.println("Products in magazine:" + count);
}
}
