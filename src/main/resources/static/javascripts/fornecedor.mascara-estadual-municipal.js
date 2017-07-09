var Cofipa = Cofipa || {};

Cofipa.MascaraEstadualMunicipal = (function() {
	
	function MascaraEstadualMunicipal() {
		this.radioTipoInscricao = $('.js-radio-tipo-inscricao');
		this.labelInscEstadualMunicipal = $('[for=inscEstadualMunicipal]');
		this.inputInscEstadualMunicipal = $('#inscEstadualMunicipal');
	}
	
	MascaraEstadualMunicipal.prototype.iniciar = function() {
		this.radioTipoInscricao.on('change', onTipoInscricaoAlterado.bind(this));
		var tipoInscricaoSelecionada = this.radioTipoInscricao.filter(':checked')[0];
		if (tipoInscricaoSelecionada) {
			aplicarMascara.call(this, $(tipoInscricaoSelecionada));
		}
	}
	
	function onTipoInscricaoAlterado(evento) {
		var tipoInscricaoSelecionada = $(evento.currentTarget);
		aplicarMascara.call(this, tipoInscricaoSelecionada);
		this.inputInscEstadualMunicipal.val('');
	}
	
	function aplicarMascara(tipoInscricaoSelecionada) {
		this.labelInscEstadualMunicipal.text(tipoInscricaoSelecionada.data('documento'));
		this.inputInscEstadualMunicipal.mask(tipoInscricaoSelecionada.data('mascara'));
		this.inputInscEstadualMunicipal.removeAttr('disabled');
	}
	
	return MascaraEstadualMunicipal;
	
}());

$(function() {
	var mascaraEstadualMunicipal = new Cofipa.MascaraEstadualMunicipal();
	mascaraEstadualMunicipal.iniciar();
});