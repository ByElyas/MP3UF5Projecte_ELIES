/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilscontroller;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author profe
 */
public class Utils {

    //Mètode que carrega els objectes continguts a l'ArrayList i els mostra a la JTable. La classe indica de quin tipo són els objectes de l'ArrayList (3r paràmetre)
    //Si volem que es puguen modificar les dades directament des de la taula hauríem d'usar el model instància de la classe ModelCanvisBD, que varia d'una BD a una altra
    //Al mètode usem el NonEditTableModel que no permet editar les cel·les de la JTable (el DefaultTableModel sí ho permet)
    //Esta versió afegix a la darrera columna de la taula l'objecte mostrat a la mateixa de manera que el podrem recuperar fàcilment per fer updates, deletes, etc...
    //Esta columna extra no apareix a la taula ja que la borrem, però la retornem per poder-la afegir quan sigue necessari
    public static<E> TableColumn loadTable(ArrayList<E> dades, JTable taula, Class<E> classe) {

        //variables locals
        Vector columnNames = new Vector();
        Vector data = new Vector();
        //Per poder actualitzar la BD des de la taula usaríem el model comentat
        //ModelCanvisBD model;
        DefaultTableModel model;

        //Anotem el nº de camps de la classe
        Field[] camps = classe.getDeclaredFields();
        //Ordenem els camps alfabèticament
        Arrays.sort(camps, new OrderClassFieldsAlphabetically());
        int ncamps = camps.length;
        //Recorrem els camps de la classe i posem els seus noms com a columnes de la taula
        //Com hem hagut de posar _numero_ davant el nom dels camps, mostrem el nom a partir del 2n _ 
        for (Field f : camps) {
            columnNames.addElement(f.getName().substring(f.getName().lastIndexOf('_') + 1).toUpperCase());
        }
        //Afegixo al model de la taula una columna on guardaré l'objecte mostrat a cada fila (amago la columna al final per a que no aparegue a la vista)
        columnNames.addElement("objecte");
        //Si hi ha algun element a l'arraylist omplim la taula
        if (dades.size() != 0) {

            //Guardem els descriptors de mètode que ens interessen (els getters), més una columna per guardar l'objecte sencer
            Vector<Method> methods = new Vector(ncamps + 1);
            try {

                PropertyDescriptor[] descriptors = Introspector.getBeanInfo(classe).getPropertyDescriptors();
                Arrays.sort(descriptors, new OrderClassMethodsAlphabetically());
                for (PropertyDescriptor pD : descriptors) {
                    Method m = pD.getReadMethod();
                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }

            } catch (IntrospectionException ex) {}
            for (E m : dades) {
                Vector row = new Vector(ncamps + 1);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {} 
                }

                //Aquí guardo l'objecte sencer a la darrera columna
                row.addElement(m);
                //Finalment afegixo la fila a les dades
                data.addElement(row);
            }
        }

        //Utilitzem el model que no permet editar les caselles de la taula
        model = new NotEditTableModel(data, columnNames);
        taula.setModel(model);

        //Borro la darrera columna per a que no aparegue a la vista, però abans la guardo en una variable que al final serà el que retorna el mètode
        TableColumnModel tcm = taula.getColumnModel();
        TableColumn columna = tcm.getColumn(tcm.getColumnCount() - 1);
        tcm.removeColumn(columna);

        //Fixo l'amplada de les columnes que sí es mostren
        TableColumn column;
        for (int i = 0; i < taula.getColumnCount(); i++) {
            column = taula.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }

