/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal_aed2_lp2.aux;

import java.io.Serializable;
import projetofinal_aed2_lp2.FonteEnergiaG;

/**
 *
 * @author patricia
 */
public class NoFonteEnergia extends GraphNode implements Serializable{
    
    private FonteEnergiaG fonte;

    public NoFonteEnergia(FonteEnergiaG f) {
        super();
        this.fonte = f;
    }

    public NoFonteEnergia(int id,FonteEnergiaG f) {
        super(id);
        this.fonte = f;
    }

    public FonteEnergiaG getFonte() {
        return fonte;
    }
    
    
}
