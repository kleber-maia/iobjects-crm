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

import imanager.misc.*;

/**
 * Representa o cartão de Contato Aniversariante.
 */
public class CartaoAgendaFeriado extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAgendaFeriado";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoAgendaFeriado", "Feriado", "Exibe os proximos feriados.", HELP, "card/cartaoagendaferiado.jsp", "Contato", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padrão.
   */
  public CartaoAgendaFeriado() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os Feriados dos proximos 30 Dias.
   * @param empresaId Empresa ID.
   * <ol>
   *   <li>da_feriado
   *   <li>va_nome
   *   <li>sm_bloqueio
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Contatos que realizam
   *         aniversário ou aniversário de cadastro nos próximos 7 dias.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetCartaoAgendaFeriado(int empresaId) throws Exception { 
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT da_feriado, va_nome, sm_bloqueio "
                  + "FROM relacionamento_agenda_feriado "
                  + "WHERE "
                  + "((in_empresa_id = ?) OR (in_empresa_id = 0)) "
                  + "AND da_feriado <= ? "
                  + "AND da_feriado >= ?";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setTimestamp(2, new Timestamp(DateTools.getCalculatedDays(DateTools.getActualDate(), 30).getTime())); 
      preparedStatement.setTimestamp(3, new Timestamp(DateTools.getActualDate().getTime()));
      // executa
      preparedStatement.execute();
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      return preparedStatement.getResultSet();
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

}
