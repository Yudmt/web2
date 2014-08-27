function PacienteDB () {
    /** @type {Object.<Number, Paciente>} */ this.pacientes = {};
    /** @type {Array.<Paciente>} */ this.pacientesOrdered = [];
    
    this.updateFromJSON = function (json, clean) {
        if (typeof json['Pacientes'] !== 'undefined') {
            if (clean) {
                this.pacientes = {};
                this.pacientesOrdered = [];
            }
            for (var i = 0; i < json['Pacientes'].length; i++) {
                if (typeof this.pacientes[json['Pacientes'][i]['id']] === undefined) {
                    this.pacientes[json['Pacientes'][i]['id']] = new Paciente();
                }
                this.pacientes[json['Pacientes'][i]['id']].updateFromJSON(json['Pacientes'][i]);
                this.pacientesOrdered.push(this.pacientes[json['Pacientes'][i]['id']]);
            }
            this.pacientesOrdered.sort(function (a, b) {
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
     * @param {Paciente} paciente
     * @returns {Paciente}
     */
    this.addPaciente = function (paciente) {
        if (typeof this.pacientes[paciente.id] === 'undefined') {
            this.pacientes[paciente.id] = paciente;
        }
        return this.pacientes[paciente.id];
    };
    
    /**
     * 
     * @param {number} id
     * @returns {Paciente}
     */
    this.getPaciente = function (id) {
        if (typeof this.pacientes[id] !== 'undefined') {
            return this.pacientes[id];
        }
        return null;
    };
    
    
}