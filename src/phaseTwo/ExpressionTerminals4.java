package phaseTwo;

public class ExpressionTerminals4 implements ExpressionTerminals{

	AfterNew afterNew;
	public ExpressionTerminals4 (AfterNew afterNew) {
		this.afterNew = afterNew;
	}
	@Override
	public String getValue() {
		return "new " + afterNew.getValue();
	}
	
}
