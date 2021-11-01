/**
 * Package AWTreech
 */
package awtreech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Danilo Almeida
 * @author Fernando Nascimento
 * @author Guilherme Nascimento
 *
 */
public class G6 {
	// Incializando classe principal
	static G6 principal = new G6();

	// Scanner Global
	static Scanner input = new Scanner(System.in);

	// Var Global
	private boolean push = false; // Push : True = Encerra o jogo, False = Não encerra o jogo
	private final String[] qQuestions = {"a", "b", "c", "d", "e"}; // Alternativas disponíveis no jogo
	private ArrayList<String> qResponses = new ArrayList<>();
	private boolean bonus = false; // Verifica se foi realizado push para bonusQuestion
	static String name; //name do jogador
	static int level; // easy = 1 , medium = 2 , hard = 3

	/**
	 * Gateway para sair do jogo
	 */
	public void exit() {
		System.exit(0);
	}

	/**
	 * Limpa tela
	 */
	public void clearScreen(){
		char esc = 27;
		String clear = esc + "[2J"; //codigo ansi para limpar a tela
		System.out.println(clear);
	}

	/**
     * Foi determinado nos cases de 1 a 3 o total de vidas extras
     * 
     * Descrição dos parâmetros:
     * @param selectLevel : definir nível escolhido pelo usuário
     */
    public void level(int selectLevel) {
		try {
			switch (selectLevel) {
				case 1 :  // Fácil
					System.out.println("Nivel fácil selecionado, Voce tem 3 vidas extras");
					principal.easy(1, this.push, 3);
					break;
				case 2: // Médio
					System.out.println("Nivel médio selecionado, Voce tem 2 vidas extras");
					principal.medium(1, this.push, 2);
					break;
				case 3: // Difícil
					System.out.println("Nivel difícil selecionado, Voce tem 2 vidas extras");
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
	 * Gateway para respostas corretas e incorretas
	 * 
	 * Descrição parâmetros:
	 * @param data : resposta escolhida pelo usuário
	 * @param eLife : Vidas extras do player
	 * @param cQuestion : Resposta correta para validação
	 * @param level : Nível do jogo
	 * @param nQuestion : Número da questão
	 */
	public void gateway(String data, int eLife,  String cQuestion, int level, int nQuestion, boolean bonusQ) {
		try {
			// Caso o jogador decida sair do jogo
			if (data.equalsIgnoreCase("sair")) principal.exit();

			if (data.toLowerCase().equals(cQuestion)) {
				if (nQuestion == 7){ // Se o jogador acertou a questão ele é redirecionado para o final da história
					principal.clearScreen();
					principal.stories(nQuestion, true);
					principal.stories(10, true);
				} else {
					nQuestion++;
					switch (level) {
						case 1: // Histórias e desafios - Fácil
							System.out.println("\n Resposta correta \n");
							principal.stories(nQuestion, true);
							principal.coteTime(3000);
							principal.easy(nQuestion, this.push, eLife);
							break;

						case 2: // Histórias e desafios - Médio
							System.out.println("\n Resposta correta \n");
							principal.stories(nQuestion, true);
							principal.coteTime(3000);
							principal.medium(nQuestion, this.push, eLife);
							break;
							
						case 3: // Histórias e desafios - Difícil
							System.out.println("\n Resposta correta \n");
							principal.stories(nQuestion, true);
							principal.coteTime(3000);
							principal.hard(nQuestion, this.push, eLife);
							break;
					}
				}
			} else {
				System.out.println("\n Resposta incorreta \n");
				
				if (!bonusQ) eLife--;

				if (!bonus && eLife == 0) { // Verifica se já foi realizado push e se qtde de vidas extras é igual a 0
					principal.stories(0, false);
					bonus = true;
					principal.bonusQuestion(nQuestion);
				}

				switch (level) {
					case 1: // Histórias e desafios - Fácil
						if (eLife < 0) {
							principal.easy(nQuestion, this.push = true, eLife);
						} else {
							principal.stories(9, false);
							// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente 
							principal.easy(nQuestion, this.push, eLife); 
						}
						break;

					case 2: // Histórias e desafios - Médio
						if (eLife < 0) {
							principal.medium(nQuestion, this.push = true, eLife);
						} else {
							principal.stories(9, false);
							// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente
							principal.medium(nQuestion, this.push, eLife); 
						}
						break;
						
					case 3: // Histórias e desafios - Difícil
						if (eLife < 0) {
							principal.hard(nQuestion, this.push = true, eLife);
						} else {
							principal.stories(9, false);
							// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente 
							principal.hard(nQuestion, this.push, eLife); 
						}
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
	 * @param nQuestion : número da questão atual
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
					principal.gateway(alternativa, extraLife, qQuestions[0], 2, nQuestion, false); // Chamando o gateway
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
	 * @param nQuestion : número da questão atual
	 * @param noPush : Verifica se faz push da função de história negativa
	 * @param extraLife : Vidas extras do player
	 */
	public void medium(int nQuestion, boolean noPush, int extraLife) {
		// Limpando a tela
		principal.clearScreen();

		try {
			String alternativa;	
			int index = 0;

			if (noPush) {
				principal.stories(8, false);
				System.out.println("GAME OVER!");
				principal.exit();
			} else {
				System.out.println("Você tem "+ extraLife + " restante(s)");
				
				switch (nQuestion) {
					case 1:
						System.out.println("\n Desafio: " + nQuestion);
						qResponses.clear();

						principal.stories(14, true);

						qResponses.add("Incrementar x de 1 em 1."); // resposta correta
						qResponses.add("Tirar a estrutura de decisão.");
						qResponses.add("Colocar mais um laço de repetição.");
						qResponses.add("Utilizar o laço FOR.");
						qResponses.add("Nenhuma dessas opções.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nPrecisamos de laço de repetição que leia apenas dois input x e y, e vá de x até y, e lance diariamente uma média da distância de um valor ao outro, sendo eles números pares.");
						System.out.println("Entretanto, ao rodar o seguinte código: \n\nwhile (x <= y) { \n    if (x%2 == 0) \n       System.out.println((x + y) / x);  \n} ");
						System.out.println("\n O código fica em um loop infinito. Logicamente pensando, o que deve ser feito para resolver este problema?\n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Incrementar x de 1 em 1."))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;

					case 2:
						System.out.println("\n Desafio: " + nQuestion);
						qResponses.clear();
							
						qResponses.add("do while"); // resposta correta
						qResponses.add("while");
						qResponses.add("if");
						qResponses.add("foreach");
						qResponses.add("O que melhor se adequar a sua regra de negócio.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nFoi decidido que uma execução de código deve entrar em um laço de repetição.");
						System.out.println("Entretanto, precisamos executar o código dentro deste laço pela primeira vez, posteriormente validar se o código deverá ficar em loop.");
						System.out.println("\n Logicamente pensando nos modelos de execuçõs dos laços, qual o melhor laço a se utilizar nesta situação? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("do while"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 3:	
						System.out.println("\n Desafio: " + nQuestion);
						qResponses.clear();

						qResponses.add("do while"); // resposta correta
						qResponses.add("while");
						qResponses.add("if");
						qResponses.add("foreach");
						qResponses.add("O que melhor se adequar a sua regra de negócio.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nFoi decidido que uma execução de código deve entrar em um laço de repetição.");
						System.out.println("Entretanto, precisamos executar o código dentro deste laço pela primeira vez, posteriormente validar se o código deverá ficar em loop.");
						System.out.println("\n Logicamente pensando nos modelos de execuçõs dos laços, qual o melhor laço a se utilizar nesta situação? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("do while"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 4:			
						System.out.println("\n Desafio: " + nQuestion);	
						qResponses.clear();
								
						qResponses.add("do while"); // resposta correta
						qResponses.add("while");
						qResponses.add("if");
						qResponses.add("foreach");
						qResponses.add("O que melhor se adequar a sua regra de negócio.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nFoi decidido que uma execução de código deve entrar em um laço de repetição.");
						System.out.println("Entretanto, precisamos executar o código dentro deste laço pela primeira vez, posteriormente validar se o código deverá ficar em loop.");
						System.out.println("\n Logicamente pensando nos modelos de execuçõs dos laços, qual o melhor laço a se utilizar nesta situação? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("do while"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 5:	
						System.out.println("\n Desafio: " + nQuestion);		
						qResponses.clear();
								
						qResponses.add("do while"); // resposta correta
						qResponses.add("while");
						qResponses.add("if");
						qResponses.add("foreach");
						qResponses.add("O que melhor se adequar a sua regra de negócio.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nFoi decidido que uma execução de código deve entrar em um laço de repetição.");
						System.out.println("Entretanto, precisamos executar o código dentro deste laço pela primeira vez, posteriormente validar se o código deverá ficar em loop.");
						System.out.println("\n Logicamente pensando nos modelos de execuçõs dos laços, qual o melhor laço a se utilizar nesta situação? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("do while"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 6:	
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("do while"); // resposta correta
						qResponses.add("while");
						qResponses.add("if");
						qResponses.add("foreach");
						qResponses.add("O que melhor se adequar a sua regra de negócio.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nFoi decidido que uma execução de código deve entrar em um laço de repetição.");
						System.out.println("Entretanto, precisamos executar o código dentro deste laço pela primeira vez, posteriormente validar se o código deverá ficar em loop.");
						System.out.println("\n Logicamente pensando nos modelos de execuçõs dos laços, qual o melhor laço a se utilizar nesta situação? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("do while"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 7:
						System.out.println("\n Desafio: " + nQuestion);
						qResponses.clear();
								
						qResponses.add("do while"); // resposta correta
						qResponses.add("while");
						qResponses.add("if");
						qResponses.add("foreach");
						qResponses.add("O que melhor se adequar a sua regra de negócio.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nFoi decidido que uma execução de código deve entrar em um laço de repetição.");
						System.out.println("Entretanto, precisamos executar o código dentro deste laço pela primeira vez, posteriormente validar se o código deverá ficar em loop.");
						System.out.println("\n Logicamente pensando nos modelos de execuçõs dos laços, qual o melhor laço a se utilizar nesta situação? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("do while"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
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
	 * @param nQuestion : número da questão atual
	 * @param noPush : Verifica se faz push da função de história negativa
	 * @param extraLife : Vidas extras do player
	 */
	public void hard(int nQuestion, boolean noPush, int extraLife) {
		// Limpando a tela
		principal.clearScreen();
		try {
			String alternativa;	
			int index = 0;

			if (noPush) {
				principal.stories(8, false);
				System.out.println("GAME OVER!");
				principal.exit();
			} else {
				System.out.println("Você tem "+ extraLife + " restante(s)");
				// Saída histórias positivas
				switch (nQuestion) {
					case 1:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("Quando se sabe o ponto inicial e final, sabendo quantas vezes o c�digo vai ser rodado."); // resposta correta
						qResponses.add("Para executar o c�digo ao m�nimo uma vez.");
						qResponses.add("Para verificar a condi��o antes de executar o c�digo.");
						qResponses.add("Quando � necess�rio executar outra estrutura de repeti��o dentro da estrutura.");
						qResponses.add("Quando � preciso tratar apenas de uma vari�vel.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n As estruturas de repeti��o muitas vezes podem ser trocadas por outras sem afetar o funcionamento do programa, ");
						System.out.println("tamb�m cabe ao programador ou a equipe decidir entre essas estruturas devido ao seu funcionamento ser muito parecido, ");
						System.out.println("em quais momentos � melhor utilizar a estrutura for ao inv�s de outras? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Quando se sabe o ponto inicial e final, sabendo quantas vezes o c�digo vai ser rodado."))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;

					case 2:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("Fechar a estrutura de repeti��o"); // resposta correta
						qResponses.add("Sair de uma condicional (Ex: if)");
						qResponses.add("Cancelar o funcionamento em apenas um momento, continuando logo ap�s");
						qResponses.add("Cancelar toda a opera��o, at� aquela que j� foi feita anteriormente");
						qResponses.add("A fun��o aparece somente na presen�a da estrutura Switch");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Qual o funcionamento da fun��o break dentro dos la�os de repeti��o? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Fechar a estrutura de repeti��o"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 3:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("As fun��es static executam uma fun��o sem depend�ncia do conte�do de um objeto"); // resposta correta
						qResponses.add("Fun��es static s�o apenas usadas para retornar valores");
						qResponses.add("Apenas o main � uma fun��o static");
						qResponses.add("Fun��es static executam uma fun��o apenas na depend�ncia do conte�do de um objeto");
						qResponses.add("As duas fun��es apresentam as mesmas caracter�sticas");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Qual a diferen�a entre fun��es static e n�o static? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("As fun��es static executam uma fun��o sem depend�ncia do conte�do de um objeto"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 4:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("Para aproveitar o mesmo m�todo novamente no c�digo"); // resposta correta
						qResponses.add("Pois alguns procedimentos funcionam apenas se colocados sozinhos em fun��es");
						qResponses.add("Pois a fun��o main funciona apenas utilizando outras fun��es");
						qResponses.add("Para deixar o c�digo mais limpo para o usu�rio");
						qResponses.add("Fun��es al�m do main s�o exclusivas do Java, pois o programa exige que tudo esteja separado");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Por qual motivo se torna v�lido utilizar fun��es em c�digos \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Para aproveitar o mesmo m�todo novamente no c�digo"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 5:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("Fun��es static s�o chamadas utilizando o nome da classe e o nome da fun��o"); // resposta correta
						qResponses.add("N�o existe diferen�a");
						qResponses.add("Fun��es n�o static s�o chamadas utilizando o nome da classe e o nome da fun��o");
						qResponses.add("N�o existe diferen�a entre elas, apenas onde s�o chamadas");
						qResponses.add("Fun��es static s�o chamadas utilizando somente o nome da fun��o");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Qual a diferen�a em chamar uma fun��o static e uma fun��o n�o static? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Fun��es static s�o chamadas utilizando o nome da classe e o nome da fun��o"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 6:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("Para sinalizar que pode ser chamado em qualquer lugar"); // resposta correta
						qResponses.add("Para sinalizar que pode ser chamado em qualquer lugar dentro das classes que usam a principal como base");
						qResponses.add("Para sinalizar a fun��o main");
						qResponses.add("A palavra n�o possui nenhuma fun��o");
						qResponses.add("Apenas classes do mesmo pacote podem chamar o m�todo");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Qual a fun��o da palavra public antes do m�todo? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Para sinalizar que pode ser chamado em qualquer lugar"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 7:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("10"); // resposta correta
						qResponses.add("9");
						qResponses.add("12");
						qResponses.add("11");
						qResponses.add("13");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Qual o valor de a ao final do c�digo");
						System.out.println("int a = 0, b = 0, c;");
						System.out.println("\n while (b<=3) { \n 	for (c = b; c <=3; c++) { \n a = a + 1 } \n b++ } \n System.out.println(a); \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("10"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway

						break;
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

	/**
	 * Chamada para questão bônus
	 * 
	 * Descrição dos parâmetros:
	 * @param nQuestion
	*/
	public void bonusQuestion(int nQuestion) {
		String alternativa;	

		principal.clearScreen();

		System.out.println("\n Você recebeu uma pergunta bônus, acertando irá ganhar mais uma vida extra e pulará a pergunta anterior.");
		
		qResponses.clear();
		qResponses.add("public static void main(String[] args)"); // resposta correta
		qResponses.add("public static void principal(String[] args)");
		qResponses.add("public private void main([] args)");
		qResponses.add("public static void java(int] argumentos)");
		qResponses.add("private noStatic main(String[])");

		System.out.println("\n Como é contruído a função 'main' do JAVA? ");
		System.out.println(qQuestions[0] + ") " + qResponses.get(0)); // resposta correta
		System.out.println(qQuestions[1] + ") " + qResponses.get(1));
		System.out.println(qQuestions[2] + ") " + qResponses.get(2));
		System.out.println(qQuestions[3] + ") " + qResponses.get(3));
		System.out.println(qQuestions[4] + ") " + qResponses.get(4));
		System.out.print("Escolha uma alternativa: ");

		alternativa = input.next();	

		if (alternativa.toLowerCase().equals(qQuestions[0]))
			principal.gateway(alternativa, 1, qQuestions[0], level, nQuestion, true); // Chamando o gateway
		else 
			principal.gateway(alternativa, 0, qQuestions[0], level, nQuestion, true); // Chamando o gateway
		
	}

	/**
	 * Retorna histórias referentes aos cápitulos
	 * 
	 * Descrição do(s) parâmetro(s):
	 * @param cap : capítulo que o jogo se encontra
	 * @param cResponse : define se a resposta é correta ou não
	 * @param eLife : vidas extras restantes
	 */
	public void stories(int cap, boolean cResponse) throws Exception{
		switch (cap) {
			case 0:
				principal.coteTime(100);
				System.out.println("\t Mãe Natureza: Parece que você está sem vidas extras, não se preocupe, acerte esse desafio bônus e ganhe novas vidas extras. \n");
				principal.coteTime(5000);
				break;
			case 1:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t Mãe Natureza: O que foi isso? Uma questão? Verdade elas podem meio que aparecer do nada. Vejo que acertou, só para explicar, quando você acerta ou erra gera consequências, quando acerta a máquina me ajuda a me recuperar, porém se erra ela começa a destruir aquilo que me ajuda a manter minha energia. \n");
				else 
					System.out.println("\t Mãe Natureza: O que foi isso? Uma questão? Verdade elas podem meio que aparecer do nada. Vejo que errou, só para explicar, quando você erra ou acerta gera consequências, quando acerta a máquina me ajuda a me recuperar, porém se erra ela começa a destruir aquilo que me ajuda a manter minha energia. \n");
					principal.coteTime(100);
					System.out.println("\t Mãe Natureza: Eu sei o que deve estar pensando, tipo meu deus por que a máquina que era para ensinar está ajudando ou destruindo as coisas? A explicação para isso é a modificação que eu fiz nela. Eu coloquei apenas a parte boa claro, porém a própria máquina fez a parte contraria também. \n");

				principal.coteTime(5000);
				break;
			case 2:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t Mãe Natureza: Vejo que fiz a escolha certa de pedir a sua ajuda, pode ser apenas um pouco, mas já vejo que minha energia está se recuperando. Veja você plantou arvores por toda essa região, muito obrigada. \n");
				else 
					System.out.println("\t Mãe Natureza: A máquina começou a desmatar toda a região, estou me sentindo fraca, mas não se preocupe, conforme você acertar as questões eu serei capaz de me recuperar. \n");

				principal.coteTime(5000);
				break;
			case 3:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t Mãe Natureza: Já consigo sentir a minha força aumentando novamente, muito obrigada, mas ainda tem um longo caminho para percorrer, dessa vez a máquina despoluiu a nascente desse rio. \n");
				else 
					System.out.println("\t Mãe Natureza: Minha força está diminuindo, não se preocupe sei que você consegue acertar na próxima, veja, a máquina acabou de poluir ainda mais a nascente do rio. \n");
				break;
			case 4:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t Mãe Natureza: Certo, acho que iremos conseguir recuperar minha força, apenas tente manter acertando as questões, a máquina começou a despoluir o ar, retirando todo o excesso de gás carbônico. \n");
				else 
					System.out.println("\t Mãe Natureza: Cof.Cof. A máquina está soltando gases tóxicos, se continuar assim a situação pode ser irreversível. \n");
				
				principal.coteTime(5000);
				break;
			case 5:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t Mãe Natureza: A máquina está retirando a toxicidade do solo, isto é incrível!! Muito obrigada, se continuar assim tenho certeza de que vai conseguir. \n");
				else 
					System.out.println("\t Mãe Natureza: A máquina começou a liberar resíduos tóxicos no solo, isto é bem ruim, continue com foco para conseguirmos reverter essa situação. \n");

				principal.coteTime(5000);
				break;
			case 6:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t Mãe Natureza: Vejo que a flora está conseguindo se estabelecer com a ajuda da máquina, se continuar dessa maneira logo a fauna conseguirá se estabelecer também. \n");
				else 
					System.out.println("\t Mãe Natureza: A situação não poderia ser pior, a máquina está derramando óleo no rio, assim as espécies que conseguiram sobreviver até agora irão morrer asfixiadas. \n");

				principal.coteTime(5000);
				break;
			case 7:
				principal.coteTime(100);
				if (cResponse)
					System.out.println("\t Mãe Natureza: Consigo sentir, minha energia e força estão recuperados, muito obrigado por sua ajuda, eu queria ter alguma forma de retribuir, porém por agora eu preciso cuidar de todo esse planeta, espero que nos encontremos outra vez, e não se preocupe eu irei te retirar dessa máquina. \n");
				else
					System.out.println("\t Mãe natureza: Estou sentindo minha energia esvaindo, o oxigênio está deteriorado, os seres deste planeta irão morrer! :(");
				
				principal.coteTime(5000);
				break;
			case 8:
				principal.coteTime(100);
				System.out.println("\t Mãe Natureza: Acho que agora se tornou irreversível, pobres animais não perceberam que estavam apenas se autodestruindo, muito obrigado por tentar me ajudar, mas agora é um adeus. Queria ter passado mais tempo ao seu lado. \n");
				principal.coteTime(5000);
				break;
			case 9:
				principal.coteTime(100);
				System.out.println("\t Mãe Natureza: Ainda temos alguma chance sobrando, foque e eu tenho certeza de que você irá conseguir \n");
				principal.coteTime(3000);
				break;
			case 10: 
				System.out.println("\t Mãe Natureza: " + name + " você é um herói, graças a você todos os seres deste planeta poderão viver... Parabéns, você é a pessoa mais inteligente que já vi.");
				System.out.println("\t Mãe Natureza: Ahhh... Mil perdões esqueci de te tirar deste ambiente virtual... Até a próxima herói");
				principal.coteTime(8000);
				principal.clearScreen();
				
				System.out.println("\t                           8888888888 8888888 888b     d888 ");
				principal.coteTime(800);
				System.out.println("\t                           888          888   8888b   d8888 ");
				principal.coteTime(700);
				System.out.println("\t                           888          888   88888b.d88888 ");
				principal.coteTime(600);
				System.out.println("\t                           8888888      888   888Y88888P888 ");
				principal.coteTime(500);
				System.out.println("\t                           888          888   888 Y888P 888 ");
				principal.coteTime(400);
				System.out.println("\t                           888          888   888  Y8P  888 ");
				principal.coteTime(200);
				System.out.println("\t                           888          888   888   '   888 ");
				principal.coteTime(100);
				System.out.println("\t                           888        8888888 888       888 ");
				principal.coteTime(5000);
				principal.credits();
				principal.exit();
				break;
			case 11:
				System.out.println("\t Mãe Natureza: Oi, finalmente acordou. Estive esperando por isso por muito tempo, eu preciso da sua ajuda. Eu sei que você deve estar cheio de dúvidas, porém não temos tempo para isso. Prazer eu sou o que vocês humanos chamam de mãe natureza, e você? \n");
				break;
			case 12:
				System.out.println("\n");
				System.out.println("\t Mãe Natureza: Me desculpe não me apresentar formalmente, porém atualmente da maneira que estou eu posso apenas me manifestar como uma voz em sua cabeça. Enfim chega de demora, preciso da sua ajuda para restaurar a minha forma original, se não conseguir eu irei morrer, e comigo todo o planeta \n");
				principal.coteTime(100);
				System.out.println("\t Mãe Natureza: Será que está funcionando? Oi? Ufa acho que funcionou, só para ter certeza, consegue me dizer o seu nome? \n");
				principal.coteTime(100);
				System.out.println("\t " + name + ": Meu nome é " + name + " \n");
				principal.coteTime(1000);
				System.out.println("\t Mãe Natureza: Certo acho que está funcionando, bom antes de me introduzir para você eu deveria explicar a situação, eu transferi a sua consciência para uma máquina que encontrei em um local onde ensinavam pessoas como programar e tudo mais, então provavelmente para manter o funcionamento aparecerão questões que você precisa acertar ok? \n");
				break;
			case 13:
				System.out.println("\t Mãe Natureza: Primeiro, precisa dizer a máquina o nível que você está em programação, se eu não me engano é de 1 a 3, tipo um fácil, médio e difícil, sabe? \n");
				break;
			case 14:
				System.out.println("\t Mãe Natureza: Certo, agora é hora de me apresentar, sou a Natureza, eu estava acostumada a estar em toda parte, porém agora estou sumindo cada vez mais e preciso da sua ajuda para recuperar minha energia, os humanos estão sumindo, assim como você estava antes de eu conseguir transferir sua consciência para essa máquina\n");
				break;
		}
	}

	/**
	 * Retorna instruções para o jogo
	 * 
	 * Descrição do(s) parâmetro(s):
	 */
	public void instructions() throws Exception {
		System.out.println("\t \n                                    Instruções - AWTreech \n");
		System.out.println("\t - O jogo só começa quando digitar o comando '1';");
		System.out.println("\t - Você poderá sair a qualquer momento, digitando 'sair';");
		System.out.println("\t - Assim que escolher a dificuldade do jogo, você terá vidas extras, se adaptando com dificuldade:");
		System.out.println("\t      * Nível Fácil - Você terá 3 vidas extras");
		System.out.println("\t      * Nível Médio - Você terá 2 vidas extras");
		System.out.println("\t      * Nível Difícil - Você terá 1 vidas extras");

		System.out.println("1 - Jogar");
		System.out.println("2 - Créditos");
		System.out.println("3 - Instrução");
		System.out.println("4 - Sair");
		System.out.print("\nDigite algum comando: ");

		int value = input.nextInt();
		principal.menu(value);
	}

	/**
	 * Retorna menu do do jogo
	 * 
	 * Descrição do(s) parâmetro(s):
	 * @param option : opção escolhida pelo usuário
	 */
	public void menu(int option) throws Exception{
		try {
			switch (option) {
				case 1: // Jogo
					principal.clearScreen(); // Limpando a tela
					// Dando introdução ao jogo
					principal.stories(11, true);
					// Armazenando nome em variável global
					System.out.print("Digite o nome do seu personagem: ");
					name = input.next();
					principal.stories(12, true);
					// Definindo level do jogo
					principal.stories(13, true);
					System.out.print("Digite, de 1 a 3 qual seu nível em programação?");
					level = input.nextInt();
					principal.level(level);
					break;
				case 2: // Créditos
					principal.credits();
					break;
				case 3: // Instruções
					principal.instructions();
				case 4: // Sair
					principal.exit();
					break;
			
				default: // Caso seja passado uma opção invalída, retorna para o menu
					System.out.println("\nComando inválido. Tente novamente.");
					System.out.println("\n \tAWTreech\n");
					System.out.println("1 - Jogar");
					System.out.println("2 - Créditos");
					System.out.println("3 - Instrução");
					System.out.println("4 - Sair");
					System.out.print("\nDigite algum comando: ");
					
					int value = input.nextInt();
					principal.menu(value);
					break;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	
	/**
	 * Retorna créditos do jogo
	 * 
	 * Descrição do(s) parâmetro(s):
	 */
	public void credits() throws Exception {
		System.out.println("\n \tCriadores do jogo: \n");
		System.out.println("\tDanilo Almeida dos Santos");
		System.out.println("\tFernando Martiniano");
		System.out.println("\tGuilherme Monteiro \n");

		System.out.println("\n \tAWTreech\n");
		System.out.println("1 - Jogar");
		System.out.println("2 - Créditos");
		System.out.println("3 - Instrução");
		System.out.println("4 - Sair");
		System.out.print("\nDigite algum comando: ");

		int value = input.nextInt();
		principal.menu(value);
	}

	/**
	 * Retorna tempo de espera em milisegundos para a próxima thread
	 * 
	 *  Descrição do(s) parâmetro(s):
	 * @param milSegs : Define o tempo de espera
	 */
	private void coteTime(int milSegs) throws InterruptedException {
		Thread.sleep(milSegs);
	}

	/**
	 * Classe principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("\tAWTreech\n");
			System.out.println("1 - Jogar");
			System.out.println("2 - Créditos");
			System.out.println("3 - Instrução");
			System.out.println("4 - Sair");
			System.out.print("\nDigite algum comando: ");

			int value = input.nextInt();
			principal.menu(value);

		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
