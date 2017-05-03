package phaseTwo;

public class Matched2 implements Matched{
	Statement statement;
	public Matched2(Statement statement) {
		this.statement = statement;
	}
	@Override
	public String getValue() {
		return statement.getValue();
	}
}
