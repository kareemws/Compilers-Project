package phaseTwo;

public class IntType implements Type {

	private Bracket bracket;
	public IntType(Bracket bracket){
		this.bracket = bracket;
	}
	
	@Override
	public String getValue() {
		return "int" + bracket.getValue();
	}

}
