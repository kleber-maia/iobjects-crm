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
 * Representa o cartão de Atendimento Assunto.
 */ 
public class CartaoCampanhaOportunidade extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoCampanhaOportunidade";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoCampanhaOportunidade", "Oportunidade Com/Sem Campanha", "Exibe os últimos 30 dias de oportunidade informando se está em período de campanha ou não.", HELP, "card/cartaocampanhaoportunidade.jsp", "CRM", "", Action.CATEGORY_CARD, false, false);

  /**
   * Construtor padrão.
   */
  public CartaoCampanhaOportunidade() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os valores das vendas realizadas 
   * no período de 1 mês e informa se houve campanha nesse periodo.
   * <ol>
   *   <li>valor
   *   <li>quantidade
   *   <li>data
   *   <li>campanha
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Assuntos e as
   *         quantidades de Atendimentos no mês.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetCampanhaOportunidade(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT SUM(crm_oportunidade.do_valor) as valor, COUNT(crm_oportunidade.in_oportunidade_id) as quantidade, CAST(crm_oportunidade.da_inclusao as DATE) as data, crm_campanha.va_nome as campanha "
                 + " FROM crm_oportunidade "
                 + " LEFT JOIN crm_campanha ON (crm_oportunidade.da_inclusao >= crm_campanha.da_inicio AND crm_oportunidade.da_inclusao <= crm_campanha.da_termino) "
                 + " WHERE  "
                 + " crm_oportunidade.in_empresa_id = ? AND "
                 + " (crm_oportunidade.da_inclusao >= ? AND crm_oportunidade.da_inclusao < ?) "
                 + " GROUP BY data, campanha "
                 + " ORDER BY data";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setTimestamp(2, new Timestamp(DateTools.getCalculatedMonths(DateTools.getActualDate(), -1).getTime()));
      preparedStatement.setTimestamp(3, new Timestamp(DateTools.getCalculatedDays(DateTools.getActualDate(), 1).getTime())); 
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
