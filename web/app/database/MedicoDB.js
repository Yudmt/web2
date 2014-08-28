function MedicoDB () {
    /** @type {Object.<Number, Medico>} */ this.medicos = {};
    /** @type {Array.<Medico>} */ this.medicoOrdered = [];
    
    this.updateFromJSON = function (json, clean) {
        if (typeof json['Medicos'] !== 'undefined') {
            if (clean) {
                this.medicos = {};
                this.medcisoOrdered = [];
            }
            for (var i = 0; i < json['Medicos'].length; i++) {
                if (typeof this.medicos[json['Medicos'][i]['id']] === undefined) {
                    this.medicos[json['Medicos'][i]['id']] = new Medico();
                }
                this.medicos[json['Medicos'][i]['id']].updateFromJSON(json['Medicos'][i]);
                this.medicosOrdered.push(this.medicos[json['Medicos'][i]['id']]);
            }
            this.medicosOrdered.sort(function (a, b) {
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
     * @param {Medico} medico
     * @returns {Medico}
     */
    this.addPaciente = function (medico) {
        if (typeof this.pacientes[medico.id] === 'undefined') {
            this.medicos[medico.id] = medico;
        }
        return this.medicos[medico.id];
    };
    
    /**
     * 
     * @param {number} id
     * @returns {Medico}
     */
    this.getPaciente = function (id) {
        if (typeof this.medicos[id] !== 'undefined') {
            return this.medicos[id];
        }
        return null;
    };
    
    
}