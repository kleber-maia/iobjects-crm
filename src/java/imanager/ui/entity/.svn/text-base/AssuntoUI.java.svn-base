package imanager.ui.entity;

import iobjects.*;
import iobjects.entity.EntityField;
import iobjects.ui.FormSelectEx;
import iobjects.ui.entity.*;

import imanager.entity.*;

/**
 * Utilitário para criação de controles para a classe Assunto.
 */
public class AssuntoUI {

  /**
   * Retorna a representação HTML de um LookupSelectEx contendo valores da
   * entidade Assunto.
   * @param facade Fachada.
   * @param param Param cujas propriedades irão gerar o controle de seleção.
   * @param width int Largura do controle de seleção na página ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formatação do ParamFormSelect.
   * @param onChangeScript Código JavaScript para ser executado quando o usuário
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representação HTML de um LookupSelectEx contendo valores
   *         da entidade Assunto.
   * @throws java.lang.Exception
   */
  static public String lookupSelectExForParam(Facade facade,
                                              Param  param,
                                              int    width,
                                              String style,
                                              String onChangeScript) throws Exception {
    // retorna
    return LookupSelectEx.script(facade,
                                 param.getName(),
                                 Assunto.CLASS_NAME,
                                 new EntityField[]{Assunto.FIELD_NOME},
                                 Assunto.LEVEL_DELIMITER,
                                 new EntityField[]{Assunto.FIELD_ASSUNTO_ID},
                                 new String[]{param.getIntValue() + ""},
                                 new EntityField[]{Assunto.FIELD_NOME},
                                 "",
                                 width,
                                 param.getScriptConstraint(),
                                 param.getScriptConstraintErrorMessage(),
                                 style,
                                 onChangeScript,
                                 false,
                                 true);
  }

}
