function UI (app) {
    this.app = app;
    
    /**
     * Children
     */
    this.medicoui = new MedicoUI(this);
    this.loginui = new LoginUI(this);
    
    /**
     * Elements
     */
    this.$loading = $('#loadingDiv');
    this.$menu = $('#menu');
    this.$conteudo = $('#conteudo');
    
    /**
     * Variables
     */
    this.blockCount = 1;
    
    /**
     * Functions
     */
    this.blockScreen = function () {
        if (++this.blockCount > 0) {
            this.$loading.stop(true, true).fadeIn();
        }
    };
    
    this.unblockScreen = function () {
        if (--this.blockCount < 1) {
            this.$loading.stop(true,true).fadeOut();
        }
    };
    
    this.hideUI = function () {
        this.$menu.hide();
        this.$conteudo.hide();
    };
    
    this.showUI = function () {
        this.$menu.stop(true,true).fadeIn();
        this.$conteudo.stop(true,true).fadeIn();
    };
    
    this.resizeMenu = function () {
        var height = this.$menu.height();
        height = height/2;
        height = parseInt(height);
        this.$menu.css("margin-top", "-" + height + "px");
    };
    
    this.resizeMenu();
    this.hideUI();
    
    this.unblockScreen();
}