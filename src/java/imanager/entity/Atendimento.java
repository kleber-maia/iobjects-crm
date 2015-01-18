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
import iobjects.ui.entity.*;
import iobjects.util.*;

/**
 * Representa a entidade Atendimento no banco de dados da aplicação.
 */ 
public class Atendimento extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.Atendimento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("atendimento", "Atendimento", "Mantém o cadastro de Atendimentos realizados a Clientes.", HELP, "entity/atendimento.jsp", "CRM", "", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("atendimentoCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/atendimentocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT                       = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT                     = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE                     = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE                       = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH                     = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa ID", "", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_ATENDIMENTO_ID = new EntityField("in_atendimento_id", "ID", "Informa o ID do Atendimento.", "atendimentoId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_LEFT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DEPARTAMENTO_ID = new EntityField("in_departamento_id", "Departamento ID", "", "departamentoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "selectedIndex > 0", "Obrigatório selecionar o Departamento.");
  static public final EntityField FIELD_ASSUNTO_ID = new EntityField("in_assunto_id", "Assunto ID", "", "assuntoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigatório selecionar o Assunto.");
  static public final EntityField FIELD_MEIO_ID = new EntityField("in_meio_id", "Meio ID", "", "meioId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "selectedIndex > 0", "Obrigatório selecionar o Meio.");
  static public final EntityField FIELD_CLIENTE_ID = new EntityField("in_cliente_id", "Cliente ID", "", "clienteId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigatório selecionar o Cliente.");
  static public final EntityField FIELD_CAMPANHA_ID = new EntityField("in_campanha_id", "Campanha", "Selecione a Campanha", "campanhaId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != 0", "Obrigatório selecionar a Campanha.");
  static public final EntityField FIELD_DESCRICAO = new EntityField("va_descricao", "Descrição", "Informe a Descrição.", "descricao", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true, "", "", "");
  static public final EntityField FIELD_DATA_HORA_INICIO = new EntityField("dt_inicio", "Início", "Informe a Data/Hora de Início.", "dataHoraInicio", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE_TIME, "", false);
  static public final EntityField FIELD_DATA_HORA_TERMINO = new EntityField("dt_termino", "Término", "Informe a Data/Hora de Término.", "dataHoraTermino", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE_TIME, "", false);
  static public final EntityField FIELD_DATA_INCLUSAO = new EntityField("da_inclusao", "Data Inclusão", "", "dataInclusao", Types.TIMESTAMP, 10, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", true);
  static public final EntityField FIELD_USUARIO_INCLUSAO_ID = new EntityField("in_usuario_inclusao_id", "Usuário de Inclusão", "", "usuarioInclusaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_DATA_HORA_ALTERACAO = new EntityField("dt_alteracao", "Data/Hora Alteração", "", "dataHoraAlteracao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", true);
  static public final EntityField FIELD_USUARIO_ALTERACAO_ID = new EntityField("in_usuario_alteracao_id", "Usuário de Alteração", "", "usuarioAlteracaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_LINK_EXTERNO = new EntityField("va_link_externo", "Endereço", "Informe o Endereço.", "linkExterno", Types.VARCHAR, 2000, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  // nossos lookups
  static public final EntityLookup LOOKUP_EMPRESA      = new EntityLookup("lookupEmpresa", "Empresa", "", Empresa.CLASS_NAME, new EntityField[]{FIELD_EMPRESA_ID}, new EntityField[]{Empresa.FIELD_NOME}, new EntityField[]{Empresa.FIELD_NOME}, "", true, true);
  static public final EntityLookup LOOKUP_DEPARTAMENTO = new EntityLookup("lookupDepartamento", "Departamento", "Selecione o Departamento.", Departamento.CLASS_NAME, new EntityField[]{FIELD_EMPRESA_ID, FIELD_DEPARTAMENTO_ID}, new EntityField[]{Departamento.FIELD_NOME}, new EntityField[]{Departamento.FIELD_NOME});
  static public final EntityLookup LOOKUP_ASSUNTO      = new EntityLookup("lookupAssunto", "Assunto", "Selecione o Assunto.", Assunto.CLASS_NAME, FIELD_ASSUNTO_ID, Assunto.FIELD_NOME, Assunto.FIELD_NOME);
  static public final EntityLookup LOOKUP_MEIO         = new EntityLookup("lookupMeio", "Meio", "Selecione o Meio.", Meio.CLASS_NAME, FIELD_MEIO_ID, Meio.FIELD_NOME, Meio.FIELD_NOME);
  static public final EntityLookup LOOKUP_CLIENTE      = new EntityLookup("lookupCliente", "Cliente", "Selecione o Cliente.", Contato.CLASS_NAME, FIELD_CLIENTE_ID, Contato.FIELD_NOME, Contato.FIELD_NOME);
  static public final EntityLookup LOOKUP_CAMPANHA     = new EntityLookup("lookupCampanha", "Campanha", "Selecione a Campanha.", Campanha.CLASS_NAME, FIELD_CAMPANHA_ID, Campanha.FIELD_NOME, Campanha.FIELD_NOME);
  // nossos parâmetros de usuário
  public final Param USER_PARAM_ATENDIMENTO_ID = new Param("userParamAtendimentoId", "ID", "Informe o ID do Atendimento.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_DEPARTAMENTO_ID = new Param("userParamDepartamentoId", "Departamento", "Selecione o Departamento.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_ASSUNTO_ID = new Param("userParamAssuntoId", "Assunto", "Selecione o Assunto.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_MEIO_ID = new Param("userParamMeioId", "Meio", "Selecione o Meio.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_CLIENTE_ID = new Param("userParamClienteId", "Cliente", "Selecione o Cliente.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_CAMPANHA_ID = new Param("userParamCampanhaId", "Campanha", "Selecione a Campanha.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_CLIENTE_USER = new Param("userParamClienteId" + ExternalLookup.USER, "");
  public final Param USER_PARAM_USUARIO_ID = new Param("userParamUsuarioId", "Usuário", "Selecione o Usuário.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_USUARIO_USER = new Param("userParamUsuarioId" + ExternalLookup.USER, "");
  public final Param USER_PARAM_DATA_INICIAL = new Param("userParamDataInicial", "De", "Informe a Data Inicial.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar a Data Inicial.");
  public final Param USER_PARAM_DATA_FINAL = new Param("userParamDataFinal", "Até", "Informe a Data Final.", "", 10, Param.ALIGN_LEFT, Param.FORMAT_DATE, "", "value != ''", "Obrigatório informar a Data Final.");
  // nosso parâmetro de controle
  public final Param CONTROL_PARAM_CENTRAL_ATENDIMENTO = new Param("controlParamCentraLAtendimento", "false");


  {
    USER_PARAM_DATA_INICIAL.setValue("01/" + NumberTools.completeZero(DateTools.getActualMonth(), 2) + "/" + DateTools.getActualYear());
    USER_PARAM_DATA_FINAL.setValue(DateTools.formatDate(DateTools.getActualDate()));
    // *
    USER_PARAM_DATA_FINAL.setSpecialConstraint(true, USER_PARAM_DATA_INICIAL);
  }

  /**
   * Construtor padrão.
   */
  public Atendimento() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_atendimento");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_ATENDIMENTO_ID);  
    fieldList().add(FIELD_DEPARTAMENTO_ID);  
    fieldList().add(FIELD_ASSUNTO_ID);  
    fieldList().add(FIELD_MEIO_ID);  
    fieldList().add(FIELD_CLIENTE_ID);
    fieldList().add(FIELD_CAMPANHA_ID);
    fieldList().add(FIELD_DESCRICAO);  
    fieldList().add(FIELD_DATA_HORA_INICIO);  
    fieldList().add(FIELD_DATA_HORA_TERMINO);  
    fieldList().add(FIELD_DATA_INCLUSAO);
    fieldList().add(FIELD_USUARIO_INCLUSAO_ID);  
    fieldList().add(FIELD_DATA_HORA_ALTERACAO);  
    fieldList().add(FIELD_USUARIO_ALTERACAO_ID);
    fieldList().add(FIELD_LINK_EXTERNO);
    // nossos lookups
    lookupList().add(LOOKUP_EMPRESA);
    lookupList().add(LOOKUP_DEPARTAMENTO);
    lookupList().add(LOOKUP_ASSUNTO);
    lookupList().add(LOOKUP_MEIO);
    lookupList().add(LOOKUP_CLIENTE);
    lookupList().add(LOOKUP_CAMPANHA);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_ATENDIMENTO_ID);
    userParamList().add(USER_PARAM_DEPARTAMENTO_ID);
    userParamList().add(USER_PARAM_ASSUNTO_ID);
    userParamList().add(USER_PARAM_MEIO_ID);
    userParamList().add(USER_PARAM_CLIENTE_ID);
    userParamList().add(USER_PARAM_CAMPANHA_ID);
    userParamList().add(USER_PARAM_CLIENTE_USER);
    userParamList().add(USER_PARAM_USUARIO_ID);
    userParamList().add(USER_PARAM_USUARIO_USER);
    userParamList().add(USER_PARAM_DATA_INICIAL);
    userParamList().add(USER_PARAM_DATA_FINAL);
  }

  /**
   * Exclui o(a) Atendimento informado(a) por 'atendimentoInfo'.
   * @param atendimentoInfo AtendimentoInfo referente a(o) Atendimento
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(AtendimentoInfo atendimentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(atendimentoInfo);
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
   * Inicializa o objeto.
   */
  public void initialize() {
    // define o usuário atual como valor padrão do parâmetro de pesquisa
    //USER_PARAM_USUARIO_ID.setValue(getFacade().getLoggedUser().getId() + "");
    //USER_PARAM_USUARIO_USER.setValue(getFacade().getLoggedUser().getName());
  }

  /**
   * Insere o(a) Atendimento identificado(a) por 'atendimentoInfo'.
   * @param atendimentoInfo AtendimentoInfo contendo as informações do(a) Atendimento que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(AtendimentoInfo atendimentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(atendimentoInfo);
      // valor da seqüência
      atendimentoInfo.setAtendimentoId(getNextSequence(FIELD_ATENDIMENTO_ID, FIELD_EMPRESA_ID.getFieldName() + "=" + atendimentoInfo.getEmpresaId()));
      // usuários
      atendimentoInfo.setUsuarioInclusaoId(getFacade().getLoggedUser().getId());
      atendimentoInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      atendimentoInfo.setDataInclusao(new Timestamp(DateTools.getActualDate().getTime())); // apenas a data
      atendimentoInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // insere o registro
      super.insert(atendimentoInfo);
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
   * Retorna um AtendimentoInfo referente a(o) Atendimento
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param empresaId Empresa ID.
   * @param atendimentoId Atendimento ID.
   * @return Retorna um AtendimentoInfo referente a(o) Atendimento
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AtendimentoInfo selectByPrimaryKey(
                int empresaId,
                int atendimentoId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_ATENDIMENTO_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, atendimentoId);
      AtendimentoInfo[] result = (AtendimentoInfo[])select(statement);
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
   * Retorna um AtendimentoInfo[] contendo a lista de Atendimento
   * indicados(as) pelos parâmetros de pesquisa.
   * @param empresaId Empresa ID ou 0.
   * @param atendimentoId Atendimento ID ou 0.
   * @param departamentoId Departamento ID ou 0.
   * @param assuntoId Assunto ID ou 0.
   * @param meioId Meio ID ou 0.
   * @param clienteId Cliente ID ou 0.
   * @param usuarioId Usuário ID ou 0.
   * @param dataInicial Data Inicial.
   * @param dataFinal Data Final.
   * @param paginate Informações de paginação dos resultados ou null.
   * @return Retorna um AtendimentoInfo[] contendo a lista de Atendimento
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AtendimentoInfo[] selectByFilter(
                int empresaId,
                int atendimentoId,
                int departamentoId,
                int assuntoId,
                int meioId,
                int clienteId,
                int campanhaId,
                int usuarioId,
                Timestamp dataInicial,
                Timestamp dataFinal,
                Paginate paginate
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_EMPRESA_ID.getFieldName(getTableName()), FIELD_ATENDIMENTO_ID.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "((" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) OR (" + empresaId + " = 0)) AND " +
                                                  "((" + FIELD_ATENDIMENTO_ID.getFieldName(getTableName()) + " = ?) OR (" + atendimentoId + " = 0)) AND " +
                                                  "((" + FIELD_DEPARTAMENTO_ID.getFieldName(getTableName()) + " = ?) OR (" + departamentoId + " = 0)) AND " +
                                                  "((" + FIELD_ASSUNTO_ID.getFieldName(getTableName()) + " = ?) OR (" + assuntoId + " = 0)) AND " +
                                                  "((" + FIELD_MEIO_ID.getFieldName(getTableName()) + " = ?) OR (" + meioId + " = 0)) AND " +
                                                  "((" + FIELD_CLIENTE_ID.getFieldName(getTableName()) + " = ?) OR (" + clienteId + " = 0)) AND " +
                                                  "((" + FIELD_CAMPANHA_ID.getFieldName(getTableName()) + " = ?) OR (" + campanhaId + " = 0)) AND " +
                                                  "((" + FIELD_USUARIO_INCLUSAO_ID.getFieldName(getTableName()) + " = ?) OR (" + usuarioId + " = 0)) AND " +
                                                  "((" + FIELD_DATA_INCLUSAO.getFieldName(getTableName()) + " >= ?) AND (" + FIELD_DATA_INCLUSAO.getFieldName(getTableName()) + " <= ?)) ",
                                                  new String[]{},
                                                  paginate
                                                );
      statement.setInt(1, empresaId);
      statement.setInt(2, atendimentoId);
      statement.setInt(3, departamentoId);
      statement.setInt(4, assuntoId);
      statement.setInt(5, meioId);
      statement.setInt(6, clienteId);
      statement.setInt(7, campanhaId);
      statement.setInt(8, usuarioId);
      statement.setTimestamp(9, dataInicial);
      statement.setTimestamp(10, dataFinal);
      // nosso resultado
      AtendimentoInfo[] result = (AtendimentoInfo[])select(statement);
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
   * Retorna um AtendimentoInfo[] contendo os últimos 30 registros de Atendimento
   * indicados(as) pelos parâmetros de pesquisa.
   * @param clienteId Cliente ID.
   * @return Retorna um AtendimentoInfo[] contendo os últimos 30 registros de Atendimento
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public AtendimentoInfo[] lastRecents(int clienteId) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_DATA_INCLUSAO.getFieldName(getTableName()) + " DESC "};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_CLIENTE_ID.getFieldName(getTableName()) + " = ?) ",
                                                  new String[]{},
                                                  new Paginate(29,0)
                                                 );
      statement.setInt(1, clienteId);
      // nosso resultado
      AtendimentoInfo[] result = (AtendimentoInfo[])select(statement);
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
   * Atualiza o(a) Atendimento identificado(a) por 'atendimentoInfo'.
   * @param atendimentoInfo AtendimentoInfo contendo as informações do(a) Atendimento que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(AtendimentoInfo atendimentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(atendimentoInfo);
      // usuários
      atendimentoInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId());
      atendimentoInfo.setDataHoraAlteracao(new Timestamp(System.currentTimeMillis()));
      // atualiza o registro
      super.update(atendimentoInfo);
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
   * Valida o(a) Atendimento identificado(a) por 'atendimentoInfo'.
   * @param atendimentoInfo AtendimentoInfo contendo as informações do(a) Atendimento que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(AtendimentoInfo atendimentoInfo) throws Exception {
  }

}
