class LettersSynced {

  public static void main (String[] args) {
    String letters = Alphabet.letters();

    (new StringWriter(letters)).startWriting();
  }
}
