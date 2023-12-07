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
		
		for (int i = 0; i< numberOfHands; i++) {
			result += hands.get(i).getBid() * (i+1);
		}
		
		return result;
	}
	
	public long getWinAmountJokers(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		List<JokerHand> hands = new ArrayList<JokerHand>();
		
		for(String s : lines) {
			String cards = s.substring(0, 5);
			Integer bid = Integer.parseInt(s.substring(6));
			hands.add(new JokerHand(cards, bid));
		}
				
		Collections.sort(hands);
		int numberOfHands = hands.size();
		
		for (int i = 0; i< numberOfHands; i++) {
			result += hands.get(i).getBid() * (i+1);
		}
		
		return result;
	}
	
	
}
