package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import Model.StatusbarModel;
import Model.Interface.IActor;

@SuppressWarnings("serial")
public class Statusbar extends JPanel implements Observer {
	private Vector<JLabel> labelVector;

	public Statusbar() {
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
		labelVector = new Vector<JLabel>();
	}
	
	public void addLabelToVector(JLabel jLabel) {
		labelVector.add(jLabel);
	}

	public void addLabels() {
		for(int i = 0; i < labelVector.size(); i++) {
			add(labelVector.elementAt(i));
			if(i < labelVector.size() - 1) {
				System.out.println("Addet");
				JSeparator separator = new JSeparator(JSeparator.VERTICAL);
				separator.setPreferredSize(new Dimension(1, 15));
				add(separator);
			}
		}
	}

	@Override
	public void update(Observable observable, Object object) {
		StatusbarModel statusbarModel = ((StatusbarModel) observable);
		statusbarModel.repaintElements();
		repaint();
	}
}