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
 * Representa o cart�o de Atendimento Campanha.
 */
public class CartaoAtendimentoCampanha extends Card {

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAtendimentoCampanha";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION = new Action("cartaoAtendimentoCampanha", "Atendimentos por Campanha", "Exibe o total de Atendimentos por Campanha.", HELP, "card/cartaoatendimentocampanha.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padr�o.
   */
  public CartaoAtendimentoCampanha() {
    // nossas a��es
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo as Campanhas e as quantidades de
   * Atendimentos no m�s.
   * <ol>
   *   <li>crm_campanha.in_campanha_id</li>
   *   <li>crm_campanha.va_nome</li>
   *   <li>crm_atendimento.in_atendimento_id</li>
   *   <li>cliente.va_nome</li>
   *   <li>cliente.va_nome</li>
   *   <li>cliente.va_nome</li>
   *   <li>cliente.va_nome</li>
   *   <li>cliente.va_nome</li>
   * </ol>
   * <b>Importante: a rotina que executar este m�todo deve ser respons�vel por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Departamentos e as
   *         quantidades de Atendimentos no m�s.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetCartaoAtendimentoCampanha(int empresaId) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "select crm_campanha.in_campanha_id, "
                 + "crm_campanha.va_nome,"
                 + "crm_atendimento.in_atendimento_id, "
                 + "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "crm_atendimento.dt_inicio, "
                 + "crm_atendimento.va_descricao "              
                 +  "from crm_atendimento "
                 +  "inner join crm_campanha on (crm_campanha.in_campanha_id = crm_atendimento.in_campanha_id) "
                 +  "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_atendimento.in_cliente_id) "              
                 +  "where (crm_atendimento.in_empresa_id = ?) "
                 +  "and (to_char(crm_atendimento.da_inclusao, 'mm/yyyy') = ?) "
                 +  "group by crm_campanha.in_campanha_id, crm_campanha.va_nome, crm_atendimento.in_atendimento_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, crm_atendimento.dt_inicio, crm_atendimento.va_descricao "
                 +  "order by crm_campanha.va_nome, crm_campanha.in_campanha_id, crm_atendimento.in_atendimento_id";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os par�metros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setString(2, DateTools.formatMonthYear(DateTools.getActualDate()));
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
