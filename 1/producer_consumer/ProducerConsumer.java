import java.util.Random;
public class ProducerConsumer implements Runnable {
  protected MonitoredStack<Integer> s;
  protected int howMany;
  protected Random r;

  ProducerConsumer(MonitoredStack<Integer> stack, int howMany) {
    r = new Random();
    this.howMany = howMany;
    s = stack;
  }

  public void produce() {
    Integer[] toPush = new Integer[howMany];
    for(int i = 0; i < howMany; i++) {
      toPush[i] = new Integer(r.nextInt());
    }
    s.push(toPush);
  }

  public void consume() {
    s.pop(howMany);
  }

  public void run() {
    while (true) {
      produce();
      consume();
    }
  }
}
