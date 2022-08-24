package br.com.professorisidro.isilanguage.ast;

public abstract class AbstractCommand {

	protected final String getIdent(int indentacao) {
		StringBuilder tap = new StringBuilder();

		for (int i = 0; i < indentacao; i++) {
			tap.append("\t");
		}

		return tap.toString();
	}

	public abstract String generateJavaCode(int indentacao);
}
