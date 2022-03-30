package br.com.alura.leilao.acceptance.steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class PropondoLancesSteps {

	private Lance lance;
	private Leilao leilao;
	private List<Lance> listaLances;
	
	
	@Dado("um lance valido")
	public void dado_um_lance_valido() {
		Usuario usuario = new Usuario("fulano");
		lance = new Lance(usuario , BigDecimal.TEN);
	}

	@Quando("propoe ao leilao")
	public void quando_propoe_o_lance() {
		leilao.propoe(lance);
	}
	
	@Entao("o lance eh aceito")
	public void entao_o_lance_eh_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}
	
//	@Dado("varios lances validos")
//	public void varios_lances_validos() {
//		Usuario usuario = new Usuario("fulano");
//		lance10 = new Lance(usuario , BigDecimal.TEN);
//		
//		Usuario usuario1 = new Usuario("beltrano");
//		lance15 = new Lance(usuario1 , new BigDecimal("15.0"));
//
//		leilao = new Leilao("Tablet XPTO");
//	}
	
	@Before //vai ser chamado antes de cada @Dado
	public void setup() {
		this.listaLances = new ArrayList<Lance>();
		leilao = new Leilao("Tablet XPTO");
	}
	
	@Dado("um lance de {double} reais do usuario {string}")
	public void um_lance_de_reais_do_usuario_fulano(Double valor, String nomeUsuario) {
		Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
		listaLances.add(lance);
	}

	@Quando("propoe varios lances ao leilao")
	public void propoe_varios_lances_ao_leilao() {
		this.listaLances.forEach(lance -> leilao.propoe(lance));
	}
	
	@Entao("os lances sao aceitos")
	public void os_lances_sao_aceitos() {
		Assert.assertEquals(this.listaLances.size(), leilao.getLances().size());
		Assert.assertEquals(this.listaLances.get(0).getValor(), leilao.getLances().get(0).getValor());
		Assert.assertEquals(this.listaLances.get(1).getValor(), leilao.getLances().get(1).getValor());
	}
}
