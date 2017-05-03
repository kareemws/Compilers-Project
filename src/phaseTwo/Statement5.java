package phaseTwo;

public class Statement5 implements Statement{
	Identifier identifier;
	AfterIdentifierStmnt afterIdentifierStmnt ;
	
	public Statement5(Identifier identifier, AfterIdentifierStmnt afterIdentifierStmnt ){
		this.identifier = identifier ;
		this.afterIdentifierStmnt = afterIdentifierStmnt;
	}
	
	@Override
	public String getValue(){
		return identifier.getValue() + " " + afterIdentifierStmnt.getValue() + " " ;
	}
	
}
