/*
The MIT License (MIT)

Copyright (c) 2008 Kleber Maia de Andrade

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/   

package imanager.card;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.card.*;
import iobjects.help.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o cartão de Contato Vendedor Meta.
 */
public class CartaoAgendaAgendamento extends Card {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.card.CartaoAgendaAgendamento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("cartaoAgendaAgendamento", "Agendamentos", "Exibe os horários marcadados do dia atual de cada agenda.", HELP, "card/cartaoagendaagendamento.jsp", "Contato", "", Action.CATEGORY_CARD, false, true);
  // nossos comandos
  static public final Command COMMAND_PREVIOUS = new Command(Command.COMMAND_PREVIOUS, "Anterior", "Exibe os horários do dia anterior.");
  static public final Command COMMAND_NEXT     = new Command(Command.COMMAND_NEXT, "Anterior", "Exibe os horários do próximo dia.");
  // nossos parâmetros de usuário
  public Param USER_PARAM_DATA = new Param("userParamData", DateTools.formatDate(DateTools.getActualDate()));

  /**
   * Construtor padrão.
   */
  public CartaoAgendaAgendamento() {
    // nossas ações
    actionList().add(ACTION);
    // nossos parâmetros de usuário
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
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @return ResultSet Retorna o ResultSet contendo as Saídas para acompanhamento.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ResultSet getResultSetCartaoAgendaAgendamento(int empresaId, Timestamp data) throws Exception {
    // inicia transação
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
      // preenche os parâmetros
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
