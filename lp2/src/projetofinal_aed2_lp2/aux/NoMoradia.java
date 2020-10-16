/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal_aed2_lp2.aux;

import java.io.Serializable;
import projetofinal_aed2_lp2.Moradia;

/**
 *
 * @author patricia
 */
public class NoMoradia extends GraphNode implements Serializable{
    
    private Moradia m;

    public NoMoradia(Moradia m) {
        super();
        this.m = m;
    }

    public NoMoradia(int id, Moradia m) {
        super(id);
        this.m = m;
    }

    public Moradia getM() {
        return m;
    }
    
    
}
