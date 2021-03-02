package Project3;

import java.awt.Toolkit;
import java.util.ArrayList;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <pre>
 * File             AddCity.java
 * Description      Generate random values with the range and amount provided 
 *                  by the user, validating all inputs. once they are made they
 *                  are converted into 2 decimal place doubles. The main GUI may
 *                  then recieve the random arrayList
 * Platform         PC, NetBeans 8.2, jdk 1.8.0_191, Windows 10
 * Class            CS142A, Winter 2019
 * Date             4/14/2019
 * History Log      
 * Hours            
 * @version         1.0.0
 * @see             java.util.ArrayList
 * @author          <i>Josh Watts</i>
 * Images for academic purposes only and is from:
 * </pre>
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class AddCity extends javax.swing.JDialog 
{
//    ArrayList<String> namesArray = null;
//    ArrayList<ArrayList<Double>> dblArray = null;
    String name = null;
    ArrayList<Double> dblArray = null;
    ArrayList<String> namesCopy = null;
    ArrayList<ArrayList<Double>> numCopy = null;
    final int MAX_POINTS = 100;
    final double MIN_VALUE = -1000;
    final double MAX_VALUE = 1000;
    final double MAX_NAME = 2;
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     AddCity()
    *	Description     Default constructor--creates form, sets generate
    *                   button as default
    *   @see            java.awt.Toolkit
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public AddCity() 
    {
        initComponents();
        this.getRootPane().setDefaultButton(addJButton); //set buttonAdd as default
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage("src/Images/SalespersonIcon.png"));
        //set title
        this.setTitle("Project #3--Random Cities");
        //centers the form at start.
        setLocationRelativeTo(null);
        nameJTextField.requestFocus();
        name = null;
        dblArray = null;
        this.setModal(true);        //make form modal so it waits to complete before returning
        this.setAlwaysOnTop(true);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     AddCity()
    *	Description     Overloaded constructor--calls default constructor, sets
    *                   this instance of names array and points array to 
    *                   ones provided by driver
    *   @param          strCopy-ArrayList, this instacnes copy of the names array
    *   @param          dblCopy--ArrayList, this instances copy of the points array
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public AddCity(ArrayList<String> strCopy, ArrayList<ArrayList<Double>> dblCopy)
    {
        this();
        namesCopy = strCopy;
        numCopy = dblCopy;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          getName()
    *	Description     A getter for the new name
    *   @return         String- name of city
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getName()
    {
        return name;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          getValue()
    *	Description     A getter for the values of the city(point)
    *   @return         ArrayList- city points
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<Double> getValue()
    {
        return dblArray;
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
        if(numCopy.size() > 0)
        {
            for(int i = 0; i < numCopy.size() && !isDuplicate; i++)
                if(numCopy.get(i).get(0) == xVal && numCopy.get(i).get(1) == yVal)
                    isDuplicate = true;
        }
        return isDuplicate;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          isDuplicateName()
    *	Description     test if the name given is already in the list
    *   @param          name-String, name to search for in names array
    *   @return         boolean, true if duplicate, false if not
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isDuplicateName(String name)
    {
        boolean isDuplicate = false;
        if(namesCopy.size() > 0)
        {
            for(int i = 0; i < namesCopy.size() && !isDuplicate; i++)
                if(namesCopy.get(i).equals(name))
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
        xJLabel = new javax.swing.JLabel();
        xJTextField = new javax.swing.JTextField();
        yJLabel = new javax.swing.JLabel();
        yJTextField = new javax.swing.JTextField();
        addJButton = new javax.swing.JButton();
        errorJScrollPane = new javax.swing.JScrollPane();
        errorJTextArea = new javax.swing.JTextArea();
        cancelJButton = new javax.swing.JButton();
        nameJLabel = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        backgroundJPanel.setBackground(new java.awt.Color(153, 204, 255));

        numberJLabel.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        numberJLabel.setText("Add City Point");

        xJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        xJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xJLabel.setText("X Value:");

        xJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        xJTextField.setToolTipText("X value of city point");

        yJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        yJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yJLabel.setText("Y Value:");

        yJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        yJTextField.setToolTipText("Y value of city point");

        addJButton.setBackground(new java.awt.Color(153, 255, 255));
        addJButton.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        addJButton.setMnemonic('a');
        addJButton.setText("Add");
        addJButton.setToolTipText("Add the city with the current information");
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
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
        cancelJButton.setToolTipText("Close the add city form");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });

        nameJLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        nameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameJLabel.setText("Name:");

        nameJTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        nameJTextField.setToolTipText("Name of city (less than 3 characters)");

        javax.swing.GroupLayout backgroundJPanelLayout = new javax.swing.GroupLayout(backgroundJPanel);
        backgroundJPanel.setLayout(backgroundJPanelLayout);
        backgroundJPanelLayout.setHorizontalGroup(
            backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(errorJScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundJPanelLayout.createSequentialGroup()
                                    .addComponent(addJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(cancelJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(xJTextField)
                                    .addComponent(xJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(yJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(yJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(backgroundJPanelLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(numberJLabel)
                            .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        backgroundJPanelLayout.setVerticalGroup(
            backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xJLabel)
                    .addComponent(yJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    *	Date            4/15/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed
        //close the edit form
        this.dispose();
    }//GEN-LAST:event_cancelJButtonActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Event Handler	addJButtonActionPerformed()
    *	Description     check all the inputs to make sure they are true number
    *                   values and then set them to this instances values so they
    *                   can be retrived by main
    *   @param          evt ActionEvent
    *   @see            java.awt.event.ActionEvent
    *   @author         <i>Josh Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJButtonActionPerformed
        double xVal = 0;
        double yVal = 0;
        String strName = "";
        String errorMessage = "";
        //set all to false so they become true as they are tested
        boolean isValidName = false, isValidX = false, isValidY = false,
                error = false;
        //reset error message
        errorJTextArea.setText("");
        
        //validate entry
        try
        {
            //parse into correct type, if any cant be parsed, catch will engage
            //and first false value will be displayed
            strName = nameJTextField.getText();
            isValidName = true;
            xVal = Double.parseDouble(xJTextField.getText());
            isValidX = true;
            yVal = Double.parseDouble(yJTextField.getText());
            isValidY = true;
            
        }
        catch(NumberFormatException exp)
        {
            if(!isValidName)
            {
                errorMessage = "Incorrect Name: Please enter a unique name for the city.";
                nameJTextField.requestFocus();
                error = true;
            }
            else if(!isValidX)
            {
                errorMessage = "Incorrect Value: Please use a real number for "
                        + "X value.";
                xJTextField.requestFocus();
                error = true;
            }
            else if(!isValidY)
            {
                errorMessage = "Incorrect Value: Please use a real number for "
                        + "Y value.";
                yJTextField.requestFocus();
                error = true;
            }
        }
        if(strName.length() == 0 || strName.length() > MAX_NAME)
        {
            errorMessage = "Incorrect Name: Please enter at least one character"
                    + " long but no more than " + MAX_NAME + " characters long";
            xJTextField.requestFocus();
            error = true;
        }
        else if (isDuplicateName(strName))
        {
            errorMessage = "Name already used, please make a new name";
            xJTextField.requestFocus();
            error = true;
        }
        else if(xVal < MIN_VALUE || xVal > MAX_VALUE)
        {
            errorMessage = "X value out of range: Please use a value greater"
                    + "than " + MIN_VALUE + " and less than " + MAX_VALUE;
            xJTextField.requestFocus();
            error = true;
        }
        else if(yVal < MIN_VALUE || yVal > MAX_VALUE)
        {
            errorMessage = "Y value out of range: Please use a value greater"
                    + "than " + MIN_VALUE + " and less than " + MAX_VALUE;
            yJTextField.requestFocus();
            error = true;
        }
        else if(isDuplicate(xVal, yVal))
        {
            errorMessage = "X and Y values are the same as another point.";
            xJTextField.requestFocus();
            error = true;
        }
        
        //set error text area with message
        errorJTextArea.setText(errorMessage);
        
        //generate random points
        if(!error)
        {
            name = strName;
            ArrayList<Double> tempArray = new ArrayList<>();
            tempArray.add(xVal);
            tempArray.add(yVal);
            dblArray = tempArray;
            this.dispose();
        }
    }//GEN-LAST:event_addJButtonActionPerformed


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
    private javax.swing.JButton addJButton;
    private javax.swing.JPanel backgroundJPanel;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JScrollPane errorJScrollPane;
    private javax.swing.JTextArea errorJTextArea;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JLabel numberJLabel;
    private javax.swing.JLabel xJLabel;
    private javax.swing.JTextField xJTextField;
    private javax.swing.JLabel yJLabel;
    private javax.swing.JTextField yJTextField;
    // End of variables declaration//GEN-END:variables
}
