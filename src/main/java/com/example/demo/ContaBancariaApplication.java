package com.example.demo;

import com.example.model.Usuario;
import com.example.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
@EntityScan(basePackages = "com.example.model")
@EnableJpaRepositories(basePackages = "com.example.repository")
@RestController
@RequestMapping("/usuario")
public class ContaBancariaApplication {

	@Autowired
	public UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ContaBancariaApplication.class, args);}



//---------- O código abaixo é para fazer testes e requisições. Recomendado o uso do postman para o teste ----------\\



	@GetMapping
	public List<Usuario> obterUsuarios(){
		return usuarioRepository.findAll();
	}
	@PostMapping
	public void criarUsuario(@RequestBody Usuario usuario){

		usuarioRepository.save(usuario);
	}
	@DeleteMapping("/{id}")
	public void deletarUsuario(@PathVariable UUID id) {
		usuarioRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarUsuario(@PathVariable UUID id,
										   @RequestBody Usuario usuario){
		Optional<Usuario> usuarioExiste = usuarioRepository.findById(id);
		if (usuarioExiste.isPresent()){
			Usuario atualizarUsuario = usuarioExiste.get();

			atualizarUsuario.setNome(usuario.getNome());
			atualizarUsuario.setEmail(usuario.getEmail());
			atualizarUsuario.setSenha(usuario.getSenha());
			usuarioRepository.save(atualizarUsuario);

			return ResponseEntity.ok("Usuario atualizado com sucesso");
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

		}

	}



//---------- O código abaixo é para fazer testes de login, visualizar saldo, trocar senha, etc. ----------\\



	@PostConstruct
	public void executarBanco () {
		Scanner scanner = new Scanner(System.in);

		Usuario usuario = new Usuario(2.500);

		while (true){

			System.out.println("Qual das opções abaixo você deseja executar?" +
					"\n1.Criar usuario no banco" +
					"\n2.Entrar no banco" +
					"\n3.Deletar conta" +
					"\n4.Sair");

			int opcao = scanner.nextInt();

//			-------Referente a Criar usuario-------

			switch (opcao) {
				case 1:
					System.out.println("Digite seu nome:");
					String nome = scanner.next();
					System.out.println("Digite seu email:");
					String email = scanner.next();
					System.out.println("Digite sua senha:");
					String senha = scanner.next();
					System.out.println("Digite seu saldo inicial:");
					double saldo = scanner.nextDouble();

					Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);
					Optional<Usuario> usuarioExistentePorNome = usuarioRepository.findByNome(nome);

					if (usuarioExistente.isPresent()) {
						System.out.println("Este email já está em uso!");
					}else {
						usuario.setNome(nome);
						usuario.setEmail(email);
						usuario.setSenha(senha);
						usuario.setSaldo(saldo);

						usuarioRepository.save(usuario);
					}
					break;

				case 2:

					System.out.println("Informe seu email:");
					String emailLogin = scanner.next();
					System.out.println("Informe sua senha:");
					String senhaLogin = scanner.next();

					Optional<Usuario> usuarioLogado = usuarioRepository.findByEmailAndSenha(emailLogin, senhaLogin);

					if (usuarioLogado.isPresent()) {

						usuario = usuarioLogado.get();

						System.out.println("Bem vindo! " +usuario.getNome());

						while (true) {
							System.out.println("Você entrou na sua conta!");
							System.out.println("Qual das operações seguintes você deseja fazer?" +
									"\n1. Visualizar saldo" +
									"\n2. Sacar" +
									"\n3. Enviar saldo para alguém" +
									"\n4. Trocar informações de usuario" +
									"\n5. Visualizar dados da conta" +
									"\n6. Sair");

//					-------Referente a sacar saldo-------

							int opcao2 = scanner.nextInt();

							switch (opcao2) {

								case 1:
									System.out.println("Seu saldo atual: " + usuario.getSaldo());
									break;
								case 2:
									System.out.println("Quantos você deseja sacar?: ");
									Double valorSaque = scanner.nextDouble();

									if (valorSaque > usuario.getSaldo()) {
										System.out.println("Valor insuficiente!");
									} else {
										usuario.setSaldo(usuario.getSaldo() - valorSaque);
										usuarioRepository.save(usuario);
										System.out.println("Você retirou: R$" + valorSaque + " da sua conta");
									}
									break;
								case 3:
									System.out.println("Digite o email para quem você deseja enviar o saldo:");
									String emailDestino = scanner.next();
									System.out.println("Digite a quantia que deseja enviar:");
									Double valorTransferencia = scanner.nextDouble();

									Optional<Usuario> usuarioDestino = usuarioRepository.findByEmail(emailDestino);

									if (usuarioDestino.isPresent() && valorTransferencia <= usuario.getSaldo()) {

										Usuario destino = usuarioDestino.get();
										destino.setSaldo(destino.getSaldo() + valorTransferencia);
										usuarioRepository.save(destino);
										System.out.println("Dinheiro enviado para " + destino.getNome() + " com sucesso!");

										usuario.setSaldo(usuario.getSaldo() - valorTransferencia);
										usuarioRepository.save(usuario);
										System.out.println("Valor da conta retirado foi R$" + valorTransferencia + "!");
									} else {
										System.out.println("Email errado ou saldo insuficiente!");
									}
								case 4:
									System.out.println("Escolha o que você deseja atualizar: " +
											"\n1. Email" +
											"\n2. Senha" +
											"\n3. Voltar");
									int opcao3 = scanner.nextInt();

//								-------Referente as trocas de informação de usuario-------

									switch (opcao3) {
										case 1:
											System.out.println("Informe seu email: ");
											String emailUser = scanner.next();

											System.out.println("Digite agora para qual email deseja alterar: ");
											String atualizarEmail = scanner.next();

											Optional<Usuario> emailExiste = usuarioRepository.findByEmail(emailUser);
											if (emailExiste.isPresent()) {
												Usuario emailAtualizado = emailExiste.get();
												emailAtualizado.setEmail(atualizarEmail);

												usuarioRepository.save(emailAtualizado);
												break;
											} else {
												System.out.println("Este email não existe");
											}
											break;

										case 2:
											System.out.println("Informe sua senha: ");
											String senhaUser = scanner.next();

											System.out.println("Confirme sua senha: ");
											String senhaUserConfirmar = scanner.next();


											if (senhaUser.equals(senhaUserConfirmar)) {
												System.out.println("Digite agora a senha para alterar: ");
												String atualizarSenha = scanner.next();

												Usuario senhaNova = new Usuario();

												senhaNova.setSenha(atualizarSenha);
												senhaNova.setEmail(usuario.getEmail());
												senhaNova.setNome(usuario.getNome());

												usuarioRepository.save(senhaNova);
												break;
											} else {
												System.out.println("As Senhas não são idêntica");
											}
											break;
										case 3:
											return;
										default:
											System.out.println("Número inserido não existe nos comandos!");

									}
								case 5:
									System.out.println("Os seus dados são: " +
											"\n Nome: " +usuario.getNome() +
											"\n Email: " +usuario.getEmail() +
											"\n Senha: " +usuario.getSenha() +
											"\n Saldo: " +usuario.getSaldo());

								case 6:
									return;
							}
						}
					}else {
						System.out.println("Email ou senha incorretos!");
						break;
					}

//					-------Deletar conta de usuario-------

				case 3:

					System.out.println("Digite o ID para deletar a conta:");
					UUID id = UUID.fromString(scanner.next());

					usuario.setId(id);

					usuarioRepository.delete(usuario);
					break;
				case 4:
					return;
				default:
					System.out.println("Número inválido!");
					}
			}

		}
	}


