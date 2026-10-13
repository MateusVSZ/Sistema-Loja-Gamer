package com.mycompany.mazinhoperifericos;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Mateus
 */
public class Cliente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Entrada do cliente
        String pedido = sc.nextLine();

        //Separador ; pra cada pedido
        String[] produto = pedido.split(";");

        //tirar os espaços em branco para nao dar erro
        for (int i = 0; i < produto.length; i++) {

            produto[i] = produto[i].trim();

        }
        //produto
        String nomeProduto = produto[0];
        int qtd = Integer.parseInt(produto[1]);
        double preco_unitario = Double.parseDouble(produto[2]);
        String estadoDeEntrega = produto[3];

        double subTotal = calcularSubTotal(qtd, preco_unitario);
        double desconto = desconto(qtd, preco_unitario);
        double frete = calcularFrete(estadoDeEntrega, qtd, preco_unitario);
        double totalFinal = calcularTotalFinal(qtd, preco_unitario, estadoDeEntrega);
        mostrarResumo(nomeProduto, qtd, subTotal, desconto, frete, totalFinal);

    }

    //processamento do total
    public static double calcularSubTotal(int qtd, double preco_unitario) {
        double subTotal;

        subTotal = qtd * preco_unitario;

        return subTotal;
    }

    //aplicar desconto
    public static double desconto(int qtd, double preço_unitario) {
        double subTotal = calcularSubTotal(qtd, preço_unitario);
        double desconto;

        if (subTotal >= 200) {
            desconto = subTotal * 0.10;

        } else {
            desconto = 0.00;
        }
        return desconto;
    }

    public static double depoisDesconto(int qtd, double preco_unitario) {
        double depoisDesconto = desconto(qtd, preco_unitario);
        double subTotal = calcularSubTotal(qtd, preco_unitario);
        double descontoFinal = subTotal - depoisDesconto;

        return descontoFinal;
    }
//calcular frete

    public static double calcularFrete(String estadoDeEntrega, int qtd, double preco_unitario) {
        double frete;
        double subTotal = calcularSubTotal(qtd, preco_unitario);
        double totalDepoisDesconto = depoisDesconto(qtd, preco_unitario);

        String[] freteEstados = {"SP", "MG", "RJ"};

        if (totalDepoisDesconto >= 250) {
            frete = 0;
        } else {

            for (String estado : freteEstados) {
                if (estadoDeEntrega.equals(estado)) {
                    return 12.00;
                }
            }
            return 25.00;
        }

        return frete;

    }

    //calcular total frete mais subtotal com desconto
    public static double calcularTotalFinal(int qtd, double preco_unitario, String estadoDeEntrega) {
        double subTotal = calcularSubTotal(qtd, preco_unitario);
        double frete = calcularFrete(estadoDeEntrega, qtd, preco_unitario);
        double desconto = desconto(qtd, preco_unitario);
        double totalFinal = frete + subTotal;
        totalFinal -= desconto;

        return totalFinal;
    }

    public static void mostrarResumo(String produto, int qtd, double subTotal, double desconto, double frete, double totalFinal) {

        System.out.printf("Produto %s\n", produto);

        System.out.printf("Quantidade %d\n", qtd);

        System.out.printf("SubTotal %.2f%n", subTotal);

        System.out.printf("Desconto %.2f%n", desconto);

        if (frete == 0) {
            System.out.printf("Frete gratis");
        } else {
            System.out.printf("Frete %.2f", frete);
        }

        System.out.printf("%nTotal final %.2f%n", totalFinal);
    }
}
