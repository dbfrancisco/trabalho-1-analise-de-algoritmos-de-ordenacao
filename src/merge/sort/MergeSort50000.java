package merge.sort;

import java.util.Random;

public class MergeSort50000 {
    public static void main(String[] args) {
        int tamanho = 50000; // tamanho do vetor
        int repeticoes = 10;
        long[] tempos = new long[repeticoes];

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor = gerarVetorAleatorio(tamanho);

            // Medindo o tempo do MergeSort apenas
            long inicio = System.nanoTime();
            mergeSort(vetor);  // Aqui é o algoritmo que estamos medindo
            long fim = System.nanoTime();

            tempos[i] = fim - inicio;
            System.out.printf("Execução %d: %.3f ms%n", i + 1, tempos[i] / 1_000_000.0);
        }

        // Cálculo da média e desvio padrão
        double media = calcularMedia(tempos);
        double desvio = calcularDesvioPadrao(tempos, media);

        System.out.printf("Tempo médio: %.3f ms%n", media / 1_000_000.0);
        System.out.printf("Desvio padrão: %.3f ms%n", desvio / 1_000_000.0);
    }

    // Função MergeSort
    public static void mergeSort(int[] vetor) {
        if (vetor.length < 2) {
            return;
        }
        int meio = vetor.length / 2;
        int[] esquerda = new int[meio];
        int[] direita = new int[vetor.length - meio];

        System.arraycopy(vetor, 0, esquerda, 0, meio);
        System.arraycopy(vetor, meio, direita, 0, vetor.length - meio);

        mergeSort(esquerda);
        mergeSort(direita);

        merge(vetor, esquerda, direita);
    }

    // Função para mesclar dois vetores ordenados
    public static void merge(int[] vetor, int[] esquerda, int[] direita) {
        int i = 0, j = 0, k = 0;
        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] <= direita[j]) {
                vetor[k++] = esquerda[i++];
            } else {
                vetor[k++] = direita[j++];
            }
        }

        while (i < esquerda.length) {
            vetor[k++] = esquerda[i++];
        }

        while (j < direita.length) {
            vetor[k++] = direita[j++];
        }
    }

    // Gera vetor com valores aleatórios
    public static int[] gerarVetorAleatorio(int tamanho) {
        Random rand = new Random();
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = rand.nextInt(1000000); // até 1 milhão
        }
        return vetor;
    }

    // Calcula média dos tempos
    public static double calcularMedia(long[] tempos) {
        long soma = 0;
        for (long tempo : tempos) {
            soma += tempo;
        }
        return (double) soma / tempos.length;
    }

    // Calcula desvio padrão
    public static double calcularDesvioPadrao(long[] tempos, double media) {
        double soma = 0;
        for (long tempo : tempos) {
            soma += Math.pow(tempo - media, 2);
        }
        return Math.sqrt(soma / tempos.length);
    }
}
