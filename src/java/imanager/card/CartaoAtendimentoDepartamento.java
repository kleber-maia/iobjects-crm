   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/** 
 * Representa o cartão de Atendimento Departamento.
 */
public class CartaoAtendimentoDepartamento extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAtendimentoDepartamento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoAtendimentoDepartamento", "Atendimentos por Departamento", "Exibe o total de Atendimentos por Departamento.", HELP, "card/cartaoatendimentodepartamento.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padrão.
   */
  public CartaoAtendimentoDepartamento() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os Departamentos e as quantidades de
   * Atendimentos no mês.
   * <ol>
   *   <li>global_departamento.in_departamento_id
   *   <li>global_departamento.va_nome
   *   <li>quantidade de atendimentos
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Departamentos e as
   *         quantidades de Atendimentos no mês.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetAtendimentoDepartamento(int empresaId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "select global_departamento.in_departamento_id, "
                 + "global_departamento.va_nome, "
                 + "crm_atendimento.in_atendimento_id, "              
                 + "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 + "crm_atendimento.dt_inicio, "
                 + "crm_atendimento.va_descricao "
                 +  "from crm_atendimento "
                 +  "inner join global_departamento on (global_departamento.in_empresa_id = crm_atendimento.in_empresa_id and global_departamento.in_departamento_id = crm_atendimento.in_departamento_id) "
                 +  "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_atendimento.in_cliente_id) "                            
                 +  "where (crm_atendimento.in_empresa_id = ?) "
                 +  "and (to_char(crm_atendimento.da_inclusao, 'mm/yyyy') = ?) "
                 +  "group by global_departamento.in_departamento_id, global_departamento.va_nome, crm_atendimento.in_atendimento_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, crm_atendimento.dt_inicio, crm_atendimento.va_descricao "
                 +  "order by global_departamento.va_nome, global_departamento.in_departamento_id, crm_atendimento.in_atendimento_id";
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
