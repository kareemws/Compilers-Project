package phaseTwo;

public class Type3 implements Type {

	private Bracket bracket;
	
	public Type3(Bracket bracket){
		this.bracket = bracket;
	}
	
	@Override
	public String getValue() {
		return "float" + bracket.getValue();
	}

}
