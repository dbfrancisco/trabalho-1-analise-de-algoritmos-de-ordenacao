package ordem.aleatoria;

public class HeapSortTodosTamanhosInverso {

    public static void main(String[] args) {
        // Tamanhos dos vetores que você vai testar
        int[] tamanhos = {10000, 50000, 100000, 300000, 800000, 1000000};
        int repeticoes = 10;

        // Para cada tamanho de vetor
        for (int tamanho : tamanhos) {
            System.out.println("Testando vetor de tamanho: " + tamanho);
            long[] tempos = new long[repeticoes];

            for (int i = 0; i < repeticoes; i++) {
                int[] vetor = gerarVetorInverso(tamanho);

                // Medindo o tempo do HeapSort
                long inicio = System.nanoTime();
                heapSort(vetor);
                long fim = System.nanoTime();

                tempos[i] = fim - inicio;
                System.out.printf("Execução %d: %.3f ms%n", i + 1, tempos[i] / 1_000_000.0);
            }

            double media = calcularMedia(tempos);
            double desvio = calcularDesvioPadrao(tempos, media);

            System.out.printf("Tempo médio para vetor de tamanho %d: %.3f ms%n", tamanho, media / 1_000_000.0);
            System.out.printf("Desvio padrão: %.3f ms%n", desvio / 1_000_000.0);
            System.out.println();
        }
    }

    // Função HeapSort
    public static void heapSort(int[] vetor) {
        int n = vetor.length;

        // Criar um heap (rearranjo do vetor)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }

        // Extrair elementos do heap um por um
        for (int i = n - 1; i >= 0; i--) {
            // Troca o root (maior valor) com o último elemento
            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;

            // Chama heapify no vetor reduzido
            heapify(vetor, i, 0);
        }
    }

    // Função para "heapificar" um subvetor
    public static void heapify(int[] vetor, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        // Se o filho esquerdo for maior que o pai
        if (esquerda < n && vetor[esquerda] > vetor[maior]) {
            maior = esquerda;
        }

        // Se o filho direito for maior que o maior valor até agora
        if (direita < n && vetor[direita] > vetor[maior]) {
            maior = direita;
        }

        // Se o maior não for o nó atual
        if (maior != i) {
            int temp = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = temp;

            // Recursivamente "heapifica" o subárvore afetado
            heapify(vetor, n, maior);
        }
    }

    // Gera vetor em ordem inversa
    public static int[] gerarVetorInverso(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = tamanho - i; // Ex: 10000, 9999, ..., 1
        }
        return vetor;
    }

    public static double calcularMedia(long[] tempos) {
        long soma = 0;
        for (long tempo : tempos) {
            soma += tempo;
        }
        return (double) soma / tempos.length;
    }

    public static double calcularDesvioPadrao(long[] tempos, double media) {
        double soma = 0;
        for (long tempo : tempos) {
            soma += Math.pow(tempo - media, 2);
        }
        return Math.sqrt(soma / tempos.length);
    }
}

