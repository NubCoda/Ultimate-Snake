package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import Model.Interface.IActor;

@SuppressWarnings("serial")
public class Statusbar extends JPanel implements Observer {

	public Statusbar() {
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
		setLayout(new GridLayout());
	}

	public void addStatusLabel(StatusLabelView statusLabel) {
		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setPreferredSize(new Dimension(1, 15));
		add(statusLabel);
		add(separator);
	}
	
	@Override
	public void update(Observable observable, Object object) {
		repaint();
	}
}