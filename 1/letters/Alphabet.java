class Alphabet {
  public static String letters() {
    return letters('d');
  }

  public static String letters(char end) {
    char c = 'a';
    int size = end - c + 1;
    char[] aux = new char[size];

    for(int i = 0; i < size; i++)
      aux[i] = c++;

    return new String(aux);
  }
}
