/**
 * 
 */
package componente;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import original.JEditorMenu;
import formulario.FrmRol;

/**
 * @author LP-Desarrollo
 *
 */
public class VisorTreeRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;
	public JEditorMenu jem = new JEditorMenu();

    public VisorTreeRenderer(JEditorMenu jEditorMenu){
    	this.jem = jEditorMenu;
    }
     
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus){   
     super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
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
