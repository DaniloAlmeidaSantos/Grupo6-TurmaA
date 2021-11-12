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
	private boolean push = false; // Push : True = Encerra o jogo, False = NÃ£o encerra o jogo
	private final String[] qQuestions = {"a", "b", "c", "d", "e"}; // Alternativas disponÃ­veis no jogo
	private ArrayList<String> qResponses = new ArrayList<>();
	private boolean bonus = false; // Verifica se foi realizado push para bonusQuestion
	private String name; //name do jogador
	private int level; // easy = 1 , medium = 2 , hard = 3

	/**
	 * @author Danilo Almeida
	 * @author Guilherme Monteiro
	 * 
	 * Gateway para sair do jogo
	 */
	private void exit() {
		try {
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
		
	}

	/**
	 * @author Danilo Almeida
	 * @author Guilherme Monteiro
	 * 
	 * Limpa todos caracteres na tela
	 */
	private void clearScreen(){
		try {
			char esc = 27;
			String clear = esc + "[2J"; // CÃ³digo ansi para limpar a tela
			System.out.println(clear);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}	
	}

	/**
	 * @author Guilherme Monteiro
     * Foi determinado nos cases de 1 a 3 o total de vidas extras
     * 
     * DescriÃ§Ã£o dos parÃ¢metros:
     * @param selectLevel : definir nÃ­vel escolhido pelo usuÃ¡rio
	 * @return
     */
    public boolean level(int selectLevel) {
		try {
			switch (selectLevel) {
				case 1 :  // FÃ¡cil
					System.out.println("Nivel fÃ¡cil selecionado, Voce tem 3 vidas extras");
					principal.easy(1, this.push, 3);
					return true;
				case 2: // MÃ©dio
					System.out.println("Nivel mÃ©dio selecionado, Voce tem 2 vidas extras");
					principal.medium(1, this.push, 2);
					return true;
				case 3: // DifÃ­cil
					System.out.println("Nivel difÃ­cil selecionado, Voce tem 1 vidas extras");
					principal.hard(1, this.push, 1);
					return true;
				default:
					System.out.println("Nivel Invalido");
					principal.coteTime(3000);
					principal.menu(5); // Caso o nivel digitado nÃ£o esteja condicionando, Ã© redirecionado para o menu
					return false;
			}
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return false;
		}
    }

	/**
	 * @author Danilo Almeida
	 * @author Guilherme Monteiro
	 * Gateway para respostas corretas e incorretas
	 * 
	 * DescriÃ§Ã£o parÃ¢metros:
	 * @param data : resposta escolhida pelo usuÃ¡rio
	 * @param eLife : Vidas extras do player
	 * @param cQuestion : Resposta correta para validaÃ§Ã£o
	 * @param level : NÃ­vel do jogo
	 * @param nQuestion : NÃºmero da questÃ£o
	 * @param bonusQ : Verrifica se tem questÃ£o bÃ´nus pendente
	 * @return
	 */
	public boolean gateway(String data, int eLife,  String cQuestion, int level, int nQuestion, boolean bonusQ) {
		try {
			// Caso o jogador decida sair do jogo
			if (data.equalsIgnoreCase("sair")) principal.exit();

			if (data.toLowerCase().equals(cQuestion) && nQuestion <= 7) {
				if (nQuestion == 7){ // Se o jogador acertou a questÃ£o ele Ã© redirecionado para o final da histÃ³ria
					principal.clearScreen();
					principal.stories(nQuestion, true);
					principal.stories(10, true);
					return true;
				} else {
					nQuestion++;
					switch (level) {
						case 1: // HistÃ³rias e desafios - FÃ¡cil
							System.out.println("\n Resposta correta \n");
							principal.stories(nQuestion, true);
							principal.coteTime(3000);
							principal.easy(nQuestion, this.push, eLife);
							return true;

						case 2: // HistÃ³rias e desafios - MÃ©dio
							System.out.println("\n Resposta correta \n");
							principal.stories(nQuestion, true);
							principal.coteTime(3000);
							principal.medium(nQuestion, this.push, eLife);
							return true;
							
						case 3: // HistÃ³rias e desafios - DifÃ­cil
							System.out.println("\n Resposta correta \n");
							principal.stories(nQuestion, true);
							principal.coteTime(3000);
							principal.hard(nQuestion, this.push, eLife);
							return true;
						
						default:
							System.out.println("Erro: level inexistente");
							return false;
					}
				}
			} else {
				System.out.println("\n Resposta incorreta \n");

				principal.stories(nQuestion, false);
				
				if (!bonusQ) eLife--;

				if (!bonus && eLife == 0) { // Verifica se jÃ¡ foi realizado push e se qtde de vidas extras Ã© igual a 0
					principal.stories(0, false);
					bonus = true;
					principal.bonusQuestion(nQuestion);
					return true;
				}

				switch (level) {
					case 1: // HistÃ³rias e desafios - FÃ¡cil
						if (eLife < 0) {
							principal.easy(nQuestion, this.push = true, eLife);
							return false;
						} else {
							principal.stories(9, false);
							// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente 
							principal.easy(nQuestion, this.push, eLife); 
							return true;
						}

					case 2: // HistÃ³rias e desafios - MÃ©dio
						if (eLife < 0) {
							principal.medium(nQuestion, this.push = true, eLife);
							return false;
						} else {
							principal.stories(9, false);
							// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente
							principal.medium(nQuestion, this.push, eLife); 
							return true;
						}
						
					case 3: // HistÃ³rias e desafios - DifÃ­cil
						if (eLife < 0) {
							principal.hard(nQuestion, this.push = true, eLife);
							return false;
						} else {
							principal.stories(9, false);
							// Caso o jogador erre, mas tenha mais vidas extras ele pode responder novamente 
							principal.hard(nQuestion, this.push, eLife); 
							return true;
						}
					
					default:
						return false;
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
			return false;
		}
	}

	/**
	 * @author Guilherme Monteiro
	 * QuestÃµes nÃ­vel MÃ©dio
	 * 
	 *  DescriÃ§Ã£o parÃ¢metros:
	 * @param nQuestion : nÃºmero da questÃ£o atual
	 * @param noPush : Verifica se faz push da funÃ§Ã£o de histÃ³ria negativa
	 * @param extraLife : Vidas extras do player
	 */
	public void easy(int nQuestion, boolean noPush, int extraLife) throws Exception {
		
		// Limpando a tela
		principal.clearScreen();
		String alternativa;	

		if (noPush) {
			principal.stories(8, false);
			System.out.println("GAME OVER!");
			principal.exit();
		} else {
			System.out.println("VocÃª tem "+ extraLife + " vidas extra(s) restante(s)");
			// SaÃ­da histÃ³rias positivas
			switch (nQuestion) {
				case 1:
					System.out.println("\n Desafio: " + nQuestion);
					// Chamar push da histÃ³ria positiva
					qResponses.add("101010");// resposta correta
					qResponses.add("101011");
					qResponses.add("111010");
					qResponses.add("101110");
					qResponses.add("101111");

					System.out.println("O valor em binÃ¡rio do decimal 42 Ã©:");
					System.out.println(qQuestions[0] + ") " + qResponses.get(0)); // resposta correta
					System.out.println(qQuestions[1] + ") " + qResponses.get(1));
					System.out.println(qQuestions[2] + ") " + qResponses.get(2));
					System.out.println(qQuestions[3] + ") " + qResponses.get(3));
					System.out.println(qQuestions[4] + ") " + qResponses.get(4));
					System.out.print("Escolha uma alternativa: ");

					alternativa = input.next();	
					principal.gateway(alternativa, extraLife, qQuestions[0], level , nQuestion, false); // Chamando o gateway
					break;

				case 2:		
					System.out.println("\n Desafio: " + nQuestion);
					qResponses.add("A variavel (int)");
					qResponses.add("A variavel (float)");
					qResponses.add("A variavel (double)");
					qResponses.add("A variavel (String)");// resposta correta
					qResponses.add("A variavel (boolean)");
	
					System.out.println("Qual a variÃ¡vel utilizada para representar caracteres de nomes: ");
					System.out.println(qQuestions[0] + ") " + qResponses.get(0));
					System.out.println(qQuestions[1] + ") " + qResponses.get(1));
					System.out.println(qQuestions[2] + ") " + qResponses.get(2));
					System.out.println(qQuestions[3] + ") " + qResponses.get(3));// resposta correta
					System.out.println(qQuestions[4] + ") " + qResponses.get(4));
					System.out.print("Escolha uma alternativa: ");

					alternativa = input.next();
					principal.gateway(alternativa, extraLife, qQuestions[3], level, nQuestion, false);
					break;

				case 3:	
					System.out.println("\n Desafio: " + nQuestion);	
					qResponses.add("A variavel (int)");// resposta correta
					qResponses.add("A variavel (float)");
					qResponses.add("A variavel (double)");
					qResponses.add("A variavel (String)");
					qResponses.add("A variavel (boolean)");

					System.out.println("Qual a variÃ¡vel utilizada para representar numeros inteiros: ");
					System.out.println(qQuestions[0] + ") " + qResponses.get(0));// resposta correta
					System.out.println(qQuestions[1] + ") " + qResponses.get(1));
					System.out.println(qQuestions[2] + ") " + qResponses.get(2));
					System.out.println(qQuestions[3] + ") " + qResponses.get(3));
					System.out.println(qQuestions[4] + ") " + qResponses.get(4));
					System.out.print("Escolha uma alternativa: ");	

					alternativa = input.next();
					principal.gateway(alternativa, extraLife, qQuestions[0], level, nQuestion, false);
					break;
				case 4:
					System.out.println("\n Desafio: " + nQuestion);
					qResponses.add("O simbolo (>)");
					qResponses.add("O simbolo (<)");
					qResponses.add("O simbolo (>=)");// resposta correta
					qResponses.add("O simbolo (<=)");
					qResponses.add("O simbolo (<>)");

					System.out.println("Qual simbolo e representeado por maior igual na programaÃ§Ã£o: ");
					System.out.println(qQuestions[0] + ") " + qResponses.get(0));
					System.out.println(qQuestions[1] + ") " + qResponses.get(1));
					System.out.println(qQuestions[2] + ") " + qResponses.get(2));// resposta correta
					System.out.println(qQuestions[3] + ") " + qResponses.get(3));
					System.out.println(qQuestions[4] + ") " + qResponses.get(4));
					System.out.print("Escolha uma alternativa: ");

					alternativa = input.next();
					principal.gateway(alternativa, extraLife, qQuestions[2], level, nQuestion, false);
					
					break;
				case 5:
					System.out.println("\n Desafio: " + nQuestion);
					qResponses.add("switch/case");
					qResponses.add("for");// resposta correta
					qResponses.add("do/while");
					qResponses.add("break");
					qResponses.add("string");

					System.out.println("Qual a variÃ¡vel utilizada em uma estrutura de repetiÃ§Ã£o quando se sabe o \n numero de repetiÃ§Ãµes em que deve ser executada: ");
					System.out.println(qQuestions[0] + ") " + qResponses.get(0));
					System.out.println(qQuestions[1] + ") " + qResponses.get(1));// resposta correta
					System.out.println(qQuestions[2] + ") " + qResponses.get(2));
					System.out.println(qQuestions[3] + ") " + qResponses.get(3));
					System.out.println(qQuestions[4] + ") " + qResponses.get(4));
					System.out.print("Escolha uma alternativa: ");

					alternativa = input.next();
					principal.gateway(alternativa, extraLife, qQuestions[1], level, nQuestion, false);
					
					break;
				case 6:		
					System.out.println("\n Desafio: " + nQuestion);
					qResponses.add("A variavel (while)");
					qResponses.add("A variavel (for)");
					qResponses.add("A variavel (double)");
					qResponses.add("A variavel (String)");
					qResponses.add("A variavel (if/else)");// resposta correta

					System.out.println("Qual a variavel utilizada para determinar se uma condicao Ã© verdadeira ou falsa: ");
					System.out.println(qQuestions[0] + ") " + qResponses.get(0));
					System.out.println(qQuestions[1] + ") " + qResponses.get(1));
					System.out.println(qQuestions[2] + ") " + qResponses.get(2));
					System.out.println(qQuestions[3] + ") " + qResponses.get(3));
					System.out.println(qQuestions[4] + ") " + qResponses.get(4));// resposta correta
					System.out.print("Escolha uma alternativa: ");
					
					alternativa = input.next();
					principal.gateway(alternativa, extraLife, qQuestions[4], level, nQuestion, false);
						
					break;
				case 7:	
					System.out.println("\n Desafio: " + nQuestion);
					qResponses.add("if(x<20)");
					qResponses.add("else(x>20)");
					qResponses.add("x Ã© maior do que 20");
					qResponses.add("x Ã© menor do que 20");// resposta correta
					qResponses.add("System.out.println(â€œx Ã© menor do que 20â€�);");

					System.out.println("No cÃ³digo apresentado abaixo, qual a saida verdadeira: " +
					"\n int x = 10;\n if(x < 20){\nSystem.out.println(â€œx Ã© menor do que 20â€�);\n }else{\nSystem.out.println(â€œx Ã© maior do que 20â€�);\n}\n}\n}");
					System.out.println(qQuestions[0] + ") " + qResponses.get(0));
					System.out.println(qQuestions[1] + ") " + qResponses.get(1));
					System.out.println(qQuestions[2] + ") " + qResponses.get(2));
					System.out.println(qQuestions[3] + ") " + qResponses.get(3));// resposta correta
					System.out.println(qQuestions[4] + ") " + qResponses.get(4));
					System.out.print("Escolha uma alternativa: ");
					
					alternativa = input.next();
					principal.gateway(alternativa, extraLife, qQuestions[3], level, nQuestion, false);
						
					break;
			}
		}
	}

	/**
	 * @author Danilo Almeida
	 * QuestÃµes nÃ­vel MÃ©dio
	 * 
	 *  DescriÃ§Ã£o parÃ¢metros:
	 * @param nQuestion : nÃºmero da questÃ£o atual
	 * @param noPush : Verifica se faz push da funÃ§Ã£o de histÃ³ria negativa
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
				System.out.println("VocÃª tem "+ extraLife + " vidas extra(s) restante(s)");
				
				switch (nQuestion) {
					case 1:
						System.out.println("\n Desafio: " + nQuestion);
						qResponses.clear();

						principal.stories(14, true);
						// Adicionando possÃ­veis respostas
						qResponses.add("Incrementar x de 1 em 1."); // resposta correta
						qResponses.add("Tirar a estrutura de decisÃ£o.");
						qResponses.add("Colocar mais um laÃ§o de repetiÃ§Ã£o.");
						qResponses.add("Utilizar o laÃ§o FOR.");
						qResponses.add("Nenhuma dessas opÃ§Ãµes.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nPrecisamos de laÃ§o de repetiÃ§Ã£o que leia apenas dois input x e y, e vÃ¡ de x atÃ© y, e lance diariamente uma mÃ©dia da distÃ¢ncia de um valor ao outro, sendo eles nÃºmeros pares.");
						System.out.println("Entretanto, ao rodar o seguinte cÃ³digo: \n\nwhile (x <= y) { \n    if (x%2 == 0) \n       System.out.println((x + y) / x);  \n} ");
						System.out.println("\n O cÃ³digo fica em um loop infinito. Logicamente pensando, o que deve ser feito para resolver este problema?\n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");
						// Verifica qual a resposta correta e seta o index da alternativa certa
						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Incrementar x de 1 em 1."))	index = i;
						}

						alternativa = input.next();	
						// Chamada do gateway para validaÃ§Ã£o de respostas
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;

					case 2:
						System.out.println("\n Desafio: " + nQuestion);
						qResponses.clear();
						// Adicionando possÃ­veis respostas	
						qResponses.add("do while"); // resposta correta
						qResponses.add("while");
						qResponses.add("if");
						qResponses.add("foreach");
						qResponses.add("O que melhor se adequar a sua regra de negÃ³cio.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nFoi decidido que uma execuÃ§Ã£o de cÃ³digo deve entrar em um laÃ§o de repetiÃ§Ã£o.");
						System.out.println("Entretanto, precisamos executar o cÃ³digo dentro deste laÃ§o pela primeira vez, posteriormente validar se o cÃ³digo deverÃ¡ ficar em loop.");
						System.out.println("\n Logicamente pensando nos modelos de execuÃ§Ãµs dos laÃ§os, qual o melhor laÃ§o a se utilizar nesta situaÃ§Ã£o? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");
						// Verifica qual a resposta correta e seta o index da alternativa certa
						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("do while"))	index = i;
						}

						alternativa = input.next();
						// Chamada do gateway para validaÃ§Ã£o de respostas	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 3:	
						System.out.println("\n Desafio: " + nQuestion);
						qResponses.clear();

						// Adicionando possÃ­veis respostas
						qResponses.add("Utilizar a funÃ§Ã£o Math.pow(base, expoente)"); // resposta correta
						qResponses.add("Utilizar o laÃ§o de repetiÃ§Ã£o multiplicando o resultado com a base x vezes definido pelo expoente");
						qResponses.add("Utilizar o if para descobrir qual Ã© o expoente");
						qResponses.add("Utilizar o funÃ§Ã£o Math.sqrt(base, expoente)");
						qResponses.add("Nenhuma dessas opÃ§Ãµes");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Dado que seja necessÃ¡rio o usuÃ¡rio digitar a base e o expoente, qual a forma mais simplificada de codificar essa necessidade?");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						// Verifica qual a resposta correta e seta o index da alternativa certa
						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Utilizar a funÃ§Ã£o Math.pow(base, expoente)"))	index = i;
						}

						alternativa = input.next();	
						// Chamada do gateway para validaÃ§Ã£o de respostas
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 4:			
						System.out.println("\n Desafio: " + nQuestion);	
						qResponses.clear();
						// Adicionando possÃ­veis respostas
						qResponses.add("Verdadeiro"); 
						qResponses.add("Falso"); // resposta correta

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Olhe para este cÃ³digo:");
						System.out.println("\n Scanner input = new Scanner(System.in); \n System.out.println('Digite um valor numÃ©rico: ');\n int n1 = input.nextDouble();");
						System.out.println("\n if (n1 % 2 == 0) \n      System.out.println(n1);");
						System.out.println("\n Este Ã© SIM funcional. \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.print("Escolha uma alternativa: ");

						// Verifica qual a resposta correta e seta o index da alternativa certa
						for (int i = 0; i < 2; i++) {
							if (qResponses.get(i).equals("Falso"))	index = i;
						}

						alternativa = input.next();	
						// Chamada do gateway para validaÃ§Ã£o de respostas
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 5:	
						System.out.println("\n Desafio: " + nQuestion);		
						qResponses.clear();
						// Adicionando possÃ­veis respostas		
						qResponses.add("Scanner input = new Scanner(System.in); \n int num =  input.nextInt();\n int count = 0; \n while (num >= count ){\n         if (num % 5 == 0){ \n              System.out.println(num);\n          }\n          count++\n }"); // resposta correta
						qResponses.add("int num =  input.nextInt();\n int count = 0; \n while (num >= count ){\n         if (num % 5 == 0){ \n              System.out.println(num);\n              count++\n          }\n }");
						qResponses.add("Scanner input = new Scanner(System.in) \n int num =  input.nextInt();\n int count = 0; \n do while (num >= count )\n         if (num % 5 == 0){ \n              System.out.println(num);\n              count++\n          }\n }");
						qResponses.add("Scanner input = new Scanner(System.in); \n int num =  input.nextInt();\n int count = 0; \n while (num >= count ){\n         for (num % 5 == 0){ \n              System.out.println(num);\n                  }\n }");
						qResponses.add("INT input = new Scanner(System.in); \n int num =  input.nextInt();\n int count = num; \n while (num >= count ){\n         if (num % 5 == 0){ \n              System.out.println(count);\n              count++\n          }\n }");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Preciso de um programa que seja capaz de ler um valor por valor, e imprimir apenas os nÃºmeros mÃºltiplos de 5.\n");
						System.out.println("Analise as alternativas, e escolha aquela que melhor se enquadra para esta soluÃ§Ã£o: \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0));
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");
						// Verifica qual a resposta correta e seta o index da alternativa certa
						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Scanner input = new Scanner(System.in); \n int num =  input.nextInt();\n int count = 0; \n while (num >= count ){\n         if (num % 5 == 0){ \n              System.out.println(num);\n          }\n          count++\n }"))	{
								index = i;
							}	
						}

						alternativa = input.next();	
						// Chamada do gateway para validaÃ§Ã£o de respostas
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 6:	
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("NÃ£o, mas a estrutura de decisÃ£o se torna inÃºtil, pois verifica um nÃºmero inicializado pelo sistema, sendo que logo apÃ³s tem leitura de um valor digitado pelo usuÃ¡rio"); // resposta correta
						qResponses.add("Sim, o if nÃ£o pode verificar um valor inteiro igual a zero.");
						qResponses.add("NÃ£o, cÃ³digo em perfeito estado.");
						qResponses.add("Sim, Scanner inicializado incorretamente.");
						qResponses.add("Sim, na linguagem JAVA nÃ£o Ã© necessÃ¡rio ponto e vÃ­rgula ao final de um comando.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Preciso da sua ajuda para melhorar / consertar este cÃ³digo: \n");
						System.out.println("\n int num = 0; \n Scanner input = new Scanner(System.in); \n if (num % 2 == 0) { \n    System.out.print(num);\n}\n System.out.print('Digite um nÃºmero'); \n num = input.nextInt();\n");
						System.out.println("\n VocÃª enxerga algum problema neste cÃ³digo? ");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");
						// Verifica qual a resposta correta e seta o index da alternativa certa
						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("NÃ£o, mas a estrutura de decisÃ£o se torna inÃºtil, pois verifica um nÃºmero inicializado pelo sistema, sendo que logo apÃ³s tem leitura de um valor digitado pelo usuÃ¡rio")){
								index = i;
							}
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;

					case 7:
						System.out.println("\n Desafio: " + nQuestion);
						qResponses.clear();
								
						qResponses.add("Ao fechar as chaves no else, estÃ¡ sendo fechado com parÃªnteses"); // resposta correta
						qResponses.add("A construÃ§Ã£o da lÃ³gica do cÃ³digo estÃ¡ incorreta");
						qResponses.add("O for nÃ£o pode ter uma uma variÃ¡vel double incializado nele");
						qResponses.add("A variÃ¡vel n estÃ¡ recebendo valores float ao invÃ©s de double");
						qResponses.add("NÃ£o hÃ¡ erros!");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\nQual o problema deste cÃ³digo? \n");
						System.out.println("\n Scanner input = new Scanner(System.in);\n System.out.print('Digite um nÃºmero natural: '); \n double n = input.nextFloat(); \n");
						System.out.println("\n double s1 = 1 / n; \n double s2 = n / 1;");
						System.out.println("\n if (n < 0) System.out.println('NÃºmero digitado nÃ£o Ã© natural'); \n  {\n       System.out.println('NÃºmero digitado nÃ£o Ã© natural');");
						System.out.println("\n } else {\n    for (double i = 2; i<n; i++) {\n      s1 = i / n - i; \n      s2 = n - i / i;   \n    } \n)\n");
						System.out.println("\n System.out.println('S = '+ (s1 + s2));\n ");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Ao fechar as chaves no else, estÃ¡ sendo fechado com parÃªnteses"))	index = i;
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
	 * @author Fernando Nascimento
	 * QuestÃµes nÃ­vel difÃ­cil
	 * 
	 *  DescriÃ§Ã£o do(s) parÃ¢metro:
	 * @param nQuestion : NÃºmero da questÃ£o atual
	 * @param noPush : Verifica se faz push da funÃ§Ã£o de histÃ³ria negativa
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
				System.out.println("VocÃª tem "+ extraLife + " vidas extra(s) restante(s)");
				// SaÃƒÂ­da histÃƒÂ³rias positivas
				switch (nQuestion) {
					case 1:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("Quando se sabe o ponto inicial e final, sabendo quantas vezes o cÃ³digo vai ser rodado."); // resposta correta
						qResponses.add("Para executar o cÃ³digo ao mÃ­nimo uma vez.");
						qResponses.add("Para verificar a condiÃ§Ã£o antes de executar o cÃ³digo.");
						qResponses.add("Quando Ã© necessÃ¡rio executar outra estrutura de repetiÃ§Ã£o dentro da estrutura.");
						qResponses.add("Quando Ã© preciso tratar apenas de uma variÃ¡vel.");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n As estruturas de repetiÃ§Ã£o muitas vezes podem ser trocadas por outras sem afetar o funcionamento do programa, ");
						System.out.println("tambÃ©m cabe ao programador ou a equipe decidir entre essas estruturas devido ao seu funcionamento ser muito parecido, ");
						System.out.println("em quais momentos Ã© melhor utilizar a estrutura for ao invÃ©s de outras? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Quando se sabe o ponto inicial e final, sabendo quantas vezes o cÃ³digo vai ser rodado."))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;

					case 2:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("Fechar a estrutura de repetiÃ§Ã£o"); // resposta correta
						qResponses.add("Sair de uma condicional (Ex: if)");
						qResponses.add("Cancelar o funcionamento em apenas um momento, continuando logo apÃ³s");
						qResponses.add("Cancelar toda a operaÃ§Ã£o, atÃ© aquela que jÃ¡ foi feita anteriormente");
						qResponses.add("A funÃ§Ã£o aparece somente na presenÃ§a da estrutura Switch");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Qual o funcionamento da funÃ§Ã£o break dentro dos laÃ§os de repetiÃ§Ã£o? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Fechar a estrutura de repetiÃ§Ã£o"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 3:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("As funÃ§Ãµes static executam uma funÃ§Ã£o sem dependÃªncia do conteÃºdo de um objeto"); // resposta correta
						qResponses.add("FunÃ§Ãµes static sÃ£o apenas usadas para retornar valores");
						qResponses.add("Apenas o main Ã© uma funÃ§Ã£o static");
						qResponses.add("FunÃ§Ãµes static executam uma funÃ§Ã£o apenas na dependÃªncia do conteÃºdo de um objeto");
						qResponses.add("As duas funÃ§Ãµes apresentam as mesmas caracterÃ­sticas");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Qual a diferenÃ§a entre funÃ§Ãµes static e nÃ£o static? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("As funÃ§Ãµes static executam uma funÃ§Ã£o sem dependÃªncia do conteÃºdo de um objeto"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 4:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("Para aproveitar o mesmo mÃ©todo novamente no cÃ³digo"); // resposta correta
						qResponses.add("Pois alguns procedimentos funcionam apenas se colocados sozinhos em funÃ§Ãµes");
						qResponses.add("Pois a funÃ§Ã£o main funciona apenas utilizando outras funÃ§Ãµes");
						qResponses.add("Para deixar o cÃ³digo mais limpo para o usuÃ¡rio");
						qResponses.add("FunÃ§Ãµes alÃ©m do main sÃ£o exclusivas do Java, pois o programa exige que tudo esteja separado");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Por qual motivo se torna vÃ¡lido utilizar funÃ§Ãµes em cÃ³digos \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("Para aproveitar o mesmo mÃ©todo novamente no cÃ³digo"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 5:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("FunÃ§Ãµes static sÃ£o chamadas utilizando o nome da classe e o nome da funÃ§Ã£o"); // resposta correta
						qResponses.add("NÃ£o existe diferenÃ§a");
						qResponses.add("FunÃ§Ãµes nÃ£o static sÃ£o chamadas utilizando o nome da classe e o nome da funÃ§Ã£o");
						qResponses.add("NÃ£o existe diferenÃ§a entre elas, apenas onde sÃ£o chamadas");
						qResponses.add("FunÃ§Ãµes static sÃ£o chamadas utilizando somente o nome da funÃ§Ã£o");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Qual a diferenÃ§a em chamar uma funÃ§Ã£o static e uma funÃ§Ã£o nÃ£o static? \n");
						System.out.println(qQuestions[0] + ") " + qResponses.get(0)); 
						System.out.println(qQuestions[1] + ") " + qResponses.get(1));
						System.out.println(qQuestions[2] + ") " + qResponses.get(2));
						System.out.println(qQuestions[3] + ") " + qResponses.get(3));
						System.out.println(qQuestions[4] + ") " + qResponses.get(4));
						System.out.print("Escolha uma alternativa: ");

						for (int i = 0; i < 5; i++) {
							if (qResponses.get(i).equals("FunÃ§Ãµes static sÃ£o chamadas utilizando o nome da classe e o nome da funÃ§Ã£o"))	index = i;
						}

						alternativa = input.next();	
						principal.gateway(alternativa, extraLife, qQuestions[index], level, nQuestion, false); // Chamando o gateway
						break;
					case 6:
						System.out.println("\n Desafio: " + nQuestion);			
						qResponses.clear();
								
						qResponses.add("Para sinalizar que pode ser chamado em qualquer lugar"); // resposta correta
						qResponses.add("Para sinalizar que pode ser chamado em qualquer lugar dentro das classes que usam a principal como base");
						qResponses.add("Para sinalizar a funÃ§Ã£o main");
						qResponses.add("A palavra nÃ£o possui nenhuma funÃ§Ã£o");
						qResponses.add("Apenas classes do mesmo pacote podem chamar o mÃ©todo");

						Collections.shuffle(qResponses); // Embaralhando alternativas

						System.out.println("\n Qual a funÃ§Ã£o da palavra public antes do mÃ©todo? \n");
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

						System.out.println("\n Qual o valor de a ao final do cÃ³digo");
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
	 * @author Danilo Almeida
	 * Chamada para questÃ£o bÃ´nus
	 * 
	 * DescriÃ§Ã£o do(s) parÃ¢metro(s):
	 * @param nQuestion
	 * @return
	*/
	public boolean bonusQuestion(int nQuestion) {
		String alternativa;	

		principal.clearScreen();

		System.out.println("\n VocÃª recebeu uma pergunta bÃ´nus, acertando irÃ¡ ganhar mais uma vida extra e pularÃ¡ a pergunta anterior.");
		
		qResponses.clear();
		qResponses.add("public static void main(String[] args)"); // resposta correta
		qResponses.add("public static void principal(String[] args)");
		qResponses.add("public private void main([] args)");
		qResponses.add("public static void java(int] argumentos)");
		qResponses.add("private noStatic main(String[])");

		System.out.println("\n Como Ã© contruÃ­do a funÃ§Ã£o 'main' do JAVA? ");
		System.out.println(qQuestions[0] + ") " + qResponses.get(0)); // resposta correta
		System.out.println(qQuestions[1] + ") " + qResponses.get(1));
		System.out.println(qQuestions[2] + ") " + qResponses.get(2));
		System.out.println(qQuestions[3] + ") " + qResponses.get(3));
		System.out.println(qQuestions[4] + ") " + qResponses.get(4));
		System.out.print("Escolha uma alternativa: ");

		alternativa = input.next();	

		if (alternativa.toLowerCase().equals(qQuestions[0])){
			principal.gateway(alternativa, 1, qQuestions[0], level, nQuestion, true); // Chamando o gateway
			return true;
		} else {
			principal.gateway(alternativa, 0, qQuestions[0], level, nQuestion, true); // Chamando o gateway
			return false;
		}
	}

	/**
	 * @author Fernando Nascimento
	 * Retorna histÃ³rias referentes aos cÃ¡pitulos
	 * 
	 * DescriÃ§Ã£o do(s) parÃ¢metro(s):
	 * @param cap : capÃ­tulo que o jogo se encontra
	 * @param cResponse : define se a resposta Ã© correta ou nÃ£o
	 */
	public boolean stories(int cap, boolean cResponse) throws Exception{
		switch (cap) {
			case 0:
				principal.coteTime(100);
				System.out.println("\t MÃ£e Natureza: Parece que vocÃª estÃ¡ sem vidas extras, nÃ£o se preocupe, acerte esse desafio bÃ´nus e ganhe novas vidas extras. \n");
				principal.coteTime(5000);
				return true;
			case 1:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t MÃ£e Natureza: O que foi isso? Uma questÃ£o? Verdade elas podem meio que aparecer do nada. Vejo que acertou, sÃ³ para explicar, quando vocÃª acerta ou erra gera consequÃªncias, quando acerta a mÃ¡quina me ajuda a me recuperar, porÃ©m se erra ela comeÃ§a a destruir aquilo que me ajuda a manter minha energia. \n");
				else 
					System.out.println("\t MÃ£e Natureza: O que foi isso? Uma questÃ£o? Verdade elas podem meio que aparecer do nada. Vejo que errou, sÃ³ para explicar, quando vocÃª erra ou acerta gera consequÃªncias, quando acerta a mÃ¡quina me ajuda a me recuperar, porÃ©m se erra ela comeÃ§a a destruir aquilo que me ajuda a manter minha energia. \n");
					principal.coteTime(100);
					System.out.println("\t MÃ£e Natureza: Eu sei o que deve estar pensando, tipo meu deus por que a mÃ¡quina que era para ensinar estÃ¡ ajudando ou destruindo as coisas? A explicaÃ§Ã£o para isso Ã© a modificaÃ§Ã£o que eu fiz nela. Eu coloquei apenas a parte boa claro, porÃ©m a prÃ³pria mÃ¡quina fez a parte contraria tambÃ©m. \n");

				principal.coteTime(5000);
				return true;
			case 2:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t MÃ£e Natureza: Vejo que fiz a escolha certa de pedir a sua ajuda, pode ser apenas um pouco, mas jÃ¡ vejo que minha energia estÃ¡ se recuperando. Veja vocÃª plantou arvores por toda essa regiÃ£o, muito obrigada. \n");
				else 
					System.out.println("\t MÃ£e Natureza: A mÃ¡quina comeÃ§ou a desmatar toda a regiÃ£o, estou me sentindo fraca, mas nÃ£o se preocupe, conforme vocÃª acertar as questÃµes eu serei capaz de me recuperar. \n");

				principal.coteTime(5000);
				return true;
			case 3:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t MÃ£e Natureza: JÃ¡ consigo sentir a minha forÃ§a aumentando novamente, muito obrigada, mas ainda tem um longo caminho para percorrer, dessa vez a mÃ¡quina despoluiu a nascente desse rio. \n");
				else 
					System.out.println("\t MÃ£e Natureza: Minha forÃ§a estÃ¡ diminuindo, nÃ£o se preocupe sei que vocÃª consegue acertar na prÃ³xima, veja, a mÃ¡quina acabou de poluir ainda mais a nascente do rio. \n");
				return true;
			case 4:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t MÃ£e Natureza: Certo, acho que iremos conseguir recuperar minha forÃ§a, apenas tente manter acertando as questÃµes, a mÃ¡quina comeÃ§ou a despoluir o ar, retirando todo o excesso de gÃ¡s carbÃ´nico. \n");
				else 
					System.out.println("\t MÃ£e Natureza: Cof.Cof. A mÃ¡quina estÃ¡ soltando gases tÃ³xicos, se continuar assim a situaÃ§Ã£o pode ser irreversÃ­vel. \n");
				
				principal.coteTime(5000);
				return true;
			case 5:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t MÃ£e Natureza: A mÃ¡quina estÃ¡ retirando a toxicidade do solo, isto Ã© incrÃ­vel!! Muito obrigada, se continuar assim tenho certeza de que vai conseguir. \n");
				else 
					System.out.println("\t MÃ£e Natureza: A mÃ¡quina comeÃ§ou a liberar resÃ­duos tÃ³xicos no solo, isto Ã© bem ruim, continue com foco para conseguirmos reverter essa situaÃ§Ã£o. \n");

				principal.coteTime(5000);
				return true;
			case 6:
				principal.coteTime(100);
				if (cResponse) 
					System.out.println("\t MÃ£e Natureza: Vejo que a flora estÃ¡ conseguindo se estabelecer com a ajuda da mÃ¡quina, se continuar dessa maneira logo a fauna conseguirÃ¡ se estabelecer tambÃ©m. \n");
				else 
					System.out.println("\t MÃ£e Natureza: A situaÃ§Ã£o nÃ£o poderia ser pior, a mÃ¡quina estÃ¡ derramando Ã³leo no rio, assim as espÃ©cies que conseguiram sobreviver atÃ© agora irÃ£o morrer asfixiadas. \n");

				principal.coteTime(5000);
				return true;
			case 7:
				principal.coteTime(100);
				if (cResponse)
					System.out.println("\t MÃ£e Natureza: Consigo sentir, minha energia e forÃ§a estÃ£o recuperados, muito obrigado por sua ajuda, eu queria ter alguma forma de retribuir, porÃ©m por agora eu preciso cuidar de todo esse planeta, espero que nos encontremos outra vez, e nÃ£o se preocupe eu irei te retirar dessa mÃ¡quina. \n");
				else
					System.out.println("\t MÃ£e natureza: Estou sentindo minha energia esvaindo, o oxigÃªnio estÃ¡ deteriorado, os seres deste planeta irÃ£o morrer! :(");
				
				principal.coteTime(5000);
				return true;
			case 8:
				principal.coteTime(100);
				System.out.println("\t MÃ£e Natureza: Acho que agora se tornou irreversÃ­vel, pobres animais nÃ£o perceberam que estavam apenas se autodestruindo, muito obrigado por tentar me ajudar, mas agora Ã© um adeus. Queria ter passado mais tempo ao seu lado. \n");
				principal.coteTime(5000);
				return true;
			case 9:
				principal.coteTime(100);
				System.out.println("\t MÃ£e Natureza: Ainda temos alguma chance sobrando, foque e eu tenho certeza de que vocÃª irÃ¡ conseguir \n");
				principal.coteTime(3000);
				return true;
			case 10: 
				System.out.println("\t MÃ£e Natureza: " + name + " vocÃª Ã© um herÃ³i, graÃ§as a vocÃª todos os seres deste planeta poderÃ£o viver... ParabÃ©ns, vocÃª Ã© a pessoa mais inteligente que jÃ¡ vi.");
				System.out.println("\t MÃ£e Natureza: Ahhh... Mil perdÃµes esqueci de te tirar deste ambiente virtual... AtÃ© a prÃ³xima.");
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
				return true;
			case 11:
				System.out.println("\t MÃ£e Natureza: Oi, finalmente acordou. Estive esperando por isso por muito tempo, eu preciso da sua ajuda. Eu sei que vocÃª deve estar cheio de dÃºvidas, porÃ©m nÃ£o temos tempo para isso. Prazer eu sou o que vocÃªs humanos chamam de mÃ£e natureza, e vocÃª? \n");
				return true;
			case 12:
				System.out.println("\n");
				System.out.println("\t MÃ£e Natureza: Me desculpe nÃ£o me apresentar formalmente, porÃ©m atualmente da maneira que estou eu posso apenas me manifestar como uma voz em sua cabeÃ§a. Enfim chega de demora, preciso da sua ajuda para restaurar a minha forma original, se nÃ£o conseguir eu irei morrer, e comigo todo o planeta \n");
				principal.coteTime(100);
				System.out.println("\t MÃ£e Natureza: SerÃ¡ que estÃ¡ funcionando? Oi? Ufa acho que funcionou, sÃ³ para ter certeza, consegue me dizer o seu nome? \n");
				principal.coteTime(100);
				System.out.println("\t " + name + ": Meu nome Ã© " + name + " \n");
				principal.coteTime(1000);
				System.out.println("\t MÃ£e Natureza: Certo acho que estÃ¡ funcionando, bom antes de me introduzir para vocÃª eu deveria explicar a situaÃ§Ã£o, eu transferi a sua consciÃªncia para uma mÃ¡quina que encontrei em um local onde ensinavam pessoas como programar e tudo mais, entÃ£o provavelmente para manter o funcionamento aparecerÃ£o questÃµes que vocÃª precisa acertar ok? \n");
				return true;
			case 13:
				System.out.println("\t MÃ£e Natureza: Primeiro, precisa dizer a mÃ¡quina o nÃ­vel que vocÃª estÃ¡ em programaÃ§Ã£o, se eu nÃ£o me engano Ã© de 1 a 3, tipo um fÃ¡cil, mÃ©dio e difÃ­cil, sabe? \n");
				return true;
			case 14:
				System.out.println("\t MÃ£e Natureza: Certo, agora Ã© hora de me apresentar, sou a Natureza, eu estava acostumada a estar em toda parte, porÃ©m agora estou sumindo cada vez mais e preciso da sua ajuda para recuperar minha energia, os humanos estÃ£o sumindo, assim como vocÃª estava antes de eu conseguir transferir sua consciÃªncia para essa mÃ¡quina\n");
				return true;
			
			default:
				//Caso ocorra algum problema para encontrar o método
				System.out.println("História não encontrada");
				return false;
		}
	}

	/**
	 * Retorna instruÃ§Ãµes para o jogo
	 * 
	 * DescriÃ§Ã£o do(s) parÃ¢metro(s):
	 */
	public void instructions() throws Exception {
		System.out.println("\t \n                                   InstruÃ§Ãµes - AWTreech \n");
		System.out.println("\t - O jogo sÃ³ comeÃ§a quando digitar o comando '1';");
		System.out.println("\t - VocÃª poderÃ¡ sair a qualquer momento, digitando 'sair';");
		System.out.println("\t - Assim que escolher a dificuldade do jogo, vocÃª terÃ¡ vidas extras, se adaptando com dificuldade:");
		System.out.println("\t      * NÃ­vel FÃ¡cil - VocÃª terÃ¡ 3 vidas extras");
		System.out.println("\t      * NÃ­vel MÃ©dio - VocÃª terÃ¡ 2 vidas extras");
		System.out.println("\t      * NÃ­vel DifÃ­cil - VocÃª terÃ¡ 1 vidas extras");

		System.out.println("1 - Jogar");
		System.out.println("2 - CrÃ©ditos");
		System.out.println("3 - InstruÃ§Ã£o");
		System.out.println("4 - Sair");
		System.out.print("\nDigite algum comando: ");

		int value = input.nextInt();
		principal.menu(value);
	}

	/**
	 * @author Danilo Almeida
	 * Retorna menu do do jogo
	 * 
	 * DescriÃ§Ã£o do(s) parÃ¢metro(s):
	 * @param option : opÃ§Ã£o escolhida pelo usuÃ¡rio
	 */
	public void menu(int option) throws Exception{
		try {
			switch (option) {
				case 1: // Jogo
					principal.clearScreen(); // Limpando a tela
					// Dando introduÃ§Ã£o ao jogo
					principal.stories(11, true);
					// Armazenando nome em variÃ¡vel global
					System.out.print("\t Digite o nome do seu personagem: ");
					name = input.next();
					principal.stories(12, true);
					// Definindo level do jogo
					principal.stories(13, true);
					System.out.print("\t Digite, de 1 a 3 qual seu nÃ­vel em programaÃ§Ã£o? ");
					level = input.nextInt();
					principal.level(level);
					break;
				case 2: // CrÃ©ditos
					principal.credits();
					break;
				case 3: // InstruÃ§Ãµes
					principal.instructions();
				case 4: // Sair
					principal.exit();
					break;
				case 5: // Caso o usuÃ¡rio digite algum valor nÃ£o aceitÃ¡vel no level
					principal.clearScreen();
					System.out.println("\t Digite o nÃ­vel desejÃ¡vel, sÃ³ que dessa vez de 1 atÃ© 3! \n");
					System.out.print("\t Digite, de 1 a 3 qual seu nÃ­vel em programaÃ§Ã£o? ");
					level = input.nextInt();
					principal.level(level);
					break;
			
				default: // Caso seja passado uma opÃ§Ã£o invalÃ­da, retorna para o menu
					System.out.println("\nComando invÃ¡lido. Tente novamente.");
					System.out.println("\n \tAWTreech\n");
					System.out.println("1 - Jogar");
					System.out.println("2 - CrÃ©ditos");
					System.out.println("3 - InstruÃ§Ã£o");
					System.out.println("4 - Sair");
					System.out.print("\nDigite algum comando: ");
					
					int value = input.nextInt();
					principal.menu(value);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
	}

	
	/**
	 * @author Guilherme Monteiro
	 * Retorna crÃ©ditos do jogo
	 * 
	 * DescriÃ§Ã£o do(s) parÃ¢metro(s):
	 */
	public void credits() throws Exception {
		System.out.println("\n \tCriadores do jogo: \n");
		System.out.println("\tDanilo Almeida dos Santos");
		System.out.println("\tFernando Martiniano");
		System.out.println("\tGuilherme Monteiro \n");

		System.out.println("\n \tAWTreech\n");
		System.out.println("1 - Jogar");
		System.out.println("2 - CrÃ©ditos");
		System.out.println("3 - InstruÃ§Ã£o");
		System.out.println("4 - Sair");
		System.out.print("\nDigite algum comando: ");

		int value = input.nextInt();
		principal.menu(value);
	}

	/**
	 * @author Danilo Almeida
	 * Retorna tempo de espera em milisegundos para a prÃ³xima thread
	 * 
	 *  DescriÃ§Ã£o do(s) parÃ¢metro(s):
	 * @param milSegs : Define o tempo de espera
	 */
	private void coteTime(int milSegs) throws InterruptedException {
		Thread.sleep(milSegs);
	}

	/**
	 * @author Fernando Nascimento
	 * Classe principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("\tAWTreech\n");
			System.out.println("1 - Jogar");
			System.out.println("2 - CrÃ©ditos");
			System.out.println("3 - InstruÃ§Ã£o");
			System.out.println("4 - Sair");
			System.out.print("\nDigite algum comando: ");

			int value = input.nextInt();
			principal.menu(value);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
	}
}
