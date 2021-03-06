package graph.bfs;

import graph.base.UnWeightedGraph;

import java.util.*;

public class SingleSourcePathBFS {

    private UnWeightedGraph G;
    private int s;

    private boolean[] visited;
    private int[] pre;

    public SingleSourcePathBFS(UnWeightedGraph G, int s){

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

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)){
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

        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        SingleSourcePathBFS sspath = new SingleSourcePathBFS(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
    }


}
