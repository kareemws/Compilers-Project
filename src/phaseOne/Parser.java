package phaseOne;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import phaseTwo.AfterDot;
import phaseTwo.AfterDot1;
import phaseTwo.AfterExpression;
import phaseTwo.AfterExpression1;
import phaseTwo.AfterExpression2;
import phaseTwo.AfterExpression3;
import phaseTwo.AfterIdentifierStmnt;
import phaseTwo.AfterIdentifierStmnt1;
import phaseTwo.AfterIdentifierStmnt2;
import phaseTwo.BinaryOp;
import phaseTwo.BinaryOp1;
import phaseTwo.BinaryOp2;
import phaseTwo.BinaryOp3;
import phaseTwo.BinaryOp4;
import phaseTwo.BinaryOp5;
import phaseTwo.BooleanType;
import phaseTwo.Bracket;
import phaseTwo.CharType;
import phaseTwo.ClassDeclaration;
import phaseTwo.Expression;
import phaseTwo.Epsilon;
import phaseTwo.ExistingBracket;
import phaseTwo.FloatType;
import phaseTwo.Goal;
import phaseTwo.Identifier;
import phaseTwo.IntType;
import phaseTwo.MainClass;
import phaseTwo.MethodDeclaration;
import phaseTwo.PrivateMethodDeclaration;
import phaseTwo.PublicMethodDeclaration;
import phaseTwo.Statement;
import phaseTwo.Statement4;
import phaseTwo.Statement5;
import phaseTwo.StringType;
import phaseTwo.Type;
import phaseTwo.VarDeclaration;

public class Parser {

	private Queue<Lexeme> lexemes;
	
	public Parser(Queue<Lexeme> lexemes){
		this.lexemes = lexemes;
	}
	
	public Goal parse(){
		return goal();
	}
	
	private Goal goal(){
		MainClass mainClass = mainClass();
		ArrayList<ClassDeclaration> classDeclarations = new ArrayList<>();
		while(true){
			ClassDeclaration tempClassDeclaration = classDeclaration();
			if(tempClassDeclaration == null)
				break;
			classDeclarations.add(tempClassDeclaration);
		}
		ClassDeclaration[] classDeclarationsArray = null;
		classDeclarations.toArray(classDeclarationsArray);
		return new Goal(mainClass, classDeclarationsArray);
	}
	
