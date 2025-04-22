package ordem.aleatoria;

public class SelectionSortTodosTamanhosInverso {

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

                // Medindo o tempo do SelectionSort
                long inicio = System.nanoTime();
                selectionSort(vetor);
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

    // Função SelectionSort
    public static void selectionSort(int[] vetor) {
        int n = vetor.length;

        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < n; j++) {
                if (vetor[j] < vetor[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }

            // Troca o valor mínimo encontrado com o valor na posição i
            int temp = vetor[i];
            vetor[i] = vetor[indiceMinimo];
            vetor[indiceMinimo] = temp;
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
