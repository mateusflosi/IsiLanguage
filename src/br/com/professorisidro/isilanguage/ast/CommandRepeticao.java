package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {

	private String condition;
	private ArrayList<AbstractCommand> lista;

	public CommandRepeticao(String condition, ArrayList<AbstractCommand> lista) {
		this.condition = condition;
		this.lista = lista;
	}

	@Override
	public String generateJavaCode(int indentacao) {
		StringBuilder str = new StringBuilder();
		str.append(getIdent(indentacao) + "while (" + condition + ") {\n");
		for (AbstractCommand cmd : lista) {
			str.append(cmd.generateJavaCode(indentacao + 1));
			if(!(cmd.equals(lista.get(lista.size() - 1)))){
				str.append("\n");
			}
		}
		str.append("\n" + getIdent(indentacao) + "}\n");
		return str.toString();
	}

	@Override
	public String toString() {
		return "CommandRepeticao [condition=" + condition + ", lista=" + lista + "]";
	}

}
