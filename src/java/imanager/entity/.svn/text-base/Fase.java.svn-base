     
package imanager.entity;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;

/**
 * Representa a entidade Fase no banco de dados da aplicação.
 */
public class Fase extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.Fase";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("fase", "Fase", "Mantém o cadastro de Fase de Oportunidade.", HELP, "entity/fase.jsp", "CRM", "Auxiliares", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("faseCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/fasecadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_FASE_ID = new EntityField("in_fase_id", "Fase ID", "", "faseId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Nome.");
  static public final EntityField FIELD_DESCRICAO = new EntityField("va_descricao", "Descrição", "Informe a Descrição.", "descricao", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  static public final EntityField FIELD_DIAS_ACOMPANHAMENTO = new EntityField("sm_dias_acompanhamento", "Acomp. (Dias)", "Informe a quantidade de Dias para Acompanhamento.", "diasAcompanhamento", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_PERCENTUAL_SUCESSO = new EntityField("sm_percentual_sucesso", "Sucesso (%)", "Informe o Percentual de Sucesso.", "percentualSucesso", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);

  /**
   * Construtor padrão.
   */
  public Fase() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_fase");
    // nossos campos  
    fieldList().add(FIELD_FASE_ID);  
    fieldList().add(FIELD_NOME);  
    fieldList().add(FIELD_DESCRICAO);  
    fieldList().add(FIELD_DIAS_ACOMPANHAMENTO);  
    fieldList().add(FIELD_PERCENTUAL_SUCESSO);  
  }

  /**
   * Exclui o(a) Fase informado(a) por 'faseInfo'.
   * @param faseInfo FaseInfo referente a(o) Fase
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(FaseInfo faseInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(faseInfo);
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
   * Insere o(a) Fase identificado(a) por 'faseInfo'.
   * @param faseInfo FaseInfo contendo as informações do(a) Fase que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(FaseInfo faseInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(faseInfo);
      // valor da seqüência
      faseInfo.setFaseId(getNextSequence(FIELD_FASE_ID));
      // insere o registro
      super.insert(faseInfo);
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
   * Retorna um FaseInfo referente a(o) Fase
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param faseId Fase ID.
   * @return Retorna um FaseInfo referente a(o) Fase
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public FaseInfo selectByPrimaryKey(
                int faseId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_FASE_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, faseId);
      FaseInfo[] result = (FaseInfo[])select(statement);
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
   * Retorna um FaseInfo[] contendo a lista de Fase
   * indicados(as) pelos parâmetros de pesquisa.
   * @param nome Nome.
   * @param paginate Informações de paginação dos resultados ou null.
   * @return Retorna um FaseInfo[] contendo a lista de Fase
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public FaseInfo[] selectByFilter(
                String   nome,
                Paginate paginate
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_PERCENTUAL_SUCESSO.getFieldName(getTableName()), FIELD_NOME.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?)",
                                                  new String[0],
                                                  paginate
                                                );
      statement.setString(1, nome + "%");
      // nosso resultado
      FaseInfo[] result = (FaseInfo[])select(statement);
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
   * Atualiza o(a) Fase identificado(a) por 'faseInfo'.
   * @param faseInfo FaseInfo contendo as informações do(a) Fase que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(FaseInfo faseInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(faseInfo);
      // atualiza o registro
      super.update(faseInfo);
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
   * Valida o(a) Fase identificado(a) por 'faseInfo'.
   * @param faseInfo FaseInfo contendo as informações do(a) Fase que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(FaseInfo faseInfo) throws Exception {
  }

}
