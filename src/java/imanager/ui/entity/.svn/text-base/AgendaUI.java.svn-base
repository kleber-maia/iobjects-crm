package imanager.ui.entity;

import iobjects.*;
import iobjects.entity.*;
import iobjects.ui.entity.*;

import imanager.entity.*;

/**
 * Utilitário para criação de controles para a classe Agenda.
 */
public class AgendaUI {

  /**
   * Retorna a representação HTML de um LookupList contendo valores da
   * entidade Agenda.
   * @param facade Fachada.
   * @param param Param cujas propriedades irão gerar o controle de seleção.
   * @param empresaId Empresa ID.
   * @param width int Largura do controle de seleção na página ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formatação do ParamFormSelect.
   * @param onChangeScript Código JavaScript para ser executado quando o usuário
   *                       alterar o valor do elemento HTML.
   * @param includeBlank true para poder pesquisar com o campo vazio.
   * @return Retorna a representação HTML de um LookupList contendo valores
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
   * Retorna a representação JavaScript de um LookupList contendo valores da
   * entidade Agenda.
   * @param facade Fachada.
   * @param entityLookup EntityLookup cujas propriedades irão gerar o controle de seleção.
   * @param entityInfo EntityInfo contendo os valores do registro que está sendo
   *                   editado/inserido.
   * @param empresaId Empreda ID.
   * @param width int Largura do controle de seleção na página ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formatação do ParamFormSelect.
   * @param onChangeScript Código JavaScript para ser executado quando o usuário
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representação JavaScript de um LookupList contendo
   *         valores da entidade Empresa.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de dados.
   */
  static public String lookupListForLookup(Facade       facade,
                                           EntityLookup entityLookup,
                                           EntityInfo   entityInfo,
                                           int          empresaId,
                                           int          width,
                                           String       style,
                                           String       onChangeScript,
                                           boolean      readOnly) throws Exception {
    // obtém Agenda ID
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
