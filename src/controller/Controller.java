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
import model.Conductor;
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

    private int filaSel = -1;
    private int filaSelCond = -1;
    private TableColumn tc;
    private TableColumn tcE;

    public Controller(Model m, View v) {
        view = v;
        model = m;

        controlador();
    }

    private void defecteText() {
        /**
         * VEHICLE
         */
        
        //Vehicles per defecte
        model.insertarVehicle("Mazda", "RX-7 FC", 1989, 6);
        model.insertarVehicle("Unity", "RX-7 FC", 1986, 8);
        model.insertarVehicle("Nissan", "Skyline GTR R32", 1991, 22);
        model.insertarVehicle("Toyota", "Corolla Trueno AE86", 1986, 86);
        model.insertarVehicle("Nissan", "Silvia S15", 1998, 66);
                
        //Conductors per defecte
        model.insertarConductor("Pepe", "Viyuela", 45, 6589);
        model.insertarConductor("Paul", "Walker", 47, 2254);
        model.insertarConductor("Ian", "Lewis", 24, 222);
        model.insertarConductor("Frank", "Williams", 53, 1);
        model.insertarConductor("Alex", "Cañizares", 21, 1574);
        
        
        ///////////////////////////
        
        //TITOL PANEL VEHICLE
        view.getVehicleLabel().setText("VEHICLES");

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

        //SELECCIONAR FILA ACTUAL   (potser no tindria que anar a text default, pero ho necesssito
        //per a posar les dades a els camps de text i estess coses
        //FORMULARI -EDITAR
//        TableColumnModel tcmE = view.getJTaulaVehicles().getColumnModel();
//        tcmE.addColumn(tc);
////      System.out.println(filaSel);  
//        Vehicle veh=(Vehicle)view.getJTaulaVehicles().getValueAt(filaSel, tcmE.getColumnCount()-1);
////      System.out.println(veh.toString());
        view.getEditarVehicleButton().setText("Editar!");
        view.getEditarAnyLabel().setText("Any Vehicle");
        view.getEditarAnyText().setText("9999");
        view.getEditarMarcaLabel().setText("Marca Vehicle");
        view.getEditarMarcaText().setText("Placeholder");
        view.getEditarModelLabel().setText("Model Vehicle");
        view.getEditarModelText().setText("Placeholder");
        view.getEditarNumeroLabel().setText("Numero Vehicle");
        view.getEditarNumeroText().setText("999");
//        tcmE.removeColumn(tc);


        /**
         * Conductor
         */
                //TITOL PANEL VEHICLE
        view.getConductorLabel().setText("CONDUCTORS");

        //CAIXES DE TEXT - FORMULARI
//        view.getAfegirMarcaText().setSize(0, 0);
        view.getAfegirNomConductorText().setText("Nom Placeholder");
        view.getAfegirCognomConductorText().setText("Cognom Placeholder");
        view.getAfegirEdatConductorText().setText("999");
        view.getAfegirIdConductorText().setText("99999");

        //FILTRE
        view.getFiltrarConductorCombobox().removeAllItems();
        view.getFiltrarConductorCombobox().addItem("Ordenar per ID");
        view.getFiltrarConductorCombobox().addItem("Ordenar per nom");

        //LABELS - FORMULARI
        view.getAfegirNomConductorLabel().setText("Nom Conductor");
        view.getAfegirCognomConductorLabel().setText("Cognom Conductor");
        view.getAfegirEdatConductorLabel().setText("Edat Conductor");
        view.getAfegirIdConductorLabel().setText("ID Conductor");

        //BOTÓ SUBMIT - FORMULARI
        view.getAfegirConductorButton().setText("Afegir registre");

        //FORMULARI ELIMINAR - TEXT DEFECTE
        view.getEliminarConductorButton().setText("Eliminar fila!");


        view.getEditarConductorButton().setText("Editar!");
        view.getEditarNomConductorLabel().setText("Nom Conductor");
        view.getEditarEdatConductorText().setText("999");
        view.getEditarCognomConductorLabel().setText("Cognom Conductor");
        view.getEditarNomConductorText().setText("Placeholder");
        view.getEditarCognomConductorText().setText("Placeholder");
        view.getEditarEdatConductorLabel().setText("Edat Conductor");
        view.getEditarIdConductorLabel().setText("ID Conductor");
        view.getEditarIdConductorText().setText("99999");
//        tcmE.removeColumn(tc);
        
    }

    public void carregarTaulaVehicle() {
        tc = Utils.<Vehicle>loadTable(model.getData(), view.getJTaulaVehicles(), Vehicle.class, true, true);
    }
    public void carregarTaulaVehicleOrdenada() {
        model.getDataOrd().addAll(model.getData());
        tc = Utils.<Vehicle>loadTable(model.getDataOrd(), view.getJTaulaVehicles(), Vehicle.class, true, true);
    }
    
    public void carregarTaulaConductor() {
        tc = Utils.<Conductor>loadTable(model.getDataConductor(), view.getJTaulaConductor(), Conductor.class, true, true);
    }

    private void controlador() {

        //Codi que inicilitza la vista
        view.setVisible(true);

        //Inicialitzem els textos per defecte que ens mostrarà l'alicatiu
        defecteText();

        //TAULA
        //Carregar les dades localitzades a Model.java, tambè servirà per al insertar dades      
        carregarTaulaVehicle();
        carregarTaulaConductor();

        //ACCIONS DE CRUD AQUI
        
        //VEHICLE
        //editarVehicle
        //Afegir text dinamic a l'apartat de edit
        view.getJTaulaVehicles().addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filaSel = view.getJTaulaVehicles().getSelectedRow();
                TableColumnModel tcmE = view.getJTaulaVehicles().getColumnModel();
                tcmE.addColumn(tc);
//                        System.out.println(filaSel);  
                Vehicle vehE = (Vehicle) view.getJTaulaVehicles().getValueAt(filaSel, tcmE.getColumnCount() - 1);
//                System.out.println(String.valueOf(vehE));
                view.getEditarNumeroText().setText(String.valueOf(vehE.get1_numero_Vehicle()));
                view.getEditarAnyText().setText(String.valueOf(vehE.get3_any_Vehicle()));
                view.getEditarModelText().setText(vehE.get2_model_Vehicle());
                view.getEditarMarcaText().setText(vehE.get4_marca_Vehicle());
                tcmE.removeColumn(tc);
    
            }
        }
        );
        view.getEditarVehicleButton().addActionListener(e -> {
//                    System.out.println(filaSel);
                    if (filaSel != -1) {
                        TableColumnModel tcm = view.getJTaulaVehicles().getColumnModel();
                        tcm.addColumn(tc);
//                        System.out.println(filaSel);  
                        Vehicle veh = (Vehicle) view.getJTaulaVehicles().getValueAt(filaSel, tcm.getColumnCount() - 1);
//                        System.out.println(veh.toString());
                        veh.set1_numero_Vehicle(Integer.parseInt(view.getEditarNumeroText().getText()));
                        veh.set2_model_Vehicle(view.getEditarModelText().getText());
                        veh.set3_any_Vehicle(Integer.parseInt(view.getEditarAnyText().getText()));
                        veh.set4_marca_Vehicle(view.getEditarMarcaText().getText());
//                        view.getJTaulaVehicles().setValueAt(veh, filaSel, tcm.getColumnCount()-1);     
                        carregarTaulaVehicle();
                        tcm.removeColumn(tc);
                        filaSel = -1;
                    } else {
                        System.out.println(filaSel);
                        JOptionPane.showMessageDialog(view, "Has de seleccionar una fila per a editarla");
                    }
                }
        );

        //afegirVehicle
        view.getAfegirVehicleButton().addActionListener(e -> {
                    model.insertarVehicle(view.getAfegirMarcaText().getText(),
                            view.getAfegirModelText().getText(),
                            Integer.parseInt(view.getAfegirAnyText().getText()),
                            Integer.parseInt(view.getAfegirNumeroText().getText())
                    );
                    carregarTaulaVehicle();
                }
        );

        //eliminarVehicle
        view.getEliminarVehicleButton().addActionListener(e -> {
//                    System.out.println(filaSel);
                    if (filaSel != -1) {
                        TableColumnModel tcm = view.getJTaulaVehicles().getColumnModel();
                        tcm.addColumn(tc);
//                        System.out.println(filaSel);  
                        Vehicle veh = (Vehicle) view.getJTaulaVehicles().getValueAt(filaSel, tcm.getColumnCount() - 1);
//                        System.out.println(veh.toString());
                        tcm.removeColumn(tc);
                        model.eliminarVehicle(veh);
                        carregarTaulaVehicle();
                        filaSel = -1;
                    } else {
                        System.out.println(filaSel);
                        JOptionPane.showMessageDialog(view, "Has de seleccionar una fila per a borrarla!");
                    }
                }
        );

        //FILTRE 
        view.getFiltrarVehiclesCombobox().addItemListener(e -> {
                    if (view.getFiltrarVehiclesCombobox().getSelectedIndex() == 0) {
                        carregarTaulaVehicle();
                    }
                    if (view.getFiltrarVehiclesCombobox().getSelectedIndex() == 1) {
                        carregarTaulaVehicleOrdenada();
                    }
                }
        );
        
        
        
        ////
        //CONDUCTOR
        ////
        //editarVehicle
        //Afegir text dinamic a l'apartat de edit
        view.getJTaulaConductor().addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filaSelCond = view.getJTaulaConductor().getSelectedRow();
                TableColumnModel tcmCondE = view.getJTaulaConductor().getColumnModel();
                tcmCondE.addColumn(tc);
