/*
*  Name         : Natasya Nadhira Binti Ahmad Nazrain (A20EC0103)
*  Section      : 12
*  Instructor   : Dr. Arafat Mohammed Rashad Al-dhaqm
*  Last Modified: 5 July 2022, 1:37 p.m.
*
*  Brief Description: 
*  This program will display the ordering system menu. It has the main method
*  and will use other classes which are Order, Food, Drinks and Payment class.
*/
import tablemanager.ButtonColumn;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DecimalFormat;

class OrderingSystem {
    //Instanced variables
    static final DecimalFormat df = new DecimalFormat("0.00"); //Forces decimals to display 2 decimal places
    
    ArrayList<Food> menuFood = new ArrayList<Food>();
    ArrayList<Drinks> menuDrinks = new ArrayList<Drinks>();
    ArrayList<Order> ordersList = new ArrayList<Order>();
    Payment pay = new Payment(ordersList);

    //For GUI
    DefaultTableModel ordersTableModel;
    JTable ordersTable;
    JLabel priceLabel = new JLabel();

    public static void main(String[] args){
        OrderingSystem os = new OrderingSystem();
        os.loadGUI();
    }

    //Default constructor
    public OrderingSystem(){
        menuFood.add(new Burger(101,"Beef Burger",9.50));
        menuFood.add(new Burger(102,"Chicken Burger",8.00));
        menuFood.add(new Burger(103,"Fish Burger",7.80));
        menuFood.add(new Chicken(104,"Roasted Chicken",11.00));
        menuFood.add(new Chicken(105,"Fried Chicken",8.00));
        menuFood.add(new Chicken(106,"Chicken Chop",10.50));

        menuDrinks.add(new SoftDrinks(201,"7-Up",3.70));
        menuDrinks.add(new SoftDrinks(202,"Coca Cola",3.70));
        menuDrinks.add(new SoftDrinks(203,"Sprite",3.70));
        menuDrinks.add(new Coffee(204,"Mocha",11.50));
        menuDrinks.add(new Coffee(205,"Latte",11.50));
        menuDrinks.add(new Coffee(206,"Cappuccino",11.50));
    }

