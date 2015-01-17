    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Grupo Contato.
 */
public class GrupoContatoInfo extends EntityInfo {
  
  private int grupoContatoId = 0;   
  private String nome = "";  

  /**
   * Construtor padrão.
   */
  public GrupoContatoInfo() {
  }

  /**
   * Construtor estendido.
   * @param grupoContatoId int Grupo Contato ID.
   * @param nome String Nome.
   */
  public GrupoContatoInfo(
           int grupoContatoId,
           String nome
         ) {
    // guarda nossos dados
    this.grupoContatoId = grupoContatoId;
    this.nome = nome;
  }
  
  public int getGrupoContatoId() {
    return grupoContatoId;
  }
  
  public String getNome() {
    return nome;
  }
     
  public void setGrupoContatoId(int grupoContatoId) {
    this.grupoContatoId = grupoContatoId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
}
