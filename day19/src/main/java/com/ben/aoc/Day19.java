package com.ben.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.javatuples.Pair;

public class Day19 {
	List<Part> parts = new ArrayList<Part>();
	Map<String, Workflow> workflows = new HashMap<String, Workflow>();
	
	public Day19(String fileNameParts, String fileNameWorkflows) {
		List<String> partLines = Util.readFile(getClass(), fileNameParts);
		List<String> workflowLines = Util.readFile(getClass(), fileNameWorkflows);
		
		for(String p : partLines) {
			String partString = p.substring(1, p.length()-1);
			String[] values = partString.split(",");
			int x = Integer.parseInt(values[0].substring(2, values[0].length()));
			int m = Integer.parseInt(values[1].substring(2, values[1].length()));
			int a = Integer.parseInt(values[2].substring(2, values[2].length()));
			int s = Integer.parseInt(values[3].substring(2, values[3].length()));
			Part part = new Part(x, m, a, s);
			parts.add(part);
		}
		
		for(String w : workflowLines) {
			String name = w.substring(0, w.indexOf('{'));
			String rules = w.substring(w.indexOf('{') + 1, w.indexOf('}'));
			Workflow workflow = new Workflow(name, rules);
			workflows.put(name, workflow);
		}

		
	}
		
	public long puzzle1() {		
		long result = 0;
		
		for(Part p : parts) {
			String workflowName = "in";
			while (!(workflowName.equals("A") || workflowName.equals("R"))) {
				Workflow w = workflows.get(workflowName);
				
				workflowName = w.getWorkflowForPart(p);
			}
			if(workflowName.equals("A")) {
				result += p.getValue();
			}
		}
				
		return result;
	}
	
	public long puzzle2() {
		long result = 0;
		Workflow in = workflows.get("in");
		List<PartRange> successfulRanges = new ArrayList<PartRange>();
		Stack<Pair<Workflow, PartRange>> stack = new Stack<Pair<Workflow, PartRange>>();
		
		Workflow r = new Workflow("R", "");
		Workflow a = new Workflow("A", "");
		workflows.put("R", r);
		workflows.put("A", a);
		
		Pair<Workflow, PartRange> start = new Pair<Workflow, PartRange>(in, new PartRange());
		stack.push(start);
		
		while(!stack.isEmpty()) {
			Pair<Workflow, PartRange> current = stack.pop();
			Workflow w = current.getValue0();
			if(w.name.equals("A")) {
				successfulRanges.add(current.getValue1());
			}else if(w.name.equals("R")) {
				//Dead end
			}else {
				int ruleCount = w.rules.size();
				List<PartRange> newRanges = new ArrayList<PartRange>();
				for(int i=0; i<ruleCount+1; i++) {
					newRanges.add(clonePartRange(current.getValue1()));
				}
				for(int i=0; i<ruleCount; i++) {
					Rule rule = w.rules.get(i);
					Workflow nextWF = workflows.get(rule.dest);
					Pair<Workflow, PartRange> next = new Pair<Workflow, PartRange>(nextWF, partRangeForRule(rule, newRanges.get(i)));
					for(int j=i+1; j<ruleCount+1; j++) {
						PartRange pr = newRanges.get(j);
						newRanges.set(j, partRangeForAntiRule(rule, pr));
					}
					stack.add(next);
				}
				Workflow nextWF = workflows.get(w.elseDest);
				Pair<Workflow, PartRange> next = new Pair<Workflow, PartRange>(nextWF, newRanges.get(ruleCount));
				stack.add(next);
			}
		}
		
		
		for(PartRange pr : successfulRanges) {
			long xRange = (pr.maxX + 1) - pr.minX;
			long mRange = (pr.maxM + 1) - pr.minM;
			long aRange = (pr.maxA + 1) - pr.minA;
			long sRange = (pr.maxS + 1) - pr.minS;
			long combinations = xRange*mRange*aRange*sRange;
			result += combinations;
		}
		return result;
	}
	
	private PartRange clonePartRange(PartRange pr) {
		PartRange partRange = new PartRange();
		partRange.minX = pr.minX;
		partRange.minM = pr.minM;
		partRange.minA = pr.minA;
		partRange.minS = pr.minS;
		partRange.maxX = pr.maxX;
		partRange.maxM = pr.maxM;
		partRange.maxA = pr.maxA;
		partRange.maxS = pr.maxS;
		return partRange;
	}
	
	private PartRange partRangeForRule(Rule rule, PartRange partRange) {
		switch(rule.type) {
		case 'x':
			if(rule.lessThan) {
				partRange.maxX = rule.value -1;
			}else{
				partRange.minX = rule.value + 1;
			}
			break;
		case 'm':
			if(rule.lessThan) {
				partRange.maxM = rule.value -1;
			}else{
				partRange.minM = rule.value + 1;
			}
			break;
		case 'a':
			if(rule.lessThan) {
				partRange.maxA = rule.value -1;
			}else{
				partRange.minA = rule.value + 1;
			}
			break;
		case 's':
			if(rule.lessThan) {
				partRange.maxS = rule.value -1;
			}else{
				partRange.minS = rule.value + 1;
			}
			break;
		}
		return partRange;
	}
	
	private PartRange partRangeForAntiRule(Rule rule, PartRange partRange) {
		switch(rule.type) {
		case 'x':
			if(rule.lessThan) {
				partRange.minX = rule.value;
			}else{
				partRange.maxX = rule.value;
			}
			break;
		case 'm':
			if(rule.lessThan) {
				partRange.minM = rule.value;
			}else{
				partRange.maxM = rule.value;
			}
			break;
		case 'a':
			if(rule.lessThan) {
				partRange.minA = rule.value;
			}else{
				partRange.maxA = rule.value;
			}
			break;
		case 's':
			if(rule.lessThan) {
				partRange.minS = rule.value;
			}else{
				partRange.maxS = rule.value;
			}
			break;
		}
		return partRange;
	}
	
}
