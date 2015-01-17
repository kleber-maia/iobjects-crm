    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Contato Meta.
 */
public class ContatoMetaInfo extends EntityInfo {
  
  private int empresaId = 0;
  private int contatoId = 0;  
  private int ano = 0;  
  private int mes = 0;  
  private double metaVenda = 0.0D;  
  private double metaServico = 0.0D;  

  /**
   * Construtor padrão.
   */
  public ContatoMetaInfo() {
  }

  /**
   * Construtor estendido.
   * @param empresaId int Empresa ID.
   * @param contatoId int Contato ID.
   * @param ano int Ano.
   * @param mes int Mês.
   * @param metaVenda double Meta.
   */
  public ContatoMetaInfo(
           int empresaId,
           int contatoId,
           int ano,
           int mes,
           double metaVenda,
           double metaServico
         ) {
    // guarda nossos dados
    this.empresaId = empresaId;
    this.contatoId = contatoId;
    this.ano = ano;
    this.mes = mes;
    this.metaVenda = metaVenda;
    this.metaServico = metaServico;
  }
  
  public int getEmpresaId() {
    return empresaId;
  }

  public int getContatoId() {
    return contatoId;
  }
  
  public int getAno() {
    return ano;
  }
  
  public int getMes() {
    return mes;
  }
  
  public double getMetaVenda() {
    return metaVenda;
  }
  
  public double getMetaServico() {
    return metaServico;
  }  
     
  public void setEmpresaId(int empresaId) {
    this.empresaId = empresaId;
  }

  public void setContatoId(int contatoId) {
    this.contatoId = contatoId;
  }
  
  public void setAno(int ano) {
    this.ano = ano;
  }
  
  public void setMes(int mes) {
    this.mes = mes;
  }
  
  public void setMetaVenda(double metaVenda) {
    this.metaVenda = metaVenda;
  }

  public void setMetaServico(double metaServico) {
    this.metaServico = metaServico;
  }  
  
}
