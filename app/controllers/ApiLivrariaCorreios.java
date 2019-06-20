package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import models.FretePrecoPrazo;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Http;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.libs.XML;
import play.mvc.Controller;

public class ApiLivrariaCorreios extends Controller{
	
	public static void calculoFrete(String cepDestino, String peso, String comprimento, String altura, String largura, double valorDeclarado) {
		ApiLivrariaCorreios.response.accessControl("*");
		HttpResponse res = WS.url("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa=08082650&sDsSenha=564321"
				+ "&sCepOrigem=59910000&sCepDestino="+cepDestino+"&nVlPeso="+peso+"&nCdFormato=1&nVlComprimento="+comprimento+
				"&nVlAltura="+altura+"&nVlLargura="+largura+"&sCdMaoPropria=n&nVlValorDeclarado="+valorDeclarado
				+ "&sCdAvisoRecebimento=n&nCdServico=04510&nVlDiametro=0&StrRetorno=xml&nIndicaCalculo=3").get();
		
		String xml = res.getString();
		String tagV = "<Valor>";
		String preco = getTagValue(xml, tagV);
		
		String tagP = "<PrazoEntrega>";
		String prazo = getTagValue(xml, tagP);
		
		FretePrecoPrazo fretePrecoPrazo = new FretePrecoPrazo(preco, prazo);
		
		Gson gson = new Gson();
        
        String json = gson.toJson(fretePrecoPrazo);
 
        renderJSON(json);		
		
	} 
	 

	public static String getTagValue(String xml, String tag) {
	    String closeTag = new StringBuffer(tag).insert(1, "/").toString();
	    int from = xml.indexOf(tag) + tag.length();
	    int to = xml.indexOf(closeTag);
	
	    return xml.substring(from, to);
	}

}
