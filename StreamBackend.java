import java.io.IOException;
public final class StreamBackend {
		
	public StreamBackend()
	{
		
	}
	
	public static void initStreaming(int opt, String ip, String port, String source,BoolObj isStop){
		
		Process streamProcess = null;
		String command = "ls";
		
		switch (opt) {
		case 0:
			System.out.println("audio streaming");
			command = "gst-launch-0.10 filesrc location=" + source + " ! tcpclientsink host=" + ip + " port=" + port;
			break;
		case 1:
			System.out.println("WebCam streaming");
			command = 	"gst-launch-0.10 v4l2src device="+ source +" !"+
					" 'video/x-raw-yuv,width=640,height=480' !"+
					"  x264enc pass=qual quantizer=20 tune=zerolatency !"+
					" rtph264pay !"+
					" udpsink host="+ ip +" port="+port;
			break;
			
		case 2:
			System.out.println("video streaming");
			command = "cvlc -vvv "+ source +" --sout '#udp{dst="+ ip +":"+ port +"}' --loop";
			break;

		default:
			System.out.println("Error in Streaming option");
			break;
		}
		System.out.println(command);
		try {
			streamProcess = GSTreamer_send(ip, port, source,command);
			StreamGobbler errorGobbler  = new StreamGobbler(streamProcess.getErrorStream(), false);
		    StreamGobbler outputGobbler = new StreamGobbler(streamProcess.getInputStream(), false);
		    errorGobbler.start();
		    outputGobbler.start();
		    
			KillProcessThread killProcess = new KillProcessThread(streamProcess, isStop);
			killProcess.start();
			System.out.println("wait for");
			streamProcess.waitFor();
			System.out.print("listo");
	        
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}
	
	static Process GSTreamer_send(String ip, String port, String source, String command) throws IOException {
        Runtime runTime= Runtime.getRuntime();
        Process process=null;
        try {
            process = runTime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return process;
    }	
}