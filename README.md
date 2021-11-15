![GitHub repo size](https://img.shields.io/github/repo-size/MarceloCPJunior/ProjetoPOO?style=for-the-badge)
# Projeto POO
- Our Anime List
- Api baseada no site <a href="https://myanimelist.net/watch/episode" target="_blank">MyAnimeList</a>, que permite salvar e comentar seus animes favoritos.

## Grupo
- Marcelo de Castro Pereira Junior - 1922082008
- Djalma Farias Bastos Neto - 1822082002

## Tecnologias
As seguintes ferramentas foram usadas na construção do projeto:

- [Java](https://www.java.com/pt-BR/)
- [Spring](https://spring.io)

## Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Postman](https://www.postman.com) 
- [Java](https://www.java.com/pt-BR/) 
- Além disto é bom ter um editor para trabalhar com o código.

## EndPoints
### Autenticação :
`http://localhost:8080/OurAnimeList/user`
- `/signup`     - Criar conta
- `/login`      - Logar em uma conta existente

### Anime :
`http://localhost:8080/OurAnimeList/anime`
- `/search-id`  - Procurar anime por ID
- `/search-all` - Mostrar todos os animes
- #### Somente ADMIN e UPLOADER
- `/save`       - Salvar anime
- `/delete-id`  - Deletar anime por ID
- `/update-id`  - Atualizar anime por ID

### User : 
`http://localhost:8080/OurAnimeList/user`
- `/search-all` - Mostrar todos usuários
- `/search-id` - Procurar usuário por ID
- `/mylist-add-id` - Adicionar anime na lista do usuário
- `/mylist-delete-id` - Deletar anime na lista do usuário
- #### Somente ADMIN
- `/delete-id` - Deletar usuário por ID
- `/update-id` - Atualizar usuário por ID
- `/update-authorization/{id}` - Modificar a autorização de um usuário por ID

### Review :
`http://localhost:8080/OurAnimeList/anime`
- `/add-review` - Comentar review
- `/edit-review` - Editar review
- `/delete-review` - Deletar review

## Padrão para inserção de dados no Postman

### Raw
### Adicionar Anime :
- Para um update é necessário um "id" : 1 acima do "name"
```
{
    "name": "exemplo nome",
    "synopsis": "exemplo sinopse",
    "genres": [
        "alguns"
    ],
    "episodes": 15,
    "status": true,
    "studios":[
        "exemplos"
    ],
    "source": "TV",
    "season": "verão",
    "aired": "data"
}
```
### Signup :
- Para update é necessário um "id": 0 acima do "nickName"
```
{
    "nickName": "aaaaaaaa",
    "email": "asdfa@asdf.asd",
    "password": "Abacaxi_123"
}
```
### Login :
```
{
    "email": "admin1@admin.ad",
    "password": "Aaaaa_123"
}
```

### form-data :
- buscas e delets por ID
- reviews
- atualizar autorização
