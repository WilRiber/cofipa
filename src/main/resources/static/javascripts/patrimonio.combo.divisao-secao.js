var Cofipa = Cofipa|| {};

Cofipa.ComboDivisao = (function(){
	var comboDivisao = $('#divisao');
	console.log( comboDivisao, divisao)
}());

Cofipa.ComboDepartamento = (function() {
	
	function ComboDepartamento() {
		this.combo = $('#departamento');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboDepartamento.prototype.iniciar = function() {
		this.combo.on('change', onDepartamentoAlterado.bind(this));
	}
	
	function onDepartamentoAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboDepartamento;
	
}());

Cofipa.ComboDivisao = (function() {
	
	function ComboDivisao(comboDepartamento) {
		this.comboDepartamento = comboDepartamento;
		this.combo = $('#divisao');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
		this.imgLoading = $('.js-img-loading');
	}
	
	ComboDivisao.prototype.iniciar = function() {
		reset.call(this);
		this.comboDepartamento.on('alterado', onDepartamentoAlterado.bind(this));
		//reset.call(this);
		this.combo.on('change', onDivisaoAlterada.bind(this));
	}
	
	
	function onDivisaoAlterada() {
		this.emitter.trigger('alterada', this.combo.val());
	}
	
	function onDepartamentoAlterado(evento, codigoDepartamento) {
		console.log('departamento selecionado', codigoDepartamento)
		console.log('this', this);
		if (codigoDepartamento) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'departamento': codigoDepartamento}, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarDivisoesFinalizada.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarDivisoesFinalizada(divisoes) {
		var options = [];
		divisoes.forEach(function(divisao) {
			console.log('divisoes', divisao)
			options.push('<option value"' + divisao.codigo + '">' + divisao.nome + '</option>');
			
		});
		
	
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
	
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione a divisao</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	
	
	}
	
	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizarRequisicao() {
		this.imgLoading.hide();
	}
	
	
	return ComboDivisao;
	
	
}());


Cofipa.ComboSecao = (function() {
	
	function ComboSecao(comboDivisao) {
		console.log('secao', comboDivisao)
		console.log('this', this);		
		this.comboDivisao = comboDivisao;
		this.combo = $('#secao');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
		this.imgLoading = $('.js-img-loading');
	}
	
	ComboSecao.prototype.iniciar = function() {
		reset.call(this);
		//eventEmitter.on('alterado', onDivisaoAlterada.bind(this));
		this.comboDivisao.on('alterada', onDivisaoAlterada.bind(this));
	}
	
	function onDivisaoAlterada(evento, codigoDivisao) {
		console.log('divisao selecionado', codigoDivisao)
		console.log('this', this);
		if (codigoDivisao) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'divisao': codigoDivisao}, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarSecoesFinalizada.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarSecoesFinalizada(secoes) {
		var options = [];
		secoes.forEach(function(secao) {
			console.log('secoes', secao)
			options.push('<option value"' + secao.codigo + '">' + secao.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione a secao</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
		
	}
	
	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizarRequisicao() {
		this.imgLoading.hide();
	}
	
	return ComboSecao;

	
	
}());


$(function() {
	
	var comboDepartamento = new Cofipa.ComboDepartamento();
	comboDepartamento.iniciar();
	
	var comboDivisao = new Cofipa.ComboDivisao(comboDepartamento);
	comboDivisao.iniciar();
	
	var comboSecao = new Cofipa.ComboSecao(comboDivisao);
	comboSecao.iniciar();
	
});