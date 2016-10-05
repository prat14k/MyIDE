import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Find_Replace extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6233913843545760653L;
	int start=0;
	JLabel lb1;
	JLabel lb2;
	JTextField tf1;
	JTextField tf2;
	JButton but1;
	JButton but2;
	JButton but3;
	JButton but4;
	JButton cancel;
	JTextArea txt;
	public Find_Replace(JTextArea txt,String strs){
		super(strs);
		setLayout(null);
		
		this.txt = txt;
		lb1 = new JLabel("Find : ");
		lb2 = new JLabel("Replace : ");
		tf1 = new JTextField(30);
		tf2 = new JTextField(30);
		but1 = new JButton("Find");
		but2 = new JButton("Replace");
		but3 = new JButton("Find Next");
		but4 = new JButton("Replace All");
		cancel = new JButton("Cancel");
		
		lb1.setBounds(10,20,90,30);
		tf1.setBounds(10,45,160,20);
		but1.setBounds(10, 70, 90, 30);
		but3.setBounds(110,70,90,30);
		lb2.setBounds(10,135,90,30);
		tf2.setBounds(10, 160,160,20);
		but2.setBounds(10, 185, 90, 30);
		but4.setBounds(110,185,120,30);
		cancel.setBounds(60, 300, 90, 50);
		getContentPane().add(lb1);
		getContentPane().add(tf1);
		but1.addActionListener(this);
		getContentPane().add(but1);
		but3.addActionListener(this);
		getContentPane().add(but3);
		getContentPane().add(lb2);
		getContentPane().add(tf2);
		but2.addActionListener(this);
		getContentPane().add(but2);
		but4.addActionListener(this);
		getContentPane().add(but4);
		cancel.addActionListener(this);
		getContentPane().add(cancel);
		setBounds(70,70,300,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximizedBounds(getBounds());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == but1){
			int ind = txt.getText().toLowerCase().indexOf(tf1.getText().toLowerCase());
			if(ind==-1){
				JOptionPane.showMessageDialog(null, "Couldn't find any \""+tf1.getText()+"\" in the text !!!");
				start=0;
			}
			else{
				txt.select(ind, ind+tf1.getText().length());
				start=ind+tf1.getText().length()+1;
			}
			if(ind==txt.getText().toLowerCase().lastIndexOf(tf1.getText().toLowerCase())){
				start=0;
			}
		}
		else if(event.getSource() == but3){
			int ind = txt.getText().toLowerCase().indexOf(tf1.getText().toLowerCase(),start);
			if(ind==-1){
				JOptionPane.showMessageDialog(null, "Couldn't find anymore \""+tf1.getText()+"\" in the text !!!");
				start=0;
			}
			else{
				txt.select(ind, ind+tf1.getText().length());
				if(ind==txt.getText().toLowerCase().lastIndexOf(tf1.getText().toLowerCase())){
					start=0;
				}
				else
					start=ind+tf1.getText().length()+1;
			}
		}
		else if(event.getSource() == but2){
			int ind = txt.getText().toLowerCase().indexOf(tf1.getText().toLowerCase());
			if(ind==-1){
				JOptionPane.showMessageDialog(null, "Couldn't find any \""+tf1.getText()+"\" in the text !!!");
				start=0;
			}
			else{
				txt.select(ind, ind+tf1.getText().length());
				if(ind==txt.getText().toLowerCase().lastIndexOf(tf1.getText().toLowerCase())){
					start=0;
				}
				else
					start=ind+tf1.getText().length()+1;
				txt.replaceSelection(tf2.getText());
			}
			
		}
		else if(event.getSource() == but4){
			int ind = txt.getText().toLowerCase().indexOf(tf1.getText().toLowerCase());
			if(ind==-1){
				JOptionPane.showMessageDialog(null, "Couldn't find any \""+tf1.getText()+"\" in the text !!!");
				start=0;
			}
			else{
				//txt.select(ind, ind+tf1.getText().length());
				//start=ind+tf1.getText().length()+1;
				txt.setText(txt.getText().toLowerCase().replaceAll(tf1.getText().toLowerCase(),tf2.getText()));
				start=0;
			}		
		}
		else if(event.getSource() == cancel){
			this.dispose();
		}
	}
	
}
