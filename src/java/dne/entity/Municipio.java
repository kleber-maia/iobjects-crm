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
     
package dne.entity;

import java.sql.*;
import java.util.*;

import iobjects.*;
import iobjects.entity.*;
import iobjects.help.*;

/**
 * Representa a entidade Município no banco de dados da aplicação.
 */
public class Municipio extends Entity {

  // identificação da classe
  static public final String CLASS_NAME = "dne.entity.Municipio";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas ações
  static public final Action ACTION          = new Action("municipio", "Município", "Mantém o cadastro de Município.", HELP, "entity/municipio.jsp", "DNE", "", Action.CATEGORY_ENTITY, false, false);
  static public final Action ACTION_CADASTRO = new Action("municipioCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/municipiocadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que está sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os parâmetros informados."));
  // nossos campos
  static public final EntityField FIELD_CODIGO_MUNICIPIO = new EntityField("ch_codigo_municipio", "Código Município", "Informe o(a) Código Município.", "codigoMunicipio", Types.CHAR, 7, 0, true, EntityField.ALIGN_LEFT, false, true, EntityField.FORMAT_NONE, "", true);
  static public final EntityField FIELD_MUNICIPIO = new EntityField("va_municipio", "Município", "Informe o(a) Município.", "municipio", Types.VARCHAR, 250, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);
  static public final EntityField FIELD_CODIGO_UF = new EntityField("ch_codigo_uf", "Código UF", "Informe o(a) Código UF.", "codigoUf", Types.CHAR, 2, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);
  static public final EntityField FIELD_UF = new EntityField("ch_uf", "UF", "Informe o(a) UF.", "uf", Types.CHAR, 2, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", true);

  /**
   * Construtor padrão.
   */
  public Municipio() {
    // nossas ações
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("dne_municipio");
    // nossos campos  
    fieldList().add(FIELD_CODIGO_MUNICIPIO);  
    fieldList().add(FIELD_MUNICIPIO);  
    fieldList().add(FIELD_CODIGO_UF);  
    fieldList().add(FIELD_UF);  
  }

  /**
   * Exclui o(a) Município informado(a) por 'municipioInfo'.
   * @param municipioInfo MunicipioInfo referente a(o) Município
   *        que se deseja excluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(MunicipioInfo municipioInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(municipioInfo);
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
   * Insere o(a) Município identificado(a) por 'municipioInfo'.
   * @param municipioInfo MunicipioInfo contendo as informações do(a) Município que se
   *                    deseja incluir.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(MunicipioInfo municipioInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(municipioInfo);
      // insere o registro
      super.insert(municipioInfo);
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
   * Retorna um MunicipioInfo referente a(o) Município
   * indicado(a) pelos parâmetros que representam sua chave primária.
   * @param codigoMunicipio Código Município.
   * @return Retorna um MunicipioInfo referente a(o) Município
   * indicado pelos parâmetros que representam sua chave primária.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public MunicipioInfo selectByPrimaryKey(
                String codigoMunicipio
           ) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_CODIGO_MUNICIPIO.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setString(1, codigoMunicipio);
      MunicipioInfo[] result = (MunicipioInfo[])select(statement);
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
   * Retorna um MunicipioInfo[] contendo a lista de Município
   * indicados(as) pelos parâmetros de pesquisa.
   * @param municipio Município.
   * @param uf UF.
   * @param paginate Informação de paginação dos resultados ou null.
   * @return Retorna um MunicipioInfo[] contendo a lista de Município
   * indicados(as) pelos parâmetros de pesquisa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public MunicipioInfo[] selectByFilter(
                String municipio,
                String uf,
                Paginate paginate) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_MUNICIPIO.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "(" + FIELD_MUNICIPIO.getFieldName(getTableName()) + " LIKE ?) AND " +
                                                  "(" + FIELD_UF.getFieldName(getTableName()) + " LIKE ?)",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setString(1, municipio + "%");
      statement.setString(2, uf + "%");
      // nosso resultado
      MunicipioInfo[] result = (MunicipioInfo[])select(statement);
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
   * Atualiza o(a) Município identificado(a) por 'municipioInfo'.
   * @param municipioInfo MunicipioInfo contendo as informações do(a) Município que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(MunicipioInfo municipioInfo) throws Exception {
    // inicia transação
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(municipioInfo);
      // atualiza o registro
      super.update(municipioInfo);
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
   * Valida o(a) Município identificado(a) por 'municipioInfo'.
   * @param municipioInfo MunicipioInfo contendo as informações do(a) Município que se
   *                    deseja validar.
   * @throws Exception Em caso de exceção no preenchimento dos dados informados.
   */
  public void validate(MunicipioInfo municipioInfo) throws Exception {
  }

}
