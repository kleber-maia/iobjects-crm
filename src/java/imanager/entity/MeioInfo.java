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
    
package imanager.entity;

import java.sql.*;

import iobjects.entity.*;
import iobjects.util.*;

/**
 * Representa as informa��es contidas pela entidade Meio.
 */
public class MeioInfo extends EntityInfo {
  
  private int meioId = 0;  
  private String nome = "";  

  /**
   * Construtor padr�o.
   */
  public MeioInfo() {
  }

  /**
   * Construtor estendido.
   * @param meioId int Meio ID.
   * @param nome String Nome.
   */
  public MeioInfo(
           int meioId,
           String nome
         ) {
    // guarda nossos dados
    this.meioId = meioId;
    this.nome = nome;
  }
  
  public int getMeioId() {
    return meioId;
  }
  
  public String getNome() {
    return nome;
  }
     
  public void setMeioId(int meioId) {
    this.meioId = meioId;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
}
