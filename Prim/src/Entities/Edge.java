package Entities;

public class Edge {

    public Vertex source;
    public Vertex target;
    public int weight;

    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return "Aresta: " + this.source.name + " - " + this.target.name + "; Peso = " + this.weight + ";"; 
    }
}