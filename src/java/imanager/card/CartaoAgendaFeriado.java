   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cartão de Contato Aniversariante.
 */
public class CartaoAgendaFeriado extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAgendaFeriado";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoAgendaFeriado", "Feriado", "Exibe os proximos feriados.", HELP, "card/cartaoagendaferiado.jsp", "Contato", "", Action.CATEGORY_CARD, false, true);

  /**
   * Construtor padrão.
   */
  public CartaoAgendaFeriado() {
    // nossas ações
    actionList().add(ACTION);
  }

  /**
   * Retorna o ResultSet contendo os Feriados dos proximos 30 Dias.
   * @param empresaId Empresa ID.
   * <ol>
   *   <li>da_feriado
   *   <li>va_nome
   *   <li>sm_bloqueio
   * </ol>
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo os Contatos que realizam
   *         aniversário ou aniversário de cadastro nos próximos 7 dias.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetCartaoAgendaFeriado(int empresaId) throws Exception { 
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT da_feriado, va_nome, sm_bloqueio "
                  + "FROM relacionamento_agenda_feriado "
                  + "WHERE "
                  + "((in_empresa_id = ?) OR (in_empresa_id = 0)) "
                  + "AND da_feriado <= ? "
                  + "AND da_feriado >= ?";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setTimestamp(2, new Timestamp(DateTools.getCalculatedDays(DateTools.getActualDate(), 30).getTime())); 
      preparedStatement.setTimestamp(3, new Timestamp(DateTools.getActualDate().getTime()));
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
