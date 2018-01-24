package by.htp.logic;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import by.htp.exc.IncorectFlowerException;
import by.htp.flower.Flower;

public class FutureTellerTest {
	
	private FutureTeller teller;
	
	@Before
	public void initFutureTeller() {
		List <Flower> flower = new ArrayList<>();
		Flower rose = new Flower("Rose");
		Flower cham = new Flower("Chamomile");
		flower.add(rose);
		flower.add(cham);
		try {
			teller = new FutureTeller(flower);
		} catch (IncorectFlowerException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testNotNullFlowersList() {
		assertNotNull(teller.getFlowers());
	}
	@Test
	public void testNotNullPredictionsMap() {
		assertNotNull(teller.getPredictionMap());
	}

	@Test
	public void testIfNotChamomileFlower() {
		Flower flower = new Flower("Rose");
		if(teller.ifChamomileFlower(flower)) {
			fail();
		}
	}
	@Test
	public void testIfChamomileFlower() {
		Flower flower = new Flower("Chamomile");
		Flower camomileFromList = teller.getFlowers().get(0);
		for(int i = 0 ; i <teller.getFlowers().size() ; ++i ) {
			if(teller.ifChamomileFlower(teller.getFlowers().get(i))) {
				camomileFromList = teller.getFlowers().get(i);
				break;
			}
			
		}
		if(teller.ifChamomileFlower(flower)) {
			assertEquals("The titles are different! ",flower.getTitle(), camomileFromList.getTitle());
		}
	}
	
	@Test
	public void testWrongFlowers() {
		List<Flower> wrongFlowers = new ArrayList<>();
		wrongFlowers.add(new Flower("Rose"));
		wrongFlowers.add( new Flower("Carnation") );
		try {
			teller = new FutureTeller(wrongFlowers);
		} catch (IncorectFlowerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> res = teller.makePredictionsForAllChamomiles();
		if(res.size()>0) {
			fail();
		}
	}
	@Test
	public void testCorrectFlower() {
		List<Flower> flowers = new ArrayList<>();
		Flower cur = new Flower("Chamomile");
		cur.bloom();
		flowers.add(cur);
		try {
			teller = new FutureTeller(flowers);
		} catch (IncorectFlowerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> res = teller.makePredictionsForAllChamomiles();
		assertTrue("Success in one flower prediction", res.size()== 1 );
	}
	@Test
	public void testSleepingTime() {
		int topicNumber = teller.getPredictionMap().size();
		teller.addOneTopic();
		int newNumber = teller.getPredictionMap().size();
		assertTrue("Add new topic!",newNumber>topicNumber);
	}
}
