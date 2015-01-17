     
<%@include file="../include/beans.jsp"%>

<%@page import="imanager.entity.*"%>

<%!
  public DepartamentoInfo[] search(Departamento departamento, int empresaId, StringBuffer searchErrorMessage) {
    try {
      // resultado da pesquisa...
      return departamento.selectByFilter(empresaId);
    }
    catch (Exception e) {
      searchErrorMessage.append(ExtendedException.extractMessage(e));
      return new DepartamentoInfo[0];
    } // try-catch
  }
%>

<%
  // in�cio do bloco protegido
  try {
    // nossa inst�ncia de Departamento
    Departamento departamento = (Departamento)facade.getEntity(Departamento.CLASS_NAME);
    // nossa inst�ncia de Empresa
    Empresa empresa = (Empresa)facade.getEntity(Empresa.CLASS_NAME);

    // se a Empresa � apenas um Estoque...exce��o
    if (empresa.isEstoque(selectedEmpresaId))
      throw new Exception("Opera��o n�o permitida em uma Empresa que � apenas estoque.");

    // lista que iremos exibir
    DepartamentoInfo[] departamentoInfoList = new DepartamentoInfo[0];
    // mensagem de erro na pesquisa
    StringBuffer searchErrorMessage = new StringBuffer();

    // se estamos excluindo...
    if (Controller.isDeleting(request)) {
      // registros selecionados
      DepartamentoInfo[] selectedInfoList = (DepartamentoInfo[])EntityGrid.selectedInfoList(departamento, request);
      // comp�e a resposta
      Form.composeAjaxResponse(response, Form.COMPOSE_TYPE_JAVA_SCRIPT);
      // nosso Grid
      EntityGrid entityGrid = EntityGrid.getInstance(facade, departamento);
      // exclui e apaga do Grid
      for (int i=0; i<selectedInfoList.length; i++) {
        departamento.delete(selectedInfoList[i]);%>
        <%=entityGrid.deleteRow(selectedInfoList[i])%>
      <%} // for
      // dispara
      return;
    }
    // se estamos pesquisando...
    else if (Controller.isSearching(request)) {
      // define os valores dos par�metros de usu�rio
      departamento.userParamList().setParamsValues(request);
      // comp�e a resposta
      Form.composeAjaxResponse(response, Form.COMPOSE_TYPE_JAVA_SCRIPT);
      // atualiza tudo
      departamentoInfoList = search(departamento, selectedEmpresaId, searchErrorMessage);
      %><%=EntityGrid.getInstance(facade, departamento).update(departamentoInfoList, "", searchErrorMessage.toString())%><%
      // dispara
      return;
    } // if

    // define os valores dos par�metros de usu�rio
    departamento.userParamList().setParamsValues(request);
    // realiza a pesquisa
    departamentoInfoList = search(departamento, selectedEmpresaId, searchErrorMessage);

    // nosso Form de pesquisa
    Form formSearch = new Form(request, "formSearchDepartamento", Departamento.ACTION, Departamento.COMMAND_SEARCH, "", true, true);
    // nosso Form de exclus�o
    Form formDelete = new Form(request, "formDeleteDepartamento", Departamento.ACTION, Departamento.COMMAND_DELETE, "", false, true, true);
    // nosso FrameBar
    FrameBar frameBar = new FrameBar(facade, "frameBarDepartamento");
    // nosso Grid
    EntityGrid entityGrid = EntityGrid.getInstance(facade, departamento, 0, 0);
    entityGrid.addColumn(Departamento.FIELD_NOME, 500, Departamento.ACTION_CADASTRO, Departamento.COMMAND_EDIT, "", true);
%>

<html>
  <head>
    <title><%=Departamento.ACTION.getCaption()%></title>
    <link href="<%=facade.getStyle().userInterface()%>" rel="stylesheet" type="text/css">
    <script src="include/scripts.js" type="text/javascript"></script>
  </head>
  <body style="margin:0px;" onselectstart="return true;" oncontextmenu="return false;">

    <!-- inicia o FrameBar -->
    <%=frameBar.begin()%>

      <!-- �rea de frames -->
      <%=frameBar.beginFrameArea()%>

        <!-- Frame de identifica��o do objeto -->
        <%=frameBar.actionFrame(Departamento.ACTION)%>

        <!-- Frame de comandos -->
        <%=frameBar.beginFrame("Comandos", false, false)%>
          <table style="width:100%;">
            <tr>
              <td><%=CommandControl.entityFormHyperlink(facade, departamento, Departamento.ACTION_CADASTRO, Departamento.COMMAND_INSERT, ImageList.COMMAND_INSERT, false)%></td>
            </tr>
            <tr>
              <td><%=CommandControl.formHyperlink(facade, formDelete, ImageList.COMMAND_DELETE, "Deseja mesmo excluir o(s) registro(s) selecionado(s)?", true, false)%></td>
            </tr>
          </table>
        <%=frameBar.endFrame()%>

      <%=frameBar.endFrameArea()%>

      <!-- �rea do Grid -->
      <%=frameBar.beginClientArea()%>
        <!-- Form de exclus�o -->
        <%=formDelete.begin()%>

          <!-- Grid -->
          <%=entityGrid.script(departamentoInfoList, "", searchErrorMessage.toString())%>

        <%=formDelete.end()%>
      <%=frameBar.endClientArea()%>

    <%=frameBar.end()%>


  </body>
</html>

<%}
  // t�rmino do bloco protegido
  catch (Exception e) {
    Controller.forwardException(e, pageContext);
  } // try-catch
%>
