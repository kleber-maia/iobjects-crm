     
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
 * Representa a entidade Tarefa Encaminhamento no banco de dados da aplicação.
 */
public class TarefaEncaminhamento extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.TarefaEncaminhamento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = Tarefa.ACTION.addNestedAction(new Action("tarefaEncaminhamento", "Encaminhamento", "Mantém o cadastro dos Encaminhamentos de uma Tarefa entre Usuários e Departamentos.", HELP, "entity/tarefaencaminhamento.jsp", "CRM", "", Action.CATEGORY_ENTITY, false, true));
  static public final Action ACTION_CADASTRO = new Action("tarefaEncaminhamentoCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/tarefaencaminhamentocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa ID", "", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_TAREFA_ID = new EntityField("in_tarefa_id", "Tarefa ID", "", "tarefaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_TAREFA_ENCAMINHAMENTO_ID = new EntityField("in_tarefa_encaminhamento_id", "ID", "", "tarefaEncaminhamentoId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_DEPARTAMENTO_ID = new EntityField("in_departamento_id", "Departamento ID", "", "departamentoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigatório selecionar o Departamento.");
  static public final EntityField FIELD_USUARIO_ID = new EntityField("in_usuario_id", "Usuário ID", "", "usuarioId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigatório selecionar o Usuário.");
  static public final EntityField FIELD_DESCRICAO = new EntityField("va_descricao", "Descrição", "Informe a Descrição.", "descricao", Types.VARCHAR, 500, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", false);
  static public final EntityField FIELD_USUARIO_INCLUSAO_ID = new EntityField("in_usuario_inclusao_id", "Usuário Inclusão ID", "", "usuarioInclusaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_DATA_HORA_INCLUSAO = new EntityField("dt_inclusao", "Data/Hora", "Data/Hora de Inclusão.", "dataHoraInclusao", Types.TIMESTAMP, 10, 0, false, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_DATE_TIME, "", true);
  // nossos lookups
  static public final EntityLookup LOOKUP_DEPARTAMENTO = new EntityLookup("lookupDepartamento", "Departamento", "Selecione o Departamento.", Departamento.CLASS_NAME, new EntityField[]{FIELD_EMPRESA_ID, FIELD_DEPARTAMENTO_ID}, new EntityField[]{Departamento.FIELD_NOME}, new EntityField[]{Departamento.FIELD_NOME});
  static public final EntityLookup LOOKUP_USUARIO = new EntityLookup("lookupUsuario", "Usuário", "", Usuario.CLASS_NAME, FIELD_USUARIO_ID, Usuario.FIELD_NOME, Usuario.FIELD_NOME);
  static public final EntityLookup LOOKUP_USUARIO_INCLUSAO = new EntityLookup("lookupUsuarioInclusao", "Inclusão", "", Usuario.CLASS_NAME, FIELD_USUARIO_INCLUSAO_ID, Usuario.FIELD_NOME, Usuario.FIELD_NOME);
  // nossos parâmetros de usuário
  public final Param USER_PARAM_EMPRESA_ID = new Param("userParamEmpresaId", "Empresa ID", "Informe o(a) Empresa ID.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER, "", "value != ''", "Obrigatório informar  Empresa ID.");
  public final Param USER_PARAM_TAREFA_ID = new Param("userParamTarefaId", "Tarefa ID", "Informe o(a) Tarefa ID.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER, "", "value != ''", "Obrigatório informar  Tarefa ID.");

  /**
   * Construtor padrão.
   */
  public TarefaEncaminhamento() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_tarefa_encaminhamento");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_TAREFA_ID);  
    fieldList().add(FIELD_TAREFA_ENCAMINHAMENTO_ID);  
    fieldList().add(FIELD_DEPARTAMENTO_ID);  
    fieldList().add(FIELD_USUARIO_ID);  
    fieldList().add(FIELD_DESCRICAO);  
    fieldList().add(FIELD_USUARIO_INCLUSAO_ID);  
    fieldList().add(FIELD_DATA_HORA_INCLUSAO);
    // nossos lookups
    lookupList().add(LOOKUP_DEPARTAMENTO);
    lookupList().add(LOOKUP_USUARIO);
    lookupList().add(LOOKUP_USUARIO_INCLUSAO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_EMPRESA_ID);
    userParamList().add(USER_PARAM_TAREFA_ID);
  }

  /**
   * Exclui o(a) Tarefa Encaminhamento informado(a) por 'tarefaEncaminhamentoInfo'.
   * @param tarefaEncaminhamentoInfo TarefaEncaminhamentoInfo referente a(o) Tarefa Encaminhamento
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(TarefaEncaminhamentoInfo tarefaEncaminhamentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(tarefaEncaminhamentoInfo);
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
   * Insere o(a) Tarefa Encaminhamento identificado(a) por 'tarefaEncaminhamentoInfo'.
   * @param tarefaEncaminhamentoInfo TarefaEncaminhamentoInfo contendo as informações do(a) Tarefa Encaminhamento que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(TarefaEncaminhamentoInfo tarefaEncaminhamentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(tarefaEncaminhamentoInfo);
      // valor da sequência
      tarefaEncaminhamentoInfo.setTarefaEncaminhamentoId(getNextSequence(FIELD_TAREFA_ENCAMINHAMENTO_ID, FIELD_EMPRESA_ID.getFieldName() + "=" + tarefaEncaminhamentoInfo.getEmpresaId() + " AND " + FIELD_TAREFA_ID.getFieldName() + "=" + tarefaEncaminhamentoInfo.getTarefaId()));
      // usuários
      tarefaEncaminhamentoInfo.setUsuarioInclusaoId(getFacade().getLoggedUser().getId());
      tarefaEncaminhamentoInfo.setDataHoraInclusao(new Timestamp(System.currentTimeMillis()));
      // insere o registro
      super.insert(tarefaEncaminhamentoInfo);
      
      // nossa instância de Tarefa
      Tarefa tarefa = (Tarefa)getFacade().getEntity(Tarefa.CLASS_NAME);
      // seleciona a tarefa
      TarefaInfo tarefaInfo = tarefa.selectByPrimaryKey(tarefaEncaminhamentoInfo.getEmpresaId(), tarefaEncaminhamentoInfo.getTarefaId());
      // altera o departamento e o usuário
      tarefaInfo.setDepartamentoId(tarefaEncaminhamentoInfo.getDepartamentoId());
      tarefaInfo.setUsuarioId(tarefaEncaminhamentoInfo.getUsuarioId());
      // salva
      tarefa.update(tarefaInfo);
      
      // salva tudo
      getFacade().commitTransaction();
      
      // envia e-mail ao usuário responsável
      tarefa.sendMailUsuarioResponsavel(tarefaInfo, tarefaEncaminhamentoInfo.getDescricao());
      
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Retorna um TarefaEncaminhamentoInfo referente a(o) Tarefa Encaminhamento
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param empresaId Empresa ID.
   * @param tarefaId Tarefa ID.
   * @param tarefaEncaminhamentoId Tarefa Encaminhamento ID.
   * @return Retorna um TarefaEncaminhamentoInfo referente a(o) Tarefa Encaminhamento
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public TarefaEncaminhamentoInfo selectByPrimaryKey(
                int empresaId,
                int tarefaId,
                int tarefaEncaminhamentoId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_TAREFA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_TAREFA_ENCAMINHAMENTO_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, tarefaId);
      statement.setInt(3, tarefaEncaminhamentoId);
      TarefaEncaminhamentoInfo[] result = (TarefaEncaminhamentoInfo[])select(statement);
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
   * Retorna um TarefaEncaminhamentoInfo[] contendo a lista de Tarefa Encaminhamento
   * indicados(as) pelos parâmetros de pesquisa.
   * @param empresaId Empresa ID.
   * @param tarefaId Tarefa ID.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um TarefaEncaminhamentoInfo[] contendo a lista de Tarefa Encaminhamento
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public TarefaEncaminhamentoInfo[] selectByFilter(
                int empresaId,
                int tarefaId,
                Paginate paginate) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_EMPRESA_ID.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) AND " +
                                                  "(" + FIELD_TAREFA_ID.getFieldName(getTableName()) + " = ?)",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setInt(1, empresaId);
      statement.setInt(2, tarefaId);
      // nosso resultado
      TarefaEncaminhamentoInfo[] result = (TarefaEncaminhamentoInfo[])select(statement);
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
   * Valida o(a) Tarefa Encaminhamento identificado(a) por 'tarefaEncaminhamentoInfo'.
   * @param tarefaEncaminhamentoInfo TarefaEncaminhamentoInfo contendo as informações do(a) Tarefa Encaminhamento que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(TarefaEncaminhamentoInfo tarefaEncaminhamentoInfo) throws Exception {
  }

}
