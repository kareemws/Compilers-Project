package phaseTwo;

public class Type5 implements Type {

	private Bracket bracket;
	
	public Type5(Bracket bracket){
		this.bracket = bracket;
	}
	@Override
	public String getValue() {
		return "char" + bracket.getValue();
	}

}
