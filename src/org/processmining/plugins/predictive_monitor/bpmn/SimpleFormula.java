package org.processmining.plugins.predictive_monitor.bpmn;

public class SimpleFormula implements Formula{

	private String LTLFormula;
	
	public SimpleFormula() {
		LTLFormula = null;
	}
	

	public SimpleFormula(String lTLFormula) {
		super();
		LTLFormula = lTLFormula;
	}



	public String getLTLFormula() {
		return LTLFormula;
	} 
	
	
}
