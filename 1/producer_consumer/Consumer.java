public class Consumer extends ProducerConsumer {

  Consumer(MonitoredStack stack, int howMany, Object[] universe) {
    super(stack, howMany, universe);
  }

  Consumer(MonitoredStack stack, int howMany, Object[] universe, String productClass) {
    super(stack, howMany, universe, productClass);
  }

  public void run() {
    while (true) {
      consume();
    }
  }
}
