import java.util.*;

public class Crypted {
	/*
	 	If there is no -mode, the program should work in enc mode.
		If there is no -key, the program should consider that key = 0.
		If there is no -data, and there is no -in the program should assume that the data is an empty string.
		If there is no -out argument, the program must print data to the standard output.
		If there are both -data and -in arguments, your program should prefer -data over -in.
		
		If there is a non-standard situation (an input file does not exist or an argument doesn’t have a value), the program should not fail. 
		Instead, it must display a clear message about the problem and stop successfully. The message should contain the word "Error" in any case.
		
		When starting the program, the necessary algorithm should be specified by an argument (-alg). 
		The first algorithm should be named shift, the second one should be named unicode. If there is no -alg you should default it to shift.
	 */
	
	public static void main(String[] args) {
		
		List<String> arguments = Arrays.asList(args);
		//System.out.println(Arrays.toString(args));
		
		String mode = arguments.contains("-mode") ? getArgument("-mode", arguments) : "enc";
		String data = arguments.contains("-data") ? getArgument("-data", arguments) : ""; 
		int key = arguments.contains("-key") ? Integer.parseInt(getArgument("-key", arguments)) : 0;  
		
		String in = arguments.contains("-in") ? getArgument("-in", arguments) : "";
		String out = arguments.contains("-out") ? getArgument("-out", arguments) : in;
		
		String algorithm  = arguments.contains("-alg") ? getArgument("-alg", arguments) : "shift";
		String cryptedData = "";

		if(!data.equals("")) {
			// If there are both -data and -in arguments, your program should prefer -data over -in.
			cryptedData = algorithm.equals("unicode") ? getCryptedData(mode, data, key) : getShiftedData(mode, data, key);
			System.out.println(cryptedData);
		} else if(!in.equals("") && !out.equals("")){
			// No empty in file
			data = FileUtils.readFromFile(in);
			cryptedData = algorithm.equals("unicode") ? getCryptedData(mode, data, key) : getShiftedData(mode, data, key);
			FileUtils.writeToFile(out, cryptedData);
		} else {
			 System.out.println("Error");
		}
	}

	private static String getShiftedData(String mode, String data, int key) {
		String shiftedData = "";
		switch (mode) {
	        case "enc":
	        	shiftedData = Shift.shiftText(data, key);
	            break;
	        case "dec":
	        	shiftedData = Shift.shiftText(data, key * -1);
	            break;
	        default:
	            System.out.println("Unknown operation");
	            break;
	    }
		return shiftedData;
	}

	private static String getCryptedData(String mode, String data, int key) {
		String cryptData = "";
		switch (mode) {
	        case "enc":
	        	cryptData = Unicode.encode(data, key);
	            break;
	        case "dec":
	        	cryptData = Unicode.decode(data, key);
	            break;
	        default:
	            System.out.println("Unknown operation");
	            break;
	    }
		return cryptData;
	}

	private static String getArgument(String option, List<String> arguments) {
		String value = "Unknown operation";
		
		for (int i = 0; i < arguments.size(); i++) {			
        	if(arguments.get(i).equals(option)) {
        		value = arguments.get(i+1);
        		break;
        	}            
		}
		
		return value;
	}
}

// ********** EXAMPLES *********
/*
	java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5 -alg unicode
	This command must get data from the file road_to_treasure.txt, encrypt the data with the key 5, create a file called protected.txt and write ciphertext to it.
	
	******************************************************************************************************************************************************
	java Main -mode enc -key 5 -data "Welcome to hyperskill!" -alg unicode
	
	Output:
    \jqhtrj%yt%m~ujwxpnqq&
    
    ******************************************************************************************************************************************************
    java Main -key 5 -alg unicode -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec
    
    Output:
    Welcome to hyperskill!
    
    ******************************************************************************************************************************************************
    java Main -key 5 -alg shift -data "Welcome to hyperskill!" -mode enc
    
    Output:
	Bjqhtrj yt mdujwxpnqq!
	
	******************************************************************************************************************************************************
	java Main -key 5 -alg shift -data "Bjqhtrj yt mdujwxpnqq!" -mode dec
		
	Output:
	Welcome to hyperskill!
*/