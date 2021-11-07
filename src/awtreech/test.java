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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		G6 principal = new G6();
		
		try {
			principal.credits();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
