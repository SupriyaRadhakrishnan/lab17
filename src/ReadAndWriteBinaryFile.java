import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadAndWriteBinaryFile {

	public static void main(String[] args) {
		
String filename = "out.dat";
try {
	FileOutputStream fos = new FileOutputStream(filename);
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeInt(2048);
	oos.writeUTF("Hello");
	oos.close();
}
	
    catch (FileNotFoundException e) {
	
	e.printStackTrace();
}	catch (IOException e) {
	
	e.printStackTrace();
}
System.out.println("Done Writing");
System.out.println("Done Reading");


try {
	FileInputStream fis = new FileInputStream(filename);
	ObjectInputStream ois = new ObjectInputStream(fis);
	//you have to read the data always in the same order we wrote it into the file
	int num = ois.readInt();
	String str = ois.readUTF();
	System.out.println(num +" "+str);
}
	
    catch (FileNotFoundException e) {

	e.printStackTrace();
}	catch (IOException e) {
	
	e.printStackTrace();
}
	}

}
