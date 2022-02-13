package graph.bridge;

import java.util.Objects;

public class Edge {

    private int v, w;

    public Edge(int v, int w){
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString(){
        return String.format("%d-%d", v, w);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (v == edge.v && w == edge.w) || (v == edge.w && w == edge.v);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
