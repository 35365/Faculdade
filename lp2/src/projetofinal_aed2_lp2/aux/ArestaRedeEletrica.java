/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal_aed2_lp2.aux;

import edu.princeton.cs.algs4.FlowEdge;
import java.io.Serializable;
import projetofinal_aed2_lp2.RedeEletrica;

/**
 *
 * @author patricia
 */
public class ArestaRedeEletrica extends FlowEdge implements Serializable{
    
    private RedeEletrica rede;

    /*public ArestaRedeEletrica(int v, int w, double weight,RedeEletrica r) {
        super(v, w, weight);
        this.rede =r;
    }
    
     public ArestaRedeEletrica(GraphNode from, GraphNode to, int cost,RedeEletrica r) {
        super(from.getId(), to.getId(), cost);
        this.rede = r;
    }*/

    public ArestaRedeEletrica(int v, int w, double capacity, RedeEletrica r) {
        super(v, w, capacity);
        this.rede =r;
    }

    public ArestaRedeEletrica(int v, int w, double capacity, double flow, RedeEletrica r) {
        super(v, w, capacity, flow);
        this.rede =r;
    }

    public ArestaRedeEletrica(FlowEdge e) {
        super(e);
    }
     
    
}
