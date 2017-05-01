package phaseTwo;

public class AfterIdentifierStmnt2 implements AfterIdentifierStmnt{
	Expression leftExpression;
	Expression rightExpression;
	public AfterIdentifierStmnt2(Expression leftExpression , Expression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	@Override
	public String getValue() {
		return "[ " + leftExpression.getValue() + " ] = " + rightExpression.getValue() + " ;";
	}
}
