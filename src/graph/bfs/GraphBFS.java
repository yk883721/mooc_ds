package graph.bfs;

import graph.base.UnWeightedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

    private UnWeightedGraph G;
    private boolean[] visited;
    private ArrayList<Integer> orders = new ArrayList<>();


    public GraphBFS(UnWeightedGraph G){
        this.G = G;
        visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]){
                bfs(v);
            }

        }
    }

    private void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()){
            Integer v = queue.remove();
            orders.add(v);

            for (Integer w : G.adj(v)) {
                if (!visited[w]){
                    visited[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    public Iterable<Integer> order(){
        return orders;
    }

    public static void main(String[] args) {

        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println("BFS Order : " + graphBFS.order());

    }


}
