package phaseTwo;

import java.beans.Expression;

public class UnMatched implements IfStatement {

	private Expression expression;
	private UnMatchedDash unMatchedDash;
	
	public UnMatched(Expression expression, UnMatchedDash unMatchedDash){
		this.expression = expression;
		this.unMatchedDash = unMatchedDash;
	}
	
	@Override
	public String getValue() {
		
		return expression.getValue() + unMatchedDash.getValue();
	}

}
