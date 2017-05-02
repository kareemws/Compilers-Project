package phaseTwo;

import java.util.ArrayList;

public class ClassDeclaration {

	private Identifier firstIdentifier;
	private Identifier secondIdentifier;
	private VarDeclaration[] varDeclarations;
	private MethodDeclaration[] methodDeclarations;

	public ClassDeclaration(Identifier firstIdentifier, Identifier secondIdentifier,
			VarDeclaration[] varDeclarations, MethodDeclaration...methodDeclarations){
		this.firstIdentifier = firstIdentifier;
		this.secondIdentifier = secondIdentifier;
		this.varDeclarations = varDeclarations;
		this.methodDeclarations = methodDeclarations;
	}
	
	public ClassDeclaration(Identifier firstIdentifier, VarDeclaration[] varDeclarations,
			MethodDeclaration...methodDeclarations){
		this.firstIdentifier = firstIdentifier;
		this.secondIdentifier = null;
		this.varDeclarations = varDeclarations;
		this.methodDeclarations = methodDeclarations;
	}
	
	public String getValue(){
		return "";
	}
}
