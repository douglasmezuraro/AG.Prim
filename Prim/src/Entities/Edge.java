package Entities;

public class Edge {

    public Vertex a;
    public Vertex b;
    public int weight;

    public Edge(Vertex a, Vertex b) {
        this(a, b, 0);
    }

    public Edge(Vertex a, Vertex b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
    
}