        return columna;

    }

    
    //Com l'anterior en un booleà per dir si volem els noms de les columnes en
    //majúscula, i que obté el nom de la columna a partir del 2n _, no de l'últim
    //com passava anteriorment
    public static<E> TableColumn loadTable(ArrayList<E> dades, JTable taula, Class<E> classe, boolean majuscula, boolean segon) {

        //variables locals
        Vector columnNames = new Vector();
        Vector data = new Vector();
        //Per poder actualitzar la BD des de la taula usaríem el model comentat
        //ModelCanvisBD model;
        DefaultTableModel model;

        //Anotem el nº de camps de la classe
        Field[] camps = classe.getDeclaredFields();
        //Ordenem els camps alfabèticament
        Arrays.sort(camps, new OrderClassFieldsAlphabetically());
        int ncamps = camps.length;
        //Recorrem els camps de la classe i posem els seus noms com a columnes de la taula
        //Com hem hagut de posar _numero_ davant el nom dels camps, mostrem el nom a partir del 2n _ 
        for (Field f : camps) {
            String nom=f.getName().substring(1);    //ignorem el primer _
            if(majuscula) nom=nom.toUpperCase();
            if(segon)
                nom=nom.substring(nom.indexOf('_')+1);
            else
                nom=nom.substring(nom.lastIndexOf('_')+1);
            columnNames.addElement(nom);
        }
        //Afegixo al model de la taula una columna on guardaré l'objecte mostrat a cada fila (amago la columna al final per a que no aparegue a la vista)
        columnNames.addElement("objecte");
        //Si hi ha algun element a l'arraylist omplim la taula
        if (dades.size() != 0) {

            //Guardem els descriptors de mètode que ens interessen (els getters), més una columna per guardar l'objecte sencer
            Vector<Method> methods = new Vector(ncamps + 1);
            try {

                PropertyDescriptor[] descriptors = Introspector.getBeanInfo(classe).getPropertyDescriptors();
                Arrays.sort(descriptors, new OrderClassMethodsAlphabetically());
                for (PropertyDescriptor pD : descriptors) {
                    Method m = pD.getReadMethod();
                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }

            } catch (IntrospectionException ex) {}
            for (E m : dades) {
                Vector row = new Vector(ncamps + 1);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {} 
                }

                //Aquí guardo l'objecte sencer a la darrera columna
                row.addElement(m);
                //Finalment afegixo la fila a les dades
                data.addElement(row);
            }
        }

        //Utilitzem el model que no permet editar les caselles de la taula
        model = new NotEditTableModel(data, columnNames);
        taula.setModel(model);

        //Borro la darrera columna per a que no aparegue a la vista, però abans la guardo en una variable que al final serà el que retorna el mètode
        TableColumnModel tcm = taula.getColumnModel();
        TableColumn columna = tcm.getColumn(tcm.getColumnCount() - 1);
        tcm.removeColumn(columna);

        //Fixo l'amplada de les columnes que sí es mostren
        TableColumn column;
        for (int i = 0; i < taula.getColumnCount(); i++) {
            column = taula.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }

        return columna;

    }

    
    
    
    //Com l'anterior però podem dir si volem taula editable o no
    public static<E> TableColumn loadTable(ArrayList<E> dades, JTable taula, Class<E> classe, boolean editable) {

        //variables locals
        Vector columnNames = new Vector();
        Vector data = new Vector();
        //Per poder actualitzar la BD des de la taula usaríem el model comentat
        //ModelCanvisBD model;
        DefaultTableModel model;

        //Anotem el nº de camps de la classe
        Field[] camps = classe.getDeclaredFields();
        //Ordenem els camps alfabèticament
        Arrays.sort(camps, new OrderClassFieldsAlphabetically());
        int ncamps = camps.length;
        //Recorrem els camps de la classe i posem els seus noms com a columnes de la taula
        //Com hem hagut de posar _numero_ davant el nom dels camps, mostrem el nom a partir del 2n _ 
        for (Field f : camps) {
            columnNames.addElement(f.getName().substring(f.getName().lastIndexOf('_') + 1).toUpperCase());
        }
        //Afegixo al model de la taula una columna on guardaré l'objecte mostrat a cada fila (amago la columna al final per a que no aparegue a la vista)
        columnNames.addElement("objecte");
        //Si hi ha algun element a l'arraylist omplim la taula
        if (dades.size() != 0) {

            //Guardem els descriptors de mètode que ens interessen (els getters), més una columna per guardar l'objecte sencer
            Vector<Method> methods = new Vector(ncamps + 1);
            try {

                PropertyDescriptor[] descriptors = Introspector.getBeanInfo(classe).getPropertyDescriptors();
                Arrays.sort(descriptors, new OrderClassMethodsAlphabetically());
                for (PropertyDescriptor pD : descriptors) {
                    Method m = pD.getReadMethod();
                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }

            } catch (IntrospectionException ex) {}
            for (E m : dades) {
                Vector row = new Vector(ncamps + 1);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {} 
                }

                //Aquí guardo l'objecte sencer a la darrera columna
                row.addElement(m);
                //Finalment afegixo la fila a les dades
                data.addElement(row);
            }
        }

        //Utilitzem el model que permet o no editar les caselles de la taula 
        //segons el valor del paràmetre editable        
        if (editable) {
            model = new DefaultTableModel(data, columnNames);
        } else {
            model = new NotEditTableModel(data, columnNames);
        }

        taula.setModel(model);

        //Borro la darrera columna per a que no aparegue a la vista, però abans la guardo en una variable que al final serà el que retorna el mètode
        TableColumnModel tcm = taula.getColumnModel();
        TableColumn columna = tcm.getColumn(tcm.getColumnCount() - 1);
        tcm.removeColumn(columna);

        //Fixo l'amplada de les columnes que sí es mostren
        TableColumn column;
        for (int i = 0; i < taula.getColumnCount(); i++) {
            column = taula.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }

        return columna;

    }

    //Com l'anterior però per treballar en BDs usant un ResultSet. Si la fem 
    //editable podrem actualitzar les dades de la BD directament des de la JTable
    public static<E> TableColumn loadTable(ArrayList<E> dades, JTable taula, Class<E> classe, boolean editable, java.sql.ResultSet resultSet) {

        //variables locals
        Vector columnNames = new Vector();
        Vector data = new Vector();
        //Per poder actualitzar la BD des de la taula usaríem el model comentat
        //ModelCanvisBD model;
        DefaultTableModel model;

        //Anotem el nº de camps de la classe
        Field[] camps = classe.getDeclaredFields();
        //Ordenem els camps alfabèticament
        Arrays.sort(camps, new OrderClassFieldsAlphabetically());
        int ncamps = camps.length;
        //Recorrem els camps de la classe i posem els seus noms com a columnes de la taula
        //Com hem hagut de posar _numero_ davant el nom dels camps, mostrem el nom a partir del 2n _ 
        for (Field f : camps) {
            columnNames.addElement(f.getName().substring(f.getName().lastIndexOf('_') + 1).toUpperCase());
        }
        //Afegixo al model de la taula una columna on guardaré l'objecte mostrat a cada fila (amago la columna al final per a que no aparegue a la vista)
        columnNames.addElement("objecte");
        //Si hi ha algun element a l'arraylist omplim la taula
        if (dades.size() != 0) {

            //Guardem els descriptors de mètode que ens interessen (els getters), més una columna per guardar l'objecte sencer
            Vector<Method> methods = new Vector(ncamps + 1);
            try {

                PropertyDescriptor[] descriptors = Introspector.getBeanInfo(classe).getPropertyDescriptors();
                Arrays.sort(descriptors, new OrderClassMethodsAlphabetically());
                for (PropertyDescriptor pD : descriptors) {
                    Method m = pD.getReadMethod();
                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }

            } catch (IntrospectionException ex) {}
            for (E m : dades) {
                Vector row = new Vector(ncamps + 1);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {} 
                }

                //Aquí guardo l'objecte sencer a la darrera columna
                row.addElement(m);
                //Finalment afegixo la fila a les dades
                data.addElement(row);
            }
        }

        //Utilitzem el model que permet o no editar les caselles de la taula 
        //segons el valor del paràmetre editable        
        if (editable) {
            //Utilitzem el model que permet actualitzar la BD des de la taula
            model = new ModelCanvisBD(data, columnNames, resultSet);
            //model = new DefaultTableModel(data, columnNames);
        } else {
            model = new NotEditTableModel(data, columnNames);
        }

        taula.setModel(model);

        //Borro la darrera columna per a que no aparegue a la vista, però abans la guardo en una variable que al final serà el que retorna el mètode
        TableColumnModel tcm = taula.getColumnModel();
        TableColumn columna = tcm.getColumn(tcm.getColumnCount() - 1);
        tcm.removeColumn(columna);

        //Fixo l'amplada de les columnes que sí es mostren
        TableColumn column;
        for (int i = 0; i < taula.getColumnCount(); i++) {
            column = taula.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }

        return columna;

    }


    //Fa el mateix que loadTable() però no retorna la columna en els objectes
    //Les cel·les de la JTable no es podran editar
    public static <E> void loadTableNoReturn(ArrayList<E> resultSet, JTable taula, Class<E> classe) {
        // TODO add your handling code here:
        //Quan tornem a carregar la taula perdem la selecció que haviem fet i posem filasel a -1

        Vector columnNames = new Vector();
        Vector data = new Vector();
        DefaultTableModel model;

        //Anotem el nº de camps de la classe
        Field[] camps = classe.getDeclaredFields();

        //Ordenem els camps alfabèticament
        Arrays.sort(camps, new OrderClassFieldsAlphabetically());
        int ncamps = camps.length;

        //Recorrem els camps de la classe i posem els seus noms com a columnes de la taula
        //Com hem hagut de posar numero davant el nom dels camps, mostrem el nom a partir del 2n _ 
        for (Field f : camps) {
            columnNames.addElement(f.getName().substring(f.getName().lastIndexOf('_') + 1).toUpperCase());
        }

        //Si hi ha algun element a l'arraylist omplim la taula
        if (resultSet.size() != 0) {    

            //Guardem els descriptors de mètode que ens interessen (els getters)
            Vector<Method> methods = new Vector(resultSet.size());

            try {
                PropertyDescriptor[] descriptors = Introspector.getBeanInfo(classe).getPropertyDescriptors();
                Arrays.sort(descriptors, new OrderClassMethodsAlphabetically());

                for (PropertyDescriptor pD : descriptors) {
                    Method m = pD.getReadMethod();

                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }

            } catch (Exception ex) {}

            for (E m : resultSet) {
                Vector row = new Vector(ncamps);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {} 
                }

                data.addElement(row);
            }
        }

        model = new NotEditTableModel(data, columnNames);
        taula.setModel(model);

        TableColumn column;
        for (int i = 0; i < taula.getColumnCount(); i++) {
            column = taula.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }
    }

    //Passem el nom de columnes i les dades en arrays
    //Les cel·les de la JTable no es podran editar
    public static void loadTable(String[] nomCols, Object[][] dades, JTable taula) {

        //Mirem si han passat columnes i dades. En cas contrari sortim
        if (nomCols == null || dades == null) {
            return;
        }

        //Variables locals
        Vector columnNames = new Vector();
        Vector data = new Vector();
        DefaultTableModel model;

        //Anotem el nº de columnes a mostrar
        int ncamps = nomCols.length;

        //Recorrem l'array de noms de columna i els posem com a columnes de la taula
        for (String s : nomCols) {
            columnNames.addElement(s);
        }

        //Si hi ha algun element a l'array de dades omplim la taula
        if (dades.length != 0) {

            for (int i = 0; i < dades.length; i++) {
                Vector row = new Vector(ncamps);
                for (int j = 0; j < dades[i].length; j++) {
                    row.add(dades[i][j]);
                }
                data.addElement(row);
            }
        }

        model = new NotEditTableModel(data, columnNames);
        taula.setModel(model);

        TableColumn column;
        for (int i = 0; i < taula.getColumnCount(); i++) {
            column = taula.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }
    }

    //Classe que serveix per ordenar els mètodes de les classes alfabèticament
    private static class OrderClassMethodsAlphabetically implements Comparator {

        public int compare(Object o1, Object o2) {

            Method mo1 = ((PropertyDescriptor) o1).getReadMethod();
            Method mo2 = ((PropertyDescriptor) o2).getReadMethod();

            if (mo1 != null && mo2 != null) {
                return (int) mo1.getName().compareToIgnoreCase(mo2.getName());
            }

            if (mo1 == null) {
                return -1;

            } else {
                return 1;
            }
        }
    }

    //Classe que serveix per ordenar els camps de les classes alfabèticament
    private static class OrderClassFieldsAlphabetically implements Comparator {

        public int compare(Object o1, Object o2) {
            return (int) (((Field) o1).getName().compareToIgnoreCase(((Field) o2).getName()));
        }
    }

    //per carregar un JComboBox a partir d'un ArrayList que conté les dades 
    public static void loadCombo(ArrayList resultSet, JComboBox combo) {
        combo.setModel(new DefaultComboBoxModel((resultSet != null ? resultSet.toArray() : new Object[]{})));
    }

    //per carregar un JComboBox a partir d'un array que conté les dades 
    public static void loadCombo(Object[] resultSet, JComboBox combo) {
        combo.setModel(new DefaultComboBoxModel((resultSet != null ? resultSet : new Object[]{})));
    }
    

}


