# Trabalho Inteligencia Artificial I #

### Documentação do Trabalho ###

* Objetivos
* Regras

### Objetivos ###
Os jogadores deverão administrar suas empresas para obter maior lucro no final de X meses definidos no começo do jogo.

### Regras ###
A rodada (mês) é feita em 3 turnos: jogador 1, 2 e sistema. Cada jogador altera as variáveis em seu turno e após ambas jogadas, o sistema avalia as mudanças e investimentos e retorna um resultado baseado em fórmulas, calculando as vendas e lucros de cada empresa.
O jogador ganhador será o que obteve mais lucro até a última rodada. Caso um jogador tenha prejuízo por 3 meses seguidos, este jogador perde o jogo.

### Definições ###
#### Configuração Inicial do Jogo – Geral ####
- Escolher Ramo do Negócio
- Escolher Investimento Inicial
- Escolher Quantidade de Meses (rodadas)

 1. Ramo do Negócio 
Cada ramo possui prédios e produtos específicos. As duas empresas escolhem o mesmo ramo para competir.

 2. Investimento Inicial  
Dinheiro disponível para cada empresa no começo do jogo. O valor é o mesmo para ambas empresas.

 3. Meses (rodadas)  
Total de meses até o final do jogo. Um mês é concluído quando o turno do jogador 1, jogador 2 e turno do sistema são finalizados.

### Configuração de cada jogador ###
Escolher:
- Prédio Inicial
- Produtos a produzir
- Quantidade a produzir
- Preço de venda
- Gasto com propaganda
- Gasto em pesquisa de mercado

1. Prédios 
Prédios tem 3 níveis. Cada nível produz certos produtos e quantidade de produtos. O prédio pode ser melhorado posteriormente durante o jogo. Cada prédio tem um gasto fixo por mês (luz, água, etc.).
 
2. Produtos 
Produtos estarão disponíveis para produção de acordo com o prédio escolhido. Cada produto tem um valor fixo de custo para produzir e um valor escolhido pelo jogador para venda. 

3. Quantidade a Produzir 
A quantidade de produtos a se produzir por mês é limitada pelo prédio escolhido. Produtos não vendidos em um mês permanecem no estoque.

4. Preço de Venda 
O preço escolhido pelo jogador para vender determinado produto. Os preços são baseados no custo de produção do produto. As opções são:
. 1.25 x Preço de Produção
. 1.50 x Preço de Produção
. 2.00 x Preço de Produção

5. Gasto com Propaganda 
O gasto com propaganda influencia na chance de vender o estoque inteiro do jogador e seu preço é baseado no valor do estoque atual. É divido em:
. Baixo – 20% de chance, 5% do valor do estoque
. Medio – 55% de chance, 15% do valor do estoque
. Alto – 90% de chance, 25% do valor do estoque

6. Gasto em Pesquisa de Mercado 
A pesquisa de mercado influencia em quais produtos serão vendidos. É dividido em investimento baixo, médio e alto.

### Sistema ###
O sistema analisará cada um dos inputs dos jogadores e usará fórmulas para decidir qual o resultado da rodada. 
Equações utilizadas:

FatorVenda = (Propaganda * PrecoVendaProduto) + (PesquisaMercado * QuantidadeProduzida)

UnidadesVendidas = DemandaMercado * FatorVenda

Lucro = (UnidadesVendidas * PrecoVenda) – (QuantidadeProduzida * PrecoProducao) – (GastosFixos – GastoPesquisa – GastoPropaganda - GastoNovoPredio)

Propaganda: valor de 0 a 1, dependendo do valor investido. Este valor influencia diretamente no preço que o seu produto consegue ser vendido.
PesquisaMercado: valor de 0 a 1, dependendo do valor investido. Este valor influencia diretamente na quantidade de produtos que serão vendidos no mês.
Lucro > 0 indica lucro, caso contrário o resultado do mês foi negativo.

.Pedro Rocha
