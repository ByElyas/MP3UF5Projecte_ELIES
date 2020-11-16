/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilscontroller;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author profe
 */
//Classe ModelCanvisBD usada en la BDR mySQL. Se li hauria de fer algun retoc per fer-la genèrica
//Classse filla de DefaultTableModel que conté un Listener per automàticament actualitzar a la BD els canvis fets a una jTable
public class ModelCanvisBD extends DefaultTableModel {
    
    private ResultSet resultSet = null;
    private int columnaID;
    private boolean error=false;    //Per saber si el canvi ha provocat un error

    //El paràmetre ResultSet rs ha de ser el que hem usat per extreure les dades mostrades a la jTable, ha de ser del tipus actualitzable (CONCUR_UPDATABLE) 
    //sinó provoca una excepció i ha d'estar obert, tant ell com l'statement que el genera
    //Exemple:
    //statement = JFramePrincipal.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    //resultSet = statement.executeQuery(sql);    

    //El paràmetre colID indica quina columna del DefaultTableModel conté l'identificador de la fila de la taula
    public ModelCanvisBD(Vector data, Vector columnNames, ResultSet rs, int colID) {
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

                try {
                    int id = (Integer) model.getValueAt(row, columnaID);
                    resultSet.beforeFirst();
                    while (resultSet.next() && resultSet.getInt(columnaID+1) != id);
                    resultSet.updateObject(column + 1, data);
                    resultSet.updateRow();
                } catch (Exception ex) {
                    //Hi ha hagut una excepció, per tant un error 
                    error=true;
                }
            }
        }
        );

    }
    
    //Actualitzem la fila que ha estat modificada passant el ResultSet obtingut 
    //al consultar les dades
    public ModelCanvisBD(Vector data, Vector columnNames, ResultSet rs) {
        super(data, columnNames);
        resultSet = rs;
        //El que s'ha de fer és afegir un ModelListener al TableModel de la 
        //JTable que reaccione als canvis. Ho aconseguim implementant el mètode 
        //tableChanged()
        this.addTableModelListener(new TableModelListener() {
            
            @Override
            public void tableChanged(TableModelEvent e) {
                
                //Obtenim l'objecte contingut a la casella canviada
                
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                Object data = model.getValueAt(row, column);
                try {
                    //Ens desplacem a la fila clicada (+1 ja quew al ResultSet 
                    //comencen en la 1, no en la 0 com al TableModel)
                    resultSet.absolute(row+1);
                    //Actualitzem la columna (ara li hem de sumar 1 a la columna 
                    //retornada pel TableModel)
                    resultSet.updateObject(column + 1, data);
                    resultSet.updateRow();
                } catch (Exception ex) {
                    //Hi ha hagut una excepció, per tant un error 
                    error=true;
                }
            }
        }
        );

    }

    public boolean isError() {
        return error;
    }

}
 
