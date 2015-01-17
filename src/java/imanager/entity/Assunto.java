     
package imanager.entity;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;

/**
 * Representa a entidade Assunto no banco de dados da aplicação.
 */
public class Assunto extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.Assunto";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("assunto", "Assunto", "Mantém o cadastro de Assunto de Atendimento.", HELP, "entity/assunto.jsp", "CRM", "Auxiliares", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("assuntoCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/assuntocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_INSERT_CHILD = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir filho", "Insere um novo registro, filho do registro selecionado."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_ASSUNTO_ID = new EntityField("in_assunto_id", "Assunto ID", "", "assuntoId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Nome.");
  // nossos parâmetros de usuário
  public final Param USER_PARAM_ASSUNTO_PAI_ID = new Param("userParamAssuntoPaiID", "Assunto Pai ID", "", "", 4, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_ASSUNTO_PAI    = new Param("userParamAssuntoPai", "Assunto Pai", "", "", 0, Param.ALIGN_RIGHT, Param.FORMAT_NONE);

  /**
   * Delimitador de nível entre CentroCustos.
   */
  static public final String LEVEL_DELIMITER = "/";

  // variável de controle de alteração
  private boolean isUpdating = false;

  /**
   * Construtor padrão.
   */
  public Assunto() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_assunto");
    // nossos campos  
    fieldList().add(FIELD_ASSUNTO_ID);  
    fieldList().add(FIELD_NOME);  
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_ASSUNTO_PAI_ID);
    userParamList().add(USER_PARAM_ASSUNTO_PAI);
  }

  /**
   * Exclui o(a) Assunto informado(a) por 'assuntoInfo'.
   * @param assuntoInfo AssuntoInfo referente a(o) Assunto
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(AssuntoInfo assuntoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // pega a lista de centros filhos
      AssuntoInfo[] assuntoInfoChildren = selectByFilter(assuntoInfo.getNome() + LEVEL_DELIMITER, null);
      // exclui os filhos
      for (int i=0; i<assuntoInfoChildren.length; i++) {
        delete(assuntoInfoChildren[i]);
      } // for
      // exclui o registro
      super.delete(assuntoInfo);
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
   * Insere o(a) Assunto identificado(a) por 'assuntoInfo'.
   * @param assuntoInfo AssuntoInfo contendo as informações do(a) Assunto que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(AssuntoInfo assuntoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(assuntoInfo);
      // valor da seqüência
      assuntoInfo.setAssuntoId(getNextSequence(FIELD_ASSUNTO_ID));
      // insere o registro
      super.insert(assuntoInfo);
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
   * Retorna um AssuntoInfo referente a(o) Assunto
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param assuntoId Assunto ID.
   * @return Retorna um AssuntoInfo referente a(o) Assunto
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AssuntoInfo selectByPrimaryKey(
                int assuntoId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_ASSUNTO_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, assuntoId);
      AssuntoInfo[] result = (AssuntoInfo[])select(statement);
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
   * Retorna um AssuntoInfo[] contendo a lista de Assunto
   * indicados(as) pelos parâmetros de pesquisa.
   * @param nome Nome.
   * @param paginate Informações de paginação dos resultados ou null.
   * @return Retorna um AssuntoInfo[] contendo a lista de Assunto
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AssuntoInfo[] selectByFilter(
                String nome,
                Paginate paginate
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      /*
        A função 'replace' é utilizada pois o caractere ' ' (espaço) sempre
        precede outros caracteres e pode gerar quebra da hierarquia no seguinte
        exemplo:
        - Pai
        - Pai com espaço
        - Pai/Filho
       */
      // prepara a consulta
      String[] orderFieldNames = {"replace(" + FIELD_NOME.getFieldName(getTableName()) + ", ' ', '_')"};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?)",
                                                  new String[0],
                                                  paginate
                                                );
      statement.setString(1, nome + "%");
      // nosso resultado
      AssuntoInfo[] result = (AssuntoInfo[])select(statement);
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
   * Atualiza o(a) Assunto identificado(a) por 'assuntoInfo'.
   * @param assuntoInfo AssuntoInfo contendo as informações do(a) Assunto que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(AssuntoInfo assuntoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(assuntoInfo);
      // pega o registro atual, antes da modificação
      AssuntoInfo oldAssuntoInfo = selectByPrimaryKey(assuntoInfo.getAssuntoId());
      // mudou o nome?
      boolean nomeChanged = assuntoInfo.getNome().equals(oldAssuntoInfo.getNome());
      // se mudou o titulo...atualiza os filhos
      if (!nomeChanged && !isUpdating) {
        // inicia alteração
        isUpdating = true;
        // pega a lista de assuntos filhos
        AssuntoInfo[] assuntoInfoChildren = selectByFilter(oldAssuntoInfo.getNome() + LEVEL_DELIMITER, null);
        // altera o titulo do pai nos filhos
        for (int i=0; i<assuntoInfoChildren.length; i++) {
          AssuntoInfo assuntoInfoChild = assuntoInfoChildren[i];
          assuntoInfoChild.setNome(assuntoInfo.getNome() + assuntoInfoChild.getNome().substring(oldAssuntoInfo.getNome().length()));
          update(assuntoInfoChild);
        } // for
        // finaliza alteração
        isUpdating = false;
      } // if
      // atualiza o registro
      super.update(assuntoInfo);
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
   * Valida o(a) Assunto identificado(a) por 'assuntoInfo'.
   * @param assuntoInfo AssuntoInfo contendo as informações do(a) Assunto que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(AssuntoInfo assuntoInfo) throws Exception {
  }

}
