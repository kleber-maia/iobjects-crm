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
 * Representa as informa��es contidas pela entidade Campanha.
 */
public class CampanhaInfo extends EntityInfo {
  
  private int campanhaId = 0;  
  private String nome = "";  
  private String descricao = "";  
  private int arquivo = 0;  
  private Timestamp dataInicio = DateTools.ZERO_DATE;
  private Timestamp dataTermino = DateTools.ZERO_DATE;
  private double valorInvestido = 0.0D;
  private int automatizada = 0;  
  private int diasPerdida = 0;
  private int usuarioInclusaoId = 0;
  private Timestamp dataInclusao = DateTools.ZERO_DATE;
  private int usuarioAlteracaoId = 0;
  private Timestamp dataHoraAlteracao = DateTools.ZERO_DATE;
  

  /**
   * Construtor padr�o.
   */
  public CampanhaInfo() {
  }

  /**
   * Construtor estendido.
   * @param campanhaId int Campanha ID.
   * @param nome String Nome.
   * @param descricao String Descri��o.
   * @param arquivo int Arquivo.
   * @param dataInicio Timestamp Data Inicio.
   * @param dataTermino Timestamp Data Termino.
   * @param valorInvestido double Valor Investido.
   * @param automatizada int Automatizada.
   * @param diasPerdida int Dias Perdida.
   * @param usuarioInclusaoId int Usu�rio Inclus�o ID.
   * @param dataInclusao Timestamp Data Inclus�o.
   * @param usuarioAlteracaoId int Usu�rio Altera��o ID.
   * @param dataHoraAlteracao Timestamp Data Hora Altera��o.
   */
  public CampanhaInfo(
           int campanhaId,
           String nome,
           String descricao,
           int arquivo,
           Timestamp dataInicio,
           Timestamp dataTermino,
           double valorInvestido,
           int automatizada,
           int diasPerdida,
           int usuarioInclusaoId,
           Timestamp dataInclusao,
           int usuarioAlteracaoId,
           Timestamp dataHoraAlteracao           
         ) {
    // guarda nossos dados
    this.campanhaId = campanhaId;
    this.nome = nome;
    this.descricao = descricao;
    this.arquivo = arquivo;
    this.dataInicio = dataInicio;
    this.dataTermino = dataTermino;
    this.valorInvestido = valorInvestido;
    this.automatizada = automatizada;
    this.diasPerdida = diasPerdida;
    this.usuarioInclusaoId = usuarioInclusaoId;
    this.dataInclusao = dataInclusao;
    this.usuarioAlteracaoId = usuarioAlteracaoId;
    this.dataHoraAlteracao = dataHoraAlteracao;    
  }
  
  public int getCampanhaId() {
    return campanhaId;
  }
  
  public String getNome() {
    return nome;
  }
  
  public String getDescricao() {
    return descricao;
  }
  
  public int getArquivo() {
    return arquivo;
  }
  
  public Timestamp getDataInicio() {
    return dataInicio;
  }
  
  public Timestamp getDataTermino() {
    return dataTermino;
  }  
  
  public double getValorInvestido() {
    return valorInvestido;
  }
  
  public int getAutomatizada() {
    return automatizada;
  }
  
  public int getDiasPerdida() {
    return diasPerdida;
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
     
  public void setCampanhaId(int campanhaId) {
    this.campanhaId = campanhaId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public void setArquivo(int arquivo) {
    this.arquivo = arquivo;
  }
  
  public void setDataInicio(Timestamp dataInicio) {
    this.dataInicio = dataInicio;
  }
  
  public void setDataTermino(Timestamp dataTermino) {
    this.dataTermino = dataTermino;
  }  
  
  public void setValorInvestido(double valorInvestido) {
    this.valorInvestido = valorInvestido;
  }
  
  public void setAutomatizada(int automatizada) {
    this.automatizada = automatizada;
  }
  
  public void setDiasPerdida(int diasPerdida) {
    this.diasPerdida = diasPerdida;
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
  
}
