package graph.bfs;

import DummyLinkedList.LinkedListQueue;
import graph.base.Graph;
import queue.Queue;

public class BipartitionDetectionBFS {

    private Graph G;
    private boolean[] visited;
    private int[] colors;

    private boolean isBipartite = true;

    public BipartitionDetectionBFS(Graph G){
        this.G = G;
        this.visited = new boolean[G.V()];
        this.colors = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]){
                if (!bfs(v)){
                    isBipartite = false;
                }
            }
        }
    }

    public boolean bfs(int s){
        Queue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(s);
        visited[s] = true;
        colors[s] = 0;

        while (!queue.isEmpty()){
            Integer v = queue.dequeue();

            for (Integer w : G.adj(v)) {
                if (!visited[w]){
                    queue.enqueue(w);
                    visited[w] = true;
                    colors[w] = 1 - colors[v];
                }else if(colors[w] == colors[v]){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        BipartitionDetectionBFS bipartitionDetection = new BipartitionDetectionBFS(g);
        System.out.println(bipartitionDetection.isBipartite);
        // true

    }

}
