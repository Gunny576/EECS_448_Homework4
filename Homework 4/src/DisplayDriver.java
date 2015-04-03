
import java.util.Arrays;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;

import static javax.swing.GroupLayout.Alignment.CENTER;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;
import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;

import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DisplayDriver extends JFrame
        implements ActionListener {

    private JToggleButton sciFi;
    private JToggleButton travel;
    private JToggleButton softEng;
    private JButton quitButton;
    //private JPanel display;
    
    private JLabel label;
    private JList list;
    
    private double cost;
    
    private static final String[] EMPTY_ARRAY = new String[0];
    
    private static String[][] Database = {{"SciFi 1", "SciFi 2"},
                           {"Travel 1", "Travel 2"},
                           {"SoE 1", "SoE 2"}};
    private static String[] sciFiBooks = {};
    private static String[] travelBooks = {};
    private static String[] softEngBooks = {};
    private static String[] books = {};
    
    private static double[] price = new double[3];
    
    private static boolean[] buttonOn = new boolean[3];

    public DisplayDriver(String[][] data) {
    	{
    		Database = data;
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {

                }
            });
        
        initUI();
    }
        initUI();
    }

    private void initUI() {
        
        price[0] = 50.0;
        price[1] = 40.0;
        price[2] = 100.0;
        
        buttonOn[0] = false;
        buttonOn[1] = false;
        buttonOn[2] = false;
        
        cost = 0.0;
        
        sciFi = new JToggleButton("Science Fiction");
        sciFi.setToolTipText('$' + String.format("%.2f", price[0]) + " each");
        sciFi.addActionListener(this);

        travel = new JToggleButton("Travel");
        travel.setToolTipText('$' + String.format("%.2f", price[1]) + " each");
        travel.addActionListener(this);

        softEng = new JToggleButton("Software Engineering");
        softEng.setToolTipText('$' + String.format("%.2f", price[2]) + " each");
        softEng.addActionListener(this);
        
        quitButton = new JButton("Quit");
        quitButton.setToolTipText("Quit the program");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GraphicsEnvironment ge =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        books = concatAll(sciFiBooks, travelBooks, softEngBooks);
        
        list = new JList(books);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    cost = totalPrice(list.getSelectedIndices());
                    label.setText("Total Cart Value: $" + String.format("%.2f", cost));
                }
            }
        });

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(list);
        pane.setPreferredSize(new Dimension(250, 200));
        panel.add(pane);
        
        label = new JLabel("Total Cart Value: $" + String.format("%.2f", cost));
        label.setFont(new Font("Serif", Font.PLAIN, 18));
        
        createLayout(sciFi, travel, softEng, quitButton, panel, label);
        
        setTitle("View");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {
        
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);          
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                    .addComponent(arg[0])
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addComponent(arg[3]))
                .addPreferredGap(UNRELATED)
                .addGroup(gl.createParallelGroup()
                    .addComponent(arg[4])
                    .addComponent(arg[5]))
        );

        gl.setVerticalGroup(gl.createParallelGroup(CENTER)
                .addGroup(gl.createSequentialGroup()
                    .addComponent(arg[0])
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addGap(10, 30, 30)
                    .addComponent(arg[3]))  
                .addGroup(gl.createSequentialGroup()
                    .addComponent(arg[4])
                    .addComponent(arg[5]))
        );        
        
        gl.linkSize(sciFi, travel, softEng, quitButton);

        pack();        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Science Fiction")) {
            if (!buttonOn[0]) {
                sciFiBooks = Database[0];
            } else {
                sciFiBooks = EMPTY_ARRAY;
            }
            buttonOn[0] = !buttonOn[0];
        }
        
        if (e.getActionCommand().equals("Travel")) {
            if (!buttonOn[1]) {
                travelBooks = Database[1];
            } else {
                travelBooks = EMPTY_ARRAY;
            }
            buttonOn[1] = !buttonOn[1];
        }
        
        if (e.getActionCommand().equals("Software Engineering")) {
            if (!buttonOn[2]) {
                softEngBooks = Database[2];
            } else {
                softEngBooks = EMPTY_ARRAY;
            }
            buttonOn[2] = !buttonOn[2];
        }
        
        books = concatAll(sciFiBooks, travelBooks, softEngBooks);
        
        list.setListData(books);
    }

    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
    
    public static double totalPrice(int[] x) {
        double total = 0.0;
        int[] numBooks = new int[3];
        for(int i = 0; i < numBooks.length; i++) {
            if(buttonOn[i]){
                numBooks[i] = Database[i].length;
            } else {
                numBooks[i] = 0;
            }
        }
        for(int i = 0; i < x.length; i++) {
            if(x[i] < numBooks[0]) {
                total += price[0];
            } else if(x[i] < numBooks[0] + numBooks[1]) {
                total += price[1];
            } else {
                total += price[2];
            }
        }
        
        return total;
    }
}