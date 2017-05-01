package phaseTwo;

public class ExpressionTerminals5 implements ExpressionTerminals{

	Expression expression;
	public ExpressionTerminals5 (Expression expression) {
		this.expression = expression;
	}
	@Override
	public String getValue() {
		return "! " + expression.getValue();
	}
	
}
