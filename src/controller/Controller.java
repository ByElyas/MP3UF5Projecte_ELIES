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
import model.Model;
import view.View;

/**
 *
 * @author profe
 */
public class Controller {
    
    private static Model model;
    private static View view;
    
    public Controller(Model m, View v){
        this.view = v;
        this.model = m;
        v.setVisible(true);
        controlador();
    }
    
    private void controlador(){
        
        //Codi que inicilitza la vista
        view.setVisible(true);
        

        String nomColumnes[] = {"Marca Vehicle", "Model Vehicle", "Any Vehicle", "Unitats venudes"};
        
        String data[][]= {
            {"a", "b", "c", "1"},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        };
        
        JTable jtaula = new JTable(data, nomColumnes);
        //CAIXES DE TEXT - FORMULARI
        view.getjFieldText1().setText("                  ");
        view.getjFieldText2().setText("                  ");
        view.getjFieldText3().setText("                  ");
        view.getjFieldText4().setText("                  ");
        
        
        //LABELS - FORMULARI
        view.getjLabel1().setText("Marca Vehicle");
        view.getjLabel2().setText("Model Vehicle");
        view.getjLabel3().setText("Any Vehicle");
        view.getjLabel4().setText("Unitat venudes");
        
        //BOTÓ SUBMIT - FORMULARI
        view.getjButton1().setText("Afegir registre");
        
        //TAULA
        
//        view.getJTaula().setTableHeader(nomColumnes);
//        view.getJTaula().set
        
        
        
        
        
  
        
        
        
        
        
        
        //Codi que assigna els listeners als components de la vista

        //Coses

//        String column[] = {"Marca Vehicle", "Model Vehicle", "Any Vehicle", "Unitats venudes"};
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
