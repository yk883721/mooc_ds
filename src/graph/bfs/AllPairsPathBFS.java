package graph.bfs;

import graph.base.Graph;

public class AllPairsPathBFS {

    private Graph G;
    private SingleSourcePathBFS paths[];

    public AllPairsPathBFS(Graph G){
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
