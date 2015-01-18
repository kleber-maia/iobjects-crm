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
     
package imanager.entity;

import imanager.misc.StatusAgendaHorario;
import imanager.ui.entity.AgendaUI;
import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.util.*;
import iobjects.entity.*;
import iobjects.ui.entity.*;
import iobjects.help.*;

import imanager.misc.*;

/**
 * Representa a entidade AgendaHorario no banco de dados da aplicação.
 */
public class AgendaHorario extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.AgendaHorario";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("agendaHorario", "Agendamento", "Mantém o cadastro de Agenda Horário.", HELP, "entity/agendahorario.jsp", "Contato", "", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("agendaHorarioCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/agendahorariocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa", "", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_AGENDA_ID = new EntityField("in_agenda_id", "Agenda", "", "agendaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_AGENDA_HORARIO_ID = new EntityField("in_agenda_horario_id", "Agenda Horario", "", "agendaHorarioId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_AGENDAMENTO = new EntityField("da_data_agendamento", "Data", "Informe a Data de Agendamento.", "dataAgendamento", Types.TIMESTAMP, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", true, "", "value!=''", "Obrigatório informar a Data de Agendamento.");
  static public final EntityField FIELD_HORA_AGENDAMENTO = new EntityField("ch_hora_agendamento", "Hora", "Selecione a Hora de Agendamento.", "horaAgendamento", Types.CHAR, 4, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "00:00", false);
  static public final EntityField FIELD_CONTATO_ID = new EntityField("in_contato_id", "Contato", "", "contatoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value!=''", "Obrigatório informar o Contato.");
  static public final EntityField FIELD_ANOTACOES = new EntityField("va_anotacoes", "Anotações", "Informe as Anotações.", "anotacoes", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);
  static public final EntityField FIELD_STATUS = new EntityField("sm_status", "Status", "Informe o Status.", "status", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_USUARIO_INCLUSAO_ID = new EntityField("in_usuario_inclusao_id", "Usuário Inclusão", "", "usuarioInclusaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_HORA_INCLUSAO = new EntityField("dt_inclusao", "Data/Hora Inclusão", "", "dataHoraInclusao", Types.TIMESTAMP, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE_TIME, "", true);
  static public final EntityField FIELD_USUARIO_ALTERACAO_ID = new EntityField("in_usuario_alteracao_id", "Usuário Alteração", "", "usuarioAlteracaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_HORA_ALTERACAO = new EntityField("dt_alteracao", "Data/Hora Alteração", "", "dataHoraAlteracao", Types.TIMESTAMP, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE_TIME, "", true);
  // nossos lookups
  static public final EntityLookup LOOKUP_EMPRESA = new EntityLookup("lookupEmpresa", "Empresa", "", Empresa.CLASS_NAME, FIELD_EMPRESA_ID, Empresa.FIELD_NOME, Empresa.FIELD_NOME);
  static public final EntityLookup LOOKUP_AGENDA  = new EntityLookup("lookupAgenda", "Agenda", "", Agenda.CLASS_NAME, new EntityField[]{FIELD_EMPRESA_ID, FIELD_AGENDA_ID}, new EntityField[]{Agenda.FIELD_NOME}, new EntityField[]{Agenda.FIELD_NOME});
  static public final EntityLookup LOOKUP_CONTATO = new EntityLookup("lookupContato", "Contato", "Selecione o Contato.", Contato.CLASS_NAME, FIELD_CONTATO_ID, Contato.FIELD_NOME, Contato.FIELD_NOME);
  // nossos parâmetros de usuário
  public final Param USER_PARAM_AGENDA_ID           = new Param("userParamAgendaId", "Agenda", "Selecione a Agenda.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER, "", "value != ''", "Obrigatório informar a Agenda.");
  public final Param USER_PARAM_DATA_AGENDAMENTO    = new Param("userParamDataAgendamento", "Data Agendamento", "Informe a Data de Agendamento.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar a Data de Agendamento.");
  public final Param USER_PARAM_HORARIO_AGENDAMENTO = new Param("userParamHorarioAgendamento", "");
  public final Param USER_PARAM_FERIADO             = new Param("userParamFeriado", "");

  static {
    AgendaHorario.FIELD_STATUS.setLookupList(StatusAgendaHorario.LOOKUP_LIST_FOR_FIELD);
  }
  
  {
    USER_PARAM_DATA_AGENDAMENTO.setValue(DateTools.formatDate(DateTools.getActualDate()));
  }


  /**
   * Construtor padrão.
   */
  public AgendaHorario() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("relacionamento_agenda_horario");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);
    fieldList().add(FIELD_AGENDA_ID);  
    fieldList().add(FIELD_AGENDA_HORARIO_ID);
    fieldList().add(FIELD_DATA_AGENDAMENTO);
    fieldList().add(FIELD_HORA_AGENDAMENTO);
    fieldList().add(FIELD_CONTATO_ID);  
    fieldList().add(FIELD_ANOTACOES);    
    fieldList().add(FIELD_STATUS);  
    fieldList().add(FIELD_USUARIO_INCLUSAO_ID);  
    fieldList().add(FIELD_DATA_HORA_INCLUSAO);
    fieldList().add(FIELD_USUARIO_ALTERACAO_ID);  
    fieldList().add(FIELD_DATA_HORA_ALTERACAO);
    // nossos lookups
    lookupList().add(LOOKUP_EMPRESA);
    lookupList().add(LOOKUP_AGENDA);
    lookupList().add(LOOKUP_CONTATO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_AGENDA_ID);
    userParamList().add(USER_PARAM_DATA_AGENDAMENTO);
    userParamList().add(USER_PARAM_HORARIO_AGENDAMENTO);
    userParamList().add(USER_PARAM_FERIADO);
  }

  /**
   * Exclui o(a) AgendaHorario informado(a) por 'agendaHorarioInfo'.
   * @param agendaHorarioInfo AgendaHorarioInfo referente a(o) AgendaHorario
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(AgendaHorarioInfo agendaHorarioInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(agendaHorarioInfo);
      // salva tudo
      getFacade().commitTransaction();
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Insere o(a) AgendaHorario identificado(a) por 'agendaHorarioInfo'.
   * @param agendaHorarioInfo AgendaHorarioInfo contendo as informações do(a) AgendaHorario que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(AgendaHorarioInfo agendaHorarioInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(agendaHorarioInfo);
      // valor da sequência
      agendaHorarioInfo.setAgendaHorarioId(getNextSequence(FIELD_AGENDA_HORARIO_ID, FIELD_EMPRESA_ID.getFieldName() + "=" + agendaHorarioInfo.getEmpresaId() + " AND " + FIELD_AGENDA_ID.getFieldName() + "=" + agendaHorarioInfo.getAgendaId()));
      // usuários
      agendaHorarioInfo.setUsuarioInclusaoId(getFacade().getLoggedUser().getId());
      agendaHorarioInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      agendaHorarioInfo.setDataHoraInclusao(new Timestamp(System.currentTimeMillis())); 
      agendaHorarioInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // insere o registro
      super.insert(agendaHorarioInfo);
      // salva tudo
      getFacade().commitTransaction();
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Retorna um AgendaHorarioInfo referente a(o) AgendaHorario
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param agendaId Agenda ID.
   * @param empresaId Empresa ID.
   * @param agendaHorarioId Agenda Horário ID.
   * @return Retorna um AgendaHorarioInfo referente a(o) AgendaHorario
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AgendaHorarioInfo selectByPrimaryKey(
                int empresaId,
                int agendaId,
                int agendaHorarioId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) AND " +
                                                  "(" + FIELD_AGENDA_ID.getFieldName(getTableName()) + " = ?) AND " +
                                                  "(" + FIELD_AGENDA_HORARIO_ID.getFieldName(getTableName()) + " = ?) "
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, agendaId);
      statement.setInt(3, agendaHorarioId);
      AgendaHorarioInfo[] result = (AgendaHorarioInfo[])select(statement);
      // se não encontramos...
      if (result.length == 0)
        throw new RecordNotFoundException(getClass().getName(), "selectByPrimaryKey", "Nenhum registro encontrado.");
      // se encontramos mais...
      else if (result.length > 1)
        throw new ManyRecordsFoundException(getClass().getName(), "selectByPrimaryKey", "Mais de um registro encontrado.");
      else {
        // salva tudo
        getFacade().commitTransaction();
        // retorna
        return result[0];
      } // if
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Retorna um AgendaHorarioInfo[] contendo a lista de AgendaHorario
   * indicados(as) pelos parâmetros de pesquisa.
   * @param empresaId Empresa ID ou 0 para todas.
   * @param agendaId Agenda ID ou 0 para todas.
   * @param contatoId Contato ID ou 0 para todos.
   * @param dataAgendamento Data Agendamento ou DateTools.ZERO_DATE para todos.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um AgendaHorarioInfo[] contendo a lista de AgendaHorario
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AgendaHorarioInfo[] selectByFilter(
                int empresaId,
                int agendaId,
                int contatoId,
                Timestamp dataAgendamento,
                Paginate paginate) throws Exception {
    // se não informou pelo menos um valor...exceção
    if ((empresaId == 0) && (agendaId == 0) && (contatoId == 0) && dataAgendamento.equals(DateTools.ZERO_DATE))
      throw new ExtendedException(getClass().getName(), "selectByFilter", "Obrigatório informar pelo menos um dos valores.");
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_EMPRESA_ID.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "((" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) OR (" + empresaId + " = 0)) AND " +
                                                  "((" + FIELD_AGENDA_ID.getFieldName(getTableName()) + " = ?) OR (" + agendaId + " = 0)) AND " +
                                                  "((" + FIELD_CONTATO_ID.getFieldName(getTableName()) + " = ?) OR (" + contatoId + " = 0)) AND " +
                                                  "((TO_CHAR(" + FIELD_DATA_AGENDAMENTO.getFieldName(getTableName()) + ", 'DD/MM/YYYY') = ?) OR (? = ?)) ",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setInt(1, empresaId);
      statement.setInt(2, agendaId);
      statement.setInt(3, contatoId);
      statement.setString(4, DateTools.formatDate(dataAgendamento));
      statement.setTimestamp(5, dataAgendamento);
      statement.setTimestamp(6, DateTools.ZERO_DATE);
      // nosso resultado
      AgendaHorarioInfo[] result = (AgendaHorarioInfo[])select(statement);
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      return result;
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Retorna um AgendaHorarioInfo[] contendo os últimos 30 registros de AgendaHorario
   * indicados(as) pelos parâmetros de pesquisa.
   * @param contatoId Contato ID.
   * @return Retorna um AgendaHorarioInfo[] contendo os últimos 30 registros de AgendaHorario
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AgendaHorarioInfo[] lastRecents(int contatoId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_DATA_AGENDAMENTO.getFieldName(getTableName()) + " DESC ", FIELD_HORA_AGENDAMENTO.getFieldName(getTableName()) + " DESC "};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_CONTATO_ID.getFieldName(getTableName()) + " = ?) ",
                                                  new String[]{},
                                                  new Paginate(29,0)
                                                 );
      statement.setInt(1, contatoId);
      // nosso resultado
      AgendaHorarioInfo[] result = (AgendaHorarioInfo[])select(statement);
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      return result;
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }
  
  /**
   * Atualiza o(a) AgendaHorario identificado(a) por 'agendaHorarioInfo'.
   * @param agendaHorarioInfo AgendaHorarioInfo contendo as informações do(a) AgendaHorario que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(AgendaHorarioInfo agendaHorarioInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(agendaHorarioInfo);
      // usuários
      agendaHorarioInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      agendaHorarioInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // atualiza o registro
      super.update(agendaHorarioInfo);
      // salva tudo
      getFacade().commitTransaction();
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Valida o(a) AgendaHorario identificado(a) por 'agendaHorarioInfo'.
   * @param agendaHorarioInfo AgendaHorarioInfo contendo as informações do(a) AgendaHorario que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(AgendaHorarioInfo agendaHorarioInfo) throws Exception {
  }

}
