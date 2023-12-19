package com.ben.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 {
	List<Part> parts = new ArrayList<Part>();
	//List<Workflow> workflows = new ArrayList<Workflow>();
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
		
		
		return result;
	}
	
}
