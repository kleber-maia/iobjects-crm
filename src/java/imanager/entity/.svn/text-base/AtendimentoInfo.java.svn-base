    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Atendimento.
 */
public class AtendimentoInfo extends EntityInfo {
  
  private int empresaId = 0;  
  private int atendimentoId = 0;  
  private int departamentoId = 0;  
  private int assuntoId = 0;  
  private int meioId = 0;  
  private int clienteId = 0;
  private int campanhaId = 0;
  private String descricao = "";  
  private Timestamp dataHoraInicio = DateTools.ZERO_DATE;  
  private Timestamp dataHoraTermino = DateTools.ZERO_DATE;  
  private Timestamp dataInclusao = DateTools.ZERO_DATE;
  private int usuarioInclusaoId = 0;  
  private Timestamp dataHoraAlteracao = DateTools.ZERO_DATE;  
  private int usuarioAlteracaoId = 0;
  private String linkExterno = "";

  /**
   * Construtor padrão.
   */
  public AtendimentoInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param atendimentoId int Atendimento ID.
   * @param departamentoId int Departamento ID.
   * @param assuntoId int Assunto ID.
   * @param meioId int Meio ID.
   * @param clienteId int Cliente ID.
   * @param campanhaId int Campanha ID.
   * @param descricao String Descrição.
   * @param dataHoraInicio Timestamp Data Hora Início.
   * @param dataHoraTermino Timestamp Data Hora Término.
   * @param usuarioInclusaoId int Usuário Inclusão ID.
   * @param dataHoraAlteracao Timestamp Data Hora Alteração.
   * @param usuarioAlteracaoId int Usuário Alteração ID.
   * @param dataInclusao Timestamp Data Inclusão.
   * @param linkExterno String Link Externo.
   */
  public AtendimentoInfo(
           int empresaId,
           int atendimentoId,
           int departamentoId,
           int assuntoId,
           int meioId,
           int clienteId,
           int campanhaId,
           String descricao,
           Timestamp dataHoraInicio,
           Timestamp dataHoraTermino,
           Timestamp dataInclusao,
           int usuarioInclusaoId,
           Timestamp dataHoraAlteracao,
           int usuarioAlteracaoId,
           String linkExterno
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.atendimentoId = atendimentoId;
    this.departamentoId = departamentoId;
    this.assuntoId = assuntoId;
    this.meioId = meioId;
    this.clienteId = clienteId;
    this.campanhaId = campanhaId;
    this.descricao = descricao;
    this.dataHoraInicio = dataHoraInicio;
    this.dataHoraTermino = dataHoraTermino;
    this.dataInclusao = dataInclusao;
    this.usuarioInclusaoId = usuarioInclusaoId;
    this.dataHoraAlteracao = dataHoraAlteracao;
    this.usuarioAlteracaoId = usuarioAlteracaoId;
    this.linkExterno = linkExterno;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }
  
  public int getAtendimentoId() {
    return atendimentoId;
  }
  
  public int getDepartamentoId() {
    return departamentoId;
  }
  
  public int getAssuntoId() {
    return assuntoId;
  }
  
  public int getMeioId() {
    return meioId;
  }
  
  public int getClienteId() {
    return clienteId;
  }

  public int getCampanhaId() {
    return campanhaId;
  }
  
  public String getDescricao() {
    return descricao;
  }
  
  public Timestamp getDataHoraInicio() {
    return dataHoraInicio;
  }
  
  public Timestamp getDataHoraTermino() {
    return dataHoraTermino;
  }
  
  public int getUsuarioInclusaoId() {
    return usuarioInclusaoId;
  }
  
  public Timestamp getDataHoraAlteracao() {
    return dataHoraAlteracao;
  }
  
  public int getUsuarioAlteracaoId() {
    return usuarioAlteracaoId;
  }
  
  public Timestamp getDataInclusao() {
    return dataInclusao;
  }

  public String getLinkExterno() {
    return linkExterno;
  }
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }
  
  public void setAtendimentoId(int atendimentoId) {
    this.atendimentoId = atendimentoId;
  }
  
  public void setDepartamentoId(int departamentoId) {
    this.departamentoId = departamentoId;
  }
  
  public void setAssuntoId(int assuntoId) {
    this.assuntoId = assuntoId;
  }
  
  public void setMeioId(int meioId) {
    this.meioId = meioId;
  }
  
  public void setClienteId(int clienteId) {
    this.clienteId = clienteId;
  }
  
  public void setCampanhaId(int campanhaId) {
    this.campanhaId = campanhaId;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public void setDataHoraInicio(Timestamp dataHoraInicio) {
    this.dataHoraInicio = dataHoraInicio;
  }
  
  public void setDataHoraTermino(Timestamp dataHoraTermino) {
    this.dataHoraTermino = dataHoraTermino;
  }
  
  public void setUsuarioInclusaoId(int usuarioInclusaoId) {
    this.usuarioInclusaoId = usuarioInclusaoId;
  }
  
  public void setDataHoraAlteracao(Timestamp dataHoraAlteracao) {
    this.dataHoraAlteracao = dataHoraAlteracao;
  }
  
  public void setUsuarioAlteracaoId(int usuarioAlteracaoId) {
    this.usuarioAlteracaoId = usuarioAlteracaoId;
  }
  
  public void setDataInclusao(Timestamp dataInclusao) {
    this.dataInclusao = dataInclusao;
  }

  public void setLinkExterno(String linkExterno) {
    this.linkExterno = linkExterno;
  }

}
