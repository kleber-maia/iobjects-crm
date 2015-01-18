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
 * Representa as informações contidas pela entidade Contato Meta.
 */
public class ContatoMetaInfo extends EntityInfo {
  
  private int empresaId = 0;
  private int contatoId = 0;  
  private int ano = 0;  
  private int mes = 0;  
  private double metaVenda = 0.0D;  
  private double metaServico = 0.0D;  

  /**
   * Construtor padrão.
   */
  public ContatoMetaInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param contatoId int Contato ID.
   * @param ano int Ano.
   * @param mes int Mês.
   * @param metaVenda double Meta.
   */
  public ContatoMetaInfo(
           int empresaId,
           int contatoId,
           int ano,
           int mes,
           double metaVenda,
           double metaServico
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.contatoId = contatoId;
    this.ano = ano;
    this.mes = mes;
    this.metaVenda = metaVenda;
    this.metaServico = metaServico;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }

  public int getContatoId() {
    return contatoId;
  }
  
  public int getAno() {
    return ano;
  }
  
  public int getMes() {
    return mes;
  }
  
  public double getMetaVenda() {
    return metaVenda;
  }
  
  public double getMetaServico() {
    return metaServico;
  }  
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }

  public void setContatoId(int contatoId) {
    this.contatoId = contatoId;
  }
  
  public void setAno(int ano) {
    this.ano = ano;
  }
  
  public void setMes(int mes) {
    this.mes = mes;
  }
  
  public void setMetaVenda(double metaVenda) {
    this.metaVenda = metaVenda;
  }

  public void setMetaServico(double metaServico) {
    this.metaServico = metaServico;
  }  
  
}
