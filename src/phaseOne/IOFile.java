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
import java.util.LinkedList;
import java.util.Queue;

public class IOFile {
	public static String readLexicalFile() {
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
	
	public static Queue <Lexeme> readSyntaxFile(){
		String result = "" ;
		Queue <Lexeme> lexemes = new LinkedList<>() ;
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(new File("output.txt")));
			String line;
		    while ((line = buffer.readLine()) != null) {
		       int indexOfColon = line.indexOf(':') ;
		       String token="" , value ="";
		       for(int i=0 ; i < indexOfColon ; i++)
		    	   token += line.charAt(i);
		       for(int i=indexOfColon + 1 ; i < line.length() ; i++)
		    	   value += line.charAt(i);
		       Lexeme lexeme = new Lexeme();
		       lexeme.setLabel(token);
		       lexeme.setValue(value);
		       lexemes.add(lexeme) ;
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lexemes ;
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
