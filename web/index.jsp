<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>SUHCK</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/Geral.css" />        
        <link rel="stylesheet" href="css/Login.css" />
        <link rel="stylesheet" href="css/Paginas.css" />

    </head>
    <body>
        <div id="loadingDiv">
            <a></a>
        </div>
        <div id="loginWindow">
            <form id="loginForm">
                <p id="loginError"></p>
                <input id="lfLogin" type="text" placeholder="Login" value="DEBUG" />
                <input id="lfPassword" type="password" placeholder="Senha" />
                <select id="lfTipo">
                    <option>Atendente</option>
                    <option>Medico</option>
                    <option>Gerente</option>
                </select>
                <input id="lfSubmit" type="submit" value="Login" />
            </form>
        </div>
        <div id='menu'>
            <a onclick="openPage('paciente'); return false" href="#">Pacientes</a>
            <a onclick="openPage('paciente'); return false" href="#">Agendamentos</a>
            <a onclick="openPage('paciente'); return false" href="#">Atendimentos</a>
            <a onclick="openPage('paciente'); return false" href="#">Médicos</a>
            <a onclick="openPage('paciente'); return false" href="#">Atendentes</a>
            <a onclick="openPage('paciente'); return false" href="#">Relatórios</a>
        </div>
        <div id='conteudo'>
            <!-- Paciente -->
                <h1 class="paciente header">Pacientes</h1>
                
                <p class="paciente paragrafo">
                    <a class="textLink" onclick="openPage('pacienteForm')">Formulário de Paciente</a>
                </p>
                <p class="paciente paragrafo">
                    <a class="textLink" onclick="openPage('pacienteConsulta')">Consulta de Pacientes</a>
                </p>
                <p class="paciente paragrafo">
                    <a class="textLink" onclick="openPage('pacienteAgendamentoForm')">Formulário de Agendamento</a>
                </p>
                <p class="paciente paragrafo">
                    <a class="textLink" onclick="openPage('pacienteAgendamento')">Consulta de Agendamentos</a>
                </p>
            <!-- End -->
            <!-- Paciente Form -->
                <h1 class="pacienteForm header">Paciente</h1>
                <form id="pacienteForm" class="pacienteForm">
                    
                </form>
            <!-- End Paciente Form -->
            <!-- Paciente Consulta -->
                <h1 class="pacienteConsulta header">Pacientes</h1>
                <form class="pacienteConsulta">
                    <input type="text" placeholder="Filtrar por Nome de Paciente" />
                </form>
                <p class="pacienteConsulta textLink paragrafo" onclick="openPage('pacienteForm')">João Antônio da Silva Santos Melo de Souza Pinto</p>
                <p class="pacienteConsulta textLink paragrafo" onclick="openPage('pacienteForm')">Pedro Pedro</p>
            <!-- End Paciente Consulta -->
            <!-- Agendamento Form -->
                <h1 class="pacienteAgendamentoForm header">Agendamento</h1>
                <form id="pacienteAgendamentoForm" class="pacienteAgendamentoForm">
                    
                </form>
            <!-- End Agendamento Form -->
            <!-- Agendamentos Consulta -->
                <h1 class="pacienteAgendamento header">Agendamentos</h1>
                <form class="pacienteAgendamento">
                    <input type="text" placeholder="Filtrar por Nome de Paciente" />
                    <input type="text" placeholder="Filtro Data de início" />
                    <input type="text" placeholder="Filtro Data de fim" />
                </form>
                <p class="pacienteAgendamento textLink paragrafo" onclick="openPage('pacienteAgendamentoForm')">26/10/2014 - 19:00 - João Antônio da Silva Santos Melo de Souza Pinto</p>
            <!-- End Agendamentos Consulta -->
        </div>
        <!-- Libraries -->
            <script src="js/jquery-2.1.1.min.js"></script>
            <script src="js/jquery-ui.min.js"></script>
        <!-- Libraries -->
        <!-- Javascript includes -->
            <script src="app/Application.js"></script>
            <script src="app/ajax/AjaxController.js"></script>
            <script src="app/database/AgendamentoDB.js"></script>
            <script src="app/database/AtendenteDB.js"></script>
            <script src="app/database/MedicoDB.js"></script>
            <script src="app/database/PacienteDB.js"></script>
            <script src="app/database/PlanoSaudeDB.js"></script>
            <script src="app/database/kinds/Agendamento.js"></script>
            <script src="app/database/kinds/Atendente.js"></script>
            <script src="app/database/kinds/Medico.js"></script>
            <script src="app/database/kinds/Paciente.js"></script>
            <script src="app/database/kinds/PlanoSaude.js"></script>
            <script src="app/database/kinds/Usuario.js"></script>
            <script src="app/ui/UI.js"></script>
            <script src="app/ui/medico/MedicoUI.js"></script>
            <script src="app/ui/login/LoginUI.js"></script>
            <script src="app/program/LoginApp.js"></script>
            <script src="app/program/MedicoApp.js"></script>
        <!-- Javascript Includes -->
        <script type="text/javascript">
            window.app = new Application();
            
            var paciente = new Paciente();
            for (var id in paciente) {
                if (typeof paciente[id] !== 'function' && id !== 'id') {
                    $('#pacienteForm').append($("<input type='text' />").attr('placeholder', id));
                }
            }
            $('#pacienteForm').append('<input type="submit" value="Enviar" />').on('submit', function (e) {
                e.preventDefault();
                $('#pacienteForm').children('input[type=text]').val('');
                window.openPage('paciente');
            });
            
            var agendamento = new Agendamento();
            for (var id in agendamento) {
                if (typeof agendamento[id] !== 'function' && id !== 'id') {
                    $('#pacienteAgendamentoForm').append($("<input type='text' />").attr('placeholder', id));
                }
            }
            $('#pacienteAgendamentoForm').append('<input type="submit" value="Enviar" />').on('submit', function (e) {
                e.preventDefault();
                $('#pacienteAgendamentoForm').children('input[type=text]').val('');
                window.openPage('paciente');
            });
            
            window.openPage = function (id) {
                var $conteudo = $('#conteudo');
                $conteudo.children().hide();
                $conteudo.children('.' + id).show();
            };
            
            window.openPage('asd');
        </script>
    </body>
</html>
