package phaseTwo;

public class Statement3 implements Statement{
	Expression expression ;
	
	Statement3(Expression expression){
		this.expression = expression;
	}
	
	@Override
	public String getValue(){
		String result = " System.out.println ( " + expression.getValue() + " ) ; " ;
		return result ;
	}
}
