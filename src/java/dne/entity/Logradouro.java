     
package dne.entity;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;

/**
 * Representa a entidade Logradouro no banco de dados da aplicação.
 */
public class Logradouro extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "dne.entity.Logradouro";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("logradouro", "Logradouro", "Mantém o cadastro de Logradouro.", HELP, "entity/logradouro.jsp", "DNE", "", Action.CATEGORY_ENTITY, false, false);
  static public final Action ACTION_CADASTRO = new Action("logradouroCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/logradourocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_CEP = new EntityField("ch_cep", "CEP", "Informe o(a) CEP.", "cep", Types.CHAR, 8, 0, true, EntityField.ALIGN_LEFT, false, true, EntityField.FORMAT_NONE, "", true);
  static public final EntityField FIELD_UF = new EntityField("ch_uf", "UF", "Informe o(a) UF.", "uf", Types.CHAR, 2, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);
  static public final EntityField FIELD_MUNICIPIO = new EntityField("va_municipio", "Município", "Informe o(a) Município.", "municipio", Types.VARCHAR, 72, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);
  static public final EntityField FIELD_BAIRRO = new EntityField("va_bairro", "Bairro", "Informe o(a) Bairro.", "bairro", Types.VARCHAR, 72, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);
  static public final EntityField FIELD_ENDERECO = new EntityField("va_endereco", "Endereço", "Informe o(a) Endereço.", "endereco", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);

  /**
   * Construtor padrão.
   */
  public Logradouro() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("dne_logradouro");
    // nossos campos  
    fieldList().add(FIELD_CEP);  
    fieldList().add(FIELD_UF);  
    fieldList().add(FIELD_MUNICIPIO);  
    fieldList().add(FIELD_BAIRRO);  
    fieldList().add(FIELD_ENDERECO);  
  }

  /**
   * Exclui o(a) Logradouro informado(a) por 'logradouroInfo'.
   * @param logradouroInfo LogradouroInfo referente a(o) Logradouro
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(LogradouroInfo logradouroInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(logradouroInfo);
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
   * Insere o(a) Logradouro identificado(a) por 'logradouroInfo'.
   * @param logradouroInfo LogradouroInfo contendo as informações do(a) Logradouro que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(LogradouroInfo logradouroInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(logradouroInfo);
      // insere o registro
      super.insert(logradouroInfo);
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
   * Retorna um LogradouroInfo referente a(o) Logradouro
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param cep CEP.
   * @return Retorna um LogradouroInfo referente a(o) Logradouro
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public LogradouroInfo selectByPrimaryKey(
                String cep
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_CEP.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setString(1, cep);
      LogradouroInfo[] result = (LogradouroInfo[])select(statement);
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
   * Retorna um LogradouroInfo[] contendo a lista de Logradouro
   * indicados(as) pelos parâmetros de pesquisa.
   * @param endereco Endereço.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um LogradouroInfo[] contendo a lista de Logradouro
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public LogradouroInfo[] selectByFilter(
                String endereco,
                Paginate paginate) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_ENDERECO.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_ENDERECO.getFieldName(getTableName()) + " LIKE ?)",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setString(1, endereco + "%");
      // nosso resultado
      LogradouroInfo[] result = (LogradouroInfo[])select(statement);
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
   * Atualiza o(a) Logradouro identificado(a) por 'logradouroInfo'.
   * @param logradouroInfo LogradouroInfo contendo as informações do(a) Logradouro que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(LogradouroInfo logradouroInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(logradouroInfo);
      // atualiza o registro
      super.update(logradouroInfo);
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
   * Valida o(a) Logradouro identificado(a) por 'logradouroInfo'.
   * @param logradouroInfo LogradouroInfo contendo as informações do(a) Logradouro que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(LogradouroInfo logradouroInfo) throws Exception {
  }

}
