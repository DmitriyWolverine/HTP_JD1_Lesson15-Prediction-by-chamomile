package by.htp.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.controller.ConsoleHandler;

import by.htp.exc.IncorectFlowerException;
import by.htp.flower.Flower;


public class FutureTeller {
	private Map<String, List<String>> predictionMap;
	private List<Flower> flowers;
	
	public FutureTeller(List<Flower> flowers) throws IncorectFlowerException {
		super();
		this.flowers = flowers;
		initPredictionsMap();
	}

	public FutureTeller(Map<String, List<String>> predictionMap, List<Flower> flowers) {
		super();
		this.predictionMap = predictionMap;
		this.flowers = flowers;
	}

	public Map<String, List<String>> getPredictionMap() {
		return predictionMap;
	}

	public void setPredictionMap(Map<String, List<String>> predictionMap) {
		this.predictionMap = predictionMap;
	}
	
	public List<Flower> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<Flower> flowers) {
		this.flowers = flowers;
	}

	public void initPredictionsMap() throws IncorectFlowerException {
		predictionMap = new HashMap<String, List<String>>();
		boolean isNextTopic = true;
		while(isNextTopic) {
			addOneTopic ();
			System.out.println("Would you like to enter next topic of preditions? ");
			isNextTopic = ConsoleHandler.readYesOrNoAnswerFromCOnsole();
		}
	}
	
	public void addOneTopic () {
		System.out.println("Enter topic of prediction: ");
		String topic = ConsoleHandler.readStringFromConsole();
		List<String> guesses = new ArrayList<>();
		boolean isNextGuess = true;
		while (isNextGuess) {
			addOneOptionToList(guesses);
			System.out.print("Would you like to enter next option? ");
			isNextGuess = ConsoleHandler.readYesOrNoAnswerFromCOnsole();
		}
		predictionMap.put(topic, guesses);
	}
	
	public void addOneOptionToList(List<String> guesses) {
		System.out.println("Enter your option: ");
		String guess = ConsoleHandler.readStringFromConsole();
		guesses.add(guess);
	}
	
	public List<String> makePredictionsForAllChamomiles(){
		if(flowers == null) {
			flowers = new ArrayList<>();
		}
		List <String> results = new ArrayList<>();
		for( int i = 0 ; i < flowers.size() ; ) {
			System.out.println(flowers.get(i));
			try {
				results.add(futurePredictionFromMap(flowers.get(i)));
			} catch (IncorectFlowerException e) {
				System.out.println("Couldn't predict future with " + flowers.get(i).getTitle() + " flower");
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			flowers.get(0).wither();
			flowers.remove(0);
		}
		return results;
	}
	
	public String futurePredictionFromMap(Flower flower) throws IncorectFlowerException, InterruptedException {
		if(!ifChamomileFlower(flower)) {
			throw new IncorectFlowerException();
		}
		if(predictionMap == null || predictionMap.isEmpty()) {
			return "";
		}
		String yourChoice = readYourChoice();
		int numberOfGuesses = predictionMap.get(yourChoice).size();
		showGeneralFuturePredictionProcess(flower, yourChoice, numberOfGuesses);
		int lastNumber = ( flower.getBud().getLeaves().length - 1 ) ;
		return predictionMap.get(yourChoice).get(lastNumber%numberOfGuesses);
	}
	
	public void showGeneralFuturePredictionProcess(Flower flower, String yourChoice, int numberOfGuesses) throws InterruptedException {
		for (int i = 0 ; i < flower.getBud().getLeaves().length ; ++i) {
			System.out.println(predictionMap.get(yourChoice).get(i%numberOfGuesses));
			Thread.sleep(500);
		}
	}
	
	public String readYourChoice() {
		String yourChoice;
		do {
			System.out.println("Enter your topic from next list: ");
			showTopics();
			yourChoice = ConsoleHandler.readStringFromConsole();
			if(!predictionMap.containsKey(yourChoice)) {
				System.out.println("There is no such option. Would you like to add it?");
				if( ConsoleHandler.readYesOrNoAnswerFromCOnsole()) {
					addOneTopic ();
				}
				else {
					System.out.println("Choose another topic");
				}
			}
		}
		while(!predictionMap.containsKey(yourChoice) /*&&  flowers.size()>0*/);
		return yourChoice;
	}
	
	public void showTopics() {
		if(predictionMap!=null && !predictionMap.isEmpty()) {
			for(String key: predictionMap.keySet()){
				System.out.print(key+" ");
			}
			System.out.print("\n");
		}
	}
	
	public boolean binarFuturePredictions(Flower flower) throws IncorectFlowerException {
		if(!ifChamomileFlower(flower)) {
			throw new IncorectFlowerException();
		}
		System.out.println("Enter your positive option to guess: ");
		String optionString = ConsoleHandler.readStringFromConsole();
		showBinarPredictionProcess(flower, optionString);
		if( flower.getBud().getLeaves().length %2 == 0) {
			return false;
		}
		return true;
	}
	
	public void showBinarPredictionProcess(Flower flower, String optionString) {
		for(int i = 0 ; i < flower.getBud().getLeaves().length ; ++i) {
			if( (i&1) == 0 ) {
				System.out.println(optionString);
			}
			else {
				System.out.println("No "+optionString);
			}
		}
	}
	
	public String multipleFuturePredictions(Flower flower) throws IncorectFlowerException {
		if(!ifChamomileFlower(flower)) {
			throw new IncorectFlowerException();
		}
		String [] options = initOptionsForMultiplePrediction();
		showMultiplePredictionProcess(flower, options, options.length);
		return "As result "+  options[(flower.getBud().getLeaves().length - 1 )%options.length];
		
	}
	
	private String[] initOptionsForMultiplePrediction() {
		System.out.println("Enter your number of options: ");
		int number = ConsoleHandler.enterPositiveIntegerValue();
		String [] options = new String[number];
		for( int i = 0 ; i < number; ++i) {
			options[i] = ConsoleHandler.readStringFromConsole();
		}
		return options;
	}
	
	public void showMultiplePredictionProcess(Flower flower, String [] options, int number) {
		for(int i = 0 ; i < flower.getBud().getLeaves().length ; ++i) {
			System.out.println(options[i%number]);
		}
	}
	
	boolean ifChamomileFlower(Flower flower) {
		if(flower == null) {
			return false;
		}
		if(flower.getTitle().compareToIgnoreCase("Chamomile") != 0) {
			System.out.println("We can not predict using "+flower.getTitle() +" flower!");
			return false;
		}
		return true;
	}

}
