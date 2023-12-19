package com.ben.aoc;

public class Rule {

	char type;
	boolean lessThan;
	int value;
	String dest;
	
	public Rule(String rule) {
		type = rule.charAt(0);
		lessThan = (rule.charAt(1) == '<');
		value = Integer.parseInt(rule.substring(2, rule.indexOf(':')));
		String[] colonSplit = rule.split(":");
		dest = colonSplit[1];
	}
	
	public String getRuleDestForPart(Part part){
		switch(type) {
		case 'x':
			if(lessThan) {
				if(part.x < value) {
					return dest;
				}
			}else if(part.x > value) {
				return dest;
			}
			break;
		case 'm':
			if(lessThan) {
				if(part.m < value) {
					return dest;
				}
			}else if(part.m > value) {
				return dest;
			}
			break;
		case 'a':
			if(lessThan) {
				if(part.a < value) {
					return dest;
				}
			}else if(part.a > value) {
				return dest;
			}
			break;
		case 's':
			if(lessThan) {
				if(part.s < value) {
					return dest;
				}
			}else if(part.s > value) {
				return dest;
			}
			break;
		}
		
		return null;
	}
}
