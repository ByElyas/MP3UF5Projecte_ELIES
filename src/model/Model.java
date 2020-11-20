/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author eliesfatsini
 */
public class Model{


//    public static final String table_header[] = {"Marca Vehicle", "Model Vehicle", "Any Vehicle", "Numero Vehicle"};
    
//    public static final ArrayList<Vehicle> data = new ArrayList<Vehicle>(); 
    

    public Model() {
        data.add(new Vehicle("Nissan", "Skyline GTR R32", 1991, 22));  
        data.add(new Vehicle("Toyota", "Corolla Trueno AE86", 1986, 86)); 
        data.add(new Vehicle("Nissan", "Silvia S14", 1994, 27)); 
        data.add(new Vehicle("Mazda", "RX-7 FC", 1989, 6)); 
    }


//    public ArrayList<Vehicle> getDades() {
//        return data;
//    }
    
    private Collection<Vehicle> data = new ArrayList<>();
    
    public Collection<Vehicle> getData() {
        return data;
    }
    
    
    public void insertarVehicle(String marca, String model, int any, int numero) {
        data.add(new Vehicle(marca, model, any, numero));        
    }

    public void eliminarVehicle(Vehicle algo) {
        data.remove(algo);
    }
      
    
}
