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
 * Representa as informações contidas pela entidade OportunidadeHistorico.
 */
public class OportunidadeHistoricoInfo extends EntityInfo {
  
  private int empresaId = 0;
  private int oportunidadeId = 0;
  private int oportunidadeHistoricoId = 0;
  private int statusAnterior = 0;  
  private int status = 0;  
  private int usuarioInclusaoId = 0;
  private Timestamp dataHoraInclusao = DateTools.ZERO_DATE;  
  private String informacao = "";

  /**
   * Construtor padrão.
   */
  public OportunidadeHistoricoInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa Id.
   * @param oportunidadeId int Oportunidade Id .
   * @param oportunidadeHistoricoId int Oportunidade Histórico Id.
   * @param statusAnterior int Status Anterior.
   * @param status int Status.
   * @param usuarioInclusaoId int Usuário Inclusão Id.
   * @param dataHoraInclusao Timestamp Data Hora Inclusão.
   * @param informacao String Informação
   */
  public OportunidadeHistoricoInfo(
           int empresaId,
           int oportunidadeId,
           int oportunidadeHistoricoId,
           int statusAnterior,
           int status,
           int usuarioInclusaoId,
           Timestamp dataHoraInclusao,
           String informacao
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.oportunidadeId = oportunidadeId;
    this.oportunidadeHistoricoId = oportunidadeHistoricoId;
    this.statusAnterior = statusAnterior;
    this.status = status;
    this.usuarioInclusaoId = usuarioInclusaoId;
    this.dataHoraInclusao = dataHoraInclusao;
    this.informacao = informacao;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getOportunidadeId() {
    return oportunidadeId;
  }
  
  public int getOportunidadeHistoricoId() {
    return oportunidadeHistoricoId;
  }
  
  public int getStatusAnterior() {
    return statusAnterior;
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
  
  public String getInformacao() {
    return informacao;
  }
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setOportunidadeId(int oportunidadeId) {
    this.oportunidadeId = oportunidadeId;
  }
  
  public void setOportunidadeHistoricoId(int oportunidadeHistoricoId) {
    this.oportunidadeHistoricoId = oportunidadeHistoricoId;
  }
  
  public void setStatusAnterior(int statusAnterior) {
    this.statusAnterior = statusAnterior;
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
  
  public void setInformacao(String informacao) {
    this.informacao = informacao;
  }
  
}
