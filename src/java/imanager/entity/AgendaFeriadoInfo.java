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
 * Representa as informações contidas pela entidade AgendaFeriado.
 */
public class AgendaFeriadoInfo extends EntityInfo {
  
  private int agendaFeriadoId = 0;  
  private Timestamp feriado = DateTools.ZERO_DATE;  
  private String nome = "";  
  private int bloqueio = 0;  
  private int empresaId = 0;  
  private int agendaId = 0;  

  /**
   * Construtor padrão.
   */
  public AgendaFeriadoInfo() {
  }

  /**
   * Construtor estendido.
   * @param agendaFeriadoId int Agenda Feriado ID.
   * @param feriado Timestamp Feriado.
   * @param nome String Nome.
   * @param bloqueio int Bloqueio.
   * @param empresaId int Empresa ID.
   * @param agendaId int Agenda ID.
   */
  public AgendaFeriadoInfo(
           int agendaFeriadoId,
           Timestamp feriado,
           String nome,
           int bloqueio,
           int empresaId,
           int agendaId
         ) {
    // guarda nossos dados
    this.agendaFeriadoId = agendaFeriadoId;
    this.feriado = feriado;
    this.nome = nome;
    this.bloqueio = bloqueio;
    this.empresaId = empresaId;
    this.agendaId = agendaId;
  }
  
  public int getAgendaFeriadoId() {
    return agendaFeriadoId;
  }
  
  public Timestamp getFeriado() {
    return feriado;
  }
  
  public String getNome() {
    return nome;
  }
  
  public int getBloqueio() {
    return bloqueio;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getAgendaId() {
    return agendaId;
  }
     
  public void setAgendaFeriadoId(int agendaFeriadoId) {
    this.agendaFeriadoId = agendaFeriadoId;
  }
  
  public void setFeriado(Timestamp feriado) {
    this.feriado = feriado;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public void setBloqueio(int bloqueio) {
    this.bloqueio = bloqueio;
  }
  
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setAgendaId(int agendaId) {
    this.agendaId = agendaId;
  }
  
}
