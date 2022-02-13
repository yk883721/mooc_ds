package graph.hamilton;

import graph.base.UnWeightedGraph;

import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop {

    private UnWeightedGraph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop(UnWeightedGraph G){
        this.G = G;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        this.end = -1;

        dfs(0, 0, 0);

    }

    private boolean dfs(int v, int parent, int visitedNum){

        visited[v] = true;
        pre[v] = parent;
        visitedNum++;
        if (visitedNum == G.V() && G.hasEdge(v, 0)){
            end = v;
            return true;
        }

        for (Integer w : G.adj(v)) {
            if (!visited[w]){
                if (dfs(w, v, visitedNum)){
                    return true;
                }
            }
        }
        visited[v] = false;
        return false;
    }

    public ArrayList<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;

        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.result());

//        Graph g2 = new Graph("g2.txt");
//        HamiltonLoop hl2 = new HamiltonLoop(g2);
//        System.out.println(hl2.result());
    }

}
