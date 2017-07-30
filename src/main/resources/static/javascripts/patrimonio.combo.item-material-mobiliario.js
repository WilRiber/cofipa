var Cofipa = Cofipa || {};

Cofipa.ComboItemMaterialMobiliario = (function() {
	
	function ComboItemMaterialMobiliario() {
		this.combo = $('#itemMaterialMobiliario');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboItemMaterialMobiliario.prototype.iniciar = function() {
		this.combo.on('change', onItemMaterialMobiliarioAlterado.bind(this));
	}
	
	function onItemMaterialMobiliarioAlterado() {
		console.log('itemMaterialMobiliario', this.combo.val());
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboItemMaterialMobiliario;
	
}());

Cofipa.ComboMaterialMobiliario = (function() {
	
	function ComboMaterialMobiliario(comboItemMaterialMobiliario) {
		this.comboItemMaterialMobiliario = comboItemMaterialMobiliario;
		this.combo = $('#materialMobiliario');
		this.imgLoading = $('.js-img-loading');
		this.inputHiddenMaterialMobiliarioSelecionado = $('#inputHiddenMaterialMobiliarioSelecionado');
	}
	
	ComboMaterialMobiliario.prototype.iniciar = function() {
		reset.call(this);
		this.comboItemMaterialMobiliario.on('alterado', onItemMaterialMobiliarioAlterado.bind(this));
		var codigoItemMaterialMobiliario = this.comboItemMaterialMobiliario.combo.val();
		inicializarMateriaisMobiliario.call(this, codigoItemMaterialMobiliario);
	}
	
	function onItemMaterialMobiliarioAlterado(evento, codigoItemMaterialMobiliario) {
	   console.log('codigo do ItemMaterial no combo material', codigoItemMaterialMobiliario);
		this.inputHiddenMaterialMobiliarioSelecionado.val('');
		inicializarMateriaisMobiliario.call(this, codigoItemMaterialMobiliario);
	}
	
	function inicializarMateriaisMobiliario(codigoItemMaterialMobiliario) {
		if (codigoItemMaterialMobiliario) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'itemMaterialMobiliario': codigoItemMaterialMobiliario }, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarMateriaisMobiliarioFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarMateriaisMobiliarioFinalizado(materiaisMobiliario) {
		var options = [];
		materiaisMobiliario.forEach(function(materialMobiliario) {
			console.log('materialMobiliario', materialMobiliario)
			options.push('<option value="' + materialMobiliario.codigo + '">' + materialMobiliario.descricao + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
		var codigoMaterialMobiliarioSelecionado = this.inputHiddenMaterialMobiliarioSelecionado.val();
		if (codigoMaterialMobiliarioSelecionado) {
			this.combo.val(codigoMaterialMobiliarioSelecionado);
		}
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione o tipo material</option>');
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
	
	return ComboMaterialMobiliario;
	
}());

$(function() {
	
	var comboItemMaterialMobiliario = new Cofipa.ComboItemMaterialMobiliario();
	comboItemMaterialMobiliario.iniciar();
	
	var comboMaterialMobiliario = new Cofipa.ComboMaterialMobiliario(comboItemMaterialMobiliario);
	comboMaterialMobiliario.iniciar();
	
});