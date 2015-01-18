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
 * Representa a entidade Agenda no banco de dados da aplica��o.
 */
public class Agenda extends Entity { 

  // identifica��o da classe
  static public final String CLASS_NAME = "imanager.entity.Agenda";
  // nossa ajuda extra
  static public final String HELP = "";
  // nossas a��es
  static public final Action ACTION          = new Action("agenda", "Agenda", "Mant�m o cadastro de Agenda.", HELP, "entity/agenda.jsp", "Contato", "Auxiliares", Action.CATEGORY_ENTITY, false, true);
  static public final Action ACTION_CADASTRO = new Action("agendaCadastro", ACTION.getCaption(), ACTION.getDescription(), HELP, "entity/agendacadastro.jsp", ACTION.getModule(), ACTION.getAccessPath(), Action.CATEGORY_ENTITY, ACTION.getMobile(), false);
  // nossos comandos
  static public final Command COMMAND_EDIT   = ACTION.addCommand(new Command(Command.COMMAND_EDIT,   "Editar",    "Edita o registro exibido, clicando na lista."));
  static public final Command COMMAND_INSERT = ACTION.addCommand(new Command(Command.COMMAND_INSERT, "Inserir",   "Insere um novo registro."));
  static public final Command COMMAND_DELETE = ACTION.addCommand(new Command(Command.COMMAND_DELETE, "Excluir",   "Exclui o(s) registro(s) selecionado(s)."));
  static public final Command COMMAND_SAVE   = ACTION.addCommand(new Command(Command.COMMAND_SAVE,   "Salvar",    "Salva o registro que est� sendo editado ou inserido."));
  static public final Command COMMAND_SEARCH = ACTION.addCommand(new Command(Command.COMMAND_SEARCH, "Pesquisar", "Pesquisa por registros com os par�metros informados."));
  // nossos campos
  static public final EntityField FIELD_EMPRESA_ID     = new EntityField("in_empresa_id", "Empresa", "Informa a Empresa.", "empresaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_AGENDA_ID      = new EntityField("in_agenda_id", "Agenda", "Informa a Agenda.", "agendaId", Types.INTEGER, 10, 0, true, EntityField.ALIGN_RIGHT, false, true, EntityField.FORMAT_INTEGER, "", true);
  static public final EntityField FIELD_NOME           = new EntityField("va_nome", "Nome", "Informe o Nome.", "nome", Types.VARCHAR, 100, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_UPPER_CASE, "", true);
  static public final EntityField FIELD_TABELA_HORARIO = new EntityField("ch_tabela_horario", "Hor�rio(s) Dipon�vel(is)", "Marque os hor�rios dispon�veis.", "tabelaHorario", Types.CHAR, 42, 0, false, EntityField.ALIGN_LEFT, true, true, EntityField.FORMAT_NONE, "", false);
  static public final EntityField FIELD_INTERVALO     = new EntityField("sm_intervalo", "Intervalo (Min)", "Informe qual ser� o intervalo de cada hor�rio. OBS: O valor de ser entre 10 e 120.", "intervalo", Types.SMALLINT, 3, 0, false, EntityField.ALIGN_RIGHT, true, true, EntityField.FORMAT_INTEGER, "", true);

  static public final String TABELA_HORARIO_COMERCIAL = "00000003ff0003ff0003ff0003ff0003ff00000000";

  /**
   * Construtor padr�o.
   */
  public Agenda() {
    // nossas a��es
    actionList().add(ACTION);
    actionList().add(ACTION_CADASTRO);
    // nossa tabela
    setTableName("relacionamento_agenda");
    // nossos campos  
    fieldList().add(FIELD_EMPRESA_ID);  
    fieldList().add(FIELD_AGENDA_ID);  
    fieldList().add(FIELD_NOME);  
    fieldList().add(FIELD_TABELA_HORARIO);  
    fieldList().add(FIELD_INTERVALO);
  }

  /**
   * Exclui o(a) Agenda informado(a) por 'agendaInfo'.
   * @param agendaInfo AgendaInfo referente a(o) Agenda
   *        que se deseja excluir.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public void delete(AgendaInfo agendaInfo) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // exclui o registro
      super.delete(agendaInfo);
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
   * Retorna um boolean[7][24], onde a primeira dimens�o representa os dias
   * da semana (domgingo a segunda) e a segunda dimens�o as horas de cada dia
   * (0h a 23h) da 'tabelaHorario'.
   * @param tabelaHorario String Representa��o hexadecimal da Tabela Hor�rio.
   * @return boolean[][]
   */
  public boolean[][] decodeTabelaHorario(String tabelaHorario) {
    // nosso resultado
    boolean[][] result = new boolean[7][24];
    // obt�m os hor�rios permitidos por dia
    for (int i=0; i<7; i++) {
      // representa��o num�rica (hexadecimal) do dia
      int intDay = Integer.decode("0x" + tabelaHorario.substring(i*6, (i*6)+6)).intValue();
      // loop nas horas do dia
      for (int w=0; w<24; w++) {
        // bit que representa a hora no dia
        int bitHour = (int)Math.pow(2, w);
        // marca o dia e a hora no array
        result[i][w] = (intDay & bitHour) == bitHour;
      } // for w
    } // for i
    // retorna
    return result;
  }

  /**
   * Retorna uma String contendo a representa��o hexadecimal de 'tabelaHorario'.
   * @param tabelaHorario boolean[][] contendo os hor�rios permitidos por
   *                      dia da semana.
   * @return String Retorna uma String contendo a representa��o hexadecimal de
   *                'tabelaHorario'.
   * @see decodeTabelaHorario()
   */
  public String encodeTabelaHorario(boolean[][] tabelaHorario) {
    // tamanho m�nimo da representa��o de cada dia
    String zeros = "0000000";
    // nosso resultado
    StringBuffer result = new StringBuffer();
    // loop no nosso array
    for (int i=0; i<7; i++) {
      // representa��o num�rica do dia
      int intDay = 0;
      // marca os bits referentes aos hor�rios marcados no dia
      for (int w=0; w<24; w++) {
        if (tabelaHorario[i][w])
          intDay += (int)Math.pow(2, w);
      } // if
      // adiciona a representa��o hexadecimal do dia � tabela hor�rio
      // adicionando os zeros necess�rios na frente
      String strDay = Integer.toHexString(intDay);
      result.append(zeros.substring(0, 6-strDay.length()) + strDay);
    } // for
    // retorna
    return result.toString();
  }

  /**
   * Insere o(a) Agenda identificado(a) por 'agendaInfo'.
   * @param agendaInfo AgendaInfo contendo as informa��es do(a) Agenda que se
   *                    deseja incluir.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public void insert(AgendaInfo agendaInfo) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(agendaInfo);
      // valor da sequ�ncia
      agendaInfo.setAgendaId(getNextSequence(FIELD_AGENDA_ID, FIELD_EMPRESA_ID.getFieldName() + " = " + agendaInfo.getEmpresaId()));
      // insere o registro
      super.insert(agendaInfo);
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
   * Retorna um AgendaInfo referente a(o) Agenda
   * indicado(a) pelos par�metros que representam sua chave prim�ria.
   * @param empresaId Empresa ID.
   * @param agendaId Agenda ID.
   * @return Retorna um AgendaInfo referente a(o) Agenda
   * indicado pelos par�metros que representam sua chave prim�ria.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public AgendaInfo selectByPrimaryKey(
                int empresaId,
                int agendaId
           ) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      PreparedStatement statement = prepareSelect(
                                                  "(" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + "=?) AND " +
                                                  "(" + FIELD_AGENDA_ID.getFieldName(getTableName()) + "=?)"
                                          );
      statement.setInt(1, empresaId);
      statement.setInt(2, agendaId);
      AgendaInfo[] result = (AgendaInfo[])select(statement);
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
   * Retorna um AgendaInfo[] contendo a lista de Agenda
   * indicados(as) pelos par�metros de pesquisa.
   * @param empresaId Empresa ID.
   * @param nome Nome.
   * @param paginate Informa��o de pagina��o dos resultados ou null.
   * @return Retorna um AgendaInfo[] contendo a lista de Agenda
   * indicados(as) pelos par�metros de pesquisa.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public AgendaInfo[] selectByFilter(int      empresaId,
                                     String   nome,
                                     Paginate paginate) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // prepara a consulta
      String[] orderFieldNames = {FIELD_NOME.getFieldName(getTableName())};
      PreparedStatement statement = prepareSelect(
                                                  orderFieldNames,
                                                  "((" + FIELD_EMPRESA_ID.getFieldName(getTableName()) + " = ?) OR (" + empresaId + "=0)) AND " +
                                                  "(" + FIELD_NOME.getFieldName(getTableName()) + " LIKE ?)",
                                                  new String[]{},
                                                  paginate
                                                 );
      statement.setInt(1, empresaId);
      statement.setString(2, nome + "%");
      // nosso resultado
      AgendaInfo[] result = (AgendaInfo[])select(statement);
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
   * Atualiza o(a) Agenda identificado(a) por 'agendaInfo'.
   * @param agendaInfo AgendaInfo contendo as informa��es do(a) Agenda que se
   *                    deseja atualizar.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de
   *                   dados.
   */
  public void update(AgendaInfo agendaInfo) throws Exception {
    // inicia transa��o
    getFacade().beginTransaction();
    try {
      // valida os dados
      validate(agendaInfo);
      // atualiza o registro
      super.update(agendaInfo);
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
   * Valida o(a) Agenda identificado(a) por 'agendaInfo'.
   * @param agendaInfo AgendaInfo contendo as informa��es do(a) Agenda que se
   *                    deseja validar.
   * @throws Exception Em caso de exce��o no preenchimento dos dados informados.
   */
  public void validate(AgendaInfo agendaInfo) throws Exception {
    if (agendaInfo.getIntervalo() < 10)
      throw new ExtendedException(getClass().getName(), "validate", "O Intervalo informado � menor que 10.");
    if (agendaInfo.getIntervalo() > 120)
      throw new ExtendedException(getClass().getName(), "validate", "O Intervalo informado � maior que 120.");
  }

}
