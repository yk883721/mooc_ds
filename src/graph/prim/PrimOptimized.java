package graph.prim;

import graph.base.WeightedCC;
import graph.base.WeightedEdge;
import graph.base.WeightedGraph;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimOptimized {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public PrimOptimized(WeightedGraph G){
        this.G = G;
        mst = new ArrayList<>();

        WeightedCC cc = new WeightedCC(G);
        if (cc.count() > 1){
            return;
        }

        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        Queue<WeightedEdge> pq = new PriorityQueue<>();

        for (Integer w : G.adj(0)) {
            pq.add(new WeightedEdge(0, w, G.getWeight(0, w)));
        }

        while (!pq.isEmpty()) {
            WeightedEdge minEdge = pq.remove();
            if (visited[minEdge.getV()] && visited[minEdge.getW()]){
                continue;
            }
            mst.add(minEdge);

            int newV = minEdge.getW();
            visited[newV] = true;
            for (Integer newW : G.adj(newV)) {
                if (!visited[newW]){
                    pq.add(new WeightedEdge(newV, newW, G.getWeight(newV, newW)));
                }
            }
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("g.txt");
        PrimOptimized prim = new PrimOptimized(g);
        System.out.println(prim.result());

    }

}
