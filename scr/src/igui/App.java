package igui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import mvc.SPool;

public class App {
	public static SPool spoolconexion = new SPool();
	public static SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
	public static java.awt.Font fuentePrincipal =new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
	public final static Cursor busyCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	public final static Cursor defaultCursor = Cursor.getDefaultCursor();
	public final static Color BackgroudForm =new Color(47, 79, 79); // SystemColor.controlDkShadow;
	//public final static Color BackgroudShadowTable =
	public final static Border borderActivo = new MatteBorder(1, 5, 1, 1, (Color) Color.RED);//new MatteBorder(1, 3, 1, 1, (Color) Color.RED); //BorderFactory.createLineBorder(Color.BLUE, 5);
	public final static Border borderDesactivo = new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null);
	public final static Boolean mivalor = false;
}
