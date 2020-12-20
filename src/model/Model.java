/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;
import model.Vehicle;
import model.Conductor;

/**
 *
 * @author eliesfatsini
 */
public class Model {

//    public static final String table_header[] = {"Marca Vehicle", "Model Vehicle", "Any Vehicle", "Numero Vehicle"};
//    public static final ArrayList<Vehicle> data = new ArrayList<Vehicle>(); 
    public Model() {
        //MOGUT AL CONTROLADOR DIRECTE
        //Afegir vehicles
//        data.add(new Vehicle("Mazda", "RX-7 FC", 1989, 6));
//        data.add(new Vehicle("Nissan", "Skyline GTR R32", 1991, 22));
//        data.add(new Vehicle("Toyota", "Corolla Trueno AE86", 1986, 86));
//        data.add(new Vehicle("Nissan", "Silvia S15", 1998, 66));
//        data.add(new Vehicle("Audi", "Quattro Sport", 1988, 15));
//        data.add(new Vehicle("BMW", "M3 GTR", 2005, 19));

//        Afegir conductors
//        dataConductor.add(new Conductor("Pepe", "Viyuela", 45, 6589));
//        dataConductor.add(new Conductor("Paul", "Walker", 47, 2254));
//        dataConductor.add(new Conductor("Ian", "Lewis", 24, 222));
//        dataConductor.add(new Conductor("Alex", "Cañizares", 21, 1574));
//        dataConductor.add(new Conductor("Frank", "Williams", 53, 1));
    }

    //Vehciles
    private Collection<Vehicle> data = new TreeSet<>();
    private Collection<Vehicle> dataOrd = new TreeSet<>(new VehicleOrdenatMarca());

    public Collection<Vehicle> getData() {
        return data;
    }

    public Collection<Vehicle> getDataOrd() {
        return dataOrd;
    }

    //metode generic per a insertar dades --- tambè serveix per a conductor
    public static <T> void insertar(T a, Collection<T> col) {
        col.add(a);
    }

    public void insertarVehicle(String marca, String model, int any, int numero, String[] sponsors) {
        Vehicle ve = new Vehicle(marca, model, any, numero, sponsors);
        Model.insertar(ve, data);
        Model.insertar(ve, dataOrd);
    }

    //metode generic per a eliminar dades 
    public static <T> void eliminar(T a, Collection<T> col) {
        col.remove(a);
    }

    public void eliminarVehicle(Vehicle algo) {
        Model.eliminar(algo, data);
        Model.eliminar(algo, dataOrd);
    }

//    public void actualitzarVehicle(String marca, String model, int any, int numero) {
//
//    }
    class VehicleOrdenatMarca implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
//            return o1.get4_marca_Vehicle().compareTo(o2.get4_marca_Vehicle());
            int p;
            p = o1.get4_marca_Vehicle().compareTo(o2.get4_marca_Vehicle());
            if (p != 0) {
                return p;
            }
            return o1.get2_model_Vehicle().compareTo(o2.get2_model_Vehicle());
        }
    }

    //Conductor
    private Collection<Conductor> dataConductor = new TreeSet<>();
    private Collection<Conductor> dataOrdConductor = new TreeSet<>(new ConductorOrdenatNom());

    public Collection<Conductor> getDataConductor() {
        return dataConductor;
    }

    public Collection<Conductor> getDataOrdConductor() {
        return dataOrdConductor;
    }

    public void insertarConductor(String nom, String cognom, int edat, int id, Vehicle vehicle_Conductor) {
        Conductor co = new Conductor(nom, cognom, edat, id, vehicle_Conductor);
        Model.insertar(co, dataConductor);
        Model.insertar(co, dataOrdConductor);
    }

    public void eliminarConductor(Conductor algo) {
        Model.eliminar(algo, dataConductor);
        Model.eliminar(algo, dataOrdConductor);
    }

    class ConductorOrdenatNom implements Comparator<Conductor> {

        @Override
        public int compare(Conductor o1, Conductor o2) {
            // return o1.get4_nom_Conductor().compareTo(o2.get4_nom_Conductor());
            int p;
            p = o1.get4_nom_Conductor().compareTo(o2.get4_nom_Conductor());
            if (p != 0) {
                return p;
            }
            return o1.get2_cognom_Conductor().compareTo(o2.get2_cognom_Conductor());
        }

    }

//    public void saveVehicle(String fileName) throws FileNotFoundException {
//        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
//        for (Vehicle v : data) {
//            pw.println(v.get1_numero_Vehicle() + "," + v.get2_model_Vehicle() + "," +
//                    v.get3_any_Vehicle() + "," + v.get4_marca_Vehicle() + "," +
//                     "[" + v.get5_sponsors_Vehicle() + "]" + "," + v.get6_cond());
////              pw.println(data);
//        }
//        pw.close();
//    }
//    public void saveVehicle(String filename, Vehicle v) throws FileNotFoundException, IOException {
//        ObjectOutputStream out = null;
//        
//        try {
//            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
//            for (int i = 0; i <= data.size(); i++) {
//                out.writeObject(v);
//            }
//        } finally {
//            if(out !=null) out.close();
//        }
//    }
    
    // GUARDAR LES DADES DELS VEHICLES A UN FITXER
    public void saveVehicle(String filename) throws FileNotFoundException, IOException {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
                out.writeObject(data);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
        // GUARDAR LES DADES DELS CONDUCTORS A UN FITXER
    public void saveConductor(String filename) throws FileNotFoundException, IOException {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
                out.writeObject(dataConductor);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    //CARREGAR LES DADES DELS VEHICLES QUE ESTAN GUARDATS A UN FITXER
    public void loadVehicle(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));        
        try {
            data.addAll((TreeSet<Vehicle>) in.readObject());
        } finally {
            if(in != null) in.close();
        }
    }
    
        //CARREGAR LES DADES DELS CONDUCTORS QUE ESTAN GUARDATS A UN FITXER
    public void loadConductor(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));        
        try {
            dataConductor.addAll((TreeSet<Conductor>) in.readObject());
        } finally {
            if(in != null) in.close();
        }
    }
    
    public void deleteArxiu(String fileName) throws FileNotFoundException {
        File arx = new File(fileName);
        if (arx.delete()) {
            System.out.println("L'arxiu " + arx.getName() + " s'ha borrat correctament, preparat per a tornar a crear noves dades!");
        } else {
            System.out.println("No s'ha pogut borrar l'arxiu!");
        }
    }
    
    //Carregar password a un fitxer amb posició oculta
//    public void carregarPassword() throws FileNotFoundException, IOException {
//        Random rn=new Random();
//        long offset=rn.nextInt();
//        int secret=rn.nextInt(701554);
//        
//        File f = new File("secret.dat");
//        
//        try (RandomAccessFile fitxer = new RandomAccessFile(f,"rw")) {
//            fitxer.seek(0);
//            fitxer.writeLong(offset);
//            fitxer.seek(offset);
//            fitxer.writeInt(secret);
//            System.out.println("Password carregada correctament");
//        }catch(Exception e) {
//            System.out.println("Algo dolent ha passat!");
//        }
//    }
}
