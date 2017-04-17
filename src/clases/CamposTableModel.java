package clases;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class CamposTableModel extends AbstractTableModel {

    private ArrayList<Campo> campos;
    private String[] columns;
    int initialRowCount;
    private Object[][] data = {{null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null, null}
    };

    Class[] types = new Class[]{
        java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
    };
    boolean[] canEdit = new boolean[]{
        true, true, false, true, true, true, true, true, true
    };

    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    public CamposTableModel(ArrayList<Campo> aCamposList) {
        super();
        campos = aCamposList;
        columns = new String[]{"", "Campo", "Tipo", "Tamaño", "Atributo", "Tipo Atributo", "Relación", "Componente", "Etiqueta"};
        llenaGrid();

    }

    public CamposTableModel() {
        super();
        campos = new ArrayList<>();
        columns = new String[]{"", "Campo", "Tipo", "Tamaño", "Atributo", "Tipo Atributo", "Relación", "Componente", "Etiqueta"};
    }

    // Number of column of your table
    public int getColumnCount() {
        return columns.length;
    }

    // Number of row of your table
    public int getRowsCount() {
        return campos.size();
    }

    public void setValueAt(Object value, int row, int col) {
        try {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        } catch (Exception ex) {
        }
    }

    public void llenaGrid() {
        Iterator itr = campos.iterator();
        int fila = 0;
        while (itr.hasNext()) {
            Campo campo = (Campo) itr.next();
            setValueAt(campo.getSelected(), fila, 0);
            setValueAt(campo.getNombreCampo(), fila, 1);
            setValueAt(campo.getTipoCampo(), fila, 2);
            setValueAt(campo.getTamanno(), fila, 3);
            setValueAt(campo.getNombreAtributo(), fila, 4);
            setValueAt(campo.getTipoAtributo(), fila, 5);
            setValueAt(campo.getTipoRelacion(), fila, 6);
            setValueAt(campo.getComponente(), fila, 7);
            setValueAt(campo.getEtiqueta(), fila, 8);
            fila += 1;
        }

    }

    // The object to render in a cell
    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    // Optional, the name of your column
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public int getRowCount() {
        return campos.size();
    }

    public void addRow() {
        initialRowCount++;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

}
