   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cartão de Tarefa Assunto.
 */
public class CartaoTarefaAssunto extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoTarefaAssunto";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoTarefaAssunto", "Tarefas por Assunto", "Exibe o total de Tarefas por Assunto.", HELP, "card/cartaotarefaassunto.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /** 
   * Construtor padrão.
   */
  public CartaoTarefaAssunto() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os Assuntos e as quantidades de
   * Tarefas no mês.
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Assuntos e as
   *         quantidades de Tarefas no mês.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetTarefaAssunto(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "select crm_assunto.in_assunto_id, "
                 + "crm_assunto.va_nome, "
                 + "crm_tarefa.in_tarefa_id, "
                 + "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "crm_tarefa.da_prazo, "
                 + "usuario.in_usuario_id, "
                 + "usuario.va_nome, "
                 + "crm_tarefa.va_descricao "
                 + "from crm_tarefa "
                 + "inner join crm_assunto on (crm_assunto.in_assunto_id = crm_tarefa.in_assunto_id) "
                 + "inner join securityservice_usuario as usuario on (usuario.in_usuario_id = crm_tarefa.in_usuario_id) "
                 +  "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_tarefa.in_cliente_id) "
                 + "where (crm_tarefa.in_empresa_id = ?) "
                 + " and (to_char(crm_tarefa.da_inclusao, 'mm/yyyy') <= ?) "
                 + " and crm_tarefa.sm_status <> " + StatusTarefa.CONCLUIDA + " "
                 + "group by crm_assunto.in_assunto_id, crm_assunto.va_nome, crm_tarefa.in_tarefa_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, crm_tarefa.da_prazo, usuario.in_usuario_id, usuario.va_nome, crm_tarefa.va_descricao "
                 + "order by crm_assunto.va_nome, crm_assunto.in_assunto_id ";
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
