/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author profe
 */
public class Vehicle implements Comparable<Vehicle> {
    
    private String _4_marca_Vehicle;
    private String _2_model_Vehicle;
    private int _3_any_Vehicle;
    private int _1_numero_Vehicle;

    public String get4_marca_Vehicle() {
        return _4_marca_Vehicle;
    }

    public void set4_marca_Vehicle(String _4_marcaVehicle) {
        this._4_marca_Vehicle = _4_marcaVehicle;
    }

    public String get2_model_Vehicle() {
        return _2_model_Vehicle;
    }

    public void set2_model_Vehicle(String _2_modelVehicle) {
        this._2_model_Vehicle = _2_model_Vehicle;
    }

    public int get3_any_Vehicle() {
        return _3_any_Vehicle;
    }

    public void set3_any_Vehicle(int _3_anyVehicle) {
        this._3_any_Vehicle = _3_anyVehicle;
    }

    public int get1_numero_Vehicle() {
        return _1_numero_Vehicle;
    }

    public void set1_numero_Vehicle(int _1_numeroVehicle) {
        this._1_numero_Vehicle = _1_numeroVehicle;
    }


    public Vehicle(String _4_marcaVehicle, String _2_modelVehicle, int _3_anyVehicle, int _1_numeroVehicle) {
        this._4_marca_Vehicle = _4_marcaVehicle;
        this._2_model_Vehicle = _2_modelVehicle;
        this._3_any_Vehicle = _3_anyVehicle;
        this._1_numero_Vehicle = _1_numeroVehicle;
    }


    

    @Override
    public int compareTo(Vehicle o) {
        return this._1_numero_Vehicle-o._1_numero_Vehicle;
    }
        

    
}
