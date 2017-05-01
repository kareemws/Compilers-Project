package phaseTwo;

import java.util.ArrayList;

public class Goal {
	MainClass mainClass;
	ArrayList<ClassDeclaration> classDeclaration;
	
	public Goal(MainClass mainClass , ClassDeclaration... classDeclaration) {
		this.mainClass = mainClass;
		int length = classDeclaration.length;
		for(int i = 0; i < length; i ++) {
			this.classDeclaration.add(classDeclaration[i]);
		}
	}
	
	public String getValue() {
		StringBuilder value = new StringBuilder();
		value.append(mainClass.getValue());
		for(int i = 0; i < classDeclaration.size(); i ++) {
			value.append(classDeclaration.get(i).getValue());
		}
		return value.toString();
	}
}
