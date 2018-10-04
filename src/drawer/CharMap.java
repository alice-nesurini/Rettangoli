package drawer;

public class CharMap {
	
	private char[][] map;
	private int height;
	private int width;
	
	public void init(int w, int h) {
		this.height=h+1;
		this.width=w+1;
		this.map=new char[height][width];
	}
	
	public void put(int i, int j, char ch) {
		map[i][j]=ch;
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
