   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cartão de Atendimento Usuário.
 */
public class CartaoAtendimentoUsuario extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAtendimentoUsuario";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoAtendimentoUsuario", "Atendimentos por Usuário", "Exibe o total de Atendimentos por Usuário.", HELP, "card/cartaoatendimentousuario.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padrão.
   */
  public CartaoAtendimentoUsuario() {
    // nossas ações
    actionList().add(ACTION); 
  }

  /**
   * Retorna o ResultSet contendo os Usuários e as quantidades de
   * Atendimentos no mês.
   * <ol>
   *   <li>securityservice_usuario.in_usuario_id
   *   <li>securityservice_usuario.va_nome
   *   <li>quantidade de atendimentos
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Usuários e as
   *         quantidades de Atendimentos no mês.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetAtendimentoUsuario(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "select securityservice_usuario.in_usuario_id, "
                 + "securityservice_usuario.va_nome, "
                 + "crm_atendimento.in_atendimento_id, "              
                 + "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "crm_atendimento.dt_inicio, "
                 + "crm_atendimento.va_descricao "
                 +  "from crm_atendimento "
                 +  "inner join securityservice_usuario on (securityservice_usuario.in_usuario_id = crm_atendimento.in_usuario_inclusao_id) "
                 +  "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_atendimento.in_cliente_id) "
                 +  "where (crm_atendimento.in_empresa_id = ?) "
                 +  "and (to_char(crm_atendimento.da_inclusao, 'mm/yyyy') = ?) "
                 +  "group by securityservice_usuario.in_usuario_id, securityservice_usuario.va_nome, crm_atendimento.in_atendimento_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, crm_atendimento.dt_inicio, crm_atendimento.va_descricao "
                 +  "order by securityservice_usuario.va_nome, securityservice_usuario.in_usuario_id, crm_atendimento.in_atendimento_id";
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
