/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author daniel-ault
 */
public class GUI extends JFrame {
    // if this is true, then it will draw each bar with a hue based on the 
    // value the bar represents
    private boolean DRAW_COLORS = true;
    
    private final int ARRAY_SIZE = 100;
    
    private boolean isSorting = false;
    private SortThread currentSortThread;
    
    private PanelDraw panelDraw = new PanelDraw();
    //private Pixel[][] pixels = new Pixel[ARRAY_SIZE][ARRAY_SIZE];
    private JButton buttonBubble = new JButton("Bubble Sort");
    private JButton buttonSelection = new JButton("Selection Sort");
    private JButton buttonInsertion = new JButton("Insertion Sort");
    private JButton buttonShell = new JButton("Shell Sort");
    private JButton buttonMerge = new JButton("Merge Sort");
    private JButton buttonHeap = new JButton("Heapsort");
    private JButton buttonQuick = new JButton("Quicksort");
    private JButton buttonMonkey = new JButton("Monkey Sort");
    private JButton buttonShuffle = new JButton("Shuffle");
    private JButton buttonCancel = new JButton("Cancel");
    
    int[] array = new int[ARRAY_SIZE];
    
    private JLabel labelTop = new JLabel("woot");
    
    public GUI()
    {
        for (int i=1; i<=ARRAY_SIZE; i++)
            array[i-1] = i;
        
        setTitle("All sorts of sorts");
        // this size allows all the pixels to be 4x4 -- at least in linux
        setSize(405, 528);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ButtonHandler bh = new ButtonHandler();
        buttonBubble.addActionListener(bh);
        buttonSelection.addActionListener(bh);
        buttonInsertion.addActionListener(bh);
        buttonShell.addActionListener(bh);
        buttonMerge.addActionListener(bh);
        buttonHeap.addActionListener(bh);
        buttonQuick.addActionListener(bh);
        buttonMonkey.addActionListener(bh);
        buttonShuffle.addActionListener(bh);
        
        
        // adding the GUI elements to a JPanel object,
        // which will become the "content pane" of the JFrame
        JPanel c = new JPanel();
        c.setLayout(new BorderLayout());
        

        // initialize the draw Panel, and draw the array on it

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(3, 3));
        buttonContainer.add(buttonBubble);
        buttonContainer.add(buttonSelection);
        buttonContainer.add(buttonInsertion);
        buttonContainer.add(buttonShell);
        buttonContainer.add(buttonMerge);
        buttonContainer.add(buttonHeap);
        buttonContainer.add(buttonQuick);
        buttonContainer.add(buttonMonkey);
        buttonContainer.add(buttonShuffle);
        
        // add the elements to the content pane
        c.add(buttonContainer, BorderLayout.SOUTH);
        c.add(panelDraw, BorderLayout.CENTER);
        c.add(labelTop, BorderLayout.NORTH);
        
        //draw the list
        //drawArray();
        
