     
<%@include file="../include/beans.jsp"%>

<%@page import="imanager.misc.*"%>
<%@page import="imanager.report.*"%>
<%@page import="imanager.ui.entity.*"%>

<%
  // in�cio do bloco protegido
  try {
    // nossa inst�ncia de RelatorioRankingOportunidadeUsuario
    RelatorioRankingOportunidadeUsuario relatorioRankingOportunidadeUsuario = (RelatorioRankingOportunidadeUsuario)facade.getReport(RelatorioRankingOportunidadeUsuario.CLASS_NAME);

    // define os par�metros, caso sejamos chamados por outro objeto
    relatorioRankingOportunidadeUsuario.userParamList().setParamsValues(request);

    // nosso Form de relat�rio
    Form formReport = new Form(request, "formReportRelatorioRankingOportunidadeUsuario", RelatorioRankingOportunidadeUsuario.ACTION_RELATORIO, RelatorioRankingOportunidadeUsuario.COMMAND_MAKE_REPORT, "frameReport", true);
    // nosso FrameBar
    FrameBar frameBar = new FrameBar(facade, "frameBarRelatorioRankingOportunidadeUsuario");
%>

<html>
  <head>
    <title><%=RelatorioRankingOportunidadeUsuario.ACTION.getCaption()%></title>
    <link href="<%=facade.getStyle().userInterface()%>" rel="stylesheet" type="text/css">
    <script src="include/scripts.js" type="text/javascript"></script>
  </head>
  <body style="margin:0px;" onselectstart="return true;" oncontextmenu="return false;">

    <!-- inicia o FrameBar -->
    <%=frameBar.begin()%>

      <!-- �rea de frames -->
      <%=frameBar.beginFrameArea()%>

        <!-- Frame de identifica��o do objeto -->
        <%=frameBar.actionFrame(RelatorioRankingOportunidadeUsuario.ACTION)%>

        <!-- Frame de par�metros -->
        <%=frameBar.beginFrame("Par�metros", false, true)%>
          <!-- Form de relat�rio -->
          <%=formReport.begin()%>
            <table style="width:100%;">
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_DATA_INICIAL)%></td>
              </tr>
              <tr>
                <td><%=ParamFormEdit.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_DATA_INICIAL, 0, "", "")%></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_DATA_FINAL)%></td>
              </tr>
              <tr>
                <td><%=ParamFormEdit.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_DATA_FINAL, 0, "", "")%></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_CAMPANHA_ID)%></td>
              </tr>
              <tr>
                <td><%=CampanhaUI.lookupListForParam(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_CAMPANHA_ID, NaoSim.NAO, 0, "", "")%></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_DEPARTAMENTO_ID)%></td>
              </tr>
              <tr>
                <td><%=DepartamentoUI.lookupListForParam(facade, selectedEmpresaId, relatorioRankingOportunidadeUsuario.USER_PARAM_DEPARTAMENTO_ID, 0, "", "") %></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_FASE_ID)%></td>
              </tr>
              <tr>
                <td><%=FaseUI.lookupListForParam(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_FASE_ID, 0, "", "") %></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_STATUS)%></td>
              </tr>
              <tr>
                <td><%=ParamFormSelect.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_STATUS, 0, "", "")%></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_CONSOLIDADO)%></td>
              </tr>
              <tr>
                <td><%=ParamFormSelect.script(facade, relatorioRankingOportunidadeUsuario.USER_PARAM_CONSOLIDADO, 0, "", "")%></td>
              </tr>
              <tr>
                <td><%=CommandControl.formButton(facade, formReport, ImageList.COMMAND_PRINT, "", false, false)%></td>
              </tr>
            </table>
          <%=formReport.end()%>
        <%=frameBar.endFrame()%>

        <!-- Frame de comandos -->
        <%=frameBar.beginFrame("Comandos", false, true)%>
          <script type="text/javascript">
            function executeCommand(command) {
              frameReport.document.execCommand(command);
            }
          </script>
          <table style="width:100%;">
            <tr>
              <td><%=CommandControl.actionCustomScriptHyperlink(facade, RelatorioRankingOportunidadeUsuario.ACTION, RelatorioRankingOportunidadeUsuario.COMMAND_PRINT, ImageList.COMMAND_PRINT, "javascript:executeCommand('PRINT');", "", "", false)%></td>
            </tr>
            <tr>
              <td><%=CommandControl.actionCustomScriptHyperlink(facade, RelatorioRankingOportunidadeUsuario.ACTION, RelatorioRankingOportunidadeUsuario.COMMAND_COPY, ImageList.COMMAND_COPY, "javascript:executeCommand('SELECTALL');executeCommand('COPY');", "", "", false)%></td>
            </tr>
          </table>
        <%=frameBar.endFrame()%>

      <%=frameBar.endFrameArea()%>

      <!-- �rea do relat�rio -->
      <%=frameBar.beginClientArea()%>
        <iframe name="frameReport" id="frameReport" src="<%=RelatorioRankingOportunidadeUsuario.ACTION_RELATORIO.url()%>" frameborder="false" style="width:100%; height:100%;" />
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
