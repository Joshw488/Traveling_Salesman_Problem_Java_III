package Project3;

/**
 *<pre>
 *  Class           Graph
 *  File            Graph.java
 *  Description     class for the Graph interface
 *  Environment     PC, Windows 10, NetBeans IDE 8.2, jdk 1.8.0_131
 *  Date            5/27/2019
 *  History Log        
 *  @version        1.0.0
 *  @see            javax.swing.JFrame
 * </pre>
 */
public interface Graph<V> 
{
    /** Return the number of vertices in the graph
     * @return  */
    public int getSize();

    /** Return the vertices in the graph
     * @return  */
    public java.util.List<V> getVertices();

    /** Return the object for the specified vertex index
     * @param index
     * @return  */
    public V getVertex(int index);

    /** Return the index for the specified vertex object
     * @param v
     * @return  */
    public int getIndex(V v);

    /** Return the neighbors of vertex with the specified index
     * @param index
     * @return  */
    public java.util.List<Integer> getNeighbors(int index);

    /** Return the degree for a specified vertex
     * @param v
     * @return  */
    public int getDegree(int v);

    /** Print the edges */
    public void printEdges();

    /** Clear graph */
    public void clear();

    /** Add a vertex to the graph
     * @param vertex  
     * @return  */  
    public boolean addVertex(V vertex);

    /** Add an edge to the graph
     * @param u  
     * @param v  
     * @return  boolean*/  
    public boolean addEdge(int u, int v);

    /** Obtain a depth-first search tree
     * @param v
     * @return  */
    public AbstractGraph<V>.Tree dfs(int v);

    /** Obtain a breadth-first search tree
     * @param v
     * @return  */
    public AbstractGraph<V>.Tree bfs(int v);
}
