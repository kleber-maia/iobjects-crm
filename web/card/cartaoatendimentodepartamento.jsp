
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

<%@page import="imanager.card.*"%>
<%@page import="imanager.entity.*"%>
<%@page import="imanager.misc.*"%>

<%
  class Departamento{
    String nome = "";
    double quantidade = 0.0D;
    public Departamento(String nome, double quantidade) {
      this.nome = nome;
      this.quantidade = quantidade;
    }
  }
  // início do bloco protegido
  try {
    // nossa instância de Atendimento Departamento
    CartaoAtendimentoDepartamento cartaoAtendimentoDepartamento = (CartaoAtendimentoDepartamento)facade.getCard(CartaoAtendimentoDepartamento.CLASS_NAME);
    // nosso gráfico
    Chart chart = new Chart(facade, "chart");
%>

<html>
  <head>
    <title><%=CartaoAtendimentoDepartamento.ACTION.getCaption()%></title>
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
      ResultSet resultSetCartaoAtendimentoDepartamento = cartaoAtendimentoDepartamento.getResultSetAtendimentoDepartamento(selectedEmpresaId);
      ParamList departamentoList = new ParamList();
      int departamentoId = 0;
      try {%>
        <div id="divChart" align="center" style="width:100%;"></div>
        <script type="text/javascript">
          var aHeight = (document.body.offsetHeight != null ? document.body.offsetHeight : 320);
          document.getElementById("divChart").style.height = aHeight;
        </script>
        <table style="width:100%;">
          <tr>
            <td style="width:40px;"><b>Atendimento</b></td>
            <td align="center" style="width:70px;"><b>Data</b></td>
            <td style="width:auto;"><b>Cliente</b></td>
            <td align="center" style="width:90px;"><b>Telefone</b></td>
          </tr>
        <%// loop nos registros
          while (resultSetCartaoAtendimentoDepartamento.next()) {
            if (departamentoId != resultSetCartaoAtendimentoDepartamento.getInt(1)) {
              %>
              <tr>
                <td colspan="4"><b><%=resultSetCartaoAtendimentoDepartamento.getString(2)%></b></td> 
              </tr>
              <%
              departamentoId = resultSetCartaoAtendimentoDepartamento.getInt(1);
            }
            // nosso index do departamento
            int index = departamentoList.indexOf(departamentoId + ""); 
            // se já possuimos o departamento na lista... incrementa quantidade.
            if (index < 0) {
              Departamento departamento = new Departamento(resultSetCartaoAtendimentoDepartamento.getString(2).toUpperCase(), 1);
              departamentoList.add(new Param(departamentoId + "", departamento));
            }
            // se não...adiciona o departamento
            else {
              Departamento departamento = (Departamento)departamentoList.get(index).getObject();
              departamento.quantidade++;
            } // if
            String telefone = "";
            if (!resultSetCartaoAtendimentoDepartamento.getString(5).trim().equals(""))
              telefone = resultSetCartaoAtendimentoDepartamento.getString(5);
            else if (!resultSetCartaoAtendimentoDepartamento.getString(6).trim().equals(""))
              telefone = resultSetCartaoAtendimentoDepartamento.getString(6);
            else if (!resultSetCartaoAtendimentoDepartamento.getString(7).trim().equals(""))
              telefone = resultSetCartaoAtendimentoDepartamento.getString(7);%>
          <tr>
            <td>
              <%// se não é um dispositivo móvel...
                if (!facade.getBrowserMobile()) {%>
                  <a href="<%=Atendimento.ACTION_CADASTRO.url(Atendimento.COMMAND_EDIT, Atendimento.FIELD_EMPRESA_ID.getFieldAlias() + "=" + selectedEmpresaId + "&" + Atendimento.FIELD_ATENDIMENTO_ID.getFieldAlias() + "=" + resultSetCartaoAtendimentoDepartamento.getInt(3))%>" title="Ir para Atendimento..." target="frameContent"><%=resultSetCartaoAtendimentoDepartamento.getString(3)%></a>
              <%}
                else {%>
                  <%=resultSetCartaoAtendimentoDepartamento.getString(3)%>
              <%} // if%>
            </td>
            <td  align="center"><%=DateTools.formatDate(resultSetCartaoAtendimentoDepartamento.getTimestamp(8))%></td>
            <td>
              <%// se não é um dispositivo móvel...
                if (facade.getBrowserMobile()) {%>              
                  <a href="javascript:void(0);" onclick="anotacoes('<%="anotacoesAtendimentoId" + resultSetCartaoAtendimentoDepartamento.getString(3)%>')"><%=resultSetCartaoAtendimentoDepartamento.getString(4)%></a>
              <%}
                else {%>                  
                  <%=resultSetCartaoAtendimentoDepartamento.getString(4)%>
              <%} // if%>
            </td>
            <td  align="center"><%=StringTools.formatCustomMask(telefone, "(00) 0000-0000")%></td>
            <%if (facade.getBrowserMobile()) {%>
                <tr>
                  <td></td>
                  <td colspan="3"><div  id="<%="anotacoesAtendimentoId" + resultSetCartaoAtendimentoDepartamento.getString(3)%>" style="display:none"><%=resultSetCartaoAtendimentoDepartamento.getString(9)%></div></td> 
                </tr>
            <%} // if%>                        
          </tr>
        <%} // while%>
        </table>

    <%}
      finally {
        // libera recursos
        resultSetCartaoAtendimentoDepartamento.getStatement().close();
        resultSetCartaoAtendimentoDepartamento.close();
      } // try-finally

      // loop nos departamentos
      for(int i = 0; i < departamentoList.size(); i++) {
        Param param = departamentoList.get(i); 
        Departamento departamento = (Departamento)param.getObject(); 
        chart.addValue(departamento.nome, "", (double)departamento.quantidade);
      } // for
    %>
    
    <%=chart.script(Chart.TYPE_COLUMN, "divChart", "", "", "", Chart.INTERFACE_STYLE_USER_INTERFACE, false, !Controller.isMobileRequest(request))%>    

  </body>
</html>

<%
  }
  // término do bloco protegido
  catch (Exception e) {
    // encaminha exceção
    Controller.forwardException(e, pageContext);
  } // try-catch
%>
