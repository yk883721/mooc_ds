package graph.bridge;

import graph.base.UnWeightedGraph;

import java.util.ArrayList;

public class FindBridges {

    private UnWeightedGraph G;
    private boolean[] visited;

    private int[] order;
    private int[] low;

    private ArrayList<Edge> res;
    private int cnt;

    public FindBridges(UnWeightedGraph G){
        this.G = G;
        this.visited = new boolean[G.V()];
        this.order = new int[G.V()];
        this.low = new int[G.V()];
        this.res = new ArrayList<>();
        this.cnt = 0;

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]){
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent){

        visited[v] = true;
        order[v] = cnt;
        low[v] = order[v];
        cnt++;

        for (Integer w : G.adj(v)) {
            if (!visited[w]){
                dfs(w, v);

                if (low[w] < low[v]){
                    low[v] = low[w];
                }

                if (low[w] > order[v]){
                    res.add(new Edge(v, w));
                }

            }else if (w != parent){
                if (low[w] < low[v]){
                    low[v] = low[w];
                }
            }
        }
    }

    public ArrayList<Edge> result(){
        return res;
    }

    public static void main(String[] args){

        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        FindBridges fb = new FindBridges(g);
        System.out.println("Bridges in g : " + fb.result());

    }


}
