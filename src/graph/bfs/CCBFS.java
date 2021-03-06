package graph.bfs;

import DummyLinkedList.LinkedListQueue;
import graph.base.UnWeightedGraph;
import queue.Queue;

import java.util.ArrayList;

public class CCBFS {

    private UnWeightedGraph G;

    private int[] visited;
    private int cccount = 0;

    public CCBFS(UnWeightedGraph G){

        this.G = G;
        this.visited = new int[G.V()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }

        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1){
                bfs(v, cccount);
                cccount++;
            }
        }
    }

    private void bfs(int s, int ccid){

        Queue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(s);
        visited[s] = ccid;

        while (!queue.isEmpty()){

            Integer v = queue.dequeue();
            for (Integer w : G.adj(v)) {
                if (visited[w] == -1) {
                    visited[w] = ccid;
                    queue.enqueue(w);
                }
            }
        }
    }

    public int count(){
        return cccount;
    }

    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < cccount; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < G.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args){

        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        CCBFS cc = new CCBFS(g);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
        for(int ccid = 0; ccid < comp.length; ccid ++){
            System.out.print(ccid + " : ");
            for(int w: comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }


}