//Model de dades de les JTable que no permet editar les caselles
class NotEditTableModel extends DefaultTableModel {

    public NotEditTableModel(Vector data, Vector columns) {
        super(data, columns);
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
/*

//Classe ModelCanvisBD usada en la BDOO db4o
//Classe filla de DefaultTableModel que conté un Listener per automàticament actualitzar a la BD els canvis fets a una jTable
class ModelCanvisBD extends DefaultTableModel {

    private ObjectContainer resultSet = null;
    private int columnaID;

    //El paràmetre colID indica quina columna del DefaultTableModel conté l'objecte mostrat a la fila de la taula
    public ModelCanvisBD(Vector data, Vector columnNames, ObjectContainer rs, int colID) {
        super(data, columnNames);
        resultSet = rs;
        columnaID = colID;
        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                Object data = model.getValueAt(row, column);
                
                //Guardem el descriptor del mètode setter que ens interessa (el de la casella que modifiquem)
                Method method = null;
                try {

                    PropertyDescriptor[] descriptors = Introspector.getBeanInfo(model.getValueAt(row, columnaID).getClass()).getPropertyDescriptors();
                    Arrays.sort(descriptors, new Controlador.OrderClassMethodsAlphabetically());
                    int cont = 0;
                    for (PropertyDescriptor pD : descriptors) {
                        Method m = pD.getWriteMethod();
                        //Només agafem el setter de la columna modificada
                        if (m != null && cont == column) {
                            method = m;
                        }
                        cont++;
                    }

                } catch (IntrospectionException ex) {
                    Logger.getLogger(VistaActors.class.getName()).log(Level.SEVERE, null, ex);
                }
                //La primera instrucció del try actualitza les dades modificades de l'objecte (és com aplicar-li el setter corresponent)
                //La segona guarda l'objecte modificat a la BD
                try {
                    method.invoke(model.getValueAt(row, columnaID), new Object[]{data});
                    resultSet.store(model.getValueAt(row, columnaID));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Canvi de dada incorrecte!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        //permitim editar des de la taula totes les columnes excepte la que conté una col·lecció
        return column!=3; //To change body of generated methods, choose Tools | Templates.
    }
}
 */

 
