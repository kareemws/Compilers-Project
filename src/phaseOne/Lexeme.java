/**
 * 
 */
package phaseOne;

/**
 * @author Kareem Waleed, Abdallah Eid
 *
 */
public class Lexeme {
	private String token;
	private String value;
	private int startIndex;
	private int endIndex;
	
	public String getLabel() {
		return token;
	}
	public void setLabel(String label) {
		this.token = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
}
