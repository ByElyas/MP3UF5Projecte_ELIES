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
import javax.swing.table.TableColumnModel;
import model.Vehicle;
import view.View;
import utilscontroller.Utils;

/**
 *
 * @author profe
 */
public class Controller implements ActionListener {
    
    private static Vehicle model;
    private static View view;
    
    public Controller(Vehicle m, View v){
        this.view = v;
        this.model = m;
        v.setVisible(true);
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
//        view.getjFieldText1().setSize(0, 0);
        view.getjFieldText1().setText("0                  ");
        view.getjFieldText2().setText("0                  ");
        view.getjFieldText3().setText("0                  ");
        view.getjFieldText4().setText("0                  ");
        
        
        
        //LABELS - FORMULARI
        view.getjLabel1().setText("Marca Vehicle");
        view.getjLabel2().setText("Model Vehicle");
        view.getjLabel3().setText("Any Vehicle");
        view.getjLabel4().setText("Unitat venudes");
        
        //BOTÓ SUBMIT - FORMULARI
        view.getjButton1().setText("Afegir registre");
        
        //TAULA
                
        Vehicle model = new Vehicle();
	view.getJTaula().setModel(model);
        
  
        
        
        
        
        
        
        //Codi que assigna els listeners als components de la vista

        //Coses

//        String column[] = {"Marca Vehicle", "Vehicle Vehicle", "Any Vehicle", "Unitats venudes"};
//        
//        String data[][]= {
//            {"a", "b", "c", "1"},
//            {null, null, null, null},
//            {null, null, null, null},
//            {null, null, null, null}
//        };
//        
//        
//        model.setColumn(column);
//        model.getColumn();
//        model.setData(data);
//        model.getData();
//        
//        JTable jtaula=new JTable(data,column);
//        
//        model.setJt(jtaula);
//        
//        view.getJTaula().addColumn(column);
//        view.getJTaula().add(data);
//        
//        jtaula = new javax.swing.JTable();
//
//        jtaula.setModel(new javax.swing.table.DefaultTableModel(
//        new Object [][] {
//            {null, null, null, null},
//            {null, null, null, null},
//            {null, null, null, null},
//            {null, null, null, null}
//        },
//        new String [] {
//            "Title 1", "Title 2", "Title 3", "Title 4"
//        }
//    ));




        
        
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
