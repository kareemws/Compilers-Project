package phaseTwo;

public class Statement3 implements Statement{
	Expression expression ;
	Statement statement ;
	
	public Statement3(Expression expression, Statement statement){
		this.statement = statement;
		this.expression = expression;
	}
	
	@Override
	public String getValue(){
		String result = " while ( " + expression.getValue() + " ) " + statement.getValue() + " " ;
		return result ;
	}
}
