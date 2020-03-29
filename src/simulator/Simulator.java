package simulator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import auxiliaryClasses.DynamicArray;
import auxiliaryClasses.Paths;
import auxiliaryClasses.Person;
import auxiliaryClasses.PopulationNet;
import auxiliaryClasses.Settings;
import auxiliaryClasses.State;
import auxiliaryClasses.Tools;

public class Simulator {
	
	Long id = 0L;
	Tools tools = new Tools();
	ArrayList<Person> infected = new ArrayList<Person>();
//	LinkedList<Person> hospital = new LinkedList<Person>();
	HashMap<Double, Person> hospital = new HashMap<Double, Person>();
//	LinkedList<Person> intensive = new LinkedList<Person>();
	HashMap<Double, Person> intensive = new HashMap<Double, Person>();
	ArrayList<Person> dead = new ArrayList<Person>();
	ArrayList<Person> immune = new ArrayList<Person>();
	int tested = 0;
	int testedPositive = 0;
	String societyAction = "Normal Behaviour";
	
	/*
	 * date format: YYYYMMDD as int
	 * month 0 = January
	 * @param: futureContactType: setting how the contact will happen after the preliminary scheduled revision of no contact rule
	 * @param: initInfected number of infected persons at the start of the simulation
	 */
	public void simulate(int startYear, int startMonth, int startDay, int simulationLength, int futureContactType, int initInfected) throws ParseException{
		tools.print("---------Ö------------"+futureContactType+"-------------------------");
		int baseInfection = 1;
		int[] dates = getDates(startYear, startMonth, startDay, simulationLength);
		PopulationNet net = new PopulationNet(Settings.POPULATION, 20);
		int[] testingCapability = getTestingCapability(simulationLength);
		
//		int totalTested = 0;
//		for(int i = 0; i < simulationLength; i++){
//			totalTested+=testingCapability[i];
//			tools.print(dates[i]+" "+testingCapability[i]+" "+totalTested);
//		}
		tools.print("Datum, Betroffen, Erkrankt, Immun, Krankenhaus, Intensivstation, Gestorben, Positiv getestet, Gesamt getestet");
		simulateDays(dates,baseInfection, net, testingCapability, futureContactType, initInfected);
		
	}
	
