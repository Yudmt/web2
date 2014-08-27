function PlanoSaude () {
    /** @type number */ this.id = null;
    /** @type String */ this.nome = null;
    /** @type String */ this.cnpj = null;
    /** @type String */ this.endereco = null;
    /** @type String */ this.contato = null;
    /** @type String */ this.telefone = null;
    /** @type String */ this.email = null;
    
    this.updateFromJSON = function (json) {
        for (var i in json) {
            if (typeof this[i] !== 'undefined') {
                this[i] = json[i];
            }
        }
    };
}