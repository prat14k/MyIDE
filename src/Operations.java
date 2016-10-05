import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Operations {

	public static int confirmDialog(Main_wind ref,String heading,String message) {
		
		int dialogButtons = JOptionPane.YES_NO_CANCEL_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(ref,message,heading,dialogButtons);
		
		return dialogResult;
		
	}
	
	
	public static void safeQuit(Main_wind ref) {
		
		int dialogResult = confirmDialog(ref, "Exit Coonfirmation", "Thee file is not saved. Do you want to save before exiting ??? ");
		
		if(dialogResult == JOptionPane.YES_OPTION){
		
			try{
				if(ref.currentFile == null){
					JFileChooser jfc = new JFileChooser("user.dir");
					int res = jfc.showSaveDialog(ref);
					if(res == JFileChooser.APPROVE_OPTION){
						ref.currentFile = jfc.getSelectedFile();
					}
				}
				if(ref.currentFile != null){
					BufferedWriter brw = new BufferedWriter(new FileWriter(ref.currentFile));
					brw.write(ref.txt.getText());
					brw.close();
					ref.dispose();
				}
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(ref, e.toString());
			}
				
		}
		else if(dialogResult == JOptionPane.NO_OPTION){
			ref.dispose();
		}
	}
	
	
	public static void openUserFile(Main_wind ref){
		JFileChooser filer = new JFileChooser(System.getProperty("user.dir"));
		int opt = filer.showOpenDialog(null);
		if(opt == JFileChooser.APPROVE_OPTION){
			ref.clear();
			try(Scanner file_content = new Scanner(new FileReader(filer.getSelectedFile().getAbsoluteFile().getPath()));){
				
				while(file_content.hasNext()){
					ref.txt.append(file_content.nextLine() + "\n");
				}
				ref.currentFile = filer.getSelectedFile().getAbsoluteFile();
				ref.setTitle(ref.currentFile.getName() + " |:| MyIDE");
				ref.isSaved = true;
			}
			catch(FileNotFoundException ex){
				ex.printStackTrace();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public static void openFile(Main_wind ref){
		
		int dialogResult = Operations.confirmDialog(ref,"Open File Confirmation","The file is not saved. Do you want save this file before opening another file ??? ");
		
		if(dialogResult == JOptionPane.YES_OPTION){
		
			try{
				if(ref.currentFile == null){
					JFileChooser jfc = new JFileChooser("user.dir");
					int res = jfc.showSaveDialog(ref);
					if(res == JFileChooser.APPROVE_OPTION){
						ref.currentFile = jfc.getSelectedFile();
					}
				}
				if(ref.currentFile != null){
					BufferedWriter brw = new BufferedWriter(new FileWriter(ref.currentFile));
					brw.write(ref.txt.getText());
					brw.close();
					
					Operations.openUserFile(ref);
					
				}
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(ref, e.toString());
			}
				
		}
		else if(dialogResult == JOptionPane.NO_OPTION){
			
			Operations.openUserFile(ref);
		}
	}
	
	
	public static void newFile(Main_wind ref){
		
		int dialogResult = confirmDialog(ref,"New File Confirmation","The file is not saved. Do you want save this file before opening a new file ??? ");
		
		if(dialogResult == JOptionPane.YES_OPTION){
		
			try{
				if(ref.currentFile == null){
					JFileChooser jfc = new JFileChooser("user.dir");
					int res = jfc.showSaveDialog(ref);
					if(res == JFileChooser.APPROVE_OPTION){
						ref.currentFile = jfc.getSelectedFile();
					}
				}
				if(ref.currentFile != null){
					BufferedWriter brw = new BufferedWriter(new FileWriter(ref.currentFile));
					brw.write(ref.txt.getText());
					brw.close();
					ref.clear();
					ref.setTitle("Untitled |:| MyIDE");
					ref.currentFile = null;
					ref.isSaved = true;
				}
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(ref, e.toString());
			}
				
		}
		else if(dialogResult == JOptionPane.NO_OPTION){
			ref.clear();
			ref.setTitle("Untitled |:| MyIDE");
			ref.currentFile = null;
			ref.isSaved = true;
		}
	}
	
	public static int findOSType(){
		/*
		String nameOS = "os.name";        
        String versionOS = "os.version";        
        String architectureOS = "os.arch";
        System.out.println("\n    The information about OS");
        System.out.println("\nName of the OS: " + 
        System.getProperty(nameOS));
        System.out.println("Version of the OS: " + 
        System.getProperty(versionOS));
        System.out.println("Architecture of THe OS: " + 
        System.getProperty(architectureOS));
		 */	
	
		String os_name = System.getProperty("os.name");
		if(os_name.contains("Windows"))
			return 0;
		else if(os_name.contains("Linux"))
			return 1;
		return 2;
		
	}
	
	public static int fileType(String name){
		
		String extension = "";
		int i = name.lastIndexOf('.');
		if (i >= 0) {
		    extension = name.substring(i+1);
		}
		
		switch(extension){
		
			case "java" : 
				return 0;
			case "cpp" : 
					return 1;
			case "c" : 
					return 2;
			case "py" : 
					return 3;
			case "rb" : 
					return 4;
			default:
					return 9;
		}
		
	}
	
	public static boolean compileCode(Main_wind ref){
		
		String consoleLog = ref.console.getText();
		String file = ref.currentFile.toString();
		int typeOfFile = Operations.fileType(file);
		String outputLog="",errorLog="";
		int osType = Operations.findOSType();
		String compileCommand = null;
		String[] arggs = null;
		
		if(typeOfFile > 4){
			JOptionPane.showMessageDialog(ref, "File provided to compile is not of the known format.Please check the format in order to continue the process", "Unrecognized File Type", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(osType == 0){
			
			switch(typeOfFile){
				case 0:
					compileCommand = "javac \"" + file + "\"";
					break;
				case 1:
					compileCommand = "gcc \"" + file + "\"";
					break;
				case 2:
					compileCommand = "g++ \"" + file + "\"";
					break;
				default:
						consoleLog = consoleLog + " Compilation Completed.\n$>";
					ref.console.setText(consoleLog);
					return true;
			}
			arggs = new String[] {"cmd", "/c", compileCommand};
		}
		else if(osType == 1){

			switch(typeOfFile){
				case 0:
					compileCommand = "javac \"" + file + "\"";
					break;
				case 1:
					compileCommand = "gcc \"" + file + "\"";
					break;
				case 2:
					compileCommand = "g++ \"" + file + "\"";
			}
			arggs = new String[] {"/bin/bash", "-c", compileCommand};
		}
		
		consoleLog = consoleLog + compileCommand + "\n";
		//System.out.println(compileCommand);
		try {
	    		Process proc = new ProcessBuilder(arggs).start();

				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				
				consoleLog = consoleLog + " Compilation Completed. \n";
				
				// read the output from the command
				String s = null;
				while ((s = stdInput.readLine()) != null) {
				    outputLog = outputLog + s + "\n";
					//System.out.println(s);
				}
				
				// read any errors from the attempted command
				//System.out.println("Here is the standard error of the command (if any):\n");
				while ((s = stdError.readLine()) != null) {
					
					errorLog = errorLog + s + "\n";
					//System.out.println(s);
				}
		        
				if(outputLog!=""){
					consoleLog = consoleLog + "Compilation Message : \n" + outputLog + "\n";
				}
				
				if(errorLog!=""){
					consoleLog = consoleLog + "Compilation Errors : \n" + errorLog + "\n";
				}
				
				consoleLog = consoleLog + "$>";
		        ref.console.setText(consoleLog);
		        //System.out.println(consoleLog);
		        
			}
		    catch (Throwable t) {
		        t.printStackTrace();
		    }
		
		return (errorLog=="");
		
	}
	
	public static void executeCode(Main_wind ref){
		boolean signalToContinue = Operations.compileCode(ref);
		
		if(signalToContinue == false)
			 return;		
		
		
		String consoleLog = ref.console.getText();
		String file = ref.currentFile.getAbsolutePath().toString();
		int typeOfFile = Operations.fileType(file);
		String outputLog="",errorLog="";
		int osType = Operations.findOSType();
		String executeCommand = null;
		String[] arggs = null;
		String addon = "";
		System.out.println(file);
		String name="",path="";
		
		int ind;

		if(osType == 0){
		
			ind = file.lastIndexOf('\\');
			if (ind >= 0) {
			    path = file.substring(0, ind);
			    name = file.substring(ind+1);
			}
			ind = name.lastIndexOf('.');
			//System.out.println(name + " "+ path);
			if (ind >= 0) {
				name = name.substring(0, ind);
			}
			
			if(ref.inputFile!=""){
				addon = " < \"" + ref.inputFile + "\"";
			}
			

			switch(typeOfFile){
				case 0:
					executeCommand = "java -cp \"" + path + "\";. \"" + name + "\"" + addon;
					break;
				case 1:
					executeCommand = "./a.out" + addon;
					break;
				case 2:
					executeCommand = "./a.out" + addon;
					break;
				case 3:
					executeCommand = "python \"" + path + "\\" + name + ".py\"" + addon;
					break;
				case 4:
					executeCommand = "ruby \"" + path + "\\" + name + ".rb\"" + addon;
					break;
			}
			
			System.out.println(executeCommand);
			arggs = new String[] {"cmd", "/c", executeCommand};
				
		}
		else if(osType == 1){

			ind = file.lastIndexOf('/');
			if (ind >= 0) {
			    path = file.substring(0, ind);
			    name = file.substring(ind+1);
			}
			ind = name.lastIndexOf('.');
			//System.out.println(name + " "+ path);
			if (ind >= 0) {
				name = name.substring(0, ind);
			}
			
			if(ref.inputFile!=""){
				addon = " < \"" + ref.inputFile + "\"";
			}
			

			switch(typeOfFile){
				case 0:
					executeCommand = "java -cp \"" + path + "\":. \"" + name + "\"" + addon;
					break;
				case 1:
					executeCommand = "./a.out" + addon;
					break;
				case 2:
					executeCommand = "./a.out" + addon;
					break;
				case 3:
					executeCommand = "python \"" + path + "\\" + name + ".py\"" + addon;
					break;
				case 4:
					executeCommand = "ruby \"" + path + "\\" + name + ".rb\"" + addon;
					break;
			}
			System.out.println(executeCommand);
			arggs = new String[] {"/bin/bash", "-c", executeCommand};
				
		}
		
		//System.out.println(compileCommand);
		consoleLog = consoleLog + executeCommand + "\n";
		try {
		    
	    		Process proc = new ProcessBuilder(arggs).start();

				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				
				
				// read the output from the command
				String s = null;
				while ((s = stdInput.readLine()) != null) {
				    outputLog = outputLog + s + "\n";
					System.out.println(outputLog);
				}
				
				while ((s = stdError.readLine()) != null) {
					
					errorLog = errorLog + s + "\n";
					//System.out.println(s);
				}
		        
				if(outputLog!=""){
					consoleLog = consoleLog + "Execution Message : \n" + outputLog + "\n";
				}
				
				//System.out.println(outputLog);
				
				if(errorLog!=""){
					consoleLog = consoleLog + "Execution Errors : \n" + errorLog + "\n";
				}
				
				consoleLog = consoleLog + "$>";
		        ref.console.setText(consoleLog);
		        //System.out.println(consoleLog);
		        
			}
		    catch (Throwable t) {
		        t.printStackTrace();
		    }
		
	}
	
	public static String fetchInput(Main_wind ref){
		String input = "";
	
		JFileChooser filer = new JFileChooser(System.getProperty("user.dir"));
		filer.setApproveButtonText("Select File");
		filer.setDialogTitle("Select Input File");
		int opt = filer.showOpenDialog(ref);
		if(opt == JFileChooser.APPROVE_OPTION){
			try{
				input = filer.getSelectedFile().toString();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		return input;
	}
	
}
