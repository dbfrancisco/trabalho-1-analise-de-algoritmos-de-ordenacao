package ordem.inversa;

public class MergeSortTodosTamanhosInverso {

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

                // Medindo o tempo do MergeSort
                long inicio = System.nanoTime();
                mergeSort(vetor);
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

    public static void mergeSort(int[] vetor) {
        if (vetor.length > 1) {
            // Encontrando o ponto médio do vetor
            int meio = vetor.length / 2;
            int[] esquerda = new int[meio];
            int[] direita;

            if (vetor.length % 2 == 0) {
                direita = new int[meio];
            } else {
                direita = new int[meio + 1];
            }

            // Copiando os elementos para os vetores temporários
            System.arraycopy(vetor, 0, esquerda, 0, meio);
            System.arraycopy(vetor, meio, direita, 0, vetor.length - meio);

            // Recursivamente ordena os dois subvetores
            mergeSort(esquerda);
            mergeSort(direita);

            // Mescla os subvetores ordenados
            merge(vetor, esquerda, direita);
        }
    }

    public static void merge(int[] vetor, int[] esquerda, int[] direita) {
        int i = 0, j = 0, k = 0;

        // Ordena e mescla os elementos de esquerda e direita
        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] <= direita[j]) {
                vetor[k++] = esquerda[i++];
            } else {
                vetor[k++] = direita[j++];
            }
        }

        // Copia os elementos restantes de esquerda, se houver
        while (i < esquerda.length) {
            vetor[k++] = esquerda[i++];
        }

        // Copia os elementos restantes de direita, se houver
        while (j < direita.length) {
            vetor[k++] = direita[j++];
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
