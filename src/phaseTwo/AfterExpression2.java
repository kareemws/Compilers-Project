package phaseTwo;

public class AfterExpression2 implements AfterExpression{
	Expression expression ;
	
	public AfterExpression2(Expression expression){
		this.expression = expression ;
	}
	
	@Override
	public String getValue(){
		return " [ " + expression.getValue() + " ] " ;
	}
}
