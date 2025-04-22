package ordem.inversa;

public class QuickSortTodosTamanhosInverso {

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

                // Medindo o tempo do QuickSort
                long inicio = System.nanoTime();
                quickSort(vetor, 0, vetor.length - 1);
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

    public static void quickSort(int[] vetor, int low, int high) {
        if (low < high) {
            // Escolhendo um pivô aleatório
            int pi = partition(vetor, low, high);
            quickSort(vetor, low, pi - 1);
            quickSort(vetor, pi + 1, high);
        }
    }

    private static int partition(int[] vetor, int low, int high) {
        // Escolher um pivô aleatório para evitar o pior caso
        int randomIndex = low + (int)(Math.random() * (high - low + 1));
        int pivot = vetor[randomIndex];

        // Troca o pivô aleatório com o último elemento
        int temp = vetor[randomIndex];
        vetor[randomIndex] = vetor[high];
        vetor[high] = temp;

        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (vetor[j] < pivot) {
                i++;
                temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
        }
        temp = vetor[i + 1];
        vetor[i + 1] = vetor[high];
        vetor[high] = temp;
        return i + 1;
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

