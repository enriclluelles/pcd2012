import java.util.Stack;

class StackWithPrint extends Stack {
  private String name;
  private Object printerLock;
  public synchronized void print() {
    synchronized(printerLock) {
    try {Thread.sleep(50);} catch (InterruptedException e) {}
      System.out.println(((char) 27)+"[2J");
      System.out.println("\n\nSize: " + size());
      System.out.println("==========================\n\n\n");
      for(int i = 0; i < size(); i++) {
        System.out.println(i + ": " + elementAt(i));
      }
      System.out.println(name);
    }
  }

  StackWithPrint(String name, Object printerLock) {
    this.name = name;
    this.printerLock = printerLock;
  }
}
