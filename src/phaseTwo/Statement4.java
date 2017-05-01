package phaseTwo;

public class Statement4 implements Statement{
	Identifier identifier;
	AfterIdentifierStmnt afterIdentifierStmnt ;
	
	public Statement4(Identifier identifier, AfterIdentifierStmnt afterIdentifierStmnt ){
		this.identifier = identifier ;
		this.afterIdentifierStmnt = afterIdentifierStmnt;
	}
	
	@Override
	public String getValue(){
		return identifier.getValue() + " " + afterIdentifierStmnt.getValue() + " " ;
	}
	
}
