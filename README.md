# Documentação da API ViaCep

A API ViaCep fornece informações sobre localidades no Brasil com base no CEP.

## Endpoint

### Obter informações por CEP

- **URL**: `http://localhost:8080/cep/{cep}`

  Substitua `{cep}` pelo CEP desejado no formato XXXXX-XXX ou XXXXXXXX.
- **Parâmetros de URL**:

  | Parâmetro | Tipo   | Descrição                         |
    | --------- | ------ | --------------------------------- |
  | cep       | string | CEP no formato XXXXX-XXX ou XXXXXXXX |
  **Obs**.: Ainda não foi adicionado suporte ao tratamento de erros para inserção de letras ou outros caracteres especiais.


- **Parâmetros de Query**:
  Nenhum.

- **Método**: GET

- **Exemplo de Uso**:

  ```http
  GET http://localhost:8080/cep/01001-000
    ```

- **Resposta de Sucesso**:

  ```
  {
    "cep": "01001-000",
    "logradouro": "Praça da Sé",
    "complemento": "lado ímpar",
    "bairro": "Sé",
    "localidade": "São Paulo",
    "uf": "SP",
    "ibge": "3550308",
    "municipioInfo": {
        "nome": "São Paulo",
        "microrregiao": {
            "nome": "São Paulo",
            "mesorregiao": {
                "nome": "Metropolitana de São Paulo"
            }
        }
    }
  }
  ```