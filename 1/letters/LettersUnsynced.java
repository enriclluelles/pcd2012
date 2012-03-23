class LettersUnsynced {
  public static void main (String[] args) {
    String letters = Alphabet.letters('d');

    for(int i = 0; i < letters.length(); i++) {
      (new StringWriter(letters.substring(i,i + 1))).startWriting();
    }
  }
}
