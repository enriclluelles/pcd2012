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
      System.out.println("\n\nSize: " + size() + "  Max Size: " + maxSize);
      System.out.println("==========================\n\n\n");
      for(int i = 0; i < size(); i++) {
        System.out.println(i + ": (" + elementAt(i).getClass().getName() + ") " + elementAt(i));
      }
      System.out.println(name);
    }
  }

  MonitoredStack(String name, int maxSize, Object printerLock) {
    this.name = name;
    this.maxSize = maxSize;
    this.printerLock = printerLock;
  }

  private boolean checkType(String klass, int howMany) {
    if (howMany > size())
      return false;
    boolean b = true;
    for(int i = size() - howMany; i < size(); i++) {
      b = b && (elementAt(i).getClass().getName() == klass);
    }
    return b;
  }

  public synchronized void push(T[] toPush) {
    while (this.size() + toPush.length > this.maxSize) {
      try{ wait(); } catch(InterruptedException e) {};
    }
    realPush(toPush);
  }

  public synchronized void realPush(T[] toPush) {
    for(int i = 0; i < toPush.length; i++) {
      super.push(toPush[i]);
    }
    notifyAll();
    print();
  }

  public synchronized void tryToPush(T[] toPush) {
    if (this.size() + toPush.length > this.maxSize) {
      return;
    }
    realPush(toPush);
  }

  public synchronized T[] tryToPop(int times) {
    if (this.size() < times) {
      return null;
    }
    return realPop(times);
  }

  public synchronized T[] tryToPop(int times, String klass) {
    if (!checkType(klass, times)) {
      return null;
    }
    return realPop(times);
  }

  public synchronized T[] pop(int times, String klass) {
    while (!checkType(klass, times)) {
      try{ wait(); } catch(InterruptedException e) {};
    }
    return realPop(times);
  }

  public synchronized T[] pop(int times) {
    while (this.size() < times) {
      try{ wait(); } catch(InterruptedException e) {};
    }
    return realPop(times);
  }

  public synchronized T[] realPop(int times) {
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
