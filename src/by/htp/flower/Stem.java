package by.htp.flower;

public class Stem {
	private int height;
	private int width;
	
	public Stem(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Stem height: "+ getHeight() +" and width: " + getWidth();
	}
}