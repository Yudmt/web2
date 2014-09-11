function LoginUI (ui) {
    /** @type UI */ this.ui = ui;
    
    /**
     * Elements
     */
    
    this.$loginForm = $('#loginForm');
    this.$loginInput = $('#loginInput');
    this.$senhaInput = $('#senhaInput');
    this.$tipoInput = $('#tipoInput');
    
    
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
            // Success!
        };
        
        var cbe = function (data) {
            // Print error
        };
        
        this.ui.app.loginapp.login(tipo, login, senha, cbs, cbe);
    };
    
    //$('#menu').css('margin-top', '-' + ($('#menu').height()/2) + 'px');
}