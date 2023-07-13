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

public class SortingVisualizer extends JPanel {
    private static final int WIDTH = 800; // Width of the window
    private static final int HEIGHT = 400; // Height of the window
    private static final int BAR_WIDTH = 20; // Width of each bar
    private static final int BAR_SPACING = 2; // Spacing between bars
    private static final int DELAY = 500; // Delay between each step (in milliseconds)

    //input array
    private int[] array = { 100, 232, 43, 53, 123, 100, 150, 124, 105, 111, 20, 150, 94, 172, 50, 75, 174, 90, 26, 54 };
    private int currentIndex = 0;
    private Timer timer;

    //button initialise
    private JButton bubbleSortButton;
    private JButton selectionSortButton;
    private JButton insertionSortButton;
    private JButton mergeSortButton;
    private JButton quickSortButton;
    private JButton arraysSort;

    //main sorting step functions calling
    public SortingVisualizer() {

        //Bubble Sort
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

        //Selection Sort
        selectionSortButton = new JButton("Selection Sort");
        selectionSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                timer = new Timer(DELAY, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectionSortStep();
                    }
                });
                timer.start();
            }
        });

        //Insertion Sort
        insertionSortButton = new JButton("Insertion Sort");
        insertionSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                timer = new Timer(DELAY, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        insertionSortStep();
                    }
                });
                timer.start();
            }
        });

        //Merge Sort
        mergeSortButton = new JButton("Merge Sort");
        mergeSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                timer = new Timer(DELAY, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mergeSortStep();
                    }
                });
                timer.start();
            }
        });

        //Quick Sort
        quickSortButton = new JButton("Quick Sort");
        quickSortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                timer = new Timer(DELAY, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        quickSortStep(0, array.length - 1);
                    }
                });
                timer.start();
            }
        });

        //arrays.sort() function
        arraysSort = new JButton("Arrays.sort()");
        arraysSort.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                 timer = new Timer(DELAY, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ArraySort(array);
                    }
                });
                timer.start();
            }
        });
        //Adding Button to jframe
        add(bubbleSortButton);
        add(selectionSortButton);
        add(insertionSortButton);
        add(mergeSortButton);
        add(quickSortButton);
        add(arraysSort);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }


    // Bubble Sort algorithm (one step at a time)
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

    // Selection Sort algorithm (one step at a time)
    private void selectionSortStep() {
        if (currentIndex < array.length - 1) {
            int minIndex = currentIndex;
            for (int i = currentIndex + 1; i < array.length; i++) {
                if (array[i] < array[minIndex]) {
                    minIndex = i;
                }
            }
            int temp = array[currentIndex];
            array[currentIndex] = array[minIndex];
            array[minIndex] = temp;
             currentIndex++;
        repaint();
        timer.restart();
        } else {
            if (timer != null) {
                timer.stop();
            }
        }
    }

    private void ArraySort(int a[]){
        Arrays.sort(array);
        repaint();
         if (timer != null) {
                timer.stop();
            }

    }

    // Insertion Sort algorithm (one step at a time)
    private void insertionSortStep() {
        if (currentIndex < array.length) {
            int key = array[currentIndex];
            int j = currentIndex - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
            currentIndex++;
            repaint();
        } else {
            if (timer != null) {
                timer.stop();
            }
        }
    }

   // Merge Sort algorithm (one step at a time)
    private void mergeSortStep() {
        if (currentIndex < array.length - 1) {
            int mid = (currentIndex + array.length - 1) / 2;
            mergeSort(array, currentIndex, mid);
            mergeSort(array, mid + 1, array.length - 1);
            merge(array, currentIndex, mid, array.length - 1);
            currentIndex++;
             repaint();
        } else {
            if (timer != null) {
                timer.stop();
            }
        }
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];

        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

     // Quick Sort algorithm (one step at a time)
    private void quickSortStep(int low, int high) {
        if (currentIndex < array.length) {
            if (low < high) {
                int pi = partition(array, low, high);

                if (currentIndex <= pi) {
                    quickSortStep(low, pi - 1);
                } else {
                    quickSortStep(pi + 1, high);
                }
            }
            currentIndex++;
            repaint();
        } else {
            if (timer != null) {
                timer.stop();
            }
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // Paint the array as bars on the panel
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

    //Main 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SortingVisualizer());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
