package com.ben.aoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand>{
	
	private static final Map<Character, Integer> cardValue = createMap();
	public int strength;
	
	private List<Character> cards;
	private int bid;
	private double value;
	
	public Hand(String cards, int bid) {
		this.cards = cards.chars().mapToObj(e->(char)e).collect(Collectors.toList());
		this.bid = bid;
		this.strength = caluclateHandType();
		//this.value = calculateValue();
		
		//System.out.println("Created: " + cards + ", value: " + value + ", bid: " + bid);
	}
	
	private static Map<Character, Integer> createMap() {
	    Map<Character,Integer> cardMap = new HashMap<Character,Integer>();
	    cardMap.put('A', 13);
	    cardMap.put('K', 12);
	    cardMap.put('Q', 11);
	    cardMap.put('J', 10);
	    cardMap.put('T', 9);
	    cardMap.put('9', 8);
	    cardMap.put('8', 7);
	    cardMap.put('7', 6);
	    cardMap.put('6', 5);
	    cardMap.put('5', 4);
	    cardMap.put('4', 3);
	    cardMap.put('3', 2);
	    cardMap.put('2', 1);
	    return cardMap;
	}
	
	@Override
	public int compareTo(Hand o) {
		if (strength != o.getStrength()) {
            return strength - o.getStrength();
		}else {
			for(int i = 0; i<cards.size(); i++) {
				Character myCard = cards.get(i);
				Character oCard = o.getCards().get(i);
				if(myCard != oCard) {
					return cardValue.get(myCard) - cardValue.get(oCard);
				}
			}
			return 0;
		}
		
	}
	
	private double calculateValue() {
		int value = caluclateHandType();
		for (int i=0; i<5; i++) {
			int pow = 5-i;
			
			value+=Math.pow(13 * cardValue.get(cards.get(i)), pow);
		}
		return value;
	}
	
	private int caluclateHandType() {
		Map<Character, Long> counted = cards.stream()
	            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		if (counted.size() == 1) {
			return 6; //five of a kind
		}
		if (counted.size() ==2 && (counted.values().contains(4L))) {
			return 5; //four of a kind
		}
		if (counted.size() == 2 && counted.values().contains(3L)) {
			return 4; //full house
		}
		if (counted.size() == 3 && counted.values().contains(3L)) {
			return 3; //three of a ind
		}
		if (counted.size() == 3 && counted.values().contains(2L)) {
			return 2; //two pair
		}
		if (counted.size() == 4 && counted.values().contains(2L)) {
			return 1; //pair
		}
		return 0;
	}
	
	public int getBid() {
		return bid;
	}
	
	public double getValue() {
		return value;
	}
	
	public List<Character> getCards(){
		return cards;
	}
	
	public String getCardsString() {
		return cards.toString();
	}
	
	public int getStrength() {
		return strength;
	}

}
