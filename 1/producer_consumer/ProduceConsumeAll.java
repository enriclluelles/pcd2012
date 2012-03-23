import java.util.Random;

class ProduceConsumeAll {
  private static final int PRODUCERS = 2;
  private static final int CONSUMERS = 2;
  private static final int CONSUMERPRODUCERS = 2;
  private static final int STACKS = 1;
  public static void main(String args[]) {
    StackWithPrint s;
    Producer p;
    Consumer c;
    ProducerConsumer pc;
    Object printerLock = new Object();
    Random r = new Random();
    int max = 9;

    for (int i = 0; i < STACKS; i++) {
      s = new StackWithPrint("Stack " + i, printerLock);

      for (int j = 0; j < PRODUCERS; j++) {
        p = new Producer(s, r.nextInt(max) + 1);
        (new Thread(p)).start();
      }

      for (int j = 0; j < CONSUMERS; j++) {
        c = new Consumer(s, r.nextInt(max) + 1);
        (new Thread(c)).start();
      }

      for (int j = 0; j < CONSUMERPRODUCERS; j++) {
        pc = new ProducerConsumer(s, r.nextInt(max) + 1);
        (new Thread(pc)).start();
      }
    }

  }
}
