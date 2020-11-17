/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.Model;
import model.Vehicle;
import view.View;
import utilscontroller.Utils;

/**
 *
 * @author profe
 */
public class Controller implements ActionListener {
    
    private static Model model;
    private static View view;
    
    public Controller(Model m, View v){
        view = v;
        model = m;
        
        controlador();        
    }
    
    private void controlador(){
        
        //Codi que inicilitza la vista
        view.setVisible(true);
        
        //FILTRE
        
        view.getjLabel5().setText("Filtrar per marca:");
        view.getjFieldText5().setText("Exemple               ");
        view.getjButton2().setText("Filtrar!");
        
        //CAIXES DE TEXT - FORMULARI
//        view.getAfegirMarcaText().setSize(0, 0);
        view.getAfegirMarcaText().setText("0                  ");
        view.getAfegirModelText().setText("0                  ");
        view.getAfegirAnyText().setText("0                  ");
        view.getAfegirNumeroText().setText("0                  ");
        
        
        
        //LABELS - FORMULARI
        view.getAfegirMarcaLabel().setText("Marca Vehicle");
        view.getAfegirModelLabel().setText("Model Vehicle");
        view.getAfegirAnyLabel().setText("Any Vehicle");
        view.getAfegirNumeroLabel().setText("Numero Vehicle");
        
        //BOTÓ SUBMIT - FORMULARI
//        view.getjButton1().setText("Afegir registre");
        
        //TAULA
        //Insertar dades per defecte localitzades a Model.java        
        DefaultTableModel modelTaula = new DefaultTableModel(Model.table_header,0);    
        for (int i = 0; i < Model.data.size(); i++) {
            String marca = Model.data.get(i).getMarcaVehicle();
            String model = Model.data.get(i).getModelVehicle();
            int any = Model.data.get(i).getAnyVehicle();
            int numero = Model.data.get(i).getNumeroVehicle();
            
            Object[] dades = {marca, model, any, numero};
            
            modelTaula.addRow(dades);
        }     
        view.getJTaula().setModel(modelTaula);      
    }

    
    
    
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    
    
    //Per implementar els ActionEvents dels components de la vista (útil per 
    //exemple, per controlar l'acció que s'executa quan fem clic a un botó tant 
    //usant el ratolí com si l'apretem en la barra del teclat  
    static class Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    //Per implementar els KeyEvents
    //També podem usar un KeyAdapter
    //static class Key extends KeyAdapter{}
    static class Key implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
    
    //Podem posar tots els listeners necessaris...
}
