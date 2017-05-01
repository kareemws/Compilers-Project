package phaseTwo;

public class AfterExpression1 implements AfterExpression{
	Expression expression ;
	BinaryOp binaryOp ;
	
	public AfterExpression1(Expression expression, BinaryOp binaryOp){
		this.expression = expression ;
		this.binaryOp = binaryOp;
	}
	
	@Override
	public String getValue(){
		String result ;
		result = binaryOp.getValue() + " " + expression.getValue() + " ";
		return result;
	}
}
