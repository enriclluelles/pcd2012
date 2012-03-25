import java.util.Random;

class ProduceConsumeMore {
  private static final int PRODUCERS = 2;
  private static final int CONSUMERS = 2;
  private static final int CONSUMERPRODUCERS = 1;
  private static final int STACKS = 1;
  public static void main(String args[]) {
    MonitoredStack<Integer> s;
    ProducerConsumer pc;
    Object printerLock = new Object();
    Random r = new Random();
    int max = 9;

    for (int i = 0; i < STACKS; i++) {
      s = new MonitoredStack<Integer>("Stack " + i, r.nextInt(max) + 1, printerLock);

      for (int j = 0; j < CONSUMERPRODUCERS; j++) {
        pc = new ProducerConsumer(s, r.nextInt(max) + 1);
        (new Thread(pc)).start();
      }
    }

  }
}
