    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Tarefa Encaminhamento.
 */
public class TarefaEncaminhamentoInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private int tarefaId = 0;  
  private int tarefaEncaminhamentoId = 0;  
  private int departamentoId = 0;  
  private int usuarioId = 0;  
  private String descricao = "";  
  private int usuarioInclusaoId = 0;  
  private Timestamp dataHoraInclusao = DateTools.ZERO_DATE;  

  /**
   * Construtor padrão.
   */
  public TarefaEncaminhamentoInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param tarefaId int Tarefa ID.
   * @param tarefaEncaminhamentoId int Tarefa Encaminhamento ID.
   * @param departamentoId int Departamento ID.
   * @param usuarioId int Usuário ID.
   * @param descricao String Descrição.
   * @param usuarioInclusaoId int Usuário Inclusão ID.
   * @param dataHoraInclusao Timestamp Data Hora Inclusão.
   */
  public TarefaEncaminhamentoInfo(
           int empresaId,
           int tarefaId,
           int tarefaEncaminhamentoId,
           int departamentoId,
           int usuarioId,
           String descricao,
           int usuarioInclusaoId,
           Timestamp dataHoraInclusao
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.tarefaId = tarefaId;
    this.tarefaEncaminhamentoId = tarefaEncaminhamentoId;
    this.departamentoId = departamentoId;
    this.usuarioId = usuarioId;
    this.descricao = descricao;
    this.usuarioInclusaoId = usuarioInclusaoId;
    this.dataHoraInclusao = dataHoraInclusao;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getTarefaId() {
    return tarefaId;
  }
  
  public int getTarefaEncaminhamentoId() {
    return tarefaEncaminhamentoId;
  }
  
  public int getDepartamentoId() {
    return departamentoId;
  }
  
  public int getUsuarioId() {
    return usuarioId;
  }
  
  public String getDescricao() {
    return descricao;
  }
  
  public int getUsuarioInclusaoId() {
    return usuarioInclusaoId;
  }
  
  public Timestamp getDataHoraInclusao() {
    return dataHoraInclusao;
  }
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setTarefaId(int tarefaId) {
    this.tarefaId = tarefaId;
  }
  
  public void setTarefaEncaminhamentoId(int tarefaEncaminhamentoId) {
    this.tarefaEncaminhamentoId = tarefaEncaminhamentoId;
  }
  
  public void setDepartamentoId(int departamentoId) {
    this.departamentoId = departamentoId;
  }
  
  public void setUsuarioId(int usuarioId) {
    this.usuarioId = usuarioId;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public void setUsuarioInclusaoId(int usuarioInclusaoId) {
    this.usuarioInclusaoId = usuarioInclusaoId;
  }
  
  public void setDataHoraInclusao(Timestamp dataHoraInclusao) {
    this.dataHoraInclusao = dataHoraInclusao;
  }
  
}
