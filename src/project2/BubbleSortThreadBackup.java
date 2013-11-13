/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2;

import javax.swing.SwingWorker;
import project2.GUI.PanelDraw;

/**
 *
 * @author daniel-ault
 */
public class BubbleSortThreadBackup extends SwingWorker<Void, int[]> {
    
    int[] array;
    static final int THREAD_DELAY = 5;
    
    private PanelDraw panelDraw;
    
    public BubbleSortThreadBackup(int[] array, PanelDraw panelDraw) 
    {
        this.array = array;
        this.panelDraw = panelDraw;
    }
    
    public void bubbleSort(int[] array)
    {
        
    }

    @Override
    protected Void doInBackground() throws Exception {
        boolean isSorted = true;
        // check if the array is already sorted
        for (int i=1; i<array.length; i++) {
            if (array[i]<array[i-1]) {
                isSorted = false;
                break;
            }
        }// end for i
        //if the array is sorted, then leave the function
        if (isSorted == true)
            return null;
        
        // perform array.length passes through the array
        for (int j = 0; j < array.length; j++) {
                // for each pass, compare elements 0 and 1, 1 and 2, 2 and 3, etc., swapping them
                //  if the value at the smaller index is bigger
                // each pass "bubbles up" the largest element to the end of the array
            for (int i = 0; i < array.length - 1 - j; i++) {
                if (array[i] > array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                }
                publish(array);
                try {
                    Thread.sleep(THREAD_DELAY);
                } catch (InterruptedException e) { }
            }// end for i
        }// end for j
        
        return null;
    }
    
    /**
     *
     * @param list
     */
    protected void process(java.util.List<int[]> list)
    {
        //tf.setText("" + list.get(list.size() - 1));
        panelDraw.repaint();
    }
}
