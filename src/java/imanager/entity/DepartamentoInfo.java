    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Departamento.
 */
public class DepartamentoInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private int departamentoId = 0;  
  private String nome = "";  

  /**
   * Construtor padrão.
   */
  public DepartamentoInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param departamentoId int Departamento ID.
   * @param nome String Nome.
   */
  public DepartamentoInfo(
           int empresaId,
           int departamentoId,
           String nome
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.departamentoId = departamentoId;
    this.nome = nome;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getDepartamentoId() {
    return departamentoId;
  }
  
  public String getNome() {
    return nome;
  }
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setDepartamentoId(int departamentoId) {
    this.departamentoId = departamentoId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
}
