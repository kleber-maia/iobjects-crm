/*
The MIT License (MIT)

Copyright (c) 2008 Kleber Maia de Andrade

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/   
    
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
