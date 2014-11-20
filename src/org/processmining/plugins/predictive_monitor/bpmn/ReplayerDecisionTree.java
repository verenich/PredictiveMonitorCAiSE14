package org.processmining.plugins.predictive_monitor.bpmn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ltl2aut.automaton.Automaton;
import ltl2aut.automaton.Transition;
import ltl2aut.formula.DefaultParser;
import ltl2aut.formula.conjunction.ConjunctionFactory;
import ltl2aut.formula.conjunction.ConjunctionTreeLeaf;
import ltl2aut.formula.conjunction.ConjunctionTreeNode;
import ltl2aut.formula.conjunction.DefaultTreeFactory;
import ltl2aut.formula.conjunction.GroupedTreeConjunction;
import ltl2aut.formula.conjunction.TreeFactory;
import ltl2aut.ltl.SyntaxParserException;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.in.XMxmlGZIPParser;
import org.deckfour.xes.in.XMxmlParser;
import org.deckfour.xes.in.XesXmlGZIPParser;
import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XAttributeLiteral;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.processmining.plugins.declareminer.ExecutableAutomaton;
import org.processmining.plugins.declareminer.PossibleNodes;

import weka_predictions.data_predictions.Result;

public class ReplayerDecisionTree extends Result{

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//String inputLogFileName = "C:\\Users\\Fabrizio\\Desktop\\BPI2011_20.xes";
		//String outputFileName = "C:\\Users\\Fabrizio\\Desktop\\output05_30Part2.txt";
		String inputLogFileName = "/home/coderus/TKDE/case_study/BPI2011_20_.xes";
		String outputFileName = "/home/coderus/TKDE/case_study/outputBPI2011_bpmn.txt";
		
		File output = new File(outputFileName);
		PrintWriter pw = new PrintWriter(output);
		
		XLog log = null;

