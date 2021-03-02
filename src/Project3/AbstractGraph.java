package Project3;
import java.util.*;

/**
 *<pre>
 *  Class           AbstractGraph
 *  File            AbstractGraph.java
 *  Description     class for the abstractGraph class
 *  Environment     PC, Windows 10, NetBeans IDE 8.2, jdk 1.8.0_131
 *  Date            5/27/2019
 *  History Log        
 *  @version        1.0.0
 *  @see            javax.swing.JFrame
 * </pre>
 */
public abstract class AbstractGraph<V> implements Graph<V> 
{
    protected List<V> vertices = new ArrayList<>(); // Store vertices
    protected List<List<Edge>> neighbors = new ArrayList<>(); // Adjacency lists

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     AbstractGraph()
    *	Description     empty graph
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /** Construct an empty graph */
    protected AbstractGraph() 
    {
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     AbstractGraph()
    *	Description     overloaded graph Construct a graph from 
    *                   vertices and edges stored in arrays
    *   @param          vertices--V[]
    *   @param          edges
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected AbstractGraph(V[] vertices, int[][] edges) 
    {
        for (int i = 0; i < vertices.length; i++)
            addVertex(vertices[i]);

        createAdjacencyLists(edges, vertices.length);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     AbstractGraph()
    *	Description     Construct a graph from vertices and edges stored in List
    *   @param          vertices--V[]
    *   @param          edges
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected AbstractGraph(List<V> vertices, List<Edge> edges) 
    {
        for (int i = 0; i < vertices.size(); i++)
            addVertex(vertices.get(i));

        createAdjacencyLists(edges, vertices.size());
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     AbstractGraph()
    *	Description     Construct a graph for integer vertices 0, 1, 2 and edge list
    *   @param          edges
    *   @param          numberOfVertices
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) 
    {
        for (int i = 0; i < numberOfVertices; i++) 
            addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}

        createAdjacencyLists(edges, numberOfVertices);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     AbstractGraph()
    *	Description     Construct a graph from integer vertices 0, 1, and edge array
    *   @param          edges
    *   @param          numberOfVertices
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected AbstractGraph(int[][] edges, int numberOfVertices) 
    {
        for (int i = 0; i < numberOfVertices; i++) 
            addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}

        createAdjacencyLists(edges, numberOfVertices);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     createAdjacencyLists()
    *	Description     Create adjacency lists for each vertex
    *   @param          edges
    *   @param          numberOfVertices
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void createAdjacencyLists(int[][] edges, int numberOfVertices) 
    {
        for (int i = 0; i < edges.length; i++) 
        {
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     createAdjacencyLists()
    *	Description     Create adjacency lists for each vertex
    *   @param          edges
    *   @param          numberOfVertices
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) 
    {
        for (Edge edge: edges) 
        {
            addEdge(edge.u, edge.v);
        }
    }

    @Override /** Return the number of vertices in the graph */
    public int getSize() 
    {
      return vertices.size();
    }

    @Override /** Return the vertices in the graph */
    public List<V> getVertices() 
    {
      return vertices;
    }

    @Override /** Return the object for the specified vertex */
    public V getVertex(int index) 
    {
        return vertices.get(index);
    }

    @Override /** Return the index for the specified vertex object */
    public int getIndex(V v) 
    {
        return vertices.indexOf(v);
    }

    @Override /** Return the neighbors of the specified vertex */
    public List<Integer> getNeighbors(int index) 
    {
        List<Integer> result = new ArrayList<>();
        for (Edge e: neighbors.get(index))
            result.add(e.v);

        return result;
    }

    @Override /** Return the degree for a specified vertex */
    public int getDegree(int v) 
    {
        return neighbors.get(v).size();
    }

    @Override /** Print the edges */
    public void printEdges() 
    {
        for (int u = 0; u < neighbors.size(); u++) 
        {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e: neighbors.get(u)) 
            {
                System.out.print("(" + getVertex(e.u) + ", " +
                    getVertex(e.v) + ") ");
            }
            System.out.println();
        }
    }

    @Override /** Clear graph */
    public void clear() 
    {
        vertices.clear();
        neighbors.clear();
    }

    @Override /** Add a vertex to the graph */  
    public boolean addVertex(V vertex) 
    {
        if (!vertices.contains(vertex)) 
        {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
        else 
        {
            return false;
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          addEdge()
    *	Description     Add an edge to the graph 
    *   @param          e-edge
    *   @return         boolean true if added
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected boolean addEdge(Edge e) 
    {
        if (e.u < 0 || e.u > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + e.u);

        if (e.v < 0 || e.v > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + e.v);

        if (!neighbors.get(e.u).contains(e)) 
        {
            neighbors.get(e.u).add(e);
            return true;
        }
        else 
        {
            return false;
        }
    }

    @Override /** Add an edge to the graph */  
    public boolean addEdge(int u, int v) 
    {
        return addEdge(new Edge(u, v));
    }

    /** Edge inner class inside the AbstractGraph class */
    public static class Edge 
    {
        public int u; // Starting vertex of the edge
        public int v; // Ending vertex of the edge

        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *<pre>
        *   Constructor     Edge()
        *   Description     Construct an edge for (u, v)
        *   @param          u
        *   @param          v
        *   @author         <i>Joshua Watts</i>
        *   Date            5/27/2019
        *   History Log     
        *</pre>
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public Edge(int u, int v) 
        {
            this.u = u;
            this.v = v;
        }

        public boolean equals(Object o) 
        {
            return u == ((Edge)o).u && v == ((Edge)o).v; 
        }
    }

    @Override /** Obtain a DFS tree starting from vertex v */
    /** To be discussed later*/
    public Tree dfs(int v) 
    {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; // Initialize parent[i] to -1

        // Mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];

        // Recursively search
        dfs(v, parent, searchOrder, isVisited);

        // Return a search tree
        return new Tree(v, parent, searchOrder);
    }

    /** Recursive method for DFS search */
    private void dfs(int u, int[] parent, List<Integer> searchOrder,
        boolean[] isVisited) 
    {
        // Store the visited vertex
        searchOrder.add(u);
        isVisited[u] = true; // Vertex v visited

        for (Edge e : neighbors.get(u)) 
        {
            if (!isVisited[e.v]) 
            {
                parent[e.v] = u; // The parent of vertex e.v is u
                dfs(e.v, parent, searchOrder, isVisited); // Recursive search
            }
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     mydfs()
    *	Description     depth first search algorithm that returns all paths
    *                   from the starting index, not just the first
    *   @param          v-int, starting index
    *   @param          allPaths,-2D-Arraylist, saves all paths found
    *   @return         Tree-tree of dfs
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Tree mydfs(int v, ArrayList<ArrayList<Integer>> allPaths) 
    {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; // Initialize parent[i] to -1

        // Mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];

        // Recursively search
        mydfs(v, parent, searchOrder, isVisited, allPaths);

        // Return a search tree
        return new Tree(v, parent, searchOrder);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     mydfs()
    *	Description     Recursive method for DFS search
    *   @param          u-int, starting index
    *   @param          parent-int[], indexes of parents
    *   @param          searchOrder- List, index of search order
    *   @param          isVisited-boolean[], array that shows if visited
    *   @param          allPaths- saves all final paths found
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void mydfs(int u, int[] parent, List<Integer> searchOrder,
        boolean[] isVisited, ArrayList<ArrayList<Integer>> allPaths) 
    {
        if(searchOrder.size() == vertices.size())
        {
            ArrayList<Integer> tempList = new ArrayList<>();
            tempList.addAll(searchOrder);
            allPaths.add(tempList);
        }

        for (Edge e : neighbors.get(u)) 
        {
            if (!isVisited[e.v]) 
            {
                isVisited[e.v] = true; // Vertex v visited
                searchOrder.add(e.v);
                parent[e.v] = u; // The parent of vertex e.v is u
                mydfs(e.v, parent, searchOrder, isVisited, allPaths); // Recursive search
                isVisited[e.v] = false;
                searchOrder.remove(searchOrder.size() - 1); //dequeue so new path may be found
            }
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          dfs()
    *	Description     perform depth first search on given list
    *   @param          v-staring index
    *   @param          list-list to search
    *   @return         tree-tree of search
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Tree dfs(int v, ArrayList<ArrayList<WeightedEdge>> list) 
    {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1; // Initialize parent[i] to -1

        // Mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];

        // Recursively search
        dfs(v, parent, searchOrder, isVisited, list);

        // Return a search tree
        return new Tree(v, parent, searchOrder);
    }

    
    
    /** Recursive method for DFS search */
    private void dfs(int u, int[] parent, List<Integer> searchOrder,
        boolean[] isVisited, ArrayList<ArrayList<WeightedEdge>> list) 
    {
        // Store the visited vertex
        searchOrder.add(u);
        isVisited[u] = true; // Vertex v visited

        for (Edge e : list.get(u)) 
        {
            if (!isVisited[e.v]) 
            {
                parent[e.v] = u; // The parent of vertex e.v is u
                dfs(e.v, parent, searchOrder, isVisited, list); // Recursive search
            }
        }
    }

    @Override /** Starting bfs search from vertex v */
    /** To be discussed in later */
    public Tree bfs(int v) 
    {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
          parent[i] = -1; // Initialize parent[i] to -1

        java.util.LinkedList<Integer> queue =
            new java.util.LinkedList<>(); // list used as a queue
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v); // Enqueue v
        isVisited[v] = true; // Mark it visited

        while (!queue.isEmpty()) 
        {
            int u = queue.poll(); // Dequeue to u
            searchOrder.add(u); // u searched
            for (Edge e: neighbors.get(u)) 
            {
                if (!isVisited[e.v]) 
                {
                    queue.offer(e.v); // Enqueue w
                    parent[e.v] = u; // The parent of w is u
                    isVisited[e.v] = true; // Mark it visited
                }
            }
        }

        return new Tree(v, parent, searchOrder);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          bfs()
    *	Description     perfrom bredth first search on list
    *   @param          v-starting index
    *   @param          list-list to search
    *   @return         tree-tree of search
    *	@author         <i>Joshua Watts</i>
    *	Date            5/27/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Tree bfs(int v, ArrayList<ArrayList<WeightedEdge>> list) 
    {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++)
          parent[i] = -1; // Initialize parent[i] to -1

        java.util.LinkedList<Integer> queue =
            new java.util.LinkedList<>(); // list used as a queue
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v); // Enqueue v
        isVisited[v] = true; // Mark it visited

        while (!queue.isEmpty()) 
        {
            int u = queue.poll(); // Dequeue to u
            searchOrder.add(u); // u searched
            for (Edge e: list.get(u)) 
            {
                if (!isVisited[e.v]) 
                {
                    queue.offer(e.v); // Enqueue w
                    parent[e.v] = u; // The parent of w is u
                    isVisited[e.v] = true; // Mark it visited
                }
            }
        }

        return new Tree(v, parent, searchOrder);
    }

    /** Tree inner class inside the AbstractGraph class */
    /** To be discussed in Section 28.5 */
    public class Tree 
    {
        private int root; // The root of the tree
        private int[] parent; // Store the parent of each vertex
        private List<Integer> searchOrder; // Store the search order

        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *<pre>
        *   Constructor     Edge()
        *   Description     Construct a tree with root, parent, and searchOrder
        *   @param          root-int
        *   @param          parent-int[]
        *   @param          searchOrder- list-integer
        *   @author         <i>Joshua Watts</i>
        *   Date            5/27/2019
        *   History Log     
        *</pre>
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public Tree(int root, int[] parent, List<Integer> searchOrder) 
        {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        /** Return the root of the tree */
        public int getRoot() 
        {
            return root;
        }

        /** Return the parent of vertex v */
        public int getParent(int v) 
        {
            return parent[v];
        }

        /** Return an array representing search order
            @return list*/
        public List<Integer> getSearchOrder() 
        {
            return searchOrder;
        }

        /** Return number of vertices found */
        public int getNumberOfVerticesFound() 
        {
            return searchOrder.size();
        }

        /** Return the path of vertices from a vertex to the root
            @return list    
            @param index-int */
        public List<V> getPath(int index) 
        {
            ArrayList<V> path = new ArrayList<>();

            do 
            {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);

            return path;
        }

        /** Print a path from the root to vertex v 
            @param index-int */
        public void printPath(int index) 
        {
            List<V> path = getPath(index);
            System.out.print("A path from " + vertices.get(root) + " to " +
                vertices.get(index) + ": ");
            for (int i = path.size() - 1; i >= 0; i--)
                System.out.print(path.get(i) + " ");
        }

        /** Print the whole tree */
        public void printTree() 
        {
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) 
            {
                if (parent[i] != -1) 
                {
                    // Display an edge
                    System.out.print("(" + vertices.get(parent[i]) + ", " +
                        vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }
}
