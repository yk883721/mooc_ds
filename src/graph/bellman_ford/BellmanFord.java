package graph.bellman_ford;

import graph.base.WeightedGraph;

import java.util.Arrays;

public class BellmanFord {

    private WeightedGraph G;
    private int[] dist;

    private int s;

    private boolean hasNegCycle = false;

    public BellmanFord(WeightedGraph G, int s){
        G.validateVertex(s);

        this.G = G;
        dist = new int[G.V()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        for (int pass = 1; pass < G.V(); pass++) {

            for (int v = 0; v < G.V(); v++) {
                for (Integer w : G.adj(v)) {
                    if (dist[v] != Integer.MAX_VALUE && dist[v] + G.getWeight(v, w) < dist[w]){
                        dist[w] = dist[v] + G.getWeight(v, w);
                    }
                }
            }
        }

        for (int v = 0; v < G.V(); v++) {
            for (Integer w : G.adj(v)) {
                if (dist[v] != Integer.MAX_VALUE && dist[v] + G.getWeight(v, w) < dist[w]){
                    hasNegCycle = true;
                }
            }
        }

    }

    public boolean hasNegativeCycle(){
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);
        return dist[v] != Integer.MAX_VALUE;
    }

    public int distTo(int v){
        G.validateVertex(v);
        if(hasNegCycle) throw new RuntimeException("exist negative cycle.");
        return dist[v];
    }

    public static void main(String[] args){

        WeightedGraph g = new WeightedGraph("g.txt");
        BellmanFord bf = new BellmanFord(g, 0);
        if(!bf.hasNegativeCycle()){
            for(int v = 0; v < g.V(); v ++)
                System.out.print(bf.distTo(v) + " ");
            System.out.println();
        }
        else
            System.out.println("exist negative cycle.");

//        WeightedGraph g2 = new WeightedGraph("g2.txt");
//        BellmanFord bf2 = new BellmanFord(g2, 0);
//        if(!bf2.hasNegativeCycle()){
//            for(int v = 0; v < g2.V(); v ++)
//                System.out.print(bf2.distTo(v) + " ");
//            System.out.println();
//        }
//        else
//            System.out.println("exist negative cycle.");
    }

}
