    
package dne.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informações contidas pela entidade Logradouro.
 */
public class LogradouroInfo extends EntityInfo {
  
  private String cep = "";  
  private String uf = "";  
  private String municipio = "";  
  private String bairro = "";  
  private String endereco = "";  

  /**
   * Construtor padrão.
   */
  public LogradouroInfo() {
  }

  /**
   * Construtor estendido.
   * @param cep String CEP.
   * @param uf String UF.
   * @param municipio String Município.
   * @param bairro String Bairro.
   * @param endereco String Endereço.
   */
  public LogradouroInfo(
           String cep,
           String uf,
           String municipio,
           String bairro,
           String endereco
         ) {
    // guarda nossos dados
    this.cep = cep;
    this.uf = uf;
    this.municipio = municipio;
    this.bairro = bairro;
    this.endereco = endereco;
  }
  
  public String getCep() {
    return cep;
  }
  
  public String getUf() {
    return uf;
  }
  
  public String getMunicipio() {
    return municipio;
  }
  
  public String getBairro() {
    return bairro;
  }
  
  public String getEndereco() {
    return endereco;
  }
     
  public void setCep(String cep) {
    this.cep = cep;
  }
  
  public void setUf(String uf) {
    this.uf = uf;
  }
  
  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }
  
  public void setBairro(String bairro) {
    this.bairro = bairro;
  }
  
  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }
  
}
