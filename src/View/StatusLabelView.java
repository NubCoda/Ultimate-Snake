package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StatusLabelView extends JLabel implements Observer {
	public StatusLabelView() {
	}

	@Override
	public void update(Observable observable, Object object) {

	}
}
