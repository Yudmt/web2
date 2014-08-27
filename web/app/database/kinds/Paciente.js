function Paciente () {
    /** @type number */ this.id = null;
    /** @type string */ this.cpf = null;
    /** @type string */ this.nome = null;
    /** @type string */ this.endereco = null;
    /** @type string */ this.telefone = null;
    /** @type string */ this.email = null;
    /** @type PlanoSaude */ this.plano = null;
    /** @type string */ this.numeroPlano = null;
    /** @type string */ this.tipoPlano = null;
    /** @type string */ this.dataCadastro = null;
    
    this.updateFromJSON = function (json) {
        for (var i in json) {
            if (typeof this[i] !== 'undefined') {
                this[i] = json[i];
            }
        }
    };
}