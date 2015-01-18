
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
  class FaseOportunidade{
    String nome = "";
    double quantidade = 0.0D;
    public FaseOportunidade(String nome, double quantidade) {
      this.nome = nome;
      this.quantidade = quantidade;
    }
  }
  // início do bloco protegido
  try {
    // nossa instância de Atendimento FaseOportunidade
    CartaoFunilOportunidade cartaoFunilOportunidade = (CartaoFunilOportunidade)facade.getCard(CartaoFunilOportunidade.CLASS_NAME);
    // nosso gráfico
    Chart chart = new Chart(facade, "chart");
%>

<html>
  <head>
    <title><%=CartaoFunilOportunidade.ACTION.getCaption()%></title>
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
      ResultSet resultSetCartaoFunilOportunidade = cartaoFunilOportunidade.getResultSetFunilOportunidade(selectedEmpresaId);
      ParamList faseOportunidadeList = new ParamList();
      int faseOportunidadeId = 0;
      try {%>
        <div id="divChart" align="center" style="width:100%;"></div>
        <script type="text/javascript">
          var aHeight = (document.body.offsetHeight != null ? document.body.offsetHeight : 320);
          document.getElementById("divChart").style.height = aHeight;
        </script>
        <table style="width:100%;">
          <tr>
            <td style="width:35px;"><b>Oportunidade</b></td>
            <td align="center" style="width:140px;"><b>Acompanhamento</b></td>
            <td style="width:auto;"><b>Cliente</b></td>
            <td align="center" style="width:90px;"><b>Telefone</b></td>
          </tr>
        <%// loop nos registros
          while (resultSetCartaoFunilOportunidade.next()) {
            if (faseOportunidadeId != resultSetCartaoFunilOportunidade.getInt(1)) {
              %>
              <tr>
                <td colspan="4"><b><%=resultSetCartaoFunilOportunidade.getString(2)%></b></td> 
              </tr>
              <%
              faseOportunidadeId = resultSetCartaoFunilOportunidade.getInt(1);
            }
            // nosso index do faseOportunidade
            int index = faseOportunidadeList.indexOf(faseOportunidadeId + ""); 
            // se já possuimos o faseOportunidade na lista... incrementa quantidade.
            if (index < 0) {
              FaseOportunidade faseOportunidade = new FaseOportunidade(resultSetCartaoFunilOportunidade.getString(2).toUpperCase(), 1);
              faseOportunidadeList.add(new Param(faseOportunidadeId + "", faseOportunidade));
            }
            // se não...adiciona o faseOportunidade
            else {
              FaseOportunidade faseOportunidade = (FaseOportunidade)faseOportunidadeList.get(index).getObject();
              faseOportunidade.quantidade++;
            } // if
            String telefone = "";
            if (!resultSetCartaoFunilOportunidade.getString(5).trim().equals(""))
              telefone = resultSetCartaoFunilOportunidade.getString(5);
            else if (!resultSetCartaoFunilOportunidade.getString(6).trim().equals(""))
              telefone = resultSetCartaoFunilOportunidade.getString(6);
            else if (!resultSetCartaoFunilOportunidade.getString(7).trim().equals(""))
              telefone = resultSetCartaoFunilOportunidade.getString(7);%>
          <tr>
            <td>
              <%// se não é um dispositivo móvel...
                if (!facade.getBrowserMobile()) {%>
                  <a href="<%=Oportunidade.ACTION_CADASTRO.url(Oportunidade.COMMAND_EDIT, Oportunidade.FIELD_EMPRESA_ID.getFieldAlias() + "=" + selectedEmpresaId + "&" + Oportunidade.FIELD_OPORTUNIDADE_ID.getFieldAlias() + "=" + resultSetCartaoFunilOportunidade.getInt(3))%>" title="Ir para Oportunidade..." target="frameContent"><%=resultSetCartaoFunilOportunidade.getString(3)%></a>
              <%}
                else {%>
                  <%=resultSetCartaoFunilOportunidade.getString(3)%>
              <%} // if%>
            </td>
            <td  align="center"><%=DateTools.formatDate(resultSetCartaoFunilOportunidade.getTimestamp(8))%></td>
            <td>
              <%// se não é um dispositivo móvel...
                if (facade.getBrowserMobile()) {%>              
                  <a href="javascript:void(0);" onclick="anotacoes('<%="anotacoesOportunidadeId" + resultSetCartaoFunilOportunidade.getString(3)%>')"><%=resultSetCartaoFunilOportunidade.getString(4)%></a> 
              <%}
                else {%>                  
                  <%=resultSetCartaoFunilOportunidade.getString(4)%>
              <%} // if%>              
            </td>
            <td  align="center"><%=StringTools.formatCustomMask(telefone, "(00) 0000-0000")%></td>
            <%if (facade.getBrowserMobile()) {%>
                <tr>
                  <td></td>
                  <td colspan="3"><div  id="<%="anotacoesOportunidadeId" + resultSetCartaoFunilOportunidade.getString(3)%>" style="display:none"><%=resultSetCartaoFunilOportunidade.getString(10)%></div></td>
                </tr>
            <%} // if%>            
          </tr>
        <%} // while%>
        </table>

    <%}
      finally {
        // libera recursos
        resultSetCartaoFunilOportunidade.getStatement().close();
        resultSetCartaoFunilOportunidade.close();
      } // try-finally

      // loop nos faseOportunidades
      for(int i = 0; i < faseOportunidadeList.size(); i++) {
        Param param = faseOportunidadeList.get(i); 
        FaseOportunidade faseOportunidade = (FaseOportunidade)param.getObject(); 
        chart.addValue(faseOportunidade.nome, "", (double)faseOportunidade.quantidade);
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
