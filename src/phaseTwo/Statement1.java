package phaseTwo;

public class Statement1 implements Statement{
	Statement statement ;
	
	Statement1(Statement statement){
		this.statement = statement;
	}
	
	@Override
	public String getValue(){
		String result = " { " + statement.getValue() + " } " ;
		return result ;
	}
}
