       
<%@include file="../include/beans.jsp"%>

<%@page import="imanager.entity.*"%>
<%@page import="imanager.report.*"%>
<%@page import="imanager.misc.*"%>

<%@page import="securityservice.entity.*"%>

<%
  // in�cio do bloco protegido
  try {
    // nossa inst�ncia de Tarefa Assunto
    RelatorioTarefaAssunto relatorioTarefaAssunto = (RelatorioTarefaAssunto)facade.getReport(RelatorioTarefaAssunto.CLASS_NAME);
    // nossa inst�ncia de Assunto
    Usuario usuario = (Usuario)facade.getEntity(Usuario.CLASS_NAME);
    // nossa inst�ncia de Departamento
    Departamento departamento = (Departamento)facade.getEntity(Departamento.CLASS_NAME);
    // nossa inst�ncia de Tarefa
    Tarefa tarefa = (Tarefa)facade.getEntity(Tarefa.CLASS_NAME);

    // define os valores dos par�metros de usu�rio
    relatorioTarefaAssunto.userParamList().setParamsValues(request);

    // nosso gr�fico
    Chart chartRelatorioTarefaAssunto = new Chart(facade, "chartRelatorioTarefaAssunto");

    // procura pelo Usu�rio
    UsuarioInfo usuarioInfo = null;
    if (relatorioTarefaAssunto.USER_PARAM_USUARIO.getIntValue() > 0)
      usuarioInfo = usuario.selectByUsuarioId(relatorioTarefaAssunto.USER_PARAM_USUARIO.getIntValue());
    // procura pelo Departamento
    DepartamentoInfo departamentoInfo = null;
    if (relatorioTarefaAssunto.USER_PARAM_DEPARTAMENTO.getIntValue() > 0)
      departamentoInfo = departamento.selectByPrimaryKey(selectedEmpresaId, relatorioTarefaAssunto.USER_PARAM_DEPARTAMENTO.getIntValue());

    // estamos na central de atendimento?
    boolean isCentralAtendimento = request.getParameter(tarefa.CONTROL_PARAM_CENTRAL_ATENDIMENTO.getName()) != null;
    // grava na sess�o
    session.setAttribute(tarefa.CONTROL_PARAM_CENTRAL_ATENDIMENTO.getName(), isCentralAtendimento);

    // nosso Grid
    ReportGridEx reportGridRelatorioTarefaAssunto = new ReportGridEx("gridRelatorioTarefaAssunto", true);
    reportGridRelatorioTarefaAssunto.addColumn("ID", 5, ReportGrid.ALIGN_LEFT);
    reportGridRelatorioTarefaAssunto.addColumn("Departamento", 15, ReportGrid.ALIGN_LEFT);
    reportGridRelatorioTarefaAssunto.addColumn("Usu�rio", 20, ReportGrid.ALIGN_LEFT);
    reportGridRelatorioTarefaAssunto.addColumn("Cliente", 20, ReportGrid.ALIGN_LEFT);
    reportGridRelatorioTarefaAssunto.addColumn("Status", 15, ReportGrid.ALIGN_LEFT);
    reportGridRelatorioTarefaAssunto.addColumn("Prazo", 10, ReportGrid.ALIGN_LEFT);
    reportGridRelatorioTarefaAssunto.addColumn("Prorrogado", 5, ReportGrid.ALIGN_RIGHT);
    reportGridRelatorioTarefaAssunto.addColumn("Dias em Atraso", 10, ReportGrid.ALIGN_RIGHT);
%>

<html>
  <head>
    <title><%=RelatorioTarefaAssunto.ACTION.getCaption()%></title>
    <link href="<%=facade.getStyle().reportInterface()%>" rel="stylesheet" type="text/css">
    <script src="include/scripts.js" type="text/javascript"></script>
  </head>
  <body style="margin:5px;" oncontextmenu="return false;">

    <!-- Cabe�alho -->
    <%=ReportHeader.script(facade, RelatorioTarefaAssunto.ACTION, true, true, true, true, true)%>

    <!-- Par�metros -->
    <p>
      <table>
        <tr>
          <td><%=ReportParamLabel.script(facade, relatorioTarefaAssunto.USER_PARAM_DATA_INICIAL)%></td>
          <td><%=ReportParamText.script(facade, relatorioTarefaAssunto.USER_PARAM_DATA_INICIAL)%></td>
        </tr>
        <tr>
          <td><%=ReportParamLabel.script(facade, relatorioTarefaAssunto.USER_PARAM_DATA_FINAL)%></td>
          <td><%=ReportParamText.script(facade, relatorioTarefaAssunto.USER_PARAM_DATA_FINAL)%></td>
        </tr>
        <tr>
          <td><%=ReportParamLabel.script(facade, relatorioTarefaAssunto.USER_PARAM_STATUS)%></td>
          <td><%=ReportParamText.script(facade, relatorioTarefaAssunto.USER_PARAM_STATUS)%></td>
        </tr>
        <tr>
          <td><%=ReportParamLabel.script(facade, relatorioTarefaAssunto.USER_PARAM_USUARIO)%></td>
          <td><%=usuarioInfo != null ? usuarioInfo.getNome() : ""%></td>
        </tr>
        <tr>
          <td><%=ReportParamLabel.script(facade, relatorioTarefaAssunto.USER_PARAM_DEPARTAMENTO)%></td>
          <td><%=departamentoInfo != null ? departamentoInfo.getNome() : ""%></td>
        </tr>
        <tr>
          <td><%=ReportParamLabel.script(facade, relatorioTarefaAssunto.USER_PARAM_PRORROGADO)%></td>
          <td><%=ReportParamText.script(facade, relatorioTarefaAssunto.USER_PARAM_PRORROGADO)%></td>
        </tr>
      </table>
    </p>

    <!-- Gr�fico -->
    <p align="center">
      <div id="divChart" align="center" style="width:100%; height:300px;">Carregando dados...</div>
    </p>

    <!-- Grid de dados -->
    <%ResultSet resultSetRelatorioTarefaAssunto = relatorioTarefaAssunto.getResultSetTarefaAssunto(selectedEmpresaId, relatorioTarefaAssunto.USER_PARAM_DATA_INICIAL.getDateValue(), relatorioTarefaAssunto.USER_PARAM_DATA_FINAL.getDateValue(), relatorioTarefaAssunto.USER_PARAM_STATUS.getIntValue(), relatorioTarefaAssunto.USER_PARAM_USUARIO.getIntValue(), relatorioTarefaAssunto.USER_PARAM_DEPARTAMENTO.getIntValue(), relatorioTarefaAssunto.USER_PARAM_PRORROGADO.getIntValue(), relatorioTarefaAssunto.USER_PARAM_CONSOLIDADO.getIntValue());%>
    <%try {
        // controlador do primeiro registro
        boolean primeiro = true;
        // dias em atraso
        Calendar dataPrazo = Calendar.getInstance();
        Calendar dataAtual = Calendar.getInstance();
        // armazena os dias em atraso
        double diasAtraso = 0;
        // totalizador
        int totalRegistros = 0;
        // armazena o nome do assunto da vez
        String proximoAssunto = "";
        // loop nos registros
        while(resultSetRelatorioTarefaAssunto.next()) {
          // se o nome do assunto � diferente do anterior...
          if(!resultSetRelatorioTarefaAssunto.getString("nome_assunto").equals(proximoAssunto)) {
            // se n�o � o primeiro registro... fecha o grid
            if(!primeiro) {
              // adiciona ao gr�fico
              chartRelatorioTarefaAssunto.addValue(proximoAssunto, "", totalRegistros);%>
              <%=reportGridRelatorioTarefaAssunto.beginRow(true)%>
                <%=reportGridRelatorioTarefaAssunto.beginCell(8)%> <%=totalRegistros%> registro(s) <%=reportGridRelatorioTarefaAssunto.endCell()%>
              <%=reportGridRelatorioTarefaAssunto.endRow()%>
            <%=reportGridRelatorioTarefaAssunto.end()%>
          <%} // if
            // imprime novo cabe�alho na tela %>
          <%=ReportTitle.script(resultSetRelatorioTarefaAssunto.getString("nome_assunto"), ReportTitle.LEVEL_1)%>
          <%// proximoNome recebe o nome do pr�ximo assunto
            proximoAssunto = resultSetRelatorioTarefaAssunto.getString("nome_assunto");%>
          <%// zera totalizador
            totalRegistros = 0;%>
          <%=reportGridRelatorioTarefaAssunto.begin()%>
          <%// se � o primeiro registro... primeiro recebe false
            if(primeiro)
             primeiro = false;%>
        <%} // if
          // seta dataPrazo como a data do registro atual
          dataPrazo.setTime(resultSetRelatorioTarefaAssunto.getTimestamp("prazo"));
          // calcula a quantidade de dias em atraso
          diasAtraso = ((dataAtual.getTimeInMillis() - dataPrazo.getTimeInMillis())/(1000 * 60 * 60 * 24)) > 0 ? (dataAtual.getTimeInMillis() - dataPrazo.getTimeInMillis())/(1000 * 60 * 60 * 24) : 0;%>
        <%=reportGridRelatorioTarefaAssunto.beginRow()%>
          <%=reportGridRelatorioTarefaAssunto.beginCell()%> <a href="<%=Tarefa.ACTION_CADASTRO.url(Tarefa.COMMAND_EDIT, Tarefa.FIELD_EMPRESA_ID.getFieldAlias() + "=" + resultSetRelatorioTarefaAssunto.getInt("empresa_id") + "&" + Tarefa.FIELD_TAREFA_ID.getFieldAlias() + "=" + resultSetRelatorioTarefaAssunto.getInt("tarefa_id"))%>" title="Ir para Tarefa..." target="frameContent"> <%=resultSetRelatorioTarefaAssunto.getInt("tarefa_id")%> </a> <%=reportGridRelatorioTarefaAssunto.endCell()%>
          <%=reportGridRelatorioTarefaAssunto.beginCell()%> <%=resultSetRelatorioTarefaAssunto.getString("nome_departamento")%> <%=reportGridRelatorioTarefaAssunto.endCell()%>
          <%=reportGridRelatorioTarefaAssunto.beginCell()%> <%=resultSetRelatorioTarefaAssunto.getString("nome_usuario")%> <%=reportGridRelatorioTarefaAssunto.endCell()%>
          <%=reportGridRelatorioTarefaAssunto.beginCell()%> <%=resultSetRelatorioTarefaAssunto.getString("nome_cliente")%> <%=reportGridRelatorioTarefaAssunto.endCell()%>
          <%=reportGridRelatorioTarefaAssunto.beginCell()%> <%=StatusTarefa.LOOKUP_LIST_FOR_FIELD[resultSetRelatorioTarefaAssunto.getInt("status")]%> <%=reportGridRelatorioTarefaAssunto.endCell()%>
          <%=reportGridRelatorioTarefaAssunto.beginCell()%> <%=diasAtraso != 0 ? "<font style=color:red>" + DateTools.formatDate(resultSetRelatorioTarefaAssunto.getTimestamp("prazo")) + "</font>" : DateTools.formatDate(resultSetRelatorioTarefaAssunto.getTimestamp("prazo"))%> <%=reportGridRelatorioTarefaAssunto.endCell()%>
          <%=reportGridRelatorioTarefaAssunto.beginCell()%> <%=resultSetRelatorioTarefaAssunto.getString("prorrogado")%> <%=reportGridRelatorioTarefaAssunto.endCell()%>
          <%=reportGridRelatorioTarefaAssunto.beginCell()%> <%=(int)diasAtraso%> <%=reportGridRelatorioTarefaAssunto.endCell()%>
        <%=reportGridRelatorioTarefaAssunto.endRow()%>
        <%// adiciona no totalizador
          totalRegistros++;
          // se � o ultimo registro...%>
        <%if(resultSetRelatorioTarefaAssunto.isLast()) {
            // adiciona ao gr�fico
            chartRelatorioTarefaAssunto.addValue(resultSetRelatorioTarefaAssunto.getString("nome_assunto"), "", totalRegistros);%>
            <%=reportGridRelatorioTarefaAssunto.beginRow(true)%>
              <%=reportGridRelatorioTarefaAssunto.beginCell(8)%> <%=totalRegistros%> registro(s) <%=reportGridRelatorioTarefaAssunto.endCell()%>
            <%=reportGridRelatorioTarefaAssunto.endRow()%>
          <%=reportGridRelatorioTarefaAssunto.end()%>
        <%} // if%>
      <%} // while%>
    <%}
      finally {
        // libera recursos
        resultSetRelatorioTarefaAssunto.getStatement().close();
        resultSetRelatorioTarefaAssunto.close();
      } // try-finally
    %>

    <!-- Rodap� -->
    <%=ReportFooter.script(facade, true, true)%>

    <!-- gera o gr�fico -->
    <%=chartRelatorioTarefaAssunto.script(Chart.TYPE_COLUMN, "divChart", "Gr�fico Tarefa por Assunto", "", "", Chart.INTERFACE_STYLE_REPORT_INTERFACE)%>

  </body>
</html>

<%}
  // t�rmino do bloco protegido
  catch (Exception e) {
    Controller.forwardException(e, pageContext);
  } // try-catch
%>
