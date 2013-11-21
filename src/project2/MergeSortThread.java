/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2;

import project2.GUI.PanelDraw;

/**
 *
 * @author daniel-ault
 */
public class MergeSortThread extends SortThread {
    public MergeSortThread(int[] array, PanelDraw panelDraw) {
        super(array, panelDraw);
    }

    @Override
    protected Void doInBackground() throws Exception {
        mergeSort(array, 0, array.length - 1, "");
        return null;
    }
    
    // Recursively merge sorts the array a between indices start and end, inclusive.
    private void mergeSort(int[] a, int start, int end, String s)
    {
        System.out.println(s + "calling mergeSort(" + start + ", " + end + ")");
        
        if (isCancelled()) {
            System.out.println("Merge Sort cancelled by user.");
        }
        // base case is when start/end are the same -- in this case there's only one element, so no action is needed
        else if (end - start > 0) {
            // find the middle index
            int mid = (start + end) / 2;

            // sort each half
            mergeSort(a, start, mid, s + " ");
            mergeSort(a, mid + 1, end, s + " ");
            
            if (isCancelled())
                return;

            // now merge the two sorted halves together!
            System.out.println(s + " merging between " + start + " and " + end);

            // temp array to hold the merged elements
            int[] temp = new int[end - start + 1];

            // i tracks the position in the left half, j tracks position in right half, k tracks position in merged array
            int i = start, j = mid + 1, k = 0;
            while (i <= mid && j <= end && !isCancelled()) {
                if (a[i] < a[j])
                    temp[k++] = a[i++];
                else
                    temp[k++] = a[j++];
            }

            while (i <= mid)
                temp[k++] = a[i++];

            while (j <= end)
                temp[k++] = a[j++];

            // copy temp elements back into array
            for (i = 0; i < temp.length; i++) {
                a[start + i] = temp[i];
                publishDelay();
            }
        } else {
            System.out.println(s + " single element, no action taken");
        }
    }
    
}
