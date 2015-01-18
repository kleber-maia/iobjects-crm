/*
The MIT License (MIT)

Copyright (c) 2008 Kleber Maia de Andrade

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/   
    
package imanager.entity;

import java.sql.*;
 
import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade AgendaHorario.
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
   * Construtor padrão.
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
   * @param anotacoes String Anotações.
   * @param status int Status.
   * @param usuarioInclusaoId int Usuário Inclusão ID.
   * @param dataHoraInclusao Timestamp Data Hora Inclusão.
   * @param usuarioAlteracaoId int Usuário Alteração ID.
   * @param dataHoraAlteracao Timestamp Data Hora Alteração.
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
