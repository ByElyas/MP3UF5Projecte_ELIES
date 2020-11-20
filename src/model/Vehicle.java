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
    
    private String _1_marca_Vehicle;
    private String _2_model_Vehicle;
    private int _3_any_Vehicle;

    public String get1_marca_Vehicle() {
        return _1_marca_Vehicle;
    }

    public void set1_marca_Vehicle(String _1_marcaVehicle) {
        this._1_marca_Vehicle = _1_marca_Vehicle;
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

    public int get4_numero_Vehicle() {
        return _4_numero_Vehicle;
    }

    public void set4_numero_Vehicle(int _4_numeroVehicle) {
        this._4_numero_Vehicle = _4_numeroVehicle;
    }
    private int _4_numero_Vehicle;

    public Vehicle(String _1_marcaVehicle, String _2_modelVehicle, int _3_anyVehicle, int _4_numeroVehicle) {
        this._1_marca_Vehicle = _1_marcaVehicle;
        this._2_model_Vehicle = _2_modelVehicle;
        this._3_any_Vehicle = _3_anyVehicle;
        this._4_numero_Vehicle = _4_numeroVehicle;
    }


    JTable jtaula;
    
    
    @Override
    public int compareTo(Vehicle o) {
        return this._4_numero_Vehicle-o._4_numero_Vehicle;
    }
    
    
////    private String DATA[][];
////    String TABLE_HEADER[];
//    public void setData(String[][] data) {
////        this.DATA = data;
////    }
////    
////    public String[][] getData(){
////        return DATA;
////    }
////    
////
////    
////    public String[] getColumn() {
////        return TABLE_HEADER;
////    }
////
////    public void setColumn(String[] column) {
////        this.TABLE_HEADER = column;
////    }
    
//    public JTable getJtaula() {
//        return jtaula;
//    }
//
//    public void setJt(JTable jtaula) {
//        this.jtaula = jtaula;
//    }
//    
//    public void setMarcaVehicle() {
//        this.marcaVehicle = marcaVehicle;
//    }
//    
//    public String getMarcaVehicle() {
//        return marcaVehicle;
//    }
//    
//    public void setModelVehicle() {
//        this.modelVehicle = modelVehicle;
//    }
//    
//    public String getModelVehicle() {
//        return modelVehicle;
//    }
//    
//    public void setAnyVehicle() {
//        this.anyVehicle = anyVehicle;
//    }
//    
//    public int getAnyVehicle() {
//        return anyVehicle;
//    }
//    
//    public void setNumeroVehicle() {
//        this.numeroVehicle = numeroVehicle;
//    }
//    
//    public int getNumeroVehicle() {
//        return numeroVehicle;
//    }
//
//    public Vehicle(String marcaVehicle, String modelVehicle, int anyVehicle, int numeroVehicle) {
//        this.marcaVehicle = marcaVehicle;
//        this.modelVehicle = modelVehicle;
//        this.anyVehicle = anyVehicle;
//        this.numeroVehicle = numeroVehicle;
//    }


    

    
}
