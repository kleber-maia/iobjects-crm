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
 * Representa as informações contidas pela entidade CampanhaAutomatizada.
 */
public class CampanhaAutomatizadaInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private int campanhaId = 0;  
  private int campanhaAutomatizadaId = 0;  
  private String assunto = "";  
  private String mensagem = "";  
  private int diasEnvio = 0;  
  private String nomeRemetente = "";
  private String emailRemetente = "";

  /**
   * Construtor padrão.
   */
  public CampanhaAutomatizadaInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param campanhaId int Campanha ID.
   * @param campanhaAutomatizadaId int Campanha Automatizada ID.
   * @param assunto String Assunto.
   * @param mensagem String Mensagem.
   * @param diasEnvio int Dias Envio.
   * @param nomeRemetente String Nome do Remetente.
   * @param emailRemetente String Email do Remetente.
   */
  public CampanhaAutomatizadaInfo(
           int empresaId,
           int campanhaId,
           int campanhaAutomatizadaId,
           String assunto,
           String mensagem,
           int diasEnvio,
           String nomeRemetente,
           String emailRemetente
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.campanhaId = campanhaId;
    this.campanhaAutomatizadaId = campanhaAutomatizadaId;
    this.assunto = assunto;
    this.mensagem = mensagem;
    this.diasEnvio = diasEnvio;
    this.nomeRemetente = nomeRemetente;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getCampanhaId() {
    return campanhaId;
  }
  
  public int getCampanhaAutomatizadaId() {
    return campanhaAutomatizadaId;
  }
  
  public String getAssunto() {
    return assunto;
  }
  
  public String getMensagem() {
    return mensagem;
  }
  
  public int getDiasEnvio() {
    return diasEnvio;
  }
  
  public String getNomeRemetente() {
    return nomeRemetente;
  }
  
  public String getEmailRemetente() {
    return emailRemetente;
  }
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setCampanhaId(int campanhaId) {
    this.campanhaId = campanhaId;
  }
  
  public void setCampanhaAutomatizadaId(int campanhaAutomatizadaId) {
    this.campanhaAutomatizadaId = campanhaAutomatizadaId;
  }
  
  public void setAssunto(String assunto) {
    this.assunto = assunto;
  }
  
  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
  
  public void setDiasEnvio(int diasEnvio) {
    this.diasEnvio = diasEnvio;
  }
  
  public void setNomeRemetente(String nomeRemetente) {
    this.nomeRemetente = nomeRemetente;
  }
  
  public void setEmailRemetente(String emailRemetente) {
    this.emailRemetente = emailRemetente;
  }
  
}
