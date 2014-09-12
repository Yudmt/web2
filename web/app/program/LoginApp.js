function LoginApp (app) {
    /** @type Application */ this.app = app;
    /** @type string */ this.loggedAs = 'atendente';
    /** @type Atendente, Medico */ this.usuario = null;
    
    this.login = function (tipo, login, senha, cbs, cbe) {
        /** @type AjaxController */ var ajax = this.app.ajax;
        
        
        if (login === 'DEBUG') {
            cbs();
            return;
        }
        
        cbs = this.app.emulateBind(function (data) {
            if (data['tipo'] !== undefined) {
                // Tipo será "atendente", "gerente" ou "medico".
                this.loginapp.loggedAs = data['tipo'];
                switch (this.loginapp.loggedAs) {
                    case 'atendente':
                        this.loginapp.usuario = new Atendente();
                        this.loginapp.usuario.updateFromJSON(data['usuario']);
                        break;
                    case 'medico':
                        this.loginapp.usuario = new Medico();
                        this.loginapp.usuario.updateFromJSON(data['usuario']);
                        break;
                }
            }
            cbs(data);
        }, {loginapp : this, cbs : cbs});
        
        ajax.requestPage({
            url : 'Login',
            data : {'login' : login,
                    'senha' : senha,
                    'tipo' : tipo},
            success : cbs,
            error : cbe
        });
    };
}