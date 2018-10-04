import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import drawer.CharMap;
import parser.Parser;
import structure.Block;
import structure.HorizontalBlock;
import structure.Rect;
import structure.VerticalBlock;

public class Test {

	public static void main(String[] args) throws IOException {
		Block b=new Parser(new StringReader("7*8|2*3-7*5|5*4")).parse();
		System.out.println(b.width()+" "+b.height());
		
		CharMap charMap=new CharMap();
		charMap.init(b.width(), b.height());
		b.draw(0, 0, charMap);
		
		System.out.println(charMap.getBitMapString());
	}
}
