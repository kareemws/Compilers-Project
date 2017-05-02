package phaseTwo;


public class Matched1 implements Matched{
	Expression expression;
	Matched rightMatched;
	Matched leftMatched;
	public Matched1(Expression expression , Matched leftMatched , Matched rightMatched) {
		this.expression = expression; 
		this.leftMatched = leftMatched;
		this.rightMatched = rightMatched;
	}
	
	public Matched1() {
		
	}
	public String getValue() {
		return "if ( " + expression.getValue() + " ) "  + leftMatched.getValue() + " else " + rightMatched.getValue();
	}
}
