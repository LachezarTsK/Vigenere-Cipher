package main.vigenereCipher;

import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    String text = scanner.nextLine();
    String keyword = scanner.nextLine();
    scanner.close();

    String result = vigenereDecode_plainToCipherText_cipherToPlainText(text, keyword);
    System.out.println(result);
  }

  /**
   * 1. If the input text is the original plain text, finds the cipher text. 
   * 
   * 1.1 The conditions of the challenge assume that if the input text is the original plain text, 
   *     then it will contain punctuation marks and spaces.
   *
   * 2. If the input text is the cipher text, finds the original plain text. 
   *
   * 2.1 The conditions of the challenge assume that if the input text is the cipher text, 
   *     then it will contain only capital letters. 
   *
   * 2.2 The original plain text, as per the challenge conditions, is returned
   *     in capital letters, without punctuation marks and without spaces.
   *
   * @return The origial plain text or the cipher text, depending on the input.
   */
  public static String vigenereDecode_plainToCipherText_cipherToPlainText(String text, String keyword) {

    int startLength = text.length();
    text = text.replaceAll("[^a-zA-Z]", "").toUpperCase();
    keyword = keyword.toUpperCase();

    // entered text is plain text.
    if (text.length() < startLength) {
      return convert_fromPlainText_toCipherText(text, keyword);
    }

    // entered text is cipher text.
    return convert_fromCipherText_toPlainText(text, keyword);
  }

  private static String convert_fromPlainText_toCipherText(String plain_text, String keyword) {

    StringBuilder cipher_text = new StringBuilder();
    int i_key = 0;

    for (int i_plain = 0; i_plain < plain_text.length(); i_plain++) {

      cipher_text.append(findCipherChar(plain_text.charAt(i_plain), keyword.charAt(i_key)));
      i_key++;

      if (i_key == keyword.length()) {
        i_key = 0;
      }
    }
    return cipher_text.toString();
  }

  private static String convert_fromCipherText_toPlainText(String cipher_text, String keyword) {

    StringBuilder plain_text = new StringBuilder();
    int i_key = 0;

    for (int i_cipher = 0; i_cipher < cipher_text.length(); i_cipher++) {

      plain_text.append(findTextChar(cipher_text.charAt(i_cipher), keyword.charAt(i_key)));
      i_key++;

      if (i_key == keyword.length()) {
        i_key = 0;
      }
    }
    return plain_text.toString();
  }

  private static char findCipherChar(char char_text, char char_keyword) {

    int shifts_char_text = (int) char_text - (int) 'A';
    int position_char_keyword = (int) char_keyword - (int) 'A';
    int position_cipher = shifts_char_text + position_char_keyword;

    if (position_cipher < 26) {
      return (char) ((int) 'A' + position_cipher);
    }

    return (char) ((int) 'A' + (position_cipher % 26));
  }

  private static char findTextChar(char char_cipher, char char_keyword) {

    int position_char_cipher = (int) char_cipher - (int) 'A';
    int position_char_keyword = (int) char_keyword - (int) 'A';

    if (position_char_cipher >= position_char_keyword) {
      return (char) ((int) 'A' + position_char_cipher - position_char_keyword);
    }

    return (char) ((int) 'A' + 26 + position_char_cipher - position_char_keyword);
  }
}
