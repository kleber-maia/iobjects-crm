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
   
package imanager.report;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.report.*;
import iobjects.util.*;

import imanager.misc.*;

/**
 * Representa o relatório de RelatorioAgendaSemanal.
 */
public class RelatorioAgendaSemanal extends Report {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.report.RelatorioAgendaSemanal";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION               = new Action("relatorioAgendaSemanal", "Agenda Semanal", "Emite o relatório de Agenda Semanal.", HELP, "report/relatorioagendasemanal.jsp", "Contato", "", Action.CATEGORY_REPORT, false, true);
  static public final Action ACTION_RELATORIO     = new Action("relatorioAgendaSemanalRelatorio", ACTION.getCaption(), ACTION.getDescription(), HELP, "report/relatorioagendasemanalrelatorio.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_REPORT, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_MAKE_REPORT = ACTION.addCommand(new Command(Command.COMMAND_MAKE_REPORT, "Gerar", "Gera e exibe o relatório com os parâmetros informados."));
  static public final Command COMMAND_PRINT       = ACTION.addCommand(new Command(Command.COMMAND_PRINT, "Imprimir", "Envia o relatório exibido."));
  static public final Command COMMAND_COPY        = ACTION.addCommand(new Command(Command.COMMAND_COPY, "Copiar", "Copia todo o conteúdo do relatório exibido."));
  // nossos parâmetros de usuário
  public final Param USER_PARAM_DATA        = new Param("userParamData", "Data", "Selecione a Data.", "", 250, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório selecionar a Data.");
  public final Param USER_PARAM_AGENDA      = new Param("userParamAgenda", "Agenda", "Selecione a Agenda.", "", 50, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_CONSOLIDADO = new Param("userParamConsolidado", "Consolidado", "Selecione se os dados serão consolidados entre todas empresas.", "");

  {
    USER_PARAM_CONSOLIDADO.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    USER_PARAM_DATA.setValue(DateTools.formatDate(DateTools.getActualDate()));
  }
  
  /**
   * Construtor padrão.
   */
  public RelatorioAgendaSemanal() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_RELATORIO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_DATA);
    userParamList().add(USER_PARAM_AGENDA);
    userParamList().add(USER_PARAM_CONSOLIDADO);
  }
  
  public Vector calculateWeek(Timestamp data) {
      // calcula os mili segundos por dia
      final long millisDia = 1000 * 60 * 60 * 24;
      // calendar
      Calendar dia1 = Calendar.getInstance();        
      // data usuário
      dia1.setTime(data);
      // dia da semana selecionado pelo usuário
      int diaDaSemana = dia1.get(Calendar.DAY_OF_WEEK);  
      // dia em mili segundos
      long time = dia1.getTimeInMillis();  
      //Cálculo do primeiro dia da semana  
      long primeiroDiaSemana = time - ((diaDaSemana - Calendar.SUNDAY) * millisDia);  
      // atribui a data para a data do usuário
      dia1.setTimeInMillis(primeiroDiaSemana);
      Timestamp dia = new Timestamp(dia1.getTimeInMillis());
      // adiciona 6 dias na semana
      dia1.add(Calendar.DAY_OF_MONTH, 6);
      Timestamp dia7 = new Timestamp(dia1.getTimeInMillis());
      // nosso vetor
      Vector calculaSemana = new Vector();
      // adiciona o primeiro dia da semana no vetor
      calculaSemana.addElement(dia);
      // adiciona o último dia da semana no vetor
      calculaSemana.addElement(dia7);
      // adiciona primeiro dia da semana em long no vetor
      calculaSemana.addElement(primeiroDiaSemana);
      // retorna
      return calculaSemana;
  }
  
  /**
   * Retorna um ResultSet contendo o último e o primeiro horário da agenda selecionada 
   * indicados pelos parâmetros de pesquisa.
   * @param empresaId Empresa ID.
   * @param agendaId Agenda ID.
   * @param data Data.
   * @param consolidado Consolidado.
   * @return Retorna um ResultSet contendo o último e o primeiro horário da agenda selecionada 
   * indicados pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  
  public ResultSet selectByMinMaxHorario(int empresaId,
                                         int agendaId,
                                         Timestamp data,
                                         int consolidado) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // SQL
      String sql = "SELECT MIN(ch_hora_agendamento) as min_hora, MAX(ch_hora_agendamento) as max_hora "
                 + "FROM relacionamento_agenda_horario "
                 + "WHERE ((in_empresa_id = ?) OR (" + (consolidado == NaoSim.SIM) + ")) "
                 + "AND ((in_agenda_id = ?) OR (" + agendaId + "=0))"
                 + "AND ((da_data_agendamento >= ?) AND (da_data_agendamento <= ?))";
      PreparedStatement preparedStatement = prepare(sql);
      // calcula o primeiro e último dia da semana
      Vector semana = calculateWeek(data);
      // primeiro dia da semana (domingo)
      Timestamp dataInicio = (Timestamp)semana.elementAt(0);
      // último dia da semana (sábado)
      Timestamp dataFim = (Timestamp)semana.elementAt(1);
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setInt(2, agendaId);
      preparedStatement.setTimestamp(3, dataInicio);
      preparedStatement.setTimestamp(4, dataFim);
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

  /**
   * Retorna o ResultSet de RelatorioAgendaSemanal.
   * <b>Importante: a rotina que executar este método deve ser responsável por
   * fechar o ResultSet retornardo e o seu Statement.</b>
   * @param data Timestamp Data.
   * @param agendaId Int Agenda ID.
   * @param consolidado int Consolidado.
   * @return ResultSet Retorna o ResultSet de RelatorioAgendaSemanal.
   *                   <b>Importante: a rotina que executar este método deve ser
   *                   responsável por fechar o ResultSet retornardo e o seu
   *                   Statement.</b>
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  
  public ResultSet getResultSetRelatorioAgendaSemanal(
                       int empresaId,
                       Timestamp data,
                       int agendaId,
                       String horaMin,
                       String horaMax,
                       int diferencaHoras,
                       int consolidado
                    ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // calcula o primeiro e último dia da semana
      Vector semana = calculateWeek(data);
      // primeiro dia da semana (domingo)
      Timestamp dia1 = (Timestamp)semana.elementAt(0);
      // último dia da semana (sábado)
      Timestamp dia7 = (Timestamp)semana.elementAt(1);
      // primeiro dia da semana em long
      long primeiroDiaSemana = (Long)semana.elementAt(2);
      // primeiro dia da semana... domingo
      String domingo = dia1 + "";
      // último dia da semana... sábado
      String sabado = dia7 + "";
      // cria a agenda completa...
      StringBuffer agendaDefault = new StringBuffer();
      // nosso calendário
      Calendar diaPrimeiro = Calendar.getInstance(); 
      // seta o primeiro dia da semana
      diaPrimeiro.setTimeInMillis(primeiroDiaSemana);
      // primeiro horário da agenda...
      String horaAgendamento = horaMin;
      Timestamp diaVez = new Timestamp(diaPrimeiro.getTimeInMillis());
      // diferença entre a maior hora e a menor somando a primeira hora
      diferencaHoras++;
      // montando a semana... com o total de horas * dias da semana
      for (int i = 0;i < diferencaHoras * 7; i++) {
        // se não temos resto e não é o primeiro horário... adiciona um dia e retorna para o primeiro horário do dia 
        if (i % diferencaHoras == 0 && i > 0) {
          horaAgendamento = horaMin;
          diaPrimeiro.add(Calendar.DAY_OF_MONTH, 1);
          diaVez = new Timestamp(diaPrimeiro.getTimeInMillis());
        } // if
        // dia da vez em String
        String diaString = diaVez + "";
        // monta todos os horários não agendados da semana
        agendaDefault.append("UNION ALL SELECT 0 as empresa_id, '' as agenda, '" + horaAgendamento + "' as hora_agendamento, '" + diaString.substring(0, 10) + "' as data_agendamento, '' as contato, 0 as status, 0 as agenda_id, 0 as agenda_horario_id ");
        // adiciona uma hora
        horaAgendamento = NumberTools.completeZero(Integer.parseInt(horaAgendamento) + 100, 4);
      } // for
      // SQL
      String sql = "SELECT relacionamento_agenda_horario.in_empresa_id as empresa_id, relacionamento_agenda.va_nome as agenda, relacionamento_agenda_horario.ch_hora_agendamento as hora_agendamento, relacionamento_agenda_horario.da_data_agendamento as data_agendamento, relacionamento_contato.va_nome as contato, relacionamento_agenda_horario.sm_status as status, relacionamento_agenda_horario.in_agenda_id as agenda_id, relacionamento_agenda_horario.in_agenda_horario_id as agenda_horario_id "
                 + "FROM relacionamento_agenda_horario "
                 + "INNER JOIN relacionamento_contato ON (relacionamento_agenda_horario.in_contato_id = relacionamento_contato.in_contato_id) "
                 + "INNER JOIN relacionamento_agenda ON (relacionamento_agenda_horario.in_empresa_id = relacionamento_agenda.in_empresa_id and relacionamento_agenda_horario.in_agenda_id = relacionamento_agenda.in_agenda_id) "
                 + "WHERE ((relacionamento_agenda.in_empresa_id = ?) "
                 + "AND ((relacionamento_agenda.in_agenda_id = ?) OR (" + agendaId + "=0)) "
                 + "AND (relacionamento_agenda_horario.da_data_agendamento >='" + domingo.substring(0, 10) + "' AND relacionamento_agenda_horario.da_data_agendamento <='" + sabado.substring(0, 10) + "')) "
                 + "OR ((" + (consolidado == NaoSim.SIM) + ")"
                 + "AND (relacionamento_agenda_horario.da_data_agendamento >='" + domingo.substring(0, 10) + "' AND relacionamento_agenda_horario.da_data_agendamento <='" + sabado.substring(0, 10) + "')) "
                 + agendaDefault.toString()
                 + "ORDER BY hora_agendamento, data_agendamento";
      // prepara a query
      PreparedStatement preparedStatement = prepare(sql);
      // preenche os parâmetros
      preparedStatement.setInt(1, empresaId);
      preparedStatement.setInt(2, agendaId);
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
