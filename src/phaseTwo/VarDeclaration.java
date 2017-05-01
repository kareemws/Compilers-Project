package phaseTwo;

public class VarDeclaration {
	Type type;
	Identifier identifier;
	
	public VarDeclaration(Type type , Identifier identifier) {
		this.type = type;
		this.identifier = identifier;
	}
	
	public String getValue() {
		return type.getValue() + " " + identifier.getValue();
	}
}
