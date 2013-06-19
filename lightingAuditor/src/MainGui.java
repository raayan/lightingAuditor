import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainGui extends JFrame {

	private JPanel contentPane;
	private JTextField loc;
	private JTextField dateModified;
	private ArrayList<Light> light;
	private boolean returning = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		setResizable(false);
		setTitle("Lighting Auditor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 303, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		final JButton btnNewButton = new JButton("Create Light");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateLight createLight = new CreateLight();
				createLight.show();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		final JButton btnReturned = new JButton("Return Light");
		
		JLabel lblSelectLight = new JLabel("Select Light");
		lblSelectLight.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSelectLight = new GridBagConstraints();
		gbc_lblSelectLight.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSelectLight.gridwidth = 9;
		gbc_lblSelectLight.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectLight.gridx = 3;
		gbc_lblSelectLight.gridy = 1;
		contentPane.add(lblSelectLight, gbc_lblSelectLight);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				comboBox.removeAllItems();
				light = DatabaseMethods.getAllLights();
				int i = 0;
				for(Light x: light){
					comboBox.addItem(x);
				}
			}
		});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			       if (event.getStateChange() == ItemEvent.SELECTED) {
			          Light item = (Light) event.getItem();
			          dateModified.setText(item.getDate().toString());
			          if(comboBox.getSelectedItem() != null)
			        	  btnReturned.setEnabled(true);
			          else
			        	  btnReturned.setEnabled(false);

			          if(item.getLocation().equals("storage")){
			        	  loc.setText("storage");
			        	  loc.setEditable(true);
			        	  btnReturned.setText("Update Location");
			        	  returning = false;
			          }else{
			        	  loc.setText(item.getLocation());
			        	  loc.setEditable(false);
			        	  btnReturned.setText("Return Light");
			        	  returning = true;
			          }
			       }
			    }  
		});
		btnReturned.setEnabled(false);
		btnReturned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(returning)
					DatabaseMethods.returnToStorage(((Light) comboBox.getSelectedItem()).getID());
				else if(loc.getText().length() > 0){
					if(loc.getText().indexOf("\'") > 0){
						JOptionPane.showMessageDialog(null, "Location may not contain (\')");
						loc.setText("");
					}else{
						DatabaseMethods.updateLocation(((Light) comboBox.getSelectedItem()).getID(), loc.getText());
						JOptionPane.showMessageDialog(null, "Light " + ((Light) comboBox.getSelectedItem()).getID() + ":" + ((Light) comboBox.getSelectedItem()).getName() + " moved to "  + loc.getText());
					}
				}else{
					JOptionPane.showMessageDialog(null, "No location set!");
				}
				int j =comboBox.getSelectedIndex();
				comboBox.removeAllItems();
				light = DatabaseMethods.getAllLights();
				int i = 0;
				for(Light x: light){
					comboBox.addItem(x);
				}
				comboBox.setSelectedIndex(j);
				
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridwidth = 8;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		contentPane.add(comboBox, gbc_comboBox);
		
		JLabel lblLightPosition = new JLabel("Location");
		lblLightPosition.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblLightPosition = new GridBagConstraints();
		gbc_lblLightPosition.gridwidth = 9;
		gbc_lblLightPosition.insets = new Insets(0, 0, 5, 5);
		gbc_lblLightPosition.gridx = 3;
		gbc_lblLightPosition.gridy = 3;
		contentPane.add(lblLightPosition, gbc_lblLightPosition);
		
		loc = new JTextField();
		loc.setEditable(false);
		GridBagConstraints gbc_loc = new GridBagConstraints();
		gbc_loc.gridwidth = 9;
		gbc_loc.insets = new Insets(0, 0, 5, 5);
		gbc_loc.fill = GridBagConstraints.HORIZONTAL;
		gbc_loc.gridx = 3;
		gbc_loc.gridy = 4;
		contentPane.add(loc, gbc_loc);
		loc.setColumns(10);
		
		JLabel lblDatePlace = new JLabel("Date Modified");
		lblDatePlace.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDatePlace = new GridBagConstraints();
		gbc_lblDatePlace.gridwidth = 9;
		gbc_lblDatePlace.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatePlace.gridx = 3;
		gbc_lblDatePlace.gridy = 5;
		contentPane.add(lblDatePlace, gbc_lblDatePlace);
		
		dateModified = new JTextField();
		dateModified.setEditable(false);
		GridBagConstraints gbc_dateModified = new GridBagConstraints();
		gbc_dateModified.gridwidth = 9;
		gbc_dateModified.insets = new Insets(0, 0, 5, 5);
		gbc_dateModified.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateModified.gridx = 3;
		gbc_dateModified.gridy = 6;
		contentPane.add(dateModified, gbc_dateModified);
		dateModified.setColumns(10);
		
		
		GridBagConstraints gbc_btnReturned = new GridBagConstraints();
		gbc_btnReturned.insets = new Insets(0, 0, 5, 5);
		gbc_btnReturned.gridx = 5;
		gbc_btnReturned.gridy = 8;
		contentPane.add(btnReturned, gbc_btnReturned);
	}

}
