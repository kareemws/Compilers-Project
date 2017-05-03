package phaseTwo;

public class Statement4 implements Statement{
	Expression expression ;
	
	public Statement4(Expression expression){
		this.expression = expression;
	}
	
	@Override
	public String getValue(){
		String result = " System.out.println ( " + expression.getValue() + " ) ; " ;
		return result ;
	}
}
