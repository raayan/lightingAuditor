import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JLabel;


public class CreateLight extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField loc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateLight dialog = new CreateLight();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateLight() {
		setResizable(false);
		setTitle("Light Creator");
		setBounds(100, 100, 360, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblLightName = new JLabel("Light Name");
			GridBagConstraints gbc_lblLightName = new GridBagConstraints();
			gbc_lblLightName.gridwidth = 2;
			gbc_lblLightName.insets = new Insets(0, 0, 5, 5);
			gbc_lblLightName.gridx = 2;
			gbc_lblLightName.gridy = 0;
			contentPanel.add(lblLightName, gbc_lblLightName);
		}
		{
		}
		JButton btnNewButton = new JButton("Create Light");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(name.getText().length() > 0){
					String locat = "";
					if(loc.getText().indexOf("\'") > 0){
						JOptionPane.showMessageDialog(null, "Location may not contain (\')");
						loc.setText("");
					}else{
						if(loc.getText().length() > 0){
							DatabaseMethods.CreateLight(name.getText(), loc.getText());
							locat =  "at " + loc.getText();
						}else{
							DatabaseMethods.CreateLight(name.getText(), "storage");
							locat = "in storage";
						}
						JOptionPane.showMessageDialog(null, "Light (" + name.getText() + ") "  + locat);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Name is empty!");
				}
			}
		});
		{
			name = new JTextField();
			GridBagConstraints gbc_name = new GridBagConstraints();
			gbc_name.gridwidth = 2;
			gbc_name.insets = new Insets(0, 0, 5, 5);
			gbc_name.fill = GridBagConstraints.HORIZONTAL;
			gbc_name.gridx = 2;
			gbc_name.gridy = 1;
			contentPanel.add(name, gbc_name);
			name.setColumns(10);
		}
		{
			JLabel lblLocation = new JLabel("Location");
			GridBagConstraints gbc_lblLocation = new GridBagConstraints();
			gbc_lblLocation.gridwidth = 2;
			gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
			gbc_lblLocation.gridx = 2;
			gbc_lblLocation.gridy = 2;
			contentPanel.add(lblLocation, gbc_lblLocation);
		}
		{
			loc = new JTextField();
			loc.setColumns(10);
			GridBagConstraints gbc_loc = new GridBagConstraints();
			gbc_loc.gridwidth = 2;
			gbc_loc.insets = new Insets(0, 0, 5, 5);
			gbc_loc.fill = GridBagConstraints.HORIZONTAL;
			gbc_loc.gridx = 2;
			gbc_loc.gridy = 3;
			contentPanel.add(loc, gbc_loc);
		}
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 4;
		contentPanel.add(btnNewButton, gbc_btnNewButton);
	}

}
