package sorting;

/**
 * class implementing merge sort
 * @author Gavin Bosman
 * @auhtor Mekael Wasti
 */

public class MergeSorter extends Sorter {

    /**
     * Uses the sorting algorithm "Merge Sort" to sort passed list in ascending order
     * @param list is the unsorted array, to be sorted
     */
    @Override
    public void sort(double[] list) {
        int arrayLength = list.length;
        countOp();

        //Base case - Check for array of size 1 or less
        if (arrayLength <= 1) {
            return;
        }

        //Divide passed Array into two arrays
        int midpoint = arrayLength/2;
        countOp();
        double[] leftArray = new double[midpoint];
        countOp();
        double[] rightArray = new double[arrayLength - midpoint];
        countOp();

        //Populate left and right array with appropriate values
        for (int i = 0; i < midpoint; i++) {
            leftArray[i] = list[i];
            countOp();
        }
        for (int i = midpoint; i < arrayLength; i++) {
            rightArray[i - midpoint] = list[i];
            countOp();
        }

        //Divide Step
        sort(leftArray);
        sort(rightArray);


        //Merging Step:
        //Make appropriate iterators for left and right sub-array sorting
        int leftLength = leftArray.length;
        countOp();
        int rightLength = rightArray.length;
        countOp();

        int i = 0;
        countOp();
        int j = 0;
        countOp();
        int k = 0;
        countOp();

        //Sort each sub-array
        while (i < leftLength && j < rightLength) {
            if (leftArray[i] <= rightArray[j]) {
                list[k] = leftArray[i];
                countOp();
                i++;
                countOp();
            }
            else {
                list[k] = rightArray[j];
                countOp();
                j++;
                countOp();
            }
            k++;
            countOp();
        }
        //Check for any remaining values in each sub-array
        while (i < leftLength) {
            list[k] = leftArray[i];
            countOp();
            i++;
            countOp();
            k++;
            countOp();
        }
        while (j < rightLength) {
            list[k] = rightArray[j];
            countOp();
            j++;
            countOp();
            k++;
            countOp();
        }
    }
}
