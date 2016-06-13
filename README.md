# Trabalho Inteligencia Artificial I #

### Documentação - Simulador de Competição entre Empresas no Mercado ###

* Apresentação
* Descrição e Modelagem
* Plataforma
* Técnica de IA
* O Simulador

### Apresentação ###
Universidade: Universidade Estadual de Maringá (UEM)

Curso: Ciência da Computação – UEM

Disciplina: Inteligência Artificial I

Trabalho: Simulador de Competição entre Empresas no Mercado


### Descrição e Modelagem 
O programa desenvolvido simula uma competição por clientes e lucro entre duas empresas.

O simulador pode ser visto como um jogo, já que cada empresa toma decisões em forma de turnos e o sistema analisa as jogadas, formando uma rodada completa.

Há 3 modos de jogo, pessoa vs pessoa, pessoa vs computador e computador vs computador. 


#### Regras
A rodada (contabilizada em meses) é feita em 3 turnos: jogador 1, jogador 2 e sistema. 

Cada jogador altera as variáveis em seu turno, e, após ambas jogadas, o sistema avalia as mudanças e investimentos e retorna um resultado baseado em fórmulas, calculando a demanda do mercado, as vendas e lucros de cada empresa.

O jogador ganhador será o que tem mais dinheiro na última rodada. Caso um jogador tenha prejuízo por 3 meses seguidos, este jogador perde o jogo.

#### Modelagem
As configurações iniciais do jogo são escolhidas logo após o modo de jogo. Há três variáveis principais e que são compartilhadas entre os jogadores:
* Ramo do Negócio

 O ramo do negócio define os tipos de estruturas e produtos que estarão no jogo.

* Investimento Inicial

 Dinheiro inicial disponível para cada empresa no começo do jogo. Este dinheiro é usado para comprar a primeira estrutura. 

* Meses até final do jogo

 Total de rodadas até o final do jogo. Uma rodada é concluída após o turno de ambos jogadores e do sistema. 

As configurações de cada jogador são escolhidas e alteradas a cada rodada. Suas variáveis são:
* Estrutura

 Estruturas tem 3 níveis. Cada estrutura produz certos produtos e quantidade por mês dos mesmos de acordo com seu nível. Toda estrutura tem um preço e gasto fixo por mês (luz, água, serviços de internet e telefonia, etc).

* Produtos

 Produtos estarão disponíveis para produção de acordo com a estrutura usada. Cada produto tem um custo de produção fixo, preço de venda e quantidade a ser produzida no mês.

* Estoque

 Todo produto produzido pela empresa é guardado no estoque até ser vendido. O estoque contem os produtos, quantidade e preço de cada um.

* Gasto com Propaganda

 O gasto com propaganda é definido como baixo, médio e alto. Os custos são relacionados ao valor total de venda estoque do jogador, já que a propaganda é feita de todos os produtos simultaneamente. O gasto influencia no cálculo do custo benefício de cada produto feito pelo sistema e também na quantidade de pessoas interessadas no ramo do negócio.

* Gasto com Pesquisa de Mercado

 O gasto com pesquisa de mercado é similar ao gasto com propaganda, também dividido em baixo, médio e alto. Seu valor é baseado no custo de produção do estoque. Influencia no cálculo do custo benefício com peso menor que propaganda.

O turno do sistema é dividido em uma série de cálculos para determinar os resultados da rodada. 
Primeiramente o sistema calcula a quantidade de pessoas interessadas no produto e a demanda do mercado. A fórmula usada é baseada no gasto em propaganda de ambas empresas atuando sobre uma população de consumidores fixa. O peso do gasto em propaganda de cada empresa é o mesmo na equação, ou seja, uma empresa pode se beneficiar da propaganda da outra.  

Após calcular a quantidade de pessoas interessadas no ramo de atividade das empresas, o sistema irá calcular a demanda do mercado. 
Dentre as pessoas interessadas, quais estão interessadas em cada produto deste ramo de atividade. Uma equação é usada para tal cálculo, cada produto tem um peso de acordo com o seu preço e estrutura necessária para produção. 
Produtos baratos e que podem ser produzidos em grandes quantidades possuem um peso maior, ou seja, possui maior clientela. Produtos caros e complexos tem um peso pequeno, um número menor de pessoas estarão interessadas neste tipo de produto.

Com a demanda do mercado definida, o próximo passo do sistema é calcular as vendas de cada empresa. 
O sistema verifica o estoque de cada empresa procurando por produtos que tenham clientes interessados. Conforme o sistema varre o estoque dos jogadores, ele calcula o custo benefício de cada produto usando uma equação. A equação consiste em uma relação do preço de venda com o preço de produção multiplicados por fatores de venda, como o gasto em propaganda e pesquisa, cada um com seu peso.
O custo benefício do produto definirá qual empresa vende determinado produto para um consumidor indeciso. Caso apenas uma empresa tenha o produto desejado pelo consumidor em estoque, não há competição.  

Com os estoques atualizados e produtos vendidos, o sistema calcula os lucros e dinheiro final do turno de cada empresa.
O lucro é calculado basicamente usando a quantidade dos produtos vendidos multiplicado pelo preço de venda definido. 
O dinheiro final do turno leva em conta todas as variáveis até então, custo de produção do estoque inteiro, gasto fixo da estrutura usada, gasto com propaganda e pesquisa e lucro com vendas.   

Este processo se repete até a rodada final, onde o jogador com mais dinheiro é o ganhador. A cada rodada o jogador pode alterar e melhorar suas variáveis, montando sua própria estratégia.

### Plataforma
O computador usado foi um Windows 10, processador i7, 4gb de RAM e GeForce 630M.

O simulador foi desenvolvido na linguagem Java.

### Técnica de IA
A técnica implementada foi uma busca gulosa. O algoritmo procura a melhor configuração de jogada possível usando o máximo de dinheiro disponível. 

Possibilidades são montadas de acordo com cada etapa de escolhas.

Etapas:
* Upgrade de estrutura atual
* Produtos a produzir
* Quantidade de cada produto
* Preço de venda de cada produto
* Gasto com propaganda
* Gasto com pesquisa de mercado

Dentre as possibilidades, a escolhida será a de maior lucro assumindo que o haja compradores para seu estoque.

A escolha gulosa ocorre em cada etapa descrita acima, gerando uma possibilidade gulosa no final.

As IA’s competem de forma igual e não tem acesso a escolhas umas das outras antes do turno do sistema.

### O Simulador
O simulador deve ser executado através da linha de comando 'java -jar trabalhoIA.jar'

Uma interface abrirá com as escolhas do tipo de jogo e a partir dai a simulação começa.

A versão desenvolvida não possui interface gráfica em todas as telas, os turnos e configurações são feitos através do console.


#### Pedro Rocha
