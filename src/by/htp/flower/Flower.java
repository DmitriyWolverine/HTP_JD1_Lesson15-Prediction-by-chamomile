package by.htp.flower;

public class Flower {
	
	private String title;
	private Bud bud;
	private Stem stem;
	private static int growingProcessCounter=1;
	private boolean withered = false;
	
	public Flower(String title) {
		this.title = title;
		bud = new Bud(2);
		stem = new Stem(10,1);
	}
	
	public Flower(String title, Bud bud, Stem stem) {
		super();
		this.title = title;
		this.bud = bud;
		this.stem = stem;
	}

	public Flower(String title, int budSize, int stemHeight, int stemWeight) {
		super();
		this.title = title;
		bud = new Bud(budSize);
		stem = new Stem(stemHeight,stemWeight);
	}
	
	public void bloom() {
		if (bud.isBloomed() != true)
		{
			bud.setSize(bud.getSize()*2);
			bud.setColour("Yellow");
			int numberOfLeaves = 7+ (int) ( 30 * (Math.random() ) );
			bud.addFreshleaves(numberOfLeaves);
		}
		bud.setBloomed(true);
	}
	
	public void grow() {
		stem.setHeight(stem.getHeight()+1);
		growingProcessCounter++;
		if(growingProcessCounter % 10 == 0)
			stem.setWidth(stem.getWidth()+1);
		if(growingProcessCounter % 8 == 0)
			bud.setSize(bud.getSize()+1);
	}
	
	public void wither() {
		if (withered != true)
		{
			bud.setColour("Grey");
			bud.setLeaves(new Leaf[0]);
			withered = true;
		}
	}
	
	

	public boolean isWithered() {
		return withered;
	}

	public void setWithered(boolean withered) {
		this.withered = withered;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Bud getBud() {
		return bud;
	}

	public void setBud(Bud bud) {
		this.bud = bud;
	}

	public Stem getStem() {
		return stem;
	}

	public void setStem(Stem stem) {
		this.stem = stem;
	}

	@Override
	public String toString() {
		return "Flower [title=" + title + ", bud=" + bud + ", stem=" + stem + ", isWithered=" + withered + "]";
	}
}