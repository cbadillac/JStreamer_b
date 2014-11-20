import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;


public class Client_GUI extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private int option  = 0; // 0: Audio, 1: Webcam, 2: Video
	private String ip   = "127.0.0.1";
	private String port = "5000";
	

	public Client_GUI() {
		
		
		setTitle("JStreamer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		final JList list = new JList();
		list.setVisibleRowCount(3);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Audio", "WebCam", "Video"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(251, 40, 76, 65);
		getContentPane().add(list);
		
		JLabel lblSelectStreamingOptions = new JLabel("Select Streaming Options:");
		lblSelectStreamingOptions.setBounds(28, 41, 192, 15);
		getContentPane().add(lblSelectStreamingOptions);
		
		JLabel lblSetIpRemote = new JLabel("Set IP Remote device:");
		lblSetIpRemote.setBounds(28, 129, 175, 15);
		getContentPane().add(lblSetIpRemote);
		
		textField = new JTextField();
		textField.setBounds(251, 127, 114, 19);
		textField.setText(ip);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPortInThe = new JLabel("Port in the remote device:");
		lblPortInThe.setBounds(28, 175, 204, 15);
		getContentPane().add(lblPortInThe);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(251, 173, 114, 19);
		textField_1.setText(port);
		getContentPane().add(textField_1);
		
		Button button = new Button("next");
		button.setBounds(319, 223, 86, 23);
		getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Next");
				option = list.getSelectedIndex();
				ip     = textField.getText();
				port   = textField_1.getText();
				SourceSelectionGUI sourceSelectionGUI = new SourceSelectionGUI(option, ip, port);
				sourceSelectionGUI.setDefaultCloseOperation(sourceSelectionGUI.HIDE_ON_CLOSE);
				sourceSelectionGUI.setVisible(true);
			}
		});
	}
}
