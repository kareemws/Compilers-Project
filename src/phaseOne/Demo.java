package phaseOne;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
	
	public static void main(String[] args)
	{	

		ArrayList<Token> resultCatcher = Tokenizer.tokenize("int intvalue = 10+5; jkhakjdfhkas@@hfkjaskjf  ddfsdafsdf%%$#");
		for(int i=0; i < resultCatcher.size(); i++)
		System.out.println(resultCatcher.get(i).getLabel() + ": " + 
				resultCatcher.get(i).getValue() + "\t" + resultCatcher.get(i).getStartIndex());
		
	}
}
