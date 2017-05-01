package phaseTwo;

public class FloatType implements Type {

	private Bracket bracket;
	
	public FloatType(Bracket bracket){
		this.bracket = bracket;
	}
	
	@Override
	public String getValue() {
		return "float" + bracket.getValue();
	}

}
