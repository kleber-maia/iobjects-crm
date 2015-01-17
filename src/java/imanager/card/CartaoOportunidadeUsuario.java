   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cartão de Oportunidade Usuário.
 */
public class CartaoOportunidadeUsuario extends Card {
 
  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoOportunidadeUsuario";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoOportunidadeUsuario", "Oportunidades por Usuário", "Exibe as Oportunidade por Usuário.", HELP, "card/cartaooportunidadeusuario.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padrão.
   */
  public CartaoOportunidadeUsuario() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo as Oportunidade por Usuário.
   * <ol>
   *   <li>securityservice_usuario.va_nome
   *   <li>quantidade de oportunidades
   *   <li>crm_oportunidade.in_usuario_id
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Usuários e as
   *         quantidades de Tarefas no mês.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetOportunidadeUsuario(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT securityservice_usuario.in_usuario_id,"
                 + "securityservice_usuario.va_nome, "
                 + "crm_oportunidade.in_oportunidade_id, "
                 + "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "crm_oportunidade.da_acompanhamento, "
                 + "crm_oportunidade.va_descricao "
                 + "FROM crm_oportunidade "
                 + "INNER JOIN securityservice_usuario ON (crm_oportunidade.in_usuario_id = securityservice_usuario.in_usuario_id) "
                 + "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_oportunidade.in_cliente_id) "              
                 + "WHERE in_empresa_id = ? "
                 + "AND crm_oportunidade.sm_status = " + StatusOportunidade.EM_ANDAMENTO + " "
                 + "group by securityservice_usuario.in_usuario_id, securityservice_usuario.va_nome, crm_oportunidade.in_oportunidade_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, crm_oportunidade.da_acompanhamento, crm_oportunidade.va_descricao  "
                 + "ORDER BY securityservice_usuario.va_nome, securityservice_usuario.in_usuario_id, crm_oportunidade.in_oportunidade_id";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
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
