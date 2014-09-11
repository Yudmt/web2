function Application () {
    
    /**
     * Ajax
     */
    this.ajax = new AjaxController();
    
    /**
     * Databases
     */
    this.medicodb = new MedicoDB(this);
    this.pacientedb = new PacienteDB(this);
    this.agendamentodb = new AgendamentoDB(this);
    this.atendentedb = new AtendenteDB(this);
    this.planosaudedb = new PlanoSaudeDB(this);
    
    /**
     * Apps
     */
//    this.medicoapp = new MedicoApp(this);
//    this.pacienteapp = new PacienteApp(this);
//    this.agendamentoapp = new AgendamentoApp(this);
//    this.atendenteapp = new AtendenteApp(this);
//    this.planosaudeapp = new PlanoSaudeApp(this);
    
    /**
     * UI
     */
    this.ui = new UI(this);
    
    /**
     * Functions
     */
    
    this.emulateBind = function (f, context) {
        return function () {
            f.apply(context, arguments);
        };
    };
}