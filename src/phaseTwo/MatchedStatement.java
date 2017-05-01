package phaseTwo;

public class MatchedStatement extends Matched{
	Statement statement;
	public MatchedStatement(Statement) {
		this.statement = statement;
	}
	@Override
	public String getValue() {
		return statement.getValue();
	}
}
