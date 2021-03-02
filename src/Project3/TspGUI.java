package Project3;

import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <pre>
 * File             TspGUI.java
 * Description      
 * Platform         PC, NetBeans 8.2, jdk 1.8.0_191, Windows 10
 * Class            CS143A, Spring 2019
 * Date             5/27/2019
 * History Log
 * Hours            
 * @version         1.0.0
 * @see             java.util.ArrayList
 * @author          <i>Josh Watts</i>
 * Images for academic purposes only and is from:
 * 
 * icon from
 * 
 * </pre>
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class TspGUI extends javax.swing.JFrame 
{
    ReadFromFile myReader = new ReadFromFile();
    WriteToFile myWriter = new WriteToFile();
    String fileName = "src/Cities/Cities5.txt";
    ArrayList<WeightedEdge> edgeArray = new ArrayList<>();
    ArrayList<String> namesArray = new ArrayList<>();
    //ArrayList<String> tempArray = new ArrayList<>();
    ArrayList<ArrayList<Double>> tempArray = new ArrayList<>();
    ArrayList<Integer> pathList = new ArrayList<>();
    private long startTime, stopTime, timePassed;  //for timing
    WeightedGraph graph = null;
    final double epsilon = .00000001;
    final int MAX_SIZE = 100;
    final String FILE_PATH = "src/Cities";
    DecimalFormat decimal = new DecimalFormat("#.##");
    
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     TspGUI()
    *	Description     Default constructor--creates form, sets add
    *                   button as default, sets icon of form, set focus, 
    *                   reads from the file.
    *   @see            java.awt.Toolkit
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public TspGUI() 
    {
        initComponents();
        //set defualt button
        this.getRootPane().setDefaultButton(calculateJButton);
        //center
        this.setLocationRelativeTo(null);
        //set icon
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/SalespersonIcon.png"));
        //set title
        this.setTitle("Project #3--Traveling Salesperson");
        
        //read file into array list
        myReader.readFromFile(fileName);
        namesArray = myReader.getStringArr();
        tempArray = myReader.getNumberArr();
        loadJList();
        
        //make list of edges
        makeEdgeList(namesArray, tempArray);
        
        //make weighted graph object
        graph = new WeightedGraph(namesArray, edgeArray);
        
        //findPaths();
        //getPath();
        calculateJButton.doClick();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getPath()
     * Description  check which radio button is selected and find the path
     *              using the correct algorithm
     * @author      <i>Joshua Watts</i>
     * Date         5/31/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void getPath()
    {
        if(bruteForceJRadioButton.isSelected())//brute force method
        {
            methodJLabel.setText("Brute Force");
            startTime = System.nanoTime();//times should be in class where brute force is found
            pathList = graph.getBruteForcePath();
            stopTime = System.nanoTime();
            timePassed = stopTime - startTime;
        }
        else if(nearestJRadioButton.isSelected())//nearest neighbor method
        {
            methodJLabel.setText("Nearest Neighbor");
            startTime = System.nanoTime();
            pathList = graph.getNearestNeighbourPath();
            stopTime = System.nanoTime();
            timePassed = stopTime - startTime;
        }
        else //sorted edges method
        {
            methodJLabel.setText("Sorted Edges");
            startTime = System.nanoTime();
            pathList = graph.getSortedEdgePath();
            stopTime = System.nanoTime();
            timePassed = stopTime - startTime;
        }
        displayStats();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       makeEdgeList()
     * Description  check which radio button is selected and find the path
     *              using the correct algorithm
     * @param       names--ArrayList-names of vertices
     * @param       numStr--2D-ArrayList, coordinate points
     * @author      <i>Joshua Watts</i>
     * Date         5/30/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void makeEdgeList(ArrayList<String> names, ArrayList<ArrayList<Double>> numStr)
    {
        edgeArray.clear();
        double dist = 0;
        for(int i = 0; i < numStr.size(); i++)
        {
            for(int j = 0; j < numStr.size(); j++)
            {
                dist = distance(numStr.get(i).get(0), numStr.get(i).get(1), numStr.get(j).get(0), numStr.get(j).get(1));
                //if(dist > epsilon)   //if dist = 0, looking at same point, dont add
                    edgeArray.add(new WeightedEdge(i, j, dist));
            }
        }
            //has not made weighted graph yet
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       distance()
     * Description  distance from point 1 to point 2
     * @param       x1-double, x value of point 1
     * @param       y1-double, y value of point 1
     * @param       x2-double, x value of point 2
     * @param       y2-double, y value of point 2
     * @return      double-distance from point 1 to point 2
     * @author      <i>Joshua Watts</i>
     * Date         5/30/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public double distance(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       displayStats()
     * Description  input text into the statistics tab
     * @author      <i>Joshua Watts</i>
     * Date         5/31/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void displayStats()
    {
        if(graph == null)
        {
            clearStats();
        }
        else
        {   
            pathJTextArea.setText(graph.getPathString(pathList));
            costJTextField.setText(decimal.format(graph.getPathCost(pathList)));
            timeJTextField.setText(String.valueOf(timePassed));
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       clearStats()
     * Description  clear the statistics information
     * @author      <i>Joshua Watts</i>
     * Date         6/2/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void clearStats()
    {
        pathJTextArea.setText("");
        costJTextField.setText("");
        timeJTextField.setText("");
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       loadJList()
     * Description  fill up the Jlist with the names and points of all cities
     * @author      <i>Joshua Watts</i>
     * Date         6/2/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void loadJList()
    {
        if(!namesArray.isEmpty() && !tempArray.isEmpty())
        {
            Vector<String> displayVec = new Vector<>();
            for(int i = 0; i < namesArray.size(); i++)
            {
                displayVec.add(namesArray.get(i) + " (" + tempArray.get(i).get(0)
                    + ", " + tempArray.get(i).get(1) + ")");
            }
            cityJList.setListData(displayVec);
            cityJList.setSelectedIndex(0);
        }
        else
        {
            cityJList.setListData(new Vector<String>());
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       checkEnabled()
     * Description  check which buttons/menu items should be enabled based on
     *              size of verticies
     * @author      <i>Joshua Watts</i>
     * Date         6/1/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void checkEnabled()
    {
        if(graph == null || graph.vertices.size() < 1)
        {
            removeJButton.setEnabled(false);
            removeJMenuItem.setEnabled(false);
            printJMenuItem.setEnabled(false);
            calculateJButton.setEnabled(false);
            findPathJMenuItem.setEnabled(false);
            bruteForceJRadioButton.setEnabled(true);
            bruteForceJRadioButtonMenuItem.setEnabled(true);
        }
        else if(graph.vertices.size() < 3)
        {
            removeJButton.setEnabled(true);
            removeJMenuItem.setEnabled(true);
            printJMenuItem.setEnabled(false);
            calculateJButton.setEnabled(false);
            findPathJMenuItem.setEnabled(false);
            bruteForceJRadioButton.setEnabled(true);
            bruteForceJRadioButtonMenuItem.setEnabled(true);
        }
        else if(graph.vertices.size() <= 5)
        {
            removeJButton.setEnabled(true);
            removeJMenuItem.setEnabled(true);
            printJMenuItem.setEnabled(true);
            calculateJButton.setEnabled(true);
            findPathJMenuItem.setEnabled(true);
            bruteForceJRadioButton.setEnabled(true);
            bruteForceJRadioButtonMenuItem.setEnabled(true);
        }
        else
        {
            if(bruteForceJRadioButton.isSelected())
            {
                nearestJRadioButton.setSelected(true);
                nearestNeighborJRadioButtonMenuItem.setSelected(true);
                methodJLabel.setText("Nearest Neighbor");
            }
            printJMenuItem.setEnabled(true);
            removeJButton.setEnabled(true);
            removeJMenuItem.setEnabled(true);
            calculateJButton.setEnabled(true);
            findPathJMenuItem.setEnabled(true);
            bruteForceJRadioButton.setEnabled(false);
            bruteForceJRadioButtonMenuItem.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        methodButtonGroup = new javax.swing.ButtonGroup();
        fileMethodButtonGroup = new javax.swing.ButtonGroup();
        backgroundJPanel = new javax.swing.JPanel();
        addJPanel = new javax.swing.JPanel();
        fromFileJButton = new javax.swing.JButton();
        randomJButton = new javax.swing.JButton();
        cityJPanel = new javax.swing.JPanel();
        addJButton = new javax.swing.JButton();
        removeJButton = new javax.swing.JButton();
        cityJScrollPane = new javax.swing.JScrollPane();
        cityJList = new javax.swing.JList<>();
        statisticsJPanel = new javax.swing.JPanel();
        pathJLabel = new javax.swing.JLabel();
        pathJScrollPane = new javax.swing.JScrollPane();
        pathJTextArea = new javax.swing.JTextArea();
        costJLabel = new javax.swing.JLabel();
        costJTextField = new javax.swing.JTextField();
        timeJLabel = new javax.swing.JLabel();
        timeJTextField = new javax.swing.JTextField();
        methodJLabel = new javax.swing.JLabel();
        methodJPanel = new javax.swing.JPanel();
        bruteForceJRadioButton = new javax.swing.JRadioButton();
        nearestJRadioButton = new javax.swing.JRadioButton();
        sortedJRadioButton = new javax.swing.JRadioButton();
        calculateJButton = new javax.swing.JButton();
        exitJButton = new javax.swing.JButton();
        titleJLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tspJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        openJMenuItem = new javax.swing.JMenuItem();
        saveJMenuItem = new javax.swing.JMenuItem();
        clearJMenuItem = new javax.swing.JMenuItem();
        printJSeparator = new javax.swing.JPopupMenu.Separator();
        printJMenuItem = new javax.swing.JMenuItem();
        printFormJMenuItem = new javax.swing.JMenuItem();
        exitJSeparator = new javax.swing.JPopupMenu.Separator();
        exitJMenuItem = new javax.swing.JMenuItem();
        methodJMenu = new javax.swing.JMenu();
        bruteForceJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        nearestNeighborJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        sortedEdgeJRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        actionJMenu = new javax.swing.JMenu();
        addJMenuItem = new javax.swing.JMenuItem();
        removeJMenuItem = new javax.swing.JMenuItem();
        findPathJMenuItem = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundJPanel.setBackground(new java.awt.Color(153, 204, 255));

        addJPanel.setBackground(new java.awt.Color(153, 204, 255));
        addJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Cities"));

        fromFileJButton.setBackground(new java.awt.Color(153, 255, 255));
        fromFileJButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        fromFileJButton.setMnemonic('i');
        fromFileJButton.setText("From File");
        fromFileJButton.setToolTipText("Open a new set of cities from an external file");
        fromFileJButton.setNextFocusableComponent(randomJButton);
        fromFileJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromFileJButtonActionPerformed(evt);
            }
        });

        randomJButton.setBackground(new java.awt.Color(153, 255, 255));
        randomJButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        randomJButton.setMnemonic('e');
        randomJButton.setText("Create Random");
        randomJButton.setToolTipText("Create random cities with bounds provided by the user");
        randomJButton.setNextFocusableComponent(addJButton);
        randomJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addJPanelLayout = new javax.swing.GroupLayout(addJPanel);
        addJPanel.setLayout(addJPanelLayout);
        addJPanelLayout.setHorizontalGroup(
            addJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fromFileJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(randomJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        addJPanelLayout.setVerticalGroup(
            addJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fromFileJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(randomJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cityJPanel.setBackground(new java.awt.Color(153, 204, 255));
        cityJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cities"));

        addJButton.setBackground(new java.awt.Color(153, 255, 255));
        addJButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        addJButton.setMnemonic('d');
        addJButton.setText("Add");
        addJButton.setToolTipText("Add a new city to the list");
        addJButton.setNextFocusableComponent(removeJButton);
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });

        removeJButton.setBackground(new java.awt.Color(153, 255, 255));
        removeJButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        removeJButton.setMnemonic('m');
        removeJButton.setText("Remove");
        removeJButton.setToolTipText("Remove the currently selected city");
        removeJButton.setNextFocusableComponent(calculateJButton);
        removeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeJButtonActionPerformed(evt);
            }
        });

        cityJList.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cityJList.setToolTipText("Current cities");
        cityJScrollPane.setViewportView(cityJList);

        javax.swing.GroupLayout cityJPanelLayout = new javax.swing.GroupLayout(cityJPanel);
        cityJPanel.setLayout(cityJPanelLayout);
        cityJPanelLayout.setHorizontalGroup(
            cityJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cityJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cityJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cityJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(cityJPanelLayout.createSequentialGroup()
                        .addComponent(addJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeJButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cityJPanelLayout.setVerticalGroup(
            cityJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cityJPanelLayout.createSequentialGroup()
                .addComponent(cityJScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cityJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        statisticsJPanel.setBackground(new java.awt.Color(153, 204, 255));
        statisticsJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Statistics"));

        pathJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        pathJLabel.setText("Path:");

        pathJTextArea.setEditable(false);
        pathJTextArea.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.disabledBackground"));
        pathJTextArea.setColumns(20);
        pathJTextArea.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        pathJTextArea.setLineWrap(true);
        pathJTextArea.setRows(5);
        pathJTextArea.setToolTipText("Path of the cycle");
        pathJTextArea.setWrapStyleWord(true);
        pathJScrollPane.setViewportView(pathJTextArea);

        costJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        costJLabel.setText("Cost:");

        costJTextField.setEditable(false);
        costJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        costJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        costJTextField.setToolTipText("Cost of cycle");

        timeJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        timeJLabel.setText("Time(in nanoseconds):");

        timeJTextField.setEditable(false);
        timeJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        timeJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        timeJTextField.setToolTipText("Time to find cycle");

        methodJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        methodJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        methodJLabel.setText("Brute Force");
        methodJLabel.setToolTipText("Mehtod used");

        javax.swing.GroupLayout statisticsJPanelLayout = new javax.swing.GroupLayout(statisticsJPanel);
        statisticsJPanel.setLayout(statisticsJPanelLayout);
        statisticsJPanelLayout.setHorizontalGroup(
            statisticsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsJPanelLayout.createSequentialGroup()
                .addGroup(statisticsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statisticsJPanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(pathJLabel)
                        .addGap(0, 67, Short.MAX_VALUE))
                    .addGroup(statisticsJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(statisticsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(methodJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costJTextField)
                            .addComponent(timeJTextField)
                            .addComponent(pathJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statisticsJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timeJLabel)
                .addGap(20, 20, 20))
            .addGroup(statisticsJPanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(costJLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        statisticsJPanelLayout.setVerticalGroup(
            statisticsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statisticsJPanelLayout.createSequentialGroup()
                .addComponent(methodJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathJScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(costJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(costJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(timeJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        methodJPanel.setBackground(new java.awt.Color(153, 204, 255));
        methodJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Method"));

        bruteForceJRadioButton.setBackground(new java.awt.Color(153, 204, 255));
        methodButtonGroup.add(bruteForceJRadioButton);
        bruteForceJRadioButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        bruteForceJRadioButton.setMnemonic('b');
        bruteForceJRadioButton.setSelected(true);
        bruteForceJRadioButton.setText("Brute Force");
        bruteForceJRadioButton.setToolTipText("Use the brute force method (for 5 cities and below)");
        bruteForceJRadioButton.setNextFocusableComponent(nearestJRadioButton);
        bruteForceJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bruteForceJRadioButtonActionPerformed(evt);
            }
        });

        nearestJRadioButton.setBackground(new java.awt.Color(153, 204, 255));
        methodButtonGroup.add(nearestJRadioButton);
        nearestJRadioButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        nearestJRadioButton.setMnemonic('n');
        nearestJRadioButton.setText("Nearest Neighbor");
        nearestJRadioButton.setToolTipText("Use the nearest neighbor method");
        nearestJRadioButton.setNextFocusableComponent(sortedJRadioButton);
        nearestJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nearestJRadioButtonActionPerformed(evt);
            }
        });

        sortedJRadioButton.setBackground(new java.awt.Color(153, 204, 255));
        methodButtonGroup.add(sortedJRadioButton);
        sortedJRadioButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        sortedJRadioButton.setMnemonic('s');
        sortedJRadioButton.setText("Sorted Edge");
        sortedJRadioButton.setToolTipText("Use the sorted edge method");
        sortedJRadioButton.setNextFocusableComponent(fromFileJButton);
        sortedJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortedJRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout methodJPanelLayout = new javax.swing.GroupLayout(methodJPanel);
        methodJPanel.setLayout(methodJPanelLayout);
        methodJPanelLayout.setHorizontalGroup(
            methodJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(methodJPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(methodJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sortedJRadioButton)
                    .addComponent(nearestJRadioButton)
                    .addComponent(bruteForceJRadioButton))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        methodJPanelLayout.setVerticalGroup(
            methodJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(methodJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bruteForceJRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nearestJRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sortedJRadioButton)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        calculateJButton.setBackground(new java.awt.Color(153, 255, 255));
        calculateJButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        calculateJButton.setMnemonic('p');
        calculateJButton.setText("Find Path");
        calculateJButton.setToolTipText("Calculate the lowest cost path with the method selected");
        calculateJButton.setNextFocusableComponent(exitJButton);
        calculateJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateJButtonActionPerformed(evt);
            }
        });

        exitJButton.setBackground(new java.awt.Color(153, 255, 255));
        exitJButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        exitJButton.setMnemonic('x');
        exitJButton.setText("Exit");
        exitJButton.setToolTipText("Close the form");
        exitJButton.setNextFocusableComponent(bruteForceJRadioButton);
        exitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJButtonActionPerformed(evt);
            }
        });

        titleJLabel.setFont(new java.awt.Font("Rockwell Condensed", 0, 32)); // NOI18N
        titleJLabel.setText("Traveling Salesman Problem");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/RunSmall.png"))); // NOI18N

        javax.swing.GroupLayout backgroundJPanelLayout = new javax.swing.GroupLayout(backgroundJPanel);
        backgroundJPanel.setLayout(backgroundJPanelLayout);
        backgroundJPanelLayout.setHorizontalGroup(
            backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(methodJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(cityJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addComponent(calculateJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(statisticsJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(titleJLabel)
                .addGap(67, 67, 67)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(63, 63, 63))
        );
        backgroundJPanelLayout.setVerticalGroup(
            backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleJLabel)
                    .addComponent(jLabel1))
                .addGap(11, 11, 11)
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addComponent(methodJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(106, Short.MAX_VALUE))
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cityJPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                                .addComponent(statisticsJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(calculateJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(exitJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18))))
        );

        fileJMenu.setText("File");

        openJMenuItem.setMnemonic('o');
        openJMenuItem.setText("Open");
        openJMenuItem.setToolTipText("Open a new set of cities from an external file");
        openJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(openJMenuItem);

        saveJMenuItem.setMnemonic('v');
        saveJMenuItem.setText("Save");
        saveJMenuItem.setToolTipText("Save the current cities to an external file");
        saveJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(saveJMenuItem);

        clearJMenuItem.setMnemonic('C');
        clearJMenuItem.setText("Clear");
        clearJMenuItem.setToolTipText("Clear the graph and all fields");
        clearJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(clearJMenuItem);
        fileJMenu.add(printJSeparator);

        printJMenuItem.setMnemonic('r');
        printJMenuItem.setText("Print");
        printJMenuItem.setToolTipText("Print the current statistics");
        printJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printJMenuItem);

        printFormJMenuItem.setMnemonic('f');
        printFormJMenuItem.setText("Print Form");
        printFormJMenuItem.setToolTipText("Print the form as a GUI");
        printFormJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printFormJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printFormJMenuItem);
        fileJMenu.add(exitJSeparator);

        exitJMenuItem.setMnemonic('x');
        exitJMenuItem.setText("Exit");
        exitJMenuItem.setToolTipText("Close the application");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenuItem);

        tspJMenuBar.add(fileJMenu);

        methodJMenu.setText("Method");
        methodJMenu.setToolTipText("Select the type of method to use");

        fileMethodButtonGroup.add(bruteForceJRadioButtonMenuItem);
        bruteForceJRadioButtonMenuItem.setMnemonic('b');
        bruteForceJRadioButtonMenuItem.setSelected(true);
        bruteForceJRadioButtonMenuItem.setText("Brute Force");
        bruteForceJRadioButtonMenuItem.setToolTipText("Use the brute force method (for 5 cities and below)");
        bruteForceJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bruteForceJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        methodJMenu.add(bruteForceJRadioButtonMenuItem);

        fileMethodButtonGroup.add(nearestNeighborJRadioButtonMenuItem);
        nearestNeighborJRadioButtonMenuItem.setMnemonic('n');
        nearestNeighborJRadioButtonMenuItem.setText("Nearest Neighbor");
        nearestNeighborJRadioButtonMenuItem.setToolTipText("Use the nearest neighbor method");
        nearestNeighborJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nearestNeighborJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        methodJMenu.add(nearestNeighborJRadioButtonMenuItem);

        fileMethodButtonGroup.add(sortedEdgeJRadioButtonMenuItem);
        sortedEdgeJRadioButtonMenuItem.setMnemonic('s');
        sortedEdgeJRadioButtonMenuItem.setText("Sorted Edge");
        sortedEdgeJRadioButtonMenuItem.setToolTipText("Use the sorted edge method");
        sortedEdgeJRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortedEdgeJRadioButtonMenuItemActionPerformed(evt);
            }
        });
        methodJMenu.add(sortedEdgeJRadioButtonMenuItem);

        tspJMenuBar.add(methodJMenu);

        actionJMenu.setMnemonic('t');
        actionJMenu.setText("Action");
        actionJMenu.setToolTipText("");

        addJMenuItem.setMnemonic('d');
        addJMenuItem.setText("Add");
        addJMenuItem.setToolTipText("Add a new city");
        addJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(addJMenuItem);

        removeJMenuItem.setMnemonic('m');
        removeJMenuItem.setText("Remove");
        removeJMenuItem.setToolTipText("Remove the currently selected city");
        removeJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(removeJMenuItem);

        findPathJMenuItem.setMnemonic('p');
        findPathJMenuItem.setText("Find Path");
        findPathJMenuItem.setToolTipText("Calculate the lowest cost path with the method selected");
        findPathJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findPathJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(findPathJMenuItem);

        tspJMenuBar.add(actionJMenu);

        helpJMenu.setText("Help");

        aboutJMenuItem.setMnemonic('A');
        aboutJMenuItem.setText("About");
        aboutJMenuItem.setToolTipText("Open the about form");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenuItem);

        tspJMenuBar.add(helpJMenu);

        setJMenuBar(tspJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	aboutJMenuItemActionPerformed()
    *	Description     Open the about form
    *   @param          evt ActionEvent
    *   @author         <i>Josh Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutJMenuItemActionPerformed
        //show the about form
        AboutTSP myAbout = new AboutTSP(this, true);
        myAbout.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	fromFileJButtonActionPerformed()
    *	Description     call the readFromFile method which reads the text file
    *                   line by line and turns it into two arrayLists. This method
    *                   handles the exceptions and problems with files.
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @see            javax.swing.JFileChooser
    *   @see            javax.swing.filechooser.FileNameExtensionFilter
    *   @author         <i>Josh Watts</i>
    *	Date            5/27/2018
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void fromFileJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromFileJButtonActionPerformed
        //load new database
        JFileChooser chooser = new JFileChooser("src/Cities");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "text Files", "txt");
        chooser.setFileFilter(filter);
        int choice = chooser.showOpenDialog(null);
        if(choice == JFileChooser.APPROVE_OPTION)
        {
            namesArray = new ArrayList<>();
            tempArray = new ArrayList<>();
            File chosenFile = chooser.getSelectedFile();
            fileName = "src/Cities/" + chosenFile.getName();
            //read file into array list
            myReader.readFromFile(fileName);
            namesArray = myReader.getStringArr();
            tempArray = myReader.getNumberArr();
        
            
            if(namesArray == null || tempArray == null)   //if try to open an empty file
            {
                JOptionPane.showMessageDialog(null, "File you are trying to "
                        + "open is empty", " File Error", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                //make list of edges
                makeEdgeList(namesArray, tempArray);
                //make weighted graph object
                graph = new WeightedGraph(namesArray, edgeArray);
                loadJList();
                checkEnabled();
                clearStats();
                calculateJButton.doClick();
                //check what should be selected
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Unable to open file", " File "
                    + "Input Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_fromFileJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	randomJButtonActionPerformed()
    *	Description     open a form which allows the user to input the number
    *                   of points to be generated, and the bounds for x and y, 
    *                   then returns the name and points array
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @author         <i>Josh Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void randomJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomJButtonActionPerformed
        RandomPoints randPts = new RandomPoints();
        randPts.setVisible(true);
        namesArray = randPts.getNames();   //get new names array and new temp then redo edge and graph, check enabled
        tempArray = randPts.getValues();
        if(namesArray != null && tempArray != null)
        {
            //make list of edges
            makeEdgeList(namesArray, tempArray);
            //make weighted graph object
            graph = new WeightedGraph(namesArray, edgeArray);
            loadJList();
            checkEnabled();
            clearStats();
            calculateJButton.doClick();
            //check what should be selected
        }
    }//GEN-LAST:event_randomJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	addJButtonActionPerformed()
    *	Description     open a new form to read the x and y values, as well as
    *                   the name, then add that new point to the graph
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @see            javax.swing.JOptionPane
    *   @author         <i>Josh Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJButtonActionPerformed
        //pull up new form, validate inputs
        //add to names array and temp then redo edge and graph
        //check enabled
        if(namesArray.size() >= MAX_SIZE)
            JOptionPane.showMessageDialog(null, "Maximum number (" + MAX_SIZE 
                    + ") of cities reached/nDelete some cities if you "
                    + "wish to add more", "Max Cities", JOptionPane.INFORMATION_MESSAGE);
        else
        {
            AddCity myAdd = new AddCity(namesArray, tempArray);
            myAdd.setVisible(true);
            String name = myAdd.getName();   //get new names array and new temp then redo edge and graph, check enabled
            ArrayList<Double> doubArray = myAdd.getValue();
            if(name != null && doubArray != null)
            {
                namesArray.add(name);
                tempArray.add(doubArray);
                //make list of edges
                makeEdgeList(namesArray, tempArray);
                //make weighted graph object
                graph = new WeightedGraph(namesArray, edgeArray);
                loadJList();
                checkEnabled();
                clearStats();
                calculateJButton.doClick();
                cityJList.setSelectedIndex(namesArray.size() - 1);
                //check what should be selected
            }
        }
    }//GEN-LAST:event_addJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	removeJButtonActionPerformed()
    *	Description     remove the currently selected city and refresh the graph
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @see            javax.swing.JOptionPane
    *   @author         <i>Josh Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void removeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeJButtonActionPerformed
        //remove current index from name and temp then redo edge and graph
        //check enabled
        int index = cityJList.getSelectedIndex();
        //determine if user still wants to delte employee
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want"
                + " to delete city: "
                + namesArray.get(index) + " (" + tempArray.get(index).get(0) 
                + ", " + tempArray.get(index).get(1) +")?",
                "Delete City", JOptionPane.YES_NO_OPTION);

        if(result == JOptionPane.YES_OPTION)
        {
            namesArray.remove(index);
            tempArray.remove(index);
            //make list of edges
            makeEdgeList(namesArray, tempArray);
            //make weighted graph object
            graph = new WeightedGraph(namesArray, edgeArray);
            loadJList();
            checkEnabled();
            clearStats();
            calculateJButton.doClick();
        }
    }//GEN-LAST:event_removeJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	calculateJButtonActionPerformed()
    *	Description     find the path for the current method
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @author         <i>Josh Watts</i>
    *	Date            5/30/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void calculateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateJButtonActionPerformed
        //getPath, one with radsiob btons
        getPath();
        displayStats();
    }//GEN-LAST:event_calculateJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	exitJMenuItemActionPerformed()
    *	Description     close the form
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @author         <i>Josh Watts</i>
    *	Date            5/30/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	printFormJMenuItemActionPerformed()
    *	Description     uses Marty Hall's method to print the form as a GUI image
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @author         <i>Josh Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printFormJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printFormJMenuItemActionPerformed
        //Credit to: Marty Hall
        //print form as GUI image
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printFormJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	printJMenuItemActionPerformed()
    *	Description     print the surrent statistics as text
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @see            javax.swing.JOptionPane
    *   @author         <i>Josh Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printJMenuItemActionPerformed
        if(costJTextField.getText() == "")
            JOptionPane.showMessageDialog(null, "Fields are empty, please calculate "
                    + "to populate the fields", "Empty Fields",
                    JOptionPane.INFORMATION_MESSAGE);
        else
        {
            try 
            { 

                // print stats information
                JTextArea printStats = new JTextArea();
                //wrap the text so it is not one long line
                printStats.setLineWrap(true);
                printStats.setWrapStyleWord(true);
                String output = "";
                output = "Method: " + methodJLabel.getText()
                        + "\nPath: " + pathJTextArea.getText()
                        + "\nCost: " + costJTextField.getText()
                        + "\nTime: " + timeJTextField.getText();

                printStats.setText(output);
                try 
                {
                    printStats.print();
                }
                catch (PrinterException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Unable to print"
                            + " statistics.", "Print Error", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Unable to print"
                            + " statistics.", "Print Error", 
                            JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_printJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		bruteForceJRadioButtonActionPerformed()
    *	Description     link the bruteforce radio button the the menu one
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void bruteForceJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bruteForceJRadioButtonActionPerformed
        bruteForceJRadioButtonMenuItem.setSelected(true);
        calculateJButton.doClick();
//        methodJLabel.setText("Brute Force");
//        clearStats();
    }//GEN-LAST:event_bruteForceJRadioButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		nearestJRadioButtonActionPerformed()
    *	Description     link the nearest neighbor radio button the the menu one
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void nearestJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nearestJRadioButtonActionPerformed
        nearestNeighborJRadioButtonMenuItem.setSelected(true);
        calculateJButton.doClick();
//        methodJLabel.setText("Nearest Neighbor");
//        clearStats();
    }//GEN-LAST:event_nearestJRadioButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		sortedJRadioButtonActionPerformed()
    *	Description     link the sorted edge radio button the the menu one
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void sortedJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortedJRadioButtonActionPerformed
        sortedEdgeJRadioButtonMenuItem.setSelected(true);
        calculateJButton.doClick();
//        methodJLabel.setText("Sorted Edge");
//        clearStats();
    }//GEN-LAST:event_sortedJRadioButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		bruteForceJRadioButtonMenuItemActionPerformed()
    *	Description     link the bruteforce radio button the the menu one
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void bruteForceJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bruteForceJRadioButtonMenuItemActionPerformed
        bruteForceJRadioButton.setSelected(true);
        calculateJButton.doClick();
//        methodJLabel.setText("Brute Force");
//        clearStats();
    }//GEN-LAST:event_bruteForceJRadioButtonMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		nearestNeighborJRadioButtonMenuItemActionPerformed()
    *	Description     link the nearest neighbor radio button the the menu one
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void nearestNeighborJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nearestNeighborJRadioButtonMenuItemActionPerformed
        nearestJRadioButton.setSelected(true);
        calculateJButton.doClick();
//        methodJLabel.setText("Nearest Neighbor");
//        clearStats();
    }//GEN-LAST:event_nearestNeighborJRadioButtonMenuItemActionPerformed
   
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		sortedEdgeJRadioButtonMenuItemActionPerformed()
    *	Description     link the sorted edge radio button the the menu one
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void sortedEdgeJRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortedEdgeJRadioButtonMenuItemActionPerformed
        sortedJRadioButton.setSelected(true);
        calculateJButton.doClick();
//        methodJLabel.setText("Sorted Edge");
//        clearStats();
    }//GEN-LAST:event_sortedEdgeJRadioButtonMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		saveJMenuItemActionPerformed()
    *	Description     save the current cities to an external file
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void saveJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveJMenuItemActionPerformed
        //save the current points
        myWriter.saveToFile(FILE_PATH, namesArray, tempArray);
    }//GEN-LAST:event_saveJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		clearJMenuItemActionPerformed()
    *	Description     clear all arrayLists and text areas, set form to its 
    *                   default look
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void clearJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJMenuItemActionPerformed
        //reset arrayLists
        namesArray = new ArrayList<>();
        tempArray = new ArrayList<>();
        edgeArray = new ArrayList<>();
        graph = null;
        
        loadJList();
        timePassed = 0;
        //reset displayed values
        
        pathJTextArea.setText("");
        costJTextField.setText("");
        timeJTextField.setText("");
        bruteForceJRadioButton.setSelected(true);
        checkEnabled();
    }//GEN-LAST:event_clearJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		openJMenuItemActionPerformed()
    *	Description     open file to read from
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void openJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openJMenuItemActionPerformed
        fromFileJButton.doClick();
    }//GEN-LAST:event_openJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		exitJButtonActionPerformed()
    *	Description     close the form
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void exitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		addJMenuItemActionPerformed()
    *	Description     add a new city by calling the button
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void addJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJMenuItemActionPerformed
        addJButton.doClick();
    }//GEN-LAST:event_addJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		removeJMenuItemActionPerformed()
    *	Description     remove the current selected city by calling the button
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void removeJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeJMenuItemActionPerformed
        removeJButton.doClick();
    }//GEN-LAST:event_removeJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		findPathJMenuItemActionPerformed()
    *	Description     find the path by calling the jbutton
    *   @see            java.awt.event.ActionEvent
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void findPathJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findPathJMenuItemActionPerformed
        calculateJButton.doClick();
    }//GEN-LAST:event_findPathJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		main()
    *	Description     Create, load and display the splashscreen. Create a 
    *                   JFrame by invoking the default constructor
    *	@param		args are the command line strings
    *   @see            javax.swing.JOptionPane
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main(String args[]) 
    {
        //declare constant to hold the speed of the loader
        final int LOAD_SPEED = 10;
        //create the new splash screen called mySplash
        TspSplash mySplash = new TspSplash();
        //center splash
        mySplash.setLocationRelativeTo(null);
        //set icon 
        mySplash.setIconImage(Toolkit.getDefaultToolkit().getImage
        ("src/Images/Salesperson.png")); 
        //make splash visible
        mySplash.setVisible(true);
        
        try
        {
            //create a variable which will count loop to 100, incrementing by
            //one until it reaches 100
            for(int loader = 0; loader <= 100; loader++)
                {
                    //set speed of counter (miliseconds)
                    Thread.sleep(LOAD_SPEED);
                    //display the counter in the loading label
                    mySplash.loadingJLabel.setText("Loading " 
                            + Integer.toString(loader) + "%");
                    //increment the progress bar with the counter
                    mySplash.splashJProgressBar.setValue(loader);
                    //when the loop gets to 100, close the splash screen
                    if (loader == 100)
                    {
                        //when loader is done, close the splash screen
                        mySplash.dispose();
                    }
                }
        }
        catch (Exception exp)
        {
            JOptionPane.showMessageDialog(null, "Loading error--" 
                    + "Cannot load splash");
        }
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TspGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TspGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TspGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TspGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new TspGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JMenu actionJMenu;
    private javax.swing.JButton addJButton;
    private javax.swing.JMenuItem addJMenuItem;
    private javax.swing.JPanel addJPanel;
    private javax.swing.JPanel backgroundJPanel;
    private javax.swing.JRadioButton bruteForceJRadioButton;
    private javax.swing.JRadioButtonMenuItem bruteForceJRadioButtonMenuItem;
    private javax.swing.JButton calculateJButton;
    private javax.swing.JList<String> cityJList;
    private javax.swing.JPanel cityJPanel;
    private javax.swing.JScrollPane cityJScrollPane;
    private javax.swing.JMenuItem clearJMenuItem;
    private javax.swing.JLabel costJLabel;
    private javax.swing.JTextField costJTextField;
    private javax.swing.JButton exitJButton;
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JPopupMenu.Separator exitJSeparator;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.ButtonGroup fileMethodButtonGroup;
    private javax.swing.JMenuItem findPathJMenuItem;
    private javax.swing.JButton fromFileJButton;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.ButtonGroup methodButtonGroup;
    private javax.swing.JLabel methodJLabel;
    private javax.swing.JMenu methodJMenu;
    private javax.swing.JPanel methodJPanel;
    private javax.swing.JRadioButton nearestJRadioButton;
    private javax.swing.JRadioButtonMenuItem nearestNeighborJRadioButtonMenuItem;
    private javax.swing.JMenuItem openJMenuItem;
    private javax.swing.JLabel pathJLabel;
    private javax.swing.JScrollPane pathJScrollPane;
    private javax.swing.JTextArea pathJTextArea;
    private javax.swing.JMenuItem printFormJMenuItem;
    private javax.swing.JMenuItem printJMenuItem;
    private javax.swing.JPopupMenu.Separator printJSeparator;
    private javax.swing.JButton randomJButton;
    private javax.swing.JButton removeJButton;
    private javax.swing.JMenuItem removeJMenuItem;
    private javax.swing.JMenuItem saveJMenuItem;
    private javax.swing.JRadioButtonMenuItem sortedEdgeJRadioButtonMenuItem;
    private javax.swing.JRadioButton sortedJRadioButton;
    private javax.swing.JPanel statisticsJPanel;
    private javax.swing.JLabel timeJLabel;
    private javax.swing.JTextField timeJTextField;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JMenuBar tspJMenuBar;
    // End of variables declaration//GEN-END:variables
}
