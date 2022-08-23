package br.com.professorisidro.isilanguage.ast;

import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
import br.com.professorisidro.isilanguage.exceptions.IsiLexicException;
import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;

public class CommandAtribuicao extends AbstractCommand {

	private String id;
	private String expr;
	private IsiSymbolTable list;

	public CommandAtribuicao(String id, String expr, IsiSymbolTable list) {
		this.id = id;
		this.expr = expr;
		this.list = list;
	}

	@Override
	public String generateJavaCode(int indentacao) throws IsiLexicException {
		// throw new IsiLexicException("Era esperado");
		return getIdent(indentacao) + id + " = " + expr + ";";
	}

	@Override
	public String toString() {
		return "CommandAtribuicao [id=" + id + ", expr=" + expr + "]";
	}

}
