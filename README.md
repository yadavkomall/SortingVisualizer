# SortingVisualizer
 Sorting.Visualizer is a web app for visualizing a bunch of different sorting algorithms

 Code explaination-
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
These import statements bring in the required classes and packages for working with GUI components (javax.swing), graphics (java.awt), and timers (javax.swing.Timer).

public class SortingVisualizer extends JPanel {
This line defines a class named SortingVisualizer that extends JPanel. This class will serve as the main panel where the sorting visualization will be drawn.


private static final int WIDTH = 800; // Width of the window
private static final int HEIGHT = 400; // Height of the window
private static final int BAR_WIDTH = 20; // Width of each bar
private static final int BAR_SPACING = 2; // Spacing between bars
private static final int DELAY = 500; // Delay between each step (in milliseconds)
These are constants used to define the size of the window (WIDTH and HEIGHT), the width of each bar in the visualization (BAR_WIDTH), the spacing between bars (BAR_SPACING), and the delay between each step of the sorting algorithm (DELAY).


private int[] array = { ... }; // An array representing the input data for sorting
private int currentIndex = 0; // Current index used during the sorting process
private Timer timer; // A timer used to control the visualization animation
These are instance variables used in the class. array is the input array containing the data to be sorted. currentIndex keeps track of the current index during the sorting process. timer is a Swing timer used to control the animation of the sorting process.


private JButton bubbleSortButton;
private JButton selectionSortButton;
private JButton insertionSortButton;
private JButton mergeSortButton;
private JButton quickSortButton;
private JButton arraysSort;
These are JButton instances representing the buttons for various sorting algorithms. In this code, bubbleSortButton is used for the Bubble Sort algorithm.


public SortingVisualizer() {
    // Constructor - This is the initialization part of the class
    // It sets up the buttons and their action listeners.
    // Main sorting step functions are called from these buttons.
This is the constructor for the SortingVisualizer class. It sets up the GUI components and their action listeners for the sorting buttons.


bubbleSortButton = new JButton("Bubble Sort");
bubbleSortButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bubbleSortStep();
            }
        });
        timer.start();
    }
});
This block of code sets up the bubbleSortButton with a label "Bubble Sort" and adds an action listener to it. When the button is clicked, the action listener is triggered.

The action listener checks if the timer is running. If it is, the timer is stopped to prevent multiple sorting processes running simultaneously.

A new Timer object is created with a delay of DELAY milliseconds between each step of the sorting algorithm.

The timer's actionPerformed method is set to call the bubbleSortStep method, which performs one step of the Bubble Sort algorithm.

Finally, the timer is started.



private void bubbleSortStep() {
    if (currentIndex < array.length - 1) {
        if (array[currentIndex] > array[currentIndex + 1]) {
            int temp = array[currentIndex];
            array[currentIndex] = array[currentIndex + 1];
            array[currentIndex + 1] = temp;
            currentIndex = 0; // Reset currentIndex to start from the beginning for each pass
        } else {
            currentIndex++;
        }
        repaint();
    } else {
        if (timer != null) {
            timer.stop();
        }
    }
}
This method bubbleSortStep is the implementation of one step of the Bubble Sort algorithm.

It checks if the currentIndex is less than the length of the array minus 1. If it is, there are still elements to be sorted.

If the current element is greater than the next element, they are swapped to sort them in ascending order.

The currentIndex is then set to 0 to start the next pass of the Bubble Sort algorithm from the beginning.

If the current element is not greater than the next element, the currentIndex is incremented to move on to the next pair of elements to be compared and sorted.

After each step, the repaint() method is called, which triggers the paintComponent method to redraw the visualization with the updated array.

If the currentIndex is equal to the length of the array minus 1, it means the sorting process is complete, and the timer is stopped.

protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int offset = 0;
    for (int i = 0; i < array.length; i++) {
        int x = i * (BAR_WIDTH + BAR_SPACING) + offset;
        int y = HEIGHT - array[i];
        g.setColor(Color.BLUE);
        g.fillRect(x, y, BAR_WIDTH, array[i]);
        offset += BAR_SPACING; // Increase offset to add spacing between bars
    }
}
This method paintComponent is overridden to customize the appearance of the sorting visualization.

It is called automatically when the GUI needs to be repainted (e.g., after each step of the sorting algorithm).

The method loops through the elements of the array and draws a blue rectangle (g.fillRect) representing each element at its corresponding position.

The x and y coordinates are calculated to position the bars correctly on the panel.

The offset variable is used to add spacing between bars.


public static void main(String[] args) {
    JFrame frame = new JFrame("Sorting Visualizer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new SortingVisualizer());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
This is the main method, which is the entry point of the program.

It creates a new JFrame named "Sorting Visualizer" and sets it up with a SortingVisualizer instance (i.e., the main panel where the sorting visualization will be displayed).

The frame is then packed to fit its components, centered on the screen, and finally made visible.

When you run this code, a GUI window will open with a visualization of the Bubble Sort algorithm in action. You can click the "Bubble Sort" button to perform one step of the sorting process at a time, animating the sorting of the bars in the panel.




