import java.util.Random;

class QuickSort<T extends Comparable<T>> {

  public static void printArray(Object[] a, int from, int to) {
    System.out.print("[");
    for(int i = from; i < to; i++) {
      System.out.print(a[i]);
      if (i != to - 1)
        System.out.print(",");
    }
    System.out.println("]");
  }

  public static void printArray(Object[] a) {
    printArray(a, 0, a.length);
  }

  public void swap (T[] a, int pos1, int pos2) {
    T aux = a[pos1];
    a[pos1] = a[pos2];
    a[pos2] = aux;
    return;
  }

  public void qsort (T[] to_sort, int from, int to) {
    if (from >= to)
      return;

    int range = to - from;
    int i = from;
    int j = to;

    int rd = Math.abs(new Random().nextInt() % range) + from;

    swap(to_sort, from, rd);

    T pivot = to_sort[from];

    //Position indexes and actual swapping
    for(;;) {
      do i++; while (i < to && to_sort[i].compareTo(pivot) < 0);
      do j--; while (to_sort[j].compareTo(pivot) > 0);
      if (j < i) break;
      swap(to_sort, i, j);
    }

    swap(to_sort, from, j);

    //We've got the array split between the pivot

    //Then we sort the two parts
    qsort(to_sort, from, j);
    qsort(to_sort, j + 1, to);

    return;
  }


  public static void main(String args[]) {

    //wrap the arrays
    Integer[] iv = new Integer[10];
    Integer[] iv2 = new Integer[50];
    Integer[] iv3 = new Integer[100];

    Random r = new Random();
    for (int i = 0; i < iv.length; i++)
      iv[i] = new Integer(r.nextInt() % 1000);
    for (int i = 0; i < iv2.length; i++)
      iv2[i] = new Integer(r.nextInt() % 1000);
    for (int i = 0; i < iv3.length; i++)
      iv3[i] = new Integer(r.nextInt() % 1000);


    //Sorting
    QuickSort<Integer> q = new QuickSort<Integer>();
    q.qsort(iv, 0, iv.length);
    q.qsort(iv2, 0, iv2.length);
    q.qsort(iv3, 0, iv3.length);

    //Print results
    printArray(iv);
    printArray(iv2);
    printArray(iv3);

    //Now, let's go for the doubles
    Double[] dv = new Double[10];
    Double[] dv2 = new Double[50];
    Double[] dv3 = new Double[100];

    for (int i = 0; i < dv.length; i++)
      dv[i] = new Double(r.nextDouble() * 1000);
    for (int i = 0; i < dv2.length; i++)
      dv2[i] = new Double(r.nextDouble() * 1000);
    for (int i = 0; i < dv3.length; i++)
      dv3[i] = new Double(r.nextDouble() * 1000);

    QuickSort<Double> q2 = new QuickSort<Double>();
    q2.qsort(dv, 0, dv.length);
    q2.qsort(dv2, 0, dv2.length);
    q2.qsort(dv3, 0, dv3.length);

    printArray(dv);
    printArray(dv2);
    printArray(dv3);
  }
}
