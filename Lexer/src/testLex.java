
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class testLex {
	public static void main(String[] args) {
		boolean commentMode = false;
		String line = "";
		String tokenBuilder = "";
		char currentToken;

		try {

			Scanner scanner = new Scanner(new File("test.txt"));

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (!line.isEmpty()) {
					System.out.println("INPUT:" + line);
					for (int i = 0; i < line.length(); i++) {
						currentToken = line.charAt(i);
						switch (currentToken) {

						case '+':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case '-':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case '/':

							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '*') {
										commentMode = true;
										i++;
									} else
										System.out.println(line.charAt(i));

								} else {
									System.out.println(line.charAt(i));
								}

							}

							break;
						case '*':

							if (commentMode == true) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '/') {
										commentMode = false;
										i++;
									}

								}

							} else {
								System.out.println(line.charAt(i));
							}

							break;
						case '<':

							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '=') {
										System.out.println("<=");
										i++;
									} else
										System.out.println(line.charAt(i));

								} else
									System.out.println(line.charAt(i));

							} else {
								System.out.println(line.charAt(i));
							}

							break;
						case '>':

							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '=') {
										System.out.println(">=");
										i++;
									} else
										System.out.println(line.charAt(i));

								} else
									System.out.println(line.charAt(i));

							}

							break;

						case '=':

							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '=') {
										System.out.println("==");
										i++;
									} else
										System.out.println(line.charAt(i));

								} else
									System.out.println(line.charAt(i));

							}

							break;
						case '!':

							if (commentMode == false) {
								if (i + 1 < line.length()) {
									if (line.charAt(i + 1) == '=') {
										System.out.println("!=");
										i++;
									} else
										System.out.println("Error: " + line.charAt(i));

								} else
									System.out.println("Error: " + line.charAt(i));

							}

							break;
						case ',':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case '(':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case ')':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case '[':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case ']':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case '{':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case '}':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case ';':

							if (commentMode == false) {
								System.out.println(line.charAt(i));
							}

							break;
						case ' ':

							break;
						default:

							if (commentMode == false) {
								if (Character.isAlphabetic(line.charAt(i)) || Character.isDigit(line.charAt(i))) {
									if (Character.isAlphabetic(line.charAt(i))) {
										while (i < line.length() && Character.isAlphabetic(line.charAt(i))) {
											tokenBuilder = tokenBuilder + line.charAt(i);
											i++;
										}

									} else if (Character.isDigit(line.charAt(i))) {
										while (i < line.length() && Character.isDigit(line.charAt(i))) {
											tokenBuilder = tokenBuilder + line.charAt(i);
											i++;
										}
									}

								}
								if (isKeyword(tokenBuilder)) {
									System.out.println("Keyword: " + tokenBuilder);
									tokenBuilder = "";
									i--;

								} else if (isOnlyDigits(tokenBuilder)) {
									System.out.println("NUM: " + tokenBuilder);
									tokenBuilder = "";
									i--;
								} else if (isOnlyAlphas(tokenBuilder)) {
									System.out.println("ID: " + tokenBuilder);
									tokenBuilder = "";
									i--;

								} else {
									System.out.println("Error:" + line.charAt(i));

								}
							}
							break;
						}
					}
					System.out.println();
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

	}

	public static boolean isKeyword(String x) {
		boolean y = false;
		String[] keywords = { "while", "int", "void", "return", "if", "else" };
		for (int j = 0; j < keywords.length; j++) {
			if (x.equals(keywords[j])) {
				y = true;
			}
		}
		return y;
	}

	public static boolean isOnlyDigits(String x) {
		boolean y = false;

		for (int j = 0; j < x.length(); j++) {
			if (Character.isDigit(x.charAt(j))) {
				y = true;
			}
		}
		return y;
	}

	public static boolean isOnlyAlphas(String x) {
		boolean y = false;

		for (int j = 0; j < x.length(); j++) {
			if (Character.isAlphabetic(x.codePointAt(j))) {
				y = true;
			}
		}
		return y;
	}

}
