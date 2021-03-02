package Project3;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <pre>
 * File             RandomPoints.java
 * Description      Generate random values with the range and amount provided 
 *                  by the user, validating all inputs. once they are made they
 *                  are converted into 2 decimal place doubles. The main GUI may
 *                  then recieve the random arrayList
 * Platform         PC, NetBeans 8.2, jdk 1.8.0_191, Windows 10
 * Class            CS142A, Winter 2019
 * Date             6/2/2019
 * History Log      
 * Hours            
 * @version         1.0.0
 * @see             java.util.ArrayList
 * @author          <i>Josh Watts</i>
 * Images for academic purposes only and is from:
 * </pre>
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class RandomPoints extends javax.swing.JDialog 
{
    ArrayList<String> namesArray = null;
    ArrayList<ArrayList<Double>> dblArray = null;
    final int MAX_POINTS = 93;
    final double MIN_VALUE = -1000;
    final double MAX_VALUE = 1000;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     RandomPoints()
    *	Description     Default constructor--creates form, sets generate
    *                   button as default
    *   @see            java.awt.Toolkit
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public RandomPoints() 
    {
        initComponents();
        this.getRootPane().setDefaultButton(generateJButton); //set buttonAdd as default
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage("src/Images/SalespersonIcon.png"));
        //set title
        this.setTitle("Project #3--Random Cities");
        //centers the form at start.
        setLocationRelativeTo(null);
        numberJTextField.requestFocus();
        //ArrayList<String> namesArray = null;
        //ArrayList<Double> dblArray = null;
        this.setModal(true);        //make form modal so it waits to complete before returning
        this.setAlwaysOnTop(true);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          getNames()
    *	Description     A getter for the names arrayList which contain the
    *                   names of the random cities
    *   @return         ArrayList- names of cities
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<String> getNames()
    {
        return namesArray;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          getValues()
    *	Description     A getter for the values arrayList which contain the
    *                   randomly generated points
    *   @return         ArrayList- names of cities
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<ArrayList<Double>> getValues()
    {
        return dblArray;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          generateRandomPoints()
    *	Description     generate the random values with the lower bound being 
    *                   determined by adding the lower val, and upper by multiplying
    *                   the net range(upper - lower). uses integer division to
    *                   truncate the values to 2 decimal places
    *   @param          numOfPts- number of points to generate
    *   @param          minX- lowest x value
    *   @param          maxX- highest x value
    *   @param          minY- lowest y value
    *   @param          maxY- highest y value
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void generateRandomPoints(int numOfPts, double minX, double maxX, double minY, double maxY)
    {
        namesArray = new ArrayList<>();
        dblArray = new ArrayList<>();
        Random myRand = new Random();
        
        double randX = 0;
        double randY = 0;
        String name = "";
        for(int i = 0; i < numOfPts; i++)
        {
            //Point2D newPoint = new Point2D.Double();
            //generate the random values with the lower bound being determined by 
            //adding the lower val. if it is a negative it will add the negative, 
            //therefore subtracting
            randX = (myRand.nextDouble() * (maxX - minX)) + minX;
            randX *= 100;               //remove the extra decimal places by
            randX = (int)randX / 1;     //multiplying by 100, truncating using int division
            randX /= 100;               //then returning back to a double
            
            randY = (myRand.nextDouble() * (maxY - minY)) + minY;
            randY *= 100;               //truncate p1
            randY = (int)randY / 1;     //p2
            randY /= 100;               //p3
            
            name = Character.toString((char) (i + 33));
            //newPoint.setLocation(randX, randY);
            if(!isDuplicate(randX, randY))
            {
                namesArray.add(name);
                dblArray.add(new ArrayList<>());
                dblArray.get(i).add(randX);
                dblArray.get(i).add(randY);
            }
            else    //if there is a dupolicate it wont be added, subtract 1 from i to gaurantee same number of points
                i--;
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          isDuplicate()
    *	Description     test if the point given is already in the list
    *   @param          xVal-double, xVal to test if its a duplicate
    *   @param          yVal-double, yVal to test if its a duplicate
    *   @return         boolean, true if duplicate, false if not
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isDuplicate(double xVal, double yVal)
    {
        boolean isDuplicate = false;
        if(dblArray.size() > 0)
        {
            for(int i = 0; i < dblArray.size() && !isDuplicate; i++)
                if(dblArray.get(i).get(0) == xVal && dblArray.get(i).get(1) == yVal)
                    isDuplicate = true;
        }
        return isDuplicate;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundJPanel = new javax.swing.JPanel();
        numberJLabel = new javax.swing.JLabel();
        numberJTextField = new javax.swing.JTextField();
        infoJLabel = new javax.swing.JLabel();
        minXJLabel = new javax.swing.JLabel();
        minXJTextField = new javax.swing.JTextField();
        maxXJLabel = new javax.swing.JLabel();
        maxXJTextField = new javax.swing.JTextField();
        minYJLabel = new javax.swing.JLabel();
        minYJTextField = new javax.swing.JTextField();
        maxYJLabel = new javax.swing.JLabel();
        maxYJTextField = new javax.swing.JTextField();
        generateJButton = new javax.swing.JButton();
        errorJScrollPane = new javax.swing.JScrollPane();
        errorJTextArea = new javax.swing.JTextArea();
        cancelJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        backgroundJPanel.setBackground(new java.awt.Color(153, 204, 255));

        numberJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        numberJLabel.setText("Enter the number of points you wish to create");

        numberJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        numberJTextField.setToolTipText("Number of cities you wish to randomly generate");

        infoJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        infoJLabel.setText("Enter the bounds for your random city points");

        minXJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        minXJLabel.setText("Minimum X Value:");

        minXJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        minXJTextField.setToolTipText("The lower x value bound");

        maxXJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        maxXJLabel.setText("Maximum X Value:");

        maxXJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        maxXJTextField.setToolTipText("The upper x value bound");

        minYJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        minYJLabel.setText("Minimum Y Value:");

        minYJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        minYJTextField.setToolTipText("The lower y value bound");

        maxYJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        maxYJLabel.setText("Maximum Y Value:");

        maxYJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        maxYJTextField.setToolTipText("The upper y value bound");

        generateJButton.setBackground(new java.awt.Color(153, 255, 255));
        generateJButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        generateJButton.setMnemonic('G');
        generateJButton.setText("Generate");
        generateJButton.setToolTipText("Generate random cities with the values provided");
        generateJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateJButtonActionPerformed(evt);
            }
        });

        errorJTextArea.setEditable(false);
        errorJTextArea.setColumns(20);
        errorJTextArea.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        errorJTextArea.setRows(5);
        errorJTextArea.setText("Error Messages:");
        errorJTextArea.setToolTipText("Text for any errors that may be generated");
        errorJScrollPane.setViewportView(errorJTextArea);

        cancelJButton.setBackground(new java.awt.Color(153, 255, 255));
        cancelJButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cancelJButton.setMnemonic('C');
        cancelJButton.setText("Cancel");
        cancelJButton.setToolTipText("Close the random cities form");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundJPanelLayout = new javax.swing.GroupLayout(backgroundJPanel);
        backgroundJPanel.setLayout(backgroundJPanelLayout);
        backgroundJPanelLayout.setHorizontalGroup(
            backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(numberJLabel))
                            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(numberJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errorJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                                        .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(minXJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(minXJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(minYJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(minYJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(30, 30, 30)
                                        .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(maxXJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(maxXJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(maxYJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(maxYJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                                        .addComponent(generateJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cancelJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(infoJLabel)))
                .addContainerGap())
        );
        backgroundJPanelLayout.setVerticalGroup(
            backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberJLabel)
                .addGap(3, 3, 3)
                .addComponent(numberJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(infoJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addComponent(minXJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minXJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addComponent(maxXJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxXJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addComponent(minYJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minYJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addComponent(maxYJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxYJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(errorJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	cancelJButtonActionPerformed()
    *	Description     Close the random points page, performing no action
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @author         <i>Josh Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed
        //close the edit form
        this.dispose();
    }//GEN-LAST:event_cancelJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	generateJButtonActionPerformed()
    *	Description     check all the inputs to make sure they are true number
    *                   values and then parse them and give them to the generate
    *                   method
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @author         <i>Josh Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void generateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateJButtonActionPerformed
        int numOfPoints = 0;
        double minX = 0;
        double maxX = 0;
        double minY = 0;
        double maxY = 0;
        String errorMessage = "";
        //set all to false so they become true as they are tested
        boolean isValidNum = false, isValidMinX = false, isValidMaxX = false,
                isValidMinY = false, isValidMaxY = false, error = false;
        //reset error message
        errorJTextArea.setText("");
        
        //validate entry
        try
        {
            //parse into correct type, if any cant be parsed, catch will engage
            //and first false value will be displayed
            numOfPoints = Integer.parseInt(numberJTextField.getText());
            isValidNum = true;
            minX = Double.parseDouble(minXJTextField.getText());
            isValidMinX = true;
            maxX = Double.parseDouble(maxXJTextField.getText());
            isValidMaxX = true;
            minY = Double.parseDouble(minYJTextField.getText());
            isValidMinY = true;
            maxY = Double.parseDouble(maxYJTextField.getText());
            isValidMaxY = true;
            
            
        }
        catch(NumberFormatException exp)
        {
            if(!isValidNum)
            {
                errorMessage = "Incorrect Value: Please only enter positive "
                        + "whole numbers for the amount of points to create.";
                numberJTextField.requestFocus();
                error = true;
            }
            else if(!isValidMinX)
            {
                errorMessage = "Incorrect Value: Please use a real number for "
                        + "minimum X value.";
                minXJTextField.requestFocus();
                error = true;
            }
            else if(!isValidMaxX)
            {
                errorMessage = "Incorrect Value: Please use a real number for "
                        + "maximum X value.";
                maxXJTextField.requestFocus();
                error = true;
            }
            else if(!isValidMinY)
            {
                errorMessage = "Incorrect Value: Please use a real number for "
                        + "minimum Y value.";
                minYJTextField.requestFocus();
                error = true;
            }
            else if(!isValidMaxY)
            {
                errorMessage = "Incorrect Value: Please use a real number for "
                        + "maximum Y value.";
                maxYJTextField.requestFocus();
                error = true;
            }
        }
        //range check
        if(numOfPoints <= 0 || numOfPoints > MAX_POINTS)
        {
            errorMessage = "Value out of range: Please only enter positive "
                    + "whole numbers less than " + MAX_POINTS + " for the "
                    + "amount of points to create.";
            numberJTextField.requestFocus();
            error = true;
        }
        else if(minX < MIN_VALUE)
        {
            errorMessage = "X value out of range: Please use a value greater"
                    + "than " + MIN_VALUE;
            minXJTextField.requestFocus();
            error = true;
        }
        else if(maxX > MAX_VALUE)
        {
            errorMessage = "X value out of range: Please use a value less"
                    + "than " + MAX_VALUE;
            maxXJTextField.requestFocus();
            error = true;
        }
        else if(minY < MIN_VALUE)
        {
            errorMessage = "Y value out of range: Please use a value greater"
                    + "than " + MIN_VALUE;
            minYJTextField.requestFocus();
            error = true;
        }
        else if(maxY > MAX_VALUE)
        {
            errorMessage = "Y value out of range: Please use a value less"
                    + "than " + MAX_VALUE;
            maxYJTextField.requestFocus();
            error = true;
        }
        //confirm lows are less than highs
        else if(minX >= maxX)
        {
            errorMessage = "Incorrect Order: Minimum X may not be less than or"
                    + " equal to the maximum X. Please fix it or switch the order.";
            minXJTextField.requestFocus();
            error = true;
        }
        else if(minY >= maxY)
        {
            errorMessage = "Incorrect Order: Minimum Y may not be less than or"
                    + " equal to the maximum Y. Please fix it or switch the order.";
            minYJTextField.requestFocus();
            error = true;
        }
        
        //set error text area with message
        errorJTextArea.setText(errorMessage);
        
        //generate random points
        if(!error)
        {
            generateRandomPoints(numOfPoints, minX, maxX, minY, maxY);
            this.dispose();
        }
    }//GEN-LAST:event_generateJButtonActionPerformed


//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(RandomPoints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RandomPoints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RandomPoints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RandomPoints.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                RandomPoints dialog = new RandomPoints(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundJPanel;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JScrollPane errorJScrollPane;
    private javax.swing.JTextArea errorJTextArea;
    private javax.swing.JButton generateJButton;
    private javax.swing.JLabel infoJLabel;
    private javax.swing.JLabel maxXJLabel;
    private javax.swing.JTextField maxXJTextField;
    private javax.swing.JLabel maxYJLabel;
    private javax.swing.JTextField maxYJTextField;
    private javax.swing.JLabel minXJLabel;
    private javax.swing.JTextField minXJTextField;
    private javax.swing.JLabel minYJLabel;
    private javax.swing.JTextField minYJTextField;
    private javax.swing.JLabel numberJLabel;
    private javax.swing.JTextField numberJTextField;
    // End of variables declaration//GEN-END:variables
}
