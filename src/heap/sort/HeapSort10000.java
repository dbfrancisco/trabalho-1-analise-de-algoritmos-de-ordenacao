package heap.sort;

import java.util.Random;

public class HeapSort10000 {

    public static void main(String[] args) {
        int tamanho = 10000; // tamanho do vetor
        int repeticoes = 10;
        long[] tempos = new long[repeticoes];

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor = gerarVetorAleatorio(tamanho);

            // Medindo o tempo do HeapSort apenas
            long inicio = System.nanoTime();
            heapSort(vetor);  // Aqui é o algoritmo que estamos medindo
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

    // Função HeapSort
    public static void heapSort(int[] vetor) {
        int n = vetor.length;

        // Construir o heap (reorganiza o vetor)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }

        // Extrair elementos do heap um por um
        for (int i = n - 1; i >= 0; i--) {
            // Move o elemento atual para o final
            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;

            // Chama heapify no vetor reduzido
            heapify(vetor, i, 0);
        }
    }

    // Função para manter a propriedade do heap
    public static void heapify(int[] vetor, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n && vetor[esquerda] > vetor[maior]) {
            maior = esquerda;
        }

        if (direita < n && vetor[direita] > vetor[maior]) {
            maior = direita;
        }

        if (maior != i) {
            int troca = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = troca;

            heapify(vetor, n, maior);
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

