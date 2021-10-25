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
				case 1 -> { // Fácil
					System.out.println("Nivel fácil selecionado, Voce tem 3 vidas extras");
					principal.easy(1, this.push, 3);
				}
				case 2 -> { // Médio
					System.out.println("Nivel médio selecionado, Voce tem 2 vidas extras");
					principal.medium(1, this.push, 2);
				}
				case 3 -> { // Difícil
					System.out.println("Nivel difícil selecionado, Voce tem 2 vidas extras");
					principal.hard(1, this.push, 1);
				}
				default -> {
					System.out.println("Nivel Invalido");
					principal.level(3);
				}
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
					principal.stories(10);
				} else {
					nQuestion++;
					switch (level) {
						case 1: // Histórias e desafios - Fácil
							System.out.println("Colocar fácil aqui");
							break;

						case 2: // Histórias e desafios - Médio
							System.out.println("Resposta correta");
							principal.medium(nQuestion, this.push, eLife);
							break;
							
						case 3: // Histórias e desafios - Difícil
							System.out.println("Colocar dificil aqui");
							break;
					}
				}
			} else {
				System.out.println("Resposta incorreta");
				
				if (!bonusQ) eLife--;

				if (!bonus && eLife == 0) { // Verifica se já foi realizado push e se qtde de vidas extras é igual a 0
					bonus = true;
					principal.bonusQuestion(nQuestion);
				}

				switch (level) {
					case 1: // Histórias e desafios - Fácil
						if (eLife < 0) principal.easy(nQuestion, this.push = true, eLife);
						// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente
						else principal.easy(nQuestion, this.push, eLife); 
						break;

					case 2: // Histórias e desafios - Médio
						if (eLife < 0) principal.medium(nQuestion, this.push = true, eLife);
						// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente
						else principal.medium(nQuestion, this.push, eLife); 
						break;
						
					case 3: // Histórias e desafios - Difícil
						if (eLife < 0) principal.hard(nQuestion, this.push = true, eLife);
						// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente
						else principal.hard(nQuestion, this.push, eLife); 
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
				System.out.println("GAME OVER!");
				principal.exit();
			} else {
				System.out.println("Você tem "+ extraLife + " restante(s)");
				
				switch (nQuestion) {
					case 1:
						System.out.println("\n Desafio: " + nQuestion);
						qResponses.clear();
						
						// Chamar push da história positiva
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
							
						// Chamar push da história positiva
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
								
						// Chamar push da história positiva
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
								
						// Chamar push da história positiva
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
								
						// Chamar push da história positiva
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
								
						// Chamar push da história positiva
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
								
						// Chamar push da história positiva
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
	 */
	public void stories(int cap) {
		switch (cap) {
			case 10: // 10 = Último capítulo
				principal.exit();
				break;
		
			default:
				break;
		}
	}

	/**
	 * Retorna instruções para o jogo
	 * 
	 * Descrição do(s) parâmetro(s):
	 */
	public void instructions() {
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
	public void menu(int option) {
		switch (option) {
			case 1: // Jogo
				principal.clearScreen(); // Limpando a tela
				System.out.println("Colocar história aqui, qual seu nome?");
				name = input.next();
				System.out.println("Diga, de 1 a 3 qual seu nível em programação?");
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
	}

	
	/**
	 * Retorna créditos do jogo
	 * 
	 * Descrição do(s) parâmetro(s):
	 */
	public void credits(){
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
