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
    
    this.unblockScreen();
}