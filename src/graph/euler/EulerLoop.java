package graph.euler;

import graph.base.UnWeightedGraph;
import graph.bridge.Edge;
import graph.dfs.CC;

import java.util.*;

public class EulerLoop {

    private UnWeightedGraph G;

    public EulerLoop(UnWeightedGraph G){
        this.G = G;

        this.res = new ArrayList<>();
        this.visited = new HashSet<>();

//        dfs(0,0,0);
    }

    private boolean hasEulerLoop(){
        CC cc = new CC(G);
        if (cc.count() > 1) {
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (G.degree(v) % 2 == 1) {
                return false;
            }
        }

        return true;
    }

    private ArrayList<Edge> res;
    private Set<Edge> visited;

    private boolean dfs(int v, int parent, int visitedNum){

        visited.add(new Edge(parent, v));
        visitedNum++;

        if (v == 0 && visitedNum == G.E() + 1){
            return true;
        }

        for (Integer w : G.adj(v)) {
            if (!visited.contains(new Edge(v, w))){
                if (dfs(w, v, visitedNum)){
                    res.add(new Edge(v, w));
                    return true;
                }
            }
        }

        visited.remove(new Edge(parent, v));
        return false;
    }
    public ArrayList<Edge> paths(){
        Collections.reverse(res);
        return res;
    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()){
            return res;
        }
        Stack<Integer> stack = new Stack<>();
        int curv = 0;
        stack.push(curv);

        while (!stack.isEmpty()){

            boolean allVistied = true;
            for (Integer w : G.adj(curv)) {
                if (!visited.contains(new Edge(curv, w))){
                    visited.add(new Edge(curv, w));
                    allVistied = false;
                    stack.push(curv);
                    curv = w;
                    break;
                }
            }

            if (allVistied){
                res.add(curv);
                curv = stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {

        UnWeightedGraph G = new UnWeightedGraph("g.txt");
        System.out.println(new EulerLoop(G).result());

    }


}
