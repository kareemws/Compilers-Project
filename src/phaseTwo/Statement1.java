package phaseTwo;

public class Statement1 implements Statement{
	Statement[] statement ;
	
	public Statement1(Statement... statement){
		this.statement = statement;
	}
	
	@Override
	public String getValue(){
		String result = " { ";
		for(int i=0; i < statement.length; i++){
			result += statement[i].getValue();
		}
		return result ;
	}
}
