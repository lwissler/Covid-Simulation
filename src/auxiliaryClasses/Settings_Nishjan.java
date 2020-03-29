package auxiliaryClasses;

public class Settings_Nishjan {
		
	/*
	 * I cannot imagine an increased chance to test positive of more than 15x
	 * So it seems RKI vastly underestimated total infected population and by that overestimated the chance to be hospitalized
	 * Tweaking numbers here so sim fits the available data
	 * 
	 * maybe i can accept 20x higher chance to detect
	 * ok maybe even 25x
	 */
	
	/*
	 * Estimation: 
	 * >167000 tested 15.03. source world in data links to (https://mobil.stern.de/amp/gesundheit/covid-19--so-oft-wird-wirklich-auf-das-coronavirus-getestet-9189186.html)
	 * 150000 - 200000 weekly test capability source newspapers (zb https://www.spiegel.de/wissenschaft/medizin/coronavirus-testet-deutschland-zu-wenig-im-vergleich-zu-suedkorea-a-4fb86f9e-1a5f-4434-b05f-7fad3dda34f4?utm_source=pocket-newtab)
	 * total tests 300000 - 410000 tested 23.03 accounting for lost capacity or delayed capacity by lower minimum 
	 * total number of positive tests 29000 23.03. source john hopkins
	 * percent positive tests 9.67 - 7%
	 * 
	 * testing is not done randomly but by suspicion. so how much higher will suspicion drive positive test results vs random testing?
	 * estimation: 2 - 5 times higher
	 * leading to a total infected population of 4.8% - 1.4%
	 * infected population of 2-3% in germany is probable
	 */

//	public static double INCREASE_CHANCE_TO_TEST_POSITIVE = 25; //good case
	public static double INCREASE_CHANCE_TO_TEST_POSITIVE = 0.3; //bad case
	public static double ESTIMATE_CURRENT_INFECTED_RATIO_GERMANY = 0.025; // yes i know thats 2 million people but a decent amount is already healthy and immune again
	
	/*
	 * Estimation:
	 * confirmed positives 15.03 5800 source john hopkins
	 * confirmed tests 167000 15.03 world of data
	 * infected population: 5800/167000 = 3,47%
	 * by above estimates: 3,47%/2 - 3,47%/5 = 1,735% - 0,695%
	 * infected population of 1% is probable
	 * mean estimated population penetration (1.735+0.695)/2 = 1,215 -> (4.8+1.4)/2 = 3.1 leading to an estimated rate of spread of 2.5x within 8 days
	 * 8sqrt(2.56) = 1.124 is the estimated average daily rate of spread
	 * meaning 100 infected people have infected roughly 12,4 healthy people each day
	 * this is with strongly impeded daily life, closed restaurants
	 * 
	 * this growth rate is marginally too fast because the calculations do no account for healed/dead population
	 * small amount at this stage but nevertheless there. so reducing to 1.195-1.12
	 * 
	 */
	
	public static double DAILY_RATE_OF_SPREAD_SOCIAL_DISTANCING = 1.124;
//	public static double DAILY_RATE_OF_SPREAD_SOCIAL_DISTANCING = 1.3; //with reduced infectiousness
	
	/*
	 * Barrier for overall decline in cases is less than doubling numbers within 10 days (infection period)
	 * for that daily spread needs to be below 1.071
	 */
	
	/*
	 * unimpeded rate of spread by UK example
	 * 18.03 50000 people tested with 5000 tests per day (assumed weekday) previous week
	 * so 25000 people tested before 11.03. source https://www.gov.uk/government/news/testing-for-coronavirus-covid-19-will-increase-to-25-000-a-day
	 * 18.03. total positives 2600 john hopkins
	 * 11.03 total positives 459 john hopkins
	 * estimated infected population 18.03. 2600/50000 = 5% accounting for testing on suspicion/risk -> 1% - 2.5%
	 * estimated infected population 11.03. 459/25000 = 1.83% -> 0.366% - 0.915%
	 * mean estimated population penetration 0.64% -> 1.75% that is 3.11x in 7 days
	 * 7sqrt(1.75/0.64) = 1.176
	 * meaning 100 infected people have infected roughly 17.6 healthy people each day
	 * 
	 * also reducing to 1.1695-1.17
	 */
	
	public static double DAILY_RATE_OF_SPREAD_UNIMPEDED = 1.176;
//	public static double DAILY_RATE_OF_SPREAD_UNIMPEDED = 1.4; //with reduced infectiousness
	
	
	/*
	 * daily rate of spread with people not caring about the dangers of corona. party and socialising normally
	 * no idea how to estimate this yet
	 * top of the head 1.3
	 */
	
	public static double DAILY_RATE_OF_SPREAD_NO_CLUE = 1.27;
//	public static double DAILY_RATE_OF_SPREAD_NO_CLUE = 1.7;
	
