
<%@include file="../include/beans.jsp"%>

<%@page import="imanager.entity.*"%>
<%@page import="imanager.report.*"%>
<%@page import="imanager.misc.*"%>
<%@page import="imanager.ui.entity.*"%>

<%@page import="securityservice.ui.entity.*"%>

<%
  // in�cio do bloco protegido
  try {
    // nossa inst�ncia de Oportunidade
    Oportunidade oportunidade = (Oportunidade)facade.getEntity(Oportunidade.CLASS_NAME);
    // nossa inst�ncia de Oportunidade Hist�rico
    OportunidadeHistorico oportunidadeHistorico = (OportunidadeHistorico)facade.getEntity(OportunidadeHistorico.CLASS_NAME);
    // nossa inst�ncia de Historico Contato
    RelatorioHistoricoContato relatorioRelatorioHistoricoContato = (RelatorioHistoricoContato)facade.getReport(RelatorioHistoricoContato.CLASS_NAME);
    // nossa inst�ncia de Fase
    Fase fase = (Fase)facade.getEntity(Fase.CLASS_NAME);

    // Ajax para obter percentual de sucesso
    Ajax ajaxPercentualSucesso = new Ajax(facade, "ajaxPercentualSucesso", Oportunidade.ACTION_CADASTRO.getName(), "consultaPercentualSucesso");

    // OportunidadeInfo para editar
    OportunidadeInfo oportunidadeInfo = null;
    // FaseInfo para editar
    FaseInfo faseInfo = null;

    // se estamos inserindo...
    if (Controller.isInserting(request)) {
      // info em branco
      oportunidadeInfo = new OportunidadeInfo();
      // valor padr�o
      oportunidadeInfo.setEmpresaId(selectedEmpresaId);
      oportunidadeInfo.setLinkExterno("http://");
    }
    // se estamos salvando...
    else if (Controller.isSaving(request)) {
      // info preenchido na p�gina
      oportunidadeInfo = (OportunidadeInfo)oportunidade.entityInfoFromRequest(request);
      // se estamos salvando e � um novo...inclui
      if (Controller.isSavingNew(request)) {
        oportunidade.insert(oportunidadeInfo);
      }
      // se estamos salvando e � um existente...atualiza
      else {
        oportunidade.update(oportunidadeInfo);
      } // if
      // comp�e a resposta
      Form.composeAjaxResponse(response, Form.COMPOSE_TYPE_JAVA_SCRIPT);
      // atualiza a consulta
      %><%=EntityGrid.forwardBrowse(facade, oportunidade, Oportunidade.ACTION, Controller.isSavingNew(request) ? EntityGrid.OPERATION_INSERT : EntityGrid.OPERATION_UPDATE, oportunidadeInfo)%><%
      // dispara
      return;
    }
    // se estamos editando...
    else if (Controller.isEditing(request)) {
      // info do registro para editar
      oportunidadeInfo =
        oportunidade.selectByPrimaryKey(
          NumberTools.parseInt(request.getParameter(Oportunidade.FIELD_EMPRESA_ID.getFieldAlias())),
          NumberTools.parseInt(request.getParameter(Oportunidade.FIELD_OPORTUNIDADE_ID.getFieldAlias()))
        );
    }
    else if (Controller.getCommand(request).equals("consultaPercentualSucesso")) {
      // comp�e a resposta
      Form.composeAjaxResponse(response, Form.COMPOSE_TYPE_JAVA_SCRIPT);
      // fase selecionada
      faseInfo = fase.selectByPrimaryKey(Integer.parseInt(request.getParameter("faseId")));
      // comp�e a resposta
      Ajax.setResponseHeader(response, "percentualSucesso", NumberTools.format(faseInfo.getPercentualSucesso()));
      return;
    }
    // se o comando � desconhecido...
    else {
      throw new UnknownCommandException();
    } // if

    // estamos na central de atendimento?
    Object obj = session.getAttribute(oportunidade.CONTROL_PARAM_CENTRAL_ATENDIMENTO.getName());
    boolean isCentralAtendimento = (obj != null) && ((Boolean)obj).booleanValue();

    // nosso FrameBar
    FrameBar frameBar = new FrameBar(facade, "frameBarOportunidade");
    // nosso Form de dados
    Form formData = new Form(request, "formDataOportunidade", Oportunidade.ACTION_CADASTRO, Oportunidade.COMMAND_SAVE, "", true, true);
    // nosso PageControl
    PageControl pageControl = new PageControl(facade, "pageControl", true);;
%>

<html>
  <head>
    <title><%=Oportunidade.ACTION.getCaption()%></title>
    <link href="<%=facade.getStyle().userInterface()%>" rel="stylesheet" type="text/css">
    <script src="include/scripts.js" type="text/javascript"></script>
  </head>
  <body style="margin:0px;" onselectstart="return true;" oncontextmenu="return false;">

    <script type="text/javascript">

      function relatorioRelatorioHistoricoContato() {
        // contatoId
        var contatoId = document.getElementById("<%=Oportunidade.FIELD_CLIENTE_ID.getFieldAlias()%>").value;
        // chama o relat�rio
        <%=RelatorioHistoricoContato.ACTION.url(RelatorioHistoricoContato.COMMAND_MAKE_REPORT, relatorioRelatorioHistoricoContato.USER_PARAM_CONTATO_ID.getName() + "=' + contatoId + '")%>
      }

      function consultaPercentualSucesso() {
        // nossos par�metros
        var paramFaseId = "faseId=" + document.getElementById("<%=Oportunidade.FIELD_FASE_ID.getFieldAlias()%>").value;
        var fase        = document.getElementById("<%=Oportunidade.FIELD_FASE_ID.getFieldAlias()%>").value;
        // se temos fase selecionada...tras o percentual de sucesso
        if (fase != 0) {
          // faz a requisi��o
          <%=ajaxPercentualSucesso.request(new String[]{"paramFaseId"})%>
          // se ocorreu tudo bem...mostra os valor
          if (<%=ajaxPercentualSucesso.isResponseStatusOK()%>) {
            // mostra o percentual de sucesso
            document.getElementById("<%=Oportunidade.FIELD_PERCENTUAL_SUCESSO.getFieldAlias()%>").value = <%=ajaxPercentualSucesso.getResponseHeader("percentualSucesso")%>;
          } // if
          // se ocorreu um erro...mostra
          else {
            alert(<%=ajaxPercentualSucesso.responseErrorMessage()%>);
            return;
          } // if
        } // if
      }

      function abrirLink() {
        var url = document.getElementById('<%=Oportunidade.FIELD_LINK_EXTERNO.getFieldAlias()%>').value;
        if (url == "http://" || url == "")
          return;        
        // link externo
        window.open(url,'LinkExterno','resizable=yes,scrollbars=yes,status=no');
      }

    </script>

    <%// se n�o estamos na central de atendimento...
    if (!isCentralAtendimento) {%>
      <!-- inicia o FrameBar -->
      <%=frameBar.begin()%>

        <!-- �rea de frames -->
        <%=frameBar.beginFrameArea()%>

          <!-- Frame de identifica��o do objeto -->
          <%=frameBar.actionFrame(Oportunidade.ACTION)%>

          <!-- Frame de comandos -->
          <%=frameBar.beginFrame("Comandos", false, false)%>
            <table style="width:100%;">
              <tr>
                <td><%=CommandControl.formHyperlink(facade, formData, ImageList.COMMAND_SAVE, "Deseja mesmo salvar o registro?", true, false)%></td>
              </tr>
              <tr>
                <td><%=CommandControl.entityFormHyperlink(facade, oportunidade, Oportunidade.ACTION_CADASTRO, Oportunidade.COMMAND_INSERT, ImageList.COMMAND_INSERT, false)%></td>
              </tr>
              <tr>
                <td><%=CommandControl.actionCustomScriptHyperlink(facade, RelatorioHistoricoContato.ACTION, RelatorioHistoricoContato.COMMAND_MAKE_REPORT, ImageList.IMAGE_REPORT, "javascript:void(0);", "", "relatorioRelatorioHistoricoContato()", false)%></td>
              </tr>
              <tr>
                <td><%=CommandControl.entityBrowseHyperlink(facade, oportunidade, Oportunidade.ACTION, ImageList.COMMAND_BACK, "Voltar", "Volta para a consulta.", false)%></td>
              </tr>
            </table>
          <%=frameBar.endFrame()%>
        <%=frameBar.endFrameArea()%>
    <%} // if%>

      <!-- Form de dados -->
      <%=formData.begin()%>

        <!-- �rea de dados -->
        <%=frameBar.beginClientArea()%>
          <!-- dados ocultos -->
          <%=EntityFormEdit.script(facade, Oportunidade.FIELD_EMPRESA_ID, oportunidadeInfo, request, -1, "", "")%>
          <%=EntityFormEdit.script(facade, Oportunidade.FIELD_USUARIO_INCLUSAO_ID, oportunidadeInfo, request, -1, "", "")%>
          <%=EntityFormEdit.script(facade, Oportunidade.FIELD_DATA_INCLUSAO, oportunidadeInfo, request, -1, "", "")%>
          <%=EntityFormEdit.script(facade, Oportunidade.FIELD_USUARIO_ALTERACAO_ID, oportunidadeInfo, request, -1, "", "")%>
          <%=EntityFormEdit.script(facade, Oportunidade.FIELD_DATA_HORA_ALTERACAO, oportunidadeInfo, request, -1, "", "")%>
          <!-- dados de Oportunidade -->
          <table style="width:100%;height:100%" cellpadding="0" cellspacing="0">
            <tr style="height:50px">
              <td style="width:100%;">

                <!-- campos Departamento, Campanha, Fase, Cliente -->
                <%=GroupBox.begin("Identifica��o")%>
                  <table style="width:100%; table-layout: fixed;">
                    <tr>
                      <td style="width:35px;"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_OPORTUNIDADE_ID, request, "", "", false)%></td>
                      <td style="width:auto;"><%=EntityLookupLabel.script(facade, Oportunidade.LOOKUP_CLIENTE, request, "linkCliente", "", Contato.ACTION, false)%></td>
                      <td style="width:200px"><%=EntityLookupLabel.script(facade, Oportunidade.LOOKUP_DEPARTAMENTO, request, "linkDepartamento", "", Departamento.ACTION, false)%></td>
                      <td style="width:200px"><%=EntityLookupLabel.script(facade, Oportunidade.LOOKUP_CAMPANHA, request, "linkCampanha", "", Campanha.ACTION, false)%></td>
                      <td style="width:200px"><%=EntityLookupLabel.script(facade, Oportunidade.LOOKUP_FASE, request, "linkFase", "", Fase.ACTION ,false)%></td>
                    </tr>
                    <tr>
                      <td><%=EntityFormEdit.script(facade, Oportunidade.FIELD_OPORTUNIDADE_ID, oportunidadeInfo, request, 0, "", "", true, false)%></td>
                      <td><%=ContatoUI.lookupSearchForLookup(facade, Oportunidade.LOOKUP_CLIENTE, oportunidadeInfo, NaoSim.SIM, NaoSim.TODOS, NaoSim.TODOS, NaoSim.TODOS, NaoSim.TODOS, 0, "", "", false) %></td>
                      <td><%=DepartamentoUI.lookupListForLookup(facade, Oportunidade.LOOKUP_DEPARTAMENTO, oportunidadeInfo, selectedEmpresaId, 0, "", "") %></td>
                      <td><%=CampanhaUI.lookupListForLookup(facade, Oportunidade.LOOKUP_CAMPANHA, oportunidadeInfo, NaoSim.NAO, 0, "", "")%></td>
                      <td><%=FaseUI.lookupListForLookup(facade, Oportunidade.LOOKUP_FASE, oportunidadeInfo, 0, "", "consultaPercentualSucesso()")%></td>
                    </tr>
                  </table>
                <%=GroupBox.end()%>
              </td>
            </tr>
            <tr style="height:50px">
              <td style="width:100%">
                <table style="width:100%; table-layout: fixed;" cellpadding="0" cellspacing="0">
                  <tr>
                    <td style="width:60%">

                      <!-- campos Usu�rio , Acompanhamento, Percentual Sucesso, Status, Valor -->
                      <%=GroupBox.begin("Oportunidade")%>
                        <table style="width:100%; table-layout: fixed;">
                          <tr>
                            <td style="width:auto"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_USUARIO_ID, request)%></td>
                            <td style="width:100px"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_DATA_ACOMPANHAMENTO, request)%></td>
                            <td style="width:80px"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_PERCENTUAL_SUCESSO, request, "", "", true)%></td>
                            <td style="width:150px"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_STATUS, request)%></td>
                            <td style="width:120px"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_VALOR, request)%></td>
                          </tr>
                          <tr>
                            <td><%=UsuarioUI.lookupSearchForLookup(facade, Oportunidade.LOOKUP_USUARIO, oportunidadeInfo, 0, "", "", false)%></td>
                            <td><%=EntityFormEdit.script(facade, Oportunidade.FIELD_DATA_ACOMPANHAMENTO, oportunidadeInfo, request, 0, "", "")%></td>
                            <td><%=EntityFormEdit.script(facade, Oportunidade.FIELD_PERCENTUAL_SUCESSO, oportunidadeInfo, request, 0, "", "", true, false)%></td>
                            <td><%=EntityFormSelect.script(facade, Oportunidade.FIELD_STATUS, oportunidadeInfo, request, 0, "", "",false)%></td>
                            <td><%=EntityFormEdit.script(facade, Oportunidade.FIELD_VALOR, oportunidadeInfo, request, 0, "", "")%></td>
                          </tr>
                        </table>
                      <%=GroupBox.end()%>
                    </td>
                    <td>

                      <%// se n�o estamos na central de atendimento...
                      if (!isCentralAtendimento) {%>
                        <!-- campo Usu�rio Inclus�o, Data Inclus�o, Usu�rio Altera��o, Data Hora Altera��o -->
                        <%=GroupBox.begin("Seguran�a")%>
                          <table style="width:100%; table-layout: fixed;">
                            <tr>
                              <td style="width:50%"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_USUARIO_INCLUSAO_ID, request)%></td>
                              <td style="width:auto"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_USUARIO_ALTERACAO_ID, request)%></td>
                            </tr>
                            <tr>
                              <td><%=FormEdit.script(facade, "inclusao", (oportunidadeInfo.getUsuarioInclusaoId() > 0 ? facade.securityService().getUser(oportunidadeInfo.getUsuarioInclusaoId()).getName() : "") + " - " + DateTools.formatDate(oportunidadeInfo.getDataInclusao()), 0, 0, FormEdit.ALIGN_LEFT, "", "", "", "", "", true)%></td>
                              <td><%=FormEdit.script(facade, "alteracao", (oportunidadeInfo.getUsuarioAlteracaoId() > 0 ? facade.securityService().getUser(oportunidadeInfo.getUsuarioAlteracaoId()).getName() : "") + " - " + DateTools.formatDateTime(oportunidadeInfo.getDataHoraAlteracao()), 0, 0, FormEdit.ALIGN_LEFT, "", "", "", "", "", true)%></td>
                            </tr>
                          </table>
                        <%=GroupBox.end()%>
                    <%}
                      else {%>
                        <%=GroupBox.begin("Link Externo")%>
                        <table style="width:100%; table-layout: fixed;">
                          <tr>
                            <td style="width: auto;"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_LINK_EXTERNO, request)%></td>
                          </tr>
                          <tr>
                            <td><%=EntityFormEdit.script(facade, Oportunidade.FIELD_LINK_EXTERNO, oportunidadeInfo, request, 0, "", "")%></td><td style="width:4%;"><%=Button.script(facade, "botaoAbrir", "Abrir", "Abrir", "", "", Button.KIND_DEFAULT, "", "abrirLink()", false)%></td>
                          </tr>
                        </table>
                      <%=GroupBox.end()%>
                    <%} // if%>

                    </td>
                  </tr>
                </table>
              </td>
            </tr>

            <%// se n�o estamos na central de atendimento...
              if (!isCentralAtendimento) {%>
                <tr style="height:15px">
                  <td style="width:100%">
                    <%=GroupBox.begin("Link Externo")%>
                      <table style="width:100%; table-layout: fixed;">
                        <tr>
                          <td style="width: auto;"><%=EntityFieldLabel.script(facade, Oportunidade.FIELD_LINK_EXTERNO, request)%></td>
                        </tr>
                        <tr>
                          <td><%=EntityFormEdit.script(facade, Oportunidade.FIELD_LINK_EXTERNO, oportunidadeInfo, request, 0, "", "")%></td><td style="width:4%;"><%=Button.script(facade, "botaoAbrir", "Abrir", "Abrir", "", "", Button.KIND_DEFAULT, "", "abrirLink()", false)%></td>
                        </tr>
                      </table>
                    <%=GroupBox.end()%>
                  </td>
                </tr>
            <%} // if%>

            <tr style="height:auto;">
              <td style="width:100%;">

                <%=pageControl.begin()%>
                  <!-- Descri��o -->
                  <%=pageControl.beginTabSheet("Descri��o")%>
                    <%=EntityFormMemo.script(facade, Oportunidade.FIELD_DESCRICAO, oportunidadeInfo, request, 0, 0, "", "", false, true)%>
                  <%=pageControl.endTabSheet()%>
                  <!-- Hist�rico -->
                  <%// se estamos inserindo
                    if (Controller.isInserting(request)) {%>
                    <%=pageControl.beginTabSheet("Hist�rico")%>
                    <span>N�o dispon�vel em tempo de inclus�o.</span>
                  <%=pageControl.endTabSheet()%>
                  <%}
                   else {%>
                   <%=pageControl.iFrameTabSheet("Hist�rico", OportunidadeHistorico.ACTION.url(oportunidadeHistorico.USER_PARAM_EMPRESA_ID.getName() + "=" + oportunidadeInfo.getEmpresaId() + "&" + oportunidadeHistorico.USER_PARAM_OPORTUNIDADE_ID.getName() + "=" + oportunidadeInfo.getOportunidadeId()))%>
                 <%} // if%>
                <%=pageControl.end()%>


              </td>
            </tr>

            <%// se estamos na central de atendimento...
              if (isCentralAtendimento) {%>
                <tr style="height:15px">
                  <td style="width:100%">
                    <table cellpadding="0" cellspacing="0">
                      <tr>
                        <td><%=CommandControl.formButton(facade, formData, ImageList.COMMAND_SAVE, "Deseja mesmo salvar o registro?", true, false)%></td>
                        <td>&nbsp;</td>
                        <td><%=CommandControl.entityBrowseButton(facade, oportunidade, Oportunidade.ACTION, ImageList.COMMAND_BACK, "Voltar", "Volta para a consulta.", false)%></td>
                      </tr>
                    </table>
                  </td>
                </tr>
            <%} // if%>

          </table>
        <%=frameBar.endClientArea()%>

      <%=formData.end()%>
    <%if(!isCentralAtendimento) {%>
      <%=frameBar.end()%>
    <%} // if%>

  </body>
</html>

<%}
  // t�rmino do bloco protegido
  catch (Exception e) {
    Controller.forwardException(e, pageContext);
  } // try-catch
%>
