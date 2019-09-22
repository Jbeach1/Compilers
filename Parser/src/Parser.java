
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
	static String token = null;
	static Scanner scanner = null;

	public static void main(String[] args) {

		File tester = new File(args[0]);
		LexicalAnalyzer LexLuther = new LexicalAnalyzer(tester);
		tester = new File("tokens.txt");

		try {
			scanner = new Scanner(tester);
		} catch (FileNotFoundException e) {
			System.out.println("File not found in parser.");

		}
		token = scanner.nextLine();
		program();

		if (token.equals("#")) {
			System.out.println("ACCEPT");
		} else
			reject();

	} // end of main
	
	
////////////////////C- language methods begin here///////////////////////////////////////////////

	public static void program() {
		declarationList();
	}

	public static void declarationList() {
		declaration();
		declarationList1();
	}

	public static void declarationList1() {
		if (token.contains("K: ")) {
			declaration();
			declarationList1();
		} 
	}

	public static void declaration() {
		typeSpecifier();
		if (token.contains("ID: ")) {
			returnToken();
			declaration1();

		} else reject();  //ghjhgfd
	}

	public static void declaration1() {
		if (token.equals(";") || token.equals("[")) {
			varDeclaration1();
		} else if (token.equals("(")) {
			funDeclaration1();
		} else
			reject();
	}

	public static void varDeclaration() {
		
		typeSpecifier();
		if (token.contains("ID: ")) {
			returnToken();
			varDeclaration1();
		} else
			reject();
		
	}

	public static void varDeclaration1() {
		if (token.equals(";")) {
			returnToken();
		} else if (token.equals("[")) {
			returnToken();
			if (token.contains("NUM: ")) {
				returnToken();
				if (token.equals("]")) {
					returnToken();
					if (token.equals(";")) {
						returnToken();
					} else
						reject();
				} else
					reject();
			} else
				reject();
		} else
			reject();
	}

	public static void typeSpecifier() {
		if (token.equals("K: int") || token.equals("K: void")) {
			returnToken();
		} else
			reject();
	}

	public static void funDeclaration1() {
		if (token.equals("(")) {
			returnToken();
			params();
			if (token.equals(")")) {
				returnToken();
				compoundStmt();
				
			} else
				reject();
		}

	}

	public static void params() {
		if (token.equals("K: void")) {
			returnToken();
		} else
			paramsList();

	}

	public static void paramsList() {
		param();
		paramsList1();
	}

	public static void paramsList1() {
		if (token.equals(",")) {
			returnToken();
			param();
			paramsList1();
		} else
			epsilon();

	}

	public static void param() {
		typeSpecifier();
		if (token.contains("ID: ")) {
			returnToken();
			if (token.equals("[")) {
				returnToken();
				if (token.equals("]")) {
					returnToken();
				}

			}
		}
	}

	public static void compoundStmt() {
		if (token.equals("{")) {
			returnToken();
			localDeclaration();
			statementList();
			if (token.equals("}")) {
				returnToken();
			}
		}
	}

	public static void localDeclaration() {
		
		if (token.equals("K: int") || token.equals("K: float") || token.equals("K: void")) {
			varDeclaration();
			localDeclaration();
			
		}

	}

	public static void statementList() {
	
		if (token.contains("K: ") || token.contains("ID: ") || token.equals("(") || token.equals("{")
				|| token.equals(";")) {
			statement();
			statementList();
		}
	}

	public static void statement() {
		if (token.equals("K: if")) {
			selectionStmt();
		} else if (token.equals("K: return")) {
			returnStmt();
		} else if (token.equals("K: while")) {
			iterationStmt();
		} else if (token.equals("{")) {
			compoundStmt();
		} else
			expressionStmt();

	}

	public static void expressionStmt() {
		if (token.equals(";")) {
			returnToken();
		} else {
			expression();
			if (token.equals(";")) {
				returnToken();
			} else
				reject();
		}
	}

	public static void selectionStmt() {

		if (token.equals("K: if")) {
			returnToken();
			if (token.equals("(")) {
				returnToken();
				expression();
				if (token.equals(")")) {
					returnToken();
					statement();
					if (token.equals("K: else")) {
						returnToken();
						statement();
					}
				} else
					reject();
			} else
				reject();
		} else
			reject();
	}

	public static void iterationStmt() {
		if (token.equals("K: while")) {
			returnToken();
			if (token.equals("(")) {
				returnToken();
				expression();
				if (token.equals(")")) {
					returnToken();
					statement();
				} else
					reject();
			} else
				reject();
		} else
			reject();
	}

	public static void returnStmt() {
		if (token.equals("K: return")) {
			returnToken();
			if (token.equals(";")) {
				returnToken();
			} else {
				expression();
				if (token.equals(";")) {
					returnToken();
				} else
					reject();
			}
		} else
			reject();
	}

	public static void expression() {
		
		additiveExpression();
		if (token.equals("<=") || token.equals("<") || token.equals(">") || token.equals(">=") || token.equals("==")
				|| token.equals("!=")) {
			relop();
			additiveExpression();
		}
	}

	public static void var1() {
	
		if (token.equals("[")) {
			returnToken();
			expression();
			if (token.equals("]")) {
				returnToken();
				if (token.equals("=")) {
					returnToken();
					if (token.contains("ID: ")) {
						returnToken();
						if (token.equals("[")) {
							returnToken();
							expression();
							if (token.equals("]")) {
								returnToken();
								var1();
							} else
								reject();
						} else
							epsilon();

					} else
						reject();

				} else
					epsilon();

			} else
				reject();

		} else if (token.equals("=")) {
			returnToken();
			expression();
		} 
		
	}

	public static void relop() {
		if (token.equals("<=") || token.equals("<") || token.equals(">") || token.equals(">=") || token.equals("==")
				|| token.equals("!=")) {
			returnToken();
		} else
			reject();

	}

	public static void additiveExpression() {
		term();
		additiveExpression1();
	}

	public static void additiveExpression1() {
	
		if (token.equals("+") || token.equals("-")) {
			addop();
			term();
			additiveExpression1();
		}
	}

	public static void addop() {
		if (token.equals("+")) {
			returnToken();
		} else if (token.equals("-")) {
			returnToken();
		} else
			reject();
	}

	public static void term() {
		factor();
		term1();
	}

	public static void term1() {
		if (token.equals("*") || token.equals("/")) {
			mulop();
			factor();
			term1();
		}
	}

	public static void mulop() {
		if (token.equals("*")) {
			returnToken();
		} else if (token.equals("/")) {
			returnToken();
		} else
			reject();
	}

	public static void factor() {
		
		if (token.contains("NUM: ")) {
			returnToken();
		} else if (token.contains("ID: ")) {
			returnToken();
			factor1();
		} else if (token.equals("(")) {
			returnToken();
			expression();
			if (token.equals(")")) {
				returnToken();
			} else
				reject();

		}
		
	}

	public static void factor1() {
		if (token.equals("(")) {
			call1();
		} else {
			var1();
		}

	}

	public static void call() {
		if (token.contains("ID: ")) {
			returnToken();
			call1();
		} else
			reject();
	}

	public static void call1() {

		if (token.equals("(")) {
			returnToken();
			args();
			if (token.equals(")")) {
				returnToken();
			} else
				reject();
		} else
			reject();
	}

	public static void args() {

		if (!token.equals(")")) {
			argList();
		}
	}

	public static void argList() {
		expression();
		argList1();
	}

	public static void argList1() {
		if (token.equals(",")) {
			returnToken();
			expression();
			argList1();
		}
	}

	public static void reject() {
	
		System.out.println("REJECT");
		System.exit(0);
	}

	public static void returnToken() {
		//System.out.println(token);
		token = scanner.nextLine();
	}

	public static void epsilon() {
		// nothing
	}

	public static void test() {
		System.out.println("@@@@@@@@@@     " + token + "           @@@@@@@@@");
	}
}// end of entire code (parser class)