     
package imanager.entity;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;

/**
 * Representa a entidade Departamento no banco de dados da aplicação.
 */
public class Departamento extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.Departamento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("departamento", "Departamento", "Mantém o cadastro de Departamento.", HELP, "entity/departamento.jsp", "Global", "", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("departamentoCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/departamentocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa ID", "", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_DEPARTAMENTO_ID = new EntityField("in_departamento_id", "Departamento ID", "", "departamentoId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Nome.");

  /**
   * Construtor padrão.
   */
  public Departamento() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("global_departamento");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_DEPARTAMENTO_ID);  
    fieldList().add(FIELD_NOME);  
  }

  /**
   * Exclui o(a) Departamento informado(a) por 'departamentoInfo'.
   * @param departamentoInfo DepartamentoInfo referente a(o) Departamento
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(DepartamentoInfo departamentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(departamentoInfo);
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
   * Insere o(a) Departamento identificado(a) por 'departamentoInfo'.
   * @param departamentoInfo DepartamentoInfo contendo as informações do(a) Departamento que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(DepartamentoInfo departamentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(departamentoInfo);
      // valor da seqüência
      departamentoInfo.setDepartamentoId(getNextSequence(FIELD_DEPARTAMENTO_ID, FIELD_EMPRESA_ID.getFieldName() + "=" + departamentoInfo.getEmpresaId()));
      // insere o registro
      super.insert(departamentoInfo);
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
   * Retorna um DepartamentoInfo referente a(o) Departamento
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param empresaId Empresa ID.
   * @param departamentoId Departamento ID.
   * @return Retorna um DepartamentoInfo referente a(o) Departamento
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public DepartamentoInfo selectByPrimaryKey(
                int empresaId,
                int departamentoId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_DEPARTAMENTO_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, departamentoId);
      DepartamentoInfo[] result = (DepartamentoInfo[])select(statement);
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
   * Retorna um DepartamentoInfo[] contendo a lista de Departamento
   * indicados(as) pelos parâmetros de pesquisa.
   * @param empresaId Empresa ID.
   * @return Retorna um DepartamentoInfo[] contendo a lista de Departamento
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public DepartamentoInfo[] selectByFilter(
                int empresaId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_NOME.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?)"
                                                );
      statement.setInt(1, empresaId);
      // nosso resultado
      DepartamentoInfo[] result = (DepartamentoInfo[])select(statement);
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
   * Atualiza o(a) Departamento identificado(a) por 'departamentoInfo'.
   * @param departamentoInfo DepartamentoInfo contendo as informações do(a) Departamento que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(DepartamentoInfo departamentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(departamentoInfo);
      // atualiza o registro
      super.update(departamentoInfo);
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
   * Valida o(a) Departamento identificado(a) por 'departamentoInfo'.
   * @param departamentoInfo DepartamentoInfo contendo as informações do(a) Departamento que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(DepartamentoInfo departamentoInfo) throws Exception {
  }

}
