     
package imanager.entity;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;

/**
 * Representa a entidade AtendimentoFrequente no banco de dados da aplicação.
 */
public class AtendimentoFrequente extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.AtendimentoFrequente";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("atendimentofrequente", "Atendimento Frequente", "Mantém o cadastro de Atendimento Frequente.", HELP, "entity/atendimentofrequente.jsp", "CRM", "Auxiliares", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("atendimentofrequenteCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/atendimentofrequentecadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa", "Informa a Empresa.", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_ATENDIMENTO_FREQUENTE_ID = new EntityField("in_atendimento_frequente_id", "Atendimento Frequente", "Informa o Atendimento Frequente.", "atendimentoFrequenteId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 200, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Nome.");
  static public final EntityField FIELD_DEPARTAMENTO_ID = new EntityField("in_departamento_id", "Departamento", "Selecione o Departamento.", "departamentoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_ASSUNTO_ID = new EntityField("in_assunto_id", "Assunto", "Selecione o Assunto.", "assuntoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_DESCRICAO = new EntityField("va_descricao", "Descrição", "Informe a Descrição.", "descricao", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);
  // nossos lookups
  static public final EntityLookup LOOKUP_DEPARTAMENTO = new EntityLookup("lookupDepartamento", "Departamento", "Selecione o Departamento.", Departamento.CLASS_NAME, new EntityField[]{FIELD_EMPRESA_ID, FIELD_DEPARTAMENTO_ID}, new EntityField[]{Departamento.FIELD_NOME}, new EntityField[]{Departamento.FIELD_NOME});
  static public final EntityLookup LOOKUP_ASSUNTO = new EntityLookup("lookupAssunto", "Assunto", "Selecione o Assunto.", Assunto.CLASS_NAME, FIELD_ASSUNTO_ID, Assunto.FIELD_NOME, Assunto.FIELD_NOME);
  // nossos parâmetros de usuário
  public final Param USER_PARAM_EMPRESA_ID = new Param("userParamEmpresaId", "Empresa", "Informa a Empresa.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER, "", "value != ''", "Obrigatório informar  Empresa ID.");
  public final Param USER_PARAM_NOME = new Param("userParamNome", "Nome", "Informe o Nome.", "", 200, Param.ALIGN_LEFT, Param.FORMAT_NONE);
  public final Param USER_PARAM_DEPARTAMENTO_ID = new Param("userParamDepartamentoId", "Departamento", "Selecione o Departamento.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_ASSUNTO_ID = new Param("userParamAssuntoId", "Assunto", "Selecione o Assunto.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);

  /**
   * Construtor padrão.
   */
  public AtendimentoFrequente() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_atendimento_frequente");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_ATENDIMENTO_FREQUENTE_ID);  
    fieldList().add(FIELD_NOME);  
    fieldList().add(FIELD_DEPARTAMENTO_ID);  
    fieldList().add(FIELD_ASSUNTO_ID);  
    fieldList().add(FIELD_DESCRICAO);
    // nossos lookups
    lookupList().add(LOOKUP_DEPARTAMENTO);
    lookupList().add(LOOKUP_ASSUNTO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_EMPRESA_ID);
    userParamList().add(USER_PARAM_NOME);
    userParamList().add(USER_PARAM_DEPARTAMENTO_ID);
    userParamList().add(USER_PARAM_ASSUNTO_ID);
  }

  /**
   * Exclui o(a) AtendimentoFrequente informado(a) por 'atendimentofrequenteInfo'.
   * @param atendimentofrequenteInfo AtendimentoFrequenteInfo referente a(o) AtendimentoFrequente
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(AtendimentoFrequenteInfo atendimentofrequenteInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(atendimentofrequenteInfo);
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
   * Insere o(a) AtendimentoFrequente identificado(a) por 'atendimentofrequenteInfo'.
   * @param atendimentofrequenteInfo AtendimentoFrequenteInfo contendo as informações do(a) AtendimentoFrequente que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(AtendimentoFrequenteInfo atendimentofrequenteInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(atendimentofrequenteInfo);
      // valor da sequencia
      atendimentofrequenteInfo.setAtendimentoFrequenteId(getNextSequence(FIELD_ATENDIMENTO_FREQUENTE_ID, FIELD_EMPRESA_ID.getFieldName() + "=" + atendimentofrequenteInfo.getEmpresaId()));
      // insere o registro
      super.insert(atendimentofrequenteInfo);
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
   * Retorna um AtendimentoFrequenteInfo referente a(o) AtendimentoFrequente
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param empresaId Empresa ID.
   * @param atendimentoFrequenteId Atendimento Frequente ID.
   * @return Retorna um AtendimentoFrequenteInfo referente a(o) AtendimentoFrequente
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AtendimentoFrequenteInfo selectByPrimaryKey(
                int empresaId,
                int atendimentoFrequenteId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_ATENDIMENTO_FREQUENTE_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, atendimentoFrequenteId);
      AtendimentoFrequenteInfo[] result = (AtendimentoFrequenteInfo[])select(statement);
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
   * Retorna um AtendimentoFrequenteInfo[] contendo a lista de AtendimentoFrequente
   * indicados(as) pelos parâmetros de pesquisa.
   * @param empresaId Empresa ID.
   * @param nome Nome.
   * @param departamentoId Departamento ID.
   * @param assuntoId Assunto ID.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um AtendimentoFrequenteInfo[] contendo a lista de AtendimentoFrequente
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AtendimentoFrequenteInfo[] selectByFilter(
                int empresaId,
                String nome,
                int departamentoId,
                int assuntoId,
                Paginate paginate) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_NOME.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) AND " +
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "(" + FIELD_DEPARTAMENTO_ID.getFieldName(getTableName()) + " = ?) OR (" + departamentoId + "= 0) AND " +
                                                  "(" + FIELD_ASSUNTO_ID.getFieldName(getTableName()) + " = ?) OR (" + assuntoId + " = 0)",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setInt(1, empresaId);
      statement.setString(2, nome + "%");
      statement.setInt(3, departamentoId);
      statement.setInt(4, assuntoId);
      // nosso resultado
      AtendimentoFrequenteInfo[] result = (AtendimentoFrequenteInfo[])select(statement);
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
   * Atualiza o(a) AtendimentoFrequente identificado(a) por 'atendimentofrequenteInfo'.
   * @param atendimentofrequenteInfo AtendimentoFrequenteInfo contendo as informações do(a) AtendimentoFrequente que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(AtendimentoFrequenteInfo atendimentofrequenteInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(atendimentofrequenteInfo);
      // atualiza o registro
      super.update(atendimentofrequenteInfo);
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
   * Valida o(a) AtendimentoFrequente identificado(a) por 'atendimentofrequenteInfo'.
   * @param atendimentofrequenteInfo AtendimentoFrequenteInfo contendo as informações do(a) AtendimentoFrequente que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(AtendimentoFrequenteInfo atendimentofrequenteInfo) throws Exception {
  }

}
