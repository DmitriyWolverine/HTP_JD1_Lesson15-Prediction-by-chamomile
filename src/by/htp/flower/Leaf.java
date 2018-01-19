package by.htp.flower;

public class Leaf {
	private String colour;
	private String shape;
	
	public Leaf(String colour, String shape) {
		super();
		this.colour = colour;
		this.shape = shape;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	@Override
	public String toString() {
		return "Leave has "+ colour+" colour and " + shape+ " shape" ;
	}
}