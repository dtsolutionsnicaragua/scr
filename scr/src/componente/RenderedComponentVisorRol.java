/**
 * 
 */
package componente;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

import formulario.FrmRol;

/**
 * @author LP-Desarrollo
 *
 */
public class RenderedComponentVisorRol implements TreeCellRenderer {
	
	public RenderedComponentVisorRol() {
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus){
		DefaultMutableTreeNode jemplo  = (DefaultMutableTreeNode)value;
        EditorRol nodoActual = ( (EditorRol)jemplo.getUserObject() );
        nodoActual.getLblValor().setIcon(new ImageIcon(FrmRol.class.getResource("/icon/general/"+ ((leaf)?"imgFormulario":"imgMenu") +".png")));
        /*if(!leaf){
        	//nodoActual.soloVer(true);
        	//nodoActual.cbxVer.setVisible(row!=0);
        	nodoActual.validate();
        }*/
        
		return nodoActual;
	}
}
