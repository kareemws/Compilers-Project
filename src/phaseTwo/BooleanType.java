package phaseTwo;

public class BooleanType implements Type {

	private Bracket bracket;
	
	public BooleanValue(Bracket bracket){
		this.bracket = bracket;
	}
	@Override
	public String getValue() {
		return "boolean" + bracket.getValue();
	}

}
