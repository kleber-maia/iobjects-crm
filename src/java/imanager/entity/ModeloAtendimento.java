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
 * Representa a entidade Modelo Atendimento no banco de dados da aplicação.
 */
public class ModeloAtendimento extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "imanager.entity.ModeloAtendimento";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("modeloAtendimento", "Modelo de Atendimento", "Mantém o cadastro de Modelo de Atendimento.", HELP, "entity/modeloatendimento.jsp", "CRM", "Auxiliares", Action.CATEGORY_ENTITY, false, false);
  static public final Action ACTION_CADASTRO = new Action("modeloAtendimentoCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/modeloatendimentocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID = new EntityField("in_empresa_id", "Empresa ID", "", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_MODELO_ATENDIMENTO_ID = new EntityField("in_modelo_atendimento_id", "Modelo Atendimento ID", "", "modeloAtendimentoId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 50, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatorio informar o Nome.");
  static public final EntityField FIELD_DEPARTAMENTO_ID = new EntityField("in_departamento_id", "Departamento ID", "", "departamentoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != ''", "Obrigatório selecionar o Departamento.");
  static public final EntityField FIELD_ASSUNTO_ID = new EntityField("in_assunto_id", "Assunto ID", "", "assuntoId", Types.INTEGER, 10, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true, "", "value != ''", "Obrigatório selecionar o Assunto.");
  static public final EntityField FIELD_DESCRICAO = new EntityField("va_descricao", "Descrição", "Informe a Descrição.", "descricao", Types.VARCHAR, 2147483647, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true, "", "value != ''", "Obrigatório informar a Descrição.");
  // nossos lookups
  static public final EntityLookup LOOKUP_DEPARTAMENTO = new EntityLookup("lookupDepartamento", "Departamento", "Selecione o Departamento.", Departamento.CLASS_NAME, new EntityField[]{FIELD_EMPRESA_ID, FIELD_DEPARTAMENTO_ID}, new EntityField[]{Departamento.FIELD_NOME}, new EntityField[]{Departamento.FIELD_NOME});
  static public final EntityLookup LOOKUP_ASSUNTO = new EntityLookup("lookupAssunto", "Assunto", "Selecione o Assunto.", Assunto.CLASS_NAME, new EntityField[]{FIELD_ASSUNTO_ID}, new EntityField[]{Assunto.FIELD_NOME}, new EntityField[]{Assunto.FIELD_NOME});
  // nossos parâmetros de usuário
  public final Param USER_PARAM_DEPARTAMENTO_ID = new Param("userParamDepartamentoId", "Departamento", "Selecione o Departamento.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_ASSUNTO_ID = new Param("userParamAssuntoId", "Assunto", "Selecione o Assunto.", "", 10, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);

  /**
   * Construtor padrão.
   */
  public ModeloAtendimento() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("crm_modelo_atendimento");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_MODELO_ATENDIMENTO_ID);  
    fieldList().add(FIELD_NOME);  
    fieldList().add(FIELD_DEPARTAMENTO_ID);  
    fieldList().add(FIELD_ASSUNTO_ID);  
    fieldList().add(FIELD_DESCRICAO);
    // nossos lookups
    lookupList().add(LOOKUP_DEPARTAMENTO);
    lookupList().add(LOOKUP_ASSUNTO);
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_DEPARTAMENTO_ID);
    userParamList().add(USER_PARAM_ASSUNTO_ID);
  }

  /**
   * Exclui o(a) Modelo Atendimento informado(a) por 'modeloAtendimentoInfo'.
   * @param modeloAtendimentoInfo ModeloAtendimentoInfo referente a(o) Modelo Atendimento
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(ModeloAtendimentoInfo modeloAtendimentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(modeloAtendimentoInfo);
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
   * Insere o(a) Modelo Atendimento identificado(a) por 'modeloAtendimentoInfo'.
   * @param modeloAtendimentoInfo ModeloAtendimentoInfo contendo as informações do(a) Modelo Atendimento que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(ModeloAtendimentoInfo modeloAtendimentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(modeloAtendimentoInfo);
      // valor da seqüência
      modeloAtendimentoInfo.setModeloAtendimentoId(getNextSequence(FIELD_MODELO_ATENDIMENTO_ID, FIELD_EMPRESA_ID.getFieldName() + "=" + modeloAtendimentoInfo.getEmpresaId()));
      // insere o registro
      super.insert(modeloAtendimentoInfo);
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
   * Retorna um ModeloAtendimentoInfo referente a(o) Modelo Atendimento
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param empresaId Empresa ID.
   * @param modeloAtendimentoId Modelo Atendimento ID.
   * @return Retorna um ModeloAtendimentoInfo referente a(o) Modelo Atendimento
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ModeloAtendimentoInfo selectByPrimaryKey(
                int empresaId,
                int modeloAtendimentoId
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_MODELO_ATENDIMENTO_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, modeloAtendimentoId);
      ModeloAtendimentoInfo[] result = (ModeloAtendimentoInfo[])select(statement);
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
   * Retorna um ModeloAtendimentoInfo[] contendo a lista de Modelo Atendimento
   * indicados(as) pelos parâmetros de pesquisa.
   * @param empresaId Empresa ID.
   * @param nome Nome.
   * @param departamentoId Departamento ID ou 0 para todos.
   * @param assuntoId Assunto ID ou 0 para todos.
   * @param paginate Informações de paginação dos resultados ou null.
   * @return Retorna um ModeloAtendimentoInfo[] contendo a lista de Modelo Atendimento
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public ModeloAtendimentoInfo[] selectByFilter(
                int      empresaId,
                String   nome,
                int      departamentoId,
                int      assuntoId,
                Paginate paginate
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_NOME.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) AND " +
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "((" + FIELD_DEPARTAMENTO_ID.getFieldName(getTableName()) + " = ?) OR (" + departamentoId + "= 0)) AND " +
                                                  "((" + FIELD_ASSUNTO_ID.getFieldName(getTableName()) + " = ?) OR (" + assuntoId + " = 0))",
                                                  new String[0],
                                                  paginate
                                                );
      statement.setInt(1, empresaId);
      statement.setString(2, nome + "%");
      statement.setInt(3, departamentoId);
      statement.setInt(4, assuntoId);
      // nosso resultado
      ModeloAtendimentoInfo[] result = (ModeloAtendimentoInfo[])select(statement);
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
   * Atualiza o(a) Modelo Atendimento identificado(a) por 'modeloAtendimentoInfo'.
   * @param modeloAtendimentoInfo ModeloAtendimentoInfo contendo as informações do(a) Modelo Atendimento que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(ModeloAtendimentoInfo modeloAtendimentoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(modeloAtendimentoInfo);
      // atualiza o registro
      super.update(modeloAtendimentoInfo);
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
   * Valida o(a) Modelo Atendimento identificado(a) por 'modeloAtendimentoInfo'.
   * @param modeloAtendimentoInfo ModeloAtendimentoInfo contendo as informações do(a) Modelo Atendimento que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(ModeloAtendimentoInfo modeloAtendimentoInfo) throws Exception {
  }

}
