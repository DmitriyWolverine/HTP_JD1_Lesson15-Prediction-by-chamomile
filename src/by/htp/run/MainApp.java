package by.htp.run;

import java.util.ArrayList;
import java.util.List;

import by.htp.exc.IncorectFlowerException;
import by.htp.flower.Bud;
import by.htp.flower.Flower;
import by.htp.logic.FutureTeller;

public class MainApp {

	public static void main(String[] args) {
		Flower flower = new Flower("Chamomile");
		Flower flower1 = new Flower("Chamomile");
		Flower flower2 = new Flower("Chamomile");
		List<Flower> chamomiles = new ArrayList<>();
		chamomiles.add(flower);
		chamomiles.add(flower1);
		chamomiles.add(flower2);
	
		for(int i = 0 ; i < 20 ; ++i) {
			flower.grow();
			flower1.grow();
			flower2.grow();
		}
		flower.bloom();
		flower1.bloom();
		flower2.bloom();
		FutureTeller teller;
		try {
			teller = new FutureTeller(chamomiles);
			List<String> results = teller.makePredictionsForAllChamomiles();
			for(int i = 0 ; i < teller.makePredictionsForAllChamomiles().size() ; ++i) {
				 if (!teller.getFlowers().get(i).isWithered()) {
					 teller.getFlowers().get(i).wither();
				 }
			}
			System.out.println("Result of predictions are: ");
			for(int i = 0 ; i < results.size() ; ++i) {
				System.out.println("Flower " +(i+1)+" : "+results.get(i)); 
			}
		} catch (IncorectFlowerException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void printWitheredFlower(Flower flower) {
		System.out.println("The flower withered");
		System.out.println("New bud colour : "+flower.getBud().getColour());
		printBudLeave(flower.getBud());
		System.out.println("==============================");
	}
	
	public static void printBloomingFlower(Flower flower) {
		System.out.println("The flower is blooming");
		System.out.println("New bud colour : "+flower.getBud().getColour());
		printBudLeave(flower.getBud());
		System.out.println("==============================");
	}
	
	public static void printBudLeave(Bud bud) {
		if(bud.getLeaves().length <1) {
			System.out.println("The flower does not have any leaves! Probably it has already died!");
			return;
		}
		for(int i = 0 ; i < bud.getLeaves().length ; ++i) {
			System.out.println(bud.getLeaves()[i]);
		}
	}
	public static void printFlower(Flower flower, String message) {
		System.out.println(message);
		System.out.println("Flower: "+flower.getTitle());
		System.out.println( flower.getStem());
		System.out.println( flower.getBud());
		System.out.println("==============================");
	}
}