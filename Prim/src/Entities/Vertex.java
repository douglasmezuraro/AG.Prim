package Entities;

import java.util.List;
import java.util.ArrayList;

public class Vertex {

    public int value;
    public int key;
    public Color color;
    public Vertex predecessor;
    public List<Vertex> adjacent;

    Vertex() {
        this(0);
    }   
    
    Vertex(int value) {
        this.value = 0;
        this.key = 0;
        this.color = Color.white;
        this.predecessor = null;
        this.adjacent = new ArrayList<>();
    }

}
