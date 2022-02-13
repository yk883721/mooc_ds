package graph.dijkstra;

import graph.base.WeightedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraOptimized {

    private WeightedGraph G;
    private int s;

    private int[] dist;
    private boolean[] visited;

    private int[] pre;

    public DijkstraOptimized(WeightedGraph G, int s){

        this.G = G;
        this.s = s;
        dist = new int[G.V()];
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        dist[s] = 0;
        pre[s] = s;

        for (int w = 1; w < G.V(); w++) {
            dist[w] = Integer.MAX_VALUE;
        }
        for (int w = 1; w < G.V(); w++) {
            pre[w] = -1;
        }

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()){

            Node curNode = pq.remove();
            int cur = curNode.v;

            if (visited[cur]){
                continue ;
            }

            visited[cur] = true;
            for (Integer w : G.adj(cur)) {
                if (!visited[w] && (dist[cur] + G.getWeight(cur, w)) < dist[w]){
                    dist[w] = dist[cur] + G.getWeight(cur, w);
                    pq.add(new Node(w,dist[w]));
                    pre[w] = cur;
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

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if (!visited[t]){
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

    private static class Node implements Comparable<Node> {

        public int v, dist;

        public Node(int v, int dist){
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node another) {
            return dist - another.dist;
        }

    }

    static public void main(String[] args){

        WeightedGraph g = new WeightedGraph("g.txt");
        DijkstraOptimized dij = new DijkstraOptimized(g, 0);
        for(int v = 0; v < g.V(); v ++)
            System.out.print(dij.distTo(v) + " ");
        System.out.println();

        System.out.println(dij.path(4));

    }


}
