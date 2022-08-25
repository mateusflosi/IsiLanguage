package br.com.professorisidro.isilanguage.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.com.professorisidro.isilanguage.exceptions.*;
import br.com.professorisidro.isilanguage.parser.IsiLangLexer;
import br.com.professorisidro.isilanguage.parser.IsiLangParser;

/* esta é a classe que é responsável por criar a interação com o usuário
 * instanciando nosso parser e apontando para o arquivo fonte
 *
 * Arquivo fonte: extensao .isi
 *
 */
public class MainClass {
	public static void main(String[] args) {
		String[] inputs = { "Leitura", "Escrita", "Atribuicao",
				"CondicionalSimples", "CondicionalComposta", "Loop",
				"NovosOperadores", "MainClass" };
		for (int i = 0; i < inputs.length; i++) {
			System.out.println("--------" + inputs[i] + ".isi--------\n\n\n");
			try {
				IsiLangLexer lexer;
				IsiLangParser parser;

				// leio o arquivo "input.isi" e isso é entrada para o Analisador Lexico
				lexer = new IsiLangLexer(CharStreams.fromFileName("inputs/" + inputs[i] + ".isi"));

				// crio um "fluxo de tokens" para passar para o PARSER
				CommonTokenStream tokenStream = new CommonTokenStream(lexer);

				// crio meu parser a partir desse tokenStream
				parser = new IsiLangParser(tokenStream);

				parser.prog();

				System.out.println("Compilation Successful");

				parser.generateCode(inputs[i]);

				parser.exibeComandos();

			} catch (IsiSemanticException ex) {
				System.err.println("Semantic error - " + ex.getMessage());
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println("ERROR " + ex.getMessage());
			}

			System.out.println("\n\n\n");
		}

	}

}
