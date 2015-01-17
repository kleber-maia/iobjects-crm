     
package imanager.entity;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*; 

import imanager.misc.*;
import iobjects.util.*;

/**
 * Representa a entidade AgendaFeriado no banco de dados da aplicação.
 */
public class AgendaFeriado extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.AgendaFeriado";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("agendaFeriado", "Agenda Feriado", "Mantém o cadastro de AgendaFeriado.", HELP, "entity/agendaferiado.jsp", "Contato", "Auxiliares", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("agendaFeriadoCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/agendaferiadocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_AGENDA_FERIADO_ID = new EntityField("in_agenda_feriado_id", "Agenda Feriado", "Informe o(a) Agenda Feriado ID.", "agendaFeriadoId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_FERIADO = new EntityField("da_feriado", "Data", "Selecione o a Data.", "feriado", Types.TIMESTAMP, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", true, "", "value !=''", "Obrigatório selecionar a Data");
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 150, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value !=''", "Obrigatório informar o Nome.");
  static public final EntityField FIELD_BLOQUEIO = new EntityField("sm_bloqueio", "Bloqueio", "Informe o(a) Bloqueio.", "bloqueio", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa", "Informe o(a) Empresa ID.", "empresaId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_AGENDA_ID = new EntityField("in_agenda_id", "Agenda", "Informe o(a) Agenda ID.", "agendaId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  // nossos parâmetros de usuário
  public final Param USER_PARAM_NOME = new Param("userParamNome", "Nome", "Informe o Nome.", "", 150, Param.ALIGN_LEFT, Param.FORMAT_UPPER_CASE);
  public final Param USER_PARAM_BLOQUEIO = new Param("userParamBloqueio", "Bloqueio", "Selecione o Bloqueio.", TipoBloqueio.TODOS + "", 5, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_ANO = new Param("userParamAno", "Ano", "Informe o Ano.", DateTools.getActualYear() + "", 4, Param.ALIGN_LEFT, Param.FORMAT_NONE);

  static {
    FIELD_BLOQUEIO.setLookupList(TipoBloqueio.LOOKUP_LIST_FOR_FIELD);
  }
  
  {
    USER_PARAM_BLOQUEIO.setLookupList(TipoBloqueio.LOOKUP_LIST_FOR_PARAM);
  }
  
  /**
   * Construtor padrão.
   */
  public AgendaFeriado() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("relacionamento_agenda_feriado");
    // nossos campos  
    fieldList().add(FIELD_AGENDA_FERIADO_ID);  
    fieldList().add(FIELD_FERIADO);  
    fieldList().add(FIELD_NOME);  
    fieldList().add(FIELD_BLOQUEIO);  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_AGENDA_ID);  
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_NOME);
    userParamList().add(USER_PARAM_BLOQUEIO);
    userParamList().add(USER_PARAM_ANO);
  }

  /**
   * Exclui o(a) AgendaFeriado informado(a) por 'agendaFeriadoInfo'.
   * @param agendaFeriadoInfo AgendaFeriadoInfo referente a(o) AgendaFeriado
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(AgendaFeriadoInfo agendaFeriadoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(agendaFeriadoInfo);
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
   * Insere o(a) AgendaFeriado identificado(a) por 'agendaFeriadoInfo'.
   * @param agendaFeriadoInfo AgendaFeriadoInfo contendo as informações do(a) AgendaFeriado que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(AgendaFeriadoInfo agendaFeriadoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(agendaFeriadoInfo);
      // valor padrão
      agendaFeriadoInfo.setAgendaFeriadoId(getNextSequence(FIELD_AGENDA_FERIADO_ID));
      // se o bloqueio é total... zera a empresa e a agenda
      if (agendaFeriadoInfo.getBloqueio() == TipoBloqueio.TOTAL) {
        agendaFeriadoInfo.setEmpresaId(0);
        agendaFeriadoInfo.setAgendaId(0);
      } 
      // se o bloqueio é por empresa... zera a agenda
      else if (agendaFeriadoInfo.getBloqueio() == TipoBloqueio.EMPRESA)
        agendaFeriadoInfo.setAgendaId(0);
      // insere o registro
      super.insert(agendaFeriadoInfo);
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
   * Retorna um AgendaFeriadoInfo referente a(o) AgendaFeriado
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param agendaFeriadoId Agenda Feriado ID.
   * @return Retorna um AgendaFeriadoInfo referente a(o) AgendaFeriado
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AgendaFeriadoInfo selectByPrimaryKey(
                int agendaFeriadoId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_AGENDA_FERIADO_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, agendaFeriadoId);
      AgendaFeriadoInfo[] result = (AgendaFeriadoInfo[])select(statement);
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
   * Retorna um AgendaFeriadoInfo[] contendo a lista de AgendaFeriado
   * indicados(as) pelos parâmetros de pesquisa.
   * @param nome Nome ou vazio.
   * @param bloqueio Bloqueio ou TipoBloqueio.TODOS.
   * @param dataInicial Data Inicial.
   * @param dataFinal Data Final.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um AgendaFeriadoInfo[] contendo a lista de AgendaFeriado
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AgendaFeriadoInfo[] selectByFilter(
                String nome,
                int bloqueio,
                int ano,
                Paginate paginate) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // se não informou pelo menos um valor...dispara
      if (nome.equals("") && ano == 0)
        throw new ExtendedException(CLASS_NAME, "selectByFilter", "Obrigatório informar pelo menos um dos parâmetros.");
      // prepara a consulta
      String[] orderFieldNames = {FIELD_FERIADO.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "((" + FIELD_BLOQUEIO.getFieldName(getTableName()) + " = ?) OR ( " + bloqueio + " = " + TipoBloqueio.TODOS + ")) AND " +
                                                  "(TO_CHAR(" + FIELD_FERIADO.getFieldName(getTableName()) + ", 'YYYY') = ?) ",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setString(1, nome + "%");
      statement.setInt(2, bloqueio);
      statement.setString(3, ano + "");
      // nosso resultado
      AgendaFeriadoInfo[] result = (AgendaFeriadoInfo[])select(statement);
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
   * Retorna um AgendaFeriadoInfo[] contendo a lista de AgendaFeriado
   * da semana.
   * @param nome Nome ou vazio.
   * @param bloqueio Bloqueio ou TipoBloqueio.TODOS.
   * @param dataInicial Data Inicial.
   * @param dataFinal Data Final.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um AgendaFeriadoInfo[] contendo a lista de AgendaFeriado
   * da semana.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AgendaFeriadoInfo[] selectWeek(
                String nome,
                int bloqueio,
                Timestamp dataInicial,
                Timestamp dataFinal,
                Paginate paginate) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_FERIADO.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "((" + FIELD_BLOQUEIO.getFieldName(getTableName()) + " = ?) OR ( " + bloqueio + " = " + TipoBloqueio.TODOS + ")) AND " +
                                                  "((" + FIELD_FERIADO.getFieldName(getTableName()) + " >= ?) AND (" + FIELD_FERIADO.getFieldName(getTableName()) + " <= ?))",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setString(1, nome + "%");
      statement.setInt(2, bloqueio);
      statement.setTimestamp(3, dataInicial);
      statement.setTimestamp(4, dataFinal);
      // nosso resultado
      AgendaFeriadoInfo[] result = (AgendaFeriadoInfo[])select(statement);
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
   * Retorna um AgendaFeriadoInfo[] contendo a lista de AgendaFeriado
   * indicados(as) pelos parâmetros de pesquisa.
   * @param empresaId int Empresa ID ou 0 para todas.
   * @param agendaId int Agenda ID ou 0 para todas.
   * @param data Timestamp Data.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um AgendaFeriadoInfo[] contendo a lista de AgendaFeriado
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AgendaFeriadoInfo[] selectFeriado(
                int empresaId,
                int agendaId,
                Timestamp data) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_FERIADO.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "((" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) OR (" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = 0)) AND " +
                                                  "((" + FIELD_AGENDA_ID.getFieldName(getTableName()) + " = ?) OR (" + FIELD_AGENDA_ID.getFieldName(getTableName()) + " = 0)) AND " +
                                                  "(" + FIELD_FERIADO.getFieldName(getTableName()) + " = ?) " 
                                                 );
      statement.setInt(1, empresaId);
      statement.setInt(2, agendaId);
      statement.setTimestamp(3, data);
      // nosso resultado
      AgendaFeriadoInfo[] result = (AgendaFeriadoInfo[])select(statement);
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
   * Atualiza o(a) AgendaFeriado identificado(a) por 'agendaFeriadoInfo'.
   * @param agendaFeriadoInfo AgendaFeriadoInfo contendo as informações do(a) AgendaFeriado que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(AgendaFeriadoInfo agendaFeriadoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(agendaFeriadoInfo);
      // atualiza o registro
      super.update(agendaFeriadoInfo);
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
   * Valida o(a) AgendaFeriado identificado(a) por 'agendaFeriadoInfo'.
   * @param agendaFeriadoInfo AgendaFeriadoInfo contendo as informações do(a) AgendaFeriado que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(AgendaFeriadoInfo agendaFeriadoInfo) throws Exception {
  }

}
