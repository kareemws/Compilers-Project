package phaseTwo;

import java.util.ArrayList;

public class ClassDeclaration {

	private Identifier firstIdentifier;
	private Identifier secondIdentifier;
	private ArrayList<VarDeclaration> varDeclaration;
	private ArrayList<MethodDeclaration> methodDeclaration;
	
	public CLassDeclaration(Identifier firstIdentifier, Identifier secondIdentifier, ArrayList<VarDeclaration> varDeclaration,
			ArrayList<MethodDeclaration> methodDeclaration){
		this.firstIdentifier = firstIdentifier;
		this.secondIdentifier = secondIdentifier;
		this.varDeclaration = varDeclaration;
		this.methodDeclaration = methodDeclaration;
	}
	
	public CLassDeclaration(Identifier firstIdentifier, Identifier secondIdentifier, ArrayList<VarDeclaration> varDeclaration,
			MethodDeclaration methodDeclaration){
		this.firstIdentifier = firstIdentifier;
		this.secondIdentifier = secondIdentifier;
		this.varDeclaration = varDeclaration;
		this.methodDeclaration = new ArrayList<>();
		this.methodDeclaration.add(methodDeclaration);
	}
	
	public CLassDeclaration(Identifier firstIdentifier, Identifier secondIdentifier, VarDeclaration varDeclaration,
			ArrayList<MethodDeclaration> methodDeclaration){
		this.firstIdentifier = firstIdentifier;
		this.secondIdentifier = secondIdentifier;
		this.varDeclaration = new ArrayList<>();
		this.varDeclaration.add(varDeclaration);
		this.methodDeclaration = methodDeclaration;
	}
	
	public CLassDeclaration(Identifier firstIdentifier, Identifier secondIdentifier, VarDeclaration varDeclaration,
			MethodDeclaration methodDeclaration){
		this.firstIdentifier = firstIdentifier;
		this.secondIdentifier = secondIdentifier;
		this.varDeclaration = new ArrayList<>();
		this.varDeclaration.add(varDeclaration);
		this.methodDeclaration = new ArrayList<>();
		this.methodDeclaration.add(methodDeclaration);
	}

	public CLassDeclaration(Identifier firstIdentifier, ArrayList<VarDeclaration> varDeclaration,
			ArrayList<MethodDeclaration> methodDeclaration){
		this.firstIdentifier = firstIdentifier;
		this.varDeclaration = varDeclaration;
		this.methodDeclaration = methodDeclaration;
	}
	
	public CLassDeclaration(Identifier firstIdentifier, ArrayList<VarDeclaration> varDeclaration,
			MethodDeclaration methodDeclaration){
		this.firstIdentifier = firstIdentifier;
		this.varDeclaration = varDeclaration;
		this.methodDeclaration = new ArrayList<>();
		this.methodDeclaration.add(methodDeclaration);
	}
	
	public CLassDeclaration(Identifier firstIdentifier, VarDeclaration varDeclaration,
			ArrayList<MethodDeclaration> methodDeclaration){
		this.firstIdentifier = firstIdentifier;
		this.varDeclaration = new ArrayList<>();
		this.varDeclaration.add(varDeclaration);
		this.methodDeclaration = methodDeclaration;
	}
	
	public CLassDeclaration(Identifier firstIdentifier, VarDeclaration varDeclaration,
			MethodDeclaration methodDeclaration){
		this.firstIdentifier = firstIdentifier;
		this.varDeclaration = new ArrayList<>();
		this.varDeclaration.add(varDeclaration);
		this.methodDeclaration = new ArrayList<>();
		this.methodDeclaration.add(methodDeclaration);
	}
	
	public String getValue(){
		
	}
}
