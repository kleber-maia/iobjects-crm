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
import iobjects.util.*;
import securityservice.entity.Usuario;

/**
 * Representa a entidade Oportunidade no banco de dados da aplicação.
 */
public class Oportunidade extends Entity {

  // nossos faqs
  FAQ FAQ_TUTORIAL_OPORTUNIDADE = new FAQ("tutorialOportunidade", " Passo a Passo ", "Oportunidade: cadastro de uma nova Oportunidade.", "", "tutorial_oportunidade.html", "tutorial_oportunidade.zip");

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.Oportunidade";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("oportunidade", "Oportunidade", "Mantém o cadastro de Oportunidades de negócios.", HELP, "entity/oportunidade.jsp", "CRM", "", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("oportunidadeCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/oportunidadecadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT                       = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT                     = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE                     = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE                       = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH                     = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa", "", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_OPORTUNIDADE_ID = new EntityField("in_oportunidade_id", "ID", "Informa o ID da Oportunidade.", "oportunidadeId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DEPARTAMENTO_ID = new EntityField("in_departamento_id", "Departamento", "", "departamentoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != ''", "Obrigatório informar o Departamento.");
  static public final EntityField FIELD_USUARIO_ID = new EntityField("in_usuario_id", "Usuário", "", "usuarioId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != ''", "Obrigatório informar o Usuário.");
  static public final EntityField FIELD_CAMPANHA_ID = new EntityField("in_campanha_id", "Campanha", "", "campanhaId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != ''", "Obrigatório informar a Campanha.");
  static public final EntityField FIELD_CLIENTE_ID = new EntityField("in_cliente_id", "Cliente", "", "clienteId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != ''", "Obrigatório informar o Cliente.");
  static public final EntityField FIELD_FASE_ID = new EntityField("in_fase_id", "Fase", "Informe a Fase.", "faseId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != ''", "Obrigatório informar a Fase.");
  static public final EntityField FIELD_DATA_ACOMPANHAMENTO = new EntityField("da_acompanhamento", "Acompanhamento", "Informe a Data de Acompanhamento.", "dataAcompanhamento", Types.TIMESTAMP, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_PERCENTUAL_SUCESSO = new EntityField("sm_percentual_sucesso", "% Sucesso", "Informe o Percentual de Sucesso.", "percentualSucesso", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_STATUS = new EntityField("sm_status", "Status", "Informe o Status.", "status", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_VALOR = new EntityField("do_valor", "Valor", "Informe o Valor.", "valor", Types.DOUBLE, 34, 2, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_DOUBLE, "", false);
  static public final EntityField FIELD_DESCRICAO = new EntityField("va_descricao", "Descrição", "Informe a Descrição.", "descricao", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  static public final EntityField FIELD_USUARIO_INCLUSAO_ID = new EntityField("in_usuario_inclusao_id", "Usuário Inclusão", "Informa o Usuário de Inclusão.", "usuarioInclusaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_INCLUSAO = new EntityField("da_inclusao", "Data Inclusão", "Informa a Data de Inclusão.", "dataInclusao", Types.TIMESTAMP, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_USUARIO_ALTERACAO_ID = new EntityField("in_usuario_alteracao_id", "Usuário Alteração", "Informa o Usuário de Alteração.", "usuarioAlteracaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_HORA_ALTERACAO = new EntityField("dt_alteracao", "Data Hora Alteração", "Informa a Data Hora de Alteração.", "dataHoraAlteracao", Types.TIMESTAMP, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_LINK_EXTERNO = new EntityField("va_link_externo", "Endereço", "Informe o Endereço.", "linkExterno", Types.VARCHAR, 2000, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  // nossos lookups
  static public final EntityLookup LOOKUP_DEPARTAMENTO = new EntityLookup("lookupDepartamento", "Departamento", "Selecione o Departamento.", Departamento.CLASS_NAME, new EntityField[]{FIELD_EMPRESA_ID, FIELD_DEPARTAMENTO_ID}, new EntityField[]{Departamento.FIELD_NOME}, new EntityField[]{Departamento.FIELD_NOME});
  static public final EntityLookup LOOKUP_CLIENTE = new EntityLookup("lookupCliente", "Cliente", "Selecione o Cliente.", Contato.CLASS_NAME, FIELD_CLIENTE_ID, Contato.FIELD_NOME, Contato.FIELD_NOME);
  static public final EntityLookup LOOKUP_CAMPANHA = new EntityLookup("lookupCampanha", "Campanha", "Selecione a Campanha.", Campanha.CLASS_NAME, FIELD_CAMPANHA_ID, Campanha.FIELD_NOME, Campanha.FIELD_NOME);
  static public final EntityLookup LOOKUP_USUARIO = new EntityLookup("lookupUsuario", "Usuário Responsável", "Selecione o Usuário Responsável.", Usuario.CLASS_NAME, FIELD_USUARIO_ID, Usuario.FIELD_NOME, Usuario.FIELD_NOME);
  static public final EntityLookup LOOKUP_FASE = new EntityLookup("lookupFase", "Fase", "Selecione a Fase.", Fase.CLASS_NAME, new EntityField[]{FIELD_FASE_ID}, new EntityField[]{Fase.FIELD_NOME}, new EntityField[]{Fase.FIELD_PERCENTUAL_SUCESSO, Fase.FIELD_NOME});
  // nossos parâmetros de usuário
  public final Param USER_PARAM_EMPRESA_ID = new Param("userParamEmpresaId", "Empresa", "Informa a Empresa.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_OPORTUNIDADE_ID = new Param("userParamOportunidadeId", "ID", "Informe a Oportunidade.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_DEPARTAMENTO_ID = new Param("userParamDepartamentoId", "Departamento", "Informe o Departamento.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_USUARIO_ID = new Param("userParamUsuarioId", "Usuário", "Informe o Usuário.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_CAMPANHA_ID = new Param("userParamCampanhaId", "Campanha", "Informe a Campanha.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_CLIENTE_ID = new Param("userParamClienteId", "Cliente", "Informe o Cliente.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_FASE_ID = new Param("userParamFaseId", "Fase", "Informe a Fase.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_STATUS = new Param("userParamStatus", "Status", "Informe o Status.", StatusOportunidade.EM_ANDAMENTO + "", 5, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  // nosso parâmetro de controle
  public final Param CONTROL_PARAM_CENTRAL_ATENDIMENTO = new Param("controlParamCentraLAtendimento", "false");

  static public final String LEVEL_DELIMITER = "/";

  static {
    Oportunidade.FIELD_STATUS.setLookupList(StatusOportunidade.LOOKUP_LIST_FOR_FIELD);
  }

  {
    USER_PARAM_STATUS.setLookupList(StatusOportunidade.LOOKUP_LIST_FOR_PARAM);
  }
  /**
   * Construtor padrão.
   */
  public Oportunidade() {
    // nossas faqs
    faqList().add(FAQ_TUTORIAL_OPORTUNIDADE);
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_oportunidade");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_OPORTUNIDADE_ID);  
    fieldList().add(FIELD_DEPARTAMENTO_ID);  
    fieldList().add(FIELD_USUARIO_ID);  
    fieldList().add(FIELD_CAMPANHA_ID);  
    fieldList().add(FIELD_CLIENTE_ID);  
    fieldList().add(FIELD_FASE_ID);  
    fieldList().add(FIELD_DATA_ACOMPANHAMENTO);  
    fieldList().add(FIELD_PERCENTUAL_SUCESSO);  
    fieldList().add(FIELD_STATUS);  
    fieldList().add(FIELD_VALOR);  
    fieldList().add(FIELD_DESCRICAO);  
    fieldList().add(FIELD_USUARIO_INCLUSAO_ID);  
    fieldList().add(FIELD_DATA_INCLUSAO);  
    fieldList().add(FIELD_USUARIO_ALTERACAO_ID);  
    fieldList().add(FIELD_DATA_HORA_ALTERACAO);
    fieldList().add(FIELD_LINK_EXTERNO);
    // nossos lookups
    lookupList().add(LOOKUP_DEPARTAMENTO);
    lookupList().add(LOOKUP_CLIENTE);
    lookupList().add(LOOKUP_CAMPANHA);
    lookupList().add(LOOKUP_USUARIO);
    lookupList().add(LOOKUP_FASE);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_EMPRESA_ID);
    userParamList().add(USER_PARAM_OPORTUNIDADE_ID);
    userParamList().add(USER_PARAM_DEPARTAMENTO_ID);
    userParamList().add(USER_PARAM_USUARIO_ID);
    userParamList().add(USER_PARAM_CAMPANHA_ID);
    userParamList().add(USER_PARAM_CLIENTE_ID);
    userParamList().add(USER_PARAM_FASE_ID);
    userParamList().add(USER_PARAM_STATUS);
  }

  /**
   * Exclui o(a) Oportunidade informado(a) por 'oportunidadeInfo'.
   * @param oportunidadeInfo OportunidadeInfo referente a(o) Oportunidade
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(OportunidadeInfo oportunidadeInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // nossa instância de Oportunidade Histórico
      OportunidadeHistorico oportunidadeHistorico = (OportunidadeHistorico)getFacade().getEntity(OportunidadeHistorico.CLASS_NAME);
      // seleciona as oportunidades
      OportunidadeHistoricoInfo[] oportunidadeHistoricoInfoList = oportunidadeHistorico.selectByFilter(oportunidadeInfo.getEmpresaId(), oportunidadeInfo.getOportunidadeId(), null);
      for (int i=0; i<oportunidadeHistoricoInfoList.length; i++)
        oportunidadeHistorico.delete(oportunidadeHistoricoInfoList[i]);
      // exclui o registro
      super.delete(oportunidadeInfo);
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
   * Insere o(a) Oportunidade identificado(a) por 'oportunidadeInfo'.
   * @param oportunidadeInfo OportunidadeInfo contendo as informações do(a) Oportunidade que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(OportunidadeInfo oportunidadeInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(oportunidadeInfo);
      // valor da seqüência
      oportunidadeInfo.setOportunidadeId(getNextSequence(FIELD_OPORTUNIDADE_ID, Oportunidade.FIELD_EMPRESA_ID.getFieldName() + "=" + oportunidadeInfo.getEmpresaId()));
      // usuários
      oportunidadeInfo.setUsuarioInclusaoId(getFacade().getLoggedUser().getId());
      oportunidadeInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      oportunidadeInfo.setDataInclusao(new Timestamp(DateTools.getActualDate().getTime())); // apenas a data
      oportunidadeInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // insere o registro
      super.insert(oportunidadeInfo);
      // salva tudo
      getFacade().commitTransaction();
      // nossa instância de Oportunidade Histórico
      OportunidadeHistorico oportunidadeHistorico = (OportunidadeHistorico)getFacade().getEntity(OportunidadeHistorico.CLASS_NAME);
      // OportunidadeHistoricoInfo para editar
      OportunidadeHistoricoInfo oportunidadeHistoricoInfo = null;
      // info em branco
      oportunidadeHistoricoInfo = new OportunidadeHistoricoInfo();
      // insere no histórico
      oportunidadeHistoricoInfo.setEmpresaId(oportunidadeInfo.getEmpresaId());
      oportunidadeHistoricoInfo.setOportunidadeId(oportunidadeInfo.getOportunidadeId());
      oportunidadeHistoricoInfo.setUsuarioInclusaoId(oportunidadeInfo.getUsuarioId());
      oportunidadeHistoricoInfo.setStatus(oportunidadeInfo.getStatus());
      oportunidadeHistoricoInfo.setStatusAnterior(oportunidadeInfo.getStatus());
      // insere na Oportunidade Histórico
      oportunidadeHistorico.insert(oportunidadeHistoricoInfo);
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Retorna um OportunidadeInfo referente a(o) Oportunidade
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param empresaId Empresa ID.
   * @param oportunidadeId Oportunidade ID.
   * @return Retorna um OportunidadeInfo referente a(o) Oportunidade
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public OportunidadeInfo selectByPrimaryKey(
                int empresaId,
                int oportunidadeId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_OPORTUNIDADE_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, oportunidadeId);
      OportunidadeInfo[] result = (OportunidadeInfo[])select(statement);
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
   * Retorna um OportunidadeInfo[] contendo a lista de Oportunidade
   * indicados(as) pelos parâmetros de pesquisa.
   * @param empresaId Empresa ID.
   * @param oportunidadeId Oportunidade ID ou 0 para todos.
   * @param departamentoId Departamento ID ou 0 para todos.
   * @param usuarioId Usuário ID ou 0 para todos.
   * @param campanhaId Campanha ID ou 0 para todos.
   * @param clienteId Cliente ID ou 0 para todos.
   * @param faseId Fase ID ou 0 para todos.
   * @param dataAcompanhamento Data Acompanhamento.
   * @param status Status ou 0 para todos.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um OportunidadeInfo[] contendo a lista de Oportunidade
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public OportunidadeInfo[] selectByFilter(
                int empresaId,
                int oportunidadeId,
                int departamentoId,
                int usuarioId,
                int campanhaId,
                int clienteId,
                int faseId,
                int status,
                Paginate paginate) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_OPORTUNIDADE_ID.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) AND " +
                                                  "((" + FIELD_OPORTUNIDADE_ID.getFieldName(getTableName()) + " = ?) OR (" + oportunidadeId + " = 0)) AND " +
                                                  "((" + FIELD_DEPARTAMENTO_ID.getFieldName(getTableName()) + " = ?) OR (" + departamentoId + " = 0)) AND " +
                                                  "((" + FIELD_USUARIO_ID.getFieldName(getTableName()) + " = ?) OR (" + usuarioId + " = 0)) AND " +
                                                  "((" + FIELD_CAMPANHA_ID.getFieldName(getTableName()) + " = ?) OR (" + campanhaId + " = 0)) AND " +
                                                  "((" + FIELD_CLIENTE_ID.getFieldName(getTableName()) + " = ?) OR (" + clienteId + " = 0)) AND " +
                                                  "((" + FIELD_FASE_ID.getFieldName(getTableName()) + " = ?) OR (" + faseId + " = 0)) AND " +
                                                  "((" + FIELD_STATUS.getFieldName(getTableName()) + " = ?) OR (" + status + " = " + StatusOportunidade.TODOS   + "))",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setInt(1, empresaId);
      statement.setInt(2, oportunidadeId);
      statement.setInt(3, departamentoId);
      statement.setInt(4, usuarioId);
      statement.setInt(5, campanhaId);
      statement.setInt(6, clienteId);
      statement.setInt(7, faseId);
      statement.setInt(8, status);
      // nosso resultado
      OportunidadeInfo[] result = (OportunidadeInfo[])select(statement);
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
   * Atualiza o(a) Oportunidade identificado(a) por 'oportunidadeInfo'.
   * @param oportunidadeInfo OportunidadeInfo contendo as informações do(a) Oportunidade que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(OportunidadeInfo oportunidadeInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(oportunidadeInfo);
      // usuários
      oportunidadeInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      oportunidadeInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // atualiza o registro
      super.update(oportunidadeInfo);
      // salva tudo
      getFacade().commitTransaction();
      // nossa instância de Oportunidade Histórico
      OportunidadeHistorico oportunidadeHistorico = (OportunidadeHistorico)getFacade().getEntity(OportunidadeHistorico.CLASS_NAME);
      // OportunidadeHistoricoInfo para editar
      OportunidadeHistoricoInfo oportunidadeHistoricoInfo = null;
      // info em branco
      oportunidadeHistoricoInfo = new OportunidadeHistoricoInfo();
      // info do último histórico da Oportunidade Histórico
      oportunidadeHistoricoInfo = oportunidadeHistorico.selectLast(oportunidadeInfo.getEmpresaId(), oportunidadeInfo.getOportunidadeId());
      if (oportunidadeHistoricoInfo.getStatus() != oportunidadeInfo.getStatus() || oportunidadeHistoricoInfo.getUsuarioInclusaoId() != oportunidadeInfo.getUsuarioId()) {
        // insere no histórico
        oportunidadeHistoricoInfo.setUsuarioInclusaoId(oportunidadeInfo.getUsuarioId());
        oportunidadeHistoricoInfo.setStatusAnterior(oportunidadeHistoricoInfo.getStatus());
        oportunidadeHistoricoInfo.setStatus(oportunidadeInfo.getStatus());
        // insere na Oportunidade Histórico
        oportunidadeHistorico.insert(oportunidadeHistoricoInfo);
      }
    }
    catch (Exception e) {
      // desfaz tudo
      getFacade().rollbackTransaction();
      throw e;
    } // try-catch
  }

  /**
   * Valida o(a) Oportunidade identificado(a) por 'oportunidadeInfo'.
   * @param oportunidadeInfo OportunidadeInfo contendo as informações do(a) Oportunidade que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(OportunidadeInfo oportunidadeInfo) throws Exception {
  }

}
