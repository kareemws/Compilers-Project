package phaseTwo;

import java.util.ArrayList;

public class PublicMethodDeclaration implements MethodDeclaration{
	Type type;
	Identifier identifier;
	ArrayList <Type> types = new ArrayList<>();
	ArrayList <Identifier> identifiers = new ArrayList<>();
	ArrayList<VarDeclaration> varDeclarations  = new ArrayList<>();
	ArrayList<Statement> statements = new ArrayList<>();;
	Expression expression ;
	
	public PublicMethodDeclaration(Type type, Identifier identifier, ArrayList<VarDeclaration> varDeclarations, ArrayList<Statement> statements, Expression expression){
		this.type = type ;
		this.identifier = identifier;
		this.types = null ;
		this.identifiers = null ;
		this.varDeclarations = varDeclarations ;
		this.statements = statements ;
		this.expression = expression;
	}
	
	public PublicMethodDeclaration(Type type, Identifier identifier, ArrayList<VarDeclaration> varDeclarations, ArrayList<Statement> statements, Expression expression, Type type2, Identifier identifier2 ){
		this.type = type ;
		this.identifier = identifier;
		this.types = null ;
		this.identifiers = null ;
		this.varDeclarations = varDeclarations ;
		this.statements = statements ;
		this.expression = expression;
	}
	
	public PublicMethodDeclaration(Type type, Identifier identifier, ArrayList<VarDeclaration> varDeclarations, ArrayList<Statement> statements, Expression expression, ArrayList<Type> types ,ArrayList<Identifier> identifiers ){
		this.type = type ;
		this.identifier = identifier;
		this.types = null ;
		this.identifiers = null ;
		this.varDeclarations = varDeclarations ;
		this.statements = statements ;
		this.expression = expression;
	}
	
	@Override
	public String getValue(){
		String result = " public " + type.getValue() + " " + identifier.getValue() + " ( " ;
		for(int i=0 ; i < types.size() ; i++){
			if(i == 0)
				result += types.get(i).getValue() + " " + identifiers.get(i).getValue() + " " ;
			else
				result += " , " + types.get(i).getValue() + " " + identifiers.get(i).getValue() + " " ;
		}
		result += " ) { " ;
		for(int i=0 ; i < varDeclarations.size() ; i++)
			result += varDeclarations.get(i).getValue() + " " ;
		for(int i=0 ; i < statements.size() ; i++)
			result += statements.get(i).getValue() + " " ;
		result += " return " + expression.getValue() + " ; } " ;
		return result ;
	}
	
	// end class
}
