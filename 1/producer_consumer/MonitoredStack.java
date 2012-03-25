import java.util.Stack;

/* This a special stack where you cannot insert stuff if the stack is full
 */
class MonitoredStack<T> extends Stack<T> {
  private String name;
  private Object printerLock;
  private int maxSize;

  /* As we don't want two stack being printed at the same time
   * we need to synchronize the printing */
  public void print() {
    synchronized(printerLock) {
    try {Thread.sleep(500);} catch (InterruptedException e) {}
      System.out.println(((char) 27)+"[2J");
      System.out.println("\n\nSize: " + size());
      System.out.println("==========================\n\n\n");
      for(int i = 0; i < size(); i++) {
        System.out.println(i + ": " + elementAt(i));
      }
      System.out.println(name);
    }
  }

  MonitoredStack(String name, int maxSize, Object printerLock) {
    this.name = name;
    this.maxSize = maxSize;
    this.printerLock = printerLock;
  }

  public synchronized void push(T[] toPush) {
    while (this.size() >= this.maxSize) {
      try{ wait(); } catch(InterruptedException e) {};
    }
    notifyAll();
    for(int i = 0; i < toPush.length; i++) {
      super.push(toPush[i]);
    }
    print();
  }

  public synchronized T[] pop(int times) {
    while (this.size() < times) {
      try{ wait(); } catch(InterruptedException e) {};
    }
    @SuppressWarnings("unchecked")
    T[] res = (T[])new Object[times];
    for(int i = 0; i < times; i++) {
      res[i] = super.pop();
    }
    notifyAll();
    print();
    return res;
  }
}
