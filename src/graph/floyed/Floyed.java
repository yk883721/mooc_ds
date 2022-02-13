package graph.floyed;

import graph.base.WeightedGraph;

import java.util.Arrays;

public class Floyed {

    private WeightedGraph G;
    private int[][] dist;
    private boolean hasNegCycle = false;

    public Floyed(WeightedGraph G){
        this.G = G;
        dist = new int[G.V()][G.V()];

        for (int i = 0; i < G.V(); i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        for (int v = 0; v < G.V(); v++) {
            dist[v][v] = 0;
            for (Integer w : G.adj(v)) {
                dist[v][w] = G.getWeight(v, w);
            }
        }

        for (int t = 0; t < G.V(); t++) {
            for (int v = 0; v < G.V(); v++) {
                for (int w = 0; w < G.V(); w++) {
                    if (dist[v][t] != Integer.MAX_VALUE && dist[t][w] != Integer.MAX_VALUE
                            && dist[v][t] + dist[t][w] < dist[v][w]){
                        dist[v][w] = dist[v][t] + dist[t][w];
                    }
                }
            }
        }

        for (int v = 0; v < G.V(); v++) {
            if (dist[v][v] < 0) {
                hasNegCycle = true;
            }
        }

    }

    public boolean hasNegativeCycle(){
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return dist[v][w] != Integer.MAX_VALUE;
    }

    public int distTo(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return dist[v][w];
    }

    static public void main(String[] args){

        WeightedGraph g = new WeightedGraph("g.txt");
        Floyed floyed = new Floyed(g);
        if(!floyed.hasNegativeCycle()){
            for(int v = 0; v < g.V(); v ++){
                for(int w = 0; w < g.V(); w ++)
                    System.out.print(floyed.distTo(v, w) + " ");
                System.out.println();
            }
        }
        else
            System.out.println("exist negative cycle.");


    }

}
