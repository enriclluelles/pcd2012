import java.util.Random;
public class ProducerConsumer implements Runnable {
  protected MonitoredStack s;
  protected int howMany;
  protected Random r;
  protected Object[] universe;
  protected String productClass = null;

  ProducerConsumer(MonitoredStack stack, int howMany, Object[] universe, String productClass) {
    this(stack, howMany, universe);
    this.productClass = productClass;
  }

  ProducerConsumer(MonitoredStack stack, int howMany, Object[] universe) {
    r = new Random();
    this.howMany = howMany;
    this.s = stack;
    this.universe = universe;
  }

  public Object generateElement() {
    return universe[r.nextInt(universe.length)];
  }

  public void produce() {
    Object[] toPush = new Object[howMany];
    for(int i = 0; i < howMany; i++) {
      toPush[i] = generateElement();
    }
    s.push(toPush);
  }

  public void consume() {
    if (productClass == null) {
      s.pop(howMany);
    } else {
      s.pop(howMany, productClass);
    }
  }

  public void consumeOrProduce() {
    Object res;
    if (productClass == null) {
      res = s.tryToPop(howMany);
    } else {
      res = s.tryToPop(howMany, productClass);
    }
    if (res == null) {
      Integer[] toPush = new Integer[howMany];
      for(int i = 0; i < howMany; i++) {
        toPush[i] = new Integer(r.nextInt());
      }
      s.tryToPush(toPush);
    }
  }

  public void run() {
    while (true) {
      consumeOrProduce();
    }
  }
}
