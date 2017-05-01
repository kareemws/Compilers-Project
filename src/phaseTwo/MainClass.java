package phaseTwo;

public class MainClass {
	Identifier firstIdentifier ;
	Identifier secondIdentifier ;
	Statement statement;
	
	public MainClass(Identifier firstIdentifier, Identifier secondIdentifier, Statement statement){
		this.firstIdentifier = firstIdentifier ;
		this.secondIdentifier = secondIdentifier ;
		this.statement = statement ;
	}
	
	public String getValue(){
		System.out.println("class " + firstIdentifier.getValue() + " { public static void main ( String [ ] " + 
							secondIdentifier.getValue() + " ) { " + statement.getValue() + " } } ");
	}
	// end Main 
}
