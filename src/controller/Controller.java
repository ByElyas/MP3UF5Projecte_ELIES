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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.System.console;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.Model;
import model.Vehicle;
import view.View;
import utilscontroller.Utils;

/**
 *
 * @author profe
 */
public class Controller {

    private static Model model;
    private static View view;
    
    private int filaSel=-1;
    private TableColumn tc;

    public Controller(Model m, View v) {
        view = v;
        model = m;

        controlador();
    }

    private void defecteText() {
        //CAIXES DE TEXT - FORMULARI
//        view.getAfegirMarcaText().setSize(0, 0);
        view.getAfegirMarcaText().setText("Marca Placeholder");
        view.getAfegirModelText().setText("Model Placeholder");
        view.getAfegirAnyText().setText("9999");
        view.getAfegirNumeroText().setText("999");

        //FILTRE
        view.getFiltrarVehiclesCombobox().removeAllItems();
        view.getFiltrarVehiclesCombobox().addItem("Ordenar per numero");
        view.getFiltrarVehiclesCombobox().addItem("Ordenar per marca");

        //LABELS - FORMULARI
        view.getAfegirMarcaLabel().setText("Marca Vehicle");
        view.getAfegirModelLabel().setText("Model Vehicle");
        view.getAfegirAnyLabel().setText("Any Vehicle");
        view.getAfegirNumeroLabel().setText("Numero Vehicle");

        //BOTÓ SUBMIT - FORMULARI
        view.getAfegirVehicleButton().setText("Afegir registre");

        //FORMULARI ELIMINAR - TEXT DEFECTE
        view.getEliminarVehicleButton().setText("Eliminar fila!");
    }

    public void carregarTaula() {
           tc = Utils.<Vehicle>loadTable(model.getData(), view.getJTaulaVehicles(), Vehicle.class, true, true);
    }

    private void controlador() {

        //Codi que inicilitza la vista
        view.setVisible(true);
        
        //Inicialitzem els textos per defecte que ens mostrarà l'alicatiu
        defecteText();
        

        
        //TAULA
        //Carregar les dades localitzades a Model.java, tambè servirà per al insertar dades      
        carregarTaula();


        //ACCIONS DE CRUD AQUI
        //afegirVehicle
        view.getAfegirVehicleButton().addActionListener(
                e -> {
                    model.insertarVehicle(view.getAfegirMarcaText().getText(),
                            view.getAfegirModelText().getText(),
                            Integer.parseInt(view.getAfegirAnyText().getText()),
                            Integer.parseInt(view.getAfegirNumeroText().getText())
                    );
                    carregarTaula();
                }
        );

        //eliminarVehicle
        
        view.getJTaulaVehicles().addMouseListener(
        
                new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        filaSel=view.getJTaulaVehicles().getSelectedRow();
                    }             
                } 
        );
        
        view.getEliminarVehicleButton().addActionListener(
                e -> {
//                    System.out.println(filaSel);
                    if (filaSel!=-1) {                        
                        TableColumnModel tcm = view.getJTaulaVehicles().getColumnModel();
                        tcm.addColumn(tc);
                        System.out.println(filaSel);  
                        Vehicle veh=(Vehicle)view.getJTaulaVehicles().getValueAt(filaSel, tcm.getColumnCount()-1);
//                        System.out.println(veh.toString());
                        tcm.removeColumn(tc);
                        model.eliminarVehicle(veh);
                        carregarTaula();
                        filaSel=-1;
                    }else {
                        System.out.println(filaSel);  
                        JOptionPane.showMessageDialog(view,"Has de seleccionar una fila per a borrarla!");
                    }                  
                }
        );
        
        view.getFiltrarVehiclesCombobox().addItemListener(
                
                e->{
                    if (view.getFiltrarVehiclesCombobox().getSelectedIndex()==0) {
                       carregarTaula();
                    }
                    if (view.getFiltrarVehiclesCombobox().getSelectedIndex()==1) {
                       model.getDataOrd().addAll(model.getData());
                       tc = Utils.<Vehicle>loadTable(model.getDataOrd(), view.getJTaulaVehicles(), Vehicle.class, true, true);
                    }
                }
        
        );
    }

    
    
    
    
    
    
    
    
    
    
    
////    @Override
//    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    //Per implementar els ActionEvents dels components de la vista (útil per 
    //exemple, per controlar l'acció que s'executa quan fem clic a un botó tant 
    //usant el ratolí com si l'apretem en la barra del teclat  
    static class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    //Per implementar els KeyEvents
    //També podem usar un KeyAdapter
    //static class Key extends KeyAdapter{}
    static class Key implements KeyListener {

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
