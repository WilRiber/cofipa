var Cofipa = Cofipa || {};

Cofipa.FornecedorCadastroRapido = (function(){
	
	function FornecedorCadastroRapido(){
		this.modal = $('#modalCadastroRapidoFornecedor');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-fornecedor-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeFornecedor = $('#nomeFornecedor');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-fornecedor');
	}
	
	FornecedorCadastroRapido.prototype.iniciar = function(){
		this.form.on('submit', function(event) { event.preventDefault() });	
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('shown.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	

	function onModalShow() {
		this.inputNomeFornecedor.focus();
		
	}
	
	function onModalClose(){
		this.inputNomeFornecedor.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeFornecedor = this.inputNomeFornecedor.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType:'application/json',
			data: JSON.stringify({nome : nomeFornecedor}),
			error: onErroSalvandoFornecedor.bind(this),
			success: onFornecedorSalvo.bind(this)
		});
	}
	
	function onErroSalvandoFornecedor(obj){
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onFornecedorSalvo(fornecedor) {
		var comboFornecedor = $('#fornecedor');
		comboFornecedor.append('<option value=' + fornecedor.codigo + '>' + fornecedor.nome + '</option>');
		comboFornecedor.val(fornecedor.codigo);
		this.modal.modal('hide');
	}
	
	return FornecedorCadastroRapido;
	
}());

$(function() {
	
	var fornecedorCadastroRapido = new Cofipa.FornecedorCadastroRapido();
	fornecedorCadastroRapido.iniciar();
	
});

