package parser;
import java.io.Reader;

import structure.Block;
import structure.HorizontalBlock;
import structure.Rect;
import structure.VerticalBlock;
import tokenizer.MockTokenizer;
import tokenizer.Token;
import tokenizer.Type;

public class Parser {
	
	private final MockTokenizer tokenizer=new MockTokenizer();
	private Token token;

	public Parser(Reader reader) {
		//tokenizer=new Tokenizer(reader);
		//token=tokenizer.next();
	}
	
	public Block parse() {
		token=tokenizer.next();
		Block b=vertExpr();
		if(token.getType().equals(Type.EOS)) {
			return b;
		}
		return b;
	}

	private Block vertExpr() {
		Block b=horizExpr();
		while(tokenizer.peek().getValue().equals("|")) {
			token=tokenizer.next();
			token=tokenizer.next();
			b=new VerticalBlock(b, horizExpr());
		}
		return b;
	}

	private Block horizExpr() {
		Block b=primaryExpr();
		while(tokenizer.peek().getValue().equals("-")) {
			token=tokenizer.next();
			token=tokenizer.next();
			b=new HorizontalBlock(b, primaryExpr());
		}
		return b;
	}

	private Block primaryExpr() {
		if(token.getType() == Type.NUMBER) {
			return rectExpr();
		}
		else {
			return vertExpr();
		}
	}

	private Block rectExpr() {
		if(token.getType().equals(Type.NUMBER)) {
			tokenizer.next();
			return new Rect(
				Integer.parseInt(token.getValue()),
				Integer.parseInt(tokenizer.next().getValue())
			);
		}
		else {
			//NO no no no
			return null;
		}
	}
}
