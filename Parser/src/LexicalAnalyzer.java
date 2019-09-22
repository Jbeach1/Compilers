

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LexicalAnalyzer {

	/*
	 * Wilson Joshua Beach, N01109437 Lexical Analyzer Project 1 Compilers class
	 * Program to be executed from command line expecting file as arg
	 */
	public LexicalAnalyzer(File tester) {
		try {
			boolean commentMode = false;
			char currentToken;
			String line = "";
			String tokenBuilder = "";
			Scanner scanner = new Scanner(tester);
			PrintWriter writer = new PrintWriter("tokens.txt");

			// iterates file setting each line equal to a string
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				// skip all this if the line is empty
				if (!line.isEmpty()) {
					//System.out.println("INPUT:" + line);
					for (int i = 0; i < line.length(); i++) {
						currentToken = line.charAt(i);
						// switch checks if the token is a spec char
						switch (currentToken) {

						case '+':

							if (commentMode == false) {
								//System.out.println(line.charAt(i));
								writer.println(line.charAt(i));
							}

							break;
						case '-':

							if (commentMode == false) {
								// System.out.println(line.charAt(i));
								writer.println(line.charAt(i));
							}

							break;
						case '/':
							// checks for * following / to turn out comment mode on
							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '*') {
										commentMode = true;
										i++;
									} else {
										if (line.charAt(i + 1) == '/') {
											while (i + 1 < line.length()) {
												i++;
											}

										} else
											// System.out.println(line.charAt(i));
											writer.println(line.charAt(i));

									}
								} else
									// System.out.println(line.charAt(i));
									writer.println(line.charAt(i));
							}

							break;
						case '*':
							// checks for any backslash following directly after the * to turn comments off
							if (commentMode == true) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '/') {
										commentMode = false;
										i++;
									}

								}

							} else {
								writer.println(line.charAt(i));
								// System.out.println(line.charAt(i));
							}

							break;
						case '<':
							// accepts/checks for any <=
							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '=') {
										// System.out.println("<=");
										writer.println("<=");
										i++;
									} else
										// System.out.println(line.charAt(i));
										writer.println(line.charAt(i));

								} else
									// System.out.println(line.charAt(i));
									writer.println(line.charAt(i));
							} else {
								// System.out.println(line.charAt(i));
								writer.println(line.charAt(i));
							}

							break;
						case '>':
							// accepts/check for any >=
							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '=') {
										// System.out.println(">=");
										writer.println(">=");
										i++;
									} else
										// System.out.println(line.charAt(i));
										writer.println(line.charAt(i));

								} else
									// System.out.println(line.charAt(i));
									writer.println(line.charAt(i));
							}

							break;

						case '=':
							// accept ='s and check for =='s
							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '=') {
										// System.out.println("==");
										writer.println("==");
										i++;
									} else
										// System.out.println(line.charAt(i));
										writer.println(line.charAt(i));

								} else
									// System.out.println(line.charAt(i));
									writer.println(line.charAt(i));
							}

							break;
						case '!':
							// includes check to give error for only ! but accept !=
							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '=') {
										// System.out.println("!=");
										writer.println("!=");
										i++;
									} else
										// System.out.println("Error: " + line.charAt(i));
										//System.out.println("Error Invalid Character found in LexicalAnalyzer");
									//System.exit(0);
									writer.println("Error: " + line.charAt(i));

								} else
									System.out.println("Error: " + line.charAt(i));
								//System.out.println("Error Invalid Character found in LexicalAnalyzer");
								//System.exit(0);
								//writer.println("Error: " + line.charAt(i));
							}

							break;
						// checks if is any of these are the current token
						case ',':

							if (commentMode == false) {
								//System.out.println(line.charAt(i));
								writer.println(line.charAt(i));
							}

							break;
						case '(':

							if (commentMode == false) {
								writer.println(line.charAt(i));
								//System.out.println(line.charAt(i));
							}

							break;
						case ')':

							if (commentMode == false) {
								writer.println(line.charAt(i));
								//System.out.println(line.charAt(i));
							}

							break;
						case '[':

							if (commentMode == false) {
								writer.println(line.charAt(i));
								//System.out.println(line.charAt(i));
							}

							break;
						case ']':

							if (commentMode == false) {
								writer.println(line.charAt(i));
								//System.out.println(line.charAt(i));
							}

							break;
						case '{':

							if (commentMode == false) {
								writer.println(line.charAt(i));
								//System.out.println(line.charAt(i));
							}

							break;
						case '}':

							if (commentMode == false) {
								writer.println(line.charAt(i));
								//System.out.println(line.charAt(i));
							}

							break;
						case ';':

							if (commentMode == false) {
								writer.println(line.charAt(i));
								//System.out.println(line.charAt(i));
							}

							break;
						// check for whitespace
						case ' ':

							break;
						// if our token is not a spec char, it can only be 1 of 4 things:
						// a ID, Keyword, error, or num
						default:

							if (commentMode == false) {
								// if we are a potiential ID or num or Keyword
								if (Character.isAlphabetic(line.charAt(i)) || Character.isDigit(line.charAt(i))) {
									if (Character.isAlphabetic(line.charAt(i))) {
										while (i < line.length() && Character.isAlphabetic(line.charAt(i))) {
											tokenBuilder = tokenBuilder + line.charAt(i);
											i++;
										}
										// check if its a num
									} else if (Character.isDigit(line.charAt(i))) {
										while (i < line.length() && Character.isDigit(line.charAt(i))) {
											tokenBuilder = tokenBuilder + line.charAt(i);
											i++;
										}
									}

								} // at this point we are formed a continous token comprised only of digits or
									// Nums
									// so here I am checking if our token matches a keyword
								if (isKeyword(tokenBuilder)) {
									writer.println("K: "+tokenBuilder);
									//System.out.println("Keyword: " + tokenBuilder);
									tokenBuilder = "";
									i--;
									// verify to check it is only digits, then print as a num
								} else if (isOnlyDigits(tokenBuilder)) {
									writer.println("NUM: "+tokenBuilder);
									//System.out.println("NUM: " + tokenBuilder);
									tokenBuilder = "";
									i--;
									// verify it is only alphabetical chars and NOT a keyword
								} else if (isOnlyAlphas(tokenBuilder)) {
									writer.println("ID: "+tokenBuilder);
									//System.out.println("ID: " + tokenBuilder);
									tokenBuilder = "";
									i--;

								} else {
									// if its not alphas, spec chars, or nums or whitespace, then error
									//writer.println("Error: "+line.charAt(i));
									//System.out.println("Error Invalid Character found in LexicalAnalyzer");
									//System.exit(0);
									//System.out.println("Error:" + line.charAt(i));

								}
							}
							break;
						}
					}
					// formatting
					//System.out.println();
				}
			}
			writer.println("#");
			scanner.close();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

	} 

//private helper functions for verifying tokens
	private static boolean isKeyword(String x) {
		boolean y = false;
		String[] keywords = { "while", "int", "void", "return", "if", "else"};
		for (int j = 0; j < keywords.length; j++) {
			if (x.equals(keywords[j])) {
				y = true;
			}
		}
		return y;
	}

	private static boolean isOnlyDigits(String x) {
		boolean y = false;

		for (int j = 0; j < x.length(); j++) {
			if (Character.isDigit(x.charAt(j))) {
				y = true;
			}
		}
		return y;
	}

	private static boolean isOnlyAlphas(String x) {
		boolean y = false;

		for (int j = 0; j < x.length(); j++) {
			if (Character.isAlphabetic(x.codePointAt(j))) {
				y = true;
			}
		}
		return y;
	}

}