		if(inputLogFileName.toLowerCase().contains("mxml.gz")){
			XMxmlGZIPParser parser = new XMxmlGZIPParser();
			if(parser.canParse(new File(inputLogFileName))){
				try {
					log = parser.parse(new File(inputLogFileName)).get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(inputLogFileName.toLowerCase().contains("mxml")){
			XMxmlParser parser = new XMxmlParser();
			if(parser.canParse(new File(inputLogFileName))){
				try {
					log = parser.parse(new File(inputLogFileName)).get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if(inputLogFileName.toLowerCase().contains("xes.gz")){
			XesXmlGZIPParser parser = new XesXmlGZIPParser();
			if(parser.canParse(new File(inputLogFileName))){
				try {
					log = parser.parse(new File(inputLogFileName)).get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(inputLogFileName.toLowerCase().contains("xes")){
			XesXmlParser parser = new XesXmlParser();
			if(parser.canParse(new File(inputLogFileName))){
				try {
					log = parser.parse(new File(inputLogFileName)).get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//CONFIGURAZIONE
		//String formulaLTL = "<>(\"ca-125 using meia\")";
		//String formulaLTL = "(<>(\"order rate\"))/\\(<>(\"ca-125 using meia\"))";
		//String formulaLTL = "(<>(\"ca-125 using meia\"))";
		//String formulaLTL = "(<>(\"O2 saturation\"))";
		//String formulaLTL = "[]((\"calcium\")->(<>(\"Calcium - urgent\")))";
		//int traceIndex = 44;
		//int traceIndex = 11;

		String[] confFormulas = new String[2];
		
	//	confFormulas[2]="( (<>(\"ca-125 using meia\")) -> (<>(\"CEA - tumor marker using meia\"))  )/\\([](    ((\"CEA - tumor marker using meia\") -> (\"squamous cell carcinome using eia\"))))";
		
		
		
		
		
		//
	//	confFormulas[0]="(  (! (\"Calcium - urgent\")) U (\"calcium\")) ";
	//	confFormulas[2]=" ([](    ((\"unconjugated bilirubin\") -> (<>(\"squamous cell carcinoma using eia\")))))/\\  (!(<>(\"bilirubin - total\")))";
		//confFormulas[0]="( [] ( (\"glucose\") -> (<>(\"Glucose - urgent\")))) \\/ (!(<>(\"glucose\")) )";
	//	confFormulas[2]="! (\"histological examination - biopsies nno\")) U (\"cytology - ectocervix -\"))";
		
//		confFormulas[3]=" (  [](     (  (\"unconjugated bilirubin\") ) ->  ( !   ( <> (\"teletherapy - megavolt photons bestrali\") ) )    )  )";
		
		
		
		
		
		
		
		
	//	confFormulas[0]="(  <>(\"tumor marker CA-19.9\") ) \\/ ( <> (\"ca-125 using meia\") )  ";
	//	confFormulas[1]="([](    ((\"CEA - tumor marker using meia\") -> ( <>(\"squamous cell carcinoma using eia\")))))";
	//	confFormulas[2]="(  (! (\"histological examination - biopsies nno\")) U (\"cytology - ectocervix -\"))";
		confFormulas[1]="(  (! (\"histological examination - biopsies nno\")) U (\"squamous cell carcinoma using eia\"))";
		confFormulas[0]="   ( <> (\"histological examination - big resectiep\") )   ";		
		
		
		
		
		
		
		
		
		
		
		
		
		//confFormulas[1]="( ( (!(\"calcium\")) /\\ (!(\"ca-125 using meia\"))  /\\ (!(\"glucose\"))  /\\ (!(\"urea\"))  /\\ (!(\"unconjugated bilirubin\")) )U (\"assumption laboratory\"))\\/ ([]( (!(\"calcium\")) /\\ (!(\"ca-125 using meia\"))  /\\ (!(\"glucose\"))  /\\ (!(\"urea\"))  /\\ (!(\"unconjugated bilirubin\")) ))";
	//	confFormulas[1]="( [](    ((\"calcium\") -> (\"Calcium - urgent\")) /\\  ((\"glucose\") -> (\"Glucose - urgent\"))   ) )";
		
		
		//###########################
		
	// Formula traceId eventPosition eventIndex isSat viol confidence support similarityThrashold
		
		//double accuracy = 0;
		//double totalCases = 0;

		pw.println("Formula\tTraceId\teventPosition\teventIndex\tpredictedValue\tactualValue\tConfidence\tSupport\tsimilarityThreshold");
		
	//	boolean start = false;
		for(String formulaLTL : confFormulas){
			double accuracy = 0;
			double totalCases = 0;
			for(XTrace trace : log){
			//	int x = 0;
			//	int y = trace.size()-1;
				//int eventIndex = 1;//(int)(Math.random()*(y-x))+x;
				int traceSize = trace.size();
			
		//		if(((XAttributeLiteral) trace.getAttributes().get("concept:name")).getValue().equals("00001100")){
		//			start = true;
		//		}
			//	if(start){
				int[] evIndexes = new int[3];
				evIndexes[0] = 0;
				evIndexes[1] = (int)(traceSize/4.f);
				evIndexes[2] = (int)(traceSize/2.f);
				int j=0;
				for(int eventIndex : evIndexes){
					pw.print(formulaLTL+"\t");
					pw.flush();
					pw.print(((XAttributeLiteral) trace.getAttributes().get("concept:name")).getValue()+"\t");
					pw.flush();
					
					if(j==0){
						pw.print("start\t");
						pw.flush();
						pw.print(evIndexes[j]+"\t");
						pw.flush();
					}else if(j==1){
						pw.print("early\t");
						pw.flush();
						pw.print(evIndexes[j]+"\t");
						pw.flush();
					}else{
						pw.print("middle\t");
						pw.flush();
						pw.print(evIndexes[j]+"\t");
						pw.flush();
					}
					
					j++;
				//if(trace.size()>2){
					Vector<Formula> formulas = new Vector<Formula>();
					Formula formula = new SimpleFormula(formulaLTL);
					formulas.add(formula);
					//XTrace trace = log.get(traceIndex);
					List<String> currentEvents = new Vector<String>();
					int i = 0;
					for(XEvent event : trace){
						currentEvents.add(event.getAttributes().get(XConceptExtension.KEY_NAME).toString());
						i++;
						if(i>eventIndex){
							break;
						}
					}

					Vector<String> currentVariables = new Vector<String>();
					for(String attribute : trace.get(eventIndex).getAttributes().keySet()){
						currentVariables.add(attribute);
					}

					Map<String, String> variables = new HashMap<String, String>();

					XAttributeMap traceAttr = trace.getAttributes();
					for(String attribute : traceAttr.keySet()){
						//if(!attribute.contains("date"))
						variables.put(attribute, traceAttr.get(attribute).toString());
					}
					i= 0;
					for(XEvent e : trace){
						XAttributeMap eventAttr = e.getAttributes();
						for(String attribute : eventAttr.keySet()){
							//	if(!attribute.contains("date"))
							variables.put(attribute, eventAttr.get(attribute).toString());
						}
						i++;
						if(i>eventIndex){
							break;
						}
					}

					String ltlFormula = formula.getLTLFormula();
					List<ltl2aut.formula.Formula> formulaeParsed = new ArrayList<ltl2aut.formula.Formula>();
					try {
						formulaeParsed.add(new DefaultParser(ltlFormula).parse());
					} catch (SyntaxParserException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treeFactory = DefaultTreeFactory.getInstance();
					ConjunctionFactory<? extends GroupedTreeConjunction> conjunctionFactory = GroupedTreeConjunction
							.getFactory(treeFactory);
					GroupedTreeConjunction conjunction = conjunctionFactory.instance(formulaeParsed);
					Automaton aut = conjunction.getAutomaton().op.reduce();
					ExecutableAutomaton execAut = new ExecutableAutomaton(aut);
					execAut.ini();
					PossibleNodes current = execAut.currentState();
					boolean violated = false;
					for(XEvent e : trace){
						String label = ((XAttributeLiteral) e.getAttributes().get("concept:name")).getValue();
						violated = true;
						current = execAut.currentState();
						//	XAttributeMap eventAttr = e.getAttributes();
						//	String label = ((XAttributeLiteral) e.getAttributes().get("concept:name")).getValue();
						if(current!=null && !(current.get(0)==null)){
							for (Transition out : current.output()) {
								if (out.parses(label)) {
									violated = false;
									break;
								}
							}
						}
						if(!violated){
							execAut.next(label);
						}else{
							break;
						}
					}
					current = execAut.currentState();
					if(!violated){
						if(!current.isAccepting()){
							violated = true;
						}
					}

					Map<String, Result> result = OperationalSupport.provideOperationalSupport(formulas,currentEvents, currentVariables, variables, trace.getAttributes().get(XConceptExtension.KEY_NAME).toString());
					Result prediction;
					boolean key = result.keySet().iterator().hasNext();
					if(key ==true){
						prediction = result.get(result.keySet().iterator().next());
//
	//					System.out.println("Confidence: "+ prediction.getConfidence());
System.out.print("Predicted: "+ prediction.isSatisfied());
System.out.println(" --- Real: "+ !violated);
						pw.print(prediction.isSatisfied()+"\t");
						pw.flush();
					
						pw.print(!violated+"\t");
						pw.flush();
						
						pw.print(prediction.getConfidence()+"\t");
						pw.flush();
						
						double support = prediction.getSupport();
						
						pw.print(support+"\t");
						pw.flush();
						
						pw.print(0.5+"\t");
						pw.flush();
						
						if(prediction.getConfidence()>=0.){
							totalCases ++;
							if(prediction.isSatisfied() && ! violated){
								accuracy++;
							}
							if(!prediction.isSatisfied() && violated){
								accuracy++;
							}
						}
					}
					pw.println("");
				}
	//		}
			}
			accuracy = accuracy / totalCases;
			pw.println("ACCURACY: "+accuracy);
			//	System.out.println(result.get(key));

		}


	}

	public String simplifiedPrettyString() {
		// TODO Auto-generated method stub
		return null;
	}



	public double getSupport() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isSatisfied() {
		// TODO Auto-generated method stub
		return false;
	}

	public double getConfidence() {
		// TODO Auto-generated method stub
		return 0;
	}

}