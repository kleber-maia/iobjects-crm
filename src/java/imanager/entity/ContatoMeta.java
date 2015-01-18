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
 * Representa a entidade Contato Meta no banco de dados da aplicação.
 */
public class ContatoMeta extends Entity {

  // nossos faqs
  FAQ FAQ_META = new FAQ("meta", "Contato", "Como definir metas para os Vendedores?", "", "meta.html", "");

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.ContatoMeta";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = Contato.ACTION.addNestedAction(new Action("contatoMeta", "Meta", "Mantém o cadastro de Metas de Vendas.", HELP, "entity/contatometa.jsp", "Contato", "", Action.CATEGORY_ENTITY, false, true));
  static public final Action ACTION_CADASTRO = new Action("contatoMetaCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/contatometacadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID   = new EntityField("in_empresa_id", "Empresa ID", "", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_CONTATO_ID   = new EntityField("in_contato_id", "Contato ID", "", "contatoId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, false, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_ANO          = new EntityField("sm_ano", "Ano", "Informe o Ano.", "ano", Types.INTEGER, 4, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_UPPER_CASE, "0000", true);
  static public final EntityField FIELD_MES          = new EntityField("sm_mes", "Mês", "Informe o Mês.", "mes", Types.INTEGER, 2, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_UPPER_CASE, "00", true);
  static public final EntityField FIELD_META_VENDA   = new EntityField("do_meta_venda", "Meta Vendas", "Informe a Meta de Vendas.", "metaVenda", Types.DOUBLE, 10, 2, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_DOUBLE, "", true);
  static public final EntityField FIELD_META_SERVICO = new EntityField("do_meta_servico", "Meta Serviço", "Informe a Meta de Serviços.", "metaServico", Types.DOUBLE, 10, 2, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_DOUBLE, "", true);
  // nossos parâmetros de usuário
  public final Param USER_PARAM_CONTATO_ID = new Param("userParamContatoId", "Contato ID", "", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);

  /**
   * Construtor padrão.
   */
  public ContatoMeta() {
    // nossos faqs
    faqList().add(FAQ_META);
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("relacionamento_contato_meta");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);
    fieldList().add(FIELD_CONTATO_ID);  
    fieldList().add(FIELD_ANO);  
    fieldList().add(FIELD_MES);  
    fieldList().add(FIELD_META_VENDA);  
    fieldList().add(FIELD_META_SERVICO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_CONTATO_ID);
  }

  /**
   * Exclui o(a) Contato Meta informado(a) por 'contatoMetaInfo'.
   * @param contatoMetaInfo ContatoMetaInfo referente a(o) Contato Meta
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(ContatoMetaInfo contatoMetaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(contatoMetaInfo);
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
   * Insere o(a) Contato Meta identificado(a) por 'contatoMetaInfo'.
   * @param contatoMetaInfo ContatoMetaInfo contendo as informações do(a) Contato Meta que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(ContatoMetaInfo contatoMetaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(contatoMetaInfo);
      // insere o registro
      super.insert(contatoMetaInfo);
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
   * Retorna um ContatoMetaInfo referente a(o) Contato Meta
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param empresaId Empresa ID.
   * @param contatoId Contato ID.
   * @param ano Ano.
   * @param mes Mês.
   * @return Retorna um ContatoMetaInfo referente a(o) Contato Meta
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ContatoMetaInfo selectByPrimaryKey(
                int empresaId,
                int contatoId,
                int ano,
                int mes
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_CONTATO_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_ANO.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_MES.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, contatoId);
      statement.setInt(3, ano);
      statement.setInt(4, mes);
      ContatoMetaInfo[] result = (ContatoMetaInfo[])select(statement);
      // retorna
      if (result.length == 0)
        throw new RecordNotFoundException(getClass().getName(), "selectByPrimaryKey", "Nenhum registro encontrado.");
      else if (result.length > 1)
        throw new ManyRecordsFoundException(getClass().getName(), "selectByPrimaryKey", "Mais de um registro encontrado.");
      else {
        // salva tudo
        getFacade().commitTransaction();
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
   * Retorna um ContatoMetaInfo[] contendo a lista de Contato Meta
   * indicados(as) pelos parâmetros de pesquisa.
   * @param empresaId Empresa ID ou 0.
   * @param contatoId Contato ID.
   * @return Retorna um ContatoMetaInfo[] contendo a lista de Contato Meta
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ContatoMetaInfo[] selectByFilter(
                int empresaId,
                int contatoId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_ANO.getFieldName(getTableName()) + " DESC", FIELD_MES.getFieldName(getTableName()) + " DESC"};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "((" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) OR (" + empresaId + " = 0)) AND " +
                                                  "(" + FIELD_CONTATO_ID.getFieldName(getTableName()) + " = ?)",
                                                  new String[]{},
                                                  new Paginate(6, 0)
                                                );
      statement.setInt(1, empresaId);
      statement.setInt(2, contatoId);
      // nosso resultado
      ContatoMetaInfo[] result = (ContatoMetaInfo[])select(statement);
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
   * Retorna um ContatoMetaInfo referente ao Contato Meta mais recente do
   * Contato indicado.
   * @param empresaId Empresa ID.
   * @param contatoId Contato ID.
   * @return Retorna um ContatoMetaInfo referente ao Contato Meta mais recente do
   * Contato indicado.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ContatoMetaInfo selectLast(
                int empresaId,
                int contatoId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_ANO.getFieldName(getTableName()) + " DESC", FIELD_MES.getFieldName(getTableName()) + " DESC"};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) AND " +
                                                  "(" + FIELD_CONTATO_ID.getFieldName(getTableName()) + " = ?)",
                                                  new String[]{},
                                                  new Paginate(1, 0)
                                                );
      statement.setInt(1, empresaId);
      statement.setInt(2, contatoId);
      // nosso resultado
      ContatoMetaInfo[] result = (ContatoMetaInfo[])select(statement);
      // retorna
      if (result.length == 0)
        throw new RecordNotFoundException(getClass().getName(), "selectLast", "Nenhum registro encontrado.");
      else {
        // salva tudo
        getFacade().commitTransaction();
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
   * Atualiza o(a) Contato Meta identificado(a) por 'contatoMetaInfo'.
   * @param contatoMetaInfo ContatoMetaInfo contendo as informações do(a) Contato Meta que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(ContatoMetaInfo contatoMetaInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(contatoMetaInfo);
      // atualiza o registro
      super.update(contatoMetaInfo);
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
   * Valida o(a) Contato Meta identificado(a) por 'contatoMetaInfo'.
   * @param contatoMetaInfo ContatoMetaInfo contendo as informações do(a) Contato Meta que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(ContatoMetaInfo contatoMetaInfo) throws Exception {
  }

}
