   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cartão de Oportunidade Acompanhamento.
 */
public class CartaoOportunidadeAcompanhamento extends Card {
 
  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoOportunidadeAcompanhamento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoOportunidadeAcompanhamento", "Oportunidades para Acompanhamento", "Exibe as Oportunidade para Acompanhamento.", HELP, "card/cartaooportunidadeacompanhamento.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padrão.
   */
  public CartaoOportunidadeAcompanhamento() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo as Oportunidade para Acompanhamento.
   * <ol>
   *   <li>crm_oportunidade.in_oportunidade_id
   *   <li>crm.oportunidade.da_acompanhamento
   *   <li>securityservice_usuario.va_nome
   *   <li>cliente.va_nome
   *   <li>cliente.ch_telefone_residencial
   *   <li>cliente.ch_telefone_celular
   *   <li>cliente.ch_telefone_trabalho
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Usuários e as
   *         quantidades de Tarefas no mês.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetOportunidadeAcompanhamento(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT crm_oportunidade.in_oportunidade_id, crm_oportunidade.da_acompanhamento, securityservice_usuario.va_nome, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "crm_oportunidade.va_descricao "
                 + "FROM crm_oportunidade "
                 + "INNER JOIN securityservice_usuario ON (securityservice_usuario.in_usuario_id = crm_oportunidade.in_usuario_id) "
                 + "INNER JOIN relacionamento_contato as cliente ON (cliente.in_contato_id = crm_oportunidade.in_cliente_id) "
                 + "WHERE crm_oportunidade.in_empresa_id = ? "
                 + "AND (crm_oportunidade.da_acompanhamento <= ? "
                 + "AND crm_oportunidade.da_acompanhamento <> ?) "
                 + "AND crm_oportunidade.sm_status = " + StatusOportunidade.EM_ANDAMENTO + " "
                 + "ORDER BY crm_oportunidade.in_oportunidade_id ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setTimestamp(2, new Timestamp(DateTools.getActualDate().getTime()));
      preparedStatement.setTimestamp(3, DateTools.ZERO_DATE);
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
