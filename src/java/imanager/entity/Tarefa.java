     
package imanager.entity;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;
import iobjects.ui.entity.*;
import iobjects.util.*;

import securityservice.entity.*;

import imanager.misc.*;

/**
 * Representa a entidade Tarefa no banco de dados da aplica��o.
 */
public class Tarefa extends Entity {

  // nossos faqs
  FAQ FAQ_TUTORIAL_TAREFA = new FAQ("tutorialTarefa", " Passo a Passo ", "Tarefa: cadastro de uma nova Tarefa.", "", "tutorial_tarefa.html", "tutorial_tarefa.zip");

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.entity.Tarefa";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION          = new Action("tarefa", "Tarefa", "Mant�m o cadastro de Tarefas realizadas para os Clientes.", HELP, "entity/tarefa.jsp", "CRM", "", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("tarefaCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/tarefacadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT                       = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT                     = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE                     = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE                       = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que est� sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH                     = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os par�metros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa ID", "", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_TAREFA_ID = new EntityField("in_tarefa_id", "ID", "Informa o ID da Tarefa.", "tarefaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DEPARTAMENTO_ID = new EntityField("in_departamento_id", "Departamento ID", "", "departamentoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigat�rio selecionar o Departamento.");
  static public final EntityField FIELD_USUARIO_ID = new EntityField("in_usuario_id", "Usu�rio ID", "", "usuarioId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigat�rio selecionar o Usu�rio Respons�vel.");
  static public final EntityField FIELD_ASSUNTO_ID = new EntityField("in_assunto_id", "Assunto ID", "", "assuntoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigat�rio selecionar o Assunto.");
  static public final EntityField FIELD_PRIORIDADE = new EntityField("sm_prioridade", "Prioridade", "", "prioridade", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_CLIENTE_ID = new EntityField("in_cliente_id", "Cliente ID", "", "clienteId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigat�rio selecionar o Cliente.");
  static public final EntityField FIELD_PRAZO_ORIGINAL = new EntityField("da_prazo_original", "Prazo Original", "Informe o Prazo Original para conclus�o.", "prazoOriginal", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, false, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_PRAZO = new EntityField("da_prazo", "Prazo", "Informe o Prazo para conclus�o.", "prazo", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", true, "", "value !=''", "Obrigat�rio informar o Prazo.");
  static public final EntityField FIELD_PRORROGACAO = new EntityField("sm_prorrogacao", "Prorroga��o", "Informa a quantidade de Prorroga��es do Prazo.", "prorrogacao", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_STATUS = new EntityField("sm_status", "Status", "Selecione o Status.", "status", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_DESCRICAO = new EntityField("va_descricao", "Descri��o", "Informe o Descri��o.", "descricao", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);
  static public final EntityField FIELD_DATA_INCLUSAO = new EntityField("da_inclusao", "Data Inclus�o", "", "dataInclusao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_DATE, "", true);
  static public final EntityField FIELD_USUARIO_INCLUSAO_ID = new EntityField("in_usuario_inclusao_id", "Usu�rio de Inclus�o", "", "usuarioInclusaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_DATA_HORA_ALTERACAO = new EntityField("dt_alteracao", "Data/Hora Altera��o", "", "dataHoraAlteracao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_DATE, "", true);
  static public final EntityField FIELD_USUARIO_ALTERACAO_ID = new EntityField("in_usuario_alteracao_id", "Usu�rio de Altera��o", "", "usuarioAlteracaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_LINK_EXTERNO = new EntityField("va_link_externo", "Endere�o", "Informe o Endere�o.", "linkExterno", Types.VARCHAR, 2000, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  // nossos lookups
  static public final EntityLookup LOOKUP_EMPRESA = new EntityLookup("lookupEmpresa", "Empresa", "", Empresa.CLASS_NAME, new EntityField[]{FIELD_EMPRESA_ID}, new EntityField[]{Empresa.FIELD_NOME}, new EntityField[]{Empresa.FIELD_NOME}, "", true, true);
  static public final EntityLookup LOOKUP_DEPARTAMENTO = new EntityLookup("lookupDepartamento", "Departamento", "Selecione o Departamento.", Departamento.CLASS_NAME, new EntityField[]{FIELD_EMPRESA_ID, FIELD_DEPARTAMENTO_ID}, new EntityField[]{Departamento.FIELD_NOME}, new EntityField[]{Departamento.FIELD_NOME});
  static public final EntityLookup LOOKUP_USUARIO = new EntityLookup("lookupUsuario", "Usu�rio Respons�vel", "Selecione o Usu�rio respons�vel.", Usuario.CLASS_NAME, FIELD_USUARIO_ID, Usuario.FIELD_NOME, Usuario.FIELD_NOME);
  static public final EntityLookup LOOKUP_ASSUNTO = new EntityLookup("lookupAssunto", "Assunto", "Selecione o Assunto.", Assunto.CLASS_NAME, FIELD_ASSUNTO_ID, Assunto.FIELD_NOME, Assunto.FIELD_NOME);
  static public final EntityLookup LOOKUP_CLIENTE = new EntityLookup("lookupCliente", "Cliente", "Selecione o Cliente.", Contato.CLASS_NAME, FIELD_CLIENTE_ID, Contato.FIELD_NOME, Contato.FIELD_NOME);
  // nossos par�metros de usu�rio
  public final Param USER_PARAM_TAREFA_ID = new Param("userParamTarefaId", "ID", "Informe o ID da Tarefa.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_DEPARTAMENTO_ID = new Param("userParamDepartamentoId", "Departamento", "Selecione o Departamento.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_ASSUNTO_ID = new Param("userParamAssuntoId", "Assunto ID", "Selecione o Assunto.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_STATUS = new Param("userParamStatus", "Status", "Selecione o Status.", StatusTarefa.TODOS + "", 5, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_CLIENTE_ID = new Param("userParamClienteId", "Cliente", "Selecione o Cliente.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_CLIENTE_USER = new Param("userParamClienteId" + ExternalLookup.USER, "");
  public final Param USER_PARAM_USUARIO_ID = new Param("userParamUsuarioId", "Usu�rio", "Selecione o Usu�rio.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_USUARIO_USER = new Param("userParamUsuarioId" + ExternalLookup.USER, "");
  public final Param USER_PARAM_DATA_INICIAL = new Param("userParamDataInicial", "De", "Informe a Data Inicial.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigat�rio informar a Data Inicial.");
  public final Param USER_PARAM_DATA_FINAL = new Param("userParamDataFinal", "At�", "Informe a Data Final.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigat�rio informar a Data Final.");
  public final Param USER_PARAM_ATRASADAS = new Param("userParamAtrasadas", "Atrasadas", "Selecione se mostra as Atrasadas.", NaoSim.SIM + "", 5, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  // nosso par�metro de controle
  public final Param CONTROL_PARAM_CENTRAL_ATENDIMENTO = new Param("controlParamCentraLAtendimento", "false");

  /**
   * Delimitador de n�vel entre CentroCustos.
   */
  static public final String LEVEL_DELIMITER = "/";

  static {
    FIELD_STATUS.setLookupList(StatusTarefa.LOOKUP_LIST_FOR_FIELD);
  }

  {
    USER_PARAM_STATUS.setLookupList(StatusTarefa.LOOKUP_LIST_FOR_PARAM);
    USER_PARAM_ATRASADAS.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    USER_PARAM_DATA_INICIAL.setValue(DateTools.formatDate(DateTools.getActualDate()));
    USER_PARAM_DATA_FINAL.setValue(DateTools.formatDate(DateTools.getCalculatedDays(30)));
    // *
    USER_PARAM_DATA_FINAL.setSpecialConstraint(true, USER_PARAM_DATA_INICIAL);
  }

  /**
   * Construtor padr�o.
   */
  public Tarefa() {
    // nossas faqs
    faqList().add(FAQ_TUTORIAL_TAREFA);
    // nossas a��es
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_tarefa");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_TAREFA_ID);  
    fieldList().add(FIELD_DEPARTAMENTO_ID);  
    fieldList().add(FIELD_USUARIO_ID);  
    fieldList().add(FIELD_ASSUNTO_ID);  
    fieldList().add(FIELD_PRIORIDADE);  
    fieldList().add(FIELD_CLIENTE_ID);  
    fieldList().add(FIELD_PRAZO_ORIGINAL);
    fieldList().add(FIELD_PRAZO);
    fieldList().add(FIELD_PRORROGACAO);  
    fieldList().add(FIELD_STATUS);  
    fieldList().add(FIELD_DESCRICAO);  
    fieldList().add(FIELD_DATA_INCLUSAO);  
    fieldList().add(FIELD_USUARIO_INCLUSAO_ID);  
    fieldList().add(FIELD_DATA_HORA_ALTERACAO);  
    fieldList().add(FIELD_USUARIO_ALTERACAO_ID);
    fieldList().add(FIELD_LINK_EXTERNO);
    // nossos lookups
    lookupList().add(LOOKUP_EMPRESA);
    lookupList().add(LOOKUP_DEPARTAMENTO);
    lookupList().add(LOOKUP_USUARIO);
    lookupList().add(LOOKUP_ASSUNTO);
    lookupList().add(LOOKUP_CLIENTE);
    // nossos par�metros de usu�rio
    userParamList().add(USER_PARAM_TAREFA_ID);
    userParamList().add(USER_PARAM_DEPARTAMENTO_ID);
    userParamList().add(USER_PARAM_ASSUNTO_ID);
    userParamList().add(USER_PARAM_STATUS);
    userParamList().add(USER_PARAM_CLIENTE_ID);
    userParamList().add(USER_PARAM_CLIENTE_USER);
    userParamList().add(USER_PARAM_USUARIO_ID);
    userParamList().add(USER_PARAM_USUARIO_USER);
    userParamList().add(USER_PARAM_DATA_INICIAL);
    userParamList().add(USER_PARAM_DATA_FINAL);
    userParamList().add(USER_PARAM_ATRASADAS);
  }

  /**
   * Exclui o(a) Tarefa informado(a) por 'tarefaInfo'.
   * @param tarefaInfo TarefaInfo referente a(o) Tarefa
   *        que se deseja excluir.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(TarefaInfo tarefaInfo) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // nossa inst�ncia de Tarefa Encaminhamento
      TarefaEncaminhamento tarefaEncaminhamento = (TarefaEncaminhamento)getFacade().getEntity(TarefaEncaminhamento.CLASS_NAME);
      // exclui os encaminhamentos
      TarefaEncaminhamentoInfo[] tarefaEncaminhamentoInfoList = tarefaEncaminhamento.selectByFilter(tarefaInfo.getEmpresaId(), tarefaInfo.getTarefaId(), null);
      for (int i=0; i<tarefaEncaminhamentoInfoList.length; i++)
        tarefaEncaminhamento.delete(tarefaEncaminhamentoInfoList[i]);
      // exclui o registro
      super.delete(tarefaInfo);
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
   * Insere o(a) Tarefa identificado(a) por 'tarefaInfo'.
   * @param tarefaInfo TarefaInfo contendo as informa��es do(a) Tarefa que se
   *                    deseja incluir.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(TarefaInfo tarefaInfo) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(tarefaInfo);
      // valor da sequ�ncia
      tarefaInfo.setTarefaId(getNextSequence(FIELD_TAREFA_ID, FIELD_EMPRESA_ID.getFieldName() + "=" + tarefaInfo.getEmpresaId()));
      // insere no prazo original
      tarefaInfo.setPrazoOriginal(tarefaInfo.getPrazo());
      // usu�rios
      tarefaInfo.setUsuarioInclusaoId(getFacade().getLoggedUser().getId());
      tarefaInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      tarefaInfo.setDataInclusao(new Timestamp(DateTools.getActualDate().getTime())); // apenas a data
      tarefaInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // insere o registro
      super.insert(tarefaInfo);
      // salva tudo
      getFacade().commitTransaction();
      
      // atualiza para obter os lookups
      tarefaInfo = (TarefaInfo)refresh(tarefaInfo);
      // envia e-mail ao usu�rio respons�vel
      sendMailUsuarioResponsavel(tarefaInfo,  "");
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Retorna um TarefaInfo referente a(o) Tarefa
   * indicado(a) pelos par�metros que representam sua chave prim�ria.
   * @param empresaId Empresa ID.
   * @param tarefaId Tarefa ID.
   * @return Retorna um TarefaInfo referente a(o) Tarefa
   * indicado pelos par�metros que representam sua chave prim�ria.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public TarefaInfo selectByPrimaryKey(
                int empresaId,
                int tarefaId
           ) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_TAREFA_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, tarefaId);
      TarefaInfo[] result = (TarefaInfo[])select(statement);
      // se n�o encontramos...
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
   * Retorna um TarefaInfo[] contendo a lista de Tarefa
   * indicados(as) pelos par�metros de pesquisa.
   * @param empresaId Empresa ID ou 0.
   * @param tarefaId Tarefa ID ou 0.
   * @param departamentoId Departamento ID ou 0.
   * @param assuntoId Assunto ID ou 0.
   * @param status Status ou Status.TODOS.
   * @param usuarioId Usu�rio ID ou 0.
   * @param clienteId Cliente ID ou 0.
   * @param dataInicial Data Inicial.
   * @param dataFinal Data Final.
   * @param atrasadas NaoSim.SIM para que as Tarefas atrasadas sejam inclu�das.
   * @param paginate Informa��o de pagina��o dos resultados ou null.
   * @return Retorna um TarefaInfo[] contendo a lista de Tarefa
   * indicados(as) pelos par�metros de pesquisa.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public TarefaInfo[] selectByFilter(
                int empresaId,
                int tarefaId,
                int departamentoId,
                int assuntoId,
                int status,
                int clienteId,
                int usuarioId,
                Timestamp dataInicial,
                Timestamp dataFinal,
                int atrasadas,
                Paginate paginate) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_EMPRESA_ID.getFieldName(getTableName()), FIELD_PRAZO.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "((" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) OR (" + empresaId + " = 0)) AND " +
                                                  "((" + FIELD_TAREFA_ID.getFieldName(getTableName()) + " = ?) OR (" + tarefaId + " = 0)) AND " +
                                                  "((" + FIELD_DEPARTAMENTO_ID.getFieldName(getTableName()) + " = ?) OR (" + departamentoId + " = 0)) AND " +
                                                  "((" + FIELD_ASSUNTO_ID.getFieldName(getTableName()) + " = ?) OR (" + assuntoId + " = 0)) AND " +
                                                  "((" + FIELD_STATUS.getFieldName(getTableName()) + " = ?) OR (" + status + " = " + StatusTarefa.TODOS + ")) AND " +
                                                  "((" + FIELD_CLIENTE_ID.getFieldName(getTableName()) + " = ?) OR (" + clienteId + " = 0)) AND " +
                                                  "((" + FIELD_USUARIO_ID.getFieldName(getTableName()) + " = ?) OR (" + usuarioId + " = 0)) AND " +
                                                  "(" +
                                                    "((" + FIELD_PRAZO.getFieldName(getTableName()) + " >= ?) AND (" + FIELD_PRAZO.getFieldName(getTableName()) + " <= ?)) OR " +
                                                    "((" + atrasadas + " = " + NaoSim.SIM + ") AND (" + FIELD_STATUS.getFieldName(getTableName()) + " <> " + StatusTarefa.CONCLUIDA + ") AND (" + FIELD_PRAZO.getFieldName(getTableName()) + " < ?)) " +
                                                  ")",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setInt(1, empresaId);
      statement.setInt(2, tarefaId);
      statement.setInt(3, departamentoId);
      statement.setInt(4, assuntoId);
      statement.setInt(5, status);
      statement.setInt(6, clienteId);
      statement.setInt(7, usuarioId);
      statement.setTimestamp(8, dataInicial);
      statement.setTimestamp(9, dataFinal);
      statement.setTimestamp(10, new Timestamp(DateTools.getActualDate().getTime()));
      // nosso resultado
      TarefaInfo[] result = (TarefaInfo[])select(statement);
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
   * Retorna um TarefaInfo[] contendo os �ltimos 30 registros de Tarefa
   * indicados(as) pelos par�metros de pesquisa.
   * @param clienteId Cliente ID.
   * @return Retorna um TarefaInfo[] contendo os �ltimos 30 registros de Tarefa
   * indicados(as) pelos par�metros de pesquisa.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public TarefaInfo[] lastRecents(int clienteId) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_PRAZO.getFieldName(getTableName()) + " DESC "};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_CLIENTE_ID.getFieldName(getTableName()) + " = ?) ",
                                                  new String[]{},
                                                  new Paginate(29,0)
                                                 );
      statement.setInt(1, clienteId);
      // nosso resultado
      TarefaInfo[] result = (TarefaInfo[])select(statement);
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
   * Envia um e-mail avisando ao usu�rio respons�vel pela Tarefa.
   * @param tarefaInfo
   * @param encaminhamento 
   * @throws Exception 
   */
  public void sendMailUsuarioResponsavel(TarefaInfo tarefaInfo,
                                         String     encaminhamento) throws Exception {
    // avisa ao usu�rio (por e-mail) que ele tem uma nova tarefa
    Empresa empresa = (Empresa)getFacade().getEntity(Empresa.CLASS_NAME);
    empresa.sendMail(tarefaInfo.getEmpresaId(), 
                     tarefaInfo.getUsuarioId(), 
                     new String[]{}, 
                     new String[]{}, 
                     "Nova tarefa", 
                     "Uma tarefa acaba de ser " + (encaminhamento.isEmpty() ? "inserida" : "encaminhada" ) + " para voc�: \r\n\r\n" 
                     + "ID\r\n" + tarefaInfo.getTarefaId() + "\r\n\r\n"
                     + "Cliente\r\n" + tarefaInfo.lookupValueList().get(Tarefa.LOOKUP_CLIENTE).getDisplayFieldValuesToString() + "\r\n\r\n"
                     + "Departamento\r\n" + tarefaInfo.lookupValueList().get(Tarefa.LOOKUP_DEPARTAMENTO).getDisplayFieldValuesToString() + "\r\n\r\n"
                     + "Assunto\r\n" + tarefaInfo.lookupValueList().get(Tarefa.LOOKUP_ASSUNTO).getDisplayFieldValuesToString() + "\r\n\r\n"
                     + "Prazo\r\n" + DateTools.formatDate(tarefaInfo.getPrazo()) + "\r\n\r\n"
                     + (encaminhamento.isEmpty() ? "" : "Encaminhamento\r\n" + encaminhamento)
                    );
  }
  
  /**
   * Envia um e-mail avisando que a Tarefa foi conclu�da.
   * @param tarefaInfo
   * @throws Exception 
   */
  public void sendMailTarefaConcluida(TarefaInfo tarefaInfo) throws Exception {
    // avisa ao usu�rio (por e-mail) que ele tem uma nova tarefa
    Empresa empresa = (Empresa)getFacade().getEntity(Empresa.CLASS_NAME);
    empresa.sendMail(tarefaInfo.getEmpresaId(), 
                     new String[]{}, 
                     new String[]{}, 
                     "Tarefa conclu�da", 
                     "Uma tarefa acaba de ser conclu�da: \r\n\r\n" 
                     + "ID\r\n" + tarefaInfo.getTarefaId() + "\r\n\r\n"
                     + "Cliente\r\n" + tarefaInfo.lookupValueList().get(Tarefa.LOOKUP_CLIENTE).getDisplayFieldValuesToString() + "\r\n\r\n"
                     + "Departamento\r\n" + tarefaInfo.lookupValueList().get(Tarefa.LOOKUP_DEPARTAMENTO).getDisplayFieldValuesToString() + "\r\n\r\n"
                     + "Assunto\r\n" + tarefaInfo.lookupValueList().get(Tarefa.LOOKUP_ASSUNTO).getDisplayFieldValuesToString() + "\r\n\r\n"
                     + "Respons�vel\r\n" + tarefaInfo.lookupValueList().get(Tarefa.LOOKUP_USUARIO).getDisplayFieldValuesToString() + "\r\n\r\n"
                     + "Prazo Original\r\n" + DateTools.formatDate(tarefaInfo.getPrazoOriginal())
                    );
  }
  
  /**
   * Atualiza o(a) Tarefa identificado(a) por 'tarefaInfo'.
   * @param tarefaInfo TarefaInfo contendo as informa��es do(a) Tarefa que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public TarefaInfo update(TarefaInfo tarefaInfo) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(tarefaInfo);
      // Obt�m o registro atual do banco
      TarefaInfo tarefaInfoTemp = selectByPrimaryKey(tarefaInfo.getEmpresaId(), tarefaInfo.getTarefaId());
      // usu�rios
      tarefaInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      tarefaInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // se o prazo foi alterado para maior... prorroga��o � incrementada
      if (tarefaInfo.getPrazo().after(tarefaInfoTemp.getPrazo()))
        tarefaInfo.setProrrogacao(tarefaInfo.getProrrogacao() + 1);
      // atualiza o registro
      super.update(tarefaInfo);
      // salva tudo
      getFacade().commitTransaction();
      
      // envia e-mail de conclus�o
      if (tarefaInfoTemp.getStatus() != StatusTarefa.CONCLUIDA && tarefaInfo.getStatus() == StatusTarefa.CONCLUIDA)
        sendMailTarefaConcluida(tarefaInfoTemp);

      // retorna
      return tarefaInfo;
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Valida o(a) Tarefa identificado(a) por 'tarefaInfo'.
   * @param tarefaInfo TarefaInfo contendo as informa��es do(a) Tarefa que se
   *                    deseja validar.
   * @throws Exception Em caso de exce��o no preenchimento dos dados informados.
   */
  public void validate(TarefaInfo tarefaInfo) throws Exception {
  }

}
