package graph.base;

import java.util.ArrayList;
import java.util.Arrays;

public class WeightedCC {

    private WeightedGraph G;
    private int[] visited;
    private int cccount;

    public WeightedCC(WeightedGraph G){
        this.G = G;
        this.visited = new int[G.V()];
        Arrays.fill(visited, -1);

        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1){
                dfs(v, cccount);
                cccount++;
            }
        }

    }

    private void dfs(int v, int ccid){
        visited[v] = ccid;
        for (Integer w : G.adj(v)) {
            if (visited[w] == -1){
                dfs(w, ccid);
            }
        }
    }

    public int count(){
        return cccount;
    }

    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);

        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){

        ArrayList<Integer>[] res = new ArrayList[cccount];
        for(int i = 0; i < cccount; i ++)
            res[i] = new ArrayList<Integer>();

        for(int v = 0; v < G.V(); v ++)
            res[visited[v]].add(v);
        return res;
    }


}
