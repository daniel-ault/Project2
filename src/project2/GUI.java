/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author daniel-ault
 */
public class GUI extends JFrame {
    private final int ARRAY_SIZE = 100;
    
    private Pixel[][] pixels = new Pixel[ARRAY_SIZE][ARRAY_SIZE];
    private JButton buttonBubble = new JButton("Bubble Sort");
    private JButton buttonSelection = new JButton("Selection Sort");
    private JButton buttonInsertion = new JButton("Insertion Sort");
    private JButton buttonShell = new JButton("Shell Sort");
    private JButton buttonMerge = new JButton("Merge Sort");
    private JButton buttonHeap = new JButton("Heapsort");
    private JButton buttonQuick = new JButton("Quicksort");
    private JButton buttonMonkey = new JButton("Monkey Sort");
    private JButton buttonShuffle = new JButton("Shuffle");
    
    int[] array = new int[ARRAY_SIZE];
    
    private JLabel labelTop = new JLabel("woot");
    
    public GUI()
    {
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
        
        // create the pixel objects, and add them to a pixelContainer
        JPanel pixelContainer = new JPanel();
        pixelContainer.setLayout(new GridLayout(pixels.length, pixels[0].length));
        for (int i=0; i<pixels.length; i++) {
            for (int j=0; j<pixels[i].length; j++) {
                pixels[i][j] = new Pixel();
                pixelContainer.add(pixels[i][j]);
            }// end for j
        }// end for i
        
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
        c.add(pixelContainer, BorderLayout.CENTER);
        c.add(labelTop, BorderLayout.NORTH);
        
        setContentPane(c);
        setVisible(true);
    }
    
    
    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            // figure out which button fired the ActionEvent
            Object source = e.getSource();
            
            if (source == buttonBubble) {
                // bubble sort
            }
            else if (source == buttonSelection) {
                // selection sort
            }
            else if (source == buttonInsertion) {
                // insertion sort
            }
            else if (source == buttonShell) {
                // shell sort
            }
            else if (source == buttonMerge) {
                // merge sort
            }
            else if (source == buttonHeap) {
                // heapsort
            }
            else if (source == buttonQuick) {
                // quicksort
            }
            else if (source == buttonMonkey) {
                // monkey sort
            }
            else if (source == buttonShuffle) {
                // shuffle elements
                printSize();
            }
        }
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
