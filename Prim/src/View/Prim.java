package View;

import Entities.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Prim {

    public static final Graph graph = new Graph();
    public static final String sVerticesTag = "vertices";
    public static final String sEdgeTag = "aresta";
    public static final String sStartTag = "inicio";
    
    /**
     * Método que recebe uma string com os vértices lidos do arquivo de entrada
     * e adiciona ao grafo
     * @param vertices 
     */
    public static void addVertices(String vertices) {
        vertices = vertices.replaceFirst(sVerticesTag + "=", "");
        for(String vertex: vertices.split(",")) {
            try {
                graph.addVertex(vertex);
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }            
        }
    }

    /**
     * Método que recebe uma string com uma aresta lida do arquivo de entrada
     * e adiciona ao grafo
     * @param edge 
     */
    public static void addEdge(String edge) {
        edge = edge.replaceFirst(sEdgeTag + "=", "");
        String[] sEdge = edge.split(",");
        
        try {
            graph.addEdge(sEdge[0], sEdge[1], Integer.valueOf(sEdge[2]));            
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que recebe uma string contendo o vértice lido do arquivo de entrada
     * do qual o algoritmo de prim será iniciado
     * @param vertex 
     */
    public static void runPrim(String vertex) {
        vertex = vertex.replaceFirst(sStartTag + "=", "");
        if(graph.connected())
            graph.prim(vertex);
        else
            System.err.println("O grafo não eh conexo!");
    }
    
    /** 
     * Método que faz a leitura do arquivo de entrada e popula o grafo
     * @throws IOException quando o arquivo não é encontrado 
     */
    public static void readFile() throws IOException {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("input"));
            try {
                String buffer = null;
                do {
                    buffer = in.readLine();                    
                    if(buffer != null) {
                        if(buffer.startsWith(sVerticesTag))
                            addVertices(buffer);
                        else if(buffer.startsWith(sEdgeTag))
                            addEdge(buffer);
                        else if(buffer.startsWith(sStartTag))
                            runPrim(buffer);
                    }                    
                } while(buffer != null);                
            } finally {
                in.close();
            }
         } catch (FileNotFoundException ex) {
             System.out.println("Arquivo não encontrado!");
        }
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch(IOException e) {
            System.err.println("Erro ao abrir arquivo!");
        }
    }
    
}
