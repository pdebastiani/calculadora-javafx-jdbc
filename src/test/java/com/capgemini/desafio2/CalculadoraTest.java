package com.capgemini.desafio2;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculadoraTest {
	
	@Test
	public void testValidaValorDigitadoEhNull() {
		String valorDigitado = "sd!@#°ºfçl*3";
		boolean resultado = Calculadora.validarValorDigitado(valorDigitado);
	    assertNotNull(null, resultado);
	}

	@Test
	public void testSeValorInformadoEhDiferenteDeNull() {
		String valorInformado = "123abc";
		boolean resultado = Calculadora.validarValorDigitado(valorInformado);
		assertNotNull("58dfg", resultado);
	}

	@Test
	public void testSeValorInformadoEhSomenteNumeros() {
		String valorInformado = "0123456789";
		boolean resultado = Calculadora.validarValorDigitado(valorInformado);
		assertNotNull("0123456789", resultado);
	}

	@Test
	public void testFiltroDaEntradaDeDadosTemNumeros() {
		String valorDigitado = "123abc";
		String valorFiltrado = Calculadora.filtrarValorDigitado(valorDigitado);
		assertEquals("123", valorFiltrado);
	}
		
	@Test
	public void testFiltroDaEntradaDeDadosSemNumeros() {
		String valorDigitado = "abc.*";
		String valorFiltrado = Calculadora.filtrarValorDigitado(valorDigitado);
		assertEquals("", valorFiltrado);
	}
	
	@Test
	public void testCalculaQtdePessoasVisualizamAnuncioOriginalIgual30vezes() {
		int valorInformado = 1;
		int valorCalculo =  Calculadora.calcularQtdePessoasVisualizamAnuncioOriginal(valorInformado);
		assertEquals(30, valorCalculo);
	}
	
	@Test
	public void testCalculaQtdePessoasVisualizamAnuncioOriginalDiferenteDe30vezes() {
		int valorInformado = 1;
		int valorCalculo = Calculadora.calcularQtdePessoasVisualizamAnuncioOriginal(valorInformado);
		assertNotEquals(31, valorCalculo, 0);
	}
	
	@Test
	public void testCalculaQtdePessoasVisualizamEClicamNoAnuncioIgual12porcento() {
		double qtdePessoasVisualizamAnuncio = 30;
		double valorCalculo = Calculadora.calcularQtdePessoasVisualizamClicamAnuncio(qtdePessoasVisualizamAnuncio);
		assertEquals(3.6, valorCalculo, 0.01);
	}
	
	@Test
	public void testCalculaQtdePessoasVisualizamEClicamNoAnuncDiferenteDe12porcento() {
		double qtdePessoasVisualizamAnuncio = 1;
		double valorCalculo = Calculadora.calcularQtdePessoasVisualizamClicamAnuncio(qtdePessoasVisualizamAnuncio);
		assertNotEquals(3.61, valorCalculo, 0.01);
	}

	@Test
	public void testCalculaQtdePessoasCompartilhamAnuncioIgual15porcento() {
		double qtdePessoasVisualizamClicamAnuncio = 3.6;
		double valorCalculo = Calculadora.calcularQtdePessoasCompartilhamAnuncio(qtdePessoasVisualizamClicamAnuncio);
		assertEquals(0.54, valorCalculo, 0.01);
	}
	
	@Test
	public void testCalculaQtdePessoasCompartilhamAnuncioDiferenteDe15porcento() {
		double qtdePessoasVisualizamClicamAnuncio = 3.6;
		double valorCalculo = Calculadora.calcularQtdePessoasCompartilhamAnuncio(qtdePessoasVisualizamClicamAnuncio);
		assertNotEquals(0.55, valorCalculo, 0.01);
	}
	
	@Test
	public void testCalculaQtdeNovasVisualizacoesGeradasIgual40vezes() {
		double qtdePessoasCompartilhamAnuncio = 0.54;
		double valorCalculo = Calculadora.calcularQtdeNovasVisualizacoesGeradas(qtdePessoasCompartilhamAnuncio);
		assertEquals(22.0, valorCalculo, 0.01);
	}
	
	@Test
	public void testCalculaQtdeNovasVisualizacoesGeradasDiferenteDe40vezes() {
		double qtdePessoasCompartilhamAnuncio = 0.54;
		double valorCalculo = Calculadora.calcularQtdeNovasVisualizacoesGeradas(qtdePessoasCompartilhamAnuncio);
		assertNotEquals(21.9, valorCalculo, 0.01);
	}
	
}


