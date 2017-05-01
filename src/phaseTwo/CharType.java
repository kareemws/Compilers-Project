package phaseTwo;

public class CharType implements Type {

	private Bracket bracket;
	
	public CharType(Bracket bracket){
		this.bracket = bracket;
	}
	@Override
	public String getValue() {
		return "char" + bracket.getValue();
	}

}
