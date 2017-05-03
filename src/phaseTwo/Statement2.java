package phaseTwo;

public class Statement2 implements Statement {
	IfStatement ifStatement ;
	
	public Statement2(IfStatement ifStatement) {
		this.ifStatement = ifStatement ;
	}

	@Override
	public String getValue() {
		return ifStatement.getValue() ;
	}
	
	
	
}
