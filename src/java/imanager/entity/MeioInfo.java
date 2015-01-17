    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Meio.
 */
public class MeioInfo extends EntityInfo {
  
  private int meioId = 0;  
  private String nome = "";  

  /**
   * Construtor padrão.
   */
  public MeioInfo() {
  }

  /**
   * Construtor estendido.
   * @param meioId int Meio ID.
   * @param nome String Nome.
   */
  public MeioInfo(
           int meioId,
           String nome
         ) {
    // guarda nossos dados
    this.meioId = meioId;
    this.nome = nome;
  }
  
  public int getMeioId() {
    return meioId;
  }
  
  public String getNome() {
    return nome;
  }
     
  public void setMeioId(int meioId) {
    this.meioId = meioId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
}
