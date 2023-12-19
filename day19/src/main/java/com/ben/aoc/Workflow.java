package com.ben.aoc;

import java.util.ArrayList;
import java.util.List;

public class Workflow {
	
	String name;
	List<Rule> rules;
	String elseDest;
	
	public Workflow(String name, String rules) {
		this.name = name;
		String[] ruleSplit = rules.split(",");
		this.rules = new ArrayList<Rule>();
		for(int i = 0; i<ruleSplit.length - 1; i++) {
			Rule r = new Rule(ruleSplit[i]);
			this.rules.add(r);
		}
		elseDest = ruleSplit[ruleSplit.length-1];
	}
	
	public String getWorkflowForPart(Part p) {
		for(Rule r : rules) {
			String ruleDest = r.getRuleDestForPart(p);
			if(ruleDest != null) {
				return ruleDest;
			}
		}
		return elseDest;
	}

}