        setContentPane(c);
        setVisible(true);
    }
    
    private void shuffleArray(int[] a)
    {
        Random rnd = new Random();
        for (int i=a.length-1; i>0; i--)
        {
            int index = rnd.nextInt(i+1);
            // simple swap
            int n = a[index];
            a[index] = a[i];
            a[i] = n;
        }
    }
    
    public class PanelDraw extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            drawArray(g2);
        }
        
        private void drawArray(Graphics2D g)
        {
            for (int i=0; i<ARRAY_SIZE; i++) {
                drawBar(i, g);
            }
        }

        private void drawBar(int n, Graphics2D g)
        {
            Color c;
            if (DRAW_COLORS)
                c = Color.getHSBColor(array[n]/100.0f, 1.0f, 1.0f);
            else 
                c = Color.BLUE;
            //fill color
            g.setColor(c);
            g.fillRect(n*4, ARRAY_SIZE*4-array[n]*4, 4, array[n]*4);
            //draw black outline
            g.setColor(Color.BLACK);
            g.drawRect(n*4, ARRAY_SIZE*4-array[n]*4, 4, array[n]*4);
            
        }
    }
    
    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            // figure out which button fired the ActionEvent
            Object source = e.getSource();
            
            if (source == buttonShuffle) {
                shuffleArray(array);
                panelDraw.repaint();
            }
            // if it is sorting, then the only active button is the cancel
            else if (isSorting) {
                cancelSort((JButton)source);
            }
            else {
                startSort((JButton)source);
            }
        }// actionPerformed
        
        private void cancelSort(JButton button) {
            isSorting = false;
            if (currentSortThread != null)
                currentSortThread.cancel(true);
            currentSortThread = null;
            setEnabledAllButtons(true);
            if (button == buttonBubble) {
                button.setText("Bubble Sort");
            }
            else if (button == buttonSelection) {
                button.setText("Selection Sort");
            }
            else if (button == buttonInsertion) {
                button.setText("Insertion Sort");
            }
            else if (button == buttonShell) {
                button.setText("Shell Sort");
            }
            else if (button == buttonMerge) {
                button.setText("Merge Sort");
            }
            else if (button == buttonHeap) {
                button.setText("Heapsort");
            }
            else if (button == buttonQuick) {
                button.setText("Quicksort");
            }
            else if (button == buttonMonkey) {
                button.setText("Monkey Sort");
            }
        }// cancelSort
        
        private void startSort(JButton button) {
            isSorting = true;
            
            setEnabledAllButtons(false);

            if (button == buttonBubble) {
                // bubble sort
                currentSortThread = new BubbleSortThread(array, panelDraw);
                button.setText("Cancel");
                button.setEnabled(true);
            }
            else if (button == buttonSelection) {
                // selection sort
                currentSortThread = new SelectionSortThread(array, panelDraw);
                button.setText("Cancel");
                button.setEnabled(true);
            }
            else if (button == buttonInsertion) {
                // insertion sort
                currentSortThread = new InsertionSortThread(array, panelDraw);
                button.setText("Cancel");
                button.setEnabled(true);
            }
            else if (button == buttonShell) {
                // shell sort
                currentSortThread = new ShellSortThread(array, panelDraw);
                button.setText("Cancel");
                button.setEnabled(true);
            }
            else if (button == buttonMerge) {
                // merge sort
                currentSortThread = new MergeSortThread(array, panelDraw);
                button.setText("Cancel");
                button.setEnabled(true);
            }
            else if (button == buttonHeap) {
                // heapsort
                currentSortThread = new HeapSortThread(array, panelDraw);
                button.setText("Cancel");
                button.setEnabled(true);
            }
            else if (button == buttonQuick) {
                // quicksort
                currentSortThread = new QuickSortThread(array, panelDraw);
                button.setText("Cancel");
                button.setEnabled(true);
            }
            else if (button == buttonMonkey) {
                // monkey sort
                currentSortThread = new MonkeySortThread(array, panelDraw);
                button.setText("Cancel");
                button.setEnabled(true);
            }
            if (currentSortThread != null)
                currentSortThread.execute();
        }
    }// ButtonHandler
    
    private void setEnabledAllButtons(boolean setTo) {
        buttonBubble.setEnabled(setTo);
        buttonSelection.setEnabled(setTo);
        buttonInsertion.setEnabled(setTo);
        buttonShell.setEnabled(setTo);
        buttonMerge.setEnabled(setTo);
        buttonHeap.setEnabled(setTo);
        buttonQuick.setEnabled(setTo);
        buttonMonkey.setEnabled(setTo);
        buttonShuffle.setEnabled(setTo);
    }
    
    public void printSize() {
        int width = this.getWidth();
        int height = this.getHeight();
        
        System.out.println("Width: " + width + "  Height: " + height);
    }
    
    
    
    
    public static void main(String[] args)
    {
        new GUI();
    }
    
}