	private MainClass mainClass(){
		Lexeme temp = lexemes.peek();
		if(temp.getLabel() != Token.CLASS){
			System.out.println("Error");
			return null;
		}
		Identifier firstIdentifier = identifier();

		lexemes.poll();
		temp = lexemes.peek();
		if(temp.getLabel() != Token.LEFT_CURLY_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.PUBLIC){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.STATIC){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.VOID){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.MAIN){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.LEFT_ROUND_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.STRING){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.LEFT_SQUARE_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.RIGHT_SQUARE_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		Identifier secondIdentifier = identifier();

		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.RIGHT_ROUND_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.LEFT_CURLY_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		Statement statement = statement();

		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.RIGHT_CURLY_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		lexemes.poll();temp = lexemes.peek();
		if(temp.getLabel() != Token.RIGHT_CURLY_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		return new MainClass(firstIdentifier, secondIdentifier, statement);
	}
	
	private ClassDeclaration classDeclaration(){
		Lexeme temp = lexemes.peek();
		if(temp.getLabel() != Token.CLASS){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		Identifier firstIdentifier = identifier();
		Queue<Lexeme> tempLexemes = lexemes;
		temp = lexemes.poll();
		Identifier secondIdentifier = null;
		/*if(temp.getLabel() == Token.EXTENDS){
			secondIdentifier = identifier();
			if(secondIdentifier == null){
				System.out.println("Error");
				lexemes = tempLexemes;
				return null;
			}
		}*/
		
		temp = lexemes.peek();
		if(temp.getLabel() != Token.LEFT_CURLY_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		
		ArrayList<VarDeclaration> varDeclarations = new ArrayList<>();
		while(true){
			VarDeclaration tempVarDeclaration = varDeclaration();
			if(tempVarDeclaration == null)
				break;
			varDeclarations.add(tempVarDeclaration);
		}
		
		
		ArrayList<MethodDeclaration> methodDeclarations = new ArrayList<>();
		while(true){
			MethodDeclaration tempMethodDeclaration = methodDeclaration();
			if(tempMethodDeclaration == null){
				break;
			}
			methodDeclarations.add(tempMethodDeclaration);
		}
		VarDeclaration[] varDeclarationsArray = null;
		varDeclarations.toArray(varDeclarationsArray);
		MethodDeclaration[] methodDeclarationsArray = null;
		methodDeclarations.toArray(methodDeclarationsArray);
		if(secondIdentifier == null)
			return new ClassDeclaration(firstIdentifier, varDeclarationsArray,
					methodDeclarationsArray);
		else
			return new ClassDeclaration(firstIdentifier, secondIdentifier, varDeclarationsArray,
					methodDeclarationsArray);
	}
	

	
	private VarDeclaration varDeclaration() {
		Type type = type();
		Identifier identifier = identifier();
		Lexeme temp = lexemes.peek();
		if(temp.getLabel() != Token.SEMICOLON){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		return new VarDeclaration(type, identifier);
	}
	
	private MethodDeclaration methodDeclaration(){
		Lexeme temp = lexemes.peek();
		String accessModifier;
		if(temp.getLabel() == Token.PRIVATE){
			accessModifier = temp.getLabel();
			lexemes.poll();
		}
		else if(temp.getLabel() == Token.PUBLIC){
			accessModifier = temp.getLabel();
			lexemes.poll();
		}
		
		Type type = type();
		Identifier identifier = identifier();
		temp = lexemes.peek();
		if(temp.getLabel() != Token.LEFT_ROUND_B){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		
		temp = lexemes.peek();
		ArrayList<Type> types = new ArrayList<>();
		ArrayList<Identifier> identifiers = new ArrayList<>();
		if(temp.getLabel() != Token.RIGHT_ROUND_B){
			while(true){
				Type tempType = type();
				if(tempType != null)
					types.add(tempType);
				else{
					System.out.println("Error");
					return null;
				}
				Identifier tempIdentifier = identifier();
				if(tempIdentifier != null)
					identifiers.add(tempIdentifier);
				else{
					System.out.println("Error");
					return null;
				}
				
				temp = lexemes.peek();
				if(temp.getLabel() != Token.COMMA){
					System.out.println("Error");
					return null;
				}
			}
			temp = lexemes.peek();
			if(temp.getLabel() != Token.RIGHT_ROUND_B){
				System.out.println("Error");
				return null;
			}
		}else{
			lexemes.poll();
		}
		
		temp = lexemes.peek();
		if(temp.getLabel() != Token.LEFT_CURLY_B){
			System.out.println("Error");
			return null;
		}
		
		ArrayList<VarDeclaration> varDeclarations = new ArrayList<>();
		while(true){
			VarDeclaration tempVarDeclaration = varDeclaration();
			if(varDeclarations == null){
				break;
			}
			varDeclarations.add(tempVarDeclaration);
		}
		
		ArrayList<Statement> statements = new ArrayList<>();
		while(true){
			Statement statement = statement();
			if(statement == null){
				break;
			}
			statements.add(statement);
		}
		
		temp = lexemes.peek();
		if(temp.getLabel() != Token.RETURN){
			System.out.println("Error");
			return null;
		}else
			lexemes.poll();
		
		Expression expression = expression();
		
		temp = lexemes.peek();
		if(temp.getLabel() != Token.SEMICOLON){
			System.out.println("Error");
			return null;
		}else
			lexemes.poll();
		
		temp = lexemes.peek();
		if(temp.getLabel() != Token.RIGHT_CURLY_B){
			System.out.println("Error");
			return null;
		}else
			lexemes.poll();
		
		if(accessModifier == Token.PRIVATE)
			return new PrivateMethodDeclaration(type, identifier, types, identifiers, varDeclarations, statements, expression);
		else
			return new PublicMethodDeclaration(type, identifier, types, identifiers, varDeclarations, statements, expression);
	}
	private Type type(){
		Bracket bracket = bracket() ;
		Lexeme temp = lexemes.peek();
		if(temp.getLabel() == Token.INT){
			lexemes.poll() ;
			return new IntType(bracket) ;
		}
		else if(temp.getLabel() == Token.BOOLEAN){
			lexemes.poll() ;
			return new BooleanType(bracket) ;
		}
		else if(temp.getLabel() == Token.FLOAT){
			lexemes.poll() ;
			return new FloatType(bracket) ;
		}
		else if(temp.getLabel() == Token.STRING){
			lexemes.poll() ;
			return new StringType(bracket) ;
		}
		else if(temp.getLabel() == Token.CHARACTER){
			lexemes.poll() ;
			return new CharType(bracket) ;
		}
		System.out.println("Error");
		return null ;
		
	}
	
	private Bracket bracket(){
		Queue<Lexeme> tempLexemes = lexemes ;
		Lexeme temp1 = tempLexemes.poll();
		Lexeme temp2 = tempLexemes.peek();
		if(temp1.getLabel() == Token.LEFT_SQUARE_B && temp2.getLabel() == Token.RIGHT_SQUARE_B ){
			lexemes.poll() ;
			lexemes.poll() ;
			return new ExistingBracket();
		}
		else if(temp1.getLabel() != Token.LEFT_SQUARE_B || temp2.getLabel() != Token.RIGHT_SQUARE_B ){
			System.out.println("Error");
			return null ;
		}
		else
			return new Epsilon();
	}
	
//	ArrayList <Statement> statements = new ArrayList<>() ;
//	
//	private Statement statement(){
//		return statement1() ;
//	}
//	
//	private Statement statement1(){
//		Lexeme tempLexeme = lexemes.poll();
//
//		if(tempLexeme.getLabel() == Token.LEFT_CURLY_B){
//			statements.add(statement());
//			tempLexeme = lexemes.poll() ;
//			if(tempLexeme.getLabel() != Token.RIGHT_CURLY_B){
//				System.out.println("Error, Missed \"}\" ");
//				return null ;
//			}
//		}
//		else{
//			//return statement4() ;
//		}
//	}
//	
//	private Statement statement4(){
//		Identifier identifier = identifier();
//		AfterIdentifierStmnt afterIdentifierStmnt = afterIdentifierStmnt();
//		return;
//	}
	
	private AfterIdentifierStmnt afterIdentifierStmnt(){
		Expression expression1 = expression() ;
		Expression expression2 = expression() ;
		Queue<Lexeme> tempLexemes = lexemes ;
		Lexeme temp1 = tempLexemes.poll();
		if(temp1.getLabel() == Token.EQUAL){
			Lexeme temp2 = tempLexemes.poll();
			if(temp2.getLabel() == Token.SEMICOLON){
				if(expression1 != null){
					lexemes.poll();
					lexemes.poll();
					return new AfterIdentifierStmnt1(expression) ;
				}
				else{
					System.out.println("Error");
					return null ;
				}
			}
			else{
				System.out.println("Error");
				return null ;
			}
		}
		else if(temp1.getLabel() == Token.LEFT_SQUARE_B){
			Lexeme temp2 = tempLexemes.poll();
			if(temp2.getLabel() == Token.RIGHT_SQUARE_B){
				Lexeme temp2 = tempLexemes.poll();
				if(temp2.getLabel() == Token.EQUAL){
					Lexeme temp2 = tempLexemes.poll();
					if(temp2.getLabel() == Token.SEMICOLON){
						if(expression1 != null && expression2 != null){
							lexemes.poll();
							lexemes.poll();
							lexemes.poll();
							lexemes.poll();
							return new AfterIdentifierStmnt2(expression) ;
						}
						else{
							System.out.println("Error");
							return null ;
						}
					}
					else{
						System.out.println("Error");
						return null ;
					}
				}
				else{
					System.out.println("Error");
					return null ;
				}
			}
			else{
				System.out.println("Error");
				return null ;
			}
		}
		else{
			System.out.println("Error");
			return null ;
		}
			
	}
	
	private AfterExpression afterExpression(){
		Expression expression = expression() ;
		AfterDot afterDot = afterDot() ;
		BinaryOp binaryOp = binaryOp();
		if(expression != null){
			Queue<Lexeme> tempLexemes = lexemes ;
			Lexeme temp = tempLexemes.poll();
			if(temp.getLabel() == Token.LEFT_SQUARE_B){
				temp = tempLexemes.poll();
				if(temp.getLabel() == Token.RIGHT_SQUARE_B){
					lexemes.poll() ;
					lexemes.poll() ;
					return new AfterExpression2(expression) ;
				}
				else{
					System.out.println("Error");
					return null ;
				}
			}
			else if(temp.getLabel() == Token.DOT){
				lexemes.poll();
				return new AfterExpression3(afterDot) ;
			}
			else if(temp.getLabel() == Token.AND || temp.getLabel() == Token.LESSTHAN || temp.getLabel() == Token.PLUS ||
					temp.getLabel() == Token.MINUS || temp.getLabel() == Token.MULTIPLY){
				lexemes.poll();
				return new AfterExpression1(expression, binaryOp);
			}
			else{
				System.out.println("Error");
				return null ;
			}
		}
		else{
			System.out.println("Error");
			return null ;
		}
	}
	
	private BinaryOp binaryOp(){
		Lexeme temp = lexemes.peek();
		if(temp.getLabel() == Token.AND){
			lexemes.poll();
			return new BinaryOp1() ;
		}
		else if(temp.getLabel() == Token.LESSTHAN){
			lexemes.poll();
			return new BinaryOp2() ;
		}
		else if(temp.getLabel() == Token.PLUS){
			lexemes.poll();
			return new BinaryOp3() ;
		}
		else if(temp.getLabel() == Token.MINUS){
			lexemes.poll();
			return new BinaryOp4() ;
		}
		else if(temp.getLabel() == Token.MULTIPLY){
			lexemes.poll();
			return new BinaryOp5() ;
		}
		else{
			System.out.println("Error");
			return null ;
		}
	}
	
	private AfterDot afterDot(){
		
	}
	
	
	private Identifier identifier() {
		Lexeme temp = lexemes.peek();
		if(temp.getLabel() != Token.ID){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		return new Identifier(temp.getLabel());
	}
	
	
}
