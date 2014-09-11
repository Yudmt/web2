function AtendenteDB (app) {
    /** @type {Object.<Number, Atendente>} */ this.atendentes = {};
    /** @type {Array.<Atendente>} */ this.atendentesOrdered = [];
    
    /** @type Application */ this.app = app;
    
    this.updateFromJSON = function (json, clean) {
        if (typeof json['Atendentes'] !== 'undefined') {
            if (clean) {
                this.atendentes = {};
                this.atendentesOrdered = [];
            }
            for (var i = 0; i < json['Atendentes'].length; i++) {
                if (typeof this.atendentes[json['Atendentes'][i]['id']] === undefined) {
                    this.atendentes[json['Atendentes'][i]['id']] = new Atendente();
                }
                this.atendentes[json['Atendentes'][i]['id']].updateFromJSON(json['Atendentes'][i]);
                this.atendentesOrdered.push(this.atendentes[json['Atendentes'][i]['id']]);
            }
            this.atendentesOrdered.sort(function (a, b) {
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
     * @param {Atendente} atendente
     * @returns {Atendente}
     */
    this.addAtendente = function (atendente) {
        if (typeof this.pacientes[atendente.id] === 'undefined') {
            this.atendentes[atendente.id] = atendente;
        }
        return this.atendentes[atendente.id];
    };
    
    /**
     * 
     * @param {number} id
     * @returns {Atendente}
     */
    this.getAtendente = function (id) {
        if (typeof this.atendentes[id] !== 'undefined') {
            return this.atendentes[id];
        }
        return null;
    };
}