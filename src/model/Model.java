/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Constants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author profe
 */
public class Model extends DefaultTableModel {
    
    private String marcaVehicle;
    private String modelVehicle;
    private String anyVehicle;
    private int unitatsVenudes;
    private String data[][];
    String column[];
    JTable jtaula;
    
    public String[][] getData(){
        return data;
    }
    
    public void setData(String[][] data) {
        this.data = data;
    }
    
    public String[] getColumn() {
        return column;
    }

    public void setColumn(String[] column) {
        this.column = column;
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
    
    public String getAnyVehicle() {
        return anyVehicle;
    }
    
    public void setUnitatsVenudes() {
        this.unitatsVenudes = unitatsVenudes;
    }
    
    public int getUnitatsVenudes() {
        return unitatsVenudes;
    }
    
    public Model() {
		super(Constants.DATA, Constants.TABLE_HEADER);
    }
    
}
