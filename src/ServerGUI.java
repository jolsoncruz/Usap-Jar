import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ServerGUI extends JFrame {
	
	static ServerSocket ss;
	static Socket s;
	static DataInputStream dis;
	static DataOutputStream dos;
	
	private JPanel post;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI frame = new ServerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			String msgin = "";
			ss = new ServerSocket(1201);
			s = ss.accept();
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			
			while(!msgin.equals("exit")){
				msgin = dis.readUTF();
				text_log.setText(text_log.getText() + "\n\n" + msgin);
				logSheet.add(timeNow() + " " + msgin);
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
	
	public static Timestamp timeNow() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

	/**
	 * Create the frame.
	 */
	public ServerGUI() {
		setTitle("De La Salle Usap (SERVER)");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mn_file = new JMenu("File");
		menuBar.add(mn_file);
		
		JMenuItem mnitem_quit = new JMenuItem("Quit App");
		mn_file.add(mnitem_quit);
		
		JMenu mn_help = new JMenu("Help");
		menuBar.add(mn_help);
		
		JMenuItem mnitem_about = new JMenuItem("About");
		mn_help.add(mnitem_about);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		sessions = new JLayeredPane();
		contentPane.add(sessions, "name_140743403389800");
		sessions.setLayout(new CardLayout(0, 0));
		
		pre = new JPanel();
		sessions.add(pre, "name_140436339033700");
		pre.setLayout(null);
		
		JLabel img_box = new JLabel("Image");
		img_box.setBounds(189, 24, 200, 200);
		pre.add(img_box);
		Image logo1 = new ImageIcon(this.getClass().getResource("/usap.png")).getImage();
		img_box.setIcon(new ImageIcon(logo1));
		
		JLabel img_welcome = new JLabel("");
		img_welcome.setBounds(39, 248, 500, 100);
		pre.add(img_welcome);
		Image welcome = new ImageIcon(this.getClass().getResource("/create.png")).getImage();
		img_welcome.setIcon(new ImageIcon(welcome));
		
		input_port = new JTextField();
		input_port.setFont(new Font("Avenir LT Std 65 Medium", Font.BOLD, 12));
		input_port.setForeground(new Color(255, 255, 255));
		input_port.setOpaque(false);
		input_port.setColumns(10);
		input_port.setBounds(283, 385, 139, 20);
		input_port.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pre.add(input_port);
		
		JLabel img_port = new JLabel("img_port");
		img_port.setBounds(139, 375, 300, 40);
		pre.add(img_port);
		Image port = new ImageIcon(this.getClass().getResource("/port.png")).getImage();
		img_port.setIcon(new ImageIcon(port));
		
		JButton btn_logIn = new JButton("Create Room");
		btn_logIn.setBounds(149, 460, 290, 48);
		pre.add(btn_logIn);
		
		actual = new JPanel();
		sessions.add(actual, "name_141027096451300");
		actual.setLayout(null);
		
		img_logo = new JLabel("");
		img_logo.setBounds(33, 11, 524, 76);
		actual.add(img_logo);
		Image logo2 = new ImageIcon(this.getClass().getResource("/banner.png")).getImage();
		img_logo.setIcon(new ImageIcon(logo2));
		
		JLabel text_connected = new JLabel("Hello, Admin! You are listening to:");
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
		
		JButton btn_closeRoom = new JButton("Close Room");
		btn_closeRoom.setBounds(445, 103, 113, 21);
		actual.add(btn_closeRoom);
		
		JScrollPane div_messages = new JScrollPane();
		div_messages.setBounds(33, 135, 390, 380);
		actual.add(div_messages);
		
		text_log = new JTextArea();
		text_log.setEditable(false);
		text_log.setLineWrap(true);
		text_log.setWrapStyleWord(true);
		text_log.setText("[BOT] Welcome, Admin! This is the beginning of your session log. You may click the button on the right to export this log as a text file.");
		div_messages.setViewportView(text_log);
		logSheet.add(timeNow() + " " + text_log.getText());
		
		JScrollPane div_users = new JScrollPane();
		div_users.setBounds(445, 135, 113, 328);
		actual.add(div_users);
		
		JTextArea text_users = new JTextArea();
		text_users.setLineWrap(true);
		text_users.setWrapStyleWord(true);
		text_users.setText("Connected Users:\r\n\r\n");
		div_users.setViewportView(text_users);
		
		JButton btn_saveLog = new JButton("Save Log");
		btn_saveLog.setBounds(444, 478, 113, 37);
		actual.add(btn_saveLog);
		
		post = new JPanel();
		sessions.add(post, "name_143580404162200");
		post.setLayout(null);
		
		JLabel img_box2 = new JLabel("Image");
		img_box2.setBounds(189, 24, 200, 200);
		post.add(img_box2);
		Image logo3 = new ImageIcon(this.getClass().getResource("/usap.png")).getImage();
		img_box2.setIcon(new ImageIcon(logo3));
		
		JLabel img_save = new JLabel("");
		img_save.setBounds(39, 248, 500, 100);
		post.add(img_save);
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		img_save.setIcon(new ImageIcon(save));
		
		JLabel text_port2 = new JLabel("");
		text_port2.setForeground(Color.WHITE);
		text_port2.setBounds(288, 382, 127, 27);
		post.add(text_port2);
		
		JLabel img_port2 = new JLabel("img_port");
		img_port2.setBounds(139, 375, 300, 40);
		post.add(img_port2);
		Image port2 = new ImageIcon(this.getClass().getResource("/port.png")).getImage();
		img_port2.setIcon(new ImageIcon(port2));
		
		JButton btn_yes = new JButton("Yes, save it!");
		btn_yes.setBounds(118, 460, 148, 48);
		post.add(btn_yes);
		
		JButton btn_no = new JButton("No, good bye!");
		btn_no.setBounds(316, 460, 148, 48);
		post.add(btn_no);
		
		//ACTION LISTENERS
		mnitem_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html><center>Software Developer: Joeme Santos<br>GUI Developer: Jolson Cruz<br><br>In partial completion of CSNETWK<br>Submitted to: Mr. Marnel Peradilla.</html>");
			}
		});
		
		mnitem_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!text_port.getText().equals("") && logSheet.size()!=0){
					switchPanels(post);
					
					message = "[BOT] Admin has closed the room for port " + input_port.getText() + ".";
					text_log.setText(text_log.getText() + "\r\n\r\n" + message);
					logSheet.add(timeNow() + " " + message);
					JOptionPane.showMessageDialog(null, "<html><center>You have succesfully closed the room.</html>");
            	} else System.exit(0);
			}
		});
		
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	if(!text_port.getText().equals("") && logSheet.size()>0){
            		switchPanels(post);
					
					message = "[BOT] Admin has closed the room for port " + input_port.getText() + ".";
					text_log.setText(text_log.getText() + "\r\n\r\n" + message);
					logSheet.add(timeNow() + " " + message);
					JOptionPane.showMessageDialog(null, "<html><center>You have succesfully closed the room.</html>");
            	} else System.exit(0);
            }
        });
		
		btn_logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!input_port.getText().equals("")){
					text_port.setText(input_port.getText());
					text_port2.setText(input_port.getText());
					message = "[BOT] Admin has opened the room for port " + input_port.getText() + ".";
					text_log.setText(text_log.getText() + "\r\n\r\n" + message);
					logSheet.add(timeNow() + " " + message);
					switchPanels(actual);	
				} else {
					JOptionPane.showMessageDialog(null, "<html><center>Port is required! Please try again.</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btn_closeRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(post);
				
				message = "[BOT] Admin has closed the room for port " + input_port.getText() + ".";
				text_log.setText(text_log.getText() + "\r\n\r\n" + message);
				logSheet.add(timeNow() + " " + message);
				JOptionPane.showMessageDialog(null, "<html><center>You have succesfully closed the room.</html>");
			}
		});
		
		btn_saveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saveFile = new JFileChooser();
				FileFilter  texts = new FileNameExtensionFilter("Text file", "txt");
				
				saveFile.addChoosableFileFilter(texts);
				saveFile.setAcceptAllFileFilterUsed(false);
				saveFile.setSelectedFile(new File(".txt"));
				
				int returnVal = saveFile.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try(FileWriter fwLog = new FileWriter(saveFile.getSelectedFile())) {
						for (String log:logSheet) fwLog.write(log + System.lineSeparator());
						fwLog.close();
						JOptionPane.showMessageDialog(null, "<html><center>Session log succesfully downloaded.</html>");
					} catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "<html><center>Error in downloading session log! Please try again.</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			}
		});
		
		btn_yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saveFile = new JFileChooser();
				FileFilter  texts = new FileNameExtensionFilter("Text file", "txt");
				
				saveFile.addChoosableFileFilter(texts);
				saveFile.setAcceptAllFileFilterUsed(false);
				saveFile.setSelectedFile(new File(".txt"));
				
				int returnVal = saveFile.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try(FileWriter fwLog = new FileWriter(saveFile.getSelectedFile())) {
						for (String log:logSheet) fwLog.write(log + System.lineSeparator());
						fwLog.close();
						JOptionPane.showMessageDialog(null, "<html><center>Session log succesfully downloaded.</html>");
					} catch (Exception ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "<html><center>Error in downloading session log! Please try again.</html>", "ERROR", JOptionPane.ERROR_MESSAGE);
			        }
			    }
				System.exit(0);
			}
		});
		
		btn_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	static List<String> connectedUsers = new ArrayList<String>();
	static List<String> logSheet = new ArrayList<String>();
	String message = new String();
	
	private static JTextArea text_log;
	private JPanel contentPane;
	private JLayeredPane sessions;
	private JPanel pre;
	private JTextField input_port;
	private JPanel actual;
	private JLabel img_logo;
}
