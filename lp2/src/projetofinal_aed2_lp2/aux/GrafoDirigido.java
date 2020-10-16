/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal_aed2_lp2.aux;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import projetofinal_aed2_lp2.RedeEletrica;

/**
 *
 * @author patricia
 */
public class GrafoDirigido extends FlowNetwork implements Serializable{
    private final ArrayList<NoMoradia> nodesM = new ArrayList<>();

    private final ArrayList<NoFonteEnergia> nodesF = new ArrayList<>();

    public GrafoDirigido(int V) {
        super(V);
    }

    public GrafoDirigido(int V, int E) {
        super(V, E);
    }

    public GrafoDirigido(In in) {
        super(in);
    }
    
    
    /**
     * Adiciona um NoMoradia ao grafo.
     * @param nodeM 
     * @throws IllegalArgumentException()
     */
    public void addNode(NoMoradia nodeM) {
        if (this.nodesM.size() >= this.V()) {
            throw new IllegalArgumentException();
        }
        this.nodesM.add(nodeM);
    }
    /**
     * Adiciona um NoFonteEnergia ao grafo.
     * @param nodeF 
     * @throws IllegalArgumentException()
     */
    public void addNode(NoFonteEnergia nodeF) {
        if (this.nodesF.size() >= this.V()) {
            throw new IllegalArgumentException();
        }
        this.nodesF.add(nodeF);
    }
    /**
     * Adiciona uma aresta.
     * @param from
     * @param to
     * @param cost
     * @param r 
     * @throws IllegalArgumentException()
     */
    public void addEdge(GraphNode from, GraphNode to, int cost,RedeEletrica r) {
        if (!(this.nodesF.contains(from) || this.nodesM.contains(to) || this.nodesM.contains(from))) {
            throw new IllegalArgumentException();
        }
        ArestaRedeEletrica cl = new ArestaRedeEletrica(from.getId(), to.getId(), cost,r);
        this.addEdge(cl);
    }
    /**
     * Adiciona uma aresta sem uma rede eletrica.
     * @param from
     * @param to
     * @param cost
     * @throws IllegalArgumentException()
     */
    public void addEdge(GraphNode from, GraphNode to, double cost) {
        if (!(this.nodesF.contains(from) || this.nodesM.contains(to) || this.nodesM.contains(from))) {
            throw new IllegalArgumentException();
        }
        FlowEdge cl = new FlowEdge(from.getId(), to.getId(), cost);
        this.addEdge(cl);
    }

    public ArrayList<NoMoradia> getNodesM() {
        return nodesM;
    }

    public ArrayList<NoFonteEnergia> getNodesF() {
        return nodesF;
    }
    /**
     * verifica se o nó pertence ao grafo
     * @param id
     * @return 
     */
    public boolean pertenceGrafo(int id){
        for(NoMoradia m : this.nodesM){
            if(m.getId()==id)
                return true;
        }
        for(NoFonteEnergia f : this.nodesF){
            if(f.getId()==id)
                return true;
        }
        return false;
    }
    
}
