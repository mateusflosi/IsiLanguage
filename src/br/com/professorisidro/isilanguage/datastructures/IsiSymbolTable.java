package br.com.professorisidro.isilanguage.datastructures;

import java.util.ArrayList;
import java.util.HashMap;

public class IsiSymbolTable {

	private HashMap<String, IsiVariable> map;

	public IsiSymbolTable() {
		map = new HashMap<String, IsiVariable>();

	}

	public void add(IsiVariable symbol) {
		map.put(symbol.getName(), symbol);
	}

	public boolean exists(String symbolName) {
		return map.get(symbolName) != null;
	}

	public IsiVariable get(String symbolName) {
		return map.get(symbolName);
	}

	public ArrayList<IsiVariable> getAll() {
		ArrayList<IsiVariable> lista = new ArrayList<IsiVariable>();
		for (IsiVariable symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}

}
