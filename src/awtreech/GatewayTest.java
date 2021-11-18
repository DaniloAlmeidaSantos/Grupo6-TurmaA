/**
 * Classe de teste do método Gateway
 */
package awtreech;

import java.util.ArrayList;

/**
 * @author Danilo Almeida
 *
 */
public class GatewayTest {
	// Inicializando
	static G6 principal = new G6();
	static int passedOnTest = 0, noPassedOnTest = 0;
	static ArrayList<String> arrPassedOnTest = new ArrayList<>();
	static ArrayList<String> arrNoPassedOnTest = new ArrayList<>();
	/**
	 * CT01: Passando no método gateway no parâmetro nQuestion maior que 7
	 * Resultado esperado: O retorno do método deve ser false, pois não existe desafio maior que 7
	 */
	private static void gatewayMaxQuestions() {
		boolean gateway = principal.gateway("verdade", 3, "verdade", 2, 8, true);
		// Verifica o retorno esperado é false
		if (!gateway) {
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
	 * CT02: Passando no método gateway no parâmetro level maior que 7
	 * Resultado esperado: O retorno do método deve ser false, pois não existe level maior que 3
	 */
	private static void gatewayMaxLevel() {
		boolean gateway = principal.gateway("verdade", 3, "verdade", 4, 6, true);
		// Verifica o retorno esperado é false
		if (!gateway) {
			System.out.println("Passou no teste! - CT02");
			passedOnTest++;
			arrPassedOnTest.add("2");
		} else {
			System.out.println("Não passou no teste! - CT02");
			noPassedOnTest++;
			arrNoPassedOnTest.add("2");
		}
	}

	/**
	 * CT03: Passando no método gateway no parâmetro eLife = 0 e resposta incorreta
	 * Resultado esperado: O retorno do método deve ser false, pois sua última tentativa não foi acertada
	 */
	private static void gatewayLastChanceV1() {
		boolean gateway;
		// Estrutura de repetição para executar em todos o level's com i
		for (int i = 1; i <= 3; i++) {
			gateway = principal.gateway("verdade", 0, "falso", i, 1, false);
			// Verifica o retorno esperado é false
			if (!gateway) {
				System.out.println("Passou no teste! - CT03");
				passedOnTest++;
				arrPassedOnTest.add("3.level."+i);
			} else {
				System.out.println("Não passou no teste! - CT03");
				noPassedOnTest++;
				arrNoPassedOnTest.add("3.level."+i);
			}
		}
	}

	/**
	 * CT04: Passando no método gateway no parâmetro eLife = 0 e resposta correta
	 * Resultado esperado: O retorno do método deve ser true
	 */
	private static void gatewayLastChanceV2() {
		boolean gateway;
		// Estrutura de repetição para executar em todos o level's com i
		for (int i = 1; i <= 3; i++) {
			gateway = principal.gateway("verdade", 0, "verdade", i, 1, false);
			// Verifica o retorno esperado é true
			if (gateway) {
				System.out.println("Passou no teste! - CT04");
				passedOnTest++;
				arrPassedOnTest.add("4.level."+i);
			} else {
				System.out.println("Não passou no teste! - CT04");
				noPassedOnTest++;
				arrNoPassedOnTest.add("4.level."+i);
			}
		}
	}

	/**
	 * CT05: Passar no parâmetro eLife = número negativos
	 * Resultado esperado: Retorno deve ser false e finaliza o jogo.
	 */
	private static void gatewayNegativeELife() {
		boolean gateway = principal.gateway("verdade", -1, "verdade", 2, 1, true);
		// Verifica o retorno esperado é false
		if (!gateway) {
			System.out.println("Passou no teste! - CT05");
			passedOnTest++;
			arrPassedOnTest.add("5");
		} else {
			System.out.println("Não passou no teste! - CT05");
			noPassedOnTest++;
			arrNoPassedOnTest.add("5");
		}
	}

	/**
	 * Retorna os resultados da execução dos testes
	 */
	public static void expectedResults() {
		System.out.println("\nCasos de testes com resultados esperados com sucesso: " + passedOnTest);
		System.out.println("Casos de testes com falhas: " + noPassedOnTest);

		System.out.println("\n Caso de teste com retorno positivo: ");
		for (String CTOnPassed : arrPassedOnTest) {
			if (CTOnPassed.length() == 0) System.out.println("\t Sem retorno positivo!"); 
			else System.out.print("\t" + CTOnPassed);
		}

		System.out.println("\n Caso de teste com retorno negativo: ");
		for (String CTNoPassed : arrNoPassedOnTest) {
			if (arrNoPassedOnTest.isEmpty()) System.out.println("\t Sem retorno negativo!"); 
			else System.out.print("\t" + CTNoPassed);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		gatewayMaxQuestions();
		gatewayMaxLevel();
		gatewayLastChanceV1();
		gatewayLastChanceV2();
		gatewayNegativeELife();

		// Retornando resultados:
		expectedResults();
	}

}
