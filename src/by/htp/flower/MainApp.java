package by.htp.flower;

public class MainApp {

	public static void main(String[] args) {
		Flower flower = new Flower("Chamomile");
		printFlower(flower,"New ");
		for(int i = 0 ; i < 20 ; ++i) {
			flower.grow();
		}
		printFlower(flower,"Flower has grown");
		
		flower.bloom();
		printBloomingFlower(flower);
		System.out.println(flower.getBud().getLeaves().length );
		System.out.println("Result is : "+ flower.futurePredictionFromMap() );
		
		flower.wither();
		printWitheredFlower(flower);
	}
	
	private static void printWitheredFlower(Flower flower) {
		System.out.println("The flower withered");
		System.out.println("New bud colour : "+flower.getBud().getColour());
		printBudLeave(flower.getBud());
		System.out.println("==============================");
	}
	
	private static void printBloomingFlower(Flower flower) {
		System.out.println("The flower is blooming");
		System.out.println("New bud colour : "+flower.getBud().getColour());
		printBudLeave(flower.getBud());
		System.out.println("==============================");
	}
	
	private static void printBudLeave(Bud bud) {
		if(bud.getLeaves().length <1) {
			System.out.println("The flower does not have any leaves! Probably it has already died!");
			return;
		}
		for(int i = 0 ; i < bud.getLeaves().length ; ++i) {
			System.out.println(bud.getLeaves()[i]);
		}
	}
	private static void printFlower(Flower flower, String message) {
		System.out.println(message);
		System.out.println("Flower: "+flower.getTitle());
		System.out.println( flower.getStem());
		System.out.println( flower.getBud().printBudSizeAndColour());
		System.out.println("==============================");
	}
}
