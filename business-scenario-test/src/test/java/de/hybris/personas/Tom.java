package de.hybris.personas;

import java.lang.reflect.Method;
import java.util.Random;

import de.hybris.base.Persona;
import de.hybris.pages.framework.SmartEdit;

public class Tom extends Persona {

	@Override
	public String reportExperience() {
		// TODO Auto-generated method stub
		return null;
	}

	public void predifinedScenario() {
		// TODO Auto-generated method stub
		
	}

	public void explore() {

		Random rand = new Random();
//		int  actionChoice = rand.nextInt(n) + 1;
		
	}

	public void explore(String strategy) {
		// TODO Auto-generated method stub
		
	}
	
	public <T> void getListOfAvailableInteractions(){
		try {
            Class c = SmartEdit.class;
            Method[] m = c.getDeclaredMethods();
            for (int i = 0; i < m.length; i++){
            	if	(m[i].isAnnotationPresent(Interaction.class))
            		System.out.println(m[i].toString());
            	
            }
            	
        } catch (Throwable e) {
            System.err.println(e);
        }
	}

}
