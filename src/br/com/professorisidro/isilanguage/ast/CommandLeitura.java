package br.com.professorisidro.isilanguage.ast;

import br.com.professorisidro.isilanguage.datastructures.IsiVariable;

public class CommandLeitura extends AbstractCommand {

	private String id;
	private IsiVariable var;

	public CommandLeitura(String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}

	@Override
	public String generateJavaCode(int indentacao) {
		return getIdent(indentacao) + id + "= _key." + (var.getType() == IsiVariable.INTEIRO ? "nextInt();"
				: var.getType() == IsiVariable.DECIMAL ? "nextDouble();" : "nextLine();");
	}

	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}

}
