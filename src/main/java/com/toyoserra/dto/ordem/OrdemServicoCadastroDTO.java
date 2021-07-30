package com.toyoserra.dto.ordem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;

import com.toyoserra.models.OrdemServico;
import com.toyoserra.models.StatusOrdem;

public class OrdemServicoCadastroDTO {
	
	@NotNull
	private Long numeroDaOrdem;
	
	@NotNull
	private LocalDate data;
	
	@NotNull
	private LocalTime hora;
	
	public OrdemServico toOrdemServico() {
		
		OrdemServico ordemServico = new OrdemServico();
		
		String dataFinal = this.data + " " + this.hora;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dataFinal, formatter);
		
		ordemServico.setNumeroOrdem(this.numeroDaOrdem);
		ordemServico.setData(dateTime);
		ordemServico.setStatus(StatusOrdem.ABERTO);
		
		return ordemServico;			
	}
	
	public Long getNumeroOrdem() {
		return numeroDaOrdem;
	}

	public void setNumeroOrdem(Long numeroOrdem) {
		this.numeroDaOrdem = numeroOrdem;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public LocalTime getHora() {
		return hora;
	}


	public void setHora(LocalTime hora) {
		this.hora = hora;
	}	

}
