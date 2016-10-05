import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultEditorKit;


public class Main_wind extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	File currentFile = null;
	String inputFile = "";
	Container c;
	JTextArea txt,console;
	JPanel topPanel,downPanel;
	JMenuBar menuBar;
	
	JScrollPane topScroll,downScroll;
	JMenu file,edit,search,help,run;
	JMenuItem neww,open,save,saveAs,clear,quit,replace,select_all,cut,copy,paste,qview,abt_me,compile,execute,error_list,inputFileChoose;
	JToolBar toolBar;
	JButton newB,openB,saveB,clearB,searchB,quitB,cutB,copyB,pasteB,compileB,executeB,inputFileChooserB;
	boolean isSaved = true;
	
	private final static ImageIcon sirji = new ImageIcon(Main_wind.class.getResource("/img/sirji.jpg"));
	private final static ImageIcon newIcon = new ImageIcon(Main_wind.class.getResource("/img/newIcon.png"));
	private final static ImageIcon saveIcon = new ImageIcon(Main_wind.class.getResource("/img/saveIcon.jpg"));
	private final static ImageIcon openIcon = new ImageIcon(Main_wind.class.getResource("/img/openIcon.png"));
	private final static ImageIcon clearIcon = new ImageIcon(Main_wind.class.getResource("/img/clearIcon.png"));
	private final static ImageIcon searchIcon = new ImageIcon(Main_wind.class.getResource("/img/searchIcon.jpg"));
	private final static ImageIcon quitIcon = new ImageIcon(Main_wind.class.getResource("/img/quitIcon.jpg"));
	private final static ImageIcon cutIcon = new ImageIcon(Main_wind.class.getResource("/img/cutIcon.png"));
	private final static ImageIcon copyIcon = new ImageIcon(Main_wind.class.getResource("/img/copyIcon.png"));
	private final static ImageIcon replaceIcon = new ImageIcon(Main_wind.class.getResource("/img/replaceIcon.jpg"));
	private final static ImageIcon pasteIcon = new ImageIcon(Main_wind.class.getResource("/img/pasteIcon.png"));
	private final static ImageIcon selectIcon = new ImageIcon(Main_wind.class.getResource("/img/selectIcon.jpg"));
	private final static ImageIcon abt_meIcon = new ImageIcon(Main_wind.class.getResource("/img/user.png"));
	private final static ImageIcon compileIcon = new ImageIcon(Main_wind.class.getResource("/img/compileIcon.png"));
	private final static ImageIcon executeIcon = new ImageIcon(Main_wind.class.getResource("/img/executeIcon.png"));
	private final static ImageIcon saveAsIcon = new ImageIcon(Main_wind.class.getResource("/img/saveAsIcon.png"));
	private final static ImageIcon inputFileSelectIcon = new ImageIcon(Main_wind.class.getResource("/img/upload.png"));
	
	private final Action select_action;
	private final KeyListener keyListen;
	
	
	/**
	 * 
	 */
	public Main_wind(){
		
		c = getContentPane();
		setSize(1400,700);
		setTitle("Untitled |:| MyIDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(new BorderLayout());
		
		txt = new JTextArea(0,0); 
		txt.setFont(new Font("Comic Sans MS" , Font.BOLD , 20));
		txt.setTabSize(2);
		txt.setBackground(new Color(0,120,51));
		txt.setForeground(new Color(255,255,151));
		txt.setCaretColor(new Color(0,0,0));
		
		keyListen  = new TabAction();
		txt.addKeyListener(keyListen);
		topScroll = new JScrollPane(txt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		topPanel = new JPanel(new GridLayout(1,1));
		topPanel.add(topScroll);
		
		console = new JTextArea("",0,0);
		console.setEditable(false);
		console.setBackground(new Color(57,14,10));
		console.setForeground(new Color(122,211,188));
		console.setText("$>");
		downScroll = new JScrollPane(console,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		TitledBorder consoleTitle = BorderFactory.createTitledBorder("Console");
		downPanel= new JPanel(new GridLayout(1,1));
		downPanel.setBorder(consoleTitle);
		downPanel.add(downScroll);
		downPanel.setPreferredSize(new Dimension(getWidth(), getHeight()/4 ));
		
		c.add(topPanel,BorderLayout.CENTER);
		c.add(downPanel,BorderLayout.SOUTH);
		
		menuBar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		search = new JMenu("Search");
		run = new JMenu("Run");
		help = new JMenu("Help");
		
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(search);
		menuBar.add(run);
		menuBar.add(help);
		
		neww = new JMenuItem("New",newIcon);
		open = new JMenuItem("Open",openIcon);
		save = new JMenuItem("Save",saveIcon);
		saveAs = new JMenuItem("Save As",saveAsIcon);
		clear = new JMenuItem("Clear Everything",clearIcon);
		quit = new JMenuItem("Quit",quitIcon);
		
		qview = new JMenuItem("QSearch",searchIcon);
		cut = new JMenuItem(new DefaultEditorKit.CutAction());
		copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		paste = new JMenuItem(new DefaultEditorKit.PasteAction());
		replace = new JMenuItem("Replace",replaceIcon);
		
		
		compile = new JMenuItem("Compile",compileIcon);
		inputFileChoose = new JMenuItem("Choose File Containing Inputs",inputFileSelectIcon);
		execute = new JMenuItem("Run Code",executeIcon);
		
		abt_me = new JMenuItem("About me",abt_meIcon);
		
		select_action = new SelectAction("Select All",selectIcon,"Select All Text",new Integer(KeyEvent.VK_A),txt);
		select_all = new JMenuItem(select_action);
		setJMenuBar(menuBar);
		
		neww.addActionListener(this);
		neww.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		file.add(neww);
		
		open.addActionListener(this);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		file.add(open);
		
		save.addActionListener(this);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		file.add(save);
		
		saveAs.addActionListener(this);
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		file.add(saveAs);
		
		quit.addActionListener(this);
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
		file.add(quit);
		
		select_all.setText("Select All");
		select_all.setIcon(selectIcon);
		select_all.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
		edit.add(select_all);
		
		clear.addActionListener(this);
		clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,InputEvent.CTRL_MASK));
		edit.add(clear);
		
		cut.setText("Cut it");
		cut.setToolTipText("Cut the selected Text");
		cut.setIcon(cutIcon);
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		edit.add(cut);
		
		copy.setText("Copy it");
		copy.setToolTipText("Copy the selected Text");
		copy.setIcon(copyIcon);
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		edit.add(copy);
		
		paste.setText("Paste it");
		paste.setToolTipText("Paste the selected Text");
		paste.setIcon(pasteIcon);
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
		edit.add(paste);
		
		qview.addActionListener(this);
		qview.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
		search.add(qview);
		
		replace.addActionListener(this);
		replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
		search.add(replace);
		
		compile.addActionListener(this);
		compile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7,InputEvent.ALT_MASK));
		run.add(compile);

		inputFileChoose.addActionListener(this);
		run.add(inputFileChoose);
		
		execute.addActionListener(this);
		execute.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9,InputEvent.ALT_MASK));
		run.add(execute);
		
		abt_me.addActionListener(this);
		abt_me.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_MASK));
		help.add(abt_me);
		
		toolBar = new JToolBar();
		add(toolBar,BorderLayout.NORTH);
		
		newB = new JButton(newIcon);
		openB = new JButton(openIcon);
		saveB = new JButton(saveIcon);
		clearB = new JButton(clearIcon);
		quitB = new JButton(quitIcon);
		cutB = new JButton(new DefaultEditorKit.CutAction());
		copyB = new JButton(new DefaultEditorKit.CopyAction());
		searchB = new JButton(searchIcon);
		pasteB = new JButton(new DefaultEditorKit.PasteAction());
		compileB = new JButton(compileIcon);
		executeB = new JButton(executeIcon);
		inputFileChooserB = new JButton(inputFileSelectIcon);
		
		newB.setToolTipText("New File");
		newB.addActionListener(this);
		toolBar.add(newB);
		toolBar.addSeparator();
		
		openB.setToolTipText("Open File");
		openB.addActionListener(this);
		toolBar.add(openB);
		toolBar.addSeparator();
		
		saveB.setToolTipText("Save File");
		saveB.addActionListener(this);
		toolBar.add(saveB);
		toolBar.addSeparator();
		
		clearB.setToolTipText("clear File");
		clearB.addActionListener(this);
		toolBar.add(clearB);
		toolBar.addSeparator();
		
		cutB.setText("");
		cutB.setIcon(cutIcon);
		cutB.setToolTipText("cut it");
		toolBar.add(cutB);
		toolBar.addSeparator();
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new java.awt.Font("Harrington",java.awt.Font.BOLD,16));
		btnExit.setBounds(402, 562, 107, 46);
		btnExit.addActionListener(this);
		
		
		copyB.setText("");
		copyB.setIcon(copyIcon);
		copyB.setToolTipText("copy it");
		toolBar.add(copyB);
		toolBar.addSeparator();
		
		pasteB.setText("");
		pasteB.setIcon(pasteIcon);
		pasteB.setToolTipText("paste it");
		toolBar.add(pasteB);
		toolBar.addSeparator();
		
		
		searchB.setToolTipText("Search File");
		searchB.addActionListener(this);
		toolBar.add(searchB);
		toolBar.addSeparator();

		compileB.setToolTipText("Compile");
		compileB.addActionListener(this);
		toolBar.add(compileB);
		toolBar.addSeparator();
		
		inputFileChooserB.setToolTipText("Choose the input File for the Program");
		inputFileChooserB.addActionListener(this);
		toolBar.add(inputFileChooserB);
		
		executeB.setToolTipText("Execute your codes and ee outputs");
		executeB.addActionListener(this);
		toolBar.add(executeB);
		toolBar.addSeparator();
		
		quitB.setToolTipText("Exit the application");
		quitB.addActionListener(this);
		toolBar.add(quitB);
		toolBar.addSeparator();
	//	pack();
		
		
				
	}
	
	protected JTextArea getTextArea(){
		return txt;
	}
	
	protected void clear(){
		txt.setText("");
	}
	
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource() == clear || event.getSource() == clearB){
			this.clear();
			if(currentFile==null)
				isSaved = true;
		}
		else if(event.getSource() == neww || event.getSource() == newB ){
			if(isSaved == false){
				Operations.newFile(this);
			}
			else{
				this.clear();
				this.setTitle("Untitled |:| MyIDE");
				currentFile = null;
				isSaved = true;
			}
		}
		else if(event.getSource() == open || event.getSource() == openB){
			
			if(isSaved == false){
				Operations.openFile(this);
			}
			else{
				Operations.openUserFile(this);
			}
		}
		else if(event.getSource() == inputFileChoose || event.getSource() == inputFileChooserB){
			inputFile = Operations.fetchInput(this);
		}
		else if(event.getSource() == save || event.getSource() == saveB || event.getSource() == saveAs){
			if((currentFile == null)||(event.getSource()==saveAs)){
				JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
				int opt = jfc.showSaveDialog(null);
				if(opt == JFileChooser.APPROVE_OPTION){
					currentFile = jfc.getSelectedFile();
				}
			}
			
			if(currentFile!=null){
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(currentFile.getPath()));){
					
					bw.write(txt.getText());
					isSaved = true;
					this.setTitle(currentFile.getName() + " |:| MyIDE");
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		else if(event.getSource() == qview || event.getSource() == searchB){
			new Find_Replace(txt,"Find nd Replace");
		}
		else if(event.getSource() == abt_me){
			JComponent[] strings = new JComponent[]{new JLabel("Hi.... My name is  Prateek Sharma , a CSE Btech engineer."),new JLabel("I won't brag about myself ..."),new JLabel("just wanna say that i learnt a lot of things making this IDE and just  wanna say that "),new JLabel("like me , you can also make it !!!"),new JLabel("Also, just so you know .... the inspiration behind this is Manish Sir ...."),new JLabel("Thank you sir for making us understand the why for the things,rather than how!!!!"),new JLabel(sirji),new JLabel("#RESPECT _/\\_")};
			JOptionPane.showMessageDialog(null,strings,"About Me",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(event.getSource() == quit || event.getSource() == quitB){
			if(isSaved == true){
				this.dispose();
			}
			else{
				Operations.safeQuit(this);
			}
		}
		else if(event.getSource() == compile || event.getSource() == compileB){
			// do some task
			if((isSaved == false)||(currentFile==null)){
				JOptionPane.showMessageDialog(this, "File not Saved. Please Save it first to compile.", "Unable to continue", JOptionPane.ERROR_MESSAGE);
			}
			else{
				Operations.compileCode(this);	
			}
		}
		else if(event.getSource() == execute || event.getSource() == executeB){
			//Run.execute()
			if((isSaved == false)||(currentFile==null)){
				JOptionPane.showMessageDialog(this, "File not Saved. Please Save it first to execute it.", "Unable to continue", JOptionPane.ERROR_MESSAGE);
			}
			else{
				Operations.executeCode(this);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		
		new Main_wind().setVisible(true);
		
	}
	
	
	public class SelectAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public SelectAction(String name,ImageIcon icon, String description, Integer mnemonic,final JTextArea txt){
			
			super(name,icon);
			putValue(SHORT_DESCRIPTION,description);
			putValue(MNEMONIC_KEY, mnemonic);
			
		}
		
		public void actionPerformed(ActionEvent e){
			txt.selectAll();
		}

	}
	public class TabAction implements KeyListener {
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			if(isSaved == true){
				isSaved = false;
				if(currentFile!=null){
					setTitle(currentFile.getName() + "* |:| MyIDE");
				}
			}
			
			if((e.getKeyChar() == '\n')||(e.getKeyChar() == '\r')){
				String content = txt.getText();
				String[] text = content.split("\n");
				String space = "";
				
				//System.out.println(text.length);
				
				for(int i=0;i<text[text.length-1].length();i++){
					if(text[text.length-1].charAt(i) == ' ')
						space = space + " ";
					else if(text[text.length-1].charAt(i) == '\t')
						space = space + "\t";
					else break;
				}
				content = content + space;
				txt.setText(content);
			}
		}

	}	
}




