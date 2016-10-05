import java.io.InputStream;
class terminal {
	public static void main(String[] args) {
		try {
    
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
			String[] arggs = new String[] {"/bin/bash", "-c", "./a.out < ~/arg.txt"};  // echo|set /p="input" | java filename
			
			Process proc = new ProcessBuilder(arggs).start();

			InputStream is = proc.getInputStream();

	        // NOTE: this is not the most elegant way to extract content from the
	        // input stream
	        int i = -1;
	        StringBuilder buf = new StringBuilder();
	        while ((i = is.read()) != -1) {
	            buf.append((char)i);
	        }

	        proc.waitFor();

	        System.out.println(buf.toString());
	    }
	    catch (Throwable t) {
	        t.printStackTrace();
	    }


	}	
}
