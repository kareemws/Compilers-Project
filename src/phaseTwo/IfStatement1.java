package phaseTwo;

public class IfStatement1 implements IfStatement {

	private Matched matched;
	public IfStatement1(Matched matched){
		this.matched = matched;
	}
	@Override
	public String getValue() {
		return matched.getValue();
	}

}
