# Traveling_Salesman_Problem_Java_III
This project is an exploration into solving the traveling salesman problem.  

The traveling salesman problem is a problem in which you are given a set of cities and you must 
find the most efficient (shortest distance) to visit each city and end up back at the starting 
city. This problem has been posed for a long time but no perfect solution has been found. There 
is a method that guarantees you will find the shortest path, but it has an O(n!) growth function, 
so it is unreasonable for moderate to large numbers of cities. Two other methods are explored in 
this program, the nearest neighbor algorithm and the sorted edge algorithm.  

The brute force algorithm is by far the most accurate method to get the most efficient path, but 
it is also the most inefficient to run. This algorithm has a O(n!) growth function, which means 
it gets way too large way too quickly. This algorithm works by finding all possible paths in a 
given graph, then finds the cost of each path. The one which has the cheapest cost is the most 
most efficient route.  

The Nearest Neighbor algorithm is much more efficient than the brute force approach but it does 
not always yield the most optimal route. This algorithm works by staring at an arbitrary vertex 
then traveling to the next vertex which is closest to the first one, after each vertex is visited 
it gets marked with a boolean value isVisited. From each vertex you reach you find the next closest 
which is not yet visited. This gets repeated until all you reach the starting vertex again, which 
means the cycle is complete.  

The sorted edge algorithm is also more efficient than the brute force approach but will not always
yield the best result. The sorted edge algorithm works by sorting all the edges in increasing order 
by their weight. The first edge (smallest) is added to the path, then the next smallest edge is 
added, each time an edge is added you must check if the vertex is full (2 edges) and the cycle is 
not completed too early.
