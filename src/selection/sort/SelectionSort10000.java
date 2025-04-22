package selection.sort;

import java.util.Random;

public class SelectionSort10000 {

    public static void main(String[] args) {
        int tamanho = 10000; // tamanho do vetor
        int repeticoes = 10;
        long[] tempos = new long[repeticoes];

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor = gerarVetorAleatorio(tamanho);

            // Medindo o tempo do SelectionSort apenas
            long inicio = System.nanoTime();
            selectionSort(vetor);  // Aqui é o algoritmo que estamos medindo
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

    // Função SelectionSort
    public static void selectionSort(int[] vetor) {
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            int menorIndice = i;
            for (int j = i + 1; j < n; j++) {
                if (vetor[j] < vetor[menorIndice]) {
                    menorIndice = j;
                }
            }
            int temp = vetor[menorIndice];
            vetor[menorIndice] = vetor[i];
            vetor[i] = temp;
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

