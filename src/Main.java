	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;
	
	public class Main {
	
		public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Cliente> clientes = new ArrayList<>();
		List<Conta> contas = new ArrayList<>();

		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println("Selecione uma opção:");
			System.out.println("1 - Criar conta corrente");
			System.out.println("2 - Criar conta poupança");
			System.out.println("3 - Depositar");
			System.out.println("4 - Sacar");
			System.out.println("5 - Transferir");
			System.out.println("6 - Imprimir Extrato");
			System.out.println("7 - Lista de contas do banco");
			System.out.println("8 - Sair");
			System.out.print("Opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer

			switch (opcao) {
				case 1: // Criar conta corrente
					System.out.print("Nome do cliente: ");
					String nomeCC = scanner.nextLine();
					Cliente clienteCC = new Cliente(nomeCC);
					clientes.add(clienteCC);
					Conta cc = new ContaCorrente(clienteCC);
					contas.add(cc);
					System.out.println("Conta corrente criada. Número: " + cc.getNumero());
					break;
				case 2: // Criar conta poupança
					System.out.print("Nome do cliente: ");
					String nomeCP = scanner.nextLine();
					Cliente clienteCP = new Cliente(nomeCP);
					clientes.add(clienteCP);
					Conta cp = new ContaPoupanca(clienteCP);
					contas.add(cp);
					System.out.println("Conta poupança criada. Número: " + cp.getNumero());
					break;
				case 3: // Depositar
					System.out.print("Número da conta: ");
					int numContaDep = scanner.nextInt();
					System.out.print("Valor: ");
					double valorDep = scanner.nextDouble();
					Conta contaDep = buscarConta(contas, numContaDep);
					if (contaDep != null) {
						contaDep.depositar(valorDep);
						System.out.println("Depósito realizado.");
					} else {
						System.out.println("Conta não encontrada.");
					}
					break;
				case 4: // Sacar
					System.out.print("Número da conta: ");
					int numContaSaq = scanner.nextInt();
					System.out.print("Valor: ");
					double valorSaq = scanner.nextDouble();
					Conta contaSaq = buscarConta(contas, numContaSaq);
					if (contaSaq != null) {
						contaSaq.sacar(valorSaq);
						System.out.println("Saque realizado.");
					} else {
						System.out.println("Conta não encontrada.");
					}
					break;
				case 5: // Transferir
					System.out.print("Número da conta de origem: ");
					int numOrigem = scanner.nextInt();
					System.out.print("Número da conta de destino: ");
					int numDestino = scanner.nextInt();
					System.out.print("Valor: ");
					double valorTransf = scanner.nextDouble();
					Conta contaOrigem = buscarConta(contas, numOrigem);
					Conta contaDestino = buscarConta(contas, numDestino);
					if (contaOrigem != null && contaDestino != null) {
						contaOrigem.transferir(valorTransf, contaDestino);
						System.out.println("Transferência realizada.");
					} else {
						System.out.println("Conta de origem ou destino não encontrada.");
					}
					break;
				case 6: // Imprimir Extrato
					System.out.print("Número da conta: ");
					int numContaExt = scanner.nextInt();
					Conta contaExt = buscarConta(contas, numContaExt);
					if (contaExt != null) {
						contaExt.imprimirExtrato();
					} else {
						System.out.println("Conta não encontrada.");
					}
					scanner.nextLine();
					break;
				case 7: // lista de cotas do banco
					Conta conta = buscarContas(contas);
					if (conta != null) {
						System.out.println("=== Lista de Contas ===");
						for (Conta c : contas) {
							c.imprimirInfosComuns();
						}
					} else {
						System.out.println("Nenhuma conta encontrada.");
					}
					break;
				case 8:
					System.out.println("Saindo...");
					scanner.close();
					return;
				default:
					System.out.println("Opção inválida.");
			}
			System.out.println("Pressione Enter para continuar...");
			scanner.nextLine();
		}
	}

	private static Conta buscarConta(List<Conta> contas1, int numero) {
		for (Conta conta : contas1) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}
		private static Conta buscarContas(List<Conta> contas1) {
		for (Conta conta : contas1) {
			if (conta != null) {
				return conta;
			}
		}
		return null;
		}
}
