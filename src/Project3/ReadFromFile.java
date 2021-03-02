package Project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <pre>
 * File             ReadFromFile.java
 * Description      A helper class which reads through space deliminated 
 *                  external file line by line and sets the information from
 *                  that into a...
 * Platform         PC, NetBeans 8.2, jdk 1.8.0_191, Windows 10
 * Class            CS142A, Winter 2019
 * Date             5/28/2019
 * History Log
 * @version         1.0.0
 * @author          <i>Josh Watts</i>
 * </pre>
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class ReadFromFile 
{
    private ArrayList<String> strArray = new ArrayList<>();
    //private ArrayList<String> numArray = new ArrayList<>();
    private ArrayList<ArrayList<Double>> numArray = new ArrayList<>();
            
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getStringArr()
     * Description  getter for the array of names for the city
     * @return      ArrayList- names of cities
     * @author      <i>Joshua Watts</i>
     * Date         5/28/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<String> getStringArr()
    {
        return strArray;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       getNumberArr()
     * Description  getter for the array of points for the city
     * @return      ArrayList- points of cities
     * @author      <i>Joshua Watts</i>
     * Date         5/28/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<ArrayList<Double>> getNumberArr()
    {
        return numArray;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       readFromFile()
     * Description  Reads points from a text file that is ' ' delimited and
     *              adds them to the name array and points array
     *              Uses an object from the ReadFile class to read record.
     * @param       file--String, path to the points txt file
     * @see         java.util.StringTokenizer
     * @see         java.util.Scanner
     * @see         java.io.FileNotFoundException
     * @see         java.io.File
     * @see         java.io.IOException
     * @author      <i>Joshua Watts</i>
     * Date         5/28/2019
     * History Log  
     * </pre>
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void readFromFile(String file)
    {
        try
        {
            int i = 0;
            Scanner input =  new Scanner(new File(file));  //file pointer
            String line = "";
            StringTokenizer token = null;
            //line = input.nextLine();
            strArray = new ArrayList<>();
            numArray = new ArrayList<>();
            //ArrayList<WeightedEdge> edgeArray = new ArrayList<>();
            while(input.hasNextLine())
            {
                line = input.nextLine();
               
                token = new StringTokenizer(line, " ");
                //break up the txt file into tokens deliminated by ' ' and put
                //them in a 2d array
                while(token.hasMoreElements())
                {
                    strArray.add(token.nextToken());
                    numArray.add(new ArrayList<>());
                    numArray.get(i).add(Double.parseDouble(token.nextToken()));
                    numArray.get(i).add(Double.parseDouble(token.nextToken()));
                }
                i++;
            }
            input.close();
        } 
        //cannot find the file, give user option to find another
        catch (FileNotFoundException ex) 
        {
            //display a JFileChooser to select a file     
            JOptionPane.showMessageDialog(null, "Cannot Find File", 
                    "File Input Error", JOptionPane.ERROR_MESSAGE);
            //from here ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            JFileChooser chooser = new JFileChooser("src/Cities");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "text Files", "txt");
            chooser.setFileFilter(filter);
            int choice = chooser.showOpenDialog(null);
            if(choice == JFileChooser.APPROVE_OPTION)
            {
                File chosenFile = chooser.getSelectedFile();
                file = "src/Cities/" + chosenFile.getName();
                readFromFile(file);
            }
            //to here, put to new for the menu item ~~~~~~~~
        }
        //cannot open file
        catch (IOException exp) 
        {
            JOptionPane.showMessageDialog(null, "Cannot Read From File", 
                    "File Read Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
