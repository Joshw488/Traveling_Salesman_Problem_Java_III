package Project3;
import java.util.*;

/**
 *<pre>
 *  Class           WeightedGraph
 *  File            WeightedGraph.java
 *  Description     class for the WeightedGraph class
 *  Environment     PC, Windows 10, NetBeans IDE 8.2, jdk 1.8.0_131
 *  Date            5/27/2019
 *  History Log        
 *  @version        1.0.0
 *  @see            javax.swing.JFrame
 * </pre>
 */
public class WeightedGraph<V> extends AbstractGraph<V> 
{
    /** Construct an empty */
    public WeightedGraph() 
    {
    }

    /** Construct a WeightedGraph from vertices and edged in arrays
     * @param vertices
     * @param edges */
    public WeightedGraph(V[] vertices, int[][] edges) 
    {
        createWeightedGraph(java.util.Arrays.asList(vertices), edges);
    }

    /** Construct a WeightedGraph from vertices and edges in list
     * @param edges
     * @param numberOfVertices */
    public WeightedGraph(int[][] edges, int numberOfVertices) 
    {
        List<V> vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++)
            vertices.add((V)(new Integer(i)));

        createWeightedGraph(vertices, edges);
    }

    /** Construct a WeightedGraph for vertices 0, 1, 2
     * @param vertices
     * @param edges */
    public WeightedGraph(List<V> vertices, List<WeightedEdge> edges) 
    {
        createWeightedGraph(vertices, edges);
    }

    /** Construct a WeightedGraph from vertices 0, 1, and edge array 
     @param edges
     @param numberOfVertices*/
    public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) 
    {
        List<V> vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++)
            vertices.add((V)(new Integer(i)));

        createWeightedGraph(vertices, edges);
    }

    /** Create adjacency lists from edge arrays */
    private void createWeightedGraph(List<V> vertices, int[][] edges) 
    {
        this.vertices = vertices;     

        for (int i = 0; i < vertices.size(); i++) 
        {
            neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
        }

        for (int i = 0; i < edges.length; i++) 
        {
            neighbors.get(edges[i][0]).add(
              new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
        }
    }

    /** Create adjacency lists from edge lists */
    private void createWeightedGraph(
        List<V> vertices, List<WeightedEdge> edges) 
    {
        this.vertices = vertices;     

        for (int i = 0; i < vertices.size(); i++) 
        {
            neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
        }

        for (WeightedEdge edge: edges) 
        {      
            neighbors.get(edge.u).add(edge); // Add an edge into the list
        }
    }

    /** Return the weight on the edge (u, v)
     * @param u
     * @param v 
       @return double- weight
       @throws Exception*/
    public double getWeight(int u, int v) throws Exception 
    {
        for (Edge edge : neighbors.get(u)) 
        {
            if (edge.v == v) 
            {
                return ((WeightedEdge)edge).weight;
            }
        }

        throw new Exception("Edge does not exit");
    }

    /** Display edges with weights */
    public void printWeightedEdges() 
    {
        for (int i = 0; i < getSize(); i++) 
        {
            System.out.print(getVertex(i) + " (" + i + "): ");
            for (Edge edge : neighbors.get(i)) 
            {
                System.out.print("(" + edge.u +
                  ", " + edge.v + ", " + ((WeightedEdge)edge).weight + ") ");
            }
            System.out.println();
        }
    }

    /** Add edges to the weighted graph
     * @param u  
     * @param v  
     * @param weight  
     * @return  */  
    public boolean addEdge(int u, int v, double weight) 
    {
        return addEdge(new WeightedEdge(u, v, weight));
    }

    /** Get a minimum spanning tree rooted at vertex 0
     * @return  */
    public MST getMinimumSpanningTree() 
    {
        return getMinimumSpanningTree(0);
    }

    /** Get a minimum spanning tree rooted at a specified vertex
     * @param startingVertex
     * @return  */
    public MST getMinimumSpanningTree(int startingVertex) 
    {
        // cost[v] stores the cost by adding v to the tree
        double[] cost = new double[getSize()];
        for (int i = 0; i < cost.length; i++) 
        {
            cost[i] = Double.POSITIVE_INFINITY; // Initial cost 
        }
        cost[startingVertex] = 0; // Cost of source is 0

        int[] parent = new int[getSize()]; // Parent of a vertex
        parent[startingVertex] = -1; // startingVertex is the root
        double totalWeight = 0; // Total weight of the tree thus far

        List<Integer> T = new ArrayList<>();

        // Expand T
        while (T.size() < getSize()) 
        {
            // Find smallest cost v in V - T 
            int u = -1; // Vertex to be determined
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) 
            {
                if (!T.contains(i) && cost[i] < currentMinCost) 
                {
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            T.add(u); // Add a new vertex to T
            totalWeight += cost[u]; // Add cost[u] to the tree

            // Adjust cost[v] for v that is adjacent to u and v in V - T
            for (Edge e : neighbors.get(u)) 
            {
                if (!T.contains(e.v) && cost[e.v] > ((WeightedEdge)e).weight) 
                {
                    cost[e.v] = ((WeightedEdge)e).weight;
                    parent[e.v] = u; 
                }
            }
        } // End of while

        return new MST(startingVertex, parent, T, totalWeight);
    }

    /** MST is an inner class in WeightedGraph */
    public class MST extends Tree 
    {
        private double totalWeight; // Total weight of all edges in the tree

        public MST(int root, int[] parent, List<Integer> searchOrder,
            double totalWeight) 
        {
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public double getTotalWeight() 
        {
            return totalWeight;
        }
    }

    /** Find single source shortest paths 
        @return ShortestPathTree -
        @param sourceVertex- starting vertex*/
    public ShortestPathTree getShortestPath(int sourceVertex) 
    {
        // cost[v] stores the cost of the path from v to the source
        double[] cost = new double[getSize()];
        for (int i = 0; i < cost.length; i++) 
        {
            cost[i] = Double.POSITIVE_INFINITY; // Initial cost set to infinity
        }
        cost[sourceVertex] = 0; // Cost of source is 0

        // parent[v] stores the previous vertex of v in the path
        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1; // The parent of source is set to -1

        // T stores the vertices whose path found so far
        List<Integer> T = new ArrayList<>();

        // Expand T
        while (T.size() < getSize()) 
        {
            // Find smallest cost v in V - T 
            int u = -1; // Vertex to be determined
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) 
            {
                if (!T.contains(i) && cost[i] < currentMinCost) 
                {
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            T.add(u); // Add a new vertex to T

            // Adjust cost[v] for v that is adjacent to u and v in V - T
            for (Edge e : neighbors.get(u)) 
            {
                if (!T.contains(e.v) 
                    && cost[e.v] > cost[u] + ((WeightedEdge)e).weight) 
                {
                    cost[e.v] = cost[u] + ((WeightedEdge)e).weight;
                    parent[e.v] = u; 
                }
            }
        } // End of while

        // Create a ShortestPathTree
        return new ShortestPathTree(sourceVertex, parent, T, cost);
    }
    

    /** ShortestPathTree is an inner class in WeightedGraph */
    public class ShortestPathTree extends Tree 
    {
        private double[] cost; // cost[v] is the cost from v to source

        /** Construct a path 
         @param source- start
         @param parent- int[]
         @param searchOrder- list
         @param cost-double[]*/
        public ShortestPathTree(int source, int[] parent, 
            List<Integer> searchOrder, double[] cost) 
        {
            super(source, parent, searchOrder);
            this.cost = cost;
        }

        /** Return the cost for a path from the root to vertex v 
         @param v*/
        public double getCost(int v) 
        {
            return cost[v];
        }

        /** Print paths from all vertices to the source */
        public void printAllPaths() 
        {
            System.out.println("All shortest paths from " +
                vertices.get(getRoot()) + " are:");
            for (int i = 0; i < cost.length; i++) 
            {
                printPath(i); // Print a path from i to the source
                System.out.println("(cost: " + cost[i] + ")"); // Path cost
            }
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     getPathCost()
    *	Description     loop through search order and return the total cost of 
    *                   the trip
    *   @param          pathList-search order of path
    *	@author         <i>Joshua Watts</i>
    *	Date            6/1/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public double getPathCost(ArrayList<Integer> pathList)
    {
        double totalWeight = 0;
        for(int i = 0; i < pathList.size(); i++)
        {
            totalWeight += ((WeightedEdge)neighbors.get(pathList.get(i)).get(pathList.get((i + 1) % pathList.size()))).weight;
        }
        return totalWeight;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     getPathString()
    *	Description     format the path from an array of integer to the name
    *                   of the cities
    *   @param          pathList-search order of path
    *   @return         String- formatted string of the path by name
    *	@author         <i>Joshua Watts</i>
    *	Date            6/1/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getPathString(ArrayList<Integer> pathList)
    {
        String path = "";
        for(int i = 0; i < pathList.size(); i++)
        {
            if(i != pathList.size() - 1)
                path += vertices.get(pathList.get(i)) + " - ";
            else
                path += vertices.get(pathList.get(i));
        }
        return path;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     getBruteForcePath()
    *	Description     find the lowest cost path by brute force(find all
    *                   paths)
    *   @return         ArrayList-int, search order
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<Integer> getBruteForcePath()
    {
        ArrayList<ArrayList<Integer>> allPossible = new ArrayList<>();
        allPossible.add(new ArrayList<>());
        allPossible.get(0).add(0);
        
        mydfs(0, allPossible);
        
        removeDuplicates(allPossible);
        
        int index = findLowestCost(allPossible);    //still has reverse order duplicates, wont be picked up by smaller
        
        return allPossible.get(index);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     removeDuplicates()
    *	Description     remove the paths which dont start with 0, complete the
    *                   cycles for those who do
    *   @param          allPaths-2D-ArrayList of all possible paths
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void removeDuplicates(ArrayList<ArrayList<Integer>> allPaths)
    {
        for(int i = 0; i < allPaths.size(); i++)
        {
            if(allPaths.get(i).size() != vertices.size())   //deletes first one
            {
                allPaths.remove(i);
                i--;
            }
            else if(allPaths.get(i).get(0) != 0)    //if starts from a different vertice
            {
                allPaths.remove(i);
                i--;
            }
            else    //make cycle, not path
            {
                allPaths.get(i).add(allPaths.get(i).get(0));
            }
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     findLowestCost()
    *	Description     find the lowest cost of the given path list
    *   @param          allPaths-2D-ArrayList of all possible paths
    *   @return         int- index of lowest cost path
    *	@author         <i>Joshua Watts</i>
    *	Date            6/2/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int findLowestCost(ArrayList<ArrayList<Integer>> allPaths)
    {
        int index = 0;
        double cost = getPathCost(allPaths.get(0));
        double testCost = 0;
        for(int i = 1; i < allPaths.size(); i++)
        {
            testCost = getPathCost(allPaths.get(i));
            if(testCost < cost)
            {
                cost = testCost;
                index = i;
            }
        }
        return index;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     getNearestNeighbourPath()
    *	Description     find the lowest cost path by nearest neighbor, goes
    *                   from vertex to vertex fiunding the smallest path that 
    *                   is not visited
    *   @return         ArrayList-int, search order
    *	@author         <i>Joshua Watts</i>
    *	Date            5/30/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<Integer> getNearestNeighbourPath() //could use shortest path tree
    {
        ArrayList<Integer> pathList = new ArrayList<>();
        boolean[] isVisited = new boolean[neighbors.size()];
        int startingIndex = 0;
        int index = startingIndex;
        pathList.add(startingIndex);
        isVisited[startingIndex] = true;
        do
        {
            index = closestPoint(index, startingIndex, isVisited);
            isVisited[index] = true;
            pathList.add(index);
        }while(index != startingIndex);
        //double d = getPathCost(pathList);
        return pathList;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     closestPoint()
    *	Description     helper function for the nearest neighbor method, finds 
    *                   the closest point from the index given
    *   @param          index-int, starting index
    *   @param          newIndex-int, ending point of edge
    *   @param          isVisited-boolean, array of indicies, shows which are
    *                               vistied
    *   @return         int, index of closest unvisited point
    *	@author         <i>Joshua Watts</i>
    *	Date            6/1/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int closestPoint(int index, int newIndex, boolean[] isVisited)
    {
        double shortest = Double.POSITIVE_INFINITY;
        double weight = 0;
        for(int i = 0; i < neighbors.size(); i++)
        {
            weight = ((WeightedEdge)neighbors.get(index).get(i)).weight;
            if(weight > 0)
                if(isVisited[i] == false && weight < shortest)//test if weight is smallest than current
                {
                    shortest = weight;
                    newIndex = i;
                }
        }
        return newIndex;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     getSortedEdgePath()
    *	Description     find the lowest cost path by sorted edge, sorts
    *                   the edges from lowest to greatest and if the 
    *                   current verticies are not full and the next
    *                   edge does not create a circuit, then add it
    *   @return         ArrayList-int, search order
    *	@author         <i>Joshua Watts</i>
    *	Date            6/1/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<Integer> getSortedEdgePath()
    {
        ArrayList<WeightedEdge> sortedList = makeSortedList();
        //ArrayList<Integer> searchOrder = new ArrayList<>();
        //ArrayList<Integer> pathList = new ArrayList<>();
        //ArrayList<Integer> helperList = new ArrayList<>();
        ArrayList<ArrayList<WeightedEdge>> pathList = new ArrayList<>();
        for(int i = 0; i < neighbors.size(); i++)
            pathList.add(new ArrayList<>());
        //int i = 0;
        //Tree myTree;
        //while()
        for(int i = 0; i < sortedList.size() && !isComplete(pathList); i++)
        {
            if(!(isFullVertice(sortedList.get(i).u, pathList) || isFullVertice(sortedList.get(i).v, pathList)))    //verticies dont already have 2 edges
            {
                //enqueue the next smallest edge
                pathList.get(sortedList.get(i).u).add(new WeightedEdge(sortedList.get(i).u, sortedList.get(i).v, sortedList.get(i).weight));
                pathList.get(sortedList.get(i).v).add(new WeightedEdge(sortedList.get(i).v, sortedList.get(i).u, sortedList.get(i).weight));
                
                Tree myTree = bfs(sortedList.get(i).u, pathList);
                if(myTree.getNumberOfVerticesFound() != pathList.size())//if the circuit includes all the verticies(complete)
                    if(testCircuit(myTree, pathList))   //if the circuit is finshed too early
                    {
                        //dequeue if the circuit is completed too early
                        pathList.get(sortedList.get(i).u).remove(pathList.get(sortedList.get(i).u).size()-1);
                        pathList.get(sortedList.get(i).v).remove(pathList.get(sortedList.get(i).v).size()-1);
                    }
            }
        }
        Tree finalTree = dfs(0, pathList);
        List<Integer> search = finalTree.getSearchOrder();
        ArrayList<Integer> searchOrder = new ArrayList<>();
        searchOrder.addAll(search);
        searchOrder.add(searchOrder.get(0));
        //double d = getPathCost(searchOrder);
        return searchOrder;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     isComplete()
    *	Description     test the graph, if all vertices have two edges then the
    *                   graph is complete
    *   @param          pathList-2D-ArrayList, list to test
    *   @return         boolean-true if the path is complete
    *	@author         <i>Joshua Watts</i>
    *	Date            6/1/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isComplete(ArrayList<ArrayList<WeightedEdge>> pathList)
    {
        boolean complete = true;
        for(int i = 0; i < pathList.size() && complete; i++)
            if(pathList.get(i).size() != 2)
                complete = false;
        return complete;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     testCircuit()
    *	Description     test if the newest edge (using bfs from the tree) creates
    *                   a circuit, done by testing if all verticies of the search
    *                   order are full
    *   @param          pathList-2D-ArrayList, list to test
    *   @param          myTree-tree of newest added point
    *   @return         boolean-true if there is a circuit
    *	@author         <i>Joshua Watts</i>
    *	Date            6/1/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean testCircuit(Tree myTree, ArrayList<ArrayList<WeightedEdge>> pathList)
    {
        List<Integer> search = myTree.getSearchOrder();
        ArrayList<Integer> searchOrder = new ArrayList<>();
        searchOrder.addAll(search);
        boolean isCircuit = true;
        for(int i = 0; i < searchOrder.size() && isCircuit; i++)
        {
            if(!isFullVertice(searchOrder.get(i), pathList))
                isCircuit = false;
        }
        return isCircuit;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     isFullVertice()
    *	Description     test if the vertice at the given index has two edges(full)
    *   @param          pathList-2D-ArrayList, list to test
    *   @param          index-vertice to test
    *   @return         boolean-true if vertice is full
    *	@author         <i>Joshua Watts</i>
    *	Date            6/1/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isFullVertice(int index, ArrayList<ArrayList<WeightedEdge>> pathList)
    {
        if(pathList.get(index).size() >= 2)
            return true;
        return false;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Constructor     makeSortedList()
    *	Description     make a new arrayList of edges and sort them by their weight
    *   @return         ArrayList, sorted list with no duplicates
    *	@author         <i>Joshua Watts</i>
    *	Date            6/1/2019
    *   History Log     
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<WeightedEdge> makeSortedList()
    {
        ArrayList<WeightedEdge> sortedList = new ArrayList<>();
        for(int i = 0; i < neighbors.size(); i++)
        {
            for(int j = i + 1; j < neighbors.size(); j++)
            {
                sortedList.add((WeightedEdge)neighbors.get(i).get(j));
            }
        }
        Collections.sort(sortedList);
        return sortedList;
    }
    
}