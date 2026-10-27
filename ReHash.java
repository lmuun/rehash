import java.util.Random;

public class ReHash {

    static int tam = 100;
    static int vazio = -1;
    static int[] tabela = new int[tam];
    static int cont = 0;
    static int colisoesTotal = 0;
    static int[] elem = new int[tam];
    static int[] col = new int[tam];

    public static int hash(int chave) {
        return chave % tam;
    }

    public static int rehash(int i) {
        return (i + 1) % tam;
    }

    public static void insere_hashing(int chave) {
        if (cont >= 90) {
            return;
        }

        int i = hash(chave);
        int c = 0;
        while (tabela[i] != vazio) {
            i = rehash(i);
            c++;
            colisoesTotal++;
        }
        tabela[i] = chave;
        elem[cont] = chave;
        col[cont] = c;
        cont++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < tam; i++) {
            tabela[i] = vazio;
        }

        Random gerador = new Random(1090);

        for (int i = 0; i < 100; i++) {
            int n = gerador.nextInt(100001);
            insere_hashing(n);
        }

        System.out.println("total de colisoes: " + colisoesTotal);

        for (int i = 0; i < cont; i++) {
            for (int j = 0; j < cont - 1; j++) {
                if (col[j] < col[j + 1]) {
                    int aux = col[j];
                    col[j] = col[j + 1];
                    col[j + 1] = aux;

                    int aux2 = elem[j];
                    elem[j] = elem[j + 1];
                    elem[j + 1] = aux2;
                }
            }
        }

        System.out.println("3 elementos que mais colidiram:");
        for (int i = 0; i < 3; i++) {
            System.out.println(elem[i] + " : " + col[i] + " colisoes");
        }

        System.out.println("posicoes livres:");
        for (int i = 0; i < tam; i++) {
            if (tabela[i] == vazio) {
                System.out.print(i + " - ");
            }
        }
        System.out.println();
    }
}