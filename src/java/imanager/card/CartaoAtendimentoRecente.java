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
   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.entity.*;
import imanager.misc.*;
import iobjects.entity.Paginate; 

/**
 * Representa o cart�o de Atendimento Recente.
 */
public class CartaoAtendimentoRecente extends Card {

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAtendimentoRecente";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION = new Action("cartaoAtendimentoRecente", "Atendimentos Recentes", "Exibe os Atendimentos Recentes do Usu�rio.", HELP, "card/cartaoatendimentorecente.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padr�o.
   */
  public CartaoAtendimentoRecente() {
    // nossas a��es
    actionList().add(ACTION);
  }

  /**
   * Retorna um AtendimentoInfo[] referente aos Atendimentos Recentes identificados
   * pela Empresa e Usu�rio.
   * @return Retorna um AtendimentoInfo[] referente aos Atendimentos Recentes
   *         identificados pela Empresa e Usu�rio.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public AtendimentoInfo[] getAtendimentoRecente(int empresaId, int usuarioId) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // nossa inst�ncia de Atendimento
      Atendimento atendimento = (Atendimento)getFacade().getEntity(Atendimento.CLASS_NAME);
      // prepara a consulta
      String[] orderFieldNames = {Atendimento.FIELD_ATENDIMENTO_ID.getFieldName(atendimento.getTableName())};
      PreparedStatement statement = atendimento.prepareSelect(
                                                              orderFieldNames,
                                                              "(" + Atendimento.FIELD_EMPRESA_ID.getFieldName(atendimento.getTableName()) + " = ?) AND " +
                                                              "(" + Atendimento.FIELD_USUARIO_INCLUSAO_ID.getFieldName(atendimento.getTableName()) + " = ?) ",
                                                              new String[0],
                                                              new Paginate(20, 0)
                                                            );
      statement.setInt(1, empresaId);
      statement.setInt(2, usuarioId);
      // nosso resultado
      AtendimentoInfo[] result = (AtendimentoInfo[])atendimento.select(statement);
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      return result;
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

}
