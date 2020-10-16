/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal_aed2_lp2.aux;

import java.io.Serializable;

/**
 *
 * @author convidado
 */
public abstract class GraphNode implements Serializable{

    private final int id;
    private static int ACTUAL_ID = 0;

    public GraphNode() {
        this.id = ACTUAL_ID++;
    }

    public GraphNode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GraphNode other = (GraphNode) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "GraphNode{" + "id=" + id + '}';
    }

}
