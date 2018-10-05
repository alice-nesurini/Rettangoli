import java.io.IOException;
import java.io.StringReader;

import drawer.CharMap;
import exceptions.ParsingException;
import parser.Parser;
import structure.Block;

public class Test {

	public static void main(String[] args) throws IOException {
		Block b=null;
		try {
			b=new Parser(new StringReader(" (101 * 8 ) | 21 *10|(1 * 1 - 7 * 5)|  5 *4 ")).parse();
			
			System.out.println(b.width()+" "+b.height());
			
			CharMap charMap=new CharMap();
			charMap.init(b.width(), b.height());
			b.draw(0, 0, charMap);
			
			System.out.println(charMap.getBitMapString());
		}
		catch (ParsingException e) {
			System.err.println(e.getMessage());
		}
	}
}
