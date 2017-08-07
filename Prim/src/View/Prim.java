package View;

import Entities.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prim {

    public static final Graph graph = new Graph();

    public static int getInt(String question) {
        System.out.println(question);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Boolean success = false;
        int result = 0;
        
        while(!success) {
            try{
                result = Integer.parseInt(in.readLine());
                success = true;
            }catch(Exception e){
                System.err.println("Erro ao ler dado do usuario, digite novamente! Detalhe do erro: " + e.getMessage());
            }
        }

        return result;
    }

    public static String getString(String question) {
        System.out.println(question);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Boolean success = false;
        String result = null;
        
        while(!success) {
            try{
                result = in.readLine();
                success = true;
            }catch(Exception e){
                System.err.println("Erro ao ler dado do usuario, digite novamente! Detalhe do erro: " + e.getMessage());
            }
        }

        return result;
    }

    public static void addVertices() {
        int nVertices = getInt("Quantos vertices deseja adicionar ao grafo?");
        for(int i = 0; i < nVertices; i++) {
            try {
                graph.addVertex(getString("nome do vertice?"));
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void addEdges() {
        int aux = getInt("\nDeseja adicionar alguma aresta? (1 = Sim / 0 = Nao)");
        while(aux == 1) {
            try {
                graph.addEdge(getString("Digite a origem:"), 
                              getString("Digite o destino:"), 
                              getInt("Digite o peso:"));
                aux = getInt("Deseja adicionar alguma aresta?");
            } catch (Exception e) {
                System.err.println(e.getMessage());    
            }
        }
    }

    public static void foo() {
        String Vertices = "a,b,c,e,f,g,h,i";
        for(String vertex: Vertices.split(",")) {
            try {
                graph.addVertex(vertex);
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }            
        }
    }

    public static void doo() {
        String edges = "a;b;1";
        String[] edge = edges.split(";");
        
        Vertex source = graph.getVertex(edge[0]);
        Vertex target = graph.getVertex(edge[1]);
        int weight = Integer.valueOf(edge[2]);

        Edge e = new Edge(source, target, weight);
    }


    public static void main(String[] args) {
        //addVertices();
        //addEdges();
        foo();
        doo();

        if(graph.connected())
            graph.prim(getString("Digite o vertice que deseja iniciar o prim:"));
        else
            System.out.println("O grafo não eh conexo!");
    }
    
}
