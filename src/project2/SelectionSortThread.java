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
public class SelectionSortThread extends SortThread {
    public SelectionSortThread(int[] array, GUI.PanelDraw panelDraw) {
        super(array, panelDraw);
    }

    @Override
    protected Void doInBackground() throws Exception {
        // find the minimum element from indices [0, array.length-1], and swap
        //  that minimum element with index 0
        // then find the minimum element from indices [1, array.length-1], and
        //  swap that minimum element with index 1
        // then find the minimum element from indices [2, array.length-1], and
        //  swap that minimum element with index 2
        // ... etc.
        for (int j = 0; !isCancelled() && j < array.length - 1; j++) {	// finding the min element array.length-1 times guarantees that the last element will be in place
            // find the minimum element in indices [j, array.length-1]
            int min = array[j];
            int minIndex = j;
            for (int i = j + 1; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                    minIndex = i;
                }
                publishDelay();
            }

            // swap that minimum element with index j
            int temp = array[j];
            array[j] = array[minIndex];
            array[minIndex] = temp;
            publishDelay();
        }
        
        return null;
    }
    
}
