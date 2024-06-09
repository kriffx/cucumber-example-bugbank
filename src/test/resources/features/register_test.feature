#language: pt
@registrar_page
Funcionalidade: BugBank cadastro teste
  Cenário: Faço cadastro no BugBank - Forma 1
    Dado Navego para "https://bugbank.netlify.app/"
    Quando Clico no botão "Registrar"
    Então Preencho "user@example.com" no campo de texto "E-mail"
    E Preencho "User Example" no campo de texto "Nome"
    E Preencho "secret" no campo de texto "Senha"
    E Preencho "secret" no campo de texto "Confirmar senha"
    Então Clico no elemento "Conta com saldo"
    E Clico no botão "Cadastrar"
    E Armazeno o número da conta