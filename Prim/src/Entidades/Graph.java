package Entidades;

import java.util.List;
import java.util.ArrayList;

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

}