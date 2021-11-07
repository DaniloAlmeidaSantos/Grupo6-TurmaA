/**
 * 
 */
package awtreech;

import awtreech.G6;

/**
 * @author Danilo
 *
 */
public class test {
	static G6 principal = new G6();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		try {
			principal.credits();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
