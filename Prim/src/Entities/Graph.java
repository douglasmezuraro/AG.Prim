package Entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {

    public final int infinite = Integer.MAX_VALUE;
    public List<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }

    public Vertex addVertex(int value) {
        Vertex vertex = new Vertex(value);
        this.vertices.add(vertex);
        return vertex;
    }

    public Vertex extractMin() {
        new Exception("Método não implementado!");
    }

    public static int weigth(Vertex a, Vertex b) {
        new ("Método não implementado!");
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
            Vertex u = extractMin();

            for(Vertex v: u.adjacent) {
                if((queue.contains(v)) && (weigth(u, v) < v.key)) {
                    v.predecessor = u;
                    v.key = weigth(u, v);
                }
            }
        }
    }

}