package simulator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Date;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PrintStream out;
		Date now = new Date();
		String nowStr = now.toLocaleString().replace(":", "-").replace(".", "_");
		try {
			out = new PrintStream(new FileOutputStream("./log/No_contact_"+nowStr+".txt"));
			System.setOut(out);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Simulator simulator = new Simulator();
		try {
			//good case
//			simulator.simulate(2020, 0, 30, 150,3,140);
//			simulator.simulate(2020, 0, 30, 150,2,140);
//			simulator.simulate(2020, 0, 30, 150,1,140);
//			simulator.simulate(2020, 0, 30, 150,0,140);
			
			//bad case
//			simulator.simulate(2020, 0, 30, 130,3,190);
//			simulator = new Simulator();
			simulator.simulate(2020, 0, 30, 115,2,190);
			simulator = new Simulator();
			simulator.simulate(2020, 0, 30, 115,1,190);
//			simulator.simulate(2020, 0, 30, 200,0,190);
			
//			simulator.simulate(2020, 0, 20, 200,2,150);nishjan
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
