
<%@include file="../include/beans.jsp"%>

<%@page import="imanager.card.*"%>
<%@page import="imanager.entity.*"%>
<%@page import="imanager.misc.*"%>

<%
  class Status{
    String nome = "";
    double quantidade = 0.0D;
    public Status(String nome, double quantidade) {
      this.nome = nome;
      this.quantidade = quantidade;
    }
  }
  // in�cio do bloco protegido
  try {
    // nossa inst�ncia de Atendimento Status
    CartaoTarefaStatus cartaoTarefaStatus = (CartaoTarefaStatus)facade.getCard(CartaoTarefaStatus.CLASS_NAME);
    // nosso gr�fico
    Chart chart = new Chart(facade, "chart");
%>

<html>
  <head>
    <title><%=CartaoTarefaStatus.ACTION.getCaption()%></title>
    <link href="<%=facade.getStyle().userInterface()%>" rel="stylesheet" type="text/css">
    <script src="include/scripts.js" type="text/javascript"></script>
  </head>
  <body class="frameBody" style="border:0px; margin:0px;" onselectstart="return false;" oncontextmenu="return false;">
    
    <script type="text/javascript">
      function anotacoes(id) {
        var divAnotacoes = document.getElementById(id);
        if (divAnotacoes.style.display == "none")
          divAnotacoes.style.display = "block";
        else
          divAnotacoes.style.display = "none";
      }
    </script>    

    <%// nosso ResultSet
      ResultSet resultSetCartaoTarefaStatus = cartaoTarefaStatus.getResultSetTarefaStatus(selectedEmpresaId);
      ParamList statusList = new ParamList();
      int statusId = 0;
      try {%>
        <div id="divChart" align="center" style="width:100%;"></div>
        <script type="text/javascript">
          var aHeight = (document.body.offsetHeight != null ? document.body.offsetHeight : 320);
          document.getElementById("divChart").style.height = aHeight;
        </script>
        <table style="width:100%;">
          <tr>
            <td style="width:40px;"><b>Tarefa</b></td>
            <td align="center" style="width:70px;"><b>Prazo</b></td>
            <td style="width:auto;"><b>Cliente</b></td>
            <td style="width:100px;"><b>Usu�rio</b></td>
            <td align="center" style="width:90px;"><b>Telefone</b></td>
          </tr>
        <%// loop nos registros
          while (resultSetCartaoTarefaStatus.next()) {
            if (statusId != resultSetCartaoTarefaStatus.getInt(1)) {
              %>
              <tr>
                <td colspan="5"><b><%=StatusTarefa.LOOKUP_LIST_FOR_FIELD[resultSetCartaoTarefaStatus.getInt(1)]%></b></td> 
              </tr>
              <%
              statusId = resultSetCartaoTarefaStatus.getInt(1);
            }
            // nosso index do status
            int index = statusList.indexOf(statusId + ""); 
            // se j� possuimos o status na lista... incrementa quantidade.
            if (index < 0) {
              Status status = new Status(StatusTarefa.LOOKUP_LIST_FOR_FIELD[resultSetCartaoTarefaStatus.getInt(1)].toUpperCase(), 1);
              statusList.add(new Param(statusId + "", status));
            }
            // se n�o...adiciona o status
            else {
              Status status = (Status)statusList.get(index).getObject();
              status.quantidade++;
            } // if
            String telefone = "";
            if (!resultSetCartaoTarefaStatus.getString(4).trim().equals(""))
              telefone = resultSetCartaoTarefaStatus.getString(4);
            else if (!resultSetCartaoTarefaStatus.getString(5).trim().equals(""))
              telefone = resultSetCartaoTarefaStatus.getString(5);
            else if (!resultSetCartaoTarefaStatus.getString(6).trim().equals(""))
              telefone = resultSetCartaoTarefaStatus.getString(6);%>
          <tr>
            <td>
              <%// se n�o � um dispositivo m�vel...
                if (!facade.getBrowserMobile()) {%>
                  <a href="<%=Tarefa.ACTION_CADASTRO.url(Tarefa.COMMAND_EDIT, Tarefa.FIELD_EMPRESA_ID.getFieldAlias() + "=" + selectedEmpresaId + "&" + Tarefa.FIELD_TAREFA_ID.getFieldAlias() + "=" + resultSetCartaoTarefaStatus.getInt(2))%>" title="Ir para Tarefa..." target="frameContent"><%=resultSetCartaoTarefaStatus.getString(2)%></a>
              <%}
                else {%>
                  <%=resultSetCartaoTarefaStatus.getString(2)%>
              <%} // if%>
            </td>
            <td  align="center"><%=DateTools.formatDate(resultSetCartaoTarefaStatus.getTimestamp(9))%></td>
            <td>
              <%// se � um dispositivo m�vel...
                if (facade.getBrowserMobile()) {%>              
                  <a href="javascript:void(0);" onclick="anotacoes('<%="anotacoesTarefaId" + resultSetCartaoTarefaStatus.getString(2)%>')"><%=resultSetCartaoTarefaStatus.getString(3)%></a>
              <%}
                else {%>                  
                  <%=resultSetCartaoTarefaStatus.getString(3)%> 
              <%} // if%>
            </td>
            <td><%=resultSetCartaoTarefaStatus.getString(8)%></td>
            <td  align="center"><%=StringTools.formatCustomMask(telefone, "(00) 0000-0000")%></td>
          <%// se � um dispositivo m�vel...
            if (facade.getBrowserMobile()) {%>
              <tr>
                <td></td>
                <td colspan="4"><div  id="<%="anotacoesTarefaId" + resultSetCartaoTarefaStatus.getString(2)%>" style="display:none"><%=resultSetCartaoTarefaStatus.getString(10)%></div></td>
              </tr>
          <%} // if%>                        
          </tr>
        <%} // while%>
        </table>

    <%}
      finally {
        // libera recursos
        resultSetCartaoTarefaStatus.getStatement().close();
        resultSetCartaoTarefaStatus.close();
      } // try-finally

      // loop nos statuss
      for(int i = 0; i < statusList.size(); i++) {
        Param param = statusList.get(i); 
        Status status = (Status)param.getObject(); 
        chart.addValue(status.nome, "", (double)status.quantidade);
      } // for
    %>
    
    <%=chart.script(Chart.TYPE_COLUMN, "divChart", "", "", "", Chart.INTERFACE_STYLE_USER_INTERFACE, false, !Controller.isMobileRequest(request))%>    

  </body>
</html>

<%
  }
  // t�rmino do bloco protegido
  catch (Exception e) {
    // encaminha exce��o
    Controller.forwardException(e, pageContext);
  } // try-catch
%>
