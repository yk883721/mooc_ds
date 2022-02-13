package graph.dfs;

import graph.base.UnWeightedGraph;

import java.util.Arrays;

public class BipartitionDetection {

    private UnWeightedGraph G;

    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(UnWeightedGraph G){

        this.G = G;
        this.visited = new boolean[G.V()];
        this.colors = new int[G.V()];
        Arrays.fill(colors, -1);

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]){
                if (!dfs(v, 0)){
                    isBipartite = false;
                }
            }
        }

    }

    public boolean dfs(int v, int color){

        visited[v] = true;
        colors[v] = color;
        for (Integer w : G.adj(v)) {
            if (!visited[w]){
                if (!dfs(w, 1 - color)){
                    return false;
                }
            }else if (colors[v] == colors[w]){
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public static void main(String[] args){

        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite);
        // true

//        Graph g2 = new Graph("g2.txt");
//        BipartitionDetection bipartitionDetection2 = new BipartitionDetection(g2);
//        System.out.println(bipartitionDetection2.isBipartite);
//        // false
//
//        Graph g3 = new Graph("g3.txt");
//        BipartitionDetection bipartitionDetection3 = new BipartitionDetection(g3);
//        System.out.println(bipartitionDetection3.isBipartite);
        // true
    }



}
