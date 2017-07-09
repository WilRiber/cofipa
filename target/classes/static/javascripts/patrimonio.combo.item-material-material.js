var Cofipa = Cofipa || {};

Cofipa.ComboItemMaterialInformatica = (function() {
	
	function ComboItemMaterialInformatica() {
		this.combo = $('#itemMaterialInformatica');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboItemMaterialInformatica.prototype.iniciar = function() {
		this.combo.on('change', onItemMaterialInformaticaAlterado.bind(this));
	}
	
	function onItemMaterialInformaticaAlterado() {
		console.log('itemMaterialInformatica', this.combo.val());
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboItemMaterialInformatica;
	
}());

Cofipa.ComboMaterialInformatica = (function() {
	
	function ComboMaterialInformatica(comboItemMaterialInformatica) {
		this.comboItemMaterialInformatica = comboItemMaterialInformatica;
		this.combo = $('#materialInformatica');
		this.imgLoading = $('.js-img-loading');
		this.inputHiddenMaterialInformaticaSelecionado = $('#inputHiddenMaterialInformaticaSelecionado');
	}
	
	ComboMaterialInformatica.prototype.iniciar = function() {
		reset.call(this);
		this.comboItemMaterialInformatica.on('alterado', onItemMaterialInformaticaAlterado.bind(this));
		var codigoItemMaterialInformatica = this.comboItemMaterialInformatica.combo.val();
		inicializarMateriaisInformatica.call(this, codigoItemMaterialInformatica);
	}
	
	function onItemMaterialInformaticaAlterado(evento, codigoItemMaterialInformatica) {
	   console.log('codigo do ItemMaterial no combo material', codigoItemMaterialInformatica);
		this.inputHiddenMaterialInformaticaSelecionado.val('');
		inicializarMateriaisInformatica.call(this, codigoItemMaterialInformatica);
	}
	
	function inicializarMateriaisInformatica(codigoItemMaterialInformatica) {
		if (codigoItemMaterialInformatica) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'itemMaterialInformatica': codigoItemMaterialInformatica }, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarMateriaisInformaticaFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarMateriaisInformaticaFinalizado(materiaisInformatica) {
		var options = [];
		materiaisInformatica.forEach(function(materialInformatica) {
			console.log('materialInformatica', materialInformatica)
			options.push('<option value="' + materialInformatica.codigo + '">' + materialInformatica.descricao + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
		var codigoMaterialInformaticaSelecionado = this.inputHiddenMaterialInformaticaSelecionado.val();
		if (codigoMaterialInformaticaSelecionado) {
			this.combo.val(codigoMaterialInformaticaSelecionado);
		}
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione o tipo equipamento</option>');
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
	
	return ComboMaterialInformatica;
	
}());

$(function() {
	
	var comboItemMaterialInformatica = new Cofipa.ComboItemMaterialInformatica();
	comboItemMaterialInformatica.iniciar();
	
	var comboMaterialInformatica = new Cofipa.ComboMaterialInformatica(comboItemMaterialInformatica);
	comboMaterialInformatica.iniciar();
	
});