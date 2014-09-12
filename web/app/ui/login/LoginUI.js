function LoginUI (ui) {
    /** @type UI */ this.ui = ui;
    
    /**
     * Elements
     */
    this.$loginWindow = $('#loginWindow');
    this.$loginForm = $('#loginForm');
    this.$loginInput = $('#lfLogin');
    this.$senhaInput = $('#lfPassword');
    this.$tipoInput = $('#lfTipo');
    this.$submit = $('#lfSubmit');
    this.$error = $('#loginError').empty().hide();
    
    
    /**
     * Bindings
     */
    
    this.$loginForm.on("submit", function (e) {
        window.app.ui.loginui.login(e);
    });
    
    
    /**
     * Functions
     */
    
    /**
     * 
     * @param {Event} event
     * @returns {undefined}
     */
    this.login = function (event) {
        event.preventDefault();
        
        var login = this.$loginInput.val();
        var senha = this.$senhaInput.val();
        var tipo = this.$tipoInput.val();
        
        var cbs = function (data) {
            window.app.ui.unblockScreen();
            window.app.ui.loginui.$loginWindow.stop(true, true).fadeOut();
            window.app.ui.loginui.$error.empty().hide();
            window.app.ui.loginui.resizeLoginForm();
            window.app.ui.showUI();
        };
        
        var cbe = function (data) {
            window.app.ui.unblockScreen();
            var erro;
            switch (data.status) {
                case 404:
                    erro = "Credenciais inválidas. Tente novamente.";
                    break;
                case 403:
                    erro = "Conta bloqueada.";
                    break;
                case 400:
                    erro = "Formulário inválido. Tente novamente.";
                    break;
                case 401:
                    erro = "Entrada não autorizada.";
                    break;
                case 500:
                    erro = "Houve um erro ao processar o login. Tente novamente.";
                    break;
                default:
                    erro = "Não foi possível completar o login. Tente novamente.";
            }
            window.app.ui.loginui.$error.text(erro).stop(true, true).fadeIn();
            window.app.ui.loginui.resizeLoginForm();
        };
        
        window.app.ui.blockScreen();
        
        this.ui.app.loginapp.login(tipo, login, senha, cbs, cbe);
    };
    
    
    this.resizeLoginForm = function () {
        var width = parseInt(this.$loginForm.width() / 2);
        var height = parseInt(this.$loginForm.height() / 2);
        this.$loginForm.css('margin-left', '-' + width + 'px');
        this.$loginForm.css('margin-top', '-' + height + 'px');
    };
    
    this.resizeLoginForm();
    this.$loginInput.focus();
}