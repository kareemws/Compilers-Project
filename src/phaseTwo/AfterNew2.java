package phaseTwo;

public class AfterNew2 implements AfterNew{

	private Identifier identifier;

	public AfterNew2(Identifier identifier){
		this.identifier = identifier;
	}
	
	@Override
	public String getValue() {
		return identifier.getValue() + "()";
	}
}
