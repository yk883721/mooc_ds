package graph.dfs;

import graph.base.UnWeightedGraph;

public class CycleDetection {

    private UnWeightedGraph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(UnWeightedGraph G){

        this.G = G;
        this.visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]){
                if (dfs(v,v)){
                    hasCycle = true;
                    break;
                }
            }
        }

    }

    private boolean dfs(int v, int parent){
        visited[v] = true;
        for (Integer w : G.adj(v)) {
            if (!visited[w]){
                if (dfs(w, v)){
                    return true;
                }
            }else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){

        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

//        Graph g2 = new Graph("g2.txt");
//        CycleDetection cycleDetection2 = new CycleDetection(g2);
//        System.out.println(cycleDetection2.hasCycle());
    }

}
