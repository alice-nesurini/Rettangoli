package tokenizer;
public class Token {

	private final String value;
	private final Type type;
	
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