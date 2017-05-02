package phaseOne;

import java.util.ArrayList;
import java.util.Queue;

import phaseTwo.ClassDeclaration;
import phaseTwo.Goal;
import phaseTwo.Identifier;
import phaseTwo.MainClass;
import phaseTwo.MethodDeclaration;
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
		ClassDeclaration[] classDeclarations = classDeclarations();
		return new Goal(mainClass, classDeclarations);
	}
	
	private MainClass mainClass(){
		Identifier firstIdentifier = identifier();
		Identifier secondIdentifier = identifier();
		Statement statement = statement();
		return new MainClass(firstIdentifier, secondIdentifier, statement);
	}
	
	private ClassDeclaration classDeclaration(){
		Identifier firstIdentifier = identifier();
		Identifier secondIdentifier = identifier();
		VarDeclaration[] varDeclarations = varDeclarations();
		MethodDeclaration[] methodDeclarations = methodDeclarations();
		if(secondIdentifier == null)
			return new ClassDeclaration(firstIdentifier, varDeclarations, methodDeclarations);
		else
			return new ClassDeclaration(firstIdentifier, secondIdentifier, varDeclarations, methodDeclarations);
	}
	
	private Identifier identifier() {
		String identifierValue = IdentifierValue();
		return new Identifier(identifierValue);
	}
	
	private VarDeclaration varDeclaration() {
		Type type = type();
		Identifier identifier = identifier();
		return new VarDeclaration(type, identifier);
	}
	
}
