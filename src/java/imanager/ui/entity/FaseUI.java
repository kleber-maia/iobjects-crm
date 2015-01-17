package imanager.ui.entity;

import iobjects.*;
import iobjects.entity.*;
import iobjects.ui.*;
import iobjects.ui.entity.*;

import imanager.entity.*;
import imanager.misc.*;

/**
 * Utilitário para criação de controles para a classe Fase.
 */
public class FaseUI {

  /**
   * Retorna a representação HTML de um LookupList contendo valores da
   * entidade Fase.
   * @param facade Fachada.
   * @param param Param cujas propriedades irão gerar o controle de seleção.
   * @param width int Largura do controle de seleção na página ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formatação do ParamFormSelect.
   * @param onChangeScript Código JavaScript para ser executado quando o usuário
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representação HTML de um LookupList contendo valores
   *         da entidade Fase.
   * @throws java.lang.Exception
   */
  static public String lookupListForParam(Facade facade,
                                          Param  param,
                                          int    width,
                                          String style,
                                          String onChangeScript) throws Exception {
    // retorna
    return LookupList.script(facade,
                             Fase.CLASS_NAME,
                             "crm_fase",
                             new String[]{Fase.FIELD_NOME.getFieldName()},
                             new String[]{Fase.FIELD_FASE_ID.getFieldName()},
                             new String[]{param.getIntValue() + ""},
                             new String[]{Fase.FIELD_PERCENTUAL_SUCESSO.getFieldName(), Fase.FIELD_NOME.getFieldName()},
                             "",
                             LookupList.SELECT_TYPE_SINGLE,
                             param.getName(),
                             param.getScriptConstraint(),
                             param.getScriptConstraintErrorMessage(),
                             "width:" + (width > 0 ? width + "px;" : "100%;") + style,
                             onChangeScript,
                             true);
  }

  /**
   * Retorna a representação JavaScript de um LookupSearch contendo valores da
   * entidade Fase.
   * @param facade Fachada.
   * @param entityLookup EntityLookup cujas propriedades irão gerar o controle de seleção.
   * @param entityInfo EntityInfo contendo os valores do registro que está sendo
   *                   editado/inserido.
   * @param width int Largura do controle de seleção na página ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formatação do ExternalLookup.
   * @param onChangeScript Código JavaScript para ser executado quando o usuário
   *                       alterar o valor do elemento HTML.
   * @param readOnly True para apenas leitura.
   * @return Retorna a representação JavaScript de um LookupSearch contendo valores da
   * entidade Fase.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de dados.
   */
  static public String lookupListForLookup(Facade       facade,
                                           EntityLookup entityLookup,
                                           EntityInfo   entityInfo,
                                           int          width,
                                           String       style,
                                           String       onChangeScript) throws Exception {
    // obtém Campanha  ID
    Integer faseId = (Integer)entityInfo.getPropertyValue(entityLookup.getKeyFields()[0].getFieldAlias());
    // retorna
    return LookupList.script(facade,
                             Fase.CLASS_NAME,
                             "crm_fase",
                             new String[]{Fase.FIELD_NOME.getFieldName()},
                             new String[]{Fase.FIELD_FASE_ID.getFieldName()},
                             new String[]{faseId + ""},
                             new String[]{Fase.FIELD_PERCENTUAL_SUCESSO.getFieldName(), Fase.FIELD_NOME.getFieldName()},
                             "",
                             LookupList.SELECT_TYPE_SINGLE,
                             entityLookup.getKeyFields()[0].getFieldAlias(),
                             entityLookup.getKeyFields()[0].getScriptConstraint(),
                             entityLookup.getKeyFields()[0].getScriptConstraintErrorMessage(),
                             (width > 0 ? "width:" + width + "px;" : "width:100%;") + style,
                             onChangeScript,
                             true);
    }

  private FaseUI() {
  }

}