	/*
	 * estimations for a daily rate of spread under the no contact rules will have to wait until evening of 30.03.
	 * top of the head estimate 1.08
	 * number confirmed 30.03 / 500k-600k tests -> ratio
	 * 3.5x suspicion chance increase
	 * 7sqrt(ratio/3.5/prevRatio(3.1)) = daily rate
	 * 7sqrt(550k/(numConfirmed*3.5*3.1)) = 
	 * 27.3. 50.9k
	 * 4sqrt(550k/(50.9k*3.5.3.1)) = 0.994
	 * 
	 */
	
	public static double DAILY_RATE_OF_SPREAD_NO_CONTACT = 1.06; //good case
//	public static double DAILY_RATE_OF_SPREAD_NO_CONTACT = 1.1; //bad case
//	public static double DAILY_RATE_OF_SPREAD_NO_CONTACT = 1.15; //reduced infection time
	 
	
	/*
	 * estimation fatality rate
	 * by RKI model 19 days until death from infection https://www.rki.de/DE/Content/InfAZ/N/Neuartiges_Coronavirus/Modellierung_Deutschland.pdf?__blob=publicationFile
	 * using UK as unimpeded model
	 * so by now on average the cases from 05.03. or before should have played out
	 * current number of deaths 336
	 * estimated population penetration 0.6% 11.03.
	 * by spread rate of 1.176 daily for three days and 1.3 for three days 
	 * leads to an estimated penetration of 0.6%/3,57 = 0.168%
	 * that is with 66400000 population roughly 115000 infected
	 * leading to fatality rate of 336/115000 = 0.00268 or roughly 0.29% without health system collapse
	 * might rise to regions of 0.5-1% with collapse
	 * 
	 * population germany 83000000
	 * with current penetration that would lead to a deathtoll of 6000-14000 people in germany by mid april
	 * depending on the capacity of the health system
	 * at a final penetration level of 70% this would lead to a total deathtoll in germany of 
	 * roughly 170000 to 500000 people
	 * expected deathtoll in europe within 1-2 years: 3 million nearly all of them above the age of 65
	 * expect corona to kill roughly 2.5% of europes population above 65
	 * 37 million europeans are older than 80. an estimated 1.5 million of them will die
	 * that is 4%
	 * 
	 */
	
	public static double FATALITY_RATE_NO_COLLAPSE = 0.0029;
	public static double FATALITY_RATE_COLLAPSE = 0.7;
	
//	0th to 9th+ decade source https://twitter.com/AndyBiotech/status/1241741127205572609/photo/1
//	public static double[] FATALITY_RATE = {0,0.001625,0.001275,0.001875,0.002975,0.008375,0.0269,0.0799,0.1584};
//	public static double FATALITY_OVERESTIMATION =
	
	public static long POPULATION = 83000000;

	/*
	 * Estimation chance to need a hospital place
	 * baseline RKI 4.5% https://www.rki.de/DE/Content/InfAZ/N/Neuartiges_Coronavirus/Modellierung_Deutschland.pdf?__blob=publicationFile
	 * leads to a morality rate of 0.56%
	 * 
	 * 
	 */
	public static final double HOSPITAL_WHEN_SICK = 0.002;
	
	/*
	 * chance to need intensive care by same RKI report if hospitalized 25%
	 * 
	 * 
	 * 
	 */
	
	public static final double INTENSIVE_CARE = 0.25;
	
	/*
	 * chance to die in intensive care assuming RKI to have taken middle between functioning and overwhelmed medical system
	 * functioning: 25%
	 * overwhelmed 75%
	 */
	
	public static final double DIE_FUNCTIONING = 0.3; //good case
//	public static final double DIE_FUNCTIONING = 0.4; //bad case
	public static final double DIE_OVERWHELMED = 0.9;
	
	/*
	 * care unit data from https://www.divi.de/aktuelle-meldungen-intensivmedizin/covid-19-erste-daten-fuer-die-anzahl-von-patienten-sowie-verfuegbaren-intensivbetten
	 * 
	 * estimating total intensive capacity at 15000;
	 * 4814 free with half reported. so about 9500 free. assuming roughly half used for corona and normal patients
	 * but assuming some of those will be needed for non corona patients
	 * 
	 * 
	 */
	
	public static int INTENSIVE_CAPACITY = 30000;
	
	/*
	 * base data from https://de.statista.com/statistik/daten/studie/157049/umfrage/anzahl-krankenhausbetten-in-deutschland-seit-1998/
	 * assuming raised capacity by 20% for corona
	 * assuming 1/3 needed for non corona patients
	 */
	
	public static int TOTAL_CAPACITY = 400000;
	
	/*
	 * precision metrics: does model conform to real world?
	 * first two deaths 9.3.
	 * 20.3. intensive care reported 323 (https://www.divi.de/aktuelle-meldungen-intensivmedizin/covid-19-erste-daten-fuer-die-anzahl-von-patienten-sowie-verfuegbaren-intensivbetten)
	 * roughly half available stations reported
	 * so roughly 600 corona patients should be in intensive care 20.3.
	 */
}
