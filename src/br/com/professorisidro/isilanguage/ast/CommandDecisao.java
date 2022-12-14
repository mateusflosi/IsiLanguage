package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandDecisao extends AbstractCommand {

	private String condition;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;

	public CommandDecisao(String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf) {
		this.condition = condition;
		this.listaTrue = lt;
		this.listaFalse = lf;
	}

	@Override
	public String generateJavaCode(int indentacao) {
		StringBuilder str = new StringBuilder();
		str.append(getIdent(indentacao) + "if (" + condition + ") {\n");
		for (AbstractCommand cmd : listaTrue) {
			str.append(cmd.generateJavaCode(indentacao + 1));
			if (!(cmd.equals(listaTrue.get(listaTrue.size() - 1)))) {
				str.append("\n");
			}
		}
		str.append("\n" + getIdent(indentacao) + "}\n");
		if (listaFalse != null && listaFalse.size() > 0) {
			str.append(getIdent(indentacao) + "else {\n");
			for (AbstractCommand cmd : listaFalse) {
				str.append(cmd.generateJavaCode(indentacao + 1));
				if (!(cmd.equals(listaFalse.get(listaFalse.size() - 1)))) {
					str.append("\n");
				}
			}
			str.append("\n" + getIdent(indentacao) + "}");

		}
		return str.toString();
	}

	@Override
	public String toString() {
		return "CommandDecisao [condition=" + condition + ", listaTrue=" + listaTrue + ", listaFalse=" + listaFalse
				+ "]";
	}

}
