package imanager.ui.entity;

import iobjects.*;
import iobjects.entity.*;
import iobjects.ui.entity.*;

import imanager.entity.*;

/**
 * Utilit�rio para cria��o de controles para a classe Agenda.
 */
public class AgendaUI {

  /**
   * Retorna a representa��o HTML de um LookupList contendo valores da
   * entidade Agenda.
   * @param facade Fachada.
   * @param param Param cujas propriedades ir�o gerar o controle de sele��o.
   * @param empresaId Empresa ID.
   * @param width int Largura do controle de sele��o na p�gina ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formata��o do ParamFormSelect.
   * @param onChangeScript C�digo JavaScript para ser executado quando o usu�rio
   *                       alterar o valor do elemento HTML.
   * @param includeBlank true para poder pesquisar com o campo vazio.
   * @return Retorna a representa��o HTML de um LookupList contendo valores
   *         da entidade Empresa.
   * @throws java.lang.Exception
   */
  static public String lookupListForParam(Facade facade,
                                          int    empresaId,
                                          Param  param,
                                          int    width,
                                          String style,
                                          String onChangeScript,
                                          boolean includeBlank) throws Exception {
    // retorna
    return LookupList.script(facade,
                             Agenda.CLASS_NAME,
                             "relacionamento_agenda",
                             new String[]{Agenda.FIELD_NOME.getFieldName()},
                             new String[]{Agenda.FIELD_AGENDA_ID.getFieldName()},
                             new String[]{param.getIntValue() + ""},
                             new String[]{Agenda.FIELD_NOME.getFieldName()},
                             Agenda.FIELD_EMPRESA_ID.getFieldName() + "=" + empresaId,
                             LookupList.SELECT_TYPE_SINGLE,
                             param.getName(),
                             param.getScriptConstraint(),
                             param.getScriptConstraintErrorMessage(),
                             "width:" + (width > 0 ? width + "px;" : "100%;") + style,
                             onChangeScript,
                             includeBlank);
  }

  /**
   * Retorna a representa��o JavaScript de um LookupList contendo valores da
   * entidade Agenda.
   * @param facade Fachada.
   * @param entityLookup EntityLookup cujas propriedades ir�o gerar o controle de sele��o.
   * @param entityInfo EntityInfo contendo os valores do registro que est� sendo
   *                   editado/inserido.
   * @param empresaId Empreda ID.
   * @param width int Largura do controle de sele��o na p�gina ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formata��o do ParamFormSelect.
   * @param onChangeScript C�digo JavaScript para ser executado quando o usu�rio
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representa��o JavaScript de um LookupList contendo
   *         valores da entidade Empresa.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de dados.
   */
  static public String lookupListForLookup(Facade       facade,
                                           EntityLookup entityLookup,
                                           EntityInfo   entityInfo,
                                           int          empresaId,
                                           int          width,
                                           String       style,
                                           String       onChangeScript,
                                           boolean      readOnly) throws Exception {
    // obt�m Agenda ID
    Integer agendaId = (Integer)entityInfo.getPropertyValue(entityLookup.getKeyFields()[0].getFieldAlias());
    // retorna
    return LookupSearch.script(facade,
                             entityLookup.getKeyFields()[0].getFieldAlias(),
                             Agenda.CLASS_NAME,
                             new EntityField[]{},
                             Agenda.FIELD_NOME,
                             "",
                             Agenda.FIELD_EMPRESA_ID.getFieldName() + "=" + empresaId,
                             Agenda.FIELD_AGENDA_ID,
                             (agendaId > 0 ? agendaId + "" : ""),
                             entityLookup.getKeyFields()[0].getScriptConstraint(),
                             entityLookup.getKeyFields()[0].getScriptConstraintErrorMessage(),
                             width,
                             style,
                             onChangeScript,
                             readOnly);
  }

  private AgendaUI() {
  }

}
