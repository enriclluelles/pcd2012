class Factorial {

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int f = n;

    while (n > 1) {
      n--;
      f *= n;
    }

    System.out.println(f);
    return;
  }
}
