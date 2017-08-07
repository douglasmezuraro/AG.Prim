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

    /**
     * @return resultado da verificação se o grafo é conexo
     */
    public Boolean connected() {
        for(Vertex u: this.vertices)
            if(u.degree() <= 0)
                return false;
        
        return true;
    }

    /**
     * Método que adiciona o vértice "name" na lista de vértices do grafo
     * @param name
     * @return vértice adicionado
     * @throws Exception erro de duplicidade 
     */
    public Vertex addVertex(String name) throws Exception {
        if(this.getVertex(name) == null) {
            Vertex vertex = new Vertex(name);
            this.vertices.add(vertex);
            return vertex;
        }
        else throw new Exception("Vertice '" + name + "' duplicado!");
    }

    /**
     * Método que adiciona a aresta passada como parâmetro na lista de arestas 
     * do grafo
     * @param source
     * @param target
     * @param weight
     * @return aresta adicionada
     * @throws Exception erro de duplicidade
     */
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

    /**
     * Método que retorna o objeto vértice passando como parâmetro sua descrição
     * @param name
     * @return vértice caso encontre, null caso contrário
     */
    public Vertex getVertex(String name) {
        for(Vertex u: this.vertices)
            if(u.name.equals(name))
                return u;
        
        return null;
    }
    
    /**
     * Método que retorna a aresta entre os dois vértices passado como parâmetro
     * @param source
     * @param target
     * @return aresta caso encontre, null caso contrário
     */
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
    
    /**
     * Método que tira o vértice de menor chave possível da fila Q
     * @param Q
     * @return vértice de menor chave
     */
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

    /**
     * Método que retorna peso da aresta que conecta os dois vértices passados
     * como parâmetro
     * @param source
     * @param target
     * @return peso da aresta
     */
    public int weigth(Vertex source, Vertex target) {
        return getEdge(source, target).weight;
    }
    
    /**
     * Overload no método "Prim()" para poder passar como parâmetro o nome do vértice
     * @param r 
     */
    public void prim(String r) {
        this.prim(this.getVertex(r));
    }
    
    /**
     * Implementação do algoritmo de Prim
     * @param r 
     */
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