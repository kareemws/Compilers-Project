package phaseOne;

import java.util.ArrayList;
import java.util.Queue;

import phaseTwo.ClassDeclaration;
import phaseTwo.Expression;
import phaseTwo.Goal;
import phaseTwo.Identifier;
import phaseTwo.MainClass;
import phaseTwo.MethodDeclaration;
import phaseTwo.PrivateMethodDeclaration;
import phaseTwo.PublicMethodDeclaration;
import phaseTwo.Statement;
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
	
	private Identifier identifier() {
		Lexeme temp = lexemes.peek()
		if(temp.getLabel() != Token.ID){
			System.out.println("Error");
			return null;
		}
		lexemes.poll();
		return new Identifier(temp.getLabel());
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
			return new PublicMethodDeclaration(type, identifier, types, identifiers, varDeclarations, statements, expression)
	}
}
