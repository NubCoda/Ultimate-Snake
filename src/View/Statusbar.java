package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;

public class Statusbar extends JPanel {
	private int statusCount = 0;
	public Statusbar() {
//		setPreferredSize(new Dimension(100, 25));
//		SpringLayout layout = new SpringLayout();
//		layout.
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
		setLayout(new GridLayout());
	}

	public void addStatusLabel(StatusLabelView statusLabel) {
		if(statusCount%2==1){
			JSeparator separator = new JSeparator(JSeparator.VERTICAL);
			separator.setPreferredSize(new Dimension(1, 15));
			add(separator);
		}
		add(statusLabel);
		statusCount++;
	}
}