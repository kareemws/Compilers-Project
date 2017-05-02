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
import java.util.Queue;

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void writeOnFile(Queue<Lexeme> tokens) {
		try {
			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
			for(Lexeme token: tokens) {
				writer.println(token.getLabel() + ": " + token.getValue());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
