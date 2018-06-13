package spaceship;

import java.util.LinkedList;

public class Physics {
	
	public static boolean Collision(InterfaceA enta, InterfaceB entb) {
				
			if(enta.getBounds().intersects(entb.getBounds())) {			
				return true;
			}		
		
		
	return false;
	}
	
	
	
	public static boolean Collision(InterfaceB entb,InterfaceA enta) {
				
			if(entb.getBounds().intersects(enta.getBounds())) {			
				return true;
			}				
		
	return false;
	}
}
