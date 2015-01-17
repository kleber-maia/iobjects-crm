   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cartão de Tarefa Status.
 */
public class CartaoFunilOportunidade extends Card {

  // identificação da classe 
  static public final String CLASS_NAME = "imanager.card.CartaoFunilOportunidade";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoFunilOportunidade", "Funil de Oportunidade", "Exibe o total de Oportunidades por Fase.", HELP, "card/cartaofuniloportunidade.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padrão.
   */
  public CartaoFunilOportunidade() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os Status e as quantidades de
   * Tarefas no mês.
   * <ol>
   *   <li>crm_fase.va_nome
   *   <li>quantidade de oportunidades
   *   <li>crm_oportunidade.in_fase_id
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Status e as
   *         quantidades de Tarefas no mês.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetFunilOportunidade(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT crm_fase.in_fase_id,"
                 + "crm_fase.va_nome, "
                 + "crm_oportunidade.in_oportunidade_id, "
                 + "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "crm_oportunidade.da_acompanhamento, "
                 + "crm_fase.sm_percentual_sucesso, "
                 + "crm_oportunidade.va_descricao "
                 + "FROM crm_oportunidade "
                 + "INNER JOIN crm_fase ON (crm_fase.in_fase_id = crm_oportunidade.in_fase_id) "
                 + "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_oportunidade.in_cliente_id) "              
                 + "WHERE (crm_oportunidade.in_empresa_id = ?) "
                 + "AND (to_char(crm_oportunidade.da_inclusao, 'mm/yyyy') <= ?) "
                 + "AND crm_oportunidade.sm_status = " + StatusOportunidade.EM_ANDAMENTO + " "
                 + "GROUP BY crm_fase.in_fase_id, crm_fase.va_nome, crm_oportunidade.in_oportunidade_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, crm_oportunidade.da_acompanhamento, crm_fase.sm_percentual_sucesso, crm_oportunidade.va_descricao "
                 + "ORDER BY crm_fase.sm_percentual_sucesso, crm_fase.va_nome, crm_fase.in_fase_id ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
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
