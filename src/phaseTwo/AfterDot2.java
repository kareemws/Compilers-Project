package phaseTwo;

import java.util.ArrayList;

public class AfterDot2 implements AfterDot{

	private Identifier identifier;
	private Expression[] expressions;
	
	public AfterDot2(Identifier identifier, Expression... expressions){
		this.identifier = identifier;
		this.expressions = expressions;
	}
	@Override
	public String getValue() {
		String result = identifier.getValue() + "(";
		for(int i=0; i < expressions.length; i++){
			result += "," + expressions[i].getValue();
		}
		result += ")";
		return result;
	}
}
