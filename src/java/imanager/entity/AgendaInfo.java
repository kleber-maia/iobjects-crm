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
 * Representa as informações contidas pela entidade Agenda.
 */
public class AgendaInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private int agendaId = 0;  
  private String nome = "";  
  private String tabelaHorario = "";
  private int intervalo = 0;

  /**
   * Construtor padrão.
   */
  public AgendaInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param agendaId int Agenda ID.
   * @param nome String Nome.
   * @param tabelaHorario String Tabela Horario.
   */
  public AgendaInfo(
           int empresaId,
           int agendaId,
           String nome,
           String tabelaHorario,
           int intervalo
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.agendaId = agendaId;
    this.nome = nome;
    this.tabelaHorario = tabelaHorario;
    this.intervalo = intervalo;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getAgendaId() {
    return agendaId;
  }
  
  public String getNome() {
    return nome;
  }
  
  public String getTabelaHorario() {
    return tabelaHorario;
  }

  public int getIntervalo() {
    return intervalo;
  }
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setAgendaId(int agendaId) {
    this.agendaId = agendaId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public void setTabelaHorario(String tabelaHorario) {
    this.tabelaHorario = tabelaHorario;
  }

  public void setIntervalo(int intervalo) {
    this.intervalo = intervalo;
  }
  
}
