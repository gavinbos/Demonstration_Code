package sorting;

/**
 * abstract class Sorter lays creates an object capable of sorting a given array in ascending order
 * the method sort is abstract and is what will be different for each child class
 * @author Gavin Bosman
 * @auhtor Mekael Wasti
 */

public abstract class Sorter {
  private long opCount = 0L;

  /**
   * Returns "this" instances' value of opCount
   */
  public long getOpCount() {
    return this.opCount;
  }

  /**
   * Resets "this" instances' value of opCount to 0
   */
  protected void resetOpCount() {
    this.opCount = 0L;
  }

  /**
   * Increments "this" instances' value of opCount by 1 when called
   */
  protected void countOp() {
    this.opCount++;
  }

  /**
   * Absteract class overriden in child class
   * Uses sorting algorithm of choice to sort passed list in ascending order
   * @param list is the unsorted array, to be sorted
   */
  public abstract void sort(double[] list);
}
