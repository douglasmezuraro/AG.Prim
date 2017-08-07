package Entities;

import java.util.List;
import java.util.ArrayList;

public class Vertex {

    public String name;
    public int key;
    public Color color;
    public Vertex predecessor;
    public List<Vertex> adjacent;
    
    Vertex(String name) {
        this.name = name;
        this.key = 0;
        this.color = Color.white;
        this.predecessor = null;
        this.adjacent = new ArrayList<>();
    }

    public int degree() {
        int degree = 0;
        
        for(Vertex u: this.adjacent)
            degree++;
        
        return degree;
    }
    
}
