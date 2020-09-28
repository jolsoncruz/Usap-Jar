import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import java.awt.TextField;
import javax.swing.JTextField;

public class USAP extends JFrame {

	private JPanel contentPane;
	private JTextField input_name;
	private JTextField input_port;
	private JLayeredPane sessions;
	private JPanel pre;
	private JPanel actual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					USAP frame = new USAP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void switchPanels(JPanel panel) {
		sessions.removeAll();
		sessions.add(panel);
		sessions.repaint();
		sessions.revalidate();
	}

	/**
	 * Create the frame.
	 */
	public USAP() {
		setTitle("De La Salle Usap (S13 Cruz & Santos)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setResizable(false);
		
		JMenuBar mnBar = new JMenuBar();
		setJMenuBar(mnBar);
		
		JMenu mn_file = new JMenu("File");
		mnBar.add(mn_file);
		
		JMenuItem mnitem_end = new JMenuItem("End Session");
		mn_file.add(mnitem_end);
		
		JMenu mn_help = new JMenu("Help");
		mnBar.add(mn_help);
		
		JMenuItem mnitem_about = new JMenuItem("About");
		mn_help.add(mnitem_about);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		sessions = new JLayeredPane();
		contentPane.add(sessions, "name_110001059263100");
		sessions.setLayout(new CardLayout(0, 0));
		
		pre = new JPanel();
		sessions.add(pre, "name_110900934946000");
		pre.setLayout(null);
		
		JLabel img_box = new JLabel("Image");
		img_box.setBounds(189, 24, 200, 200);
		pre.add(img_box);
		Image logo1 = new ImageIcon(this.getClass().getResource("/usap.png")).getImage();
		img_box.setIcon(new ImageIcon(logo1));
		
		JLabel img_welcome = new JLabel("");
		img_welcome.setBounds(39, 248, 500, 100);
		pre.add(img_welcome);
		Image welcome = new ImageIcon(this.getClass().getResource("/welcome.png")).getImage();
		img_welcome.setIcon(new ImageIcon(welcome));
		
		input_name = new JTextField();
		input_name.setFont(new Font("Avenir LT Std 65 Medium", Font.BOLD, 12));
		input_name.setForeground(new Color(255, 255, 255));
		input_name.setColumns(10);
		input_name.setBounds(283, 369, 139, 20);
		input_name.setOpaque(false);
		input_name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pre.add(input_name);
		
		JLabel img_name = new JLabel("img_name");
		img_name.setBounds(139, 359, 300, 40);
		pre.add(img_name);
		Image name = new ImageIcon(this.getClass().getResource("/name.png")).getImage();
		img_name.setIcon(new ImageIcon(name));
		
		input_port = new JTextField();
		input_port.setFont(new Font("Avenir LT Std 65 Medium", Font.BOLD, 12));
		input_port.setForeground(new Color(255, 255, 255));
		input_port.setOpaque(false);
		input_port.setColumns(10);
		input_port.setBounds(283, 410, 139, 20);
		input_port.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pre.add(input_port);
		
		JLabel img_port = new JLabel("img_port");
		img_port.setBounds(139, 399, 300, 40);
		pre.add(img_port);
		Image port = new ImageIcon(this.getClass().getResource("/port.png")).getImage();
		img_port.setIcon(new ImageIcon(port));
		
		JButton btn_logIn = new JButton("Log In");
		btn_logIn.setBounds(149, 460, 290, 48);
		pre.add(btn_logIn);
		
		actual = new JPanel();
		actual.setLayout(null);
		sessions.add(actual, "name_110039798810700");
		
		JLabel img_logo = new JLabel("");
		img_logo.setBounds(33, 11, 524, 76);
		actual.add(img_logo);
		Image logo2 = new ImageIcon(this.getClass().getResource("/banner.png")).getImage();
		img_logo.setIcon(new ImageIcon(logo2));
		
		JLabel text_connected = new JLabel("Hello, Archer! You are connected to:");
		text_connected.setFont(new Font("Avenir LT Std 55 Roman", Font.BOLD, 13));
		text_connected.setBounds(33, 104, 238, 23);
		actual.add(text_connected);
		
		JPanel div_address = new JPanel();
		div_address.setBackground(new Color(0, 128, 128));
		div_address.setBounds(281, 103, 142, 21);
		actual.add(div_address);
		
		JLabel text_port = new JLabel(input_port.getText());
		text_port.setForeground(Color.WHITE);
		text_port.setFont(new Font("Avenir LT Std 65 Medium", Font.BOLD, 11));
		div_address.add(text_port);
		
		JButton btn_logOut = new JButton("Log Out");
		btn_logOut.setBounds(445, 103, 113, 21);
		actual.add(btn_logOut);
		
		JScrollPane div_messages = new JScrollPane();
		div_messages.setBounds(33, 135, 390, 267);
		actual.add(div_messages);
		
		JTextArea text_messages = new JTextArea();
		text_messages.setEditable(false);
		text_messages.setLineWrap(true);
		text_messages.setWrapStyleWord(true);
		text_messages.setText("[BOT] Welcome, Lasallians! This is the beginning of your conversation at port: ! Please observe proper decorum at all times when communicating via DLSU USAP.");
		div_messages.setViewportView(text_messages);
		
		JScrollPane div_users = new JScrollPane();
		div_users.setBounds(445, 135, 113, 219);
		actual.add(div_users);
		
		JTextArea text_users = new JTextArea();
		text_users.setLineWrap(true);
		text_users.setWrapStyleWord(true);
		text_users.setText("Connected Users:\r\n\r\n");
		div_users.setViewportView(text_users);
		
		JButton btn_log = new JButton("Download Log");
		btn_log.setBounds(444, 365, 113, 37);
		actual.add(btn_log);
		
		JSeparator hb1 = new JSeparator();
		hb1.setBounds(33, 416, 524, 15);
		actual.add(hb1);
		
		JTextArea input_message = new JTextArea();
		input_message.setBounds(33, 431, 390, 89);
		actual.add(input_message);
		
		JButton btn_upload = new JButton("Upload Image");
		btn_upload.setBounds(445, 431, 113, 37);
		actual.add(btn_upload);
		
		JButton btn_send = new JButton("Send Message");
		btn_send.setBounds(445, 483, 113, 37);
		actual.add(btn_send);
		
		//ACTION LISTENERS
		mnitem_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html><center>Software Developer: Joeme Santos<br>GUI Developer: Jolson Cruz<br><br>In partial completion of CSNETWK<br>Submitted to: Mr. Marnel Peradilla.</html>");
			}
		});
		
		btn_logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!input_name.getText().equals("") && !input_port.getText().equals("")){
					text_connected.setText("Hello, " + input_name.getText() + "! You are connected to:");
					text_port.setText(input_port.getText());
					text_users.setText(text_users.getText() + input_name.getText() + "\r\n"); 
					switchPanels(actual);	
				} else {
					JOptionPane.showMessageDialog(null, "<html><center>All fields are required! Please try again.</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btn_logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(pre);
				JOptionPane.showMessageDialog(null, "<html><center>You have succesfully logged out.</html>");
			}
		});
		
		btn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!input_message.getText().equals("")){
					text_messages.setText(text_messages.getText() + "\r\n\r\n[" + input_name.getText() + "] " + input_message.getText() + " ");
					input_message.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "<html><center>Message field is empty! Please try again.</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	
	}
}
