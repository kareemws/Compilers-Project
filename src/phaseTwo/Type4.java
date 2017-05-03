package phaseTwo;

public class Type4 implements Type {

	private Bracket bracket;
	
	public Type4(Bracket bracket){
		this.bracket = bracket;
	}
	
	@Override
	public String getValue() {
		return "String" + bracket.getValue();
	}

}
