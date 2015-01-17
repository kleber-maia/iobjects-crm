     
<%@page import="imanager.ui.entity.AgendaUI"%>
<%@include file="../include/beans.jsp"%>

<%@page import="imanager.report.*"%>

<%
  // in�cio do bloco protegido
  try {
    // nossa inst�ncia de RelatorioAgendaSemanal
    RelatorioAgendaSemanal relatorioAgendaSemanal = (RelatorioAgendaSemanal)facade.getReport(RelatorioAgendaSemanal.CLASS_NAME);

    // define os par�metros, caso sejamos chamados por outro objeto
    relatorioAgendaSemanal.userParamList().setParamsValues(request);

    // nosso Form de relat�rio
    Form formReport = new Form(request, "formReportRelatorioAgendaSemanal", RelatorioAgendaSemanal.ACTION_RELATORIO, RelatorioAgendaSemanal.COMMAND_MAKE_REPORT, "frameReport", true);
    // nosso FrameBar
    FrameBar frameBar = new FrameBar(facade, "frameBarRelatorioAgendaSemanal");
%>

<html>
  <head>
    <title><%=RelatorioAgendaSemanal.ACTION.getCaption()%></title>
    <link href="<%=facade.getStyle().userInterface()%>" rel="stylesheet" type="text/css">
    <script src="include/scripts.js" type="text/javascript"></script>
  </head>
  <body style="margin:0px;" onselectstart="return true;" oncontextmenu="return false;">

    <!-- inicia o FrameBar -->
    <%=frameBar.begin()%>

      <!-- �rea de frames -->
      <%=frameBar.beginFrameArea()%>

        <!-- Frame de identifica��o do objeto -->
        <%=frameBar.actionFrame(RelatorioAgendaSemanal.ACTION)%>

        <!-- Frame de par�metros -->
        <%=frameBar.beginFrame("Par�metros", false, true)%>
          <!-- Form de relat�rio -->
          <%=formReport.begin()%>
            <table style="width:100%;">
              <tr>
                <td><%=ParamLabel.script(facade, relatorioAgendaSemanal.USER_PARAM_DATA)%></td>
              </tr>
              <tr>
                <td><%=ParamFormEdit.script(facade, relatorioAgendaSemanal.USER_PARAM_DATA, 0, "", "")%></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioAgendaSemanal.USER_PARAM_AGENDA)%></td>
              </tr>
              <tr>
                <td><%=AgendaUI.lookupListForParam(facade, selectedEmpresaId, relatorioAgendaSemanal.USER_PARAM_AGENDA, 0, "", "", true)%></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioAgendaSemanal.USER_PARAM_CONSOLIDADO)%></td>
              </tr>
              <tr>
                <td><%=ParamFormSelect.script(facade, relatorioAgendaSemanal.USER_PARAM_CONSOLIDADO, 0, "", "")%></td>
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
              <td><%=CommandControl.actionCustomScriptHyperlink(facade, RelatorioAgendaSemanal.ACTION, RelatorioAgendaSemanal.COMMAND_PRINT, ImageList.COMMAND_PRINT, "javascript:executeCommand('PRINT');", "", "", false)%></td>
            </tr>
            <tr>
              <td><%=CommandControl.actionCustomScriptHyperlink(facade, RelatorioAgendaSemanal.ACTION, RelatorioAgendaSemanal.COMMAND_COPY, ImageList.COMMAND_COPY, "javascript:executeCommand('SELECTALL');executeCommand('COPY');", "", "", false)%></td>
            </tr>
          </table>
        <%=frameBar.endFrame()%>

      <%=frameBar.endFrameArea()%>

      <!-- �rea do relat�rio -->
      <%=frameBar.beginClientArea()%>
        <iframe name="frameReport" id="frameReport" src="<%=RelatorioAgendaSemanal.ACTION_RELATORIO.url()%>" frameborder="false" style="width:100%; height:100%;" />
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
