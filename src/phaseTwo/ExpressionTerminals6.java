package phaseTwo;

public class ExpressionTerminals6 implements ExpressionTerminals{

	Expression expression;
	public ExpressionTerminals6 (Expression expression) {
		this.expression = expression;
	}
	@Override
	public String getValue() {
		return "( " + expression.getValue() + " )";
	}
	
}
