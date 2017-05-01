package phaseTwo;

public class StringType implements Type {

	private Bracket bracket;
	
	public StringType(Bracket bracket){
		this.bracket = bracket;
	}
	
	@Override
	public String getValue() {
		return "String" + bracket.getValue();
	}

}
