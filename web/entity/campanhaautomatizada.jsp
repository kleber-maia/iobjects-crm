
<%--
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
--%>
     
<%@include file="../include/beans.jsp"%>

<%@page import="imanager.entity.*"%>

<%!
  public CampanhaAutomatizadaInfo[] search(CampanhaAutomatizada campanhaautomatizada, StringBuffer searchErrorMessage) {
    try {
      // resultado da pesquisa...
      return campanhaautomatizada.selectByFilter(
              NumberTools.parseInt(campanhaautomatizada.USER_PARAM_CAMPANHA_ID.getValue()),
              NumberTools.parseInt(campanhaautomatizada.USER_PARAM_CAMPANHA_AUTOMATIZADA_ID.getValue()),
              null);
    }
    catch (Exception e) {
      searchErrorMessage.append(ExtendedException.extractMessage(e));
      return new CampanhaAutomatizadaInfo[0];
    } // try-catch
  }
%>

<%
  // início do bloco protegido
  try {
    // nossa instância de CampanhaAutomatizada
    CampanhaAutomatizada campanhaautomatizada = (CampanhaAutomatizada)facade.getEntity(CampanhaAutomatizada.CLASS_NAME);

    // lista que iremos exibir
    CampanhaAutomatizadaInfo[] campanhaautomatizadaInfoList = new CampanhaAutomatizadaInfo[0];
    // mensagem de erro na pesquisa
    StringBuffer searchErrorMessage = new StringBuffer();

    // se estamos excluindo...
    if (Controller.isDeleting(request)) {
      // registros selecionados
      CampanhaAutomatizadaInfo[] selectedInfoList = (CampanhaAutomatizadaInfo[])EntityGrid.selectedInfoList(campanhaautomatizada, request);
      // compõe a resposta
      Form.composeAjaxResponse(response, Form.COMPOSE_TYPE_JAVA_SCRIPT);
      // nosso Grid
      EntityGrid entityGrid = EntityGrid.getInstance(facade, campanhaautomatizada);
      // exclui e apaga do Grid
      for (int i=0; i<selectedInfoList.length; i++) {
        campanhaautomatizada.delete(selectedInfoList[i]);%>
        <%=entityGrid.deleteRow(selectedInfoList[i])%>
      <%} // for
      // dispara
      return;
    }
    // se estamos pesquisando...
    else if (Controller.isSearching(request)) {
      // define os valores dos parâmetros de usuário
      campanhaautomatizada.userParamList().setParamsValues(request);
      // compõe a resposta
      Form.composeAjaxResponse(response, Form.COMPOSE_TYPE_JAVA_SCRIPT);
      // atualiza tudo
      campanhaautomatizadaInfoList = search(campanhaautomatizada, searchErrorMessage);
      %><%=EntityGrid.getInstance(facade, campanhaautomatizada).update(campanhaautomatizadaInfoList, "", searchErrorMessage.toString())%><%
      // dispara
      return;
    } // if

    // define os valores dos parâmetros de usuário
    campanhaautomatizada.userParamList().setParamsValues(request);
    // realiza a pesquisa
    campanhaautomatizadaInfoList = search(campanhaautomatizada, searchErrorMessage);

    // nosso Form de exclusão
    Form formDelete = new Form(request, "formDeleteCampanhaAutomatizada", Campanha.ACTION, Campanha.COMMAND_DELETE, "", false, true, true);
    // nosso FrameBar
    FrameBar frameBar = new FrameBar(facade, "frameBarCampanhaAutomatizada");
    // nosso Grid
    EntityGrid entityGrid = EntityGrid.getInstance(facade, campanhaautomatizada, 0, 0);
    entityGrid.addColumn(CampanhaAutomatizada.FIELD_ASSUNTO, 650, CampanhaAutomatizada.ACTION_CADASTRO, CampanhaAutomatizada.COMMAND_EDIT, "", true);
    entityGrid.addColumn(CampanhaAutomatizada.FIELD_DIAS_ENVIO, 80);
  
%>

<html>
  <head>
    <title><%=CampanhaAutomatizada.ACTION.getCaption()%></title>
    <link href="<%=facade.getStyle().userInterface()%>" rel="stylesheet" type="text/css">
    <script src="include/scripts.js" type="text/javascript"></script>
  </head>
  <body style="margin:0px;" onselectstart="return true;" oncontextmenu="return true;">
    <!-- Form de exclusão -->
    <%=formDelete.begin()%>
      <table style="width:100%; height:100%;">
        <tr>
          <td style="height:auto;">
            <%=entityGrid.script(campanhaautomatizadaInfoList, "", searchErrorMessage.toString())%>
          </td>
        </tr>
        <tr>
          <td style="height:20px;">
            <%=CommandControl.entityFormButton(facade, campanhaautomatizada, CampanhaAutomatizada.ACTION_CADASTRO, CampanhaAutomatizada.COMMAND_INSERT, ImageList.COMMAND_INSERT, false)%>&nbsp;
            <%=CommandControl.formButton(facade, formDelete, ImageList.COMMAND_DELETE, "Deseja mesmo excluir o(s) registro(s) selecionado(s)?", true, false)%>
          </td>
        </tr>
      </table>
    <%=formDelete.end()%>
  </body>
</html>

<%}
  // término do bloco protegido
  catch (Exception e) {
    Controller.forwardException(e, pageContext);
  } // try-catch
%>
