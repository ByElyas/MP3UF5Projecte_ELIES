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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import static java.lang.System.console;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private String nomArxiuV = "fitxer_dades_VEHICLE";
    private String nomArxiuC = "fitxer_dades_CONDUCTOR";
    private int colVehicleActual = 0;
    private int colConductorActual = 0;
    private int filaSel = -1;
    private int filaSelCond = -1;
    private TableColumn tc;
    private TableColumn tcC;
    private TableColumn tcAlgo;

    public Controller(Model m, View v) {
        view = v;
        model = m;

        controlador();
    }

    private void defecteText() {
        /**
         * VEHICLE
         */

        ///////////////////////////
        //TITOL PANEL VEHICLE
        view.getVehicleLabel().setText("VEHICLES");

        //CAIXES DE TEXT - FORMULARI
        view.getAfegirMarcaText().setText("Marca Placeholder");
        view.getAfegirModelText().setText("Model Placeholder");
        view.getAfegirAnyText().setText("1900");
        view.getAfegirNumeroText().setText("99");
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

        /**
         * Conductor
         */
        //TITOL PANEL VEHICLE
        view.getConductorLabel().setText("CONDUCTORS");

        //CAIXES DE TEXT - FORMULARI
        view.getAfegirNomConductorText().setText("Nom Placeholder");
        view.getAfegirCognomConductorText().setText("Cognom Placeholder");
        view.getAfegirEdatConductorText().setText("18");
        view.getAfegirIdConductorText().setText("00001");

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
        view.getNumVehicleConductorLabel().setText("Numero del vehicle");

        actualitzarComboboxCond();
    }

    public void actualitzarComboboxCond() {
        //Combobox per a elegir vehicle per als conductors nous               
        view.getNumVehicleConductorCombobox().removeAllItems();
        Utils.<Vehicle>loadCombo(model.getData(), view.getNumVehicleConductorCombobox());
    }

    public void carregarTaulaVehicle() {
        tc = Utils.<Vehicle>loadTable(model.getData(), view.getJTaulaVehicles(), Vehicle.class, true, true);

    }

    public void carregarTaulaVehicleOrdenada() {
        model.getDataOrd().addAll(model.getData());
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
        tcC = Utils.<Conductor>loadTable(model.getDataConductor(), view.getJTaulaConductor(), Conductor.class, true, true);
    }

    public void carregarTaulaConductorOrdenada() {
        model.getDataOrdConductor().addAll(model.getDataConductor());
        tcC = Utils.<Conductor>loadTable(model.getDataOrdConductor(), view.getJTaulaConductor(), Conductor.class, true, true);
    }

    public void carregarTaulaConductorActual() {

        if (colConductorActual == 0) {
            carregarTaulaConductor();
        } else {
            carregarTaulaConductorOrdenada();
        }
    }

    public void preguntarPassword() throws FileNotFoundException {
        //carregar la password al fitxer
        Random rn = new Random();
        long offset = rn.nextInt(1000 - 100) + 100;
        String secret = "701554";

        System.out.println(offset);
        File f = new File("secret.dat");
        try ( RandomAccessFile fitxer = new RandomAccessFile(f, "rw")) {
            fitxer.seek(0);
            fitxer.writeLong(offset);
            fitxer.seek(offset);
            fitxer.writeUTF(secret);
            System.out.println("Password carregada correctament");
            fitxer.seek(offset);
            String secretFitxer = fitxer.readUTF();
            
            //Formulari de password: trobarla al fitxer i mirar si està bè o mal
            String choice = JOptionPane.showInputDialog("Entra la password:", "Password");

            if ((choice == null) || ((choice != null) && !(choice.equals(secretFitxer)))) {
                JOptionPane.showMessageDialog(view, "La password no es la correcta! El programa es tancarà...");
                System.exit(0);                
            }
        } catch (Exception e) {
            System.out.println("Algo dolent ha passat al carregar la password al fitxer!");
        }
    }

    public void carregarDades() {
        try {
            model.loadVehicle(nomArxiuV);
        } catch (IOException ex) {
            System.out.println("a");
        } catch (ClassNotFoundException ex) {
            System.out.println("a");
        }

        try {
            model.loadConductor(nomArxiuC);
        } catch (IOException ex) {
            System.out.println("a");
        } catch (ClassNotFoundException ex) {
            System.out.println("a");
        }
    }

    private void controlador() {

        try {
            preguntarPassword();
        } catch (FileNotFoundException ex) {
            System.out.println("No s'ha trobat algun fitxer, jo que se xdd");
        }

        //Codi que inicilitza la vista
        view.setVisible(true);

        //CARREGAR DADES A LES COL·LECCIONS A PARTIR DELS FITXERS
        carregarDades();

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
                tcmMC.addColumn(tc);
                Vehicle vehE = (Vehicle) view.getJTaulaVehicles().getValueAt(filaSel, tcmMC.getColumnCount() - 1);
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
                carregarTaulaConductorActual();
            } else {
                System.out.println(filaSel);
                JOptionPane.showMessageDialog(view, "Has de seleccionar una fila per a editarla");
            }
        }
        );

        //afegirVehicle
        view.getAfegirVehicleButton().addActionListener(e -> {

            if (view.getAfegirNumeroText().getText().isBlank()
                    || view.getAfegirMarcaText().getText().isBlank()
                    || view.getAfegirModelText().getText().isBlank()
                    || view.getAfegirAnyText().getText().isBlank()) {
                JOptionPane.showMessageDialog(view, "Hi ha algun camp buit. No pot haver-hi cap camp buit!");
            } else {
                //Exemple de validesa utilitzant expressions regulars
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
                for (int i = 0; i < view.getJTaulaConductor().getRowCount(); i++) {
                    TableColumnModel tcmA = view.getJTaulaConductor().getColumnModel();
                    tcmA.addColumn(tcC);
                    Conductor cond = (Conductor) view.getJTaulaConductor().getValueAt(i, tcmA.getColumnCount() - 1);
                    tcmA.removeColumn(tcC);
                    if (cond.get5_vehicle_Conductor() == veh.get1_numero_Vehicle()) {
                        model.eliminarConductor(cond);
                        carregarTaulaVehicleActual();
                        carregarTaulaConductorActual();
                    }
                }

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
                Conductor condE = (Conductor) view.getJTaulaConductor().getValueAt(filaSelCond, tcmCondE.getColumnCount() - 1);
                tcmCondE.removeColumn(tcC);
                view.getEditarIdConductorText().setText(String.valueOf(condE.get1_id_Conductor()));
                view.getEditarEdatConductorText().setText(String.valueOf(condE.get3_edat_Conductor()));
                view.getEditarCognomConductorText().setText(condE.get2_cognom_Conductor());
                view.getEditarNomConductorText().setText(condE.get4_nom_Conductor());

            }
        }
        );
        view.getEditarConductorButton().addActionListener(e -> {
            carregarTaulaVehicleActual();
            if (filaSelCond != -1) {
                TableColumnModel tcm = view.getJTaulaConductor().getColumnModel();
                tcm.addColumn(tc);
                Conductor cond = (Conductor) view.getJTaulaConductor().getValueAt(filaSelCond, tcm.getColumnCount() - 1);
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
            if (view.getAfegirIdConductorText().getText().isBlank()
                    || view.getAfegirCognomConductorText().getText().isBlank()
                    || view.getAfegirEdatConductorText().getText().isBlank()
                    || view.getAfegirNomConductorText().getText().isBlank()) {
                JOptionPane.showMessageDialog(view, "Hi ha algun camp buit. No pot haver-hi cap camp buit!");
            } else {

                if (view.getAfegirIdConductorText().getText().matches("\\d{5}")) {
                    carregarTaulaVehicleActual();
                    try {
                        if (Integer.parseInt(view.getAfegirEdatConductorText().getText()) < 18
                                || Integer.parseInt(view.getAfegirEdatConductorText().getText()) > 80) {
                            JOptionPane.showMessageDialog(view, "La edat del conductor ha d'estar entre 18 i 80 anys!");
                        } else {
                            TableColumnModel tcm = view.getJTaulaVehicles().getColumnModel();
                            tcm.addColumn(tc);;
                            Vehicle veh = (Vehicle) view.getJTaulaVehicles().getValueAt(view.getNumVehicleConductorCombobox().getSelectedIndex(),
                                    tcm.getColumnCount() - 1);
                            tcm.removeColumn(tc);

                            model.insertarConductor(view.getAfegirNomConductorText().getText(),
                                    view.getAfegirCognomConductorText().getText(),
                                    Integer.parseInt(view.getAfegirEdatConductorText().getText()),
                                    Integer.parseInt(view.getAfegirIdConductorText().getText()),
                                    veh
                            );
                        }

                    } catch (NumberFormatException a) {
                        JOptionPane.showMessageDialog(view, "La edat ha de estar en format numeric!");
                    }
                    carregarTaulaConductorActual();
                    carregarTaulaVehicleActual();
                } else {
                    JOptionPane.showMessageDialog(view, "La id del conductor ha de tenir 5 digits, encara que sigui"
                            + " una id baixa! Exemple: 00012."
                            + " Tampoc pot ser una lletra o un numero");
                }

            }

        }
        );
        //eliminarConductor
        view.getEliminarConductorButton().addActionListener(e -> {
            if (filaSelCond != -1) {
                TableColumnModel tcm = view.getJTaulaConductor().getColumnModel();
                tcm.addColumn(tcC);
                Conductor cond = (Conductor) view.getJTaulaConductor().getValueAt(filaSelCond, tcm.getColumnCount() - 1);
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

        //GUARDAR LES DADES A UN FITXER QUAN ES TANCA EL PROGRAMA 
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                //MODEL DE PROVA 2
                for (int i = 0; i < model.getData().size(); i++) {
                    try {
                        model.saveVehicle(nomArxiuV);
                        model.saveConductor(nomArxiuC);
                    } catch (IOException ex) {
                        System.out.println("Catch de excepcio de quan es crea el fitxer per primera vegada");
                    }
                }
            }
        });

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
