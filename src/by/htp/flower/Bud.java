package by.htp.flower;

public class Bud {
	private Leaf[] leaves;
	private String colour;
	private int size;
	private boolean bloomed;

	public Bud(int size) {
		this.size = size;
		this.colour = "Green";
	}
	
	public Leaf[] getLeaves() {
		return leaves;
	}

	public void setLeaves(Leaf[] leaves) {
		this.leaves = leaves;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean isBloomed() {
		return bloomed;
	}
	public void setBloomed(boolean isBloomed) {
		this.bloomed = isBloomed;
	}
	
	public void addFreshleaves(int number) {
		if ( leaves != null ) {
			
		}
		else {
			leaves = new Leaf [number];
			for(int i = 0 ; i < number ; ++i) {
				leaves[i] = new Leaf("White","ellipse");
			}
		}
	}
	
	@Override
	public String toString() {
		return "Bud size "+getSize() +" and colour " +getColour();
	}
}