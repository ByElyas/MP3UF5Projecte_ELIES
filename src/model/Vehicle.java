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
public class Vehicle {
    
    private String marcaVehicle;
    private String modelVehicle;
    private int anyVehicle;
    private int unitatsVenudes;
    private String DATA[][];
    String TABLE_HEADER[];
    JTable jtaula;
    
    public String[][] getData(){
        return DATA;
    }
    
    public void setData(String[][] data) {
        this.DATA = data;
    }
    
    public String[] getColumn() {
        return TABLE_HEADER;
    }

    public void setColumn(String[] column) {
        this.TABLE_HEADER = column;
    }
    
    public JTable getJtaula() {
        return jtaula;
    }

    public void setJt(JTable jtaula) {
        this.jtaula = jtaula;
    }
    
    public void setMarcaVehicle() {
        this.marcaVehicle = marcaVehicle;
    }
    
    public String getMarcaVehicle() {
        return marcaVehicle;
    }
    
    public void setModelVehicle() {
        this.modelVehicle = modelVehicle;
    }
    
    public String getModelVehicle() {
        return modelVehicle;
    }
    
    public void setAnyVehicle() {
        this.anyVehicle = anyVehicle;
    }
    
    public int getAnyVehicle() {
        return anyVehicle;
    }
    
    public void setUnitatsVenudes() {
        this.unitatsVenudes = unitatsVenudes;
    }
    
    public int getUnitatsVenudes() {
        return unitatsVenudes;
    }

    public Vehicle(String marcaVehicle, String modelVehicle, int anyVehicle, int unitatsVenudes) {
        this.marcaVehicle = marcaVehicle;
        this.modelVehicle = modelVehicle;
        this.anyVehicle = anyVehicle;
        this.unitatsVenudes = unitatsVenudes;
    }
    

    
}
