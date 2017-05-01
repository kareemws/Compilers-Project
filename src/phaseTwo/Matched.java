package phaseTwo;


public class Matched {
	Expression expression;
	Matched rightMatched;
	Matched leftMatched;
	public Matched(Expression expression , Matched leftMatched , Matched rightMatched) {
		this.expression = expression; 
		this.leftMatched = leftMatched;
		this.rightMatched = rightMatched;
	}
	public String getValue() {
		return "if ( " + expression.getValue() + " ) "  + leftMatched.getValue() + " else " + rightMatched.getValue();
	}
}
