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
public class ShellSortThread extends SortThread {
    public ShellSortThread(int[] array, GUI.PanelDraw panelDraw) {
        super(array, panelDraw);
    }

    @Override
    protected Void doInBackground() throws Exception {
        // determine array gap size
        int gapSize = array.length/2;

        while (!isCancelled() && gapSize > 0) {
            // insertion sort the array elements at {0, gapSize, 2*gapSize, ...},
                //  then {1, 1 + gapSize, 1 + 2*gapSize, ...},
                //  then {2, 2 + gapSize, 2 + 2*gapSize, ...}, etc.
            for (int i = gapSize; !isCancelled() && i < array.length; i++) {
                int toInsert = array[i];

                int j = i - gapSize;
                while (!isCancelled() && j >= 0 && toInsert < array[j]) {
                    array[j+gapSize] = array[j];
                    j -= gapSize;
                    publishDelay();
                }// end while

                array[j+gapSize] = toInsert;
                publishDelay();
            }
            
                // now reduce the gap size and repeat until the gap size becomes 0
            gapSize = (gapSize == 2) ? 1 : (int)(gapSize / 2.2);
        }
        
        return null;
    }
    
}
