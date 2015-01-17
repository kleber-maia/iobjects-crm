   
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
public class CartaoTarefaStatus extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoTarefaStatus";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoTarefaStatus", "Tarefas por Status", "Exibe o total de Tarefas por Usuário.", HELP, "card/cartaotarefastatus.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /** 
   * Construtor padrão.
   */
  public CartaoTarefaStatus() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os Status e as quantidades de
   * Tarefas no mês.
   * <ol>
   *   <li>crm_tarefa.sm_status
   *   <li>quantidade de tarefas
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Status e as
   *         quantidades de Tarefas no mês.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetTarefaStatus(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "select crm_tarefa.sm_status, "
                 + "crm_tarefa.in_tarefa_id, "
                 + "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "usuario.in_usuario_id, "
                 + "usuario.va_nome, "              
                 + "crm_tarefa.da_prazo, "
                 + "crm_tarefa.va_descricao "              
                 + "from crm_tarefa "
                 + "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_tarefa.in_cliente_id) "
                 + "inner join securityservice_usuario as usuario on (usuario.in_usuario_id = crm_tarefa.in_usuario_id) "
                 + "where (crm_tarefa.in_empresa_id = ?) "
                 + " and (to_char(crm_tarefa.da_inclusao, 'mm/yyyy') <= ?) "
                 + " and crm_tarefa.sm_status <> " + StatusTarefa.CONCLUIDA + " "
                 + "group by crm_tarefa.sm_status, crm_tarefa.in_tarefa_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho,usuario.in_usuario_id, usuario.va_nome, crm_tarefa.da_prazo, crm_tarefa.va_descricao "
                 + "order by crm_tarefa.sm_status ";
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