//                        System.out.println(filaSel);  
                Conductor condE = (Conductor) view.getJTaulaConductor().getValueAt(filaSelCond, tcmCondE.getColumnCount() - 1);
//                System.out.println(String.valueOf(vehE));
                view.getEditarIdConductorText().setText(String.valueOf(condE.get1_id_Conductor()));
                view.getEditarEdatConductorText().setText(String.valueOf(condE.get3_edat_Conductor()));
                view.getEditarCognomConductorText().setText(condE.get2_cognom_Conductor());
                view.getEditarNomConductorText().setText(condE.get4_nom_Conductor());
                tcmCondE.removeColumn(tc);
                
                
            }
        }
        );
        view.getEditarConductorButton().addActionListener(e -> {
//                    System.out.println(filaSel);
                    if (filaSelCond != -1) {
                        TableColumnModel tcm = view.getJTaulaConductor().getColumnModel();
                        tcm.addColumn(tc);
//                        System.out.println(filaSel);  
                        Conductor cond = (Conductor) view.getJTaulaConductor().getValueAt(filaSelCond, tcm.getColumnCount() - 1);
//                        System.out.println(veh.toString());
                        cond.set1_id_Conductor(Integer.parseInt(view.getEditarIdConductorText().getText()));
                        cond.set2_cognom_Conductor(view.getEditarCognomConductorText().getText());
                        cond.set3_edat_Conductor(Integer.parseInt(view.getEditarEdatConductorText().getText()));
                        cond.set4_nom_Conductor(view.getEditarNomConductorText().getText());
                        tcm.removeColumn(tc);
                        carregarTaulaConductor();
                        filaSelCond = -1;
                    } else {
                        System.out.println(filaSelCond);
                        JOptionPane.showMessageDialog(view, "Has de seleccionar una fila per a editarla!");
                    }
                }
        );

        //afegirConductor
        view.getAfegirConductorButton().addActionListener(e -> {
                    model.insertarConductor(view.getAfegirNomConductorText().getText(),
                            view.getAfegirCognomConductorText().getText(),
                            Integer.parseInt(view.getAfegirEdatConductorText().getText()),
                            Integer.parseInt(view.getAfegirIdConductorText().getText())
                    );
                    carregarTaulaConductor();
                }
        );

        //eliminarConductor
        view.getEliminarConductorButton().addActionListener(e -> {
//                    System.out.println(filaSel);
                    if (filaSelCond != -1) {
                        TableColumnModel tcm = view.getJTaulaConductor().getColumnModel();
                        tcm.addColumn(tc);
//                        System.out.println(filaSel);  
                        Conductor cond = (Conductor) view.getJTaulaConductor().getValueAt(filaSelCond, tcm.getColumnCount() - 1);
//                        System.out.println(veh.toString());
                        tcm.removeColumn(tc);
                        model.eliminarConductor(cond);
                        carregarTaulaConductor();
                        filaSelCond = -1;
                    } else {
                        System.out.println(filaSelCond);
                        JOptionPane.showMessageDialog(view, "Has de seleccionar una fila per a borrarla!");
                    }
                }
        );

        view.getFiltrarConductorCombobox().addItemListener(e -> {
                    if (view.getFiltrarConductorCombobox().getSelectedIndex() == 0) {
                        carregarTaulaConductor();
                    }
                    if (view.getFiltrarConductorCombobox().getSelectedIndex() == 1) {
                        model.getDataOrdConductor().addAll(model.getDataConductor());
                        tc = Utils.<Conductor>loadTable(model.getDataOrdConductor(), view.getJTaulaConductor(), Conductor.class, true, true);
                    }
                }
        );
    }

    
    

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
