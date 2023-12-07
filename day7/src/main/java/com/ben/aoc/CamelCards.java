package com.ben.aoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CamelCards {
	
	public long getWinAmount(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		List<Hand> hands = new ArrayList<Hand>();
		
		for(String s : lines) {
			String cards = s.substring(0, 5);
			Integer bid = Integer.parseInt(s.substring(6));
			hands.add(new Hand(cards, bid));
		}
				
		Collections.sort(hands);
		int numberOfHands = hands.size();
		
		for(Hand h: hands) {
			System.out.println(h.getCards() + ", " + h.getBid());
		}
		
		System.out.println("number of hands: " + numberOfHands);
		
		for (int i = 0; i< numberOfHands; i++) {
			result += hands.get(i).getBid() * (i+1);
		}
		
		return result;
	}
	
	
}
