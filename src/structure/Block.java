package structure;

import drawer.CharMap;

public abstract class Block {
	public abstract int width();
	public abstract int height();
	public abstract void draw(int x, int y, CharMap map);
}
