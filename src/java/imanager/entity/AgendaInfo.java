    
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
