/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Vehicle;
import view.View;

/**
 *
 * @author eliesfatsini
 */
public class Model {

    private final Vehicle model;
    private static View view;
    
    public static final String[] TABLE_HEADER = {"Marca Vehicle", "Model Vehicle", "Any Vehicle", "Unitats venudes"};
    
    public static final String[][] DATA = {
        {"Nissan", "Skyline GTR R32", "1991", "3"},
        {"Honda", "Civic", "1993", "34"},
        {"Toyota", "Corolla AE86 Trueno", "1986", "0"},
        {"Toyota", "Crown", "1994", "56"},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
    };
    
  
    public Model(Vehicle m, View v) {

        this.model = m;
        this.view = v;
        v.setVisible(true);
        model();
        
        
    }
    
    
    
    private void model() {
        view.setVisible(true);
        
        view.getjButton1().setText("Hola que tal");
        
        view.getjButton1().addActionListener(
                e -> {
                    
                    view.getjFieldText1().setText("hola");

                }
        );
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
  
  

}
