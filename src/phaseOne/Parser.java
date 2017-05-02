package phaseOne;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import phaseTwo.*;

public class Parser {

	private Queue<Lexeme> lexemes;
	
	public Parser(Queue<Lexeme> lexemes){
		this.lexemes = lexemes;
	}
	
	private void compactQueues(Queue<Lexeme> garabageQueue){
		garabageQueue.addAll(lexemes);
		lexemes = garabageQueue;
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
				else
					return null;
			}
			else
				return null;
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
						else
							return null;
					}
					else
						return null;
				}
				else
					return null;s
			}
			else
				return null;
		}
		else
			return null;
			
	}
	
	
	private AfterNew afterNew(){
		Lexeme temp = lexemes.peek();
		Queue<Lexeme> garabageQueue = new LinkedList<>() ; 
		if(temp.getLabel() == Token.INT){
			Expression expression = expression() ;
			if(expression != null){
				temp = lexemes.poll();
				garabageQueue.add(temp);
				return new AfterNew1(expression) ;
			}
			else{
				compactQueues(garabageQueue);
				return null;
			}
		}
		Identifier identifier = identifier();
		if(identifier != null){
			temp = lexemes.poll();
			garabageQueue.add(temp);
			return new AfterNew2(identifier) ;
		}
		else{
			compactQueues(garabageQueue);
			return null;
		}
	}
	
	private AfterExpression afterExpression(){
		Queue<Lexeme> garabageQueue = new LinkedList<>() ;
		Lexeme temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() == Token.LEFT_SQUARE_B){
			Expression expression = expression() ;
			if(expression != null){
				temp = lexemes.poll();
				garabageQueue.add(temp);
				if(temp.getLabel() == Token.RIGHT_SQUARE_B){
					return new AfterExpression2(expression) ;
				}
				else{
					compactQueues(garabageQueue);
					return null;
				}
			}
			else{
				compactQueues(garabageQueue);
				return null;
			}
		}
		else if(temp.getLabel() == Token.DOT){
			AfterDot afterDot = afterDot() ;
			if(afterDot != null){
				temp = lexemes.poll();
				garabageQueue.add(temp);
				return new AfterExpression3(afterDot) ;
			}
			else{
				compactQueues(garabageQueue);
				return null;
			}
		}
		else if(temp.getLabel() == Token.AND || temp.getLabel() == Token.LESSTHAN || temp.getLabel() == Token.PLUS ||
			temp.getLabel() == Token.MINUS || temp.getLabel() == Token.MULTIPLY){
			BinaryOp binaryOp = binaryOp() ;
			Expression expression = expression() ;
			if(expression != null){
				temp = lexemes.poll();
				garabageQueue.add(temp);
				return new AfterExpression1(expression, binaryOp);
			}
			else{
				compactQueues(garabageQueue);
				return null;
			}
		}
		else{
			compactQueues(garabageQueue);
			return null;
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
		else
			return null;
	}

	
	private Identifier identifier() {
		Lexeme temp = lexemes.peek();
		if(temp.getLabel() != Token.ID)
			return null;
		lexemes.poll();
		return new Identifier(temp.getLabel());
	}
	
	
	
	private IfStatement ifStatement(){
		return unMatched();
	}
	
	private UnMatched unMatched(){
		Queue<Lexeme> garabageQueue = new LinkedList<>();
		
		Lexeme temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.IF){
			compactQueues(garabageQueue);
			return matched();
		}
		
		temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.LEFT_ROUND_B){
			compactQueues(garabageQueue);
			return matched();
		}
		
		Expression expression = expression();
		if(expression == null){
			compactQueues(garabageQueue);
			return matched();
		}
		
		temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.RIGHT_ROUND_B){
			compactQueues(garabageQueue);
			return matched();
		}
		
		UnMatchedDash unMatchedDash = unMatchedDash();
		if(unMatchedDash == null){
			compactQueues(garabageQueue);
			return matched();
		}
		
		return new UnMatched(expression, unMatchedDash);
	}
	
	private Matched matched(){
		return matched1();
	}
	
	private Matched matched1(){
		Queue<Lexeme> garabageQueue = new LinkedList<>();
		
		Lexeme temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.IF){
			compactQueues(garabageQueue);
			return matched2();
		}
		
		temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.LEFT_ROUND_B){
			compactQueues(garabageQueue);
			return matched2();
		}
		
		Expression expression = expression();
		if(expression == null){
			compactQueues(garabageQueue);
			return matched2();
		}
		
		temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.RIGHT_ROUND_B){
			compactQueues(garabageQueue);
			return matched2();
		}
		
		Matched matched1 = matched();
		if(matched1 == null){
			compactQueues(garabageQueue);
			return matched2();
		}
		
		temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.ELSE){
			compactQueues(garabageQueue);
			return matched2();
		}
		
		Matched matched2 = matched();
		if(matched2 == null){
			compactQueues(garabageQueue);
			return matched2();
		}
		
		return new Matched1(expression, matched1, matched2);
	}

	private Matched matched2(){
		Statement statement = statement();
		if(statement == null){
			return null;
		}
		
		return new MatchedStatement(statement);
	}

	private UnMatchedDash unMatchedDash(){
		return unMatchedDash1();
	}
	
	private UnMatchedDash unMatchedDash1(){
		Statement statement = statement();
		if(statement == null){
			return unMatchedDash2();
		}
		
		return new UnMatchedDash1(statement);
	}

	private UnMatchedDash unMatchedDash2(){
		Queue<Lexeme> garabageQueue = new LinkedList<>();
		
		Matched matched = matched();
		if(matched == null){
			return null;
		}
		
		Lexeme temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() == Token.ELSE){
			compactQueues(garabageQueue);
			return null;
		}
		
		UnMatched unMatched = unMatched();
		if(unMatched == null){
			compactQueues(garabageQueue);
			return null;
		}
		
		return new UnMatchedDash2(matched, unMatched);
	}

	private Expression expression(){
		ExpressionTerminals expressionTerminals = ExpressionTerminals();
		if(expressionTerminals == null){
			return null;
		}
		
		ExpressionDash expressionDash = expressionDash();
		if(expressionDash == null){
			return null;
		}
		
		return new Expression(expressionTerminals, expressionDash);
	}

	private ExpressionDash expressionDash(){
		return expressionDash1();
	}
	
	private ExpressionDash expressionDash1(){
		AfterExpression afterExpression = afterExpression();
		if(afterExpression == null){
			return expressionDash2();
		}
		
		ExpressionDash expressionDash = expressionDash();
		if(expressionDash == null)
			return expressionDash2();
		
		return new ExpressionDash1(afterExpression, expressionDash);
	}
	
	private ExpressionDash expressionDash2(){
		return new ExpressionDash2();
	}

	private AfterDot afterDot(){
		return afterDot1();
	}
	
	private AfterDot afterDot1(){
		Queue<Lexeme> garabageQueue = new LinkedList<>();
		Lexeme temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.LENGTH){
			compactQueues(garabageQueue);
			return afterDot2();
		}
		
		return new AfterDot1();
	}
	
	private AfterDot afterDot2(){
		Queue<Lexeme> garabageQueue = new LinkedList<>();
		
		Identifier identifier = identifier();
		if(identifier == null){
			return null;
		}
		
		Lexeme temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.LEFT_ROUND_B){
			compactQueues(garabageQueue);
			return null;
		}
		
		ArrayList<Expression> expressions = new ArrayList<>();
		Expression expression;
		for(int i=0; true; i++){
			if(i == 0){
				expression = expression();
				if(expression == null)
					break;
				expressions.add(expression);
			}else
			{
				temp = lexemes.poll();
				if(temp.getLabel() != Token.COMMA){
					break;
				}else{
					garabageQueue.add(lexemes.poll());
					expression = expression();
					if(expression == null){
						compactQueues(garabageQueue);
						return null;
					}
					expressions.add(expression);
				}
			}
		}

		temp = lexemes.poll();
		garabageQueue.add(temp);
		if(temp.getLabel() != Token.RIGHT_ROUND_B){
			compactQueues(garabageQueue);
			return null;
		}
		
		Expression[] expressionsArray = null;
		expressions.toArray(expressionsArray);
		return new AfterDot2(identifier, expressionsArray);
	}

}