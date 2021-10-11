/**
 * Package AWTreech
 */
package awtreech;

import java.util.ArrayList;
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
	boolean push = false; // Push : True = Encerra o jogo, False = Não encerra o jogo
	String qQuestions[] = {"a", "b", "c", "d", "e"}; // Alternativas disponíveis no jogo

	/**
	 * Gateway para sair do jogo
	 */
	public void exit() {
		System.exit(0);
	}

	/**
	 * Gateway para respostas corretas e incorretas
	 * 
	 * Descrição variáveis:
	 * String data : resposta escolhida pelo usuário
	 * int eLife : Vidas extras do player
	 * String cQuestion : Resposta correta para validação
	 * int level : Nível do jogo
	 * int nQuestion: Número da questão
	 */
	public void gateway(String data, int eLife,  String cQuestion, int level, int nQuestion) {
		// Caso o jogador decida sair do jogo
		if (data.toLowerCase().equals("sair"))
			principal.exit();
			
		if (data.toLowerCase().equals(cQuestion)) {
			switch (level) {
				case 1: // Histórias e desafios - Fácil
					System.out.println("Colocar fácil aqui");
					break;

				case 2: // Histórias e desafios - Médio
					System.out.println("Resposta correta");
					nQuestion++; // Caso acerte a resposta, é dado continuação ao jogo
					principal.medium(nQuestion, push, eLife);
					break;
					
				case 3: // Histórias e desafios - Difícil
					System.out.println("Colocar dificil aqui");
					break;
			}
		} else {
			push = false;
			eLife--;
			System.out.println("Resposta incorreta");

			switch (level) {
				case 1: // Histórias e desafios - Fácil
					System.out.println("Colocar fácil aqui");
					break;

				case 2: // Histórias e desafios - Médio
					// Caso o player exceda o número de vidas o jogo finalizado
					if (eLife <= 0) {
						push = true;
						principal.medium(nQuestion, push, eLife);
					} else { // Histórias e desafios - Difícil
						// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente
						push = false;
						principal.medium(nQuestion, push, eLife);
					}
					break;
					
				case 3:
					System.out.println("Colocar dificil aqui");
					break;
			}
		}
		
	}

	/**
	 * Questões nível Médio
	 */
	public void medium(int nQuestion, boolean noPush, int extraLife) {
		ArrayList<String> qResponses = new ArrayList<>();
		String alternativa;	

		if (noPush) {
			// Saída - Histórias negativas
			System.out.println("Você perdeu");
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
	 * @param args
	 */
	public static void main(String[] args) {
		// principal.medium(1, false, 3);
	}
}
