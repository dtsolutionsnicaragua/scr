package original;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;

import formulario.FrmRol;

public class EditorTreeRender extends DefaultTreeCellEditor {
	public JEditorMenu jem = new JEditorMenu();

	public EditorTreeRender(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
		this.jem = new JEditorMenu();
		// TODO Auto-generated constructor stub
	}

	public EditorTreeRender(JTree arg0, DefaultTreeCellRenderer arg1,TreeCellEditor arg2) {
		super(arg0, arg1, arg2);
		this.jem = new JEditorMenu();
		// TODO Auto-generated constructor stub
	}
	
    
   public Component getTreeCellEditorComponent(JTree tree, Object value,boolean isSelected, boolean expanded, boolean leaf, int row) {
	   super.getTreeCellEditorComponent(tree, value, isSelected,expanded, leaf, row);
	   DefaultMutableTreeNode jemplo  = (DefaultMutableTreeNode)value;
       JEditorMenu jem2 = ( (JEditorMenu)jemplo.getUserObject() );
       this.jem =jem2;
       this.jem.getLblValor().setIcon(new ImageIcon(FrmRol.class.getResource("/icon/general/"+ ((leaf)?"imgFormulario":"imgMenu") +".png")));
       if(!leaf){
       	this.jem.soloVer(true);
       	this.jem.cbxVer.setVisible(row!=0);
       	this.jem.validate();
       }     

       return this.jem;
   }

}
