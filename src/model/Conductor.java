/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;

/**
 *
 * @author eliesfatsini
 */
/**
 * Conductor
 */
public class Conductor implements Comparable<Conductor> {

    private String _4_nom_Conductor;
    private String _2_cognom_Conductor;
    private int _3_edat_Conductor;
    private int _1_id_Conductor;

    public String get4_nom_Conductor() {
        return _4_nom_Conductor;
    }

    public void set4_nom_Conductor(String _4_nomConductor) {
        this._4_nom_Conductor = _4_nomConductor;
    }

    public String get2_cognom_Conductor() {
        return _2_cognom_Conductor;
    }

    public void set2_cognom_Conductor(String _2_cognomConductor) {
        this._2_cognom_Conductor = _2_cognomConductor;
    }

    public int get3_edat_Conductor() {
        return _3_edat_Conductor;
    }

    public void set3_edat_Conductor(int _3_edatConductor) {
        this._3_edat_Conductor = _3_edatConductor;
    }

    public int get1_id_Conductor() {
        return _1_id_Conductor;
    }

    public void set1_id_Conductor(int _1_idConductor) {
        this._1_id_Conductor = _1_idConductor;
    }

    public Conductor(String _4_nomConductor, String _2_cognomConductor, int _3_edatConductor, int _1_idConductor) {
        this._4_nom_Conductor = _4_nomConductor;
        this._2_cognom_Conductor = _2_cognomConductor;
        this._3_edat_Conductor = _3_edatConductor;
        this._1_id_Conductor = _1_idConductor;
    }

    @Override
    public int compareTo(Conductor o) {
//        return this._1_id_Conductor - o._1_id_Conductor;
          return Comparator.comparing(Conductor::get1_id_Conductor).thenComparing(Conductor::get4_nom_Conductor)
                  .compare(this, o);
    }

}
