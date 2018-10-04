package drawer;

public class CharMap {
	
	public static char[][] map;
	private static int height;
	private static int width;
	
	public void init(int w, int h) {
		CharMap.height=h+1;
		CharMap.width=w+1;
		CharMap.map=new char[height][width];
	}
	
	public void put(int x, int y, char ch) {
		map[x][y]=ch;
	}

	public char get(int i, int j) {
		return map[i][j];
	}
	
	public String getBitMapString() {
		StringBuilder bitString=new StringBuilder();
		for (int i = 0; i < height; i++) {
			bitString.append(i+"\t");
			for (int j = 0; j < width; j++) {
				if(map[i][j]=='\u0000') map[i][j]=' ';
				bitString.append(map[i][j]);
			}
			bitString.append("\n");
		}
		return bitString.toString();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
