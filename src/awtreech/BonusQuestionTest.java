package awtreech;
import java.util.ArrayList;

public class BonusQuestionTest {
    	// Inicializando
	static G6 principal = new G6();
	static int passedOnTest = 0, noPassedOnTest = 0;
	static ArrayList<String> arrPassedOnTest = new ArrayList<>();
	static ArrayList<String> arrNoPassedOnTest = new ArrayList<>();
	/**
	 * CT01: Passando no método bonusQuestion no parâmetro nQuestion para as questoes bonus
	 * Resultado esperado: O retorno do método deve ser verdadeiro para chamar a questao
	 */
	private static void testBonusQuestion() {
		boolean bonusQuestions = principal.bonusQuestion(4,"A");
		// Verifica o retorno esperado é false
		if (bonusQuestions) {
			System.out.println("Passou no teste! - CT01");
			passedOnTest++;
			arrPassedOnTest.add("1");
		} else {
			System.out.println("Não passou no teste! - CT01");
			noPassedOnTest++;
			arrNoPassedOnTest.add("1");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testBonusQuestion();
		
		System.out.println("Casos de testes com resultados esperados com sucesso: " + passedOnTest);
		System.out.println("Casos de testes com falhas: " + noPassedOnTest);

		System.out.println("\n Caso de teste com retorno positivo: ");
		for (String CTOnPassed : arrPassedOnTest) {
			System.out.print("\t" + CTOnPassed);
		}

		System.out.println("\n Caso de teste com retorno negativo: ");
		for (String CTNoPassed : arrNoPassedOnTest) {
			System.out.print("\t" + CTNoPassed);
		}
	}
}
