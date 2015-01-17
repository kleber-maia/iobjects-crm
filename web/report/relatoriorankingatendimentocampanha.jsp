     
<%@include file="../include/beans.jsp"%>

<%@page import="imanager.misc.*"%>
<%@page import="imanager.report.*"%>
<%@page import="imanager.ui.entity.*"%>

<%@page import="securityservice.ui.entity.*"%>

<%
  // in�cio do bloco protegido
  try {
    // nossa inst�ncia de Ranking Atendimento Campanha
    RelatorioRankingAtendimentoCampanha relatorioRankingAtendimentoCampanha = (RelatorioRankingAtendimentoCampanha)facade.getReport(RelatorioRankingAtendimentoCampanha.CLASS_NAME);

    // define os par�metros, caso sejamos chamados por outro objeto
    relatorioRankingAtendimentoCampanha.userParamList().setParamsValues(request);

    // nosso Form de relat�rio
    Form formReport = new Form(request, "formReportRelatorioRankingAtendimentoCampanha", RelatorioRankingAtendimentoCampanha.ACTION_RELATORIO, RelatorioRankingAtendimentoCampanha.COMMAND_MAKE_REPORT, "frameReport", true);
    // nosso FrameBar
    FrameBar frameBar = new FrameBar(facade, "frameBarRelatorioRankingAtendimentoCampanha");
%>

<html>
  <head>
    <title><%=RelatorioRankingAtendimentoCampanha.ACTION.getCaption()%></title>
    <link href="<%=facade.getStyle().userInterface()%>" rel="stylesheet" type="text/css">
    <script src="include/scripts.js" type="text/javascript"></script>
  </head>
  <body style="margin:0px;" onselectstart="return true;" oncontextmenu="return false;">

    <!-- inicia o FrameBar -->
    <%=frameBar.begin()%>

      <!-- �rea de frames -->
      <%=frameBar.beginFrameArea()%>

        <!-- Frame de identifica��o do objeto -->
        <%=frameBar.actionFrame(RelatorioRankingAtendimentoCampanha.ACTION)%>

        <!-- Frame de par�metros -->
        <%=frameBar.beginFrame("Par�metros", false, true)%>
          <!-- Form de relat�rio -->
          <%=formReport.begin()%>
            <table style="width:100%;">
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_DATA_INICIAL)%></td>
              </tr>
              <tr>
                <td><%=ParamFormEdit.script(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_DATA_INICIAL, 0, "", "")%></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_DATA_FINAL)%></td>
              </tr>
              <tr>
                <td><%=ParamFormEdit.script(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_DATA_FINAL, 0, "", "")%></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_USUARIO_ID)%></td>
              </tr>
              <tr>
                <td><%=UsuarioUI.lookupSearchForParam(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_USUARIO_ID, 0, "", "", false) %></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_ASSUNTO_ID)%></td>
              </tr>
              <tr>
                <td><%=AssuntoUI.lookupSelectExForParam(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_ASSUNTO_ID, 0, "", "")%></td>
              </tr>
              <tr>
                <td><%=ParamLabel.script(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_CONSOLIDADO)%></td>
              </tr>
              <tr>
                <td><%=ParamFormSelect.script(facade, relatorioRankingAtendimentoCampanha.USER_PARAM_CONSOLIDADO, 0, "", "")%></td>
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
              <td><%=CommandControl.actionCustomScriptHyperlink(facade, RelatorioRankingAtendimentoCampanha.ACTION, RelatorioRankingAtendimentoCampanha.COMMAND_PRINT, ImageList.COMMAND_PRINT, "javascript:executeCommand('PRINT');", "", "", false)%></td>
            </tr>
            <tr>
              <td><%=CommandControl.actionCustomScriptHyperlink(facade, RelatorioRankingAtendimentoCampanha.ACTION, RelatorioRankingAtendimentoCampanha.COMMAND_COPY, ImageList.COMMAND_COPY, "javascript:executeCommand('SELECTALL');executeCommand('COPY');", "", "", false)%></td>
            </tr>
          </table>
        <%=frameBar.endFrame()%>

      <%=frameBar.endFrameArea()%>

      <!-- �rea do relat�rio -->
      <%=frameBar.beginClientArea()%>
        <iframe name="frameReport" id="frameReport" src="<%=RelatorioRankingAtendimentoCampanha.ACTION_RELATORIO.url()%>" frameborder="false" style="width:100%; height:100%;" />
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
