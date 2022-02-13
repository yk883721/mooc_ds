package graph.dijkstra;

import graph.base.WeightedEdge;
import graph.base.WeightedGraph;

public class Dijkstra {

    private WeightedGraph G;
    private int s;

    private int[] dist;
    private boolean[] visited;

    public Dijkstra(WeightedGraph G, int s){

        this.G = G;
        this.s = s;
        dist = new int[G.V()];
        visited = new boolean[G.V()];

        dist[0] = 0;
        for (int w = 1; w < G.V(); w++) {
            dist[w] = Integer.MAX_VALUE;
        }

        while (true){
            int cur = -1;
            int minValue = Integer.MAX_VALUE;
            for (int v = 0; v < G.V(); v++) {
                if (!visited[v] && dist[v] < minValue){
                    cur = v;
                    minValue = dist[v];
                }
            }
            if (cur == -1){
                break;
            }
            visited[cur] = true;
            for (Integer w : G.adj(cur)) {
                if (!visited[w] && (dist[cur] + G.getWeight(cur, w)) < dist[w]){
                    dist[w] = dist[cur] + G.getWeight(cur, w);
                }
            }
        }
    }

    public boolean isConnected(int v){
        G.validateVertex(v);
        return visited[v];
    }

    public int distTo(int v){
        G.validateVertex(v);
        return dist[v];
    }

    static public void main(String[] args){

        WeightedGraph g = new WeightedGraph("g.txt");
        Dijkstra dij = new Dijkstra(g, 0);
        for(int v = 0; v < g.V(); v ++)
            System.out.print(dij.distTo(v) + " ");
        System.out.println();
    }


}
