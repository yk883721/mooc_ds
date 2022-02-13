package graph.kruskal;

import graph.base.WeightedCC;
import graph.base.WeightedEdge;
import graph.base.WeightedGraph;
import unionfind.QuickUnion;
import unionfind.UF;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    //并查集
    private final int[] parent;
    private final int[] rank;

    public Kruskal(WeightedGraph G){
        // 并查集初始化
        parent = new int[G.V()];
        rank = new int[G.V()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        // 图相关初始化
        this.G = G;
        mst = new ArrayList<>();

        // 判断连通分量个数，若大于一，则不是连通图
        WeightedCC cc = new WeightedCC(G);
        if (cc.count() > 1){
            return ;
        }

        //所有边按权值大小排序
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < G.V(); v++) {
            for (Integer w : G.adj(v)) {
                if (v < w){
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
                }
            }
        }
        // 所有边排序
        Collections.sort(edges);

        // 克鲁斯卡尔算法
        // 对于所有变，按权值大小，从小到大以此比较
        // 每次取出一条边，判断是否会将已取出的所有点连接成环
        // 若已连通，则放弃该边
        for (WeightedEdge edge : edges) {
            // 边的两个顶点
            int v = edge.getV();
            int w = edge.getW();

            // 并查集的祖先节点
            int vId = find(v);
            int wId = find(w);

            // 不连通时则进行操作
            if (vId != wId){
                mst.add(edge);

                // 并查集 union，rank帮助判断 union 的两个树的大小
                if (rank[vId] < rank[wId]){
                    parent[vId] = wId;
                }else if (rank[wId] < rank[vId]){
                    parent[wId] = vId;
                }else {
                    parent[vId] = wId;
                    rank[wId] += 1;
                }
            }
        }

    }

    /**
     * 查找元素 index 对应的集合编号
     */
    private int find(int index){
        int p = index;
        while (parent[p] != p){
            p = parent[p];
        }
        return p;
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args){

        WeightedGraph g = new WeightedGraph("g.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }

}
