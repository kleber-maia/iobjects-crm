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

import imanager.misc.StatusOportunidade;
import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;
import securityservice.entity.*;

/**
 * Representa a entidade OportunidadeHistorico no banco de dados da aplica��o.
 */
public class OportunidadeHistorico extends Entity {

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.entity.OportunidadeHistorico";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION          = new Action("oportunidadehistorico", "Oportunidade Hist�rico", "Mant�m o cadastro de Oportunidade Hist�rico.", HELP, "entity/oportunidadehistorico.jsp", "CRM", "", Action.CATEGORY_ENTITY, false, false);
  static public final Action ACTION_CADASTRO = new Action("oportunidadehistoricoCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/oportunidadehistoricocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que est� sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os par�metros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa", "Informa a Empresa.", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_OPORTUNIDADE_ID = new EntityField("in_oportunidade_id", "Oportunidade ", "Informa a Oportunidade.", "oportunidadeId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_OPORTUNIDADE_HISTORICO_ID = new EntityField("in_oportunidade_historico_id", "Oportunidade Hist�rico", "Informa a Oportunidade Hist�rico.", "oportunidadeHistoricoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_STATUS_ANTERIOR = new EntityField("sm_status_anterior", "Status Anterior", "Informa o Status Anterior.", "statusAnterior", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_STATUS = new EntityField("sm_status", "Status", "Informa o Status.", "status", Types.INTEGER, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_USUARIO_INCLUSAO_ID = new EntityField("in_usuario_inclusao_id", "Usu�rio Inclus�o", "Informa o Usu�rio de Inclus�o.", "usuarioInclusaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_DATA_HORA_INCLUSAO = new EntityField("dt_inclusao", "Data Hora Inclus�o", "Informa a Data e Hora da Inclus�o.", "dataHoraInclusao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE_TIME, "", true);
  static public final EntityField FIELD_INFORMACAO = new EntityField("va_informacao", "Informa��o", "Informe a Informa��o.", "informacao", Types.VARCHAR, 500, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  // nossos lookup
  static public final EntityLookup LOOKUP_USUARIO = new EntityLookup("lookupUsuario", "Usu�rio", ".", Usuario.CLASS_NAME, FIELD_USUARIO_INCLUSAO_ID, Usuario.FIELD_NOME, Usuario.FIELD_NOME);
  // nossos par�metros de usu�rio
  public final Param USER_PARAM_EMPRESA_ID = new Param("userParamEmpresaId", "Empresa", "", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_OPORTUNIDADE_ID = new Param("userParamOportunidadeId", "Oportunidade ", "", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);

  static {
    OportunidadeHistorico.FIELD_STATUS.setLookupList(StatusOportunidade.LOOKUP_LIST_FOR_FIELD);
  }
  /**
   * Construtor padr�o.
   */
  public OportunidadeHistorico() {
    // nossas a��es
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_oportunidade_historico");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);
    fieldList().add(FIELD_OPORTUNIDADE_ID);
    fieldList().add(FIELD_OPORTUNIDADE_HISTORICO_ID);
    fieldList().add(FIELD_STATUS_ANTERIOR);  
    fieldList().add(FIELD_STATUS);
    fieldList().add(FIELD_USUARIO_INCLUSAO_ID);
    fieldList().add(FIELD_DATA_HORA_INCLUSAO);
    fieldList().add(FIELD_INFORMACAO);
    // nossos lookups
    lookupList().add(LOOKUP_USUARIO);
    // nossos par�metros de usu�rio
    userParamList().add(USER_PARAM_EMPRESA_ID);
    userParamList().add(USER_PARAM_OPORTUNIDADE_ID);
  }
  /**
   * Verifica se � o �ltimo registro da Oportunidade
   * @param oportunidadeHistoricoInfo OportunidadeHistoricoInfo referente a OportunidadeHistorico
   * que se deseja verificar se � o �ltimo registro.
   * @return true se for o �ltimo.
   */
  public OportunidadeHistoricoInfo selectLast(
                int empresaId,
                int oportunidadeId
           ) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                    "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                    "(" + FIELD_OPORTUNIDADE_ID.getFieldName(getTableName()) + "=?) AND " +
                                                    "(" + FIELD_OPORTUNIDADE_HISTORICO_ID.getFieldName(getTableName()) + " = (SELECT max(in_oportunidade_historico_id) " +
                                                                                                                              "FROM crm_oportunidade_historico " +
                                                                                                                              " WHERE in_empresa_id = ?" +
                                                                                                                              "  AND  in_oportunidade_id = ?))"
                                                  );
      statement.setInt(1, empresaId);
      statement.setInt(2, oportunidadeId);
      statement.setInt(3, empresaId);
      statement.setInt(4, oportunidadeId);
      OportunidadeHistoricoInfo[] result = (OportunidadeHistoricoInfo[])select(statement);
      // salva tudo
      getFacade().commitTransaction();
      // retorna
      return result[0];
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Exclui a OportunidadeHistorico informado por 'oportunidadeHistoricoInfo'.
   * @param oportunidadeHistoricoInfo OportunidadeHistoricoInfo referente a OportunidadeHistorico
   *        que se deseja excluir.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(OportunidadeHistoricoInfo oportunidadeHistoricoInfo) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(oportunidadeHistoricoInfo);
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
   * Insere o(a) OportunidadeHistorico identificado(a) por 'oportunidadeHistoricoInfo'.
   * @param oportunidadeHistoricoInfo OportunidadeHistoricoInfo contendo as informa��es do(a) OportunidadeHistorico que se
   *                    deseja incluir.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(OportunidadeHistoricoInfo oportunidadeHistoricoInfo) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(oportunidadeHistoricoInfo);
      // valor da sequ�ncia
      oportunidadeHistoricoInfo.setOportunidadeHistoricoId(getNextSequence(FIELD_OPORTUNIDADE_HISTORICO_ID, OportunidadeHistorico.FIELD_EMPRESA_ID.getFieldName() + "=" + oportunidadeHistoricoInfo.getEmpresaId() + " AND " + OportunidadeHistorico.FIELD_OPORTUNIDADE_ID.getFieldName() + "=" + oportunidadeHistoricoInfo.getOportunidadeId()));
      // usu�rios
      oportunidadeHistoricoInfo.setDataHoraInclusao(new Timestamp(System.currentTimeMillis()));
      // insere o registro
      super.insert(oportunidadeHistoricoInfo);
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
   * Retorna um OportunidadeHistoricoInfo referente a(o) OportunidadeHistorico
   * indicado(a) pelos par�metros que representam sua chave prim�ria.
   * @param empresaId EmpresaId.
   * @param oportunidade Oportunidade .
   * @param oportunidadeHistorico Oportunidade Hist�rico.
   * @return Retorna um OportunidadeHistoricoInfo referente a(o) OportunidadeHistorico
   * indicado pelos par�metros que representam sua chave prim�ria.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public OportunidadeHistoricoInfo selectByPrimaryKey(
                int empresaId,
                int oportunidadeId,
                int oportunidadeHistoricoId
           ) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_OPORTUNIDADE_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_OPORTUNIDADE_HISTORICO_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, oportunidadeId);
      statement.setInt(3, oportunidadeHistoricoId);
      OportunidadeHistoricoInfo[] result = (OportunidadeHistoricoInfo[])select(statement);
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
   * Retorna um OportunidadeHistoricoInfo[] contendo a lista de OportunidadeHistorico
   * indicados(as) pelos par�metros de pesquisa.
   * @param empresaId EmpresaId.
   * @param oportunidade Oportunidade .
   * @param paginate Informa��o de pagina��o dos resultados ou null.
   * @return Retorna um OportunidadeHistoricoInfo[] contendo a lista de OportunidadeHistorico
   * indicados(as) pelos par�metros de pesquisa.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public OportunidadeHistoricoInfo[] selectByFilter(
                int empresaId,
                int oportunidadeId,
                Paginate paginate) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_OPORTUNIDADE_HISTORICO_ID.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) AND " +
                                                  "(" + FIELD_OPORTUNIDADE_ID.getFieldName(getTableName()) + " = ?)",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setInt(1, empresaId);
      statement.setInt(2, oportunidadeId);
      // nosso resultado
      OportunidadeHistoricoInfo[] result = (OportunidadeHistoricoInfo[])select(statement);
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
   * Atualiza o(a) OportunidadeHistorico identificado(a) por 'oportunidadeHistoricoInfo'.
   * @param oportunidadeHistoricoInfo OportunidadeHistoricoInfo contendo as informa��es do(a) OportunidadeHistorico que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(OportunidadeHistoricoInfo oportunidadeHistoricoInfo) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(oportunidadeHistoricoInfo);
      // usu�rios
      oportunidadeHistoricoInfo.setUsuarioInclusaoId(getFacade().getLoggedUser().getId());
      oportunidadeHistoricoInfo.setDataHoraInclusao(new Timestamp(System.currentTimeMillis()));
      // atualiza o registro
      super.update(oportunidadeHistoricoInfo);
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
   * Valida o(a) OportunidadeHistorico identificado(a) por 'oportunidadeHistoricoInfo'.
   * @param oportunidadeHistoricoInfo OportunidadeHistoricoInfo contendo as informa��es do(a) OportunidadeHistorico que se
   *                    deseja validar.
   * @throws Exception Em caso de exce��o no preenchimento dos dados informados.
   */
  public void validate(OportunidadeHistoricoInfo oportunidadeHistoricoInfo) throws Exception {
  }

}
