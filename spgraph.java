/**
 * This program solves the search problem set out in the state space graph provided in the assignment (details set out below). It prints to terminal the traversal the Bread First Search starting from vertex 0. It is written for easy modification to allow for user input.
 * @author Lauritta Burrows 21422952
 * @version 1.0
 * @since 2022-05-29
 * 
 * Assignment:    Mid-term Assignment
 * Module Title:  Artificial Intelligence
 * Module Code:   CP5SA06E
 * Assigned by:   Dr Fateme Dinmohammadi
 * Due Date:      2022-06-05
 */ 

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class SPGraph // represents the state space graph 
{
  private int totalVertices; // total number of vertices in the graph 
  private LinkedList<Integer> adjList[]; // adjacency list for the directed graph
  private Queue<Integer> fifoQueue; // a FIFO queue to track vertices as they are visited/explored  
  
  @SuppressWarnings("unchecked") // to suppress the warning resulting from not defining a generic type for adjList[i]
  /**
   * Class CONSTRUCTOR to instantiate the graph as a linked list of linked lists and to instantiate the FIFO queue
   * @param vertices // the number of vertices the graph will contain
   * @return Nothing
   */
  SPGraph(int vertices)
  {
    totalVertices = vertices;
    adjList = new LinkedList[totalVertices];
    for (int i=0; i<totalVertices; ++i)
    {
    adjList[i] = new LinkedList<>();
    }
    fifoQueue = new LinkedList<Integer>();  
  }

  /**
   * Function to add an edge to the adjacency list
   * @param vertex the graph vertex to be manipulated
   * @param adj the new adjacent edge for the vertex
   * @return Nothing
   */
  void insertEdge(int vertex, int adj)
  {
  adjList[vertex].add(adj);
  }

  /**
   * Function prints the Breadth First Search traversal starting from the given source. As the graph is traversed, it adds nodes to be explored to the back of the FIFO queue. As nodes are explored, they are removed from the front of the queue and printed to the terminal. Only newly explored nodes are printed to terminal. The process continues until the queue is empty.
   * @param start the source vertex from which to start traversing
   * @return Nothing
   */
  void BFS(int start)
  {
    boolean explored[] = new boolean[totalVertices];
    explored[start]=true;
    fifoQueue.add(start);
    while (fifoQueue.size() != 0)
    {
      start = fifoQueue.poll();
      System.out.print(start+" "); 
      Iterator<Integer> iterator = adjList[start].listIterator();
      while (iterator.hasNext())
      {
        int node = iterator.next();
        if (!explored[node])
        {
          explored[node] = true;
          fifoQueue.add(node);   
        }
      }
    }
  }

  /**
  * Main method which makes use of SPGraph class constructor, insertEdge method and BFS method. A customised message is thrown when an exception occurs due to a problem in the input
  * @param args Unused
  * @return Nothing
  * @exception IOException exception message thrown at runtime
  */
  public static void main(String args[]) throws IOException
  {
    try 
    {
      SPGraph spg = new SPGraph(5);
      spg.insertEdge(0, 1);
      spg.insertEdge(0, 2);
      spg.insertEdge(1, 2);
      spg.insertEdge(1, 4);
      spg.insertEdge(2, 3);
      spg.insertEdge(3, 4);
      spg.insertEdge(4, 4);
      
      System.out.println("The Breadth First Search, starting from vertex 0, traverses as follows to solve the search problem: ");
      spg.BFS(0);
    }
    catch (Exception e)
    {
      System.out.println("There is a problem here. Check the vertices and edges to be inserted match the state space graph ");   
    }
  }
}