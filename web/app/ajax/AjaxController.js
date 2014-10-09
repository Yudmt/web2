function AjaxController () {
    this.requestPage = function (object) {
        console.log("Ajax request for: " + object.url);
        
        if (typeof object.data !== 'undefined') {
            object.type = 'POST';
            for (var i in object.data) {
                // Transformar objetos em algo que o servidor Java entende
                if (typeof object.data[i] === 'object' || typeof object.data[i] === 'array') {
                    object.data[i] = JSON.stringify(object.data[i]);
                }
                // Não incluir campos nulos
                if (object.data[i] === null) {
                    delete object.data[i];
                }
            }
            console.log("Ajax request includes data:");
            console.log(object.data);
        }
        
        if (typeof object.timeout === 'undefined') {
            object.timeout = 30000;
        }
        
        
        $.ajax(object).done(function( data ) {
            if (typeof data === 'string')
                console.log( "Pedido Ajax bem sucedido. Slice de dados recebidos: ", data.slice( 0, 100 ) );
            else if (typeof data === 'object') {
                console.log("Pedido Ajax bem sucedido. Objeto recebido:");
                console.log(data);
            }
        }).error(function (data) {
            console.log("Pedido Ajax resultou em erro. Dados:");
            console.log(data);
            
            // Não autorizado, checar se login ainda é válido
            if (data.status === 401) {
                //window.app.loginapp.checkLogin();
            }
        });
    };
    
    this.jsonFromHTML = function ($html) {
        var $codes = $html.children('code');
        
        var json = {};
        
        var $code;
        for (var i = 0; i < $codes.length; i++) {
            $code = $($codes[i]);
            if ($code.children('code').length !== 0) {
                json[$code.attr('data-id')] = this.jsonFromHTML($code);
            } else {
                json[$code.attr('data-id')] = $code.text();
            }
        }
        
        return json;
    };
}