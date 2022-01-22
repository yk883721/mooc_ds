package graph.bfs;

import DummyLinkedList.LinkedListQueue;
import graph.base.Graph;
import queue.Queue;

import java.util.ArrayList;
import java.util.Collections;

public class USSSPath {

    private Graph G;

    private int s;

    private boolean[] visited;
    private int[] pre;
    private int[] dist;

    public USSSPath(Graph G, int s){

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dist = new int[G.V()];

        for(int i = 0; i < G.V(); i ++) {
            pre[i] = -1;
            dist[i] = -1;
        }

        bfs(s);

        for(int i = 0; i < G.V(); i ++)
            System.out.print(dist[i] + " ");
        System.out.println();
    }

    public void bfs(int s){
        Queue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(s);
        visited[s] = true;
        pre[s] = s;
        dist[s] = 0;

        while (!queue.isEmpty()){
            Integer v = queue.dequeue();
            for (Integer w : G.adj(v)) {
                if (!visited[w]){
                    queue.enqueue(w);
                    visited[w] = true;
                    pre[w] = v;
                    dist[w] = 1 + dist[v];
                }
            }
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public int dist(int t){
        G.validateVertex(t);
        return dist[t];
    }

    public Iterable<Integer> path(int t){

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(t)) return res;

        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        USSSPath ussspath = new USSSPath(g, 0);
        System.out.println("0 -> 6 : " + ussspath.path(6));
        System.out.println("0 -> 6 : " + ussspath.dist(6));
    }

}
