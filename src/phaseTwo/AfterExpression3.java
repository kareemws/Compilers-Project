package phaseTwo;

public class AfterExpression3 implements AfterExpression{
	AfterDot afterDot ;
	
	public AfterExpression3(AfterDot afterDot){
		this.afterDot = afterDot ;
	}
	
	@Override
	public String getValue(){
		return " . " + afterDot.getValue();
	}
}
