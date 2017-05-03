package phaseTwo;

public class IfStatement2 implements IfStatement{
	
	private UnMatched unMatched;
	
	public IfStatement2(UnMatched unMatched){
		this.unMatched = unMatched;
	}
	@Override
	public String getValue() {
		return unMatched.getValue();
	}

}
