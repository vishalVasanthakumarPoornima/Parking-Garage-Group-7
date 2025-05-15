package util;
import java.io.*;
import java.time.LocalDateTime;

import exception.*;
import modules.*;

public class FileIO implements Serializable {

	private static final long serialVersionUID = 3062121088622981060L;
	private static final String LOG_FILE = "garage_log.txt";
	
	public static void writeGarageObject(ParkingGarage a1) throws ParkingExceptions, IOException {

		String path = String.format("log/%s%s_parkingGarage.ser", 
				a1.getAddress().getCity(), a1.getAddress().getZipcode());

		File file = new File(path);
		File parent = file.getParentFile();

		if (!parent.exists()) 
		    parent.mkdirs();
		
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
			output.writeObject(a1);
		}
	}
	
	
	public static void writeGarageObject(ParkingGarage a1, String filename) throws ParkingExceptions {

	
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(setGarageFile(filename)));
			
			outStream.writeObject(a1);
		    outStream.close();
		}
	    catch(IOException e){  
	    	throw new ParkingExceptions(e);
	    }
	}
	
	
	public static ParkingGarage readGarageObject(String filename) throws ParkingExceptions{
	
	
		try {
//			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(setGarageFile(filename)));
			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(filename));
			
			ParkingGarage garageObject = (ParkingGarage) inStream.readObject();         // Object cast - specify - deserialize
		    inStream.close();

		    return garageObject;
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch( IOException ex ){
	    	throw new ParkingExceptions(ex);
	    } 
		return null;
	}
	
	
	public static String setGarageFile(String filename) {
		/* setGarageFile - validates the input file and sets file with known file in system. */
		try {
			if (!FixModel.isFileValid(filename))
				throw new ParkingExceptions(1, "File doesn't exist. Replacing with known file");
			
		}catch(ParkingExceptions garageE) {
			return "log/cityexamplezipexample_parkingGarage.ser"; //System.getProperty("user.dir") + 
		}
		return filename;
	}
	
    public static void log(String message) {

        System.out.println(message);
        
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(LocalDateTime.now() + " - " + message);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}