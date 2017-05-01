package phaseTwo;

public class Expression {
	
	private ExpressionTerminals expressionTerminals;
	private ExpressionDash expressionDash;
	
	public Expression(ExpressionTerminals expressionaTerminals, ExpressionDash expressionDash){
		this.expressionTerminals = expressionaTerminals;
		this.expressionDash = expressionDash;
	}
	
	public String getValue(){
		return expressionTerminals.getValue() + expressionDash.getValue();
	}

}
