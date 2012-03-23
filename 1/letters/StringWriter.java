class StringWriter {
  static private final int TIMES = 100;
  static int counter = 0;

  class LetterWriter implements Runnable {
    int position;
    StringWriter sw;

    LetterWriter(int pos, StringWriter sw) {
      this.position = pos;
      this.sw = sw;
    }

    public void run () {
      while (!done()) {
        sw.printChar(position);
      }
    }
  }

  String letters;
  int nextLetter;


  StringWriter(String letters) {
    this.letters = letters;
    this.nextLetter = 0;
  }

  public static synchronized boolean done () {
    return TIMES <= counter;
  }

  public static synchronized void inc () {
    counter++;
  }

  public synchronized void printChar(int pos) {
    if (pos == nextLetter) {
      System.out.print(letters.charAt(pos));
      nextLetter = (nextLetter + 1) % letters.length();
      inc();
      notifyAll();
    } else {
      try {
        wait();
      } catch (InterruptedException e) {}
    }
  }

  public void startWriting() {
    for(int i = 0; i < letters.length(); i++) {
      (new Thread( new LetterWriter(i, this) )).start();
    }
  }
}
