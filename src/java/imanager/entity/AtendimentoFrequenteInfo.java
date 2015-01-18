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
 * Representa as informa��es contidas pela entidade AtendimentoFrequente.
 */
public class AtendimentoFrequenteInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private int atendimentoFrequenteId = 0;  
  private String nome = "";  
  private int departamentoId = 0;  
  private int assuntoId = 0;  
  private String descricao = "";  

  /**
   * Construtor padr�o.
   */
  public AtendimentoFrequenteInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param atendimentoFrequenteId int Atendimento Frequente ID.
   * @param nome String Nome.
   * @param departamentoId int Departamento ID.
   * @param assuntoId int Assunto ID.
   * @param descricao String Descri��o.
   */
  public AtendimentoFrequenteInfo(
           int empresaId,
           int atendimentoFrequenteId,
           String nome,
           int departamentoId,
           int assuntoId,
           String descricao
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.atendimentoFrequenteId = atendimentoFrequenteId;
    this.nome = nome;
    this.departamentoId = departamentoId;
    this.assuntoId = assuntoId;
    this.descricao = descricao;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getAtendimentoFrequenteId() {
    return atendimentoFrequenteId;
  }
  
  public String getNome() {
    return nome;
  }
  
  public int getDepartamentoId() {
    return departamentoId;
  }
  
  public int getAssuntoId() {
    return assuntoId;
  }
  
  public String getDescricao() {
    return descricao;
  }
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setAtendimentoFrequenteId(int atendimentoFrequenteId) {
    this.atendimentoFrequenteId = atendimentoFrequenteId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public void setDepartamentoId(int departamentoId) {
    this.departamentoId = departamentoId;
  }
  
  public void setAssuntoId(int assuntoId) {
    this.assuntoId = assuntoId;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
}
