package Project3;
/**
 *<pre>
 *  Class           WeightedEdge
 *  File            WeightedEdge.java
 *  Description     class for the weighted edge class
 *  Environment     PC, Windows 10, NetBeans IDE 8.2, jdk 1.8.0_131
 *  Date            5/27/2019
 *  History Log        
 *  @version        1.0.0
 *  @see            javax.swing.JFrame
 * </pre>
 */
public class WeightedEdge extends AbstractGraph.Edge
    implements Comparable<WeightedEdge> 
{
    public double weight; // The weight on edge (u, v)

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     WeightedEdge()
    *	Description     Create a weighted edge on (u, v
    *   @param          u-int, starting point
    *   @param          v-int, ending point
    *   @param          weight-double, weight of edge
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public WeightedEdge(int u, int v, double weight) 
    {
        super(u, v);
        this.weight = weight;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          compareTo()
    *	Description     Compare two edges on weights
    *   @param          edge-WeightedEdge- compares given edge to this instance
    *                   of the edge
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int compareTo(WeightedEdge edge) 
    {
        if (weight > edge.weight) 
        {
            return 1;
        }
        else 
            if (weight == edge.weight) 
            {
                return 0;
            }
            else 
            {
                return -1;
            }
    }
}
