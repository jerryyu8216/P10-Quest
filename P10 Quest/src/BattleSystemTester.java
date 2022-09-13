//////////////// P10 Battle System //////////////////////////
//
// Title: BattleCharacter
// Course: CS 300 Fall 2020
//
// Author: Jerry Yu
// Email: jcyu4@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;
/**
 * Class that tests the methods within the MoveQueue class
 */
public class BattleSystemTester {
  /**
   * Calls all test methods and displays them
   * 
   */
  public static void main(String[] args) {
    System.out.println("testEnqueueMoveQueue(): " + testEnqueueMoveQueue());
    System.out.println("testDequeueMoveQueue(): " + testDequeueMoveQueue());
    System.out.println("testSizeIsEmptyClear(): " + testSizeIsEmptyClear());
    System.out.println("testPeekBest(): " + testPeekBest());
  }
  /**
   * Checks the correctness of the enqueue operation implemented in the MoveQueue class
   * @return true if method works and fals otherwise
   */
  public static boolean testEnqueueMoveQueue() {
    try {
      MoveQueue test = new MoveQueue(3);
      // Scenario 1: Adding a BattleCharacter to an empty MoveQueue
      BattleCharacter b1 = new BattleCharacter("Tobi", new int[] {1, 2, 3, 4, 4});
      test.enqueue(b1);
      String expected1 = "[ Tobi(1, 4) | ]";
      if (test.size() != 1 || test.isEmpty() != false || !(test.toString().equals(expected1))) {
        return false;
      }
      // Scenario 2: Adding null to the MoveQueue
      BattleCharacter b = null;
      try {
        test.enqueue(b);
        return false;
      } catch (IllegalArgumentException ia) {
        // Expected Exception to be thrown
      }
      // Scenario 3: Adding 2 new BattleCharacters to the MoveQueue
      BattleCharacter b2 = new BattleCharacter("Simon", new int[] {3, 3, 3, 3, 3});
      BattleCharacter b3 = new BattleCharacter("JJ", new int[] {4, 2, 3, 1, 5});
      test.enqueue(b2);
      test.enqueue(b3);
      String expected2 = "[ JJ(3, 5) | Simon(2, 3) | Tobi(1, 4) | ]";
      if (test.size() != 3 || !(test.toString().equals(expected2))) {
        return false;
      }
      // Scenario 4: Try adding to a full MoveQueue
      try {
        BattleCharacter b4 = new BattleCharacter("Cal", new int[] {1, 1, 1, 1, 1});
        test.enqueue(b4);
        return false;
      } catch (IllegalStateException ia) {
        // Expected Exception to be thrown
      }
    } catch (Exception e) {
      System.out.println("Unknown exception was thrown");
      return false;
    }
    return true;
  }

  /** 
   * Checks the correctness of the dequeue operation implemented in the MoveQueue class
   * @return true if the method works and false otherwise
   */
  public static boolean testDequeueMoveQueue() {
    try {
      MoveQueue test = new MoveQueue(3);

      // Scenario 1: Call dequeue on empty MoveQueue
      try {
        test.dequeue();
        return false;
      } catch (NoSuchElementException ne) {
        // Expected Exception to be thrown
      }
      // Scenario 2: Call dequeue on MoveQueue with one element
      BattleCharacter b1 = new BattleCharacter("Tobi", new int[] {1, 2, 3, 4, 4});
      test.enqueue(b1);
      if (test.dequeue() != b1 || test.size() != 0 || test.isEmpty() != true) {
        return false;
      }
      // Scenario 3: Call dequeue on MoveQueue with multiple elements
      BattleCharacter b2 = new BattleCharacter("Simon", new int[] {3, 3, 3, 3, 3});
      BattleCharacter b3 = new BattleCharacter("JJ", new int[] {4, 2, 3, 1, 5});
      test.enqueue(b1);
      test.enqueue(b2);
      test.enqueue(b3);
      String expected1 = "[ Tobi(5, 4) | Simon(6, 3) | ]";
      if (test.dequeue() != b3 || test.size() != 2 || !(test.toString().equals(expected1))) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Unknown exception was thrown");
      return false;
    }
    return true;
  }
  /** 
   * Checks the correctness of the size, isEmpty, and clear operations implemented in the MoveQueue 
   * class
   * @return true if the methods work and false otherwise
   */
  public static boolean testSizeIsEmptyClear() {
    try {
      MoveQueue test = new MoveQueue();
      // Scenario 1: Call methods on empty MoveQueue
      if(test.size() != 0 || test.isEmpty() != true) {
        return false;
      }
      // Scenario 2: Call methods on non-empty MoveQueue
      BattleCharacter b1 = new BattleCharacter("Tobi", new int[] {1, 2, 3, 4, 4});
      BattleCharacter b2 = new BattleCharacter("Simon", new int[] {3, 3, 3, 3, 3});
      BattleCharacter b3 = new BattleCharacter("JJ", new int[] {4, 2, 3, 1, 5});
      test.enqueue(b1);
      test.enqueue(b2);
      test.enqueue(b3);
      if(test.size() != 3 || test.isEmpty() != false) {
        return false;
      }
      // Scenario 3: Clear from non-empty MoveQueue
      test.clear();
      String expected1 = "[ ]";
      if(test.size() != 0 || test.isEmpty() != true||!(test.toString().equals(expected1))) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Unknown exception was thrown");
      return false;
    }
    return true;
  }
  /** 
   * Checks the correctness of the peekBest operation implemented in the MoveQueue class
   * @return true if the method works and false otherwise
   */
  public static boolean testPeekBest() {
    try {
      MoveQueue test = new MoveQueue();
      // Scenario 1: Call peekBest() on empty MoveQueue
      try {
        test.peekBest();
        return false;
      }catch(NoSuchElementException ne) {
        // Expected Exception to be thrown
      }
      // Scenario 2: Call peekBest() on MoveQueue with only one element
      BattleCharacter b1 = new BattleCharacter("Tobi", new int[] {1, 2, 3, 4, 4});
      test.enqueue(b1);
      if(test.peekBest() != b1) {
        return false;
      }
      // Scenario 3: Call peekBest() on MoveQueue with multiple elements
      BattleCharacter b2 = new BattleCharacter("Simon", new int[] {3, 3, 3, 3, 3});
      BattleCharacter b3 = new BattleCharacter("JJ", new int[] {4, 2, 3, 1, 5});
      test.enqueue(b2);
      test.enqueue(b3);
      if(test.peekBest() != b3) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Unknown exception was thrown");
      return false;
    }
    return true;
  }
}
