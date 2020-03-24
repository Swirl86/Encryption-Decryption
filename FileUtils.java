import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {
	
	public static boolean writeToFile(String fileName, String text){
		boolean saved = false;
		try {			
			FileWriter writer = new FileWriter(fileName);
			writer.write(text);
			writer.close();
			saved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return saved;
	}
	
	public static String readFromFile(String fileName){

		String value = "";
		try {
			
			List<String> rows = Files.readAllLines(Paths.get(fileName));
			for (String row : rows) {
				value += row;				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}