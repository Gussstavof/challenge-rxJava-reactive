# 🧩 RxJava — Reactive Order Processing Challenge

> 🇺🇸 English version below
> 🇧🇷 Versão em português abaixo

---

# 🇺🇸 English

## 📌 Objective

Build a reactive pipeline using **RxJava** to process a list of orders concurrently.

The goal is to practice the following reactive programming concepts:

* `Observable`
* `filter`
* `map`
* `flatMap`
* `subscribeOn`
* `Schedulers.io()`
* `subscribe`
* `toBlocking()`
* Concurrency control

---

## 📦 Model

Consider the following class:

```java
public class Order {

    private Long id;
    private String customer;
    private double price;

    // constructors, getters and setters
}
```

The application receives the following orders:

| ID | Customer |     Price |
| -: | -------- | --------: |
|  1 | João     | R$ 150.00 |
|  2 | Maria    |  R$ 80.00 |
|  3 | Pedro    | R$ 300.00 |
|  4 | Ana      |  R$ 50.00 |
|  5 | Carlos   | R$ 220.00 |

---

# 🎯 Requirements

### 1. Create a reactive stream

Convert the list of orders into an `Observable`.

---

### 2. Filter the orders

Process only orders whose price is **greater than or equal to R$ 100.00**.

The following orders must be ignored:

```text
Maria - R$ 80.00
Ana   - R$ 50.00
```

---

### 3. Apply a discount

Apply a **10% discount** to valid orders.

Example:

```text
R$ 150.00 → R$ 135.00
```

Expected values after the discount:

```text
Order 1 → R$ 135.00
Order 3 → R$ 270.00
Order 5 → R$ 198.00
```

---

### 4. Simulate a database query

For each valid order, simulate a time-consuming database operation.

The operation should take approximately **1 second**.

> Do not use a blocking operation as the final solution. Use RxJava operators to represent the delay.

---

### 5. Process orders concurrently

The orders must be processed **concurrently**.

Since there are three valid orders, the expected processing time should be approximately:

```text
≈ 1 second
```

rather than:

```text
≈ 3 seconds
```

Conceptually:

```text
Order 1 ──────── 1s ────────┐
                             │
Order 3 ──────── 1s ────────┼──→ Completed
                             │
Order 5 ──────── 1s ────────┘
```

---

### 6. Use a Scheduler

Use:

```java
Schedulers.io()
```

to execute the simulated I/O operations.

---

### 7. Display the results

When each order finishes processing, display information similar to:

```text
Order 1 processed: 135.0
Order 3 processed: 270.0
Order 5 processed: 198.0
```

Also display the thread responsible for processing.

Example:

```text
Order 1 processed: 135.0 - thread: RxIoScheduler-1
```

---

### 8. Wait for the stream to finish

The application must not terminate while asynchronous operations are still running.

The `main` method must wait for the `Observable` to complete using RxJava mechanisms.

**Do not use:**

```java
Thread.sleep(...)
```

to keep the application running.

---

# 🚀 Bonus Challenge — Concurrency Control

After completing the first version, modify the program so that **at most 2 orders can be processed concurrently**.

With three valid orders, the expected behavior is approximately:

```text
Order 1 ──────── 1s ────────┐
                             │
Order 3 ──────── 1s ────────┤
                             │
                             ├──→ First 2 complete
                             │
Order 5 ──────── 1s ────────┘
```

Expected processing time:

```text
≈ 2 seconds
```

---

# 🚫 Restrictions

Do not use:

* `for`
* `CompletableFuture`
* `parallelStream`
* Manually created `Thread`s
* `Thread.sleep()` to control application execution

Use RxJava to build the reactive pipeline.

---

# 💡 Concepts to Investigate

During the implementation, try to understand:

```text
Observable
filter
map
flatMap
subscribeOn
observeOn
Schedulers.io()
toBlocking()
```

Especially:

> What is the difference between `subscribeOn()` and `observeOn()`?

And:

> How can `flatMap()` control the number of concurrent operations?

---

# ✅ Expected Result

The application should:

1. Ignore orders below R$ 100.00.
2. Apply a 10% discount to valid orders.
3. Process valid orders concurrently.
4. Use `Schedulers.io()`.
5. Display the results.
6. Wait for the actual completion of the stream.
7. Later allow concurrency to be limited to 2 simultaneous operations.

---

## 📚 Learning Goals

After completing this challenge, you should be able to explain:

* How an `Observable` represents a stream of data.
* How `filter` and `map` transform a stream.
* How `flatMap` works with multiple streams.
* How RxJava executes operations using different `Schedulers`.
* The difference between `subscribeOn` and `observeOn`.
* How to work with asynchronous operations.
* How to control concurrency in a reactive pipeline.
* How to wait for a reactive stream to complete without using `Thread.sleep()`.

---

---

# 🇧🇷 Português

## 📌 Objetivo

Criar um fluxo reativo utilizando **RxJava** para processar uma lista de pedidos de forma concorrente.

O objetivo é praticar os seguintes conceitos de programação reativa:

* `Observable`
* `filter`
* `map`
* `flatMap`
* `subscribeOn`
* `Schedulers.io()`
* `subscribe`
* `toBlocking()`
* Controle de concorrência

---

## 📦 Modelo

