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
 * Representa as informações contidas pela entidade Tarefa.
 */
public class TarefaInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private int tarefaId = 0;  
  private int departamentoId = 0;  
  private int usuarioId = 0;  
  private int assuntoId = 0;  
  private int prioridade = 0;  
  private int clienteId = 0;  
  private Timestamp prazoOriginal = DateTools.ZERO_DATE;
  private Timestamp prazo = DateTools.ZERO_DATE;
  private int prorrogacao = 0;  
  private int status = 0;  
  private String descricao = "";  
  private Timestamp dataInclusao = DateTools.ZERO_DATE;  
  private int usuarioInclusaoId = 0;  
  private Timestamp dataHoraAlteracao = DateTools.ZERO_DATE;  
  private int usuarioAlteracaoId = 0;
  private String linkExterno = "";

  /**
   * Construtor padrão.
   */
  public TarefaInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param tarefaId int Tarefa ID.
   * @param departamentoId int Departamento ID.
   * @param usuarioId int Usuário ID.
   * @param assuntoId int Assunto ID.
   * @param prioridade int Prioridade.
   * @param clienteId int Cliente ID.
   * @param prazo Timestamp Prazo Original.
   * @param prazo Timestamp Prazo.
   * @param prorrogacao int Prorrogação.
   * @param status int Status.
   * @param descricao String Descrição.
   * @param dataInclusao Timestamp Data Inclusão.
   * @param usuarioInclusaoId int Usuário Inclusão ID.
   * @param dataHoraAlteracao Timestamp Data Hora Alteração.
   * @param usuarioAlteracaoId int Usuário Alteração ID.
   * @param linkExterno String Link Externo.
   */
  public TarefaInfo(
           int empresaId,
           int tarefaId,
           int departamentoId,
           int usuarioId,
           int assuntoId,
           int prioridade,
           int clienteId,
           Timestamp prazoOriginal,
           Timestamp prazo,
           int prorrogacao,
           int status,
           String descricao,
           Timestamp dataInclusao,
           int usuarioInclusaoId,
           Timestamp dataHoraAlteracao,
           int usuarioAlteracaoId,
           String linkExterno
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.tarefaId = tarefaId;
    this.departamentoId = departamentoId;
    this.usuarioId = usuarioId;
    this.assuntoId = assuntoId;
    this.prioridade = prioridade;
    this.clienteId = clienteId;
    this.prazo = prazoOriginal;
    this.prazo = prazo;
    this.prorrogacao = prorrogacao;
    this.status = status;
    this.descricao = descricao;
    this.dataInclusao = dataInclusao;
    this.usuarioInclusaoId = usuarioInclusaoId;
    this.dataHoraAlteracao = dataHoraAlteracao;
    this.usuarioAlteracaoId = usuarioAlteracaoId;
    this.linkExterno = linkExterno;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getTarefaId() {
    return tarefaId;
  }
  
  public int getDepartamentoId() {
    return departamentoId;
  }
  
  public int getUsuarioId() {
    return usuarioId;
  }
  
  public int getAssuntoId() {
    return assuntoId;
  }
  
  public int getPrioridade() {
    return prioridade;
  }
  
  public int getClienteId() {
    return clienteId;
  }

  public Timestamp getPrazoOriginal() {
    return prazoOriginal;
  }

  public Timestamp getPrazo() {
    return prazo;
  }
  
  public int getProrrogacao() {
    return prorrogacao;
  }
  
  public int getStatus() {
    return status;
  }
  
  public String getDescricao() {
    return descricao;
  }
  
  public Timestamp getDataInclusao() {
    return dataInclusao;
  }
  
  public int getUsuarioInclusaoId() {
    return usuarioInclusaoId;
  }
  
  public Timestamp getDataHoraAlteracao() {
    return dataHoraAlteracao;
  }
  
  public int getUsuarioAlteracaoId() {
    return usuarioAlteracaoId;
  }

  public String getLinkExterno() {
    return linkExterno;
  }
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setTarefaId(int tarefaId) {
    this.tarefaId = tarefaId;
  }
  
  public void setDepartamentoId(int departamentoId) {
    this.departamentoId = departamentoId;
  }
  
  public void setUsuarioId(int usuarioId) {
    this.usuarioId = usuarioId;
  }
  
  public void setAssuntoId(int assuntoId) {
    this.assuntoId = assuntoId;
  }
  
  public void setPrioridade(int prioridade) {
    this.prioridade = prioridade;
  }
  
  public void setClienteId(int clienteId) {
    this.clienteId = clienteId;
  }

  public void setPrazoOriginal(Timestamp prazoOriginal) {
    this.prazoOriginal = prazoOriginal;
  }

  public void setPrazo(Timestamp prazo) {
    this.prazo = prazo;
  }
  
  public void setProrrogacao(int prorrogacao) {
    this.prorrogacao = prorrogacao;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public void setDataInclusao(Timestamp dataInclusao) {
    this.dataInclusao = dataInclusao;
  }
  
  public void setUsuarioInclusaoId(int usuarioInclusaoId) {
    this.usuarioInclusaoId = usuarioInclusaoId;
  }
  
  public void setDataHoraAlteracao(Timestamp dataHoraAlteracao) {
    this.dataHoraAlteracao = dataHoraAlteracao;
  }
  
  public void setUsuarioAlteracaoId(int usuarioAlteracaoId) {
    this.usuarioAlteracaoId = usuarioAlteracaoId;
  }

  public void setLinkExterno(String linkExterno) {
    this.linkExterno = linkExterno;
  }
  
}
