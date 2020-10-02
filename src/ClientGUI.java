import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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

import java.sql.Timestamp;

public class ClientGUI extends JFrame {
	static Socket s;
	static DataInputStream dis;
	static DataOutputStream dos;
	
	List<String> connectedUsers = new ArrayList<String>();
	List<String> transcript = new ArrayList<String>();
	String message = new String();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			String msgin = "";
			
			s = new Socket("127.0.0.1", 1201);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			
			while(true){
				msgin = dis.readUTF();
				text_messages.setText(text_messages.getText() + "\n\n" + msgin);
			}
		} catch(Exception e1) {
			
		}
	}
	
	public void switchPanels(JPanel panel) {
		sessions.removeAll();
		sessions.add(panel);
		sessions.repaint();
		sessions.revalidate();
	}
	
	public Timestamp timeNow() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

	/**
	 * Create the frame.
	 */
	public ClientGUI() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("De La Salle Usap (CLIENT)");
		setBounds(100, 100, 600, 600);
		setResizable(false);
		
		JMenuBar mnBar = new JMenuBar();
		setJMenuBar(mnBar);
		
		JMenu mn_file = new JMenu("File");
		mnBar.add(mn_file);
		
		JMenuItem mnitem_quit = new JMenuItem("Quit App");
		mn_file.add(mnitem_quit);
		
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
		Image logo1 = new ImageIcon(this.getClass().getResource("usap.png")).getImage();
		img_box.setIcon(new ImageIcon(logo1));
		
		JLabel img_welcome = new JLabel("");
		img_welcome.setBounds(39, 248, 500, 100);
		pre.add(img_welcome);
		Image welcome = new ImageIcon(this.getClass().getResource("join.png")).getImage();
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
		Image name = new ImageIcon(this.getClass().getResource("name.png")).getImage();
		img_name.setIcon(new ImageIcon(name));
		
		input_ip = new JTextField();
		input_ip.setFont(new Font("Avenir LT Std 65 Medium", Font.BOLD, 12));
		input_ip.setForeground(new Color(255, 255, 255));
		input_ip.setOpaque(false);
		input_ip.setColumns(10);
		input_ip.setBounds(283, 410, 139, 20);
		input_ip.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pre.add(input_ip);
		
		JLabel img_ip = new JLabel("img_port");
		img_ip.setBounds(139, 399, 300, 40);
		pre.add(img_ip);
		Image port = new ImageIcon(this.getClass().getResource("IP.png")).getImage();
		img_ip.setIcon(new ImageIcon(port));
		
		JButton btn_logIn = new JButton("Log In");
		btn_logIn.setBounds(149, 460, 290, 48);
		pre.add(btn_logIn);
		
		actual = new JPanel();
		actual.setLayout(null);
		sessions.add(actual, "name_110039798810700");
		
		JLabel img_logo = new JLabel("");
		img_logo.setBounds(33, 11, 524, 76);
		actual.add(img_logo);
		Image logo2 = new ImageIcon(this.getClass().getResource("banner.png")).getImage();
		img_logo.setIcon(new ImageIcon(logo2));
		
		JLabel text_connected = new JLabel("Hello, Archer! You are connected to:");
		text_connected.setFont(new Font("Avenir LT Std 55 Roman", Font.BOLD, 13));
		text_connected.setBounds(33, 104, 238, 23);
		actual.add(text_connected);
		
		JPanel div_address = new JPanel();
		div_address.setBackground(new Color(0, 128, 128));
		div_address.setBounds(281, 103, 142, 21);
		actual.add(div_address);
		
		JLabel text_ip = new JLabel(input_ip.getText());
		text_ip.setForeground(Color.WHITE);
		text_ip.setFont(new Font("Avenir LT Std 65 Medium", Font.BOLD, 11));
		div_address.add(text_ip);
		
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
		text_messages.setText("[BOT] Welcome, Lasallians! This is the beginning of your conversation! Please observe proper decorum at all times when communicating via DLSU USAP.");
		div_messages.setViewportView(text_messages);
		transcript.add(timeNow() + " " + text_messages.getText());
		
		JScrollPane div_users = new JScrollPane();
		div_users.setBounds(445, 135, 113, 219);
		actual.add(div_users);
		
		JTextArea text_users = new JTextArea();
		text_users.setLineWrap(true);
		text_users.setWrapStyleWord(true);
		text_users.setText("Connected Users:\r\n\r\n");
		div_users.setViewportView(text_users);
		
		JButton btn_transcript = new JButton("Save Chat");
		btn_transcript.setBounds(444, 365, 113, 37);
		actual.add(btn_transcript);
		
		JSeparator hb1 = new JSeparator();
		hb1.setBounds(33, 416, 524, 15);
		actual.add(hb1);
		
		JTextArea input_message = new JTextArea();
		input_message.setBounds(33, 431, 390, 89);
		actual.add(input_message);
		
		JButton btn_upload = new JButton("Upload File");
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
		
		mnitem_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!input_name.getText().equals("") && connectedUsers.indexOf(input_name.getText()) != -1){
					try {
	            		connectedUsers.remove(connectedUsers.indexOf(input_name.getText()));
	    				message = "[BOT] " + input_name.getText() + " has left the conversation.";
	    				text_messages.setText(text_messages.getText() + "\r\n\r\n" + message);
	    				transcript.add(timeNow() + " " + message);
						String msg = "";
						msg = message;
						dos.writeUTF(msg);
					} catch(Exception e7) {
						
					}
            	}
            	System.exit(0);
			}
		});
		
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	if(!input_name.getText().equals("") && connectedUsers.indexOf(input_name.getText()) != -1){
            		try {
	            		connectedUsers.remove(connectedUsers.indexOf(input_name.getText()));
	    				message = "[BOT] " + input_name.getText() + " has left the conversation.";
	    				text_messages.setText(text_messages.getText() + "\r\n\r\n" + message);
	    				transcript.add(timeNow() + " " + message);
	    				String msg = "";
						msg = message;
						dos.writeUTF(msg);
            		} catch(Exception e6) {
            			
            		}
            	}
            	System.exit(0);
            }
        });
		
		btn_logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!input_name.getText().equals("") && !input_ip.getText().equals("")){
					try {
						if(input_ip.getText().equals("1201")) {
							text_connected.setText("Hello, " + input_name.getText() + "! You are connected to:");
							text_ip.setText(input_ip.getText());
								
							text_users.setText("Connected Users:\r\n\r\n"); 
							connectedUsers.add(input_name.getText());
							for (String name:connectedUsers) text_users.setText(text_users.getText() + name + "\r\n");
							
							message = "[BOT] " + input_name.getText() + " has joined the conversation.";
							text_messages.setText(text_messages.getText() + "\r\n\r\n" + message);
							transcript.add(timeNow() + " " + message);
							String msg = "";
							msg = message;
							dos.writeUTF(msg);
							switchPanels(actual);
						} else {
							JOptionPane.showMessageDialog(null, "<html><center>" + input_ip.getText() + " is closed. Please try another IP/PORT.</html>", "ERROR", JOptionPane.ERROR_MESSAGE);

						}
					} catch(Exception e5) {
							
					}
				} else {
					JOptionPane.showMessageDialog(null, "<html><center>All fields are required! Please try again.</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btn_logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanels(pre);
					connectedUsers.remove(connectedUsers.indexOf(input_name.getText()));
					
					message = "[BOT] " + input_name.getText() + " has left the conversation.";
					text_messages.setText(text_messages.getText() + "\r\n\r\n" + message);
					text_messages.setText("[BOT] Welcome, Lasallians! This is the beginning of your conversation! Please observe proper decorum at all times when communicating via DLSU USAP.");
					transcript.add(timeNow() + " " + message);
					String msg = "";
					msg = message;
					dos.writeUTF(msg);
					JOptionPane.showMessageDialog(null, "<html><center>You have succesfully logged out.</html>");
				}
				catch(Exception e4) {
					
				}
			}
		});
		
		btn_transcript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saveFile = new JFileChooser();
				FileFilter  texts = new FileNameExtensionFilter("Text file", "txt");
				
				saveFile.addChoosableFileFilter(texts);
				saveFile.setAcceptAllFileFilterUsed(false);
				saveFile.setSelectedFile(new File(".txt"));
				
				int returnVal = saveFile.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try(FileWriter fwLog = new FileWriter(saveFile.getSelectedFile())) {
						for (String log:transcript) fwLog.write(log + System.lineSeparator());
						fwLog.close();
						JOptionPane.showMessageDialog(null, "<html><center>Transcript succesfully downloaded.</html>");
					} catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "<html><center>Error in downloading transcript! Please try again.</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			}
		});
		
		btn_upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser openFile = new JFileChooser();
				FileFilter images = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
				FileFilter texts = new FileNameExtensionFilter("Text file", "txt");
				
				openFile.addChoosableFileFilter(images);
				openFile.addChoosableFileFilter(texts);
				openFile.setMultiSelectionEnabled(true);
				openFile.setAcceptAllFileFilterUsed(false);
				
				int returnVal = openFile.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File[] files = openFile.getSelectedFiles();
					JOptionPane.showMessageDialog(null, "<html><center>File succesfully sent.</html>");
					for (File fName:files) {
						try {
						message = "[" + input_name.getText() + "] " + "sent " + fName + ".";
						transcript.add(timeNow() + " " + message);
						text_messages.setText(text_messages.getText() + "\r\n\r\n" + message);
						String msg = "";
						msg = message;
						dos.writeUTF(msg);
						} catch(Exception e3) {
							
						}
					}
			    }
			}
		});
		
		btn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!input_message.getText().equals("")){
					try {
						message = "[" + input_name.getText() + "] " + input_message.getText();
						transcript.add(timeNow() + " " + message);
						text_messages.setText(text_messages.getText() + "\r\n\r\n" + message);
						String msg = "";
						msg = message;
						dos.writeUTF(msg);
						input_message.setText("");
						
					} catch(Exception e2) {
						
					}
				} else {
					JOptionPane.showMessageDialog(null, "<html><center>Message field is empty! Please try again.</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private static JTextArea text_messages;
	private JPanel contentPane;
	private JTextField input_name;
	private JTextField input_ip;
	private JLayeredPane sessions;
	private JPanel pre;
	private JPanel actual;
}
