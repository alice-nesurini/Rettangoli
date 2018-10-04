package structure;

import drawer.CharMap;

public class VerticalBlock extends Block{

	private final Block top;
	private final Block bottom;
	
	public VerticalBlock(Block b0, Block b1) {
		this.top=b0;
		this.bottom=b1;
	}
	
	@Override
	public int height() {
		return top.height()+bottom.height();
	}

	@Override
	public int width() {
		return Math.max(top.width(), bottom.width());
	}

	@Override
	public void draw(int x, int y, CharMap map) {
		top.draw(x, y, map);
		bottom.draw(x, y+top.height(), map);
	}
}
