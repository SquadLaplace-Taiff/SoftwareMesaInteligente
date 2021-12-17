# SoftwareMesaInteligente
Trabalho de conclusão de curso que consiste na elaboração de um software para gerenciar uma mesa de testes de produtos da empresa Taiff.

O software visa atender algumas necessidades básicas do cliente no momento do teste de produtos como secadores e escovas. A primeira delas consiste na capacidade de gerenciar a movimentação dos eixos motorizados que mantém os produtos e termopares na posição correta. Nossa solução foi a criação de uma API que coordena os dados relacionados ao posicionamento da mesa separadamente, e abaixo você pode conferir como ela funciona:

Documentação:

- Posicionamento: [Documentação em Swagger](https://github.com/SquadLaplace-Taiff/SoftwareMesaInteligente/blob/main/API-Posicionamento/Documenta%C3%A7%C3%A3o/endPoints-posicionamento-v0.1.5.yaml)

- Temperatura: [Documentação em Swageer](https://github.com/SquadLaplace-Taiff/SoftwareMesaInteligente/blob/main/API-Temperatura/Documentacao/API-Temperatura-Swagger.yaml)
 
## Passo a Passo para construção (build) ou inicialização dos containers

1) Primeiro instale o Docker em seu computador: [Tutorial de instalação do Docker](https://docs.docker.com/desktop/windows/install/)
2) Clone o projeto em seu computador.
3) Acesse o diretório do projeto e abra ele em um terminal e execute o seguinte comando:
```sh
docker-compose up -d
```
![print do comando](/assets/comando-compose.png)
Aguarde de 2 a 5 minutos até o container ser criado.

4) Após a criação abra o docker e clique no simbolo de play.
![Iniciando o container](/assets/Iniciar-container.png)

5) Após iniciar o container acesse: [localhost:3000](http://localhost:3000/) em seu navegador.



Para futuras inicializações siga os passos 4 e 5.

Caso queira finalizar o container clique no quadrado do lado direito do play.
![parar execução do container](/assets/parar-container.png)