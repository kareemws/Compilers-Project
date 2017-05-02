package phaseTwo;

public class Statement5 implements Statement {
	IfStatement ifStatement ;
	
	public Statement5(IfStatement ifStatement) {
		this.ifStatement = ifStatement ;
	}

	@Override
	public String getValue() {
		return ifStatement.getValue() ;
	}
	
	
	
}
