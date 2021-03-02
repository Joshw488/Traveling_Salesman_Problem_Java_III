package Project3;

import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <pre>
 * File             WriteToFile.java
 * Description      This helper class writes to an external file provided
 *                  by another class and space deliminates it so that it can
 *                  be read by the read from file helper class
 * Platform         PC, NetBeans 8.2, jdk 1.8.0_191, Windows 10
 * Class            CS142A, Winter 2019
 * Date             6/2/2019
 * History Log
 * @version         1.0.0
 * @author          <i>Josh Watts</i>
 * </pre>
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class WriteToFile 
{
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method		saveToFile()
    *	Description     read the points list and convert it to a text file
    *                   that may be opened at a later time.
    *   @param          fileName--String, file to write to
    *   @param          namesList--ArrayList, arrayList with names of cities
    *   @param          numList--2D-ArrayList, arrayList with 
    *   @see            java.io.FileWriter
    *   @see            java.io.PrintWriter
    *   @see            java.io.IOException
    *   @see            javax.swing.JOptionPane
    *   @see            java.util.ArrayList
    *   @author         <i>Josh Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void saveToFile(String fileName, ArrayList<String> namesList, ArrayList<ArrayList<Double>> numList)
    {
        try
        {
            fileName = fileName + "/" + namesList.size() + "Cities.txt";
            FileWriter filePointer = new FileWriter(fileName, false);   //false will replace what is there (append)
            PrintWriter output = new PrintWriter(filePointer);
            for(int i = 0; i < namesList.size(); i++)
            {
                String line = namesList.get(i) + " " 
                    + numList.get(i).get(0) + " " + numList.get(i).get(1) + "\n";
                output.write(line);
            }
            output.close();
        }
        catch(IOException exp)
        {
            JOptionPane.showMessageDialog(null, "Points not Saved" 
                    + "Save Error" + JOptionPane.WARNING_MESSAGE);
        }
    }
}
