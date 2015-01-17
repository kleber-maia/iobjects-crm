    
package dne.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informa��es contidas pela entidade Munic�pio.
 */
public class MunicipioInfo extends EntityInfo {
  
  private String codigoMunicipio = "";  
  private String municipio = "";  
  private String codigoUf = "";  
  private String uf = "";  

  /**
   * Construtor padr�o.
   */
  public MunicipioInfo() {
  }

  /**
   * Construtor estendido.
   * @param codigoMunicipio String C�digo Munic�pio.
   * @param municipio String Munic�pio.
   * @param codigoUf String C�digo UF.
   * @param uf String UF.
   */
  public MunicipioInfo(
           String codigoMunicipio,
           String municipio,
           String codigoUf,
           String uf
         ) {
    // guarda nossos dados
    this.codigoMunicipio = codigoMunicipio;
    this.municipio = municipio;
    this.codigoUf = codigoUf;
    this.uf = uf;
  }
  
  public String getCodigoMunicipio() {
    return codigoMunicipio;
  }
  
  public String getMunicipio() {
    return municipio;
  }
  
  public String getCodigoUf() {
    return codigoUf;
  }
  
  public String getUf() {
    return uf;
  }
     
  public void setCodigoMunicipio(String codigoMunicipio) {
    this.codigoMunicipio = codigoMunicipio;
  }
  
  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }
  
  public void setCodigoUf(String codigoUf) {
    this.codigoUf = codigoUf;
  }
  
  public void setUf(String uf) {
    this.uf = uf;
  }
  
}
