/**
 * Package AWTreech
 */
package awtreech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author's Danilo, Fernando && Guilherme
 *
 */
public class G6 {
	// Incializando classe principal
	static G6 principal = new G6();

	// Scanner Global
	Scanner input = new Scanner(System.in);

	// Var Global
	private boolean push = false; // Push : True = Encerra o jogo, False = Não encerra o jogo
	private String qQuestions[] = {"a", "b", "c", "d", "e"}; // Alternativas disponíveis no jogo
	private ArrayList<String> qResponses = new ArrayList<>();

	/**
     * Foi determinado nos cases de 1 a 3 o total de vidas extras
     * 
     * Descrição dos parâmetros:
     * @param selectLevel : definir nível escolhido pelo usuário
     */
    public void level(int selectLevel) {
		try {
			switch(selectLevel){

				case 1:
					System.out.println("Nivel easy selecionado, Voce tem 3 vidas extras");
					principal.easy(1, this.push, 3);
					break;
	
				case 2:
					System.out.println("Nivel medium selecionado, Voce tem 2 vidas extras");
					principal.medium(1, this.push, 2, false);
					break;
	
				case 3:
					System.out.println("Nivel medium selecionado, Voce tem 2 vidas extras");
					principal.hard(1, this.push, 1);
					break;
	
				default:
					System.out.println("Nivel Invalido");
					principal.level(3);
					break;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
    }

	/**
	 * Gateway para sair do jogo
	 */
	public void exit() {
		System.exit(0);
	}

	/**
	 * 
	 */
	public void instructions() {
		
	}

	/**
	 * Gateway para respostas corretas e incorretas
	 * 
	 * Descrição parâmetros:
	 * @param data : resposta escolhida pelo usuário
	 * @param eLife : Vidas extras do player
	 * @param cQuestion : Resposta correta para validação
	 * @param level : Nível do jogo
	 * @param nQuestion : Número da questão
	 */
	public void gateway(String data, int eLife,  String cQuestion, int level, int nQuestion) {
		try {
			// Caso o jogador decida sair do jogo
			if (data.toLowerCase().equals("sair")) principal.exit();
				
			if (data.toLowerCase().equals(cQuestion)) {
				nQuestion++;
				switch (level) {
					case 1: // Histórias e desafios - Fácil
						System.out.println("Colocar fácil aqui");
						break;

					case 2: // Histórias e desafios - Médio
						System.out.println("Resposta correta");
						principal.medium(nQuestion, this.push, eLife, false);
						break;
						
					case 3: // Histórias e desafios - Difícil
						System.out.println("Colocar dificil aqui");
						break;
				}
			} else {
				System.out.println("Resposta incorreta");
				eLife--;
				switch (level) {
					case 1: // Histórias e desafios - Fácil
						System.out.println("Colocar fácil aqui");
						break;

					case 2: // Histórias e desafios - Médio
						if (eLife < 0) principal.medium(nQuestion, this.push = true, eLife, true);
						// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente
						else principal.medium(nQuestion, this.push, eLife, true); 
						break;
						
					case 3: // Histórias e desafios - Difícil
						System.out.println("Colocar dificil aqui");
						break;
				}
			}	
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Questões nível Médio
	 * 
	 *  Descrição parâmetros:
	 * @param nQuestion : número de questões restantes
	 * @param noPush : Verifica se faz push da função de história negativa
	 * @param extraLife : Vidas extras do player
	 */
	public void easy(int nQuestion, boolean noPush, int extraLife) {
		
		String alternativa;	

		if (noPush) {
			// Saída - Histórias negativas
		} else {
			// Saída histórias positivas
			switch (nQuestion) {
				case 1:
					// Chamar push da história positiva
					qResponses.add("101010");
					qResponses.add("101011");
					qResponses.add("111010");
					qResponses.add("101110");
					qResponses.add("101111");

					System.out.println("O valor em binário do decimal 42 é:");
					System.out.println(qQuestions[0] + ") " + qResponses.get(0)); // resposta correta
					System.out.println(qQuestions[1] + ") " + qResponses.get(1));
					System.out.println(qQuestions[2] + ") " + qResponses.get(2));
					System.out.println(qQuestions[3] + ") " + qResponses.get(3));
					System.out.println(qQuestions[4] + ") " + qResponses.get(4));
					System.out.print("Escolha uma alternativa: ");

					alternativa = input.next();	
					principal.gateway(alternativa, extraLife, qQuestions[0], 2, nQuestion); // Chamando o gateway
					break;

				case 2:			
					break;
				case 3:			
					break;
				case 4:				
					break;
				case 5:			
					break;
				case 6:				
					break;
				case 7:

					break;
			}
		}
	}

	/**
	 * Questões nível Médio
	 * 
	 *  Descrição parâmetros:
	 * @param nQuestion : número de questões restantes
	 * @param noPush : Verifica se faz push da função de história negativa
	 * @param extraLife : Vidas extras do player
	 * @param reloadd : Valida se essa execução é pertinente de erro ou não
	 */
	public void medium(int nQuestion, boolean noPush, int extraLife, Boolean reload) throws Exception {
		
		try {
			String alternativa;	
			int index = 0;

			if (noPush) {
				// Saída - Histórias negativas
			} else {
				// Saída histórias positivas
				switch (nQuestion) {
					case 1:
						qResponses.clear();
						
						// Chamar push da história positiva
						qResponses.add("101010"); // resposta correta
						qResponses.add("101011");
						qResponses.add("111010");
						qResponses.add("101110");
						qResponses.add("101111");

						Collections.shuffle(qResponses);

						System.out.println("O valor em binário do decimal 42 é:");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("101010"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], 2, nQuestion); // Chamando o gateway
						break;

					case 2:			
						break;
					case 3:			
						break;
					case 4:				
						break;
					case 5:			
						break;
					case 6:				
						break;
					case 7:

						break;
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Questões nível Médio
	 * 
	 *  Descrição parâmetros:
	 * @param nQuestion : número de questões restantes
	 * @param noPush : Verifica se faz push da função de história negativa
	 * @param extraLife : Vidas extras do player
	 */
	public void hard(int nQuestion, boolean noPush, int extraLife) {
		
		String alternativa;	

		if (noPush) {
			// Saída - Histórias negativas
		} else {
			// Saída histórias positivas
			switch (nQuestion) {
				case 1:
					// Chamar push da história positiva
					qResponses.add("101010");
					qResponses.add("101011");
					qResponses.add("111010");
					qResponses.add("101110");
					qResponses.add("101111");

					System.out.println("O valor em binário do decimal 42 é:");
					System.out.println(qQuestions[0] + ") " + qResponses.get(0)); // resposta correta
					System.out.println(qQuestions[1] + ") " + qResponses.get(1));
					System.out.println(qQuestions[2] + ") " + qResponses.get(2));
					System.out.println(qQuestions[3] + ") " + qResponses.get(3));
					System.out.println(qQuestions[4] + ") " + qResponses.get(4));
					System.out.print("Escolha uma alternativa: ");

					alternativa = input.next();	
					principal.gateway(alternativa, extraLife, qQuestions[0], 2, nQuestion); // Chamando o gateway
					break;

				case 2:			
					break;
				case 3:			
					break;
				case 4:				
					break;
				case 5:			
					break;
				case 6:				
					break;
				case 7:

					break;
			}
		}
	}

	/**
	 * 
	 */
	public void stories() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		principal.level(4);
	}
}
