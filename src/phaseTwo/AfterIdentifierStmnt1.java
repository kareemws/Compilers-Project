package phaseTwo;

public class AfterIdentifierStmnt1 implements AfterIdentifierStmnt{
	Expression expression;
	public AfterIdentifierStmnt1(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public String getValue() {
		return "= " + expression.getValue() + " ;";
	}
	
	
}
