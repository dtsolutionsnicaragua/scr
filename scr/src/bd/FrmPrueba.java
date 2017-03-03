package bd;

import javax.swing.JPanel;

import componente.JInternalToolBarMenu;

import java.awt.BorderLayout;

public class FrmPrueba extends JPanel {

	private static final long serialVersionUID = -3113839279363298748L;
	private JInternalToolBarMenu internalToolBarMenu;

	/**
	 * Create the panel.
	 */
	public FrmPrueba() {

		initComponents();
	}
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		add(getInternalToolBarMenu(), BorderLayout.NORTH);
	}

	public JInternalToolBarMenu getInternalToolBarMenu() {
		if (internalToolBarMenu == null) {
			internalToolBarMenu = new JInternalToolBarMenu();
			internalToolBarMenu.getBtnGuardar().setVisible(true);
			internalToolBarMenu.getBtnActivar().setVisible(true);
		}
		return internalToolBarMenu;
	}
}
