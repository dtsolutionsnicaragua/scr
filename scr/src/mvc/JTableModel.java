package mvc;

import igui.App;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class JTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private ResultSet resultSetFilas;

	public String ConsultaSQL;

	private String[] ColumnName;
	private String[] ColumnaClass;

	private Object[][] valores;
	private boolean[][] editables;

	private int RowCount = 0;
	private int ColumnCount = 0;

	public JTableModel() {
		this.ConsultaSQL = "";
		ActualizarDatos();
	}

	public JTableModel(String ConsultaSQL) {
		this.ConsultaSQL = ConsultaSQL;
		ActualizarDatos();
	}

	public void limpiarDatos() {
		RowCount = 0;
		ColumnCount = 0;
		valores = null;
		ColumnName = null;
		ColumnaClass = null;
		valores = null;
		ColumnName = null;
		 this.fireTableDataChanged();
		 this.fireTableStructureChanged();
	}

	public void ActualizarDatos() {

		limpiarDatos(); // SE ELIMINAN RASTROS DE CONSULTA ANTERIOR

		if (ConsultaSQL.length() > 0) { // SI TIENE UNA CONSULTA QUE CARGAR
			try {
				Connection nuevaConeccion = App.spoolconexion.DataSource
						.getConnection();
				resultSetFilas = nuevaConeccion.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY).executeQuery(ConsultaSQL);
				getDAO(resultSetFilas);
				resultSetFilas.close();
				nuevaConeccion.close();
			} catch (Exception e) {
				System.out.print("Error en Actualizar Datos" + e.getMessage()
						+ e.getStackTrace());
			}
		} else {
			System.out.print("Consulta Vacia");
		}
		fireTableStructureChanged();

	}

	private void getDAO(ResultSet modelo) throws SQLException {
		ColumnName = new String[modelo.getMetaData().getColumnCount()];
		ColumnaClass = new String[ColumnName.length];

		ColumnCount = ColumnName.length;// CANTIDAD DE COLUMNAS;

		for (int i = 0; i < ColumnName.length; i++) {
			ColumnName[i] = modelo.getMetaData().getColumnLabel(i + 1);
			ColumnaClass[i] = modelo.getMetaData().getColumnClassName(i + 1);
		}

		modelo.last(); // MOVEMOS EL CURSOS HASTA EL FINAL
		RowCount = modelo.getRow(); // CANTIDAD DE FILAS;
		valores = new Object[RowCount][ColumnCount];
		editables = new boolean[RowCount][ColumnCount];

		modelo.beforeFirst();

		int iterador = 0;
		while (modelo.next()) {
			for (int c = 0; c < ColumnCount; c++) {
				valores[iterador][c] = resultSetFilas.getObject(c + 1);
				// fireTableRowsUpdated(c, ColumnCount);
				editables[iterador][c] = false;
			}
			iterador += 1;
		}
		// fireTableDataChanged();
	}

	public boolean isCellEditable(int row, int col) {
		return editables[row][col];
	}

	public void setCellEditable(int row, int col, boolean value) {
		editables[row][col] = value;
	}

	public String getColumnName(int columna) {
		return ColumnName[columna];
	}

	public int getColumnCount() {
		return ColumnCount;
	}

	public int getRowCount() {
		return RowCount;
	}

	public Object getValueAt(int indiceFila, int indiceColumna) {
		try{
			return valores[indiceFila][indiceColumna];	
		}catch(Exception e){
			return new Object();
		}
		
	}

	public void setValueAt(Object value, int indiceFila, int indiceColumna) {
		valores[indiceFila][indiceColumna] = value;
		fireTableCellUpdated(indiceFila, indiceColumna);
	}

	public Class<?> getColumnClass(int indiceColumna) {
		try {
			// devolver objeto Class que representa a nombreClase
			return Class.forName(ColumnaClass[indiceColumna]);
		} catch (Exception excepcion) {
			// SQLException y ClassNotFoundException
			return Object.class;
		}
	}
}