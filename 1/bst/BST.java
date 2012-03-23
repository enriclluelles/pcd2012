import java.util.Random;
class BST<T extends Comparable<T>>{
  class Node<T extends Comparable<T>>{
    public Node<T> left;
    public Node<T> right;
    public T value;

    Node(T val) {
      value = val;
    }

    private synchronized void insert(T el) {
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

    private synchronized void print() {
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
    System.out.println();
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
}
