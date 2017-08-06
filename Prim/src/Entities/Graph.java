package Entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {

    public static final int infinite = Integer.MAX_VALUE;
    public List<Vertex> vertices;
    public List<Edge> edges;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public Vertex addVertex(String name) throws Exception {
        if(this.getVertex(name) == null) {
            Vertex vertex = new Vertex(name);
            this.vertices.add(vertex);
            return vertex;
        }
        else throw new Exception("Vertice '" + name + "' duplicado!");
    }

    public Edge addEdge(String source, String target, int weight) throws Exception { 
        Vertex sourceVertex = getVertex(source);
        Vertex targetVertex = getVertex(target);

        if(this.getEdge(sourceVertex, targetVertex) == null) {

            Edge edge = new Edge(sourceVertex, targetVertex, weight);

            sourceVertex.adjacent.add(targetVertex);
            targetVertex.adjacent.add(sourceVertex);

            this.edges.add(edge);

            return edge;
        } else throw new Exception("Aresta '" + source + "' - '" + target + "' duplicada!");
    }

    public Vertex getVertex(String name) {
        for(Vertex u: this.vertices)
            if(u.name.equals(name))
                return u;
        
        return null;
    }

    public Edge getEdge(Vertex source, Vertex target) {
        for(Edge edge: this.edges) {
            if(edge.source.name.equals(source.name)) 
                if(edge.target.name.equals(target.name))
                    return edge;
            
            if(edge.source.name.equals(target.name)) 
                if(edge.target.name.equals(source.name))
                    return edge;  
        }
        return null;
    }

    public Vertex extractMin(Queue<Vertex> Q) {
        Vertex minVertex = null;
        int minWeight = infinite;

        for(Vertex u: Q) {
            if((u.color == Color.white) && (u.key < minWeight)) {
                minVertex = u;
                minWeight = u.key;
            }
        }
        
        Q.remove(minVertex);
        minVertex.color = Color.gray;
        return minVertex;
    }

    public int weigth(Vertex source, Vertex target) {
        return getEdge(source, target).weight;
    }

    public void prim(Vertex r) {
        for(Vertex u: this.vertices) {
            u.key = infinite;
            u.predecessor = null;
        }

        r.key = 0;

        Queue<Vertex> Q = new LinkedList<>();
        
        Q.addAll(this.vertices);

        while(!Q.isEmpty()) {
            Vertex u = extractMin(Q);

            for(Vertex v: u.adjacent) {
                if((Q.contains(v)) && (weigth(u, v) < v.key)) {
                    v.predecessor = u;
                    v.key = weigth(u, v);              
                }
            }        
            
            if(u.predecessor != null)
                System.out.println(getEdge(u.predecessor, u));
        }
    }

}