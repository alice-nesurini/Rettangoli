package structure;

import drawer.CharMap;

public class Rect extends Block{
	
	private final int w;
	private final int h;

	public Rect(int w, int h) {
		this.w=w;
		this.h=h;
	}
	
	@Override
	public int width() {
		return w+1;
	}

	@Override
	public int height() {
		return h+1;
	}

	@Override
	public void draw(int x, int y, CharMap map) {
		for (int i = 0; i <= h+1; i++) {
			for (int j = 0; j <= w+1; j++) {
				if((y+i<=map.getHeight() && x+j<=map.getWidth()) && map.get((y+i), (x+j))=='+') {
					continue;
				}
				if((i==0 && j==0) || (i==h+1 && j==0) || (i==0 && j==w+1) || (i==h+1 && j==w+1)) {
					map.put(y+i, x+j, '+');
					continue;
				}
				else if(i==0 || i==h+1){
					map.put(y+i, x+j, '-');
					continue;
				}
				else if(j==0 || j==w+1){
					map.put(y+i, x+j, '|');
					continue;
				}
				if(map.get((y+i), (x+j))=='\u0000') {
					map.put(y+i, x+j, ' ');
					continue;
				}
			}
		}
	}
}
