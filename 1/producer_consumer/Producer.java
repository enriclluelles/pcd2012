public class Producer extends ProducerConsumer {

  Producer(MonitoredStack stack, int howMany, Object[] universe) {
    super(stack, howMany, universe);
  }

  Producer(MonitoredStack stack, int howMany, Object[] universe, String productClass) {
    super(stack, howMany, universe, productClass);
  }

  public void run() {
    while (true) {
      produce();
    }
  }
}
