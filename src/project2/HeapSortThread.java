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
public class HeapSortThread extends SortThread {
    public HeapSortThread(int[] array, GUI.PanelDraw panelDraw) 
    {
        super(array, panelDraw);
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        // go through the array elements at indices 1, 2, 3, ..., placing each one into the max-heap
        for (int i = 1; !isCancelled() && i < array.length; i++) {
            int cIndex = i, pIndex = (cIndex - 1)/2;

            while (!isCancelled() && cIndex > 0 && array[cIndex] > array[pIndex]) {
                int temp = array[cIndex];
                array[cIndex] = array[pIndex];
                publishDelay();
                array[pIndex] = temp;
                publishDelay();

                cIndex = pIndex;
                pIndex = (cIndex - 1)/2;
                
                //publishDelay();
            }
        }

        // now repeatedly remove the top element from the max-heap, placing it at the end of the array
        for (int i = 0; !isCancelled() &&i < array.length; i++) {

            // swap root with last element in the array
            int topHeapElement = array[0];
            array[0] = array[array.length - 1 - i];
            publishDelay();
            array[array.length - 1 - i] = topHeapElement;
            
            publishDelay();
            
            // work our way back down the heap, swapping as necessary
            int pIndex = 0;
            while (!isCancelled()) {
                // check for left child
                int lIndex = 2*pIndex + 1;
                if (lIndex >= array.length - 1 - i)		// no left child
                    break;

                int cIndex = lIndex;	// index of the greater child (initially we assume it's the left)

                // check for right child, and see if it's greater than the left
                int rIndex = lIndex + 1;
                if (rIndex < array.length - 1 - i && array[rIndex] > array[lIndex])
                    cIndex = rIndex;

                // check if parent is less than greater child, swap if so
                if (array[pIndex] < array[cIndex] && !isCancelled()) {
                    int temp = array[cIndex];
                    array[cIndex] = array[pIndex];
                    publishDelay();
                    array[pIndex] = temp;
                    publishDelay();

                    pIndex = cIndex;
                } else
                    break;		// heapify is done!
            }
        }
        
        return null;
    }
}
