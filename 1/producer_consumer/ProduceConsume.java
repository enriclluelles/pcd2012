import java.util.Random;

class ProduceConsume {
  private static final int PRODUCERS = 2;
  private static final int CONSUMERS = 2;
  private static final int CONSUMERPRODUCERS = 0;
  private static final int STACK_SIZE = 30;
  private static final int UNIVERSE_SIZE = 200;
  public static void main(String args[]) {
    MonitoredStack s;
    ProducerConsumer pc;
    Producer p;
    Consumer c;
    Object printerLock = new Object();
    Random r = new Random();
    Integer[] integers = new Integer[UNIVERSE_SIZE];
    for(int i = 0; i < UNIVERSE_SIZE; i++) {
      integers[i] = new Integer(r.nextInt());
    }
    Double[] doubles = new Double[UNIVERSE_SIZE];
    for(int i = 0; i < UNIVERSE_SIZE; i++) {
      doubles[i] = new Double(r.nextDouble());
    }
    int max = 9;

    s = new MonitoredStack("Stack 0", STACK_SIZE, printerLock);

    for (int j = 0; j < PRODUCERS; j++) {
      p = new Producer(s, 6, doubles, Double.class.getName());
      (new Thread(p)).start();
    }

    for (int j = 0; j < PRODUCERS; j++) {
      p = new Producer(s, 6, integers, Integer.class.getName());
      (new Thread(p)).start();
    }

    for (int j = 0; j < CONSUMERS; j++) {
      c = new Consumer(s, 2, doubles, Double.class.getName());
      (new Thread(c)).start();
    }

    for (int j = 0; j < CONSUMERS; j++) {
      c = new Consumer(s, 3, integers, Integer.class.getName());
      (new Thread(c)).start();
    }

    for (int j = 0; j < CONSUMERPRODUCERS; j++) {
      pc = new ProducerConsumer(s, 1, doubles, Double.class.toString());
      (new Thread(pc)).start();
    }
  }
}
