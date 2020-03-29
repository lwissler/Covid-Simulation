package auxiliaryClasses;

import java.awt.List;
import java.util.UUID;

public class Person {
	
	public double id;
	public State currentState = State.HEALTHY;
	public int infectionDate = 0;
	public int scheduledStateChangeDate = 21991231;
	public State nextState = State.HEALTHY;
	public Boolean infecting = false;
//	String id =  UUID.randomUUID().toString();
//	DynamicArray<String> connections = new DynamicArray<String>();
	
//	public void connect(String id){
//		connections.add(id);
//	}

}
