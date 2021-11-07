/**
 * 
 */
package awtreech;
/**
 * @author Danilo
 *
 */
public class test {
	static G6 principal = new G6();

	public static void mediumTest() throws Exception {
		// Importando variável
		int test = principal.level = 2;

		// Mostrando variável na tela
		System.out.println(test);

		// Testando função Stories
		for (int i = 0; i <= 14; i++) {
			// Caso exista essa história é retornado true, e é verificado no if se passou no teste ou não
			if (principal.stories(i, false)) {
				System.out.println("Passou no teste");
			} else {
				System.out.println("Não passou no teste");
			}
		}

		// Caso exista essa história é retornado true, e é verificado no if se passou no teste ou não
		if (principal.stories(15, false)) {
			System.out.println("Passou no teste");
		} else {
			System.out.println("Não passou no teste");
		}
		
	}

	/**
	 * Método de teste
	 */
	public static void easyTest() {
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {		
		mediumTest();
	}

}
