package auxiliaryClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

public class PopulationNet {
	
//	HashMap<String, Person> net;
	public Person[] net;
	
	public PopulationNet(long size, int connectivity){
		System.out.println("Building population..");
//		net = new HashMap<String, Person>();
		net = new Person[(int)size];
		for(int i = 0; i < size; i++){
			if(i%1000000==0)
				System.out.print(i+" ");
			Person p = new Person();
			net[i] = p;
//			connect(p,connectivity);
		}
		System.out.println();
	}
	
//	public void connect(Person p, int connectivity){
//		List<String> valuesList = new ArrayList<String>(net.keySet());
//		Collections.shuffle( valuesList );
//
//		int c = 0;
//		for(int i = 0; i<valuesList.size()&&c<connectivity; i++){
//			if(Math.random()<0.2){
//				p.connect(valuesList.get(i));
//				c++;
//			}
//		}
//	}
}
