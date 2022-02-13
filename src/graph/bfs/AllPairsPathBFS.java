package graph.bfs;

import graph.base.UnWeightedGraph;

public class AllPairsPathBFS {

    private UnWeightedGraph G;
    private SingleSourcePathBFS paths[];

    public AllPairsPathBFS(UnWeightedGraph G){
        this.G = G;
        paths = new SingleSourcePathBFS[G.V()];
        for (int v = 0; v < G.V(); v++) {
            paths[v] = new SingleSourcePathBFS(G,v);
        }
    }

    public boolean isConnectedTo(int s, int t){
        G.validateVertex(s);
        return paths[s].isConnectedTo(t);
    }

    public Iterable<Integer> path(int s, int t){
        G.validateVertex(s);
        return paths[s].path(t);
    }
}
