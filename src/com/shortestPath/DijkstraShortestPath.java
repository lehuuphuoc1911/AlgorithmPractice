package com.shortestPath;

import java.util.ArrayList;

public class DijkstraShortestPath {
    private Graph graph;
    public static final int VISITED = 1;
    public static final int UNVISITED = 0;
    public static final int NO_NODE = -1;
    private int nodeStart = 0;
    private int nodeEnd = 0;
    private int nodeState[];
    private int shortestPath= Graph.INFINITY;
    private int tentativeDist[];

    public int getShortestPath() {
        return shortestPath;
    }

    public int getNodeStart() {
        return nodeStart;
    }

    public void setNodeStart(int nodeStart) {
        this.nodeStart = nodeStart;
    }

    public int getNodeEnd() {
        return nodeEnd;
    }

    public void setNodeEnd(int nodeEnd) {
        this.nodeEnd = nodeEnd;
    }

    public DijkstraShortestPath(Graph graph){
        this.graph = graph;
    }

    public void solve(int nodeStart, int nodeEnd){
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;

        nodeState = new int[graph.getNODE_QUANTITY()];
        tentativeDist = new int[graph.getNODE_QUANTITY()];
        for (int i = 0; i<graph.getNODE_QUANTITY(); i++){
            nodeState[i]=UNVISITED; //step 0
            tentativeDist[i]=graph.getDist(nodeStart,i); //step1
        }
        visitNeighbours(nodeStart);
        //nodeState[nodeStart] = VISITED;
    }

    private ArrayList<Integer> getUnvisitedNeighbours(int node){
        ArrayList<Integer> neighbours = new ArrayList<Integer>();
        int n = graph.getNODE_QUANTITY();
        for(int i = 0; i< n; i++){
            if (graph.getDist(node,i) < Graph.INFINITY && nodeState[i] == UNVISITED && i!=node){
                neighbours.add(i);
            }
        }
        /*
        System.out.print("Node " + node +": ");
        for (int neighbour:neighbours
             ) {
            System.out.print(neighbour);
        }
        System.out.println("***");
        */

        return neighbours;
    }
    private int getNearestUnvisitedNode(){
        int tempDist = Graph.INFINITY;
        int tempNode = NO_NODE;
        int n = graph.getNODE_QUANTITY();
        for(int i = 0; i< n;i++){
            if (tentativeDist[i]<tempDist && (i!=nodeStart)&&nodeState[i]==UNVISITED){
                tempDist = tentativeDist[i];
                tempNode = i;
            }
        }
        return tempNode;
    }

    private void visitNeighbours(int currentNode){
        ArrayList<Integer> unvisitedNeighbours = new ArrayList<Integer>();
        unvisitedNeighbours = getUnvisitedNeighbours(currentNode);
        for (int neighbour: unvisitedNeighbours   // step 3
             ) {
            if ((tentativeDist[currentNode]+ graph.getDist(currentNode,neighbour))<tentativeDist[neighbour]){
                tentativeDist[neighbour] = (tentativeDist[currentNode]+ graph.getDist(currentNode,neighbour));
            }
        }
        //printTentativeDist();
        nodeState[currentNode] = VISITED; //step 4
        if (currentNode == nodeEnd){ // step 5
            shortestPath = tentativeDist[currentNode];
            return;
        }
        int nextVisit = getNearestUnvisitedNode();
        if (nextVisit == NO_NODE){
            shortestPath = Graph.INFINITY;
            //System.out.println("search ends");
            return;
        } else { // step 6
            visitNeighbours(nextVisit);
        }
    }
    private void printTentativeDist(){
        for (int i = 0; i< graph.getNODE_QUANTITY();i++){
            System.out.print(tentativeDist[i] + "  ");
        }

        System.out.println(" ");
    }
}

/*
Dijkstra's algorithm (from wikipedia)

Let the node at which we are starting at be called the initial node.
Let the distance of node Y be the distance from the initial node to Y.
Dijkstra's algorithm will initially start with infinite distances and will try to improve them step by step.

1. Mark all nodes unvisited. Create a set of all the unvisited nodes called the unvisited set.
2. Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all other nodes.
Set the initial node as current.
3. For the current node, consider all of its unvisited neighbours and calculate their tentative distances through the
current node. Compare the newly calculated tentative distance to the current assigned value and assign the smaller one.
For example, if the current node A is marked with a distance of 6, and the edge connecting it with a neighbour B has length 2,
then the distance to B through A will be 6 + 2 = 8. If B was previously marked with a distance greater than 8 then change
it to 8. Otherwise, the current value will be kept.
4. When we are done considering all the unvisited neighbours of the current node, mark the current node as visited
and remove it from the unvisited set. A visited node will never be checked again.
5. If the destination node has been marked visited (when planning a route between two specific nodes) or if the
smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete traversal;
occurs when there is no connection between the initial node and remaining unvisited nodes), then stop. The algorithm has finished.
6. Otherwise, select the unvisited node that is marked with the smallest tentative distance, set it as the new "current node",
and go back to step 3.


When planning a route, it is actually not necessary to wait until the destination node is "visited" as above: the algorithm
can stop once the destination node has the smallest tentative distance among all "unvisited" nodes
(and thus could be selected as the next "current").
 */
