package graph.hamilton;

import graph.base.UnWeightedGraph;

import java.util.ArrayList;
import java.util.Collections;

public class HamiltonPath {

    private UnWeightedGraph G;

    private boolean[] visited;
    private int[] pre;
    private int end;
    private int s;

    public HamiltonPath(UnWeightedGraph G, int s){
        this.G = G;
        this.s = s;

        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        this.end = -1;

        dfs(s,s,0);

    }

    private boolean dfs(int v, int parent, int visitedNum){
        visited[v] = true;
        pre[v] = parent;
        visitedNum++;
        if (visitedNum == G.V()){
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
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        HamiltonPath hp = new HamiltonPath(g, 0);
        System.out.println(hp.result());

        HamiltonPath hp2 = new HamiltonPath(g, 1);
        System.out.println(hp2.result());
    }


}