	@SuppressWarnings("unchecked")
	private void simulateDays(int[] dates, int baseInfection, PopulationNet net, int[] testingCapability, int contactSwitch, int initialInfected) throws ParseException {
		double spreadRate = 0;
		Boolean functioningMedicalSystem = true;
		
		for(int i = 0; i < initialInfected; i++){
			Person patienZero = net.net[i];
			infected.add(patienZero);
			patienZero.currentState = State.INFECTED;
			patienZero.infectionDate = dates[0];
		}
		
		for(int i = 0; i < dates.length-1; i++){
			int date = dates[i];
//			if(i==18)
//				System.out.println();
			spreadRate = setSpreadRate(i,contactSwitch);
			if(intensive.size()>Settings.INTENSIVE_CAPACITY||hospital.size()+intensive.size()>Settings.TOTAL_CAPACITY){
				functioningMedicalSystem = false;
			}else{
				functioningMedicalSystem = true;
			}
			checkStateChange(dates[i], functioningMedicalSystem);
			int infectedPopulation = infected.size();
//			long start = System.currentTimeMillis();
			for(int k = 0; k < infectedPopulation; k++){
//				if(infected.get(k).infecting){
					infect(net,spreadRate, dates[i]);
//				}
			}
//			double elapsedTime = Math.round((System.currentTimeMillis() - start)/6000.0);
//			System.out.println("Elapsed Time Infect: " + elapsedTime/10 + " Minutes");
			
			test(testingCapability[i]);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat readableFormat = new SimpleDateFormat("dd.MM.");
			Date d = format.parse(new Integer(dates[i]).toString());
			String dateString = readableFormat.format(d);
			
//			System.out.println(i+" "+ dateString+" total touched: "+(infected.size()+immune.size())+" infected: " +infected.size()+" recovered: "+immune.size()+" hospitalized: "+hospital.size()+" intensive care: "+intensive.size()+" dead: "+dead.size()+ " positiv tested: "+testedPositive+" total tested: "+tested +" overwhelmed medical system: " +!functioningMedicalSystem);
			System.out.println(dateString+" , "+(infected.size()+immune.size())+" , " +infected.size()+" , "+immune.size()+" , "+hospital.size()+" , "+intensive.size()+" , "+dead.size()+ " , "+testedPositive+" , "+tested +" , " +!functioningMedicalSystem+", "+i+", "+spreadRate );
			
			JSONObject data = new JSONObject();
			data.put("date", tools.formatDate(d));
			data.put("totaltouched", infected.size()+immune.size());
			data.put("infected", infected.size());
			data.put("recovered", immune.size());
			data.put("hospitalized", hospital.size());
			data.put("intensive", intensive.size());
			data.put("testedpositive", testedPositive);
			data.put("tested", tested);
			data.put("overwhelmedSystem", !functioningMedicalSystem);
			data.put("societyAction", societyAction);
			try {
//				tools.postToMongo(data, Paths.coronaSim);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void test(int capacity) {
		tested += capacity;
		double testedP = infected.size()*Settings.INCREASE_CHANCE_TO_TEST_POSITIVE/Settings.POPULATION*capacity;
		if(infected.size()>1500000L){
			testedP = testedP*3/4;
		}else if(infected.size()>4000000L){
			testedP = testedP*2/4;
		}else if(infected.size()>8000000L){
			testedP = testedP*1/4;
		}
		if(testedP > 1){
			testedPositive += Math.round(testedP);
		}else{
			if(Math.random()<testedP){
				testedPositive++;
			}
		}
	}

	private void checkStateChange(int date, Boolean functioning) throws ParseException {
		long start = System.currentTimeMillis();
		for(int i = 0; i < infected.size(); i++){
			Person sickPerson = infected.get(i);
//			int infectingChangeDate = tools.addDays(sickPerson.infectionDate,3,0);
//			if(infectingChangeDate==date){
//				sickPerson.infecting = true;
//			}else{
//				infectingChangeDate = tools.addDays(sickPerson.infectionDate,13,0);
//				if(infectingChangeDate==date){
//					sickPerson.infecting = false;
//				}
//			}
			if(sickPerson.currentState==State.HOSPITALIZED){
				int stateChangeDate = tools.addDays(sickPerson.infectionDate,10,0);
				if(stateChangeDate==date){
					if(Math.random()<Settings.INTENSIVE_CARE&&functioning){
						sickPerson.currentState = State.INTENSIVE;
						hospital.remove(sickPerson.id);
						intensive.put(sickPerson.id, sickPerson);
					}else if(Math.random()<Settings.DIE_OVERWHELMED&&!functioning){
						sickPerson.currentState = State.DEAD;
						dead.add(sickPerson);
						hospital.remove(sickPerson.id);
						infected.remove(i);
						i--;
					}else{
						infected.remove(i);
						immune.add(sickPerson);
						hospital.remove(sickPerson);
						sickPerson.currentState = State.IMMUNE;
						i--;
					}
				}else{
					stateChangeDate = tools.addDays(sickPerson.infectionDate,18,0);
					if(stateChangeDate==date){
						infected.remove(i);
						immune.add(sickPerson);
						hospital.remove(sickPerson);
						sickPerson.currentState = State.IMMUNE;
						i--;
					}
				}
			}else if(sickPerson.currentState==State.INTENSIVE){
				int stateChangeDate = tools.addDays(sickPerson.infectionDate,18,0);
				if(stateChangeDate==date){
					double rng = Math.random();
					if(rng<Settings.DIE_FUNCTIONING&&functioning){
						sickPerson.currentState = State.DEAD;
						dead.add(sickPerson);
					}else{
						sickPerson.currentState = State.IMMUNE;
						immune.add(sickPerson);
					}
					intensive.remove(sickPerson.id);
					infected.remove(i);
					i--;
				}
			}else if(sickPerson.currentState==State.INFECTED){
				int stateChangeDate = tools.addDays(infected.get(i).infectionDate,8,0);
				if(stateChangeDate==date){
					if(Math.random()<Settings.HOSPITAL_WHEN_SICK){
						sickPerson.currentState = State.HOSPITALIZED;
						sickPerson.id = id++;
						hospital.put(sickPerson.id,sickPerson);
					}
				}else{
					stateChangeDate = tools.addDays(sickPerson.infectionDate,18,0);
					if(stateChangeDate==date){
						infected.remove(i);
						immune.add(sickPerson);
						sickPerson.currentState = State.IMMUNE;
						i--;
					}
				}
			}
		}
		double elapsedTime = Math.round((System.currentTimeMillis() - start)/6000.0);
//		System.out.println("Elapsed Time State change: " + elapsedTime/10 + " Minutes");
		
	}

//	private void removeFromIntensive(Person sickPerson) {
//		for(int i = 0; i < intensive.size(); i++){
//			if(sickPerson.infectionDate==intensive.get(i).infectionDate){
//				intensive.remove(i);
//			}
//		}
//		
//	}
//
//	private void removeFromHospital(Person sickPerson) {
//		for(int i = 0; i < hospital.size(); i++){
//			if(sickPerson.infectionDate==hospital.get(i).infectionDate){
//				hospital.remove(i);
//			}
//		}
//	}

	private void infect(PopulationNet net, double spreadRate, int date) {
		Random generator = new Random();
		Person randomPerson = net.net[generator.nextInt(net.net.length)];
		if(Math.random()<spreadRate&&randomPerson.currentState!=State.INFECTED&&randomPerson.currentState!=State.HOSPITALIZED&&randomPerson.currentState!=State.IMMUNE&&randomPerson.currentState!=State.INTENSIVE){
			randomPerson.currentState = State.INFECTED;
			randomPerson.infectionDate = date;
			infected.add(randomPerson);
		}
	}

	private double setSpreadRate(int i, int contactSwitch) {
		double spreadRate = 0;
//		i = i-10;
		if(i < 15)
			spreadRate = Settings.DAILY_RATE_OF_SPREAD_NO_CLUE-1;
		else if(i<35){
			spreadRate = Settings.DAILY_RATE_OF_SPREAD_UNIMPEDED-1; societyAction = "Careful";
		}else if(i<55){
			spreadRate = Settings.DAILY_RATE_OF_SPREAD_SOCIAL_DISTANCING-1; societyAction = "Social Distancing";
		}
//		else if(contactSwitch==3){
//			spreadRate = Settings.DAILY_RATE_OF_SPREAD_NO_CONTACT-1;
//		}else if(contactSwitch==2){
//			spreadRate = Settings.DAILY_RATE_OF_SPREAD_SOCIAL_DISTANCING-1;
//		}else if(contactSwitch==1){
//			spreadRate = Settings.DAILY_RATE_OF_SPREAD_UNIMPEDED-1;
//		}
		else if(i<75){
			spreadRate = Settings.DAILY_RATE_OF_SPREAD_NO_CONTACT-1; societyAction = "No Contact";
		}else{
			if(contactSwitch==0){
				spreadRate = Settings.DAILY_RATE_OF_SPREAD_NO_CLUE-1; societyAction = "Normal";
			}else if(contactSwitch==1){
				spreadRate = Settings.DAILY_RATE_OF_SPREAD_UNIMPEDED-1; societyAction = "Careful";
			}else if(contactSwitch==2){
				spreadRate = Settings.DAILY_RATE_OF_SPREAD_SOCIAL_DISTANCING-1; societyAction = "Social Distancing";
			}else if(contactSwitch==3){
				spreadRate = Settings.DAILY_RATE_OF_SPREAD_NO_CONTACT-1; societyAction = "No Contact";
			}
		}
		return spreadRate;
	}

	private int[] getTestingCapability(int simulationLength) {
		int[] testingCapability = new int[simulationLength];
		
		/*
		 * loads of assumptions here need data
		 * how many tests did germany perform per day
		 */
//		for(int i = 0; i < 15; i++){
//			testingCapability[i] = 0;
//		}
		
		for(int i = 0; i < 10; i++){
			testingCapability[i] = 1000;
		}
		
		for(int i = 10; i < 20; i++){
			testingCapability[i] = 1500;
		}
		
		for(int i = 20; i < 30; i++){
			testingCapability[i] = 2500;
		}
		
		for(int i = 30; i < 40; i++){
			testingCapability[i] = 5000;
		}
		
		for(int i = 40; i < 50; i++){
			testingCapability[i] = 11000;
		}
		
		for(int i = 50; i < 60; i++){
			testingCapability[i] = 22000;
		}
		
		for(int i = 60; i < 80; i++){
			testingCapability[i] = 25000;
		}
		
		for(int i = 80; i < simulationLength; i++){
			testingCapability[i] = 30000;
		}
//		
//		for(int i = 150; i < simulationLength; i++){
//			testingCapability[i] = 30000;
//		}
		
		return testingCapability;
	}

	private int[] getDates(int startYear, int startMonth, int startDay, int simulationLength){
		int[] dates = new int[simulationLength];
		SimpleDateFormat format = new SimpleDateFormat ("yyyyMMdd");
		
		Calendar c = Calendar.getInstance();
		c.set(startYear, startMonth, startDay);
		for(int i = 0; i < simulationLength-1; i++){
			String dateString = format.format(c.getTime());
//			tools.print(dateString);
			dates[i] = Integer.parseInt(dateString);
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return dates;
	}
}
