   
package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cart�o de Contato Vendedor Meta.
 */
public class CartaoAgendaAgendamento extends Card {

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAgendaAgendamento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION = new Action("cartaoAgendaAgendamento", "Agendamentos", "Exibe os hor�rios marcadados do dia atual de cada agenda.", HELP, "card/cartaoagendaagendamento.jsp", "Contato", "", Action.CATEGORY_CARD, false, true);
  // nossos comandos
  static public final Command COMMAND_PREVIOUS = new Command(Command.COMMAND_PREVIOUS, "Anterior", "Exibe os hor�rios do dia anterior.");
  static public final Command COMMAND_NEXT     = new Command(Command.COMMAND_NEXT, "Anterior", "Exibe os hor�rios do pr�ximo dia.");
  // nossos par�metros de usu�rio
  public Param USER_PARAM_DATA = new Param("userParamData", DateTools.formatDate(DateTools.getActualDate()));

  /**
   * Construtor padr�o.
   */
  public CartaoAgendaAgendamento() {
    // nossas a��es
    actionList().add(ACTION);
    // nossos par�metros de usu�rio
    userParamList().add(USER_PARAM_DATA);
  }

  /**
   * Retorna o ResultSet contendo as Agendas e Agendamentos.
   * <ol>
   *   <li>relacionamento_agenda.va_nome
   *   <li>relacionamento_agenda_horario.ch_hora_agendamento
   *   <li>relacionamento_agenda_horario.sm_status
   *   <li>relacionamento_agenda_horario.in_contato_id
   * </ol>
   * <b>Importante: a rotina que executar este m�todo deve ser respons�vel por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo as Sa�das para acompanhamento.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetCartaoAgendaAgendamento(int empresaId, Timestamp data) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT relacionamento_agenda.va_nome as nome_agenda, relacionamento_agenda_horario.in_agenda_id as agenda_id, relacionamento_agenda_horario.ch_hora_agendamento as horario, relacionamento_agenda_horario.sm_status as status, relacionamento_contato.va_nome as contato, relacionamento_agenda_horario.in_contato_id as contato_id, relacionamento_agenda_horario.in_agenda_horario_id as agenda_horario_id, relacionamento_agenda_horario.va_anotacoes as anotacoes "
                 + "FROM relacionamento_agenda_horario "
                 + "INNER JOIN relacionamento_contato on (relacionamento_agenda_horario.in_contato_id = relacionamento_contato.in_contato_id) "
                 + "INNER JOIN relacionamento_agenda ON (relacionamento_agenda_horario.in_empresa_id = relacionamento_agenda.in_empresa_id AND relacionamento_agenda_horario.in_agenda_id = relacionamento_agenda.in_agenda_id) "
                 + "WHERE relacionamento_agenda_horario.in_empresa_id = ? "
                 + "  AND relacionamento_agenda_horario.da_data_agendamento = ? "
                 + "ORDER BY relacionamento_agenda.va_nome, relacionamento_agenda_horario.ch_hora_agendamento ";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os par�metros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setTimestamp(2, data);
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
