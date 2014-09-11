function PlanoSaudeDB (app) {
    /** @type {Object.<Number, PlanoSaude>} */ this.planosaudes = {};
    /** @type {Array.<PlanoSaude>} */ this.planosaudesOrdered = [];
    
    /** @type Application */ this.app = app;
    
    this.updateFromJSON = function (json, clean) {
        if (typeof json['PlanoSaudes'] !== 'undefined') {
            if (clean) {
                this.planosaudes = {};
                this.planosaudesOrdered = [];
            }
            for (var i = 0; i < json['PlanoSaudes'].length; i++) {
                if (typeof this.planosaudes[json['PlanoSaudes'][i]['id']] === undefined) {
                    this.planosaudes[json['PlanoSaudes'][i]['id']] = new PlanoSaude();
                }
                this.planosaudes[json['PlanoSaudes'][i]['id']].updateFromJSON(json['PlanoSaudes'][i]);
                this.planosaudesOrdered.push(this.planosaudes[json['PlanoSaudes'][i]['id']]);
            }
            this.planosaudesOrdered.sort(function (a, b) {
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
     * @param {PlanoSaude} plano
     * @returns {PlanoSaude}
     */
    this.addPlanoSaude = function (plano) {
        if (typeof this.pacientes[plano.id] === 'undefined') {
            this.planosaudes[plano.id] = plano;
        }
        return this.planosaudes[plano.id];
    };
    
    /**
     * 
     * @param {number} id
     * @returns {PlanoSaude}
     */
    this.getPlanoSaude = function (id) {
        if (typeof this.planosaudes[id] !== 'undefined') {
            return this.planosaudes[id];
        }
        return null;
    };
}