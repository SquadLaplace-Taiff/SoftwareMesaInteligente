# SoftwareMesaInteligente
Trabalho de conclusão de curso que consiste na elaboração de um software para gerenciar uma mesa de testes de produtos da empresa Taiff.

O software visa atender algumas necessidades básicas do cliente no momento do teste de produtos como secadores e escovas. A primeira delas consiste na capacidade de gerenciar a movimentação dos eixos motorizados que mantém os produtos e termopares na posição correta. Nossa solução foi a criação de uma API que coordena os dados relacionados ao posicionamento da mesa separadamente, e abaixo você pode conferir como ela funciona:

 1. API de Posicionamento
{ Para maior detalhamento das informações, você pode conferir a documentação dessa API no link abaixo:
https://github.com/SquadLaplace-Taiff/SoftwareMesaInteligente/blob/main/API-Posicionamento/Proposta-v1/endPoints-posicionamento-v0.1.3.yaml }

Essa API consiste em apenas um endpoint que suporta 6 tipos de requisição HTTP:
 - a) POST - /posicao/teste - Cria um novo teste
 - b) GET - /posicao/teste  - Busca todos os testes salvos
 - c) GET - /posicao/teste/{modelo} - Busca um teste específico a partir do modelo do produto
 - d) GET - /posicao/teste/id/{id} - Busca um teste específico a partir do seu próprio ID
 - e) PUT - /posicao/teste/{id} - Edita um teste existente a partir de seu próprio ID
 - f) DELETE - /posicao/teste/{id} - Exclui um teste existente a partir de seu próprio ID
 
## Passo a Passo para construção (build) ou inicialização dos containers
