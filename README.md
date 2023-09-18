# Rinha de backend (2023 Q3) 🦾

Implementado com o seguinte Stack:
- Java 17
- Spring Boot (Data JPA, Web, Undertow)
- PostgreSQL

> [!IMPORTANT]
> Esse código não entrou na Rinha, perdendo a chance de apanhar feio!

## Como Executar?
No diretório em que "clonaste" esse repositório, execute:
> docker compose -f docker-compose-local.yaml up -d

Para parar os serviços, remover a imagem do projeto e os volumes, execute:
> docker compose -f docker-compose-local.yaml down --rmi local -v

Também é possível levantar somente o banco de dados. Se necesitas fazê-lo, execute:
> docker compose -f docker-compose-database-only.yaml

## "Na minha máquinha funciona..."
<img src="https://memecreator.org/static/images/memes/4915233.jpg" width="50%">

As última duas execuções terminaram com 31269 (_p99 geral_ 21376) e 30053 (_p99 geral_ 14289) pessoas cadastradas. Pelo
ranking apresentado após o concurso, essa implementação ficaria na 14ª ou 15ª posição.

## O que falta?
- O tempo de arranque do Spring Boot está rondando o minuto.
- Muitos `j.i.IOException: Premature close`. Causa não identificada! :(
- Tentar otras configurações da JVM.
