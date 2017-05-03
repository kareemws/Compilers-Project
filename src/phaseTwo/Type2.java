package phaseTwo;

public class Type2 implements Type {

	private Bracket bracket;
	
	public Type2(Bracket bracket){
		this.bracket = bracket;
	}
	@Override
	public String getValue() {
		return "boolean" + bracket.getValue();
	}

}
