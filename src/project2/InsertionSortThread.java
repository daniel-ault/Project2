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
public class InsertionSortThread extends SortThread {
    public InsertionSortThread(int[] array, PanelDraw panelDraw) {
        super(array, panelDraw);
    }
    

    @Override
    protected Void doInBackground() throws Exception {
        // for each element after index 0, insert it into the correct
        //  position relative to the already-sorted elements to its left
        for (int i = 1; !isCancelled() && i < array.length; i++) {
            int numToInsert = array[i];

            // search for the correct insertion point, shifting
            //  array elements down as we go
            int j = i - 1;
            while (!isCancelled() && j >= 0 && numToInsert < array[j]) {
                array[j+1] = array[j];
                j--;
                publishDelay();
            }

            // insert the item
            array[j+1] = numToInsert;
            publish(array);
        }
    
        return null;
    }
    
}
