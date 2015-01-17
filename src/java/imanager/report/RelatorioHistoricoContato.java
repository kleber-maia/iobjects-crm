   
package imanager.report;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.help.*;
import iobjects.report.*;
import iobjects.util.*;

import imanager.entity.*;
import imanager.misc.*;
import iobjects.entity.Paginate;

/**
 * Representa o relatório de Histórico Contato.
 */
public class RelatorioHistoricoContato extends Report {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.report.RelatorioHistoricoContato";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION = new Action("relatorioHistoricoContato", "Histórico de Contato", "Emite o relatório de Histórico de Contato.", HELP, "report/relatoriohistoricocontatorelatorio.jsp", "Contato", "Auxiliares", Action.CATEGORY_REPORT, false, true);
  static public final Action ACTION_CENTRAL_ATENDIMENTO = new Action("relacotrioHistoricoContato", "Histórico de Contato", "Emite o relatório de Histórico de Contato.", HELP, "report/relatoriohistoricocontatorelatorio.jsp", "Contato", "Auxiliares", Action.CATEGORY_REPORT, false, false);
  // nossos comandos
  static public final Command COMMAND_MAKE_REPORT = ACTION.addCommand(new Command(Command.COMMAND_MAKE_REPORT, "Histórico", "Exibe o relatório de Histórico do Contato."));
  static public final Command COMMAND_AGENDAMENTOS = ACTION.addCommand(new Command("commandAgendamentos", "Agendamentos", "Exibe os Agendamentos."));
  static public final Command COMMAND_ATENDIMENTOS = ACTION.addCommand(new Command("commandAtendimentos", "Atendimentos", "Exibe os Atendimentos."));
  static public final Command COMMAND_TAREFAS = ACTION.addCommand(new Command("commandTarefas", "Tarefas", "Exibe as Tarefas."));
  static public final Command COMMAND_TITULOS = ACTION.addCommand(new Command("commandTitulos", "Títulos", "Exibe os Títulos."));
  static public final Command COMMAND_LANCAMENTOS = ACTION.addCommand(new Command("commandLancamentos", "Lançamentos", "Exibe os Lançamentos."));
  static public final Command COMMAND_ENTRADAS = ACTION.addCommand(new Command("commandEntradas", "Entradas", "Exibe as Entradas."));
  static public final Command COMMAND_SAIDAS = ACTION.addCommand(new Command("commandSaidas", "Saídas", "Exibe as Saídas."));
  static public final Command COMMAND_PEDIDOS = ACTION.addCommand(new Command("commandPedidos", "Pedidos", "Exibe os Pedidos."));
  // nossos parâmetros de usuário
  public final Param USER_PARAM_CONTATO_ID = new Param("userParamContatoId", "Contato", "Informe o Contato.", "", 4, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "value != '0'", "Obrigatório informar o Contato.");
  
  static {
    ACTION.setShowType(Action.SHOW_TYPE_POPUP_EXTENDED);
  }

  /**
   * Construtor padrão.
   */
  public RelatorioHistoricoContato() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CENTRAL_ATENDIMENTO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_CONTATO_ID);
  }

  /**
   * Retorna os últimos Agendamentos do Cliente nos últimos anos.
   * @param clienteId
   * @return
   * @throws Exception
   */
  public AgendaHorarioInfo[] getAgendamentos(int clienteId) throws Exception {
    // nossa instância
    AgendaHorario agendaHorario = (AgendaHorario)getFacade().getEntity(AgendaHorario.CLASS_NAME);
    // registros mais recentes
    return agendaHorario.lastRecents(clienteId);
  }

  /**
   * Retorna os últimos Atendimentos do Cliente nos últimos anos.
   * @param clienteId
   * @return
   * @throws Exception
   */
  public AtendimentoInfo[] getAtendimentos(int clienteId) throws Exception {
    // nossa instância
    Atendimento atendimento = (Atendimento)getFacade().getEntity(Atendimento.CLASS_NAME);
    // registros mais recentes
    return atendimento.lastRecents(clienteId);
  }

  /**
   * Retorna as últimas Tarefas do Cliente nos últimos anos.
   * @param clienteId
   * @return
   * @throws Exception
   */
  public TarefaInfo[] getTarefas(int clienteId) throws Exception {
    // nossa instância
    Tarefa tarefa = (Tarefa)getFacade().getEntity(Tarefa.CLASS_NAME);
    // registros mais recentes
    return tarefa.lastRecents(clienteId);
  }
  
}
