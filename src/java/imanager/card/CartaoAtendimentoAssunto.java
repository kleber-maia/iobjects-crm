   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cart�o de Atendimento Assunto.
 */ 
public class CartaoAtendimentoAssunto extends Card {

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAtendimentoAssunto";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION = new Action("cartaoAtendimentoAssunto", "Atendimentos por Assunto", "Exibe o total de Atendimentos por Assunto.", HELP, "card/cartaoatendimentoassunto.jsp", "CRM", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padr�o.
   */
  public CartaoAtendimentoAssunto() {
    // nossas a��es
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os Assuntos e as quantidades de
   * Atendimentos no m�s.
   * <ol>
   *   <li>crm_assunto.in_assunto_id
   *   <li>crm_assunto.va_nome
   *   <li>quantidade de atendimentos
   * </ol>
   * <b>Importante: a rotina que executar este m�todo deve ser respons�vel por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Assuntos e as
   *         quantidades de Atendimentos no m�s.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetAtendimentoAssunto(int empresaId) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "select crm_assunto.in_assunto_id, "
                 +         "crm_assunto.va_nome, "
                 +         "crm_atendimento.in_atendimento_id, "
                 +         "cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, "
                 +         "crm_atendimento.dt_inicio, "
                 + "crm_atendimento.va_descricao "              
                 +  "from crm_atendimento "
                 +  "inner join crm_assunto on (crm_assunto.in_assunto_id = crm_atendimento.in_assunto_id) "
                 +  "inner join relacionamento_contato as cliente on (cliente.in_contato_id = crm_atendimento.in_cliente_id) "
                 +  "where (crm_atendimento.in_empresa_id = ?) "
                 +  "and (to_char(crm_atendimento.da_inclusao, 'mm/yyyy') = ?) "
                 +  "group by crm_assunto.in_assunto_id, crm_assunto.va_nome, crm_atendimento.in_atendimento_id, cliente.va_nome, cliente.ch_telefone_residencial, cliente.ch_telefone_celular, cliente.ch_telefone_trabalho, crm_atendimento.dt_inicio, crm_atendimento.va_descricao "
                 +  "order by crm_assunto.va_nome, crm_assunto.in_assunto_id, crm_atendimento.in_atendimento_id";
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
