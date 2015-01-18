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

import imanager.misc.*;
import iobjects.util.DateTools;

/**
 * Representa a entidade Campanha no banco de dados da aplicação.
 */
public class Campanha extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.Campanha";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("campanha", "Campanha", "Mantém o cadastro de Campanha de marketing ou publicitária.", HELP, "entity/campanha.jsp", "CRM", "Auxiliares", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("campanhaCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/campanhacadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_CAMPANHA_ID = new EntityField("in_campanha_id", "Campanha ID", "", "campanhaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 255, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Nome.");
  static public final EntityField FIELD_DESCRICAO = new EntityField("va_descricao", "Descrição", "Informe a Descrição.", "descricao", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  static public final EntityField FIELD_ARQUIVO = new EntityField("sm_arquivo", "Arquivo Morto", "Selecione se a Campanha está Arquivada.", "arquivo", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_INICIO = new EntityField("da_inicio", "Início", "Informe a Data de Início.", "dataInicio", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_DATA_TERMINO = new EntityField("da_termino", "Término", "Informe a Data de Término.", "dataTermino", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_VALOR_INVESTIDO = new EntityField("do_valor_investido", "Valor Investido", "Informa o Valor Investido.", "valorInvestido", Types.DOUBLE, 12, 2, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_DOUBLE, "", false);  
  static public final EntityField FIELD_AUTOMATIZADA = new EntityField("sm_automatizada", "Automatizada", "Selecione se a Campanha é Automatizada.", "automatizada", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DIAS_PERDIDA = new EntityField("sm_dias_perdida", "Dias Perdida", "Informe a quantidade de dias após a inclusão em que a Oportunidade é considerada perdida.", "diasPerdida", Types.SMALLINT, 5, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_USUARIO_INCLUSAO_ID = new EntityField("in_usuario_inclusao_id", "Usuário de Inclusão", "", "usuarioInclusaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_INCLUSAO = new EntityField("da_inclusao", "Data", "", "dataInclusao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE, "", false);
  static public final EntityField FIELD_USUARIO_ALTERACAO_ID = new EntityField("in_usuario_alteracao_id", "Usuário de Alteração", "", "usuarioAlteracaoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", false);
  static public final EntityField FIELD_DATA_HORA_ALTERACAO = new EntityField("dt_alteracao", "Data/Hora de Alteração", "", "dataHoraAlteracao", Types.TIMESTAMP, 10, 6, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_DATE_TIME, "", false);
  
  
  // nossos parâmetros de usuário
  public final Param USER_PARAM_ARQUIVO = new Param("userParamArquivo", "Arquivo Morto", "Selecione se mostra as Arquivadas.", NaoSim.NAO + "", 5, Param.ALIGN_LEFT, Param.FORMAT_INTEGER);

  {
    USER_PARAM_ARQUIVO.setLookupList(NaoSim.LOOKUP_LIST_FOR_PARAM);
  }

  static {
    FIELD_ARQUIVO.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
    FIELD_AUTOMATIZADA.setLookupList(NaoSim.LOOKUP_LIST_FOR_FIELD);
  }

  /**
   * Construtor padrão.
   */
  public Campanha() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_campanha");
    // nossos campos  
    fieldList().add(FIELD_CAMPANHA_ID);  
    fieldList().add(FIELD_NOME);  
    fieldList().add(FIELD_DESCRICAO);  
    fieldList().add(FIELD_ARQUIVO);  
    fieldList().add(FIELD_DATA_INICIO);  
    fieldList().add(FIELD_DATA_TERMINO);  
    fieldList().add(FIELD_VALOR_INVESTIDO);  
    fieldList().add(FIELD_AUTOMATIZADA);  
    fieldList().add(FIELD_DIAS_PERDIDA);  
    fieldList().add(FIELD_USUARIO_INCLUSAO_ID);  
    fieldList().add(FIELD_DATA_INCLUSAO);  
    fieldList().add(FIELD_USUARIO_ALTERACAO_ID);  
    fieldList().add(FIELD_DATA_HORA_ALTERACAO);      
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_ARQUIVO);
  }

  /**
   * Exclui o(a) Campanha informado(a) por 'campanhaInfo'.
   * @param campanhaInfo CampanhaInfo referente a(o) Campanha
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(CampanhaInfo campanhaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // nossa instancia de CampanhaAutomatizada
      CampanhaAutomatizada campanhaAutomatizada = (CampanhaAutomatizada)getFacade().getEntity(CampanhaAutomatizada.CLASS_NAME);
      // Seleciona todos as campanhasAutomatizadas para exclusão.
      CampanhaAutomatizadaInfo[] campanhaAutomatizadaInfoList = campanhaAutomatizada.selectByFilter(campanhaInfo.getCampanhaId(), 0, null);
      // loop
      for (int i = 0; i < campanhaAutomatizadaInfoList.length; i++) {
        // deleta
        campanhaAutomatizada.delete(campanhaAutomatizadaInfoList[i]);
      } // for
      // exclui o registro
      super.delete(campanhaInfo);
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
   * Insere o(a) Campanha identificado(a) por 'campanhaInfo'.
   * @param campanhaInfo CampanhaInfo contendo as informações do(a) Campanha que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(CampanhaInfo campanhaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(campanhaInfo);
      // valor da seqüência
      campanhaInfo.setCampanhaId(getNextSequence(FIELD_CAMPANHA_ID));
      // valores default
      campanhaInfo.setUsuarioInclusaoId(getFacade().getLoggedUser().getId()); 
      campanhaInfo.setDataInclusao(new Timestamp(DateTools.getActualDate().getTime()));
      campanhaInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId()); 
      campanhaInfo.setDataHoraAlteracao(new Timestamp(DateTools.getActualDateTime().getTime()));
      // insere o registro
      super.insert(campanhaInfo);
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
   * Retorna um CampanhaInfo referente a(o) Campanha
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param campanhaId Campanha ID.
   * @return Retorna um CampanhaInfo referente a(o) Campanha
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public CampanhaInfo selectByPrimaryKey(
                int campanhaId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_CAMPANHA_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, campanhaId);
      CampanhaInfo[] result = (CampanhaInfo[])select(statement);
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
   * Retorna um CampanhaInfo[] contendo a lista de Campanha
   * indicados(as) pelos parâmetros de pesquisa.
   * @param nome Nome.
   * @param dataInicial Data Inicial.
   * @param dataFinal Data Final.
   * @param arquivo NaoSim.NAO, NaoSim.SIM ou NaoSim.TODOS.
   * @param paginate Informações de paginação dos resultados ou null.
   * @return Retorna um CampanhaInfo[] contendo a lista de Campanha
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public CampanhaInfo[] selectByFilter(
                String nome,
                Timestamp dataInicial,
                Timestamp dataFinal,
                int arquivo,
                Paginate paginate
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_NOME.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  // período
                                                  "((? = ? AND ? = ?) OR " +
                                                   "(" + FIELD_DATA_INICIO.getFieldName(getTableName()) + " <> ? AND (" + FIELD_DATA_INICIO.getFieldName(getTableName()) + " >= ? OR " + FIELD_DATA_INICIO.getFieldName(getTableName()) + " <= ?)) OR " +
                                                   "(" + FIELD_DATA_TERMINO.getFieldName(getTableName()) + " <> ? AND (" + FIELD_DATA_TERMINO.getFieldName(getTableName()) + " >= ? OR " + FIELD_DATA_TERMINO.getFieldName(getTableName()) + " <= ?)) " +
                                                  ") AND " +
                                                  // arquivo
                                                  "((" + FIELD_ARQUIVO.getFieldName(getTableName()) + " = ?) OR (" + arquivo + " = " + NaoSim.TODOS + "))",
                                                  new String[0],
                                                  paginate
                                                );
      statement.setString(1, nome + "%");
      // período
      statement.setTimestamp(2, dataInicial);
      statement.setTimestamp(3, DateTools.ZERO_DATE);
      statement.setTimestamp(4, dataFinal);
      statement.setTimestamp(5, DateTools.ZERO_DATE);
      statement.setTimestamp(6, DateTools.ZERO_DATE);
      statement.setTimestamp(7, dataInicial);
      statement.setTimestamp(8, dataInicial);
      statement.setTimestamp(9, DateTools.ZERO_DATE);
      statement.setTimestamp(10, dataFinal);
      statement.setTimestamp(11, dataFinal);
      // *
      statement.setInt(12, arquivo);
      // nosso resultado
      CampanhaInfo[] result = (CampanhaInfo[])select(statement);
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
   * Atualiza o(a) Campanha identificado(a) por 'campanhaInfo'.
   * @param campanhaInfo CampanhaInfo contendo as informações do(a) Campanha que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(CampanhaInfo campanhaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(campanhaInfo);
      // altera valores
      campanhaInfo.setUsuarioAlteracaoId(getFacade().getLoggedUser().getId()); 
      campanhaInfo.setDataHoraAlteracao(new Timestamp(DateTools.getActualDateTime().getTime()));      
      // atualiza o registro
      super.update(campanhaInfo);
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
   * Valida o(a) Campanha identificado(a) por 'campanhaInfo'.
   * @param campanhaInfo CampanhaInfo contendo as informações do(a) Campanha que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(CampanhaInfo campanhaInfo) throws Exception {
  }

}
