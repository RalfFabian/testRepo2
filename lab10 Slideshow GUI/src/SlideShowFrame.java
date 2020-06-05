import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

public class SlideShowFrame extends JFrame {

	private JPanel contentPane;
	private ImagePanel imagePanel;
	private JButton btnNext;
	private JButton btnPrevious;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmOpen;
	private JMenuItem mntmExit;
	private JSeparator separator;
	/**
	 * @wbp.nonvisual location=37,775
	 */
	private final JFileChooser fileChooser = new JFileChooser(".");
	private Timer timer;
	
	private File[] imageFiles = null;
	private int imageCounter = 0;
	private JToolBar toolBar;
	private JButton btnOpen;
	private JButton btnNext_1;
	private JButton btnPrevious_1;
	private JButton btnStart;
	private JMenuItem mntmNew;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlideShowFrame frame = new SlideShowFrame();
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
	public SlideShowFrame() {
		setTitle("Slide Show");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 671);
		
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		timer = new Timer(1000,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onNext();
				
			}
		});
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		mnFile.setMnemonic('f');
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Open...");
		mntmOpen.setIcon(new ImageIcon(SlideShowFrame.class.getResource("/icons16x16/folder_open_16.png")));
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOpen();
			}
		});
		
		mntmNew = new JMenuItem("New");
		mntmNew.setIcon(new ImageIcon(SlideShowFrame.class.getResource("/icons16x16/new_document_16.png")));
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNew.setMnemonic('n');
		mntmNew.addActionListener(new NewHandler(this));
		mnFile.add(mntmNew);
		mntmOpen.setMnemonic('o');
		mnFile.add(mntmOpen);
		
		separator = new JSeparator();
		mnFile.add(separator);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setMnemonic('x');
		mnFile.add(mntmExit);
		
		mnHelp = new JMenu("Help");
		mnHelp.setMnemonic('h');
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		imagePanel = new ImagePanel();
		imagePanel.setFitToScreen(true);
		imagePanel.setAspectRatio(true);
		contentPane.add(imagePanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnPrevious = new JButton("Prev");
		btnPrevious.setIcon(new ImageIcon(SlideShowFrame.class.getResource("/icons24x24/arrowleft_green_24.png")));
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onPrevious();
			}
		});
		btnPrevious.setMnemonic('p');
		panel.add(btnPrevious);
		
		btnNext = new JButton("Next");
		btnNext.setIcon(new ImageIcon(SlideShowFrame.class.getResource("/icons24x24/arrowright_green_24.png")));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onNext();
			}
		});
		btnNext.setMnemonic('n');
		panel.add(btnNext);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnOpen = new JButton("");
		btnOpen.setIcon(new ImageIcon(SlideShowFrame.class.getResource("/icons24x24/folder_open_24.png")));
		btnOpen.setToolTipText("Open Dialog");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOpen();
			}
		});
		toolBar.add(btnOpen);
		
		btnPrevious_1 = new JButton("");
		btnPrevious_1.setIcon(new ImageIcon(SlideShowFrame.class.getResource("/icons24x24/arrowleft_green_24.png")));
		btnPrevious_1.setToolTipText("Got to previous image");
		btnPrevious_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onPrevious();
			}
		});
		toolBar.add(btnPrevious_1);
		
		btnNext_1 = new JButton("");
		btnNext_1.setIcon(new ImageIcon(SlideShowFrame.class.getResource("/icons24x24/arrowright_green_24.png")));
		btnNext_1.setToolTipText("Got to next image");
		btnNext_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onNext();
			}
		});
		toolBar.add(btnNext_1);
		
		btnStart = new JButton("Start");
		btnStart.setToolTipText("Start/Stop Animation");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onStart();
			}
		});
		toolBar.add(btnStart);
	}
	
	private void onOpen() {
		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File dir = fileChooser.getSelectedFile();
			imageFiles = dir.listFiles(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					String tmp = name.toLowerCase();
					if(tmp.endsWith(".jpg") || tmp.endsWith(".jpeg") || tmp.endsWith(".png"))
						return true;
					return false;
				}
			});
			
			if(imageFiles.length > 0)
				imageCounter = 0;
				imagePanel.loadImage(imageFiles[imageCounter].getAbsolutePath());
		}
	}
	
	private void onNext() {
		if(imageFiles.length > 0 || imageFiles != null) {
			if(imageCounter < imageFiles.length-1)
				imageCounter++;
			else
				imageCounter=0;
			imagePanel.loadImage(imageFiles[imageCounter].getAbsolutePath());
		}
	}
	
	private void onPrevious() {
		if(imageFiles.length > 0 || imageFiles != null) {
			if(imageCounter > 0)
				imageCounter--;
			else
				imageCounter = imageFiles.length-1;
			imagePanel.loadImage(imageFiles[imageCounter].getAbsolutePath());
		}
	}
	
	private void onStart() {
		if(btnStart.getText().equals("Start")) {
			timer.start();
			btnStart.setText("Stop");
		}else {
			timer.stop();
			btnStart.setText("Start");
		}
			
	}
	

	

}
