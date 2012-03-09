import java.util.Random;
class BST<T extends Comparable<T>>{
  class Node<T extends Comparable<T>>{
    public Node<T> left;
    public Node<T> right;
    public T value;

    Node(T val) {
      value = val;
    }

    private void insert(T el) {
      if (el.compareTo(value) < 0) {
        if (left == null) {
          left = new Node<T>(el);
        } else {
          left.insert(el);
        }
      }
      if (el.compareTo(value) > 0) {
        if (right == null) {
          right = new Node<T>(el);
        } else {
          right.insert(el);
        }
      }
    }

    private void print() {
      if (left != null)
        left.print();
      System.out.print(" " + value);
      if (right != null)
        right.print();
    }

  }

  public Node<T> root;

  public void insert(T el) {
    if (root == null) {
      root = new Node<T>(el);
    } else {
      root.insert(el);
    }
  }

  public void print() {
    if (root != null) {
      root.print();
    }
  }

  public static <T extends Comparable<T>> void inord_traverse(BST<T> tree) {
    tree.print();
  }

  public static <T extends Comparable<T>> BST<T> create_empty() {
    return new BST<T>();
  }

  public static <T extends Comparable<T>> boolean is_empty(BST<T> tree) {
    return tree.root == null;
  }

  public static <T extends Comparable<T>> void insert(BST<T> tree, T el) {
    tree.insert(el);
  }

  public static void main(String args[]) {
    BST<Double> t = BST.create_empty();

    System.out.println("Is empty? " + is_empty(t));

    System.out.print("Inserting elements into the tree");

    Random r = new Random();
    for (int i = 0; i < 10; i++) {
      insert(t, r.nextDouble() * 1000);
      System.out.print(".");
    }
    System.out.println();

    System.out.println("Is empty? " + is_empty(t));

    System.out.print("Traversed tree: ");

    inord_traverse(t);
    System.out.println();
  }
}
