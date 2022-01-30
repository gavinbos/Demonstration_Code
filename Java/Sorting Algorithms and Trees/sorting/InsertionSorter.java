package sorting;

/**
 * class implementing insertion sort
 * @author Gavin Bosman
 * @auhtor Mekael Wasti
 */

public class InsertionSorter extends Sorter {

    /**
     * Uses the sorting algorithm "Insertion Sort" to sort passed list in ascending order
     * @param list is the unsorted array, to be sorted
     */
    @Override
    public void sort(double[] list) {
        //Iterate through main array linearly
        for (int i = 1; i < list.length; i++) {
            //Save current index value
            double current = list[i];
            countOp();

            //Comparison iteration through main array, linearly
            int j = i-1;
            countOp();

            //if current i index smaller than previous elements,
            //iterate backwards until appropriate sorted location
            //found or beginning of array reached
            while (j >= 0 && list[j] > current) {
                list[j + 1] = list[j];
                countOp();
                j--;
            }
            //Insert element in correct sorted index
            list[j + 1] = current;
            countOp();
        }
    }
}
