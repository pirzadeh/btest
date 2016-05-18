package de.hybris.personas;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
	
	public <T> List<Method> getListOfAvailableInteractions(){
		Method[] methods = null;
		List<Method> annotatedMethods = new ArrayList<>();
		try {
            Class clazz = SmartEdit.class;
            methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++){
            	if	(methods[i].isAnnotationPresent(Interaction.class))
            		annotatedMethods.add(methods[i]);            	
            }
            	
        } catch (Throwable e) {
            System.err.println(e);
        }
		return annotatedMethods;
		
	}

}
