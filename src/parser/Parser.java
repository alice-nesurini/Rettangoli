package parser;
import java.io.Reader;

import exceptions.ParsingException;
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
		//Per il futuro
		//tokenizer=new Tokenizer(reader);
		//token=tokenizer.next();
	}
	
	public Block parse() throws ParsingException{
		token=tokenizer.next();
		Block block=vertExpr();
		if(tokenizer.peek().getType()==(Type.EOS)) {
			return block;
		}
		throw new ParsingException("The parser did not find an end of string (EOS failed)");
	}

	private Block vertExpr() throws ParsingException {
		Block block=horizExpr();
		while(isSymbol("|")) {
			token=tokenizer.next();
			block=new VerticalBlock(block, horizExpr());
		}
		return block;
	}

	private Block horizExpr() throws ParsingException {
		Block block=primaryExpr();
		while(isSymbol("-")) {
			token=tokenizer.next();
			block=new HorizontalBlock(block, primaryExpr());
		}
		return block;
	}

	private Block primaryExpr() throws ParsingException {
		return (check(Type.NUMBER))?rectExpr():vertExpr();
	}

	private Block rectExpr() throws ParsingException {
		if(check(Type.NUMBER)) {
			tokenizer.next();
			return new Rect(
				Integer.parseInt(token.getValue()),
				Integer.parseInt(tokenizer.next().getValue())
			);
		}
		else {
			throw new ParsingException("No valid number found");
		}
	}
	
	private boolean check(Type type) {
		return (token.getType()==type)?true:false;
	}
	
	private boolean isSymbol(String symbol) {
		if(tokenizer.peek().getValue().equals(symbol)) {
			tokenizer.next();
			return true;
		}
		else {
			return false;
		}
	}
}
