public class Consumer extends ProducerConsumer implements Runnable {
  Consumer(StackWithPrint stack, int howMany) {
    super(stack, howMany);
  }


  public void run () {
    while (true) {
      synchronized(s) {
        while (!canConsume()) {
          try {s.wait();} catch (InterruptedException e) {}
        }
        consume();
        s.notifyAll();
      }
    }
  }
}
