package phaseTwo;

public class Statement2 implements Statement{
	Statement statement ;
	Expression expression ;
	
	Statement2(Statement statement, Expression expression){
		this.statement = statement;
		this.expression = expression;
	}
	
	@Override
	public String getValue(){
		String result = " while ( " + expression.getValue() + " ) " + statement.getValue() + " " ;
		return result ;
	}
}
