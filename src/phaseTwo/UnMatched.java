package phaseTwo;

public class UnMatched {

	private Expression expression;
	private UnMatchedDash unMatchedDash;
	
	public UnMatched(Expression expression, UnMatchedDash unMatchedDash){
		this.expression = expression;
		this.unMatchedDash = unMatchedDash;
	}
	
	public String getValue() {
		
		return expression.getValue() + unMatchedDash.getValue();
	}

}
