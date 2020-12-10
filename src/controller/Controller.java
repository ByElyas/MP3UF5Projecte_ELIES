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
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.Conductor;
import model.Model;
import model.Vehicle;
import utilscontroller.Utils;
import view.View;
//import utilscontroller.Utils;

/**
 *
 * @author profe
 */
public class Controller {

    private static Model model;
    private static View view;

//    private int comboboxActualCond = 0;
    private int colVehicleActual = 0;
    private int colConductorActual = 0;
    private int filaSel = -1;
    private int filaSelCond = -1;
    private TableColumn tc;
    private TableColumn tcC;
    private TableColumn tcAlgo;
//    private TableColumn tcE;

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
        String[] sponsors_exemple = {"Pirelli", "Repsol", "SPARCO"};
        System.out.println(Arrays.toString(sponsors_exemple));
        model.insertarVehicle("Mazda", "RX-7 FC", 1989, 6, sponsors_exemple);
        model.insertarVehicle("Unity", "RX-7 FC", 1986, 8, sponsors_exemple);
        model.insertarVehicle("Nissan", "Skyline GTR R32", 1991, 22, sponsors_exemple);
//        model.insertarVehicle("Toyota", "Corolla Trueno AE86", 1986, 86);
//        model.insertarVehicle("Nissan", "Silvia S15", 1998, 66);
//        model.insertarVehicle("Audi", "Quattro Sport", 1988, 24);
//        model.insertarVehicle("Seat", "Ibiza KitCar", 1995, 7);

        //Conductors per defecte
//        model.insertarConductor("Pepe", "Viyuela", 45, 6589);
//        model.insertarConductor("Paul", "Walker", 47, 2254);
//        model.insertarConductor("Ian", "Mardhaveer", 24, 222);
//        model.insertarConductor("Alex", "Cañizares", 21, 1574);
//        model.insertarConductor("Frank", "Williams", 21, 1574);
        ///////////////////////////
        //TITOL PANEL VEHICLE
        view.getVehicleLabel().setText("VEHICLES");

        //CAIXES DE TEXT - FORMULARI
//        view.getAfegirMarcaText().setSize(0, 0);
        view.getAfegirMarcaText().setText("Marca Placeholder");
        view.getAfegirModelText().setText("Model Placeholder");
        view.getAfegirAnyText().setText("9999");
        view.getAfegirNumeroText().setText("999");
        view.getAfegirSponsor1Label().setText("Sponsor 1");
        view.getAfegirSponsor2Label().setText("Sponsor 2");
        view.getAfegirSponsor3Label().setText("Sponsor 3");
        view.getAfegirSponsor1Text().setText("Exemple");
        view.getAfegirSponsor2Text().setText("Exemple");
        view.getAfegirSponsor3Text().setText("Exemple");

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

        //FORMULARI -EDITAR
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
        view.getNumVehicleConductorLabel().setText("Numero del vehicle");

        actualitzarComboboxCond();
    }

    public void actualitzarComboboxCond() {
        //Combobox per a elegir vehicle per als conductors nous               
        view.getNumVehicleConductorCombobox().removeAllItems();
//        Combobox per a elegir vehicle per al conductor
//        System.out.println(view.getJTaulaConductor().getColumnCount());
        //Utils.<Vehicle>loadCombo(model.getData(), view.getNumVehicleConductorCombobox());   
        Utils.<Vehicle>loadCombo(model.getData(), view.getNumVehicleConductorCombobox());
    }
