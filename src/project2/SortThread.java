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
    static final int THREAD_DELAY = 50;
    
    private PanelDraw panelDraw;
    
    public SortThread(int[] array, PanelDraw panelDraw)
    {
        this.array = array;
        this.panelDraw = panelDraw;
    }
    
    protected void publishDelay() {
        publish(array);
        try {
            Thread.sleep(THREAD_DELAY);
        } catch (InterruptedException e) { }
    }
    
    @Override
    protected abstract Void doInBackground() throws Exception;
    
    protected void process(java.util.List<int[]> list)
    {
        //tf.setText("" + list.get(list.size() - 1));
        panelDraw.repaint();
    }
}
