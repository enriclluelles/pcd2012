import java.util.Random;
class BSTThreads implements Runnable{

  BST<Double> bst;

  BSTThreads(BST<Double> bst) {
    this.bst = bst;
  }

  public void run () {
    Random r = new Random();
    for (int i = 0; i < 10; i++) {
      bst.insert(r.nextDouble() * 1000);
    }
    bst.print();
  }


  public static void main(String args[]) {
    BST<Double> t = BST.create_empty();

    for (int i = 0; i < 6; i++) {
      (new Thread(new BSTThreads(t))).start();
    }
  }
}
