package lib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
	ObjectInputStream ois; 
	ObjectOutputStream oos;
	
	public FileManager() {
	}
	

	
	public void getData(Map<String, Client> clientsList) {

		try {
	        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../bankonet-lib/data.list"));
	        Object readMap = ois.readObject();
	        if(readMap != null && readMap instanceof HashMap) {
	            clientsList.putAll((HashMap) readMap);
	        }
	        ois.close();
	    } catch (Exception e) {
	        // Catch exceptions
	    }
	}
	
	public void writeData(Map<String, Client> clientsList) {
		
		try {
			oos = new ObjectOutputStream (new FileOutputStream("../bankonet-lib/data.list"));
			 oos.writeObject(clientsList);
		        oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
