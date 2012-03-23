import java.util.Random;
public class ProducerConsumer implements Runnable {
  protected StackWithPrint s;
  protected int howMany;
  protected Random r;
  protected static final int MAX = 32;

  ProducerConsumer(StackWithPrint stack, int howMany) {
    r = new Random();
    this.howMany = howMany;
    s = stack;
  }

  public boolean canConsume () {
    return s.size() >= howMany;
  }

  public void consume() {
    for(int i = 0; i < howMany; i++) {
      s.pop();
    }
    s.print();
  }

  public boolean canProduce () {
    return MAX - s.size() >= howMany;
  }

  public void produce() {
    for(int i = 0; i < howMany; i++) {
      s.push(r.nextInt());
    }
    s.print();
  }

  public void run() {
    while (true) {
      synchronized(s) {
        while(!canConsume()) {
          while(!canProduce()) {
            System.out.println("a");
            try {s.wait();} catch (InterruptedException e) {}
          }
          produce();
        }
        consume();
        s.notifyAll();
      }
    }
  }
}
