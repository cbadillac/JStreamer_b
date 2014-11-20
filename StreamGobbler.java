import java.io.*;
public class StreamGobbler extends Thread {
	InputStream is;
    boolean discard;
    StreamGobbler(InputStream is, boolean discard) {
      this.is = is;
      this.discard = discard;
    }

    public void run() {
      try {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line=null;
        while ( (line = br.readLine()) != null)
          if(!discard)
            System.out.println(line);    
        }
      catch (IOException ioe) {
        ioe.printStackTrace();  
      }
    }
}
