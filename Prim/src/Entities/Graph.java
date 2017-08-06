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

    public Vertex addVertex(String name) {
        Vertex vertex = new Vertex(name);
        this.vertices.add(vertex);
        return vertex;
    }

    public Edge addEdge(String source, String target, int weight) {
        Vertex sourceVertex = getVertex(source);
        Vertex targetVertex = getVertex(target);

        Edge edge = new Edge(sourceVertex, targetVertex, weight);

        this.edges.add(edge);
        
        sourceVertex.adjacent.add(targetVertex);
        targetVertex.adjacent.add(sourceVertex);

        return edge;
    }

    public Vertex getVertex(String name) throws NullPointerException {
        for(Vertex u: this.vertices)
            if(u.name == name)
                return u;
        
        throw new NullPointerException("Vértice não encontrado!");
    }

    public Edge getEdge(Vertex source, Vertex target) {
        for(Edge edge: this.edges) {
            if(edge.source == source) 
                if(edge.target == target)
                    return edge;
            else if(edge.source == target) 
                if(edge.target == source)
                    return edge;  
        }
        return null;
    }

    public Vertex extractMin(Queue<Vertex> queue) {
        Vertex minVertex = null;
        int minWeight = this.infinite;

        for(Vertex u: queue) {
            if(minVertex == null) 
                minVertex = u;
               
            for(Vertex v: u.adjacent) {
                Edge edge = getEdge(u, v);

                if(edge != null) 
                    if(edge.weight < minWeight) {
                        minVertex = v; 
                        minWeight = edge.weight;
                    }
            }                      
        }

        return minVertex;
    }

    public int weigth(Vertex source, Vertex target) {
        Edge edge = getEdge(source, target);
        return edge.weight;
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