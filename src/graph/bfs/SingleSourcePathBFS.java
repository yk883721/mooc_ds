package graph.bfs;

import graph.base.Graph;

import java.util.*;

public class SingleSourcePathBFS {

    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;

    public SingleSourcePathBFS(Graph G, int s){

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        bfs(s);

    }

    private void bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        visited[s] = true;
        pre[s] = s;

        while (!queue.isEmpty()){
            Integer v = queue.remove();

            for (Integer w : G.adj(v)) {
                if (!visited[w]){

                    visited[w] = true;
                    pre[w] = v;
                    queue.add(w);
                }
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
        SingleSourcePathBFS sspath = new SingleSourcePathBFS(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
    }


}
