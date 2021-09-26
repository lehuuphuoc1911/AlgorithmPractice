package com.shortestPath;

public class App {
    public static void main(String[] args){
        Graph graph = new Graph(8);
        graph.generateRandom();
        graph.printGraph();
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        dijkstraShortestPath.solve(0,3);
        System.out.println("*****************");
        System.out.println("Shortest Path:"+dijkstraShortestPath.getShortestPath());
    }
}
