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
 * Class that implements the PriorityQueueADT interface and acts as a priority queue for
 * BattleCharacters
 * 
 */
public class MoveQueue implements PriorityQueueADT<BattleCharacter> {
  private BattleCharacter[] data; // Array of BattleCharacters
  private int size; // Number of BattleCharacters stored in the queue

  /**
   * Creates a new MoveQueue and initializes all private instance fields
   * 
   * @param capacity the number of BattleCharacters that can be stored in this queue
   * @throws IllegalArgumentException if capacity is 0 or negative
   */
  public MoveQueue(int capacity) {
    if (capacity > 0) {
      data = new BattleCharacter[capacity];
      size = 0;
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Creates a new MoveQueue and initializes all private instance fields, sets capacity to 10
   */
  public MoveQueue() {
    data = new BattleCharacter[10];
    size = 0;
  }

  /**
   * Returns a String representation of the current contents of the MoveQueue in order from first to
   * last.
   * 
   * @author Michelle
   */
  @Override
  public String toString() {
    String s = ("[ ");
    for (int i = 0; i < size; i++) {
      s += (data[i].toString() + " | ");
    }
    s += ("]");
    return s;
  }

  /**
   * Overridden method that checks if the queue is empty
   * 
   * @return true if it is empty and false if not
   */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Overridden method that returns the size of the array
   * 
   * @return the size of the queue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Overridden method that adds a BattleCharacter to the queue at the correct index
   * @throws IllegalArgumentException if the element being added is null
   * @throws IllegalStateException if the queue is full
   */
  @Override
  public void enqueue(BattleCharacter element) {
    // Checks if the BattleCharacter being added is null
    if (element == null) {
      throw new IllegalArgumentException();
    }
    // Checks if the queue is full
    if (size == data.length) {
      throw new IllegalStateException();
    } 
    // Adds the BattleCharacter to the end of the queue and percolates it up
    else {
      data[size] = element;
      percolateUp(size);
      size++;
    }
  }

  /**
   * Overridden method that removes the BattleCharacter at the top of the queue and returns it
   * 
   * @return the BattleCharacter that was just removed
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public BattleCharacter dequeue() {
    // Checks if the queue is empty
    if (data[0] == null) {
      throw new NoSuchElementException();
    }
    // Removes the element at the top and replaces it with the bottom element then percolates that
    // character downwards
    BattleCharacter temp = data[0];
    data[0] = data[size - 1];
    percolateDown(0);
    // Decrements size
    size--;
    return temp;
  }

  /**
   * Overridden method that returns the BattleCharacter at the top of the queue
   * 
   * @return the BattleCharacter at the top of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public BattleCharacter peekBest() {
    // Checks if the queue is empty
    if (data[0] != null) {
      return data[0];
    } else {
      throw new NoSuchElementException();
    }
  }

  /**
   * Overridden method that clears the entire Queue
   */
  @Override
  public void clear() {
    // Iterates through the array and clears all values
    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
        data[i] = null;
      }
      // Reset the size to zero
      size = 0;
    }
  }

  /**
   * Recursively propagates max-heap order violations up. Checks to see if the current node i
   * violates the max-heap order property by checking its parent. If it does, swap them and continue
   * to ensure the heap condition is satisfied.
   * 
   * @param i index of the current node in this heap
   */
  protected void percolateUp(int i) {
    // Base case:
    if (i == 0) {

    } else {
      // Calculates the parent index
      int parentIndex = (i - 1) / 2;
      // Checks if the current node has higher priority than the parent node and swaps them if so
      // and then recursively calls the percolateUp method until it is in the correct spot
      if (data[i].compareTo(data[parentIndex]) > 0) {
        BattleCharacter temp;
        temp = data[i];
        data[i] = data[parentIndex];
        data[parentIndex] = temp;
        percolateUp(parentIndex);
      }
    }
  }

