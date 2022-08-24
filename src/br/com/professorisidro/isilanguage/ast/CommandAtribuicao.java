package br.com.professorisidro.isilanguage.ast;

import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
import br.com.professorisidro.isilanguage.exceptions.IsiLexicException;
import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		if(list.get(id).getType()==0 && !validateInt()){
			throw new IsiLexicException("Erro: Tipo incompatível com a variável \""+id+"\" era esperado um tipo Int");
		}
		if(list.get(id).getType()==1 && !validateDec()){
			throw new IsiLexicException("Erro: Tipo incompatível com a variável \""+id+"\" era esperado um tipo Dec");
		}
		if(list.get(id).getType()==2 && !validateString()){
			throw new IsiLexicException("Erro: Tipo incompatível com a variável \""+id+"\" era esperado um tipo String");
		}
		expr = exp(expr);
        expr= raiz(expr);
        expr = log(expr);
		return getIdent(indentacao) + id + " = " + expr + ";";
	}

	public boolean validateInt(){
		if(expr.contains("\"")==true){
			return false;
		}
		if(expr.matches(".*[a-zA-Z].*")){
			String getVar = expr;
			getVar=getVar.replace("+", " ").replace("*", " ").replace("/", " ").replace("-", " ");
			String[] words = getVar.split(" ");

        	for (String word : words){
				if(list.exists(word) && list.get(word).getType()!=0){
					return false;
				}
			};
		}
		return true;
	}

	public boolean validateDec(){
		if(expr.contains("\"")==true){
			return false;
		}
		if(expr.matches(".*[a-zA-Z].*")){
			String getVar = expr;
			getVar=getVar.replace("+", " ").replace("*", " ").replace("/", " ").replace("-", " ");
			String[] words = getVar.split(" ");

        	for (String word : words){
				if(list.exists(word) && list.get(word).getType()!=1){
					return false;
				}
			};
		}
		return true;
	}

	public boolean validateString(){
		String getVar = expr;
		getVar=getVar.replaceAll("\"(.*?)\"","");
		if(expr.matches(".*[0-9].*")){
			return false;
		}
		if(expr.matches(".*[a-zA-Z].*")){
			getVar=getVar.replace("+", " ");
			String[] words = getVar.split(" ");
        	for (String word : words){
				if(list.exists(word) && list.get(word).getType()!=2){
					return false;
				}
			};
		}
		return true;
	}


	private String exp(String expr) {
        String regex = "(?<n1>"+ "[0-9]+(\\.[0-9]+)?" +")\\^(?<n2>"+ "[0-9]+(\\.[0-9]+)?" +")";
    	Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    	Matcher matcher = pattern.matcher(expr);

		String subst = "Math.pow(${n1}, ${n2})";
    	String modifiedExpression = matcher.replaceAll(subst);
    	return modifiedExpression;
    }

    private String raiz(String expr) {
        String regex = "(?<n1>"+ "[0-9]+(\\.[0-9]+)?" +")\\:(?<n2>"+ "[0-9]+(\\.[0-9]+)?" +")";
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(expr);

		String subst = "Math.pow(${n1}, 1/${n2})";
		String modifiedExpression = matcher.replaceAll(subst);
        return modifiedExpression;
    }

    private String log(String exp) {
        String regex = "(?<n1>"+ "[0-9]+(\\.[0-9]+)?" +")\\#(?<n2>"+ "[0-9]+(\\.[0-9]+)?" +")";
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(expr);

		String subst = "Math.log(${n1})/Math.log(${n2})";
		String modifiedExpression = matcher.replaceAll(subst);
        return modifiedExpression;
    }

	@Override
	public String toString() {
		return "CommandAtribuicao [id=" + id + ", expr=" + expr + "]";
	}

}
