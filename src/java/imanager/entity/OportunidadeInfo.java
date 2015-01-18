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
 * Representa as informações contidas pela entidade Oportunidade.
 */
public class OportunidadeInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private int oportunidadeId = 0;  
  private int departamentoId = 0;  
  private int usuarioId = 0;  
  private int campanhaId = 0;  
  private int clienteId = 0;  
  private int faseId = 0;  
  private Timestamp dataAcompanhamento = DateTools.ZERO_DATE;  
  private int percentualSucesso = 0;  
  private int status = 0;  
  private double valor = 0.0D;  
  private String descricao = "";  
  private int usuarioInclusaoId = 0;  
  private Timestamp dataInclusao = DateTools.ZERO_DATE;  
  private int usuarioAlteracaoId = 0;  
  private Timestamp dataHoraAlteracao = DateTools.ZERO_DATE;  
  private String linkExterno = "";

  /**
   * Construtor padrão.
   */
  public OportunidadeInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param oportunidadeId int Oportunidade ID.
   * @param departamentoId int Departamento ID.
   * @param usuarioId int Usuário ID.
   * @param campanhaId int Campanha ID.
   * @param clienteId int Cliente ID.
   * @param faseId int Fase ID.
   * @param dataAcompanhamento Timestamp Data Acompanhamento.
   * @param percentualSucesso int Percentual Sucesso.
   * @param status int Status.
   * @param valor double Valor.
   * @param descricao String Descrição.
   * @param usuarioInclusaoId int Usuário Inclusão ID.
   * @param dataInclusao Timestamp Data Inclusão.
   * @param usuarioAlteracaoId int Usuário Alteração ID.
   * @param dataHoraAlteracao Timestamp Data Hora Alteração.
   * @param linkExterno String Link Externo.
   */
  public OportunidadeInfo(
           int empresaId,
           int oportunidadeId,
           int departamentoId,
           int usuarioId,
           int campanhaId,
           int clienteId,
           int faseId,
           Timestamp dataAcompanhamento,
           int percentualSucesso,
           int status,
           double valor,
           String descricao,
           int usuarioInclusaoId,
           Timestamp dataInclusao,
           int usuarioAlteracaoId,
           Timestamp dataHoraAlteracao,
           String linkExterno
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.oportunidadeId = oportunidadeId;
    this.departamentoId = departamentoId;
    this.usuarioId = usuarioId;
    this.campanhaId = campanhaId;
    this.clienteId = clienteId;
    this.faseId = faseId;
    this.dataAcompanhamento = dataAcompanhamento;
    this.percentualSucesso = percentualSucesso;
    this.status = status;
    this.valor = valor;
    this.descricao = descricao;
    this.usuarioInclusaoId = usuarioInclusaoId;
    this.dataInclusao = dataInclusao;
    this.usuarioAlteracaoId = usuarioAlteracaoId;
    this.dataHoraAlteracao = dataHoraAlteracao;
    this.linkExterno = linkExterno;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getOportunidadeId() {
    return oportunidadeId;
  }
  
  public int getDepartamentoId() {
    return departamentoId;
  }
  
  public int getUsuarioId() {
    return usuarioId;
  }
  
  public int getCampanhaId() {
    return campanhaId;
  }
  
  public int getClienteId() {
    return clienteId;
  }
  
  public int getFaseId() {
    return faseId;
  }
  
  public Timestamp getDataAcompanhamento() {
    return dataAcompanhamento;
  }
  
  public int getPercentualSucesso() {
    return percentualSucesso;
  }
  
  public int getStatus() {
    return status;
  }
  
  public double getValor() {
    return valor;
  }
  
  public String getDescricao() {
    return descricao;
  }
  
  public int getUsuarioInclusaoId() {
    return usuarioInclusaoId;
  }
  
  public Timestamp getDataInclusao() {
    return dataInclusao;
  }
  
  public int getUsuarioAlteracaoId() {
    return usuarioAlteracaoId;
  }

  public Timestamp getDataHoraAlteracao() {
    return dataHoraAlteracao;
  }

  public String getLinkExterno() {
    return linkExterno;
  }

  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setOportunidadeId(int oportunidadeId) {
    this.oportunidadeId = oportunidadeId;
  }
  
  public void setDepartamentoId(int departamentoId) {
    this.departamentoId = departamentoId;
  }
  
  public void setUsuarioId(int usuarioId) {
    this.usuarioId = usuarioId;
  }
  
  public void setCampanhaId(int campanhaId) {
    this.campanhaId = campanhaId;
  }
  
  public void setClienteId(int clienteId) {
    this.clienteId = clienteId;
  }
  
  public void setFaseId(int faseId) {
    this.faseId = faseId;
  }
  
  public void setDataAcompanhamento(Timestamp dataAcompanhamento) {
    this.dataAcompanhamento = dataAcompanhamento;
  }
  
  public void setPercentualSucesso(int percentualSucesso) {
    this.percentualSucesso = percentualSucesso;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public void setValor(double valor) {
    this.valor = valor;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public void setUsuarioInclusaoId(int usuarioInclusaoId) {
    this.usuarioInclusaoId = usuarioInclusaoId;
  }
  
  public void setDataInclusao(Timestamp dataInclusao) {
    this.dataInclusao = dataInclusao;
  }
  
  public void setUsuarioAlteracaoId(int usuarioAlteracaoId) {
    this.usuarioAlteracaoId = usuarioAlteracaoId;
  }
  
  public void setDataHoraAlteracao(Timestamp dataHoraAlteracao) {
    this.dataHoraAlteracao = dataHoraAlteracao;
  }

  public void setLinkExterno(String linkExterno) {
    this.linkExterno = linkExterno;
  }

}
