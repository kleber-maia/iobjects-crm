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
