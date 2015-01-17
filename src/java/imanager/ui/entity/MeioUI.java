package imanager.ui.entity;

import iobjects.*;
import iobjects.ui.entity.*;

import imanager.entity.*;

/**
 * Utilit�rio para cria��o de controles para a classe Meio.
 */
public class MeioUI {

  /**
   * Retorna a representa��o HTML de um LookupList contendo valores da
   * entidade Meio.
   * @param facade Fachada.
   * @param param Param cujas propriedades ir�o gerar o controle de sele��o.
   * @param width int Largura do controle de sele��o na p�gina ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formata��o do ParamFormSelect.
   * @param onChangeScript C�digo JavaScript para ser executado quando o usu�rio
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representa��o HTML de um LookupList contendo valores
   *         da entidade Meio.
   * @throws java.lang.Exception
   */
  static public String lookupListForParam(Facade facade,
                                          Param  param,
                                          int    width,
                                          String style,
                                          String onChangeScript) throws Exception {
    // retorna
    return LookupList.script(facade,
                             Meio.CLASS_NAME,
                             "crm_meio",
                             new String[]{Meio.FIELD_NOME.getFieldName()},
                             new String[]{Meio.FIELD_MEIO_ID.getFieldName()},
                             new String[]{param.getIntValue() + ""},
                             new String[]{Meio.FIELD_NOME.getFieldName()},
                             "",
                             LookupList.SELECT_TYPE_SINGLE,
                             param.getName(),
                             param.getScriptConstraint(),
                             param.getScriptConstraintErrorMessage(),
                             "width:" + (width > 0 ? width + "px;" : "100%;") + style,
                             onChangeScript,
                             true);
  }

  private MeioUI() {
  }

}
