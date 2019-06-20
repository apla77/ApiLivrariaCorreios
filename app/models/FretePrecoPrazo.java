package models;

import javax.persistence.Entity;

//@Entity 
public class FretePrecoPrazo {
	
	public String preco;
	public String prazo;
	
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getPrazo() {
		return prazo;
	}
	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

}
