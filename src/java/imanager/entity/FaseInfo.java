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
 * Representa as informações contidas pela entidade Fase.
 */
public class FaseInfo extends EntityInfo {
  
  private int faseId = 0;  
  private String nome = "";  
  private String descricao = "";  
  private int diasAcompanhamento = 0;  
  private int percentualSucesso = 0;  

  /**
   * Construtor padrão.
   */
  public FaseInfo() {
  }

  /**
   * Construtor estendido.
   * @param faseId int Fase ID.
   * @param nome String Nome.
   * @param descricao String Descrição.
   * @param diasAcompanhamento int Dias Acompanhamento.
   * @param percentualSucesso int Percentual Sucesso.
   */
  public FaseInfo(
           int faseId,
           String nome,
           String descricao,
           int diasAcompanhamento,
           int percentualSucesso
         ) {
    // guarda nossos dados
    this.faseId = faseId;
    this.nome = nome;
    this.descricao = descricao;
    this.diasAcompanhamento = diasAcompanhamento;
    this.percentualSucesso = percentualSucesso;
  }
  
  public int getFaseId() {
    return faseId;
  }
  
  public String getNome() {
    return nome;
  }
  
  public String getDescricao() {
    return descricao;
  }
  
  public int getDiasAcompanhamento() {
    return diasAcompanhamento;
  }
  
  public int getPercentualSucesso() {
    return percentualSucesso;
  }
     
  public void setFaseId(int faseId) {
    this.faseId = faseId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public void setDiasAcompanhamento(int diasAcompanhamento) {
    this.diasAcompanhamento = diasAcompanhamento;
  }
  
  public void setPercentualSucesso(int percentualSucesso) {
    this.percentualSucesso = percentualSucesso;
  }
  
}
