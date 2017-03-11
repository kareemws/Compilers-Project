package phaseOne;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
	
	public static void main(String[] args) {	
		String src = IOFile.readFile();
		ArrayList<Token> result = Tokenizer.tokenize(src) ;
		IOFile.writeOnFile(result);
	}
}
