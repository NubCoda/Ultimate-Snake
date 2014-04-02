package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class OptionView extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel labelResolution;
	private JComboBox<String> comboBoxResolution;
	private JButton buttonCancel;
	private JButton buttonOk;
	private MainView mainView;
	private int newWidth;
	private int newHeight;

	public OptionView(MainView mainView) {
		this.mainView = mainView;
		this.newWidth = mainView.getWidth();
		this.newHeight = mainView.getHeight();
		initGUI();
	}

	/**
	 * Create the dialog.
	 */
	public OptionView() {
		initGUI();
	}

	private void initGUI() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			labelResolution = new JLabel("Aufl\u00F6sung:");
			labelResolution.setBounds(10, 11, 62, 14);
			contentPanel.add(labelResolution);
		}
		{
			comboBoxResolution = new JComboBox<String>();
			comboBoxResolution.addActionListener(this);
			comboBoxResolution.setModel(new DefaultComboBoxModel<String>(
					new String[] { "800x600", "1024x768", "1280x720" }));
			comboBoxResolution.setBounds(82, 7, 102, 22);
			contentPanel.add(comboBoxResolution);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonOk = new JButton("OK");
				buttonOk.addActionListener(this);
				buttonOk.setActionCommand("OK");
				buttonPane.add(buttonOk);
				getRootPane().setDefaultButton(buttonOk);
			}
			{
				buttonCancel = new JButton("Abbrechen");
				buttonCancel.addActionListener(this);
				buttonCancel.setActionCommand("Cancel");
				buttonPane.add(buttonCancel);
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == buttonOk) {
			buttonOkActionPerformed(arg0);
		}
		if (arg0.getSource() == buttonCancel) {
			buttonCancelActionPerformed(arg0);
		}
		if (arg0.getSource() == comboBoxResolution) {
			comboBoxResolutionActionPerformed(arg0);
		}
	}

	protected void comboBoxResolutionActionPerformed(ActionEvent arg0) {
		String selectedItem = comboBoxResolution.getSelectedItem().toString();
		int positionOfMulti = selectedItem.indexOf("x");
		newWidth = Integer.valueOf(selectedItem.substring(0, positionOfMulti));
		newHeight = Integer.valueOf(selectedItem.substring(positionOfMulti + 1,
				selectedItem.length()));

	}

	protected void buttonCancelActionPerformed(ActionEvent arg0) {
		this.dispose();
	}

	protected void buttonOkActionPerformed(ActionEvent arg0) {
		mainView.setSize(newWidth, newHeight);
		mainView.setLocationRelativeTo(null);
		this.dispose();
	}
}
