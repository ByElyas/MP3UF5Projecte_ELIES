/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 *
 * @author eliesfatsini
 */
public class Model{


//    public static final String table_header[] = {"Marca Vehicle", "Model Vehicle", "Any Vehicle", "Numero Vehicle"};
    
//    public static final ArrayList<Vehicle> data = new ArrayList<Vehicle>(); 
    

    public Model() {
        data.add(new Vehicle("Mazda", "RX-7 FC", 1989, 6)); 
        data.add(new Vehicle("Nissan", "Skyline GTR R32", 1991, 22));  
        data.add(new Vehicle("Toyota", "Corolla Trueno AE86", 1986, 86)); 
        data.add(new Vehicle("Nissan", "Silvia S15", 1998, 66)); 
        data.add(new Vehicle("Audi", "Quattro Sport", 1988, 15)); 
        data.add(new Vehicle("BMW", "M3 GTR", 2005, 19)); 
    }



    
    private Collection<Vehicle> data = new TreeSet<>();
    private Collection<Vehicle> dataOrd = new TreeSet<>(new VehicleOrdenatMarca());
    
    public Collection<Vehicle> getData() {
        return data;
    }
    public Collection<Vehicle> getDataOrd() {
        return dataOrd;
    }
    
    
    public void insertarVehicle(String marca, String model, int any, int numero) {
        data.add(new Vehicle(marca, model, any, numero));        
    }

    public void eliminarVehicle(Vehicle algo) {
        data.remove(algo);
    }
      
    
}

class VehicleOrdenatMarca implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return o1.get4_marca_Vehicle().compareTo(o2.get4_marca_Vehicle());
    }
    
}







