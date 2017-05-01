package phaseTwo;

public class UnMatchedDash1 implements UnMatchedDash {

	private Statement statement;
	
	public UnMatchedDash1(Statement statement) {
		this.statement = statement;
	}
	
	@Override
	public String getValue() {
		return statement.getValue();
	}

}