  /**
   * Recursively propagates max-heap order violations down. Checks to see if the current node i
   * violates the max-heap order property by checking its children. If it does, swap it with the
   * optimal child and continue to ensure the heap condition is met.
   * 
   * @param i index of the current node in this heap
   */
  protected void percolateDown(int i) {
    // Base case:
    if ((2 * i) + 1 > size) {

    } else {
      // Calculates child indexes
      int childIndex1 = (2 * i) + 1;
      int childIndex2 = (2 * i) + 2;
      // Checks if the current node has child nodes
      if (data[childIndex1] != null && data[childIndex2] != null) {
        // Checks if the parent node has less priority than both children and swaps with the proper
        // child and then calls the percolateDown method on the child index that was just swapped
        if (data[i].compareTo(data[childIndex1]) < 0 && data[i].compareTo(data[childIndex2]) < 0) {
          if (data[childIndex1].compareTo(data[childIndex2]) > 0) {
            BattleCharacter temp;
            temp = data[i];
            data[i] = data[childIndex1];
            data[childIndex1] = temp;
            percolateDown(childIndex1);
          } else {
            BattleCharacter temp;
            temp = data[i];
            data[i] = data[childIndex2];
            data[childIndex2] = temp;
            percolateDown(childIndex2);
          }
        }
      }
      // Checks if the left child has more priority than the parent and swaps them if it does and
      // then recursively calls the percolateDown method until it is in the correct spot
      if (data[childIndex1] != null) {
        if (data[i].compareTo(data[childIndex1]) < 0) {
          BattleCharacter temp;
          temp = data[i];
          data[i] = data[childIndex1];
          data[childIndex1] = temp;
          percolateDown(childIndex1);
        }
      }
      // Checks if the right child has more priority than the parent and swaps them if it does and
      // then recursively calls the percolateDown method until it is in the correct spot
      if (data[childIndex2] != null) {
        if (data[i].compareTo(data[childIndex2]) < 0) {
          BattleCharacter temp;
          temp = data[i];
          data[i] = data[childIndex2];
          data[childIndex2] = temp;
          percolateDown(childIndex2);
        }
      }
    }
  }

  /**
   * Eliminates all heap order violations from the heap data array
   */
  protected void heapify() {
    for (int i = data.length / 2; i >= 0; i--) {
      heapifyHelper(i, size, data);
    }
  }

  /**
   * Private static recursive helper method that helps eliminate all heap order violations from the
   * heap data array
   * 
   * @param i    the index that is being heapified
   * @param size the number of BattleCharacters in the array
   * @param data the array of BattleCharacters being heapified
   */
  private static void heapifyHelper(int i, int size, BattleCharacter[] data) {
    int childIndex1 = (2 * i) + 1;
    int childIndex2 = (2 * i) + 2;
    int max = i;
    // Checks if the left child has higher priority than the parent
    if (childIndex1 < size && data[childIndex1].compareTo(data[max]) > 0) {
      max = childIndex1;
    }
    // Checks if the right child has higher priority than the parent and the left child
    if (childIndex2 < size && data[childIndex2].compareTo(data[max]) > 0
        && data[childIndex2].compareTo(data[childIndex1]) > 0) {
      max = childIndex2;
    }
    // Swaps the parent with the proper child and calls the heapifyHelper method on the new parent
    if (max != i) {
      BattleCharacter temp = data[max];
      data[max] = data[i];
      data[i] = temp;
      heapifyHelper(max, size, data);
    }
  }

  /**
   * This method updates a specific character after a buff, debuff, or if it has been killed
   * 
   * @param updateChara - the updated state of a character that will replace the old character
   */
  public void updateCharacter(BattleCharacter updateChara) {
    // Iterates through the BattleCharacter array to find the character that must be updated
    for (int i = 0; i < size - 1; i++) {
      if (data[i].equals(updateChara)) {
        // Checks if the updated character is still alive and swaps it with the current one if it is
        if (updateChara.isAlive()) {
          data[i] = updateChara;
        }
        // If the updated character is dead then it removes the character entirely
        else {
          int gapIndex = i;
          for (int j = gapIndex; j < size - 1; j++) {
            data[j] = data[j + 1];
          }
        }
      }
    }
    // Calls the heapify method to make sure the characters stay in a heap structure
    heapify();
  }
}
