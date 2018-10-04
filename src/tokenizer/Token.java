package tokenizer;
public class Token {

	private String value;
	private Type type;
	
	public Token(String value, Type type) {
		this.value = value;
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public String getValue() {
		return value;
	}
}