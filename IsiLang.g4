grammar IsiLang;

@header {
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.CommandLeitura;
	import br.com.professorisidro.isilanguage.ast.CommandEscrita;
	import br.com.professorisidro.isilanguage.ast.CommandAtribuicao;
	import br.com.professorisidro.isilanguage.ast.CommandDecisao;
	import br.com.professorisidro.isilanguage.ast.CommandRepeticao;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members {
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiVariable symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private Stack<String> stackDecision = new Stack<String>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<String> _getNotUsedVariables = new ArrayList<String>();

	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}

	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}

	public void generateCode(String classe){
		program.generateTarget(classe);
	}
}

prog:
	'programa' (declara)+ bloco 'fimprog.' {
		program.setVarTable(symbolTable);
        program.setComandos(stack.pop());
		if(_getNotUsedVariables.size() > 0){
        	System.err.println("Warning -"+_getNotUsedVariables+" not used");
    	}   
           };

declara:
	'declare' tipo ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);
						 _getNotUsedVariables.add(_varName);
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    } (
		VIR ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);
						 _getNotUsedVariables.add(_varName);
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
	)* SC;

tipo:
	'int' { _tipo = IsiVariable.INTEIRO;  }
	| 'decimal' { _tipo = IsiVariable.DECIMAL;  }
	| 'texto' { _tipo = IsiVariable.TEXT;  };

bloco:
	{ curThread = new ArrayList<AbstractCommand>();
	        stack.push(curThread);
          } (cmd)+;

cmd:
	cmdLeitura SC
	| cmdEscrita SC
	| cmdExpr SC
	| cmdIf
	| cmdWhile;

cmdLeitura:
	'leia' AP ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                        } FP {
              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              };

cmdEscrita:
	'escreva' AP (ID | TEXTO) { verificaID(_input.LT(-1).getText());
	                  _writeID = _input.LT(-1).getText();
					  _getNotUsedVariables.remove(_writeID);
                     } FP {
               	  CommandEscrita cmd = new CommandEscrita(_writeID);
               	  stack.peek().add(cmd);
               };

cmdExpr:
	ID { verificaID(_input.LT(-1).getText());
                    _exprID = _input.LT(-1).getText();
                   } ATTR { _exprContent = ""; } expr {
               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent, symbolTable);
				 cmd.checkTypes();
               	 stack.peek().add(cmd);
               };

cmdIf:
	'se' AP expr { _exprDecision = _input.LT(-1).getText(); } OPREL { _exprDecision += _input.LT(-1).getText();
		} expr {_exprDecision += _input.LT(-1).getText(); } FP 'entao' ACH { curThread = new ArrayList<AbstractCommand>();
                      stack.push(curThread);
					  stackDecision.push(_exprDecision);
                    } (cmd)+ FCH {
                       listaTrue = stack.pop();
					   _exprDecision = stackDecision.pop();

                    } (
		'senao' ACH {
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
						stackDecision.push(_exprDecision);
                   	 } (cmd)+ FCH {
                   		listaFalse = stack.pop();
						_exprDecision = stackDecision.pop();

                   	}
	)? {
		//Problema nessa parte. Caso coloque apenas um if sem else no input, o else ainda aparecera e com os comandos de outro else. ARRUMAR

		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
        stack.peek().add(cmd);};

cmdWhile:
	'enquanto' AP expr { _exprDecision = _input.LT(-1).getText(); } OPREL { _exprDecision += _input.LT(-1).getText();
		} expr {_exprDecision += _input.LT(-1).getText();} FP 'faca' ACH { curThread = new ArrayList<AbstractCommand>();
                      stack.push(curThread);
					  stackDecision.push(_exprDecision);
                    } (cmd)+ FCH {
                       listaTrue = stack.pop();
					   _exprDecision = stackDecision.pop();
					   CommandRepeticao cmd = new CommandRepeticao(_exprDecision, listaTrue);
					   stack.peek().add(cmd);
                    };

expr: fator termo;

termo:
	(OP { _exprContent += _input.LT(-1).getText();} fator termo)?;

fator:
	TEXTO {
              	_exprContent += _input.LT(-1).getText();
				_getNotUsedVariables.remove(_input.LT(-1).getText());
              }
	| NUMBER {
              	_exprContent += _input.LT(-1).getText();
				_getNotUsedVariables.remove(_input.LT(-1).getText());
              }
	| ID { verificaID(_input.LT(-1).getText());
	               _exprContent += _input.LT(-1).getText();
				   _getNotUsedVariables.remove(_input.LT(-1).getText());
                 };

AP: '(';

FP: ')';

SC: '.';

OP: '+' | '-' | '*' | '/' | '^' | ':' | '#';

ATTR: ':=';

VIR: ',';

ACH: '{';

FCH: '}';

A: '"';

OPREL: '>' | '<' | '>=' | '<=' | '==' | '!=';

ID: [a-z] ([a-z] | [A-Z] | [0-9])*;

NUMBER: [0-9]+ (',' [0-9]+)?;

TEXTO: A ([a-z] | [A-Z] | [0-9])* A;

WS: (' ' | '\t' | '\n' | '\r') -> skip;
