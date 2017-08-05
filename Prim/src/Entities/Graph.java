package Entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {

    public final int infinite = Integer.MAX_VALUE;
    public List<Vertex> vertices;
    public List<Edge> edges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public Vertex addVertex(int value) {
        Vertex vertex = new Vertex(value);
        this.vertices.add(vertex);
        return vertex;
    }

    public Edge addEdge(int a, int b, int weight) {
        Vertex va = getVertex(a);
        Vertex vb = getVertex(b);

        Edge edge = new Edge(va, vb, weight);

        this.edges.add(edge);
        
        va.adjacent.add(vb);
        vb.adjacent.add(va);

        return edge;
    }

    public Vertex getVertex(int value) throws NullPointerException {
        for(Vertex u: this.vertices)
            if(u.value == value)
                return u;
        
        throw new NullPointerException("Vértice não encontrado!");
    }

    public Edge getEdge(Vertex a, Vertex b) throws NullPointerException {
        for(Edge e: this.edges) {
            if(e.a == a) 
                if(e.b == b)
                    return e;
            else if(e.a == b) 
                if(e.b == a)
                    return e;  
        }
        throw new NullPointerException("Aresta não encontrada!");
    }

    public Vertex extractMin(Queue<Vertex> queue) {
        Vertex min = null;
        int weight = 0;

        for(Vertex u: queue) {
            if(min == null)
                min = u;
            else {
                for(Vertex v: u.adjacent) {
                    if(getEdge(u, v).weight < weight)
                        min = u;
                }
            }
        }

        return min;
    }

    public int weigth(Vertex a, Vertex b) {
        return getEdge(a, b).weight;
    }

    public void prim(Vertex r) {
        for(Vertex u: this.vertices) {
            u.key = infinite;
            u.predecessor = null;
        }

        r.key = 0;

        Queue<Vertex> queue = new LinkedList<>();

        queue.addAll(this.vertices);

        while(!queue.isEmpty()) {
            Vertex u = extractMin(queue);

            for(Vertex v: u.adjacent) {
                if((queue.contains(v)) && (weigth(u, v) < v.key)) {
                    v.predecessor = u;
                    v.key = weigth(u, v);
                }
            }
        }
    }

}