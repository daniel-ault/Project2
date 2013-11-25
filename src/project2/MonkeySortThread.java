/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2;

import java.util.Random;

/**
 *
 * @author daniel-ault
 */
public class MonkeySortThread extends SortThread {
    public MonkeySortThread(int[] array, GUI.PanelDraw panelDraw) {
        super(array, panelDraw);
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled() && !isSorted()) {
            shuffleArray();
            publishDelay();
        }
        return null;
    }
    
    private void shuffleArray()
    {
        Random rnd = new Random();
        for (int i=array.length-1; i>0; i--)
        {
            int index = rnd.nextInt(i+1);
            // simple swap
            int n = array[index];
            array[index] = array[i];
            array[i] = n;
        }
    }
    
    private boolean isSorted()
    {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i+1])
                return false;
        }
        return true;
    }
    
}
