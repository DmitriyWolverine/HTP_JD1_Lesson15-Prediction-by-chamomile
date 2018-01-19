package by.htp.flower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Flower {
	
	private String title;
	private Bud bud;
	private Stem stem;
	private static int growingProcessCounter=1;
	private boolean isWithered = false;
	private Map<String, List<String>> predictionMap;
	
	public Flower(String title) {
		this.title = title;
		bud = new Bud(2);
		stem = new Stem(10,1);
	}
	
	public void bloom() {
		if (bud.isBloomed() != true)
		{
			bud.setSize(bud.getSize()*2);
			bud.setColour("Yellow");
			//System.out.println("Enter Number of leaves: ");
			//int numberOfLeaves = enterPositiveIntegerValue();
			int numberOfLeaves = 7+ (int) ( 20 * (Math.random() ) );
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
		if (isWithered != true)
		{
			bud.setColour("Grey");
			bud.setLeaves(new Leaf[0]);
			isWithered = true;
		}
	}
	
	public boolean simpleFuturePredictions() {
		if(title.compareToIgnoreCase("Chamomile") != 0) {
			System.out.println("We can not predict using "+title +" flower!");
			return false;
		}
		System.out.println("Enter your positive option to guess: ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String optionString = scanner.nextLine();
		for(int i = 0 ; i < getBud().getLeaves().length ; ++i) {
			if( (i&1) == 0 ) {
				System.out.println(optionString);
			}
			else {
				System.out.println("No "+optionString);
			}
		}
		if( getBud().getLeaves().length %2 == 0) {
			return false;
		}
		return true;
	}
	
	public void optionalFuturePredictions() {
		if(title.compareToIgnoreCase("Chamomile") != 0) {
			System.out.println("We can not predict using "+title +" flower!");
			return ;
		}
		System.out.println("Enter your number of options: ");
		int number = enterPositiveIntegerValue();
		String [] options = new String[number];
		Scanner scanner = new Scanner(System.in);
		for( int i = 0 ; i < number; ++i) {
			options[i] = scanner.nextLine();
		}
		for(int i = 0 ; i < getBud().getLeaves().length ; ++i) {
			System.out.println(options[i%number]);
		}
		scanner.close();
		System.out.println("As result "+  options[(getBud().getLeaves().length - 1 )%number]);
	}
	
	public void readFuturePredictionsToMap(Scanner scanner) {
		if(title.compareToIgnoreCase("Chamomile") != 0) {
			System.out.println("We can not predict using "+title +" flower!");
			return ;
		}
		predictionMap = new HashMap<String, List<String>>();
		boolean isNextTopic = true;
		while(isNextTopic) {
			addOneTopic ();
			isNextTopic = readYesOrNoAnswerFromCOnsole(scanner);
		}
	}
	
	private void addOneTopic () {
		System.out.println("Enter topic of prediction: ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String topic = scanner.nextLine();
		List<String> guesses = new ArrayList<>();
		boolean isNextGuess = true;
		while (isNextGuess) {
			System.out.println("Enter your option: ");
			String guess = scanner.nextLine();
			guesses.add(guess);
			System.out.print("Enter next option: ");
			isNextGuess = readYesOrNoAnswerFromCOnsole(scanner);
		}
		predictionMap.put(topic, guesses);
		System.out.println("Would you like to enter next topic of preditions? ");
	}
	
	public String futurePredictionFromMap() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		readFuturePredictionsToMap(scanner);
		String yourChoice;
		do {
			System.out.println("Enter your topic from next list: ");
			for(String key: predictionMap.keySet())
			{System.out.print(key+" ");}
			System.out.print("\n");
			yourChoice = scanner.nextLine();
			if(!predictionMap.containsKey(yourChoice)) {
				System.out.println("There is no such option. Would you like to add it?");
				if(readYesOrNoAnswerFromCOnsole(scanner)) {
					addOneTopic ();
				}
			}
		}
		while(!predictionMap.containsKey(yourChoice));
		
		int numberOfGuesses = predictionMap.get(yourChoice).size();
		String res="";
		for (int i = 0 ; i < getBud().getLeaves().length ; ++i) {
			System.out.println(predictionMap.get(yourChoice).get(i%numberOfGuesses));
			if( i ==  getBud().getLeaves().length -1) {
				res = predictionMap.get(yourChoice).get(i%numberOfGuesses);
			}
		}
		scanner.close();
		return res;
	}
	
	private boolean readYesOrNoAnswerFromCOnsole(Scanner scanner) {
		String answer ="";
		do {
			System.out.println("Enter answer(y/n): ");
			answer = scanner.nextLine();
        }
		while(!answer.equals("y") && !answer.equals("n") );
		if(answer.equals("y")) {
			return true;
		}
		return false;
	}
	
	private int enterPositiveIntegerValue() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int value = -1;
		do {
            if ( scanner.hasNextInt() )
            {
                if ( (value = scanner.nextInt())  <= 0)
                {
                    System.out.println("Enter Number above zero!");
                }
                else
                {
                    System.out.println("You have entered " + value);
                }
            }
            else
            {
                System.out.println("Please, enter integer number: ");
                scanner.next();
            }
        }
        while( value <= 0 );
		return value;
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
}