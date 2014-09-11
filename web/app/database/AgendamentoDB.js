function AgendamentoDB (app) {
    /** @type {Object.<Number, Agendamento>} */ this.agendamentos = {};
    /** @type {Array.<Agendamento>} */ this.agendamentosOrdered = [];
    
    /** @type Application */ this.app = app;
    
    this.updateFromJSON = function (json, clean) {
        if (typeof json['Agendamentos'] !== 'undefined') {
            if (clean) {
                this.agendamentos = {};
                this.agendamentosOrdered = [];
            }
            for (var i = 0; i < json['Agendamentos'].length; i++) {
                if (typeof this.agendamentos[json['Agendamentos'][i]['id']] === undefined) {
                    this.agendamentos[json['Agendamentos'][i]['id']] = new Agendamento();
                }
                this.agendamentos[json['Agendamentos'][i]['id']].updateFromJSON(json['Agendamentos'][i]);
                this.agendamentosOrdered.push(this.agendamentos[json['Agendamentos'][i]['id']]);
            }
            this.agendamentosOrdered.sort(function (a, b) {
                var na = a.nome.toUpperCase();
                var nb = b.nome.toUpperCase();
                if (na < nb) {
                    return -1;
                }
                if (na > nb) {
                    return 1;
                }
                return 0;
            });
        }
    };
    
    /**
     * 
     * @param {Agendamento} agendamento
     * @returns {Agendamento}
     */
    this.addAgendamento = function (agendamento) {
        if (typeof this.pacientes[agendamento.id] === 'undefined') {
            this.agendamentos[agendamento.id] = agendamento;
        }
        return this.agendamentos[agendamento.id];
    };
    
    /**
     * 
     * @param {number} id
     * @returns {Agendamento}
     */
    this.getAgendamento = function (id) {
        if (typeof this.agendamentos[id] !== 'undefined') {
            return this.agendamentos[id];
        }
        return null;
    };
}