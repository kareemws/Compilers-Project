package phaseTwo;

public class ExpressionTerminals7 implements ExpressionTerminals{
	Identifier identifier;
	
	public ExpressionTerminals7(Identifier identifier) {
		this.identifier = identifier;
	}
	
	@Override
	public String getValue() {
		return identifier.getValue();
	}
	
}
