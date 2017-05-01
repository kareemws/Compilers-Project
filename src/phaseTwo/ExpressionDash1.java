package phaseTwo;

public class ExpressionDash1 implements ExpressionDash {

	private AfterExpression afterExpression;
	private ExpressionDash expressionDash;
	
	public ExpressionDash1(AfterExpression afterExpression, ExpressionDash expressionDash){
		this.afterExpression = afterExpression;
		this.expressionDash = expressionDash;
	}
	
	@Override
	public String getValue() {
		return afterExpression.getValue() + expressionDash.getValue();
	}

}
