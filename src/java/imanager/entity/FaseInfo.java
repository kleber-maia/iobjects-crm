    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Fase.
 */
public class FaseInfo extends EntityInfo {
  
  private int faseId = 0;  
  private String nome = "";  
  private String descricao = "";  
  private int diasAcompanhamento = 0;  
  private int percentualSucesso = 0;  

  /**
   * Construtor padrão.
   */
  public FaseInfo() {
  }

  /**
   * Construtor estendido.
   * @param faseId int Fase ID.
   * @param nome String Nome.
   * @param descricao String Descrição.
   * @param diasAcompanhamento int Dias Acompanhamento.
   * @param percentualSucesso int Percentual Sucesso.
   */
  public FaseInfo(
           int faseId,
           String nome,
           String descricao,
           int diasAcompanhamento,
           int percentualSucesso
         ) {
    // guarda nossos dados
    this.faseId = faseId;
    this.nome = nome;
    this.descricao = descricao;
    this.diasAcompanhamento = diasAcompanhamento;
    this.percentualSucesso = percentualSucesso;
  }
  
  public int getFaseId() {
    return faseId;
  }
  
  public String getNome() {
    return nome;
  }
  
  public String getDescricao() {
    return descricao;
  }
  
  public int getDiasAcompanhamento() {
    return diasAcompanhamento;
  }
  
  public int getPercentualSucesso() {
    return percentualSucesso;
  }
     
  public void setFaseId(int faseId) {
    this.faseId = faseId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public void setDiasAcompanhamento(int diasAcompanhamento) {
    this.diasAcompanhamento = diasAcompanhamento;
  }
  
  public void setPercentualSucesso(int percentualSucesso) {
    this.percentualSucesso = percentualSucesso;
  }
  
}
