package tokenizer;

import java.io.IOException;
import java.io.Reader;

public class Tokenizer {
	
	private Reader reader;
	private char ch;
	private int chInt;
	private StringBuilder builder=new StringBuilder();
	
	public Tokenizer(Reader reader) {
		this.reader=reader;
	}

	public Token next() {
		skipSpaces();
		try {
			chInt=reader.read();
			ch=(char)chInt;
		} catch (IOException e) {e.printStackTrace();}

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
	
	private void skipSpaces() {
		try {
			reader.mark(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ch = (char)reader.read();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if(Character.isWhitespace(ch)) {
			skipSpaces();
		}
		else {
			try {
				reader.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String buildNumber() {
		try {
			reader.mark(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ch=(char)reader.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(Character.isDigit(ch)) {
			builder.append(ch);
			buildNumber();
		}
		else {
			try {
				reader.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return builder.toString();
	}

	public Token peek() {
		try {
			reader.mark(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Token token=next();
		try {
			reader.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}
}
