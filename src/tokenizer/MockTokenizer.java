package tokenizer;

public class MockTokenizer {
	
	private int index=0;
	//7*8|2*3-7*5|5*4
	private Token[] tokens={
			new Token("7", Type.NUMBER), 
			new Token("*", Type.SYMBOL),
			new Token("8", Type.NUMBER),
			new Token("|", Type.SYMBOL),
			new Token("2", Type.NUMBER),
			new Token("*", Type.SYMBOL),
			new Token("3", Type.NUMBER),
			new Token("-", Type.SYMBOL),
			new Token("3", Type.NUMBER),
			new Token("*", Type.SYMBOL),
			new Token("7", Type.NUMBER),
			new Token("|", Type.SYMBOL),
			new Token("5", Type.NUMBER),
			new Token("*", Type.SYMBOL),
			new Token("4", Type.NUMBER),
			new Token("|", Type.SYMBOL),
			new Token("1", Type.NUMBER),
			new Token("*", Type.SYMBOL),
			new Token("1", Type.NUMBER),
			new Token("-", Type.SYMBOL),
			new Token("1", Type.NUMBER),
			new Token("*", Type.SYMBOL),
			new Token("1", Type.NUMBER),
			new Token("-", Type.SYMBOL),
			new Token("1", Type.NUMBER),
			new Token("*", Type.SYMBOL),
			new Token("1", Type.NUMBER),
			new Token("", Type.EOS)
	};

	public Token next() {
		return tokens[index++];
	}
	
	public Token peek() {
		return tokens[index];
	}
}