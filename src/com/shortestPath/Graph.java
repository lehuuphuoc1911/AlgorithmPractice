package com.shortestPath;

import java.util.Random;

public class Graph {
    private final int NODE_QUANTITY;
    public static final int INFINITY = Integer.MAX_VALUE;
    private int dist[][];
    public Graph(int NODE_QUANTITY){
        this.NODE_QUANTITY = NODE_QUANTITY;
    }

    public void generateRandom(){
        dist = new int[NODE_QUANTITY][NODE_QUANTITY];
        for (int i = 0 ; i<NODE_QUANTITY; i++){
            dist[i][i]=0;
            for (int j = i+1; j<NODE_QUANTITY; j++){
                Random rd = new Random();
                int num = rd.nextInt(40);
                if(num>0 && num <10){
                    dist[i][j] = num;
                }
                else dist[i][j] = Graph.INFINITY;
                dist[j][i] = dist[i][j];
            }
        }
    }

    public int getDist(int i, int j) {
        return dist[i][j];
    }

    public int getNODE_QUANTITY() {
        return NODE_QUANTITY;
    }

    public void printGraph(){
        for (int i = 0 ; i<NODE_QUANTITY; i++) {
            for (int j = 0; j < NODE_QUANTITY ; j++) {
                if (dist[i][j] != INFINITY){
                    System.out.print(dist[i][j] + "  ");
                } else {
                    System.out.print("-  ");
                }
            }
            System.out.println();
        }
    }

}