    //Loads the GUI layout
    void loadGUI(){
        /*** mainFrame **************************************************************************************************************
         *** - It is the window of the GUI that can be shown or hidden */
        //Initialises the window
        JFrame mainFrame = new JFrame("Restaurant Ordering System");
        mainFrame.setSize(800,500);
        
        //[X] button closes window
        mainFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                //Shuts the program down
                System.exit(0);
            }
        });
        
        /*** mainPanel **************************************************************************************************************
         *** - Seperates titleLabel, menuPanel, selectionPanel, ordersPanel, pricePanel, purchasePanel */
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainFrame.add(mainPanel);

            /*** titleLabel *********************************************************************************************************
             *** - The header of the window */
            JLabel titleLabel = new JLabel("Restaurant Ordering System");
            titleLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //Centers label horizontally
            mainPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between top of window and titleLabel
            mainPanel.add(titleLabel);

            /*** menuPanel **********************************************************************************************************
             *** - Seperates the foodTable, drinksTable */
            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.LINE_AXIS));
            mainPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between titleLabel and menuPanel
            mainPanel.add(menuPanel);

                /*** foodTable ******************************************************************************************************
                 *** - Holds the data for food stocks */
                String menuHeaderData[] = {"ID","FOOD","PRICE (RM)"};
                DefaultTableModel foodTableModel = new DefaultTableModel(menuHeaderData, 0);
                JTable foodTable = new JTable(foodTableModel);
                JTableHeader foodTableHeader = foodTable.getTableHeader();
                foodTableHeader.setReorderingAllowed(false); //Prevents user from editing table header
                foodTableHeader.setResizingAllowed(false); //Prevents user from editing table header
                foodTable.setEnabled(false); //Prevents user from editing table
                
                JPanel foodTablePanel = new JPanel();
                foodTablePanel.setLayout(new BoxLayout(foodTablePanel, BoxLayout.PAGE_AXIS));
                foodTablePanel.add(foodTableHeader);
                foodTablePanel.add(foodTable);
                menuPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between left of window and foodTable
                menuPanel.add(foodTablePanel);

                //Set column max width
                foodTable.getColumnModel().getColumn(0).setMaxWidth(25); //ID
                foodTable.getColumnModel().getColumn(2).setMaxWidth(70); //Price
                
                //Add food stock information
                Object[][] foodData = new Object[7][3];
                for (int i=0; i<menuFood.size(); i++){
                    if(i>2){
                        foodData[i+1][0] = menuFood.get(i).getId();
                        foodData[i+1][1] = menuFood.get(i).getName();
                        foodData[i+1][2] = df.format(menuFood.get(i).getPrice());
                    } else {
                        foodData[i][0] = menuFood.get(i).getId();
                        foodData[i][1] = menuFood.get(i).getName();
                        foodData[i][2] = df.format(menuFood.get(i).getPrice());
                    }
                }
                for (int i=0; i<foodData.length; i++) foodTableModel.addRow(foodData[i]); //Counts number of rows in the array and add those to table

                /*** drinksTable ****************************************************************************************************
                 *** - Holds the data for drinks stocks */
                menuHeaderData[0] = "ID"; menuHeaderData[1] ="DRINKS"; menuHeaderData[2] = "PRICE (RM)";
                DefaultTableModel drinksTableModel = new DefaultTableModel(menuHeaderData, 0);
                JTable drinksTable = new JTable(drinksTableModel);
                JTableHeader drinksTableHeader = drinksTable.getTableHeader();
                drinksTableHeader.setReorderingAllowed(false); //Prevents user from editing table header
                drinksTableHeader.setResizingAllowed(false); //Prevents user from editing table header
                drinksTable.setEnabled(false); //Prevents user from editing table
                
                JPanel drinksTablePanel = new JPanel();
                drinksTablePanel.setLayout(new BoxLayout(drinksTablePanel, BoxLayout.PAGE_AXIS));
                drinksTablePanel.add(drinksTableHeader);
                drinksTablePanel.add(drinksTable);
                menuPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between foodTable and drinksTable
                menuPanel.add(drinksTablePanel);
                menuPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between drinksTable and right of window

                //Set column max width
                drinksTable.getColumnModel().getColumn(0).setMaxWidth(25); //ID
                drinksTable.getColumnModel().getColumn(2).setMaxWidth(70); //Price

                //Add stock information
                Object[][] drinksData = new Object[7][3];
                for (int i=0; i<menuFood.size(); i++){
                    if(i>2){
                        drinksData[i+1][0] = menuDrinks.get(i).getId();
                        drinksData[i+1][1] = menuDrinks.get(i).getName();
                        drinksData[i+1][2] = df.format(menuDrinks.get(i).getPrice());
                    } else {
                        drinksData[i][0] = menuDrinks.get(i).getId();
                        drinksData[i][1] = menuDrinks.get(i).getName();
                        drinksData[i][2] = df.format(menuDrinks.get(i).getPrice());
                    }
                }
                for (int i=0; i<drinksData.length; i++) drinksTableModel.addRow(drinksData[i]); //Counts number of rows in the array and add those to table

            /*** selectionPanel *****************************************************************************************************
             *** - Seperates the IDTextField, quantityTextField, addButton */
            JPanel selectionPanel = new JPanel();
            selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.LINE_AXIS));
            mainPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between menuPanel and selectionPanel
            mainPanel.add(selectionPanel);

                /*** IDTextField ****************************************************************************************************
                 *** - Allows input of the ID of the selected stock */
                JLabel IDLabel = new JLabel("Enter ID: ");
                titleLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
                JTextField IDTextField = new JTextField("");
                IDTextField.setPreferredSize(new Dimension(50, 20));
                IDTextField.setMaximumSize(new Dimension(50, 20));
                selectionPanel.add(Box.createHorizontalGlue()); //Pushes all elements to the right
                selectionPanel.add(IDLabel);
                selectionPanel.add(IDTextField);
                
                /*** quantityTextField **********************************************************************************************
                 *** - Allows input of the quantity of the selected stock */
                JLabel quantityLabel = new JLabel("Enter Quantity: ");
                titleLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
                JTextField quantityTextField = new JTextField("");
                quantityTextField.setPreferredSize(new Dimension(50, 20));
                quantityTextField.setMaximumSize(new Dimension(50, 20));
                selectionPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between IDTextField and quantityLabel
                selectionPanel.add(quantityLabel);
                selectionPanel.add(quantityTextField);

                /*** addButton ******************************************************************************************************
                 *** - Validates the selected stock and asks for variations via Pop-up*/
                JButton addButton = new JButton("ADD");
                addButton.setPreferredSize(new Dimension(80, 20));
                addButton.setMaximumSize(new Dimension(80, 20));
                selectionPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between quantityTextField and addButton
                selectionPanel.add(addButton);
                selectionPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between addButton and right of window

                addButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) {
                    if (IDTextField.getText().equals("") || quantityTextField.getText().equals("")){
                        JOptionPane.showConfirmDialog(mainFrame,
                        "Incomplete input!",
                        "ERROR",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                        return;
                    } else if (!(pay.checkIfInteger(IDTextField.getText()) && pay.checkIfInteger(quantityTextField.getText()))){
                        JOptionPane.showConfirmDialog(mainFrame,
                        "Incorrect input!",
                        "ERROR",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                        return;
                    }  

                    int choosenID = Integer.valueOf(IDTextField.getText());
                    int choosenQuantity = Integer.valueOf(quantityTextField.getText());

                    if (!(((choosenID>=101 && choosenID<=106) || (choosenID>=201 && choosenID<=206)) && (choosenQuantity>0))){
                        JOptionPane.showConfirmDialog(mainFrame,
                        "Input out of range!",
                        "ERROR",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    
                    IDTextField.setText("");
                    quantityTextField.setText("");

                    String[] burgerVar = {"Normal", "Extra Cheese", "Extra Lettuce", "No Pickle"};
                    String[] chickenVar = {"Regular", "Spicy", "Extra Spicy", "Cheesy"};
                    String[] softDrinksVar = {"Small", "Regular", "Large"};
                    String[] coffeeVar = {"Hot", "Iced"};

                    Object selectedOption = null;
                    //FOOD
                    if (choosenID>=101 && choosenID<=106){
                        int pos = choosenID-101; //position of menuFood Arraylist
                        if (pos < 3){
                            selectedOption = JOptionPane.showInputDialog(null, "Please choose " + menuFood.get(pos).getName() + " variation", "Selection", JOptionPane.DEFAULT_OPTION, null, burgerVar, "0");
                            if (selectedOption != null){
                                Burger tempBurger = new Burger(menuFood.get(pos).getId(), menuFood.get(pos).getName(), menuFood.get(pos).getPrice(), choosenQuantity, selectedOption.toString());     
                                ordersList.add(new Order(tempBurger));
                            }
                        }else{
                            selectedOption = JOptionPane.showInputDialog(null, "Please choose " + menuFood.get(pos).getName() + " variation", "Selection", JOptionPane.DEFAULT_OPTION, null, chickenVar, "0");
                            if (selectedOption != null){
                                Chicken tempChicken = new Chicken(menuFood.get(pos).getId(), menuFood.get(pos).getName(), menuFood.get(pos).getPrice(), choosenQuantity, selectedOption.toString());     
                                ordersList.add(new Order(tempChicken));
                            }
                        }  
                    //DRINKS
                    } else if (choosenID>=201 && choosenID<=206){
                        int pos = choosenID-201; //position of menuDrinks Arraylist
                        if (pos < 3){
                            selectedOption = JOptionPane.showInputDialog(null, "Please choose " + menuDrinks.get(pos).getName() + " variation", "Selection", JOptionPane.DEFAULT_OPTION, null, softDrinksVar, "0");
                            if (selectedOption != null){
                                SoftDrinks tempSoftDrinks = new SoftDrinks(menuDrinks.get(pos).getId(), menuDrinks.get(pos).getName(), menuDrinks.get(pos).getPrice(), choosenQuantity, selectedOption.toString());     
                                ordersList.add(new Order(tempSoftDrinks));
                            }
                        }else{
                            selectedOption = JOptionPane.showInputDialog(null, "Please choose " + menuDrinks.get(pos).getName() + " variation", "Selection", JOptionPane.DEFAULT_OPTION, null, coffeeVar, "0");
                            if (selectedOption != null){
                                Coffee tempCoffee = new Coffee(menuDrinks.get(pos).getId(), menuDrinks.get(pos).getName(), menuDrinks.get(pos).getPrice(), choosenQuantity, selectedOption.toString());     
                                ordersList.add(new Order(tempCoffee));
                            }
                        }
                    }
                    refreshOrdersTable();
                }});

            /*** ordersPanel ********************************************************************************************************
             *** - Holds the ordersScrollPane, ordersTable */
            JPanel ordersPanel = new JPanel();
            ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.PAGE_AXIS));
            mainPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between selectionPanel and ordersPanel
            mainPanel.add(ordersPanel);

                /*** ordersTable ****************************************************************************************************
                 *** - Holds the data for orders */
                ordersTableModel = new DefaultTableModel() {
                    boolean[] canEdit = {false, false, false, false, false, false, true}; //Only REMOVE column can be interacted with
        
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                String ordersHeaderData[] = {"NO","ID","ORDER","QUANTITY","PRICE (RM)","DETAILS",""};
                ordersTableModel.setColumnIdentifiers(ordersHeaderData);

                ordersTable = new JTable(ordersTableModel);
                JTableHeader ordersTableHeader = ordersTable.getTableHeader();
                ordersTableHeader.setReorderingAllowed(false); //Prevents user from editing table header
                ordersTableHeader.setResizingAllowed(false); //Prevents user from editing table header

                JScrollPane ordersScrollPane = new JScrollPane(ordersTable); //Adds the ordersTable inside of a JScrollPane 
                ordersScrollPane.setPreferredSize(new Dimension(ordersScrollPane.getPreferredSize().width,150));
                ordersScrollPane.setMaximumSize(new Dimension(ordersScrollPane.getMaximumSize().width,150));
                ordersPanel.add(ordersTableHeader);
                ordersPanel.add(ordersScrollPane);

                //Button in the rightmost column that will remove the row
                Action delete = new AbstractAction(){
                    public void actionPerformed(ActionEvent e){
                        int modelRow = Integer.valueOf( e.getActionCommand() );
                        ordersList.remove(modelRow);
                        refreshOrdersTable();
                    }
                };
                
                ButtonColumn buttonColumn = new ButtonColumn(ordersTable, delete, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);

                //Set column max width
                ordersTable.getColumnModel().getColumn(0).setMaxWidth(25); //No
                ordersTable.getColumnModel().getColumn(1).setMaxWidth(25); //ID
                ordersTable.getColumnModel().getColumn(3).setMaxWidth(70); //Quantity
                ordersTable.getColumnModel().getColumn(4).setMaxWidth(80); //Price (RM)
                ordersTable.getColumnModel().getColumn(6).setMaxWidth(90); //Remove
                ordersTable.getColumnModel().getColumn(6).setPreferredWidth(90); //Remove
                
            /*** pricePanel *********************************************************************************************************
             *** - Positions the priceLabel */
            JPanel pricePanel = new JPanel();
            pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.LINE_AXIS));
            mainPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between ordersPanel and pricePanel
            mainPanel.add(pricePanel);

                /*** priceLabel *****************************************************************************************************
                 *** - Displays the total price of orders */
                priceLabel = new JLabel("Total Price: RM0.00");
                priceLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
                priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //Centers label horizontally
                pricePanel.add(Box.createHorizontalGlue()); //Pushes all elements to the right
                pricePanel.add(priceLabel);
                pricePanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between priceLabel and right of window

            /*** purchasePanel ******************************************************************************************************
             *** - Seperates the resetButton, payButton */
            JPanel purchasePanel = new JPanel();
            purchasePanel.setLayout(new BoxLayout(purchasePanel, BoxLayout.LINE_AXIS));
            mainPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between pricePanel and purchasePanel
            mainPanel.add(purchasePanel);
            mainPanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between purchasePanel and bottom of window

                /*** resetButton ****************************************************************************************************
                 *** - Empties the orders list */
                JButton resetButton = new JButton("RESET");
                resetButton.setPreferredSize(new Dimension(120, 20));
                resetButton.setMaximumSize(new Dimension(120, 20));
                purchasePanel.add(Box.createHorizontalGlue()); //Pushes all elements to the right
                purchasePanel.add(resetButton);

                resetButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) {
                    int n = ordersList.size();
                    for(int i=0; i<n; i++){ ordersList.remove(0); }
                    refreshOrdersTable();
                }});

                /*** purchaseButton *************************************************************************************************
                 *** - Pays for all the orders */
                JButton payButton = new JButton("PAY");
                payButton.setPreferredSize(new Dimension(120, 20));
                payButton.setMaximumSize(new Dimension(120, 20));
                purchasePanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between resetButton and payButton
                purchasePanel.add(payButton);
                purchasePanel.add(Box.createRigidArea(new Dimension(10,10))); //Spacing between payButton and right of window

                payButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) {
                    int n = ordersList.size();
                    if (n<=0) {
                        JOptionPane.showConfirmDialog(mainFrame,
                        "Please order something first!",
                        "ERROR",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                        return;
                    }

                    String inputText;
                    boolean cancelled = false;
                    while(pay.getTotalPaid() < pay.getTotalPrice() && !cancelled){
                        inputText = JOptionPane.showInputDialog(mainFrame,
                        "Please enter RM"+df.format(pay.balance())+" more",
                        "INFO",
                        JOptionPane.INFORMATION_MESSAGE);
                        
                        if(inputText == null) cancelled = true;
                        if(pay.checkIfDouble(inputText)) pay.addTotalPayment(Double.parseDouble(inputText));
                    }
                    if(cancelled){
                        pay.setTotalPaid(0.0);
                        return;
                    } 

                    JOptionPane.showConfirmDialog(mainFrame,
                    "Balance of RM"+df.format(pay.balance()*-1)+" has been returned", //change to make the balance into positive value
                    "INFO",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

                    for(int i=0; i<n; i++) ordersList.remove(0);
                    refreshOrdersTable();
                }});

        /****************************************************************************************************************************/

        //Show the window at the centre of screen
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    //Redisplay Orders Table
    void refreshOrdersTable(){
        int n = ordersList.size();
        ordersTableModel.setRowCount(0); //Removes all rows

        //Set the "No." column to the proper values
        for (int i=0; i<n; i++){
            Object[] row = new Object[7];
            row[0] = i+1;
            row[1] = ordersList.get(i).getId();
            row[2] = ordersList.get(i).getName();
            row[3] = ordersList.get(i).getQuantity();
            row[4] = df.format(ordersList.get(i).getPrice()*ordersList.get(i).getQuantity()); //display price*quantity
            row[5] = ordersList.get(i).getVariation();
            row[6] = "REMOVE";

            ordersTableModel.addRow(row);
        }

        pay.updateTotalPrice();
        priceLabel.setText("Total Price: RM" + df.format(pay.getTotalPrice()));
        ordersTable.repaint();
    }
}