Considere a seguinte classe:

```java
public class Order {

    private Long id;
    private String customer;
    private double price;

    // constructors, getters and setters
}
```

A aplicação recebe os seguintes pedidos:

| ID | Cliente |     Valor |
| -: | ------- | --------: |
|  1 | João    | R$ 150,00 |
|  2 | Maria   |  R$ 80,00 |
|  3 | Pedro   | R$ 300,00 |
|  4 | Ana     |  R$ 50,00 |
|  5 | Carlos  | R$ 220,00 |

---

# 🎯 Requisitos

### 1. Criar um fluxo reativo

Transforme a lista de pedidos em um `Observable`.

---

### 2. Filtrar os pedidos

Processe somente pedidos cujo valor seja **maior ou igual a R$ 100,00**.

Os seguintes pedidos devem ser ignorados:

```text
Maria - R$ 80,00
Ana   - R$ 50,00
```

---

### 3. Aplicar desconto

Aplique um desconto de **10%** nos pedidos válidos.

Exemplo:

```text
R$ 150,00 → R$ 135,00
```

Valores esperados após o desconto:

```text
Pedido 1 → R$ 135,00
Pedido 3 → R$ 270,00
Pedido 5 → R$ 198,00
```

---

### 4. Simular uma consulta ao banco

Para cada pedido válido, simule uma operação de banco de dados demorada.

A operação deve levar aproximadamente **1 segundo**.

> Não utilize uma operação bloqueante na solução final. Utilize operadores do RxJava para representar o atraso.

---

### 5. Processar os pedidos de forma concorrente

Os pedidos devem ser processados **concorrentemente**.

Como existem três pedidos válidos, o tempo esperado deve ser aproximadamente:

```text
≈ 1 segundo
```

e não:

```text
≈ 3 segundos
```

Conceitualmente:

```text
Pedido 1 ──────── 1s ────────┐
                              │
Pedido 3 ──────── 1s ────────┼──→ Concluído
                              │
Pedido 5 ──────── 1s ────────┘
```

---

### 6. Utilizar um Scheduler

Utilize:

```java
Schedulers.io()
```

para executar as operações de I/O simuladas.

---

### 7. Exibir os resultados

Ao finalizar cada pedido, exiba informações semelhantes a:

```text
Pedido 1 processado: 135.0
Pedido 3 processado: 270.0
Pedido 5 processado: 198.0
```

Também exiba a thread responsável pelo processamento.

Exemplo:

```text
Pedido 1 processado: 135.0 - thread: RxIoScheduler-1
```

---

### 8. Aguardar o término do fluxo

A aplicação não deve terminar enquanto existirem operações assíncronas em execução.

O método `main` deve aguardar o término do `Observable` utilizando os recursos do próprio RxJava.

**Não utilize:**

```java
Thread.sleep(...)
```

para manter a aplicação aberta.

---

# 🚀 Desafio Bônus — Controle de Concorrência

Depois de concluir a primeira versão, altere o programa para permitir que **no máximo 2 pedidos sejam processados simultaneamente**.

Com três pedidos válidos, o comportamento esperado será aproximadamente:

```text
Pedido 1 ──────── 1s ────────┐
                              │
Pedido 3 ──────── 1s ────────┤
                              │
                              ├──→ Primeiros 2 terminam
                              │
Pedido 5 ──────── 1s ────────┘
```

Tempo esperado:

```text
≈ 2 segundos
```

---

# 🚫 Restrições

Não utilize:

* `for`
* `CompletableFuture`
* `parallelStream`
* `Thread`s criadas manualmente
* `Thread.sleep()` para controlar a execução da aplicação

Utilize RxJava para construir o fluxo reativo.

---

# 💡 Conceitos para investigar

Durante a implementação, procure entender:

```text
Observable
filter
map
flatMap
subscribeOn
observeOn
Schedulers.io()
toBlocking()
```

Especialmente:

> Qual é a diferença entre `subscribeOn()` e `observeOn()`?

E:

> Como o `flatMap()` pode controlar a quantidade de operações concorrentes?

---

# ✅ Resultado Esperado

A aplicação deverá:

1. Ignorar pedidos abaixo de R$ 100,00.
2. Aplicar 10% de desconto nos pedidos válidos.
3. Processar os pedidos válidos de forma concorrente.
4. Utilizar `Schedulers.io()`.
5. Exibir os resultados.
6. Aguardar o término real do fluxo.
7. Permitir posteriormente limitar a concorrência para 2 operações simultâneas.

---

## 📚 Objetivos de Aprendizagem

Ao finalizar este desafio, você deverá ser capaz de explicar:

* Como um `Observable` representa um fluxo de dados.
* Como `filter` e `map` transformam um fluxo.
* Como `flatMap` trabalha com múltiplos fluxos.
* Como o RxJava executa operações utilizando diferentes `Schedulers`.
* A diferença entre `subscribeOn` e `observeOn`.
* Como trabalhar com operações assíncronas.
* Como controlar concorrência em um fluxo reativo.
* Como aguardar a conclusão de um fluxo sem utilizar `Thread.sleep()`.

---

## 🚀 Challenge Complete?

When you're done, compare your implementation against the requirements and measure the execution time.

Quando terminar, compare sua implementação com os requisitos e meça o tempo de execução.
