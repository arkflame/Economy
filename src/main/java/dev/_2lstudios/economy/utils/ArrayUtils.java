package dev._2lstudios.economy.utils;

public class ArrayUtils {
  public static String[] removeFirstElement(String[] arr) {
    String[] newArr = new String[arr.length - 1];
    System.arraycopy(arr, 1, newArr, 0, arr.length - 1);
    return newArr;
  }
}
