package phaseTwo;

public class UnMatchedDash2 implements UnMatchedDash {

	private Matched matched;
	private UnMatched unMatched;
	
	public UnMatchedDash2(Matched matched, UnMatched unMatched){
		this.matched = matched;
		this.unMatched = unMatched;
	}
	public String getValue() {
		return matched.getValue() + "else" + unMatched.getValue();
	}

}
