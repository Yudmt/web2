function Agendamento () {
    /** @type number */ this.id = null;
    /** @type Paciente */ this.paciente = null;
    /** @type String */ this.anamnese = null;
    /** @type String */ this.estadoPaciente = null;
    /** @type String */ this.dataAgendamento = null;
    /** @type Atendente */ this.atendente = null;
    /** @type number */ this.situacao = null;
    /** @type Medico */ this.medico = null;
    
    this.updateFromJSON = function (json) {
        for (var i in json) {
            if (typeof this[i] !== 'undefined') {
                this[i] = json[i];
            }
        }
    };
}