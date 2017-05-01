package phaseTwo;

public class AfterNew1 implements AfterNew {

	private Expression expression;
	
	public AfterNew1(Expression expression){
		this.expression = expression;
	}
	
	@Override
	public String getValue() {
		return "int[" + expression.getValue() + "]"; 
	}

}
