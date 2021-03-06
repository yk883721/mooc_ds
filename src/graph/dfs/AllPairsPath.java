package graph.dfs;

import graph.base.UnWeightedGraph;

public class AllPairsPath {

    private UnWeightedGraph G;
    private SingleSourcePath[] paths;

    public AllPairsPath(UnWeightedGraph G){

        this.G = G;
        paths = new SingleSourcePath[G.V()];

        for (int v = 0; v < G.V(); v++) {
            paths[v] = new SingleSourcePath(G, v);
        }

    }

    public boolean isConnected(int s, int t){

        G.validateVertex(s);
        return paths[s].isConnected(t);

    }

    public Iterable<Integer> path(int s, int t){

        G.validateVertex(s);
        return paths[s].path(t);

    }

    public static void main(String[] args){

        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        AllPairsPath paths = new AllPairsPath(g);

        System.out.println("0 -> 3 : " + paths.path(0, 3));

    }


}
