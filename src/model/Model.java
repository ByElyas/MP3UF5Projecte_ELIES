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
import java.util.ArrayList;

/**
 *
 * @author eliesfatsini
 */
public class Model{

    

    
    public static final String table_header[] = {"Marca Vehicle", "Model Vehicle", "Any Vehicle", "Numero Vehicle"};
    
    public static final ArrayList<Vehicle> data = new ArrayList<Vehicle>(); 
    

    public Model() {
        data.add(new Vehicle("Nissan", "Skyline GTR R32", 1991, 22));  
        data.add(new Vehicle("Toyota", "Corolla Trueno AE86", 1986, 86)); 
        data.add(new Vehicle("Nissan", "Silvia S14", 1994, 27)); 
        data.add(new Vehicle("Mazda", "RX-7 FC", 1989, 6)); 
    }


    
    
    
    
    public void insertarVehicle(String marca, String model, int any, int numero) {

        data.add(new Vehicle(marca, model, any, numero));
        
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
