package graph.bfs;

import DummyLinkedList.LinkedListQueue;
import graph.base.UnWeightedGraph;
import queue.Queue;

import java.util.Arrays;

public class CycleDetectionBFS {

    private UnWeightedGraph G;
    private boolean[] visited;
    private int[] pre;

    public boolean hasCycle = false;

    public CycleDetectionBFS(UnWeightedGraph G){

        this.G = G;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        Arrays.fill(pre, -1);

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]){
                if (bfs(v)){
                    hasCycle = true;
                    break;
                }
            }
        }

    }

    // 从顶点 v 开始，判断图中是否有环
    public boolean bfs(int s){
        Queue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(s);
        visited[s] = true;
        pre[s] = s;

        while (!queue.isEmpty()){
            Integer v = queue.dequeue();
            for (Integer w : G.adj(v)) {
                if (!visited[w]){
                    queue.enqueue(w);
                    visited[w] = true;
                    pre[w] = v;
                }else if (pre[v] != w){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){

        UnWeightedGraph g = new UnWeightedGraph("g.txt");
        CycleDetectionBFS cycleDetection = new CycleDetectionBFS(g);
        System.out.println(cycleDetection.hasCycle());

    }

}
