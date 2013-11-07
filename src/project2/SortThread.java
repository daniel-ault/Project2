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
public abstract class SortThread extends SwingWorker<Void, int[]> {
    int[] array;
    static final int THREAD_DELAY = 5;
    
    private PanelDraw panelDraw;
    
    public SortThread(int[] array, PanelDraw panelDraw)
    {
        this.array = array;
        this.panelDraw = panelDraw;
    }
    
    @Override
    protected abstract Void doInBackground() throws Exception;
}
