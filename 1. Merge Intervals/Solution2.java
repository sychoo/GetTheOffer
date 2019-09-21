// Sat Sep 21 07:53:40 EDT 2019
// Simon Chu
// Solution.java

import java.util.ArrayList;

public class Solution2 {
  /**
   * find the min of each first element of each subarray
   * then find the max of the second element of each subarray
   * create array with size max - min + 1, mark all intervals.
   * 
   * assume that left bound of intervals is always smaller than the right bound of the interval.
   */
  public static int[][] merge(int[][] intervals) {
    if (intervals.length == 0 || intervals.length == 1) {
      // case when array has 0 or 1 element
      return intervals;
    }
    int curr_element, min_first, max_second;

    // case when array has 2 or more elements
    min_first = intervals[0][0];
    for (int i = 1; i < intervals.length; i++) {
      curr_element = intervals[i][0];

      if (min_first > curr_element) {
        min_first = curr_element;
      }
    }

    max_second = intervals[0][1];
    for (int j = 1; j < intervals.length; j++) {
      curr_element = intervals[j][1];

      if (max_second < curr_element) {
        max_second = curr_element;
      }
    }

    // create an array with max_second - min_first elements, initialize all elements to 0
    byte[] array = new byte[max_second - min_first + 1];
    for (int k = 0; k < array.length; k++) {
      array[k] = 0;
    }

    ArrayList<int[]> result = new ArrayList<>();

    // mark all elements that are included.
    for (int i = 0; i < intervals.length; i++) {
      for (int j = intervals[i][0] - min_first; j < intervals[i][1] - min_first; j++) {
        array[j] = 1;
      }
    }

    // debugger, print bit vector.
    
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
    

    // check singletons
    for (int i = 0; i < intervals.length; i++) {
      //System.out.println("i " + i + " array[i]: " + array[i] + " interval " + intervals[i][0] + " " + intervals[i][1]);
      if ((intervals[i][0] == intervals[i][1]) && (/*first element has bit vector value 0*/(array[intervals[i][0] - min_first] == 0 && intervals[i][0] - min_first == 0) || (/*not first element, previous and next bit vector has value 0*/intervals[i][0] - min_first > 0 && array[intervals[i][0] - min_first - 1] == (byte) 0))){
        //System.out.println("I'm here");
        result.add(intervals[i]);
        System.out.println("appended: " + intervals[i][0] + " " + intervals[i][1]);
      }
    }

    int prev = 0, curr, begin = 0, end = 0;
    for (int i = 0; i < array.length; i++) {
      curr = array[i];
      if (prev == 0 && curr == 1) {
        begin = i + min_first;
      } else if (prev == 1 && curr == 0) {
        end = i + min_first; // since what you want is actually the previous element
        result.add(new int[] {begin, end});
      }
      prev = curr;
    }

    // append last result.
    //result.add(new int []{begin, min_first + array.length - 1});

    // convert arraylist to java array
    int[][] result2 = new int[result.size()][];
    for (int i = 0; i<result.size(); i++) {
      result2[i] = result.get(i);
    }
    return result2;
  }

  /**
   * helper function to print array.
   */
  public static void printArray(int[][] array) {
    System.out.print("[");
    for (int i = 0; i < array.length; i++) {
      System.out.print("[");
      for (int j = 0; j < 2; j++) {
        System.out.print(array[i][j]);
        if (j != 1) {
          System.out.print(", ");
        }
      }
      if (i == array.length - 1) {
        System.out.print("]");
      } else {
        System.out.print("], ");
      }
    }
    System.out.println("]");
  }
  public static void main(String[] args) {
    printArray(merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
    printArray(merge(new int[][] {{1, 4}, {4, 5}}));
    printArray(merge(new int[][] {{1, 4}, {5, 6}}));
    printArray(merge(new int[][] {{1, 4}, {0, 0}}));
    printArray(merge(new int[][] {{3, 3}, {1, 3}, {4, 7}}));
    printArray(merge(new int[][] {{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}}));
    printArray(merge(new int[][] {{4,5},{2,4},{4,6},{3,4},{0,0},{1,1},{3,5},{2,2}}));
  }
}