package imanager.ui.entity;

import iobjects.*;
import iobjects.entity.*;
import iobjects.ui.entity.*;

import imanager.entity.*;
import imanager.misc.*;

/**
 * Utilitário para criação de controles para a classe Empresa.
 */
public class EmpresaUI {

  /**
   * Retorna a representação HTML de um LookupList contendo valores da
   * entidade Empresa.
   * @param facade Fachada.
   * @param param Param cujas propriedades irão gerar o controle de seleção.
   * @param empresaId Empresa ID.
   * @param width int Largura do controle de seleção na página ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formatação do ParamFormSelect.
   * @param onChangeScript Código JavaScript para ser executado quando o usuário
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representação HTML de um LookupList contendo valores
   *         da entidade Empresa.
   * @throws java.lang.Exception
   */
  static public String lookupListForParam(Facade facade,
                                          Param  param,
                                          int    estoque,
                                          int    width,
                                          String style,
                                          String onChangeScript,
                                          boolean includeBlank) throws Exception {
    // retorna
    return LookupList.script(facade,
                             Empresa.CLASS_NAME,
                             "global_empresa",
                             new String[]{Empresa.FIELD_NOME.getFieldName()},
                             new String[]{Empresa.FIELD_EMPRESA_ID.getFieldName()},
                             new String[]{param.getIntValue() + ""},
                             new String[]{Empresa.FIELD_NOME.getFieldName()},
                             Empresa.FIELD_ESTOQUE.getFieldName() + "=" + estoque,
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
   * entidade Empresa.
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
                                           String       onChangeScript) throws Exception {
    // retorna
    return LookupList.script(facade,
                             Empresa.CLASS_NAME,
                             "global_empresa",
                             new String[]{Empresa.FIELD_NOME.getFieldName()},
                             new String[]{Empresa.FIELD_EMPRESA_ID.getFieldName()},
                             new String[]{empresaId + ""},
                             new String[]{Empresa.FIELD_NOME.getFieldName()},
                             "(" + Empresa.FIELD_EMPRESA_ID.getFieldName() + " = " + empresaId + ")",
                             LookupList.SELECT_TYPE_SINGLE,
                             entityLookup.getKeyFields()[1].getFieldAlias(),
                             entityLookup.getKeyFields()[1].getScriptConstraint(),
                             entityLookup.getKeyFields()[1].getScriptConstraintErrorMessage(),
                             (width > 0 ? "width:" + width + "px;" : "width:100%;") + style,
                             onChangeScript,
                             true);
  }

  private EmpresaUI() {
  }

}
