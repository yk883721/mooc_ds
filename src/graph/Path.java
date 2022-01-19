package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Path {

    private int s, t;

    private final Graph G;
    private boolean[] visited;
    private int[] pre;

    public Path(Graph G, int s, int t){
        G.validateVertex(s);
        G.validateVertex(t);

        this.G = G;
        this.s = s;
        this.t = t;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        dfs(s, s);

        for(boolean e: visited)
            System.out.print(e + " ");
        System.out.println();
    }

    public boolean dfs(int v, int parent){

        visited[v] = true;
        pre[v] = parent;
        if (v == this.t){
            return true;
        }

        for (Integer w : G.adj(v)) {
            if (!visited[w]){
                if (dfs(w, v)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isConnected(){
        return visited[t];
    }

    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected()){
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

        Path path = new Path(g, 0, 6);
        System.out.println("0 -> 6 : " + path.path());

        Path path2 = new Path(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());

        Path path3 = new Path(g, 0, 1);
        System.out.println("0 -> 1 : " + path3.path());
    }

}
