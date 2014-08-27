function Atendente () {
    /** @type number */ this.id = null;
    /** @type string */ this.cpf = null;
    /** @type string */ this.nome = null;
    /** @type string */ this.endereco = null;
    /** @type string */ this.telefone = null;
    /** @type string */ this.email = null;
    
    this.updateFromJSON = function (json) {
        for (var i in json) {
            if (typeof this[i] !== 'undefined') {
                this[i] = json[i];
            }
        }
    };
}