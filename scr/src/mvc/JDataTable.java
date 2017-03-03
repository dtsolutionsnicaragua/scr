package mvc;

import igui.App;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import mvc.JTableModel;

public class JDataTable extends JTable {

	private static final long serialVersionUID = 1L;
	private static int ALTURA=25;
	public JTableModel DAOModelo =new JTableModel();
	public ArrayList<ArrayList<Integer>> AnchoColumna = new ArrayList<ArrayList<Integer>>();
	
	public JDataTable(){
		setFont(App.fuentePrincipal);
		setModel(DAOModelo);
		_setDefaultConfiguracion();
		getTableHeader().setFont(App.fuentePrincipal);
	}
		
	public JDataTable(String ConsultaSQL){
		setFont(App.fuentePrincipal);
		DAOModelo.ConsultaSQL = ConsultaSQL;
		DAOModelo.ActualizarDatos();
		setModel(DAOModelo);
		_setDefaultConfiguracion();	
		getTableHeader().setFont(App.fuentePrincipal);
	
	}
	
	public JDataTable(TableModel modelo ){
		setFont(App.fuentePrincipal);
		setModel(modelo);
		_setDefaultConfiguracion();
		getTableHeader().setFont(App.fuentePrincipal);
	}
	
	
	/*public void _addModel(String ConsultaSQL){		
		columnas.clear();
		modelo.ConsultaSQL = ConsultaSQL;
		modelo.obtenerDatos();
	}
	
	public void _updateModel(String Consulta) {
		modelo.ConsultaSQL = Consulta;
		modelo.obtenerDatos(); 
	}*/
	
	public void limpiarTabla(){		
		DAOModelo.limpiarDatos();
		AnchoColumna.clear();
	}
	
	public void _actualizar() {
		DAOModelo.ActualizarDatos();
				
		for (int i = 0 ; i<AnchoColumna.size(); i++) //columnas.size()
			_setPropiedadAnchoColumna(i);	
		  
	}
	
	public void _colorEncabezado(Color HeaderBackgroundColor, Font fuente, Color HeaderForegroundColor){
		this.getTableHeader().setBackground(HeaderBackgroundColor);
		this.getTableHeader().setFont(fuente);
		this.getTableHeader().setForeground(HeaderForegroundColor);	
	}
	
	public void _setSelectionBackgroundColor(Color color){
		this.setSelectionBackground(color);
	}	
	
	public void _setSelectionForeColor(Color color){
		this.setSelectionForeground(color);
	}
	
	public void _setDefaultConfiguracion(){		
		this.getTableHeader().setResizingAllowed(true);
		this.getTableHeader().setReorderingAllowed(false);
		this.setShowGrid(true);
		this.setRowSelectionAllowed(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,false), "selectNextColumn"); 
	//	_setHeaderColorConfiguration(ConfigApp.colorEtiqueta, ConfigApp.fuente,ConfigApp.color);
	//	_setSelectionBackgroundColor(ConfigApp.colorFilaSeleccionada);
	//	_setSelectionForeColor(ConfigApp.colorEtiqueta);
		_setAltura(ALTURA);		
	}
		
	public void _setAltura(int Altura){	
		ALTURA = (Altura<=0)? 25:Altura;
		this.setRowHeight(ALTURA);
	}	
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int fila, int Columna) {
			Component componenteModificado = super.prepareRenderer(renderer, fila, Columna);
	        Color ColorPrimario = new Color(219,229,241);//App.BackgroudForm;//new Color(219,229,241);//219,229,241
	        Color ColorSecundario = Color.WHITE;
	        
	        if (!componenteModificado.getBackground().equals(getSelectionBackground())){
	            Color bg = (fila % 2 == 0 ?   ColorSecundario : ColorPrimario);
	            componenteModificado .setBackground(bg);
	            bg = null;
	        }

	        return componenteModificado;
	}
		
	public void _setAnchoColumna(int indicecolumna, int anchocolumna){
		ArrayList<Integer>  valor = new ArrayList<Integer>();
		valor.add(indicecolumna);
		valor.add(anchocolumna);
		AnchoColumna.add(valor);		
		_setPropiedadAnchoColumna(AnchoColumna.size()-1);
	}
	
	public void repintarTabla(){
		for (int i = 0 ; i<AnchoColumna.size(); i++) //columnas.size()
			_setPropiedadAnchoColumna(i);
	}
	
	private void _setPropiedadAnchoColumna(int posicion){
		if (AnchoColumna.get(posicion).get(0)< this.getColumnCount()){
			this.getColumnModel().getColumn(AnchoColumna.get(posicion).get(0)).setMinWidth(AnchoColumna.get(posicion).get(1));
			this.getColumnModel().getColumn(AnchoColumna.get(posicion).get(0)).setPreferredWidth(AnchoColumna.get(posicion).get(1));
			this.getColumnModel().getColumn(AnchoColumna.get(posicion).get(0)).setMaxWidth(AnchoColumna.get(posicion).get(1));
			this.getColumnModel().getColumn(AnchoColumna.get(posicion).get(0)).setResizable(true);
		}		
	}
	
	public boolean _sonValoresUnicos(int indiceColumna) {
	boolean respuesta=true;
		iteracion1:
		for(int i=0;i<this.getModel().getRowCount(); i++){
			for(int t=0;t<this.getModel().getRowCount(); t++){
				if(i!=t)
					if(this.getModel().getValueAt(i, indiceColumna).toString().equals(this.getModel().getValueAt(t, indiceColumna).toString())){
						respuesta = false;
						break iteracion1;
					}						
			}		
		}
		return respuesta;
	}
	
	public boolean _buscarValor(int indiceColumna,String valor) {
	boolean respuesta=false;
	
		iteracion1:
		for(int i=0;i<this.getModel().getRowCount(); i++){			
			if(this.getModel().getValueAt(i, indiceColumna).toString().equals(valor)){
				respuesta = true;
				break iteracion1;
			}		
		}
	
		return respuesta;
	}	
}
