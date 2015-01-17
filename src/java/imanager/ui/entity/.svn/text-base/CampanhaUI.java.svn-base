package imanager.ui.entity;

import iobjects.*;
import iobjects.entity.*;
import iobjects.ui.*;
import iobjects.ui.entity.*;

import imanager.entity.*;
import imanager.misc.*;

/**
 * Utilitário para criação de controles para a classe Campanha.
 */
public class CampanhaUI {

  /**
   * Retorna a representação HTML de um LookupList contendo valores da
   * entidade Campanha.
   * @param facade Fachada.
   * @param param Param cujas propriedades irão gerar o controle de seleção.
   * @param arquivo Arquivo.
   * @param width int Largura do controle de seleção na página ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formatação do ParamFormSelect.
   * @param onChangeScript Código JavaScript para ser executado quando o usuário
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representação HTML de um LookupList contendo valores
   *         da entidade Campanha.
   * @throws java.lang.Exception
   */
  static public String lookupListForParam(Facade facade,
                                          Param  param,
                                          int    arquivo,
                                          int    width,
                                          String style,
                                          String onChangeScript) throws Exception {
    // retorna
    return LookupList.script(facade,
                             Campanha.CLASS_NAME,
                             "crm_campanha",
                             new String[]{Campanha.FIELD_NOME.getFieldName()},
                             new String[]{Campanha.FIELD_CAMPANHA_ID.getFieldName()},
                             new String[]{param.getIntValue() + ""},
                             new String[]{Campanha.FIELD_NOME.getFieldName()},
                             Campanha.FIELD_ARQUIVO.getFieldName() + "=" + arquivo,
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
   * entidade Campanha.
   * @param facade Fachada.
   * @param entityLookup EntityLookup cujas propriedades irão gerar o controle de seleção.
   * @param entityInfo EntityInfo contendo os valores do registro que está sendo
   *                   editado/inserido.
   * @param arquivo Arquivo.
   * @param width int Largura do controle de seleção na página ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formatação do ExternalLookup.
   * @param onChangeScript Código JavaScript para ser executado quando o usuário
   *                       alterar o valor do elemento HTML.
   * @param readOnly True para apenas leitura.
   * @return Retorna a representação JavaScript de um LookupSearch contendo valores da
   * entidade Campanha.
   * @throws Exception Em caso de exceção na tentativa de acesso ao banco de dados.
   */
  static public String lookupListForLookup(Facade       facade,
                                           EntityLookup entityLookup,
                                           EntityInfo   entityInfo,
                                           int          arquivo,
                                           int          width,
                                           String       style,
                                           String       onChangeScript) throws Exception {
    // obtém Campanha  ID
    Integer campanhaId = (Integer)entityInfo.getPropertyValue(entityLookup.getKeyFields()[0].getFieldAlias());
    // retorna
    return LookupList.script(facade,
                             Campanha.CLASS_NAME,
                             "crm_campanha",
                             new String[]{Campanha.FIELD_NOME.getFieldName()},
                             new String[]{Campanha.FIELD_CAMPANHA_ID.getFieldName()},
                             new String[]{campanhaId + ""},
                             new String[]{Campanha.FIELD_NOME.getFieldName()},
                             "(" + Campanha.FIELD_ARQUIVO.getFieldName() + " = " + arquivo + ")",
                             LookupList.SELECT_TYPE_SINGLE,
                             entityLookup.getKeyFields()[0].getFieldAlias(),
                             entityLookup.getKeyFields()[0].getScriptConstraint(),
                             entityLookup.getKeyFields()[0].getScriptConstraintErrorMessage(),
                             (width > 0 ? "width:" + width + "px;" : "width:100%;") + style,
                             onChangeScript,
                             true);
    }

  private CampanhaUI() {
  }

}
