    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*; 
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Assunto.
 */
public class AssuntoInfo extends EntityInfo {
  
  private int assuntoId = 0;  
  private String nome = "";  

  /**
   * Construtor padrão.
   */
  public AssuntoInfo() {
  }

  /**
   * Construtor estendido.
   * @param assuntoId int Assunto ID.
   * @param nome String Nome.
   */
  public AssuntoInfo(
           int assuntoId,
           String nome
         ) {
    // guarda nossos dados
    this.assuntoId = assuntoId;
    this.nome = nome;
  }
  
  public int getAssuntoId() {
    return assuntoId;
  }
  
  public String getNome() {
    return nome;
  }
     
  public void setAssuntoId(int assuntoId) {
    this.assuntoId = assuntoId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
}
