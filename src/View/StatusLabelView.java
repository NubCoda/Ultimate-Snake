package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StatusLabelView extends JLabel implements Observer {
	public StatusLabelView(String text) {
		super(text);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub	
	}

}
