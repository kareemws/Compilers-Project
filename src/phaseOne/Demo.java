package phaseOne;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import phaseTwo.Goal;

public class Demo {
	
	public static void main(String[] args) {
		// lexical
		
		String src = IOFile.readLexicalFile();
		Queue<Lexeme> result = Tokenizer.tokenize(src) ;
		IOFile.writeOnFile(result);
		
		// Syntax 
		
		Queue <Lexeme> lexemes = IOFile.readSyntaxFile() ;
		Parser parser = new Parser(lexemes) ;
		Goal goal = parser.parse();
		System.out.println(goal.getValue());
	}
}
