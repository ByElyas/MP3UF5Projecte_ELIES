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
        model=m;
        view=v;
        controlador();
    }
    
    private void controlador(){
        
        //Codi que inicilitza la vista
        view.setVisible(true);
        
        
        //Codi que assigna els listeners als components de la vista
        String column[] = {"Marca Vehicle", "Model Vehicle", "Any Vehicle", "Unitats venudes"};
        
        String data[][]= {
            {"a", "b", "c", "1"},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        };
        
        model.setColumn(column);
        model.setData(data);
        
        JTable jtaula=new JTable(data,column);
        
        model.setJt(jtaula);
        
        
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
