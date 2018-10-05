package parser;
import java.io.IOException;
import java.io.Reader;

import exceptions.ParsingException;
import structure.Block;
import structure.HorizontalBlock;
import structure.Rect;
import structure.VerticalBlock;
import tokenizer.MockTokenizer;
import tokenizer.Token;
import tokenizer.Tokenizer;
import tokenizer.Type;

public class Parser {
	
	private final Tokenizer tokenizer;
	private Token token;

	public Parser(Reader reader) {
		tokenizer=new Tokenizer(reader);
	}
	
	public Block parse() throws ParsingException, IOException{
		token=tokenizer.next();
		Block block=vertExpr();
		if(tokenizer.peek().getType()==(Type.EOS)) {
			return block;
		}
		throw new ParsingException("The parser did not find an end of string (EOS failed)");
	}

	private Block vertExpr() throws ParsingException, IOException {
		Block block=horizExpr();
		while(isSymbol("|")) {
			token=tokenizer.next();
			block=new VerticalBlock(block, horizExpr());
		}
		return block;
	}

	private Block horizExpr() throws ParsingException, IOException {
		Block block=primaryExpr();
		while(isSymbol("-")) {
			token=tokenizer.next();
			block=new HorizontalBlock(block, primaryExpr());
		}
		return block;
	}

	private Block primaryExpr() throws ParsingException, IOException {
		if(check(Type.NUMBER)) {
			return rectExpr();
		}
		else{
			Block block;
			if(token.getValue().equals("(")) {
				token=tokenizer.next();
				block=vertExpr();
			}else {
				throw new ParsingException("Invalid symbol, no start pharentesis");
			}
			if(tokenizer.next().getValue().equals(")")) {
				return block;
			}else {
				throw new ParsingException("Invalid symbol, no end pharentesis");
			}
		}
	}

	private Rect rectExpr() throws ParsingException, IOException {
		if(check(Type.NUMBER)) {
			if(!tokenizer.next().getValue().equals("*")) {
				throw new ParsingException("No valid operation after number");
			}
			return new Rect(
				Integer.parseInt(token.getValue()),
				Integer.parseInt(tokenizer.next().getValue())
			);
		}
		else {
			throw new ParsingException("No valid number found");
		}
	}
	
	private boolean check(Type type) throws ParsingException {
		if(token.getType()==type) {
			return true;
		}
		if(token.getType()==Type.UNKNOWN) {
			throw new ParsingException("An uknown character was found");
		}
		else {
			return false;
		}
	}
	
	private boolean isSymbol(String symbol) throws IOException {
		if(tokenizer.peek().getValue().equals(symbol)) {
			tokenizer.next();
			return true;
		}
		else {
			return false;
		}
	}
}
