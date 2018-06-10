Aplicação Web com Spring Boot que disponibiliza um webservice REST que recebe como parâmetros um arquivo (PDF) e um CPF como dados de entrada.

Após o recebimento desse PDF e do CPF, o serviço gera uma chave HASH que inclui as seguintes informações
- CPF
- Data/hora da chamada ao webservice
- IP do usuário que chamou o serviço

Para o correto funcionamento da aplicação a propriedade "arquivos.assinados.path" do arquivo "application.properties" deve apontar para
um diretório onde seja possível realizar operações de escrita. Essa propriedade aponta para o diretório onde ficarão armazenados
os arquivos assinados com o hash.
