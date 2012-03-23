public class Producer extends ProducerConsumer implements Runnable {

  Producer(StackWithPrint stack, int howMany) {
    super(stack, howMany);
  }

  public void run () {
    while (true) {
      synchronized(s) {
        while (!canProduce()) {
          try {s.wait();} catch (InterruptedException e) {}
        }
        produce();
        s.notifyAll();
      }
    }
  }
}
