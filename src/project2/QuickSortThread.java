/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2;

/**
 *
 * @author daniel-ault
 */
public class QuickSortThread extends SortThread {
    public QuickSortThread(int[] array, GUI.PanelDraw panelDraw) {
        super(array, panelDraw);
    }

    @Override
    protected Void doInBackground() throws Exception {
        // Wrapper method for quicksort
        quicksort(array, 0, array.length - 1);
        return null;
    }

    // Recursively quicksorts the array array between indices start and end, inclusive
    private void quicksort(int[] array, int start, int end)
    {
        //publishDelay();
        if (!isCancelled() && start < end) {	// base case is when start is no longer < end (i.e., nothing to sort)
            int j = quicksortPartition(array, start, end);
            quicksort(array, start, j - 1);
            publishDelay();
            quicksort(array, j + 1, end);
            publishDelay();
        }
    }

    // Partitions the array array between indices start and end, inclusive.  We use the start index as the
    //  pivot element, and arrange all the array elements such that the ones <= pivot are on the left,
    //  and the ones > pivot are on the right.
    private int quicksortPartition(int[] array, int start, int end)
    {
        // pick array pivot
        int pivot = array[start];

        int lower = start, upper = end;
        while (!isCancelled() && lower < upper) {
            // look for the first element (from the left) that's greater than the pivot
            while (!isCancelled() && lower < end) {
                if (array[lower] > pivot)
                    break;
                lower++;
            }

            // look for the first element (from the right) that's less than or equal to the pivot
            while (!isCancelled() && upper > start) {
                if (array[upper] <= pivot)
                    break;
                upper--;
            }

            // swap lower/upper indices if lower < upper
            if (!isCancelled() && lower < upper) {
                int temp = array[lower];
                array[lower] = array[upper];
                array[upper] = temp;
                publishDelay();
            }
        }

        // swap the pivot (at index start) with index upper
        int temp = array[start];
        array[start] = array[upper];
        array[upper] = temp;
        
        publish(array);

        return upper;
    }
    
}
