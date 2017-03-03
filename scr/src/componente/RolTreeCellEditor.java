package componente;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.CellEditor;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;

import formulario.FrmRol;

public class RolTreeCellEditor implements TreeCellEditor {
	EditorRol nodo ;
	CellEditor EditorActual;
	
	public RolTreeCellEditor() {
		nodo = new EditorRol();
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,boolean isSelected, boolean expanded, boolean leaf, int row) {
		  DefaultMutableTreeNode jemplo  = (DefaultMutableTreeNode)value;
		  EditorRol jem2 = ( (EditorRol)jemplo.getUserObject() );
		  nodo = jem2;
	       this.nodo.getLblValor().setIcon(new ImageIcon(FrmRol.class.getResource("/icon/general/"+ ((leaf)?"imgFormulario":"imgMenu") +".png")));
	      /* if(!leaf){
	       	this.nodo.soloVer(true);
	       	this.nodo.cbxVer.setVisible(row!=0);
	       	this.nodo.validate();
	       }*/
		EditorActual = nodo;
		return (Component) EditorActual;
	}
	

	@Override
	public void cancelCellEditing() {
		EditorActual.cancelCellEditing();
		
	}

	@Override
	public Object getCellEditorValue() {
		return EditorActual.getCellEditorValue();
	}

	@Override
	public boolean isCellEditable(EventObject arg0) {
		return true;
	}


	@Override
	public boolean shouldSelectCell(EventObject event) {
		return EditorActual.shouldSelectCell(event);
	}

	@Override
	public boolean stopCellEditing() {
		return EditorActual.stopCellEditing();
	}
	

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		nodo.addCellEditorListener(l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		nodo.addCellEditorListener(l);
	}

}