//    public void defecteTextDinamic() {
//     
//    }

    public void carregarTaulaVehicle() {
//        System.out.println(model.getData());
//        System.out.println();
//        System.out.println(filaSel);
////        model.getData().addAll(model.getDataOrd());
        tc = Utils.<Vehicle>loadTable(model.getData(), view.getJTaulaVehicles(), Vehicle.class, true, true);

    }

    public void carregarTaulaVehicleOrdenada() {
//        model.getDataOrd().addAll(model.getData());
        tc = Utils.<Vehicle>loadTable(model.getDataOrd(), view.getJTaulaVehicles(), Vehicle.class, true, true);
    }

    public void carregarTaulaVehicleActual() {
        if (colVehicleActual == 0) {
            carregarTaulaVehicle();
        } else {
            carregarTaulaVehicleOrdenada();
        }
    }

    public void carregarTaulaConductor() {
//        model.getDataConductor().addAll(model.getDataOrdConductor());
        tcC = Utils.<Conductor>loadTable(model.getDataConductor(), view.getJTaulaConductor(), Conductor.class, true, true);
    }

    public void carregarTaulaConductorOrdenada() {
//        model.getDataOrdConductor().addAll(model.getDataConductor());
        tcC = Utils.<Conductor>loadTable(model.getDataOrdConductor(), view.getJTaulaConductor(), Conductor.class, true, true);
    }

    public void carregarTaulaConductorActual() {

        if (colConductorActual == 0) {
            carregarTaulaConductor();
        } else {
            carregarTaulaConductorOrdenada();
        }
    }

    private void controlador() {

//        DefaultTableModel m = new DefaultTableModel();
//        view.getJTaulaVehicles().setModel(m);
        //Codi que inicilitza la vista
        view.setVisible(true);
//        carregarTaulaVehicleActual();
//        carregarTaulaConductorActual();
        //Inicialitzem els textos per defecte que ens mostrarà l'alicatiu
        defecteText();

        //TAULA
        //Carregar les dades localitzades a Model.java, tambè servirà per al insertar dades      
        carregarTaulaVehicleActual();
        carregarTaulaConductorActual();

        //ACCIONS DE CRUD AQUI
        //VEHICLE
        //Afegir text dinamic a l'apartat de edit
        view.getJTaulaVehicles().addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filaSel = view.getJTaulaVehicles().getSelectedRow();
                TableColumnModel tcmMC = view.getJTaulaVehicles().getColumnModel();
//                System.out.println(tcmMC.getColumnCount());
                tcmMC.addColumn(tc);
//                DefaultTableModel m = (DefaultTableModel() view.getJTaulaVehicles().getModel();
//                System.out.println(filaSel);
//                System.out.println(tcmMC.getColumnCount());
//                System.out.println(Vehicle.class.getClass().getName());
                Vehicle vehE = (Vehicle) view.getJTaulaVehicles().getValueAt(filaSel, tcmMC.getColumnCount() - 1);
//                System.out.println(String.valueOf(vehE));
                tcmMC.removeColumn(tc);
                view.getEditarNumeroText().setText(String.valueOf(vehE.get1_numero_Vehicle()));
                view.getEditarAnyText().setText(String.valueOf(vehE.get3_any_Vehicle()));
                view.getEditarModelText().setText(vehE.get2_model_Vehicle());
                view.getEditarMarcaText().setText(vehE.get4_marca_Vehicle());
                if (filaSel != -1) {
                    TableColumnModel tcm = view.getJTaulaVehicles().getColumnModel();
                    tcm.addColumn(tc);
                    Vehicle obj = (Vehicle) view.getJTaulaVehicles().getValueAt(filaSel, tcm.getColumnCount() - 1);
                    tcm.removeColumn(tc);
                    carregarTaulaVehicleActual();
                    tcC = Utils.<Conductor>loadTable(obj.get6_cond(), view.getJTaulaConductor(), Conductor.class, true, true);
                }
//                carregarTaulaConductorActual();
            }
        }
        //EDITAR VEHICLE
        );
        view.getEditarVehicleButton().addActionListener(e -> {
            if (filaSel != -1) {
                TableColumnModel tcm = view.getJTaulaVehicles().getColumnModel();
                tcm.addColumn(tc);
                Vehicle veh = (Vehicle) view.getJTaulaVehicles().getValueAt(filaSel, tcm.getColumnCount() - 1);
                veh.set1_numero_Vehicle(Integer.parseInt(view.getEditarNumeroText().getText()));
                veh.set2_model_Vehicle(view.getEditarModelText().getText());
                veh.set3_any_Vehicle(Integer.parseInt(view.getEditarAnyText().getText()));
                veh.set4_marca_Vehicle(view.getEditarMarcaText().getText());
                tcm.removeColumn(tc);
                filaSel = -1;
                carregarTaulaVehicleActual();
            } else {
                System.out.println(filaSel);
                JOptionPane.showMessageDialog(view, "Has de seleccionar una fila per a editarla");
            }
        }
        );

        //afegirVehicle
        view.getAfegirVehicleButton().addActionListener(e -> {
            //Exemple de validesa utilitzant expressions regulars
            if (view.getAfegirNumeroText().getText().isBlank()
                    || view.getAfegirMarcaText().getText().isBlank()
                    || view.getAfegirModelText().getText().isBlank()
                    || view.getAfegirAnyText().getText().isBlank()) {
                JOptionPane.showMessageDialog(view, "Hi ha algun camp buit. No pot haver-hi cap camp buit!");
            } else {
                if (view.getAfegirNumeroText().getText().matches("\\d{2}") || view.getAfegirNumeroText().getText().matches("\\d{1}")) {
                    String[] sponsors_vehicle = {view.getAfegirSponsor1Text().getText(), view.getAfegirSponsor2Text().getText(), view.getAfegirSponsor3Text().getText()};
                    try {
                        if (Integer.parseInt(view.getAfegirAnyText().getText()) < 1900
                                || Integer.parseInt(view.getAfegirAnyText().getText()) > 2030) {
                            JOptionPane.showMessageDialog(view, "El any ha de ser valid (entre 1900 i 2030)");
                        } else {
                            model.insertarVehicle(view.getAfegirMarcaText().getText(),
                                    view.getAfegirModelText().getText(),
                                    Integer.parseInt(view.getAfegirAnyText().getText()),
                                    Integer.parseInt(view.getAfegirNumeroText().getText()),
                                    sponsors_vehicle
                            );
                        }
                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(view, "El any ha de ser un ANY (en numeros, no escrit)");
                    }
                    actualitzarComboboxCond();
                    carregarTaulaVehicleActual();
                    carregarTaulaVehicleActual();
                } else {
                    JOptionPane.showMessageDialog(view, "El numero del vehicle ha de ser inferior a 100! I no pot ser"
                            + " una paraula, lletres, etc..");
                }
            }

        }
        );

        //eliminarVehicle
        //Aqui sempre dona un IndexArrayOutOfBounds bla bla bla 0 <= 0... o algo així. OPerò el programa funciona bè
        //així que no tinc ni idea de que es.
        view.getEliminarVehicleButton().addActionListener(e -> {
            if (filaSel != -1) {
                TableColumnModel tcm = view.getJTaulaVehicles().getColumnModel();
                tcm.addColumn(tc);
                Vehicle veh = (Vehicle) view.getJTaulaVehicles().getValueAt(filaSel, tcm.getColumnCount() - 1);
                tcm.removeColumn(tc);
                model.eliminarVehicle(veh);
                actualitzarComboboxCond();
                carregarTaulaVehicleActual();
                carregarTaulaConductorActual();
                //Vale, lo index out of bounds sempre el dona quan es borra un vehicle que no te cap conductor relacionat
                //aaaaaaaaaaaaaaamigo amigo
//                System.out.println(veh.get6_cond());
//                if (!veh.get6_cond().equals("[]")) {
                    for (int i = 0; i < view.getJTaulaConductor().getRowCount(); i++) {
                        TableColumnModel tcmA = view.getJTaulaConductor().getColumnModel();
                        tcmA.addColumn(tcC);
                        //La Exception la dona aqui -->
                        Conductor cond = (Conductor) view.getJTaulaConductor().getValueAt(i, tcmA.getColumnCount() - 1);
                        // <--
                        tcmA.removeColumn(tcC);
                        if (cond.get5_vehicle_Conductor() == veh.get1_numero_Vehicle()) {
                            model.eliminarConductor(cond);
                            carregarTaulaVehicleActual();
                            carregarTaulaConductorActual();
                        }
                    }
//                }

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
                colVehicleActual = 0;
                carregarTaulaVehicleActual();
            }
            if (view.getFiltrarVehiclesCombobox().getSelectedIndex() == 1) {
                colVehicleActual = 1;
                carregarTaulaVehicleActual();
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
                tcmCondE.addColumn(tcC);
//                        System.out.println(filaSel);  
                Conductor condE = (Conductor) view.getJTaulaConductor().getValueAt(filaSelCond, tcmCondE.getColumnCount() - 1);
//                System.out.println(String.valueOf(vehE));
                tcmCondE.removeColumn(tcC);
                view.getEditarIdConductorText().setText(String.valueOf(condE.get1_id_Conductor()));
                view.getEditarEdatConductorText().setText(String.valueOf(condE.get3_edat_Conductor()));
                view.getEditarCognomConductorText().setText(condE.get2_cognom_Conductor());
                view.getEditarNomConductorText().setText(condE.get4_nom_Conductor());

            }
        }
        );
        view.getEditarConductorButton().addActionListener(e -> {
//                    System.out.println(filaSel);
            carregarTaulaVehicleActual();
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

                carregarTaulaConductorActual();

                filaSelCond = -1;
            } else {
                System.out.println(filaSelCond);
                JOptionPane.showMessageDialog(view, "Has de seleccionar una fila per a editarla!");
                carregarTaulaVehicleActual();
            }
        }
        );

        //afegirConductor
        view.getAfegirConductorButton().addActionListener(e -> {
            carregarTaulaVehicleActual();
            TableColumnModel tcm = view.getJTaulaVehicles().getColumnModel();
            tcm.addColumn(tc);;
            Vehicle veh = (Vehicle) view.getJTaulaVehicles().getValueAt(view.getNumVehicleConductorCombobox().getSelectedIndex(),
                    tcm.getColumnCount() - 1);
//                Vehicle veh = new Vehicle("Opel", "Corsa", 2004, 154);
            tcm.removeColumn(tc);

            model.insertarConductor(view.getAfegirNomConductorText().getText(),
                    view.getAfegirCognomConductorText().getText(),
                    Integer.parseInt(view.getAfegirEdatConductorText().getText()),
                    Integer.parseInt(view.getAfegirIdConductorText().getText()),
                    veh
            );
            carregarTaulaConductorActual();
            carregarTaulaVehicleActual();
        }
        );
        //eliminarConductor
        view.getEliminarConductorButton().addActionListener(e -> {
//                    System.out.println(filaSel);
            if (filaSelCond != -1) {
                TableColumnModel tcm = view.getJTaulaConductor().getColumnModel();
                tcm.addColumn(tcC);
//                        System.out.println(filaSel);  
                Conductor cond = (Conductor) view.getJTaulaConductor().getValueAt(filaSelCond, tcm.getColumnCount() - 1);
//                        System.out.println(veh.toString());
                tcm.removeColumn(tcC);
                model.eliminarConductor(cond);
                carregarTaulaConductorActual();
                carregarTaulaVehicleActual();
                filaSelCond = -1;
            } else {
                System.out.println(filaSelCond);
                JOptionPane.showMessageDialog(view, "Has de seleccionar una fila per a borrarla!");
            }
        }
        );

        view.getFiltrarConductorCombobox().addItemListener(e -> {
            if (view.getFiltrarConductorCombobox().getSelectedIndex() == 0) {
                colConductorActual = 0;
                carregarTaulaConductorActual();
            }
            if (view.getFiltrarConductorCombobox().getSelectedIndex() == 1) {
                colConductorActual = 1;
                carregarTaulaConductorActual();
            }
        }
        );

//        view.getNumVehicleConductorCombobox().addItemListener(e -> {
//            comboboxActualCond =;
//        });
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
