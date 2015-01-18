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

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;

/**
 * Representa a entidade CampanhaAutomatizada no banco de dados da aplicação.
 */
public class CampanhaAutomatizada extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.CampanhaAutomatizada";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("campanhaautomatizada", "CampanhaAutomatizada", "Mantém o cadastro de CampanhaAutomatizada.", HELP, "entity/campanhaautomatizada.jsp", "imanager", "", Action.CATEGORY_ENTITY, false, false);
  static public final Action ACTION_CADASTRO = new Action("campanhaautomatizadaCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/campanhaautomatizadacadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_CAMPANHA_ID = new EntityField("in_campanha_id", "Campanha ID", "Informe o(a) Campanha ID.", "campanhaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_CAMPANHA_AUTOMATIZADA_ID = new EntityField("in_campanha_automatizada_id", "ID", "Informe o(a) Campanha Automatizada ID.", "campanhaAutomatizadaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_ASSUNTO = new EntityField("va_assunto", "Assunto", "Informe o(a) Assunto.", "assunto", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true, "", "value != ''", "Obrigatório informar o Assunto");
  static public final EntityField FIELD_MENSAGEM = new EntityField("va_mensagem", "Mensagem", "Informe o(a) Mensagem.", "mensagem", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true, "", "value != ''", "Obrigatório informar a Mensagem");
  static public final EntityField FIELD_DIAS_ENVIO = new EntityField("sm_dias_envio", "Dias Envio", "Informe o(a) Dias Envio.", "diasEnvio", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigatório informar os Dias de Envio");
  static public final EntityField FIELD_NOME_REMETENTE = new EntityField("va_nome_remetente", "Nome Remetente", "Informe o Nome do Remetente.", "nomeRemetente", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Nome do Remetente");
  static public final EntityField FIELD_EMAIL_REMETENTE = new EntityField("va_email_remetente", "Email Remetente", "Informe o Email do Remetente.", "emailRemetente", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Email do Remetente");
  // nossos parâmetros de usuário
  public final Param USER_PARAM_CAMPANHA_ID = new Param("userParamCampanhaId", "Campanha ID", "Informe o(a) Campanha ID.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER, "", "value != ''", "Obrigatório informar  Campanha ID.");
  public final Param USER_PARAM_CAMPANHA_AUTOMATIZADA_ID = new Param("userParamCampanhaAutomatizadaId", "Campanha Automatizada ID", "Informe o(a) Campanha Automatizada ID.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER, "", "value != ''", "Obrigatório informar  Campanha Automatizada ID.");

  /**
   * Construtor padrão.
   */
  public CampanhaAutomatizada() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_campanha_automatizada");
    // nossos campos  
    fieldList().add(FIELD_CAMPANHA_ID);  
    fieldList().add(FIELD_CAMPANHA_AUTOMATIZADA_ID);  
    fieldList().add(FIELD_ASSUNTO);  
    fieldList().add(FIELD_MENSAGEM);  
    fieldList().add(FIELD_DIAS_ENVIO);  
    fieldList().add(FIELD_NOME_REMETENTE);  
    fieldList().add(FIELD_EMAIL_REMETENTE);    
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_CAMPANHA_ID);
    userParamList().add(USER_PARAM_CAMPANHA_AUTOMATIZADA_ID);
  }

  /**
   * Exclui o(a) CampanhaAutomatizada informado(a) por 'campanhaautomatizadaInfo'.
   * @param campanhaautomatizadaInfo CampanhaAutomatizadaInfo referente a(o) CampanhaAutomatizada
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(CampanhaAutomatizadaInfo campanhaautomatizadaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(campanhaautomatizadaInfo);
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
   * Insere o(a) CampanhaAutomatizada identificado(a) por 'campanhaautomatizadaInfo'.
   * @param campanhaautomatizadaInfo CampanhaAutomatizadaInfo contendo as informações do(a) CampanhaAutomatizada que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(CampanhaAutomatizadaInfo campanhaautomatizadaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(campanhaautomatizadaInfo);
      // sequencia
      campanhaautomatizadaInfo.setCampanhaAutomatizadaId(getNextSequence(FIELD_CAMPANHA_AUTOMATIZADA_ID, FIELD_CAMPANHA_ID.getFieldName() + "=" + campanhaautomatizadaInfo.getCampanhaId()));
      // insere o registro
      super.insert(campanhaautomatizadaInfo);
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
   * Retorna um CampanhaAutomatizadaInfo referente a(o) CampanhaAutomatizada
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param campanhaId Campanha ID.
   * @param campanhaAutomatizadaId Campanha Automatizada ID.
   * @return Retorna um CampanhaAutomatizadaInfo referente a(o) CampanhaAutomatizada
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public CampanhaAutomatizadaInfo selectByPrimaryKey(
                int campanhaId,
                int campanhaAutomatizadaId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_CAMPANHA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_CAMPANHA_AUTOMATIZADA_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, campanhaId);
      statement.setInt(2, campanhaAutomatizadaId);
      CampanhaAutomatizadaInfo[] result = (CampanhaAutomatizadaInfo[])select(statement);
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
   * Retorna um CampanhaAutomatizadaInfo[] contendo a lista de CampanhaAutomatizada
   * indicados(as) pelos parâmetros de pesquisa.
   * @param campanhaId Campanha ID.
   * @param campanhaAutomatizadaId Campanha Automatizada ID.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um CampanhaAutomatizadaInfo[] contendo a lista de CampanhaAutomatizada
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public CampanhaAutomatizadaInfo[] selectByFilter(
                int campanhaId,
                int campanhaAutomatizadaId,
                Paginate paginate) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_CAMPANHA_ID.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "((" + FIELD_CAMPANHA_ID.getFieldName(getTableName()) + " = ?) OR (" + campanhaId + " = 0)) AND " +
                                                  "((" + FIELD_CAMPANHA_AUTOMATIZADA_ID.getFieldName(getTableName()) + " = ?) OR (" + campanhaAutomatizadaId + " = 0))",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setInt(1, campanhaId);
      statement.setInt(2, campanhaAutomatizadaId);
      // nosso resultado
      CampanhaAutomatizadaInfo[] result = (CampanhaAutomatizadaInfo[])select(statement);
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
   * Atualiza o(a) CampanhaAutomatizada identificado(a) por 'campanhaautomatizadaInfo'.
   * @param campanhaautomatizadaInfo CampanhaAutomatizadaInfo contendo as informações do(a) CampanhaAutomatizada que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(CampanhaAutomatizadaInfo campanhaautomatizadaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(campanhaautomatizadaInfo);
      // atualiza o registro
      super.update(campanhaautomatizadaInfo);
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
   * Valida o(a) CampanhaAutomatizada identificado(a) por 'campanhaautomatizadaInfo'.
   * @param campanhaautomatizadaInfo CampanhaAutomatizadaInfo contendo as informações do(a) CampanhaAutomatizada que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(CampanhaAutomatizadaInfo campanhaautomatizadaInfo) throws Exception {
  }

}
