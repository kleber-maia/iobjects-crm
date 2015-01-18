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
 * Representa a entidade Grupo Contato no banco de dados da aplicação.
 */
public class GrupoContato extends Entity {

  // identificação da classe 
  static public final String CLASS_NAME = "imanager.entity.GrupoContato";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("grupoContato", "Grupo de Contato", "Mantém o cadastro dos Grupos de Contatos.", HELP, "entity/grupocontato.jsp", "Contato", "Auxiliares", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("grupoContatoCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/grupocontatocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT         = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT       = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_INSERT_CHILD = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir filho", "Insere um novo registro, filho do registro selecionado."));  
  static public final Command COMMAND_DELETE       = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE         = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH       = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_GRUPO_CONTATO_ID = new EntityField("in_grupo_contato_id", "Grupo Contato ID", "", "grupoContatoId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true, "", "value != ''", "Obrigatório informar o Nome.");
  // nossos parâmetros de usuário
  public final Param USER_PARAM_GRUPO_CONTATO_PAI_ID = new Param("userParamGrupoContatoPaiID", "Centro Pai ID", "", "", 4, Param.ALIGN_RIGHT, Param.FORMAT_INTEGER);
  public final Param USER_PARAM_GRUPO_CONTATO_PAI    = new Param("userParamGrupoContatoPai", "Centro Pai", "", "", 0, Param.ALIGN_RIGHT, Param.FORMAT_NONE);  

  /**
   * Delimitador de nível entre GrupoContatos.
   */
  static public final String LEVEL_DELIMITER = "/";  

  /**
   * Construtor padrão.
   */
  public GrupoContato() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("relacionamento_grupo_contato");
    // nossos campos  
    fieldList().add(FIELD_GRUPO_CONTATO_ID);  
    fieldList().add(FIELD_NOME);  
    // nossos parâmetros de usuário
    userParamList().add(USER_PARAM_GRUPO_CONTATO_PAI_ID);
    userParamList().add(USER_PARAM_GRUPO_CONTATO_PAI);    
  }

  /**
   * Exclui o(a) Grupo Contato informado(a) por 'grupoContatoInfo'.
   * @param grupoContatoInfo GrupoContatoInfo referente a(o) Grupo Contato
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(GrupoContatoInfo grupoContatoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(grupoContatoInfo);
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
   * Insere o(a) Grupo Contato identificado(a) por 'grupoContatoInfo'.
   * @param grupoContatoInfo GrupoContatoInfo contendo as informações do(a) Grupo Contato que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(GrupoContatoInfo grupoContatoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(grupoContatoInfo);
      // valor da seqüência
      grupoContatoInfo.setGrupoContatoId(getNextSequence(FIELD_GRUPO_CONTATO_ID));
      // insere o registro
      super.insert(grupoContatoInfo);
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
   * Retorna um GrupoContatoInfo referente a(o) Grupo Contato
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param grupoContatoId Grupo Contato ID.
   * @return Retorna um GrupoContatoInfo referente a(o) Grupo Contato
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public GrupoContatoInfo selectByPrimaryKey(
              int grupoContatoId
         ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_GRUPO_CONTATO_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, grupoContatoId);
      GrupoContatoInfo[] result = (GrupoContatoInfo[])select(statement);
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
   * Retorna um GrupoContatoInfo[] contendo a lista de Grupo Contato
   * indicados(as) pelos parâmetros de pesquisa.
   * @param nome Nome.
   * @return Retorna um GrupoContatoInfo[] contendo a lista de Grupo Contato
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public GrupoContatoInfo[] selectByFilter(
              String nome
         ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_NOME.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?)"
                                                );
      statement.setString(1, nome + "%");
      // nosso resultado
      GrupoContatoInfo[] result = (GrupoContatoInfo[])select(statement);
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
   * Atualiza o(a) Grupo Contato identificado(a) por 'grupoContatoInfo'.
   * @param grupoContatoInfo GrupoContatoInfo contendo as informações do(a) Grupo Contato que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(GrupoContatoInfo grupoContatoInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(grupoContatoInfo);
      // atualiza o registro
      super.update(grupoContatoInfo);
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
   * Valida o(a) Grupo Contato identificado(a) por 'grupoContatoInfo'.
   * @param grupoContatoInfo GrupoContatoInfo contendo as informações do(a) Grupo Contato que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(GrupoContatoInfo grupoContatoInfo) throws Exception {
  }

}
