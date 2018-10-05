package tokenizer;

import java.io.IOException;
import java.io.Reader;

public class Tokenizer {
	
	private Reader reader;
	private char ch;
	private StringBuilder builder=new StringBuilder();
	
	public Tokenizer(Reader reader) {
		this.reader=reader;
	}

	public Token next() throws IOException {
		skipSpaces();
		int chInt=reader.read();
		ch=(char)chInt;

		if(ch=='*') {
			return new Token("*", Type.SYMBOL);
		}
		if(ch=='|') {
			return new Token("|", Type.SYMBOL);
		}
		if(ch=='-') {
			return new Token("-", Type.SYMBOL);
		}
		if(ch=='(') {
			return new Token("(", Type.SYMBOL);
		}
		if(ch==')') {
			return new Token(")", Type.SYMBOL);
		}
		if(chInt==-1) {
			return new Token("", Type.EOS);
		}
		if(Character.isDigit(ch)) {
			builder.append(ch);
			Token token=new Token(buildNumber(), Type.NUMBER);
			builder=new StringBuilder();
			return token;
		}
		return new Token("", Type.UNKNOWN);
	}
	
	private void skipSpaces() throws IOException {
		reader.mark(1);
		ch = (char)reader.read();
		if(Character.isWhitespace(ch)) {
			skipSpaces();
		}
		reader.reset();
	}

	private String buildNumber() throws IOException {
		reader.mark(1);
		ch=(char)reader.read();
		
		if(Character.isDigit(ch)) {
			builder.append(ch);
			buildNumber();
		}
		else {
			reader.reset();
		}
		return builder.toString();
	}

	public Token peek() throws IOException {
		reader.mark(1);
		Token token=next();
		reader.reset();
		return token;
	}
}
