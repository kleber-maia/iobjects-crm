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
