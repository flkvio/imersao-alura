import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Conexão HTTP e buscar os top 250 filmes.
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
//        System.out.println(body);

        //Pegar os dados que interessam (titulo, poster, notas).
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir
        for (Map<String,String> filme: listaDeFilmes){
            System.out.println(filme.get("rank") + ". " + "\u001b[1m" +  filme.get("fullTitle") + " - " + filme.get("imDbRating")) ;
            System.out.println(filme.get("image"));
            System.out.println();

        }
    }
}
