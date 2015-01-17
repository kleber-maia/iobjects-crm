   
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
 * Representa o relat�rio de Hist�rico Contato.
 */
public class RelatorioHistoricoContato extends Report {

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.report.RelatorioHistoricoContato";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION = new Action("relatorioHistoricoContato", "Hist�rico de Contato", "Emite o relat�rio de Hist�rico de Contato.", HELP, "report/relatoriohistoricocontatorelatorio.jsp", "Contato", "Auxiliares", Action.CATEGORY_REPORT, false, true);
  static public final Action ACTION_CENTRAL_ATENDIMENTO = new Action("relacotrioHistoricoContato", "Hist�rico de Contato", "Emite o relat�rio de Hist�rico de Contato.", HELP, "report/relatoriohistoricocontatorelatorio.jsp", "Contato", "Auxiliares", Action.CATEGORY_REPORT, false, false);
  // nossos comandos
  static public final Command COMMAND_MAKE_REPORT = ACTION.addCommand(new Command(Command.COMMAND_MAKE_REPORT, "Hist�rico", "Exibe o relat�rio de Hist�rico do Contato."));
  static public final Command COMMAND_AGENDAMENTOS = ACTION.addCommand(new Command("commandAgendamentos", "Agendamentos", "Exibe os Agendamentos."));
  static public final Command COMMAND_ATENDIMENTOS = ACTION.addCommand(new Command("commandAtendimentos", "Atendimentos", "Exibe os Atendimentos."));
  static public final Command COMMAND_TAREFAS = ACTION.addCommand(new Command("commandTarefas", "Tarefas", "Exibe as Tarefas."));
  static public final Command COMMAND_TITULOS = ACTION.addCommand(new Command("commandTitulos", "T�tulos", "Exibe os T�tulos."));
  static public final Command COMMAND_LANCAMENTOS = ACTION.addCommand(new Command("commandLancamentos", "Lan�amentos", "Exibe os Lan�amentos."));
  static public final Command COMMAND_ENTRADAS = ACTION.addCommand(new Command("commandEntradas", "Entradas", "Exibe as Entradas."));
  static public final Command COMMAND_SAIDAS = ACTION.addCommand(new Command("commandSaidas", "Sa�das", "Exibe as Sa�das."));
  static public final Command COMMAND_PEDIDOS = ACTION.addCommand(new Command("commandPedidos", "Pedidos", "Exibe os Pedidos."));
  // nossos par�metros de usu�rio
  public final Param USER_PARAM_CONTATO_ID = new Param("userParamContatoId", "Contato", "Informe o Contato.", "", 4, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "value != '0'", "Obrigat�rio informar o Contato.");
  
  static {
    ACTION.setShowType(Action.SHOW_TYPE_POPUP_EXTENDED);
  }

  /**
   * Construtor padr�o.
   */
  public RelatorioHistoricoContato() {
    // nossas a��es
    actionList().add(ACTION);
    actionList().add(ACTION_CENTRAL_ATENDIMENTO);
    // nossos par�metros de usu�rio
    userParamList().add(USER_PARAM_CONTATO_ID);
  }

  /**
   * Retorna os �ltimos Agendamentos do Cliente nos �ltimos anos.
   * @param clienteId
   * @return
   * @throws Exception
   */
  public AgendaHorarioInfo[] getAgendamentos(int clienteId) throws Exception {
    // nossa inst�ncia
    AgendaHorario agendaHorario = (AgendaHorario)getFacade().getEntity(AgendaHorario.CLASS_NAME);
    // registros mais recentes
    return agendaHorario.lastRecents(clienteId);
  }

  /**
   * Retorna os �ltimos Atendimentos do Cliente nos �ltimos anos.
   * @param clienteId
   * @return
   * @throws Exception
   */
  public AtendimentoInfo[] getAtendimentos(int clienteId) throws Exception {
    // nossa inst�ncia
    Atendimento atendimento = (Atendimento)getFacade().getEntity(Atendimento.CLASS_NAME);
    // registros mais recentes
    return atendimento.lastRecents(clienteId);
  }

  /**
   * Retorna as �ltimas Tarefas do Cliente nos �ltimos anos.
   * @param clienteId
   * @return
   * @throws Exception
   */
  public TarefaInfo[] getTarefas(int clienteId) throws Exception {
    // nossa inst�ncia
    Tarefa tarefa = (Tarefa)getFacade().getEntity(Tarefa.CLASS_NAME);
    // registros mais recentes
    return tarefa.lastRecents(clienteId);
  }
  
}
