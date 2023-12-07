package com.ben.aoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JokerHand implements Comparable<JokerHand>{
	
	private static final Map<Character, Integer> cardValue = createMap();
	public int strength;
	
	private List<Character> cards;
	private int bid;
	private long numberOfJokers = 0;
	
	public JokerHand(String cards, int bid) {
		this.cards = cards.chars().mapToObj(e->(char)e).collect(Collectors.toList());
		this.bid = bid;
		this.strength = caluclateHandType();		
	}
	
	private static Map<Character, Integer> createMap() {
	    Map<Character,Integer> cardMap = new HashMap<Character,Integer>();
	    cardMap.put('A', 13);
	    cardMap.put('K', 12);
	    cardMap.put('Q', 11);
	    cardMap.put('T', 10);
	    cardMap.put('9', 9);
	    cardMap.put('8', 8);
	    cardMap.put('7', 7);
	    cardMap.put('6', 6);
	    cardMap.put('5', 5);
	    cardMap.put('4', 4);
	    cardMap.put('3', 3);
	    cardMap.put('2', 2);
	    cardMap.put('J', 1);
	    return cardMap;
	}
	
	@Override
	public int compareTo(JokerHand o) {
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
	
	
	private int caluclateHandType() {
		Map<Character, Long> counted = cards.stream()
	            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		numberOfJokers = counted.getOrDefault('J', 0L);

		if ((counted.size() == 1)
				|| (counted.size() ==2 && (counted.values().contains(4L)) && numberOfJokers > 0)
				|| (counted.size() == 2 && counted.values().contains(3L)) && numberOfJokers > 0) {
			return 6; //five of a kind
		}
		if ((counted.size() ==2 && (counted.values().contains(4L))) 
				||(counted.size() == 3 && counted.values().contains(3L) && numberOfJokers > 0)
				||(counted.size() == 3 && counted.values().contains(2L) && numberOfJokers == 2) ){
			return 5; //four of a kind
		}
		if ((counted.size() == 2 && counted.values().contains(3L)) 
				|| (counted.size() == 3 && counted.values().contains(2L) && numberOfJokers == 1L)) {
			return 4; //full house
		}
		if ((counted.size() == 3 && counted.values().contains(3L)) 
			||counted.size() == 4 && numberOfJokers > 0){
			return 3; //three of a kind
		}
		if (counted.size() == 3 && counted.values().contains(2L)) {
			return 2; //two pair
		}
		if ((counted.size() == 4 && counted.values().contains(2L))|| numberOfJokers == 1L) {
			return 1; //pair
		}
		return 0;
	}
	
	public int getBid() {
		return bid;
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
