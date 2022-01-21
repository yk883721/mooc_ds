package graph.dfs;

import graph.base.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SingleSourcePath {

    private Graph G;
    private int s;

    private boolean[] visited;
    private int pre[];


    public SingleSourcePath(Graph G, int s){

        G.validateVertex(s);

        this.G = G;
        this.s = s;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        Arrays.fill(pre, -1);

        dfs(s, s);

    }

    private void dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;

        for (Integer w : G.adj(v)) {
            if (!visited[w]){
                dfs(w, v);
            }
        }
    }

    public boolean isConnected(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected(t)){
            return res;
        }

        int cur = t;
        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 4);
        System.out.println("4 -> 4 : " + sspath.path(4));
        System.out.println("4 -> 3 : " + sspath.path(3));

    }

}
