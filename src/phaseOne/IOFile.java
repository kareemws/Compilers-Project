package phaseOne;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class IOFile {
	public static String readFile() {
		String result = "";
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(new File("input.txt")));
			String line;
		    while ((line = buffer.readLine()) != null) {
		       result += line + "\n";
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void writeOnFile(ArrayList<Token> tokens) {
		try {
			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
			for(Token token: tokens) {
				System.out.println(token.getLabel() + ": " + token.getValue());
				writer.println(token.getLabel() + ": " + token.getValue());
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
