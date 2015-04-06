
import java.util.Arrays;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;

import static javax.swing.GroupLayout.Alignment.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

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
    private JButton addButton;
    private JButton removeButton;
    
    private JLabel priceLabel;
    private JLabel selectedItemsLabel;
    private JLabel instructLabel;
    private JList list;
    private JList cartList;
    private String[] cartItemList;
    private String[] cartQtyList;
    
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
    
    ImageIcon addToCart, removeFromCart;
    
    ShoppingCart SCart;

    public DisplayDriver(String[][] data, ShoppingCart cart) {
    	Database = data;
    	SCart = cart;
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
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());
        cartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        GraphicsEnvironment ge =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        books = concatAll(sciFiBooks, travelBooks, softEngBooks);
        
        list = new JList(books);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    //cost = totalPrice(list.getSelectedIndices());
                    //priceLabel.setText("Total Cart Value: $" + String.format("%.2f", cost));
                	int numElements = list.getSelectedIndices().length;
                	selectedItemsLabel.setText("Books selected: " + numElements);
                }
            }
        });
        
        cartList = new JList(SCart.getList());

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(list);
        pane.setPreferredSize(new Dimension(500, 500));
        panel.add(pane);
        
        JScrollPane cartPane = new JScrollPane();
        cartPane.getViewport().add(cartList);
        cartPane.setPreferredSize(new Dimension(500, 500));
        cartPanel.add(cartPane);
        
        instructLabel = new JLabel("Select one or more genres:");
        instructLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        
        //addToCart = new ImageIcon("images/add-to-cart.jpg");
        //removeFromCart = new ImageIcon("images/remove-from-cart.jpg");
        addToCart = new ImageIcon("add-to-cart.jpg");
        removeFromCart = new ImageIcon("remove-from-cart.jpg");
        addButton = new JButton(addToCart);
        addButton.setActionCommand("add to cart");
        addButton.setToolTipText("Add to cart");
        addButton.addActionListener(this);
        removeButton = new JButton(removeFromCart);
        removeButton.setActionCommand("remove from cart");
        removeButton.setToolTipText("Remove from cart");
        removeButton.addActionListener(this);
        
        selectedItemsLabel = new JLabel("Books selected: 0");
        selectedItemsLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        
        priceLabel = new JLabel("Total Cart Value: $" + String.format("%.2f", cost));
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        
        createLayout(instructLabel, sciFi, travel, softEng, quitButton, panel, addButton, selectedItemsLabel, cartPanel, removeButton, priceLabel);
        
        setTitle("Bookstore by Group 8 - Orme, Sievert, Brouhard, and Kraus");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {
        
        Container pane = getContentPane();
        pane.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);          
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                    .addComponent(arg[0])
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addComponent(arg[3])
                    .addComponent(arg[4]))
                .addPreferredGap(UNRELATED)
                .addGroup(gl.createParallelGroup(CENTER)
                    .addComponent(arg[5])
                    .addComponent(arg[6])
                    .addComponent(arg[7]))
                .addPreferredGap(UNRELATED)
                .addGroup(gl.createParallelGroup(CENTER)
                	.addComponent(arg[8])
                	.addComponent(arg[9])
                	.addComponent(arg[10]))
        );

        gl.setVerticalGroup(gl.createParallelGroup(LEADING)
                .addGroup(gl.createSequentialGroup()
                    .addComponent(arg[0])
                    .addComponent(arg[1])
                    .addComponent(arg[2])
                    .addComponent(arg[3])
                    .addGap(10, 30, 30)
                    .addComponent(arg[4]))  
                .addGroup(gl.createSequentialGroup()
                    .addComponent(arg[5])
                    .addComponent(arg[6])
                    .addComponent(arg[7]))
                .addGroup(gl.createSequentialGroup()
                	.addComponent(arg[8])
                	.addComponent(arg[9])
                	.addComponent(arg[10]))
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
            
            books = concatAll(sciFiBooks, travelBooks, softEngBooks);
            
            list.setListData(books);
        }
        
        if (e.getActionCommand().equals("Travel")) {
            if (!buttonOn[1]) {
                travelBooks = Database[1];
            } else {
                travelBooks = EMPTY_ARRAY;
            }
            buttonOn[1] = !buttonOn[1];
            
            books = concatAll(sciFiBooks, travelBooks, softEngBooks);
            
            list.setListData(books);
        }
        
        if (e.getActionCommand().equals("Software Engineering")) {
            if (!buttonOn[2]) {
                softEngBooks = Database[2];
            } else {
                softEngBooks = EMPTY_ARRAY;
            }
            buttonOn[2] = !buttonOn[2];
            
            books = concatAll(sciFiBooks, travelBooks, softEngBooks);
            
            list.setListData(books);
        }
        
        if (e.getActionCommand().equals("add to cart")) {
        	for(int i = 0; i < list.getSelectedIndices().length; i++) {
        		SCart.add(list.getSelectedValuesList().get(i).toString(), itemPrice(list.getSelectedIndices()[i]));
        		//System.out.println(list.getSelectedValuesList().get(i).toString());
            }
        	//System.out.println("Total: $" + String.format("%.2f", SCart.getTotal()));
        	cartItemList = SCart.getList();
        	cartQtyList = SCart.getQtyList();
        	for (int i = 0; i < cartItemList.length; i++) {
        		cartItemList[i] = "(x" + cartQtyList[i] + ") " + cartItemList[i];
        	}
        	cartList.setListData(cartItemList);
        	priceLabel.setText("Total Cart Value: $" + String.format("%.2f", SCart.getTotal()));
        }
        
        if (e.getActionCommand().equals("remove from cart")) {
        	for(int i = 0; i < cartList.getSelectedIndices().length; i++) {
        		String temp = cartList.getSelectedValuesList().get(i).toString();
        		SCart.remove(temp.substring(temp.indexOf(") ") + 2), itemPrice(temp.substring(temp.indexOf(") ") + 2)));
        		//SCart.remove(cartList.getSelectedIndices()[i], itemPrice(cartList.getSelectedIndices()[i]));
        		//System.out.println(list.getSelectedValuesList().get(i).toString());
            }
        	//System.out.println("Total: $" + String.format("%.2f", SCart.getTotal()));
        	cartItemList = SCart.getList();
        	cartQtyList = SCart.getQtyList();
        	for (int i = 0; i < cartItemList.length; i++) {
        		cartItemList[i] = "(x" + cartQtyList[i] + ") " + cartItemList[i];
        	}
        	cartList.setListData(cartItemList);
        	priceLabel.setText("Total Cart Value: $" + String.format("%.2f", SCart.getTotal()));
        }
        
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
    
    public static double itemPrice(int x) {
        double iPrice = 0.0;
    	int[] numBooks = new int[3];
        for(int i = 0; i < numBooks.length; i++) {
            if(buttonOn[i]){
                numBooks[i] = Database[i].length;
            } else {
                numBooks[i] = 0;
            }
        }
        if(x < numBooks[0]) {
            iPrice = price[0];
        } else if(x < numBooks[0] + numBooks[1]) {
            iPrice = price[1];
        } else {
            iPrice = price[2];
            }
        
        return iPrice;
    }
    
    public static double itemPrice(String item) {
        double iPrice = 0.0;
        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < Database[i].length; j++) {
        		if (item.equals(Database[i][j])) {
        			iPrice = price[i];
        		}
        	}
        }
        
        return iPrice;
    }
}