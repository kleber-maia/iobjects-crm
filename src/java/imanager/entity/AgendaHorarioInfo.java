    
package imanager.entity;

import java.sql.*;
 
import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informa��es contidas pela entidade AgendaHorario.
 */
public class AgendaHorarioInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private int agendaId = 0;
  private int agendaHorarioId = 0;
  private Timestamp dataAgendamento = DateTools.ZERO_DATE;
  private String horaAgendamento = "";
  private int contatoId = 0;  
  private String anotacoes = "";  
  private int status = 0;  
  private int usuarioInclusaoId = 0;  
  private Timestamp dataHoraInclusao = DateTools.ZERO_DATE;
  private int usuarioAlteracaoId = 0;  
  private Timestamp dataHoraAlteracao = DateTools.ZERO_DATE;

  /**
   * Construtor padr�o.
   */
  public AgendaHorarioInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param agendaId int Agenda ID.
   * @param agendaHorarioId int Agenda Horario ID.
   * @param dataAgendamento Timestamp Data Agendamento.
   * @param horaAgendamento String Hora Agendamento.
   * @param contatoId int Contato ID.
   * @param anotacoes String Anota��es.
   * @param status int Status.
   * @param usuarioInclusaoId int Usu�rio Inclus�o ID.
   * @param dataHoraInclusao Timestamp Data Hora Inclus�o.
   * @param usuarioAlteracaoId int Usu�rio Altera��o ID.
   * @param dataHoraAlteracao Timestamp Data Hora Altera��o.
   */
  public AgendaHorarioInfo(
           int empresaId,
           int agendaId,
           int agendaHorarioId,
           Timestamp dataAgendamento,
           String horaAgendamento,
           int contatoId,
           String anotacoes,
           int status,
           int usuarioInclusaoId,
           Timestamp dataHoraInclusao,
           int usuarioAlteracaoId,
           Timestamp dataHoraAlteracao
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.agendaId = agendaId;
    this.agendaHorarioId = agendaHorarioId;
    this.dataAgendamento = dataAgendamento;
    this.horaAgendamento = horaAgendamento;
    this.contatoId = contatoId;
    this.anotacoes = anotacoes;
    this.status = status;
    this.usuarioInclusaoId = usuarioInclusaoId;
    this.dataHoraInclusao = dataHoraInclusao;
    this.usuarioAlteracaoId = usuarioAlteracaoId;
    this.dataHoraAlteracao = dataHoraAlteracao;
  }
  

  public int getEmpresaId() {
    return empresaId;
  }

  public int getAgendaId() {
    return agendaId;
  }

  public int getAgendaHorarioId() {
    return agendaHorarioId;
  }
    
  public Timestamp getDataAgendamento() {
    return dataAgendamento;
  }

  public String getHoraAgendamento() {
    return horaAgendamento;
  }

  public int getContatoId() {
    return contatoId;
  }
  
  public String getAnotacoes() {
    return anotacoes;
  }
  
  public int getStatus() {
    return status;
  }
  
  public int getUsuarioInclusaoId() {
    return usuarioInclusaoId;
  }
  
  public Timestamp getDataHoraInclusao() {
    return dataHoraInclusao;
  }
  
  public int getUsuarioAlteracaoId() {
    return usuarioAlteracaoId;
  }
  
  public Timestamp getDataHoraAlteracao() {
    return dataHoraAlteracao;
  }
  
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }

  public void setAgendaId(int agendaId) {
    this.agendaId = agendaId;
  }

  public void setAgendaHorarioId(int agendaHorarioId) {
    this.agendaHorarioId = agendaHorarioId;
  }
  
  public void setDataAgendamento(Timestamp dataAgendamento) {
    this.dataAgendamento = dataAgendamento;
  }

  public void setHoraAgendamento(String horaAgendamento) {
    this.horaAgendamento = horaAgendamento;
  }

  public void setContatoId(int contatoId) {
    this.contatoId = contatoId;
  }
  
  public void setAnotacoes(String anotacoes) {
    this.anotacoes = anotacoes;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public void setUsuarioInclusaoId(int usuarioInclusaoId) {
    this.usuarioInclusaoId = usuarioInclusaoId;
  }
  
  public void setDataHoraInclusao(Timestamp dataHoraInclusao) {
    this.dataHoraInclusao = dataHoraInclusao;
  }
  
  public void setUsuarioAlteracaoId(int usuarioAlteracaoId) {
    this.usuarioAlteracaoId = usuarioAlteracaoId;
  }
  
  public void setDataHoraAlteracao(Timestamp dataHoraAlteracao) {
    this.dataHoraAlteracao = dataHoraAlteracao;
  }
}
