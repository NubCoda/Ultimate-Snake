package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class Statusbar extends JPanel {

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
}