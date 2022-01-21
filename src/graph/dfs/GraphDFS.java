package graph.dfs;

import graph.base.Graph;

import java.util.ArrayList;
import java.util.Stack;

public class GraphDFS {

    private Graph G;

    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();

    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph G){

        this.G = G;
        this.visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfsNr(v);
            }
        }

    }

    private void dfs(int v){

        visited[v] = true;
        pre.add(v);

        for (Integer w : G.adj(v)) {
            if (!visited[w]){
                dfs(w);
            }
        }

        post.add(v);

    }

    private void dfsNr(int v){
        Stack<Integer> stack = new Stack<>();
        visited[v] = true;
        stack.push(v);
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            pre.add(pop);
            for (Integer w : G.adj(pop)) {
                if (!visited[w]){
                    visited[w] = true;
                    stack.push(w);
                }
            }
        }
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.pre());
//        System.out.println(graphDFS.post());

    }

}
