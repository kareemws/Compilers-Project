package phaseOne;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class Tokenizer {
	private static ArrayList<RegexPair> tokens;
	
	public static ArrayList<Token>tokenize(String source)
	{
		setTokensMap();
		ArrayList<Token> result = new ArrayList<>();
		Token tempToken = null;
		Pattern pattern;
		Matcher matcher;
		
		for(int i=0; i < tokens.size(); i++)
		{
			pattern = Pattern.compile(tokens.get(i).regex);
			matcher = pattern.matcher(source);
			while(matcher.find())
			{
				tempToken = new Token();
				tempToken.setEndIndex(matcher.end());
				tempToken.setStartIndex(matcher.start());
				tempToken.setLabel(tokens.get(i).label);
				if(matcher.group().equals("\n"))
					tempToken.setValue("\\n");
				else if(i == 2 || i == 3)
				{
					String tempString = matcher.group();
					tempString.replace("\n", "\\n");
					tempString.replace("\r", "\\r");
					tempString.replace("\t", "\\t");
					tempToken.setValue(tempString);
				}
				else
					tempToken.setValue(matcher.group());
				result.add(tempToken);
				String tempString = "";
				for(int j=0; j < matcher.group().length(); j++)
					tempString += " ";
				StringBuilder temp = new StringBuilder(source) ;
				temp.replace(matcher.start(), matcher.end(), tempString);
				source = temp.toString();
			}
		}
		sortResult(result);
		return result;
	}
	
	private static void setTokensMap()
	{
		tokens = new ArrayList<RegexPair>();
		//Comments
		tokens.add(new RegexPair("<COMMENT2>", "/\\*([^*]|[\r\n]|(\\*+([^*/]|[\r\n])))*\\*+/"));
		tokens.add(new RegexPair("<COMMENT1>", "//.*"));
		//Literals
		tokens.add(new RegexPair("<STRING_LITERAL>", "\"(\\\\\"|[^\"]|[\r\n])*\""));
		tokens.add(new RegexPair("<FLOAT_LITERAL>", "\\d+\\.\\d+"));
		tokens.add(new RegexPair("<INTEGRAL_LITERAL>", "\\d+"));
		tokens.add(new RegexPair("<A_CHAR>", "'.*'"));
		//Symbols
		tokens.add(new RegexPair("<SQUOTE>", "'"));
		tokens.add(new RegexPair("<DQUOTE>", "\""));
		tokens.add(new RegexPair("<COMMENT_L>", "/\\*"));
		tokens.add(new RegexPair("<COMMENT_R>", "\\*/"));
		tokens.add(new RegexPair("<EOL>", "\n"));
		tokens.add(new RegexPair("<PLUS>", "\\+"));
		tokens.add(new RegexPair("<LEFT_CURLY_B>", "\\{"));
		tokens.add(new RegexPair("<RIGHT_CURLY_B>", "\\}"));
		tokens.add(new RegexPair("<LEFT_SQUARE_B>", "\\["));
		tokens.add(new RegexPair("<RIGHT_SQUARE_B>", "\\]"));
		tokens.add(new RegexPair("<LEFT_ROUND_B>", "\\("));
		tokens.add(new RegexPair("<RIGHT_ROUND_B>", "\\)"));
		tokens.add(new RegexPair("<COMMA>", ","));
		tokens.add(new RegexPair("<SEMICOLON>", ";"));
		tokens.add(new RegexPair("<DOT>", "\\."));
		tokens.add(new RegexPair("<NOT>", "!"));
		tokens.add(new RegexPair("<EQUAL>", "="));
		tokens.add(new RegexPair("<AND>", "&&"));
		tokens.add(new RegexPair("<MINUS>", "-"));
		tokens.add(new RegexPair("<MULTIPLY>", "\\*"));
		tokens.add(new RegexPair("<LESSTHAN>", "<")) ;
		tokens.add(new RegexPair("<GREATERTHAN>", ">")) ;
		//Keywords
		tokens.add(new RegexPair("<IF>", "(?<!\\w)if(?!\\w)"));
		tokens.add(new RegexPair("<ELSE>", "(?<!\\w)else(?!\\w)")) ;
		tokens.add(new RegexPair("<MAIN>", "(?<!\\w)main(?!\\w)")) ;
		tokens.add(new RegexPair("<THIS>", "(?<!\\w)this(?!\\w)")) ;
		tokens.add(new RegexPair("<TRUE>", "(?<!\\w)true(?!\\w)")) ;
		tokens.add(new RegexPair("<VOID>", "(?<!\\w)void(?!\\w)")) ;
		tokens.add(new RegexPair("<CLASS>", "(?<!\\w)class(?!\\w)")) ;
		tokens.add(new RegexPair("<FALSE>", "(?<!\\w)false(?!\\w)")) ;
		tokens.add(new RegexPair("<WHILE>", "(?<!\\w)while(?!\\w)")) ;
		tokens.add(new RegexPair("<LENGTH>", "(?<!\\w)length(?!\\w)"));
		tokens.add(new RegexPair("<PUBLIC>" ,  "(?<!\\w)public(?!\\w)"));
		tokens.add(new RegexPair("<PRIVATE>" ,  "(?<!\\w)private(?!\\w)"));
		tokens.add(new RegexPair("<RETURN>", "(?<!\\w)return(?!\\w)"));
		tokens.add(new RegexPair("<STATIC>", "(?<!\\w)static(?!\\w)"));
		tokens.add(new RegexPair("<NEW>", "(?<!\\w)new(?!\\w)"));
		tokens.add(new RegexPair("<EXTENDS>", "(?<!\\w)extends(?!\\w)"));
		tokens.add(new RegexPair("<SYSTEM.OUT.PRINTLN>", "(?<!\\w)System.out.println(?!\\w)"));
		
		//Data types
		tokens.add(new RegexPair("<INT>", "(?<!\\w)int(?!\\w)")) ;
		tokens.add(new RegexPair("<STRING>", "(?<!\\w)String(?!\\w)"));
		tokens.add(new RegexPair("<FLOAT>", "(?<!\\w)float(?!\\w)"));
		tokens.add(new RegexPair("<CHARACTER>", "(?<!\\w)char(?!\\w)"));
		tokens.add(new RegexPair("<BOOLEAN>", "(?<!\\w)boolean(?!\\w)"));
		tokens.add(new RegexPair("<ID>", "[^\\s+]+"));
	}

	private static void sortResult(ArrayList<Token> result)
	{
		Token tempToken = null;
		for(int i=0; i < result.size(); i++)
		{
			for(int l=i+1; l < result.size(); l++)
			{
				if(result.get(l).getStartIndex() < result.get(i).getStartIndex())
				{
					tempToken = result.get(l);
					result.set(l, result.get(i));
					result.set(i, tempToken);
				}
			}
		}
	}
}
