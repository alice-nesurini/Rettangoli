package structure;

import drawer.CharMap;

public class HorizontalBlock extends Block{

	private final Block left;
	private final Block right;
	
	public HorizontalBlock(Block b0, Block b1) {
		this.left=b0;
		this.right=b1;
	}
	
	@Override
	public int height() {
		return Math.max(left.height(), right.height());
	}

	@Override
	public int width() {
		return left.width()+right.width();
	}

	@Override
	public void draw(int x, int y, CharMap map) {
		left.draw(x, y, map);
		right.draw(x+left.width(), y, map);
	}
}
