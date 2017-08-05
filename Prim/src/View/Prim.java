package View;

import Entities.*;

public class Prim {

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("f");
        graph.addVertex("g");
        graph.addVertex("h");
        graph.addVertex("i");

        graph.addEdge("a", "b", 4);
        graph.addEdge("a", "h", 8);
        graph.addEdge("b", "c", 8);
        graph.addEdge("b", "h", 11);
        graph.addEdge("c", "d", 7);
        graph.addEdge("c", "f", 4);
        graph.addEdge("c", "i", 2);
        graph.addEdge("d", "e", 9);
        graph.addEdge("d", "f", 14);
        graph.addEdge("e", "f", 10);
        graph.addEdge("f", "g", 2);
        graph.addEdge("g", "i", 6);
        graph.addEdge("g", "h", 1);
        graph.addEdge("i", "h", 7);

        graph.prim(graph.getVertex("a"));
    }
    
}
