package algorithm.baekjoon.middle2.C_graph2;

public class Edge {
    public int start;
    public int end;
    public int cost;

    public Edge() {
        this(0,0,0);
    }

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}
