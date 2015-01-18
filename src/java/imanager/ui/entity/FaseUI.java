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
import iobjects.entity.*;
import iobjects.ui.*;
import iobjects.ui.entity.*;

import imanager.entity.*;
import imanager.misc.*;

/**
 * Utilit�rio para cria��o de controles para a classe Fase.
 */
public class FaseUI {

  /**
   * Retorna a representa��o HTML de um LookupList contendo valores da
   * entidade Fase.
   * @param facade Fachada.
   * @param param Param cujas propriedades ir�o gerar o controle de sele��o.
   * @param width int Largura do controle de sele��o na p�gina ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formata��o do ParamFormSelect.
   * @param onChangeScript C�digo JavaScript para ser executado quando o usu�rio
   *                       alterar o valor do elemento HTML.
   * @return Retorna a representa��o HTML de um LookupList contendo valores
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
   * Retorna a representa��o JavaScript de um LookupSearch contendo valores da
   * entidade Fase.
   * @param facade Fachada.
   * @param entityLookup EntityLookup cujas propriedades ir�o gerar o controle de sele��o.
   * @param entityInfo EntityInfo contendo os valores do registro que est� sendo
   *                   editado/inserido.
   * @param width int Largura do controle de sele��o na p�gina ou 0 (zero) para
   *              que ele se ajuste automaticamente ao seu conteiner.
   * @param style String Estilo de formata��o do ExternalLookup.
   * @param onChangeScript C�digo JavaScript para ser executado quando o usu�rio
   *                       alterar o valor do elemento HTML.
   * @param readOnly True para apenas leitura.
   * @return Retorna a representa��o JavaScript de um LookupSearch contendo valores da
   * entidade Fase.
   * @throws Exception Em caso de exce��o na tentativa de acesso ao banco de dados.
   */
  static public String lookupListForLookup(Facade       facade,
                                           EntityLookup entityLookup,
                                           EntityInfo   entityInfo,
                                           int          width,
                                           String       style,
                                           String       onChangeScript) throws Exception {
    // obt�m Campanha  ID
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
