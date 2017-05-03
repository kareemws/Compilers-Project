package phaseTwo;

public class Type1 implements Type {

	private Bracket bracket;
	public Type1(Bracket bracket){
		this.bracket = bracket;
	}
	
	@Override
	public String getValue() {
		return "int" + bracket.getValue();
	}

}
